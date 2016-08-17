import java.util.*;
public class Test{
	public static void main(String[] args){
		ArrayList<Course> course = new ArrayList<Course>();
		Random rand = new Random();
		char[] alphabet ={'a','b','c','d','e','f','g','h','i','j','k','l','m','n'};
		for(int i=0; i<alphabet.length; i++){
			course.add(new Course(i,""+alphabet[i]));
		}
	}

}
