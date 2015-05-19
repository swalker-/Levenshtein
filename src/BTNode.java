import java.util.List;

public final class BTNode {
	private final char step;
	private final List<BTNode> children;
	
	public BTNode(char step, List<BTNode> children) {
		this.step = step;
		this.children = children;
	}
	
	public char step() {
		return step;
	}

	public BTNode uChild() {
		return children.get('U');
	}
	

	
	public BTNode lChild() {
		return children.get('L');
	}
	

	
	public boolean hasChild(char c) {
		for(BTNode child : children) {
			if(child.step() == c) { return true; }
		}
		return false;
	}
	
	public BTNode dChild() {
		return children.get('D');
	}
	
	public List<BTNode> children() {
		return children;
	}
}
