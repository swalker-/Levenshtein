import java.util.ArrayList;
import java.util.List;


public class BTNode {
	private char step;
	private BTNode uChild, lChild, dChild;
	
	public BTNode(char step) {
		this.step = step;
		uChild = null;
		lChild = null;
		dChild = null;
	}
	
	public char step() {
		return step;
	}

	public void addUChild(BTNode u) {
		uChild = u;
	}
	
	public BTNode uChild() {
		return uChild;
	}
	
	public void addLChild(BTNode l) {
		lChild = l;
	}
	
	public BTNode lChild() {
		return lChild;
	}
	
	public void addDChild(BTNode d) {
		dChild = d;
	}
	
	public BTNode dChild() {
		return dChild;
	}
}
