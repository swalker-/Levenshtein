import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class BTTreeTest {

	@Test
	public void canProduceList() {
		BTTree tree = new BTTree();
		BTNode node = tree.root();
		BTNode node1 = new BTNode('U');
		BTNode node2 = new BTNode('L');
		BTNode node11 = new BTNode('U');
		BTNode node12 = new BTNode('D');
		BTNode node21 = new BTNode('U');
		node.addUChild(node1);
		node.addLChild(node2);
		node1.addUChild(node11);
		node1.addDChild(node12);
		node2.addUChild(node21);
		
		List<String> expectedPaths = new ArrayList<String>();
		expectedPaths.add("UU");
		expectedPaths.add("UD");
		expectedPaths.add("LU");
		for(String child : tree.backtraces()) {
			assertTrue(expectedPaths.contains(child));
		}
		
	}

}
