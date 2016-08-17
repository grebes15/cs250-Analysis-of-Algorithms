import java.io.*;
import java.util.*;
public class DictionaryTest {

	public static void main(String [] args) throws IOException {

		Scanner fileScan = new Scanner(new FileInputStream("/Users/andreas/Documents/cs250/cs250s2015-grebes15/lab8/Part1/dictionary.txt"));
		String st;
		DLB D = new DLB();
		//DLBnode thirdNode = new DLBnode();

		while (fileScan.hasNext()) {
			st = fileScan.nextLine();
			D.add(st);

		} //while

		String[] tests = {"abc", "abe", "abet", "abx", "ace", "acid", "hives",
				   "iodin", "iodine", "idodinet", "inval", "zoo", "zool",
				   "zoology", "zoologys", "zurich"};

		for (int i = 0; i < tests.length; i++) {
			int ans = D.search(tests[i]);
			//D.writeNode(thirdNode,ans);
			System.out.print(tests[i] + " -- ");
			System.out.println(ans);
			switch (ans) {
				case 0:
					System.out.println("FALSE");
					break;
				case 1:
					System.out.println("TRUE");
					break;
			} //switch

		} //for

	} //main

} //DictionaryTest (class)


