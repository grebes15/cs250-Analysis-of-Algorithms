import java.util.*;
import java.io.*;

public class DLB {

	public DLBnode firstNode;
	public char empty = ' ';
    public TreeSet<String> solutionSet= new TreeSet<>(); // A TreeSet is sorted, elements are unique
	
	public DLB(){
		this.firstNode = new DLBnode(empty,0,null,null); //establishing DLB node, value = 0, meaning it is not a word
	}

	public void add(String input) {


		if(search(input) == 1) {
			return; //if input already exists, DO NOT ADD IT AGAIN
		}

		DLBadd(input,0,firstNode);
	}

	public void DLBadd(String input, int position, DLBnode current) {



		char c = input.charAt(position);

		DLBnode temp = current;


		while(temp != null){
			current = temp;
			if(current.key == c) { // found the character in current tree
				if(position+1 == input.length()){
					current.value = 1; // if end of string then just mark it as a word
					return;
				} else {
					if (current.child==null) { // no children, allocate new subtree with remaining characters.
						for (int i=position+1; i<input.length(); i++) {
							DLBnode d = new DLBnode();
							current.child=d;
							d.key = input.charAt(i);
							d.value = 0;
							current=d;
						}
						current.value=1; // mark word finished
						return;
					} else {
						DLBadd(input, position+1,current.child);
						return;
					}
				}
			}
			temp = current.sibling;
		}

		if(position+1 == input.length()){ //checking for the the end of the word
			current.sibling = new DLBnode(c,1,null,null); //value is 1 if it is the end of the word

		} else { //else the value is 0 and a new DLB node will be established for the sibling
			DLBnode ch = new DLBnode();
			current.sibling = new DLBnode(c,0,null,ch);

			DLBadd(input, position+1, current.sibling.child); //recursive call seeing that the word is not over
		}

	}




	public int search(String input) {
		return searchFromPosition(input,0,firstNode);
	}

	public int searchFromPosition(String input, int position, DLBnode current) { //search to see if a DLB trie will be established

		if(current == null) { //if there is nothing in the node, return 0
			return 0;
		} //if

		char c = input.charAt(position); //used for checking levels to see if it is the same character

		do { //do-while if there is something in the node

			if(c == current.key) { //if a match is found
				if(position+1 == input.length()){
					if(current.value == 1) {
						return 1; //if value of node is 1, meaning ending, then return 1 
					} else {
						return 0;
					}
				} else {
					return searchFromPosition(input,position+1,current.child); //recursive call to check whether a DLB will be created
				}
			} else {
				current = current.sibling;
			}
		}
		while(current != null); //end of do-while loop

		return 0; 

	}//searchfromPosition method


	public void writeNode(DLBnode secondNode, int position) { //print out node for debugging proposes

		while(secondNode.sibling != null){ //while a sibling exists instead of the DLB



			for(int i = 0; i <= position; i++) { //for every position of the sibling, print out a -
				System.out.print("-");
			}
			System.out.println(secondNode.key);
			if(secondNode.child != null) {
				writeNode(secondNode.child, position+1);
			}

			secondNode = secondNode.sibling;
		}
	}
	
	
	
	public int beginsWith(String input){
		
		//return values
		// 0 means no words starts with this
		// 1 means input is in dictionary
		// 2 means a word in the dictionary starts with the string input
		
		return beginsWithFromPosition(input,0,firstNode);
	}
	
	
	public int beginsWithFromPosition(String input, int position, DLBnode current) { //search to see if a DLB trie will be established

		if(current == null) { //if there is nothing in the node, return 0
			return 0;
		} //if

		char c = input.charAt(position); //used for checking levels to see if it is the same character

		do { //do-while if there is something in the node

			if(c == current.key) { //if a match is found
				if(position+1 == input.length()){
					if(current.value == 1) {
						return 1; //if value of node is 1, meaning ending, then return 1 
					} else {
						return 2; // end of input string but not end of DLB branch
					}
				} else {
					return beginsWithFromPosition(input,position+1,current.child); //recursive call to check whether a DLB will be created
				}
			} else {
				current = current.sibling;
			}
		}
		while(current != null); //end of do-while loop

		return 0; 

	}//beginsWithFromPosition method


	public  void tryNeighbors(String candidate, int row, int col, char[][] boggleBoard, int n){
		char c;
		int res;
		if (search(candidate)==1){
			solutionSet.add(candidate);
		}
		if (col>0) { // Explore to the left of row,col
			c = boggleBoard[row][col-1];
			res = beginsWith(candidate+c);
			if (res!=0) {
				tryNeighbors(candidate+c, row, col-1, boggleBoard, n);
			}
		}
		
		if (col < n - 1) {// Explore to the right of row,col
			c = boggleBoard[row][col + 1];
			res = beginsWith(candidate+c);
			if(res!=0){
				tryNeighbors(candidate+c, row, col+1, boggleBoard, n);
			}
		}
			
		if (row > 0){// Explore above of row,col
			c = boggleBoard[row-1][col];
			res = beginsWith(candidate+c);
			if(res!=0) {
				tryNeighbors(candidate+c, row-1, col, boggleBoard, n);
			}

		}
		if(row < n - 1){// Explore below of row,col
			c = boggleBoard[row+1][col];
			res = beginsWith(candidate+c);
			if(res!=0){
				tryNeighbors(candidate+c, row+1, col, boggleBoard, n);
			}
		}

	}
	

}