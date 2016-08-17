import java.util.*;
import java.io.*;


public class IntegerGenerator {


	public static void main(String [] args){
		try{
			PrintWriter out = new PrintWriter(new 
				FileWriter("/Users/Andreas/Documents/College/3rdYear/2ndSemester/CompSci250/cs250s2015-grebes15/lab6/Part2/output1000000.txt"));

			for(int i = 1; i <= 1000000; i++){
				Random rg = new Random();
				int num = 0;
				num = rg.nextInt(500);
				out.write(num + "\n");
		} //for
		out.close();
	}//try
	catch(Exception e){
		e.printStackTrace();
	} 
	} //main method
} //IntegerGenerator class