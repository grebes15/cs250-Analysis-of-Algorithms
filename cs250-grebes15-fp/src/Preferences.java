import java.util.*;

public class Preferences{
	public static int size=1000;
	public static String[] courses = {"Art 110","Art 111","Art 112","Art 225","Art 231", "Art 236", "Art 241", "Art 245", "Art 247", "Biology 060", "Biology 067", "Biology 070", "Biology 100", "Chemistry 104", "Chemistry 600", "Communciation Arts 120", "Communication Arts 611", "The Community and Justice Studies 160", "The Community and Justice Studies 460", "Computer Science 111", "Computer Science 112", "Computer Science 250", "Computer Science 580", "Dance and Movement Studies 100", "Dance and Movement Studies 101", "Dance and Movement Studies 102", "Economics 100", "Economics 101", "Economics 160", "Economics 200", "Education 100", "Education 220", "English 200", "English 203", "Environmental Science 110", "Environment Science 230", "Environment Science 315", "Geology 108", "Geology 250", "Global Health Studies 130", "Global Health Studies 370", "History 101", "History 110", "History 119", "Math 160", "Math 170", "Math 210", "Math 205", "Math 320", "Music 103", "Music 107", "Neuroscience 120", "Philosophy 210", "Philosophy 220", "Philosophy 240", "Physics 120", "Physics 210", "Political Science 110","Political Science 140", "Psychology 106","Psychology 150","Psychology 206", "Religious Studies 111", "Religious Studies 120", "Religious Studies 130", "Math 365"};
	public static	Course[] teaching = new Course[courses.length];
	public static String[] professor={"Sue Buck","Amelia Carr", "Amara Geffen", "Lee Coates", "Mark Arms", "Courtney Bailey", "Courtney Bailey", "Emily Chivers Yochim", "John Wenskovitch","Janyl Jumadinova","Gregory Kapfhammer","Robert Roos", "James Reedy", "Tomas Nonnenmacher", "Susan Slote", "Terry Bensel", "Judson Herrman", "Angela Keysor", "Stephen Lyons", "Kenneth Pinnow", "Andrea Seligman", "Barry Shapiro"};
	public static Professor[] staff = new Professor[professor.length];
	public static Class[] c1 = new Class[teaching.length];
	public static Classroom[] classroom = new Classroom[professor.length];
//	public static void main(String[] args){
	public static Student[] GeneratePreferences(){
		Random rand = new Random();
	for(int i=0; i<teaching.length; i++){
		Course teachingCourse = new Course();
		teachingCourse.setName(courses[i]);
		teaching[i]=teachingCourse;
	}
		Student[] students =  new Student[size];		
		int num=0;
		for(int i=0; i<size; i++){
			ArrayList<Course> c  = new ArrayList<Course>();
			for(int j=0; j<4; j++){
				num=rand.nextInt(courses.length-1);
				Course course =  new Course();
				course.setName(courses[num]);
				c.add(course);
			}

			Student s = new Student();
			s.setStudent_ID(i+1);
			s.setCourses(c);
			students[i]=s;
		}
		// print statment for testing
		 
		for(Student s: students){
			ArrayList<Course> a= s.getCourses();
			System.out.println("StudentID: "+ s.getStudent_ID());
			for(Course c : a){
				System.out.println(c.getName());
			}
		}
		
		return students;
	}
	
	public static Professor[] GenerateProfessor(){
		System.out.println(professor.length);
		System.out.println(courses.length);
		
		for(int i=0; i<professor.length; i++){
			Professor p = new Professor();
			p.setID(i+1);
			p.setMaxClasses(3);
			ArrayList<Course> cp = new ArrayList<Course>();
			for(int j=0; j<3; j++){
				
				cp.add(teaching[i*3+j]);
				System.out.println("___"+(i*3+j));
			}
		
			p.setCourse(cp);
			staff[i]=p;
		}
	
		for(Professor pro: staff){
			System.out.println(pro.getId());
			List<Course> pc= pro.getCourses();
			for(Course h: pc){
				System.out.println("Course: "+h.getName());
			}
			System.out.println(pro.getMaxClasses());		
		}
		return staff;
	
	}
	public static Classroom[] GenerateClassroom(){
			for(int i=0; i<classroom.length;i++){
				classroom[i] = new Classroom();
				classroom[i].setName("Room"+i);
				classroom[i].setSeats(30);
				classroom[i].setID(i);
			}
		return classroom;
	}
	public static Class[] GenerateClass(){
		for(int i=0; i<c1.length; i++){
			c1[i]= new Class();
			System.out.println("i"+i);
			Course cc= new Course();
			cc=teaching[i];
			c1[i].setCourse(teaching[i]);
			c1[i].setProfessor(searchProfessor(teaching[i].getName()));
			c1[i].setEnrolled(25);
			c1[i].setRoom(classroom[(int)i/3]);
			
		}

		for(Class cla: c1){
			System.out.println(cla.getCourse().getName());
			System.out.println(cla.getProf().getId());
			System.out.println(cla.getRoom().getName());
		}
		return c1;



	}
	public static Professor searchProfessor(String s){
		Professor right = new Professor();
		for(Professor p: staff){
			List<Course> pc = p.getCourses();
			for(Course c: pc){
				if(c.getName()==s){
					break;
				}
			}
			right=p;
		}
		return right;
	}
}
