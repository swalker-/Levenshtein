import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Levenshtein {
	
	private final int length1;
	private final int length2;
	private final String string1;
	private final String string2;
	private final Entry[][] table;
	
	public Levenshtein(String s1, String s2) {
		string1 = s1.toLowerCase();
		string2 = s2.toLowerCase();
		length1 = string1.length();
		length2 = string2.length();
		table = new Entry[length1+1][length2+1];
		setupTable();
		calculateTable();
	}
	
	private void setupTable()
	{
		for(int i=0; i<=length1; i++) {
			Entry e;
			List<Character> steps = new ArrayList<Character>();
			if(i > 0) { steps.add('L'); }
			e = new Entry(i, steps);
			table[i][0] = e;
		}
		for(int j=0; j<=length2; j++) {
			Entry e;
			List<Character> steps = new ArrayList<Character>();
			if(j > 0) { steps.add('U'); }
			e = new Entry(j, steps);
			table[0][j] = e;
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
		for(int i=1; i<=length1; i++) {
			for(int j=1; j<=length2; j++) {
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
		int[][] distanceTable = new int[length1+1][length2+1];
		for(int i=0; i<=length1; i++) {
			for(int j=0; j<=length2; j++) {
				distanceTable[i][j] = (table[i][j]).distance();
			}
		}
		return distanceTable;
	}
	
	public int editDistance() {
		return (table[length1][length2]).distance();
	}
	
	private void getBT(BTNode node, int row, int col) {
		for(BTNode entry : node.children()) {
				int eRow = row;
				int eCol = col;
				if(entry.step() != 'L') { eCol--; }
				if(entry.step() != 'U') { eRow--; }
				Entry current = table[eRow][eCol];
				if(current.hasU()) { 
					List<BTNode> steps = entry.children();
					steps.add(new BTNode('U', new ArrayList<BTNode>()));
					entry = new BTNode(entry.step(), steps);
				}
				if(current.hasL()) { 
					List<BTNode> steps = entry.children();
					steps.add(new BTNode('L', new ArrayList<BTNode>()));
					entry = new BTNode(entry.step(), steps);
				}
				if(current.hasD()) { 
					List<BTNode> steps = entry.children();
					steps.add(new BTNode('D', new ArrayList<BTNode>()));
					entry = new BTNode(entry.step(), steps);
				}
				getBT(entry, eRow, eCol);
		}
	}
	
	public List<String> backtrace() {
		BTNode root;
		Entry start = table[length1][length2];
		List<BTNode> steps = new ArrayList<BTNode>();
		if(start.hasU()) { steps.add(new BTNode('U', new ArrayList<BTNode>())); }
		if(start.hasL()) { steps.add(new BTNode('L', new ArrayList<BTNode>())); }
		if(start.hasD()) { steps.add(new BTNode('D', new ArrayList<BTNode>())); }
		root = new BTNode(' ', steps);
		getBT(root, length1, length2);
		
		BTTree backtrace = new BTTree(root);
		return backtrace.backtraces();
	}
	
}


