import java.util.HashMap;
import java.util.Map;

public class BTNode {
	private char step;
	private Map<Character, BTNode> children;
	
	public BTNode(char step) {
		this.step = step;
		children = new HashMap<Character, BTNode>();
		children.put('U', null);
		children.put('L', null);
		children.put('D', null);
	}
	
	public char step() {
		return step;
	}

	public void addUChild(BTNode u) {
		children.put('U', u);
	}
	
	public BTNode uChild() {
		return children.get('U');
	}
	
	public void addLChild(BTNode l) {
		children.put('L', l);
	}	
	
	public BTNode lChild() {
		return children.get('L');
	}
	
	public void addDChild(BTNode d) {
		children.put('D', d);
	}
	
	public boolean hasChild(char c) {
		switch(c) {
		case('U'):
			return (children.get('U') != null);
		case('L'):
			return (children.get('L') != null);
		case('D'):
			return (children.get('D') != null);
		default:
			return false;
		}
	}
	
	public BTNode dChild() {
		return children.get('D');
	}
	
	public Map<Character, BTNode> children() {
		return children;
	}
}
