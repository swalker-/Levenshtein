import java.util.ArrayList;
import java.util.List;


public class BTTree {
	private BTNode root;
	private boolean calledBacktraces;
	private List<String> backtraces;
	
	public BTTree(BTNode root) {
		this.root = root;
		calledBacktraces = false;
	}
	
	public BTNode root() {
		return root;
	}	
	
	public List<String> backtraces() {
		if (!calledBacktraces) {
			List<String> bts = new ArrayList<String>();
			findBTs(bts, root, "");
			backtraces = bts;
			calledBacktraces = true;
		}
		return backtraces;
	}
	
	private void findBTs(List<String> bts, BTNode node, String str) {
		if(node.step() != ' ') { str+=node.step(); }
		boolean endOfPath = true;
		for(BTNode entry : node.children()) {
			if(node.hasChild(entry.step())) {
				findBTs(bts, entry, str);
				endOfPath = false;
			}
		}
		if(endOfPath) { bts.add(str); }
	}
}
