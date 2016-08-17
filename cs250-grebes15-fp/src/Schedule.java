import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class Schedule{
	private Map<Integer, List<Class>> sched = new HashMap<Integer, List<Class>>();
	private double fitness = 0;	// Fitness of the schedule [initialized to 0]

	public Schedule(){
		// Ints 8 thru 17 represent timeslots (8AM - 5PM)
		// Initializes as empty schedule
		for(int i = 8; i <= 17; i++){
			sched.put(i, null);
		}

	}

	public void putList(int timeslot, List<Class> classes){
		sched.put(timeslot, classes);
	}

	public Map<Integer, List<Class>> getSched(){
		return sched;
	}

	public List<Class> getClassesAt(int timeslot){
		return sched.get(timeslot);
	}

	public void addClass(int timeslot, Class theClass){
		if(timeslot >= 8 && timeslot <= 17){
			List<Class> classList = sched.get(timeslot);
			classList.add(theClass);
			
			sched.put(timeslot, classList);
		}
		//else (?)
	}

	public void addClass(int timeslot, List<Class> classes){
		if(timeslot >= 8 && timeslot <= 17){
			sched.put(timeslot, classes);
		}
	}

	public void removeClass(int timeslot, Class theClass){
		if(timeslot >= 8 && timeslot <= 17){
			List<Class> classList = sched.get(timeslot);
			classList.remove(theClass);

			sched.put(timeslot, classList);
		}
	}

	public void removeClass(int timeslot, List<Class> classes){
		if(timeslot >= 8 && timeslot <= 17){
			classes.remove(classes);

			sched.put(timeslot, classes);
		}
	}

	public void calcFitness(ArrayList <Professor> profs, ArrayList <Class> classes, ArrayList <Student> students){

		// Conflict weights = num of student conflicts the given conflict is equivalent to
		// e.g. 1 Prof Hard conflict = 25 student Conflicts (if profHardWeight = 25)
		int profHardConflicts = 0;
		int profHardWeight = 25;
		int roomHardConflicts = 0;
		int roomHardWeight = 25;
		int numStudConflicts = 0;
		int studConflictWeight = 1;
		int profMaxConflicts = 0;
		int profMaxWeight = 10;
		int maxStudConflicts = students.size() * 6; // Max of 6 conflicts per student

		

		// Check professor (hard) conflicts
		for(int i = 8; i<=17; i++){

			List<Class> sameSlotsClass = sched.get(i);
			int countForProfessor=0;
			
				for(Class c: sameSlotsClass){
				// check is the professor is teaching he different classes at the same time
				if(sameSlotsClass.get(i-8).getProf() == c.getProf()){
					countForProfessor++;
				}
			//	count++;
			}
			if(countForProfessor != 1){
				profHardConflicts += countForProfessor - 1;
				//return;
			}


		}

		// Check classroom conflicts
		for(int i = 8; i <= 17; i++){
			List<Class> sameSlotsClass = sched.get(i);
			int roomCount = 0;
			for(Class c: sameSlotsClass){
				if(sameSlotsClass.get(i-8).getRoom() == c.getRoom()){
					roomCount++;
				}
			//	count++;
			}
			if(roomCount != 1){
				roomHardConflicts += roomCount - 1;
				//return;
			}
		}

		// Check Professor max classes constraint
		for(Professor p : profs){
			int numOver = p.getCurrentClasses() - p.getMaxClasses();
			if(numOver > 0){
				profMaxConflicts += numOver;
			}
		}

		// Check Student preference conflicts
		for(int i = 8; i <= 17; i++){
			List<Class> sameSlotsClass = sched.get(i);

			for(Student s : students){
				ArrayList<Course> studCourses = s.getCourses();

				for(int k = 0; k < studCourses.size(); k++){
					for(int j = i; j < studCourses.size(); j++){
						if(sameSlotsClass.contains(studCourses.get(k)) &&
								sameSlotsClass.contains(studCourses.get(j))){
									
							numStudConflicts++;
						}
					}
				}
			}
		}

		double fitCalc = ((profHardConflicts * profHardWeight) + (roomHardConflicts * roomHardWeight) + (profMaxConflicts * profMaxWeight) + (numStudConflicts * studConflictWeight)) / (double)maxStudConflicts;

		if(fitCalc > 0){
			fitness = fitCalc;
		}else{
			fitness = 0;
		}	
		
	}

	public double getFitness(){
		return fitness;
	}

}
