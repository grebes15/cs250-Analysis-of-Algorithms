import java.util.*;
import java.io.*;

public class Lab7MainFile {


	public static void main(String[] args) {
		Lab7 sc = new Lab7();
		//obect being passed for the Lab7.java file
		String proteinFile = "cTIM_core_align.fa";
		//file being passed in
		File file = new File(proteinFile);
		sc.parse(file);
		sc.leastCompleteMostComplete();
		sc.score();
		sc.maximumMinimumPositionAndValue();
		sc.dTIMCore();
		//sc.arrayListToArrayConverter(); 
		/*
		uncomment top line for running through the identifier
		and comment the bottom line

		uncomment the bottom line for running through the sequence 
		and comment the top line

		dont uncomment both of them, it will not run correctly
		*/
		sc.arrayListToArraySequence();
		
		time("Merge", sc.arrayListToArray);
		double t1 = time("Merge", sc.arrayListToArray);
		double t2 = time("LSD", sc.arrayListToArray);
		double t3 = time("Quick", sc.arrayListToArray);
		double t4 = time("MSD", sc.arrayListToArray);

		String alg1 = "Merge";
		String alg2 = "LSD";
        StdOut.printf("In the file cTIM_core_align.fa\n    %s is", alg1);
        StdOut.printf(" %.3f times faster than %s\n", t2/t1, alg2);       

        String alg3 = "Quick";
        String alg4 = "MSD";

 	    StdOut.printf("In the file cTIM_core_align.fa\n    %s is", alg3);
        StdOut.printf(" %.3f times faster than %s\n", t2/t3, alg2); 

        StdOut.printf("In the file cTIM_core_align.fa\n    %s is", alg1);
        StdOut.printf(" %.3f times faster than %s\n", t4/t1, alg4); 

        StdOut.printf("In the file cTIM_core_align.fa\n    %s is", alg3);
        StdOut.printf(" %.3f times faster than %s\n", t4/t3, alg4); 

        StdOut.printf("In the file cTIM_core_align.fa\n    %s is", alg2);
        StdOut.printf(" %.3f times faster than %s\n", t4/t2, alg4); 
         //StdOut.printf("For %d random Doubles\n    %s is", N, alg1);
         //StdOut.printf(" %.3f times faster than %s\n", t2/t1, alg2);       


	}

	public static double time(String alg, String[] a) {
        Stopwatch timer = new Stopwatch();

        if (alg.equals("Insertion")) {
            Insertion.sort(a);
        } else if (alg.equals("Selection")) {
            Selection.sort(a);
        } else if (alg.equals("Shell")) {
            Shell.sort(a);
        } else if (alg.equals("Merge")) {
            Merge.sort(a);
        } else if (alg.equals("Quick")) {
            Quick.sort(a);
        } else if (alg.equals("Heap")) {
            Heap.sort(a);
        } else if (alg.equals("LSD")) {
            LSD.sort(a, 21);
        } else if (alg.equals("MSD")) {
            MSD.sort(a);
        }//if-else
        return timer.elapsedTime();
    } //time
} //Lab7MainFile class