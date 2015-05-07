import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BTTree {
	private BTNode root;
	
	public BTTree() {
		root = new BTNode(' ');
	}
	
	public BTNode root() {
		return root;
	}	
	
	public List<String> backtraces() {
		List<String> bts = new ArrayList<String>();
		findBTs(bts, root, "");
		return bts;
	}
	
	private void findBTs(List<String> bts, BTNode node, String str) {
		if(node.step() != ' ') { str+=node.step(); }
		boolean endOfPath = true;
		for(Map.Entry<Character, BTNode> entry : node.children().entrySet()) {
			if(node.hasChild(entry.getKey())) {
				findBTs(bts, entry.getValue(), str);
				endOfPath = false;
			}
		}
		if(endOfPath) { bts.add(str); }
	}
}
