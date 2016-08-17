//------------------------------------
// Class contains the genetic algorithm to
// create solutions for the given input
//------------------------------------
import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.management.Attribute;

public class TimeTabler{

	static Schedule [] schedules = new Schedule [100];  // random solutions
	static ArrayList<Professor> professors = new ArrayList<Professor>();
	static ArrayList<Class> classes = new ArrayList<Class>();
	static ArrayList<Student> students = new ArrayList<Student>();
	static ArrayList<Course> courses = new ArrayList<Course>();
	static ArrayList<Classroom> rooms = new ArrayList<Classroom>();

	public static void main (String [] args){
		double mutationProb = 0.1; 		// Chance for any solution to mutate? [0,1]
		double fitThreshold = 0.95;		// Necessary fitness to end evolution [0,1]
		int numKilled = 20;				// Number of solutions killed/reborn each generation
		int numParents = 10;			// Number of top solutions to use for crossover
		int maxGens = 100;				// Maximum number of evolutions (will quit after this many regardless of fit)
		Random r = new Random();		// Random doubles for random mutation

		createObjects(new File("input.xml"));		// Creates objects by parsing xml

		// Generates schedules
		for(int i = 0; i < schedules.length; i++){
			schedules[i] = generateRandomSchedule();		// Generates random schedule based on all objects
		}

		// Evolve until fitness threshold is met
		int genCounter = 0;		// Tracks num of generations
		while(schedules[0].getFitness() < fitThreshold && genCounter < maxGens){
			genCounter ++; 		// Increment generation counter

			// Calculate fitness of every solution
			for(int i = 0; i < schedules.length; i++){
				schedules[i].calcFitness(professors, classes, students);
			}

			sortByFitness();				// Sorts schedules by fitness

			// perform crossover for all survivors to next generation
			for(int i = numKilled; i < 100; i ++){
				schedules[i] = performCrossover(schedules[i]);
			}

			// Kill worst solutions and create new ones
			for(int i = 0; i < numKilled; i++){
				schedules[i] = generateChildren();	// Random crossover from top solutions
			}

			/*
			// MUTATION NOW LUMPED IN WITH CROSSOVER
			// Perform random mutation on schedules
			for(int i = 0; i < schedules.length; i++){
			if(r.nextDouble() <= mutationProb){
			mutate(schedules[i]);
			}
			}
			*/
		}

		// Hooray, we're done!

	}

	//--------------------------
	// Creates all objects from xml(?) file using SAX(?)
	// This method only does the xml parsing
	// Calls various add*() methods to actually add objects to ALs
	//--------------------------
	public static void createObjects(File input){
		// Create profs, classes, etc.
		// Set up SAX Parser
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		SAXParser parser = null;
		try{
			parser = factory.newSAXParser();
		}catch(ParserConfigurationException e){
			System.out.println(e);
			System.exit(-1);
		}catch(SAXException e){
			System.out.println(e);
			System.exit(-1);
		}

		// Parse
		XMLReader reader;
		try{
			reader = parser.getXMLReader();
			reader.setContentHandler(new SaxParser());
			reader.parse(SaxParser.convertToFileURL(input.toString()));
		}catch(SAXException e){
			System.out.println(e);
			System.exit(-1);
		}catch(IOException e){
			System.out.println(e);
			System.exit(-1);
		}
	}

	public static Schedule mutate(Schedule schedule){
		// perform random mutation
		Random r = new Random();

		ArrayList<Course> missing = new ArrayList<Course>();
		// Finds courses not represented in schedule
		for(Course c : courses){
			boolean containsCourse = false;

innerCheck:
			for(List<Class> classes : schedule.getSched().values()){
				for(Class theClass : classes){
					if(theClass.getCourse() == c){
						containsCourse = true;
						break innerCheck;
					}
				}
			}

			if(!containsCourse){
				missing.add(c);
			}
		}

		// Find duplicate courses
		ArrayList<Course> dupes = new ArrayList<Course>();
		for(int i = 9; i <= 17; i++){
			List<Class> sched1 = schedule.getSched().get(i);
			for(int j = 8; j < i; j++){
				List<Class> sched2 = schedule.getSched().get(i);
				for(Class c1 : sched1){
					for(Class c2 : sched2){
						if(c1.getCourse() == c2.getCourse()){
							dupes.add(c1.getCourse());
						}
					}
				}

			}
		}

		Schedule newSched = schedule;

		for(Course c : missing){

			Class gend = generateClass(c);

			int chosenDupe = r.nextInt(dupes.size());
			int whatTime = 7;
outermutate:
			for(List<Class> classes : schedule.getSched().values()){
				whatTime++;
				for(Class aClass : classes){
					if(aClass.getCourse() == c){
						if(r.nextInt(2) == 0){
							classes.set(classes.indexOf(aClass), gend);
						}else{
							classes.set(classes.lastIndexOf(aClass), gend);
						}
						dupes.remove(chosenDupe);
						newSched.putList(whatTime, classes);
						break outermutate;
					}
				}
			}

		}

		return newSched;
	}

