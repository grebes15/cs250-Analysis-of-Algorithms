
public class DLBnode {
	public char key;
	public int value;
	public DLBnode sibling;
	public DLBnode child;

	public DLBnode(char keyValue, int nodeValue, DLBnode sibling, DLBnode child) {
		this.key = keyValue;
		this.value = nodeValue;
		this.sibling = sibling;
		this.child = child;
	}

	public DLBnode() {
		
	}
	
}
