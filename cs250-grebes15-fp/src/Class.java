public class Class{
	private int enrolled=0;
	private Professor prof;
	private Course course;
	private Classroom room;// Maybe store classroom ref?
	//private final ArrayList<Student> students;
	public Class(){
		
	}	
	public void setCourse(Course c){
		this.course=c;
	}
	public Course getCourse(){
		return course;
	}
	public void setProfessor(Professor p){
		this.prof=p;
	}
	public Professor getProf(){
		return prof;
	}
	public void setEnrolled(int e){
		this.enrolled = e;
	}
	public int getEnrolled(){
		return enrolled;
	}
	public void setClass(Classroom r){
		this.room = r;
	}
	public Classroom getRoom(){
		return room;
	}

	public void setRoom(Classroom theRoom){
		room = theRoom;
	}
	// For if we initialize enrolled = 0, increment as we parse students
	public void incEnrolled(){
		enrolled++;
	}
	

}
