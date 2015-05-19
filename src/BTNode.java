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
		return getChild('U');
	}
	
	public BTNode lChild() {
		return getChild('L');
	}
	
	public BTNode dChild() {
		return getChild('D');
	}
	
	public void addChild(BTNode child) {
		children.add(child);
	}
	
	public boolean hasChild(char c) {
		for(BTNode child : children) {
			if(child.step() == c) { return true; }
		}
		return false;
	}
	
	private BTNode getChild(char c) {
		for(BTNode child : children) {
			if(child.step() == c) { return child; }
		}
		return null;
	}
	
	public List<BTNode> children() {
		return children;
	}
}
