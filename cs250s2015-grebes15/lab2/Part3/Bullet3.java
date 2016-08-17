import java.util.*;
import java.io.*;

public class Bullet3 {

	public static String mystery(String s){
			if(s.length() <= 1){
				return s;
			} else {
				String a = s.substring(0, s.length()/2);
				String b = s.substring(s.length()/2, s.length());
				return mystery(b) + mystery(a);
			}// if-else
		} // mystery


	public static void main(String[] argv){
		Scanner scan = new Scanner(System.in);
		String Andreas;
		Andreas = scan.nextLine();
		mystery(Andreas);
	}//main
}//class