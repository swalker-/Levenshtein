import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Levenshtein {
	
	private final String string1;
	private final int string1Length;
	private String string2;
	private int string2Length;
	private Entry[][] table;
	
	public Levenshtein(String s1) {
		string1 = s1.toLowerCase();
		string1Length = string1.length();
	}
	
	public void comparisonString(String s2) {
		string2 = s2.toLowerCase();
		string2Length = string2.length();
		table = new Entry[string1Length+1][string2Length+1];
		setupTable();
		calculateTable();
	}
	
	private void setupTable()
	{
		for(int string1_index=0; string1_index<=string1Length; string1_index++) {
			List<Character> steps = new ArrayList<Character>();
			if(string1_index > 0) { steps.add('L'); }
			table[string1_index][0] = new Entry(string1_index, steps);
		}
		for(int string2_index=0; string2_index<=string2Length; string2_index++) {
			List<Character> steps = new ArrayList<Character>();
			if(string2_index > 0) { steps.add('U'); }
			table[0][string2_index] = new Entry(string2_index, steps);
		}
	}
	
	private int smallestValue(int v1, int v2, int v3)
	{
		int smallest = v1;
		if(v2 < smallest) { smallest = v2; }
		if(v3 < smallest) { smallest = v3; }
		return smallest;
	}
	
	private void calculateTable()
	{		
		for(int i=1; i<=string1Length; i++) {
			for(int j=1; j<=string2Length; j++) {
				Map<String, Integer> values = new HashMap<String, Integer>();
				values.put("insertion", table[i][j-1].distance()+1);
				values.put("deletion", table[i-1][j].distance()+1);
				values.put("substitution", table[i-1][j-1].distance());
				if(string1.charAt(i-1) != string2.charAt(j-1)) {
					values.put("substitution", values.get("substitution")+2);
				}
				
				Entry entry = cheapestOperation(values);
				table[i][j] = entry;
			}
		}
	}
	
	private Entry cheapestOperation(Map<String, Integer> values) {
		int smallestValue = smallestValue(values.get("insertion"), values.get("deletion"), values.get("substitution"));
		Entry entry;
		List<Character> steps = new ArrayList<Character>();
		if(values.get("insertion") == smallestValue) 	{ steps.add('U'); }
		if(values.get("deletion") == smallestValue) 	{ steps.add('L'); }
		if(values.get("substitution") == smallestValue)	{ steps.add('D'); }
		entry = new Entry(smallestValue, steps);
		return entry;
	}
	
	public String[] strings() {
		String[] strings = {string1, string2};
		return strings;
	}
	
	public int[][] distanceTable() {
		int[][] distanceTable = new int[string1Length+1][string2Length+1];
		for(int i=0; i<=string1Length; i++) {
			for(int j=0; j<=string2Length; j++) {
				distanceTable[i][j] = (table[i][j]).distance();
			}
		}
		return distanceTable;
	}
	
	public int editDistance() {
		return (table[string1Length][string2Length]).distance();
	}
	
	private void getBT(BTNode node, int row, int col) {
		for(BTNode child : node.children()) {
				int eRow = row;
				int eCol = col;
				if(child.step() != 'L') { eCol--; }
				if(child.step() != 'U') { eRow--; }
				Entry current = table[eRow][eCol];
				if(current.hasU()) { 
					child.addChild(new BTNode('U', new ArrayList<BTNode>()));
				}
				if(current.hasL()) { 
					child.addChild(new BTNode('L', new ArrayList<BTNode>()));
				}
				if(current.hasD()) { 
					child.addChild(new BTNode('D', new ArrayList<BTNode>()));
				}
				getBT(child, eRow, eCol);
		}
	}
	
	public List<String> backtrace() {
		BTNode root;
		Entry start = table[string1Length][string2Length];
		List<BTNode> steps = new ArrayList<BTNode>();
		if(start.hasU()) { steps.add(new BTNode('U', new ArrayList<BTNode>())); }
		if(start.hasL()) { steps.add(new BTNode('L', new ArrayList<BTNode>())); }
		if(start.hasD()) { steps.add(new BTNode('D', new ArrayList<BTNode>())); }
		root = new BTNode(' ', steps);
		getBT(root, string1Length, string2Length);
		
		BTTree backtrace = new BTTree(root);
		return backtrace.backtraces();
	}
	
}


