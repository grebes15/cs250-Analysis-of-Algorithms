import java.util.*;
public class Student{
	private int student_ID;
	private ArrayList<Course> courses = new ArrayList<Course>();

	public Student(){
		
	}

	public void setStudent_ID(int id){
		this.student_ID = id;
	}
	public void setCourses(ArrayList<Course> courses){
		this.courses = courses;
	}
	public ArrayList<Course> getCourses(){
		return courses;
	}
	public int getStudent_ID(){
		return student_ID;
	}

	public void addCourse(Course c){
		courses.add(c);
	}
}
