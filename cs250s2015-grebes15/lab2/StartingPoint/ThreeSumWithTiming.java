import java.io.*;
import java.util.*;

public class ThreeSumWithTiming {

    public static void main(String args[]) {
  In in = new In(args[0]);
  int[] a = in.readAllInts();

  Stopwatch timer = new Stopwatch();
  StdOut.println("Triples: " + count(a));
  StdOut.println("Elapsed time: " + timer.elapsedTime());
    } //main

 public static int count(int[] a) {

  int count = 0;

  for (int i = 0; i < a.length; i++) {
   for (int j = i+1; j < a.length; j++) {
    for (int k = j+1; k < a.length; k++) {
     if (a[i] + a[j] + a[k] == 0) {
      count++;
     } //if
    } //for
   } //for
  } //for

  return count;

 } //count

} //ThreeSumWithTiming (class)