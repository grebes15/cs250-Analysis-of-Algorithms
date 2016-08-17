import java.lang.String;

public class Classroom{
	private int ID;
	private String name; 		// Probably not needed
	private int seats;

	public Classroom(){

	}

	public void setID(int ID){
		this.ID = ID;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setSeats(int seats){
		this.seats = seats;
	}

	public int getID(){
		return ID;
	}

	public String getName(){
		return name;
	}

	public int getSeats(){
		return seats;
	}

}