	public static Class generateClass(Course course){
		Random r = new Random();

		Professor prof = new Professor();
		Class aClass = new Class();
		Classroom room = new Classroom();
		Course c;

		//int timeslot = r.nextInt(10) + 8;

		c = course;
		ArrayList<Professor> possible = new ArrayList<Professor>();
		for(Professor p : professors){
			if(p.getCourses().contains(c)){
				possible.add(p);
			}
		}

		aClass.setCourse(c);
		int theRoom = r.nextInt(rooms.size());
		aClass.setRoom(rooms.get(theRoom));

		return aClass;	
	}


	public static Schedule generateChildren(){
		// Replaces worst solutions with children of top solutions
		Random r = new Random();

		Schedule first = schedules[r.nextInt(10) + 90];

		Schedule child = performCrossover(first);

		return child;


	}

	public static Schedule performCrossover(Schedule schedule){
		// Create new schedule thru crossover with old schedule and random top solution
		Random r = new Random();
		Schedule newSched = new Schedule();

		Schedule breeder = schedules[99 - r.nextInt(10)]; 		// Random top 10 schedule

		// Generates new schedule from random crossover
		for(int i = 8; i <= 17; i++){
			int chooseSched = r.nextInt(2); 		// 0/1 to decide which sched to pull from
			if(chooseSched == 0){
				newSched.putList(i, schedule.getClassesAt(i));
			}else{
				newSched.putList(i, breeder.getClassesAt(i));
			}
		}

		// Fixes course duplicates through random mutation of those duplicates
		newSched = mutate(newSched);

		return newSched;
	}

	public static Schedule generateRandomSchedule(){
		// Generate the random schedule
		Schedule sched = new Schedule();
		Random r = new Random();

		Professor prof = new Professor();
		Class aClass = new Class();
		Classroom room = new Classroom();

		int timeslot = r.nextInt(10) + 8;

		for(Course c : courses){
			ArrayList<Professor> possible = new ArrayList<Professor>();
			for(Professor p : professors){
				if(p.getCourses().contains(c)){
					possible.add(p);
				}
			}

			aClass.setCourse(c);
			int theRoom = r.nextInt(rooms.size());
			aClass.setRoom(rooms.get(theRoom));

			sched.addClass(timeslot, aClass);

		}

		return sched;
	}

	public static void sortByFitness(){
		// Sort schedules by fitness (Insertion)
		// Sorted by least fit(index 0) to most fit(index length - 1)

		for(int i = 1; i < schedules.length; i++){
			Schedule temp = schedules[i];
			int j = i - 1;
			while( (j >= 0) && ( schedules[j].getFitness() > temp.getFitness() )){
				schedules[j+1] = schedules[j];
				j--;
			}
			schedules[j+1] = temp;
		}
	}

	//--------------------------------------------------
	// ----------------- ADDING METHODS ----------------
	// ------- POPULATES ARRAYLISTS WITH OBJECTS--------
	//--------------------------------------------------

	public static void addStudent(Student stud){
		students.add(stud);
	}

	public static void addProf(Professor prof){
		professors.add(prof);
	}

	public static void addClass(Class aClass){
		classes.add(aClass);
	}

	public static void addCourse(Course course){
		courses.add(course);
	}

	public static void addRoom(Classroom room){
		rooms.add(room);
	}

}

/*
   - Parse input file to generate all objects
   - Generate random schedules
   while(fitness < ?){
   - Calculate fitness of all
   - Sort by fitness 	(What algo?)
   - Replace bottom 20 (?) with new solutions generated from crossover of top 20 (?)
   - Random mutation (?)
   }
   - Most fit is best solution
   */
