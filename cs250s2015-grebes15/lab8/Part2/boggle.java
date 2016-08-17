import java.util.*;
import java.io.*;
public class boggle {
	
	



	public static void main(String[] args) throws IOException {



		Scanner fileScan = new Scanner(new FileInputStream("/Users/andreas/Documents/cs250/cs250s2015-grebes15/lab8/Part2/dictionary.txt"));
		String st;
		DLB D = new DLB();

		while (fileScan.hasNext()) {
			st = fileScan.nextLine();
			D.add(st);

		} //while


		int n = 4;
		char[][] boggleBoard = new char[n][n];

		try{

			File inFile = new File("/Users/andreas/Documents/cs250/cs250s2015-grebes15/lab8/Part2/data2.txt");
			Scanner in = new Scanner(inFile);

			String str = in.nextLine();

			int position=0;
			for (int row=0; row<n; row++) {
				for (int col=0; col<n; col++){
					boggleBoard[row][col]=Character.toLowerCase(str.charAt(position++));
				}
			}
			System.out.println("BoggleBoard:");
			for(int row = 0; row< n; row++){
				for(int col = 0; col < n; col++){
					System.out.print(boggleBoard[row][col]+" ");
				}
				System.out.println();
			}
			System.out.println();
			for(int row = 0; row< n; row++){
				for(int col = 0; col < n; col++){

					String candidate = Character.toString(boggleBoard[row][col]);
					D.tryNeighbors(candidate, row, col, boggleBoard, n);		



				}
			}

			System.out.println("There were " + D.solutionSet.size() + " total words:");

			for (String s:D.solutionSet) {
				System.out.println(s);
			}


		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
}
