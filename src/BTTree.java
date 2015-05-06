import java.util.ArrayList;
import java.util.List;


public class BTTree {
	private BTNode root;
	
	public BTTree() {
		root = new BTNode(' ');
	}
	
	public BTNode root() {
		return root;
	}	
	
	public List<String> backtraces() {
		List<String> paths = new ArrayList<String>();
		paths(paths, root, "");
		return paths;
	}
	
	private void paths(List<String> l, BTNode node, String str) {
		if(node.step() != ' ') { str+=node.step(); }
		if(node.uChild() != null) { paths(l, node.uChild(), str); }
		if(node.lChild() != null) { paths(l, node.lChild(), str); }
		if(node.dChild() != null) { paths(l, node.dChild(), str); }
		if(node.uChild() == null && node.lChild() == null && node.dChild() == null) { l.add(str); }
	}
}
