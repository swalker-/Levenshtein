import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class BTTreeTest {

	@Test
	public void canProduceList() {
		BTNode node11 = new BTNode('U', new ArrayList<BTNode>());
		BTNode node12 = new BTNode('D', new ArrayList<BTNode>());
		BTNode node21 = new BTNode('U', new ArrayList<BTNode>());
		
		List<BTNode> node1Children = new ArrayList<BTNode>();
		node1Children.add(node11);
		node1Children.add(node12);
		BTNode node1 = new BTNode('U', node1Children);
		
		List<BTNode> node2Children = new ArrayList<BTNode>();
		node2Children.add(node21);
		BTNode node2 = new BTNode('L', node2Children);
		
		List<BTNode> rootChildren = new ArrayList<BTNode>();
		rootChildren.add(node1);
		rootChildren.add(node2);
		BTNode root = new BTNode(' ', rootChildren);
		
		BTTree tree = new BTTree(root);
		
		List<String> expectedPaths = new ArrayList<String>();
		expectedPaths.add("UU");
		expectedPaths.add("UD");
		expectedPaths.add("LU");
		for(String child : tree.backtraces()) {
			assertTrue(expectedPaths.contains(child));
		}
		
	}

}
