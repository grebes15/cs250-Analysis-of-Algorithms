import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.StringBuilder;
import java.util.Random.*;


class SortCompare {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) {
            Insertion.sort(a);
        } else if (alg.equals("Selection")) {
            Selection.sort(a);
        } else if (alg.equals("Shell")) {
            Shell.sort(a);
        } else if (alg.equals("Merge")) {
            Merge.sort(a);
		} else if (alg.equals("MergeBU")) {
			MergeBU.sort(a);
        } else if (alg.equals("Quick")) {
            Quick.sort(a);
        } else if (alg.equals("Heap")) {
            Heap.sort(a);
        } //if-else
        return timer.elapsedTime();
    } //time

    public static double timeRandomInput(String datatype, String alg, int N, int T, String gaussian) {
    	double total = 0.0;

    	if(datatype.equals("Doubles")){
    		Double[] a = new Double[N];
    		StdOut.printf("Running %s Sort.\n", alg);
    		for (int t = 0; t < T; t++) {
    			StdOut.printf("--Iteration %d\n", t);
    			for (int i = 0; i < N; i++) {
                    if(gaussian.equals("Gaussian")){
                        a[i] = Double.valueOf(StdRandom.gaussian());
                    } //if
                    else if(gaussian.equals("Uniform")){
                        a[i] = StdRandom.uniform();
                } //if
            } //for
            total += time(alg, a);
        } //for
    } //if

    if(datatype.equals("Integers")){
    	Integer [] a = new Integer[N];
    	StdOut.printf("Running %s Sort.\n", alg);
    	for (int t = 0; t < T; t++) {
    		StdOut.printf("--Iteration %d\n", t);
    		for (int i = 0; i < N; i++) {
                if(gaussian.equals("Gaussian")){
                    a[i] = (int)StdRandom.gaussian();
                } else if(gaussian.equals("Uniform")){
                    a[i] = StdRandom.uniform(N);
                } //if-else
            } //for
            total += time(alg, a);
        } //for
    }//if
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    Random rg = new Random();
    if(datatype.equals("Strings")){
    	String [] a = new String[N];
    	StdOut.printf("Running %s Sort.\n", alg);
    	for (int t = 0; t < T; t++) {
    		StdOut.printf("--Iteration %d\n", t);
    		for (int i = 0; i < N; i++) {
                if(gaussian.equals("Gaussian")){
                    a[i] = String.valueOf(StdRandom.gaussian());
                }else if(gaussian.equals("Uniform")){

                    StringBuilder sb = new StringBuilder(N);

                    for (int j = 0; j < N; j++){

                        sb.append( alphabet.charAt( rg.nextInt(alphabet.length() )));
                    } //for
                    a[i] = sb.toString();
                }//if
            } //for
            total += time(alg, a);
        } //for
    }//if


    return total;
    } //timeRandomInput

    public static void main(String args[]) {
    	if (args.length != 6) {
    		StdOut.printf("USAGE:String datatype String alg1, String alg2, int array_size, int repeats String gaussian");
    	} else {
    			String alg1 = args[0];
        		String alg2 = args[1];
        		String alg3 = args[2];
                String g = args[5];
        		int N = Integer.parseInt(args[3]);
        		int T = Integer.parseInt(args[4]);
        		double t1 = timeRandomInput(alg1, alg2, N, T, g);
        		double t2 = timeRandomInput(alg1, alg3, N, T, g);
        		StdOut.printf("For %d random %s\n    %s is", N, alg1, alg2);
        		StdOut.printf(" %.3f times faster than %s when running %s\n", t2/t1, alg3, g);
        } //if-else
    } //main
} //SortCompare