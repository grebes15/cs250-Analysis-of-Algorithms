import java.util.List;
import java.util.ArrayList;

public class Professor{
	private  int ID;
	private  List<Course> courses;
	private  int maxClasses;		// Max classes Prof can teach
	private int currentClasses;			// Current number of classes Prof has

	// Professor with max classes specifies
	public Professor(){
		
	}
	
	// Professor with max classes not specified - defaults to 3
	public void setID(int id){
		this.ID=id;
	
	}
	
	public int getId(){
		return ID;
	}

	public void addCourse(Course c){
		courses.add(c);
	}

	public void addCourse(int id){

	}

	public void setCourse(ArrayList<Course> c){
		this.courses = c;
	}
	public List<Course> getCourses(){
		return courses;
	}
	public void setMaxClasses(int s){
		this.maxClasses=s;
	}
	public int getMaxClasses(){
		return maxClasses;
	}

	public int getCurrentClasses(){
		return currentClasses;
	}

	public void incrementCurrent(){
		currentClasses++;
	}

}
