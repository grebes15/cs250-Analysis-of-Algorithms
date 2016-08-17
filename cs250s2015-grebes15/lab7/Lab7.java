import java.io.IOException; 
import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.lang.*;
import java.lang.Object.*;
import java.lang.Number.*;
import java.lang.Integer.*;

public class Lab7 {
    //used mostly for part 1
	public static List<String> identifier = new ArrayList<String>(); //identifier array list
	static List<String> sequence = new ArrayList<String>(); //sequence array list
	static String lineOfFile; //String to identify the specific line of the file being passed in
	public static String[] arrayListToArray; //array to be used to convert the identifier and sequence array list
    //used mostly for part 2
	static int [][] a; //two dimensional array

        //method to convert array list to array for the identifier
	public static void arrayListToArrayConverter(){
		arrayListToArray = new String[identifier.size()];
		for(int i = 0; i < identifier.size(); i++){
			arrayListToArray[i] = identifier.get(i);
		}
	}
        //method to convert array list to array for the sequence
	public static void arrayListToArraySequence(){
		arrayListToArray = new String[sequence.size()];
		for(int i = 0; i < sequence.size(); i++) {
			arrayListToArray[i] = sequence.get(i);
		}
	}
        //method to add periods to the identifier for the LSD sorting algorithm
	public static String addPeriods(String lineofFile){
			if(lineOfFile.length() < 21){
				while(lineOfFile.length() < 21){
					lineOfFile = lineOfFile + ".";
				} //while
			} //if
			return lineOfFile;
	} //addPeriods methods


	public static void leastCompleteMostComplete(){
        //method to identify the most complete and least complete sequence
       int min = Integer.MAX_VALUE;
       int max = Integer.MIN_VALUE;
       int maxPosition = 0;
       int minPosition = 0;

       for(int i = 0; i < identifier.size(); i++){

            //System.out.println("This identifier: " + identifier.get(i) + " has " + counter(i) + " missing characters");

        if(counter(i) < min){
            min = counter(i);
            minPosition = i;
        } //if
        if(counter(i) > max){
            max = counter(i);
            maxPosition = i;          
        } //if
    } //for
    System.out.println("The max value is: " + max + " was " + identifier.get(maxPosition));
    System.out.println("The min value is: " + min + " was " + identifier.get(minPosition));
} //leastCompleteMostComplete method


    //method to count how many missing characters there are in the specific sequence
public static int counter(int value) {
    lineOfFile = sequence.get(value);
    char sequence[] = lineOfFile.toCharArray();
    int missingCharacters = 0;
    for(int i = 0; i < lineOfFile.length(); i++) {
        if(sequence[i] == '-'){
            missingCharacters++;
            } //if
        } //for
        return missingCharacters;
    } //counter method


    //method to parse the file being passed in
    //if the line starts with >, then it is the identifier
    //if the line of file does not equal "", then it is a sequence
    public static void parse(File proteinFile) {
        try {

            Scanner scan = new Scanner(proteinFile);

            while(scan.hasNextLine()) {

                lineOfFile = scan.nextLine();

                if(lineOfFile.startsWith(">")) {
                    identifier.add(addPeriods(lineOfFile));
                } else

                if(!lineOfFile.equals("")) {
                    sequence.add(lineOfFile);
            } //if-else
        } //while

        for(int i = 0; i < identifier.size(); i++) {
            System.out.println(identifier.get(i));
            System.out.println(sequence.get(i));
            System.out.println();
            } //for
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } //try-catch
    //System.out.println("Done");
    } //parse method

/*
    public static int partTwo(){
        int score;
        char[] one;
        char[] two;
        for(int x = 0; a <identifier.size(); i++){
            for (int y = 0; b <identifier.size(); i++){

                lineOfFile = sequence.get(x);
                one[] = lineOfFile.toCharArray();
                lineOfFile = sequence.get(y);
                two[] = lineOfFile.toCharArray();
                for(int z = 0; z < one.length; z++){
                    if(one[z] == '-'){
                    } else 
                    if(one[z] == two[z]) {
                        score = score + 3;
                    } else 
                    if(two[z] == '-') {
                        score = score - 1;
                    } else {
                        score = score - 2;
                    } //if-else
                } //3rd for
            } //two for
        } //one for
        return score;
    }
    */

        //method to compute the score according to the laboratory assignment
public static int MostSimilarByIndex(int x, int y) {
        int score = 0;
        lineOfFile = sequence.get(x);
        char one[] = lineOfFile.toCharArray();
        lineOfFile = sequence.get(y);
        char two[] = lineOfFile.toCharArray();
        for(int z = 0; z < one.length; z++) {
            if (one[z]=='-') {
            } else if(one[z]==two[z]) {
                score = score + 3;
            } else if (two[z]=='-') {
                score = score -1;
            } else {
                score = score -2;
            }
        }
        return score;
    }
    //using two dimensional array to pass to the size of the identifier
    public static void score() {
        int size = identifier.size();
        a = new int [size][size];
        for(int x = 0; x < identifier.size(); x++) {
            for(int y = 0; y < identifier.size(); y++) {
                a[x][y] = MostSimilarByIndex(x,y);
            }
        }
    }
        //method to display the minimum score and maximum score 
    public static void maximumMinimumPositionAndValue() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int minimumPositionX = 0;
        int minimumPositonY = 0;
        int maximumPositionX = 0;
        int maximumPositionY = 0;
        for(int x = 0; x < identifier.size(); x++) {
            for(int y = 0; y < identifier.size(); y++) {
                if (x == y) {
                } else if(a[x][y] < min) {
                    min = a[x][y];
                    minimumPositionX = x;
                    minimumPositonY = y;
                } else if(a[x][y] > max) {
                    max = a[x][y];
                    maximumPositionX = x;
                    maximumPositionY = y;
                }               
            }
        }
        System.out.println("Maximum score = " + max);
        System.out.println(identifier.get(maximumPositionX)+" vs. "+identifier.get(maximumPositionY));
        System.out.println("Minimum score = " + min);
        System.out.println(identifier.get(minimumPositionX)+" vs. "+identifier.get(minimumPositonY));
    }
        //method to compute what is most, least, and average similarity to the dTIMCORE protein
    public static void dTIMCore() {
        int locationOfArrayIndex = identifier.size()-1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int minPosition = 0;
        int maxPosition = 0;
        double scores = 0.0;
        for(int x = 0; x < identifier.size(); x++) {
            if (x == locationOfArrayIndex) {
            } else if(a[locationOfArrayIndex][x] < min) {
                min = a[locationOfArrayIndex][x];
                minPosition = x;
            } else if(a[locationOfArrayIndex][x] > max) {
                max = a[locationOfArrayIndex][x];
                maxPosition = x;
            } //if-else
            scores = scores + a[locationOfArrayIndex][x];
        }//640
        System.out.println("Maximum score to dTIM_core = " + max);
        System.out.println("Most similar protein: "+identifier.get(maxPosition));
        System.out.println("Minimum score to dTIM_core = " + min);
        System.out.println("Least similar protein: "+identifier.get(minPosition));
        System.out.println("Average Score in the TIM family is: " + scores/640.0);
    }

} //Lab7 class