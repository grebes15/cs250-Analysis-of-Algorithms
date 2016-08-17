import java.util.Scanner;


public class eightQueens {

	static int QueensFive[] = new int[5];

	static int QueensEight[] = new int[8];

	static int QueensEleven[] = new int[11];

	static int solutions =0;
	
	public static boolean isGoodEight(int row, int col) {
		int colLeft=col-1;
		int colRight=col+1;
		for (int i=row-1; i>=0; i--) {
			if (QueensEight[i]==colLeft--) return false;
			if (QueensEight[i]==col) return false;
			if (QueensEight[i]==colRight++) return false;
		}
		return true;
	}

	public static boolean isGoodFive(int row, int col) {
		int colLeft=col-1;
		int colRight=col+1;
		for (int i=row-1; i>=0; i--) {
			if (QueensFive[i]==colLeft--) return false;
			if (QueensFive[i]==col) return false;
			if (QueensFive[i]==colRight++) return false;
		}
		return true;
	}

	public static boolean isGoodEleven(int row, int col) {
		int colLeft=col-1;
		int colRight=col+1;
		for (int i=row-1; i>=0; i--) {
			if (QueensEleven[i]==colLeft--) return false;
			if (QueensEleven[i]==col) return false;
			if (QueensEleven[i]==colRight++) return false;
		}
		return true;
	}


	
	public static void printBoardEight() {

		for (int col=0; col < 8; col++) {
			for (int j=0; j < 8; j++) {
				if (j==QueensEight[col]) {
					System.out.print("X");
				} else {
					System.out.print(".");					
				}
			}
			System.out.println();
		}	
	}

	public static void printBoardFive() {
		for (int col = 0; col < 5; col++) {
			for (int j = 0; j < 5; j++) {
				if (j == QueensFive[col]) {
					System.out.print("X");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}

	public static void printBoardEleven() {
		for (int col = 0; col < 11; col++) {
			for (int j = 0; j < 11; j++) {
				if (j == QueensEleven[col]) {
					System.out.print("X");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
	
	public static void tryLevelEight(int Level) {
		for (int i = 0; i < 8; i++) {
			if (isGoodEight(Level,i)) {
				QueensEight[Level]=i;
				if (Level==7) {
					printBoardEight();
					//for (int j=0;j<8;j++) System.out.print(Queens[j]);
					System.out.println();
					solutions++;
				} else {
					tryLevelEight(Level+1);
				}
			}
		}
	}

	public static void tryLevelFive(int Level){
		for (int i = 0; i < 5; i++) {
			if(isGoodFive(Level,i)){
				QueensFive[Level]=i;
				if(Level==4) {
					printBoardFive();

					System.out.println();
					solutions++;
				} else{
					tryLevelFive(Level+1);
				}
			}
		}
	}

	public static void tryLevelEleven(int Level){
		for (int i = 0; i < 11; i++) {
			if(isGoodEleven(Level,i)){
				QueensEleven[Level]=i;
				if(Level==10) {
					printBoardEleven();

					System.out.println();
					solutions++;
				} else{
					tryLevelEleven(Level+1);
				}
			}
		}
	}

	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int numberOfQueens;

		System.out.println("Please scan in the number of queens");
		numberOfQueens = scan.nextInt();

		if(numberOfQueens == 8){
			tryLevelEight(0);
			System.out.println("Number of solutions: "+ solutions);
		}
		if(numberOfQueens == 5){
			tryLevelFive(0);
			System.out.println("Number of solutions: "+ solutions);
		}
		if(numberOfQueens == 11){
			tryLevelEleven(0);
			System.out.println("Number of solutions: " + solutions);
		}
	}

}
