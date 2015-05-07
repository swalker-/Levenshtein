import java.util.List;
import java.util.Map;


public class Levenshtein {
	
	private final int length1;
	private final int length2;
	private final String string1;
	private final String string2;
	private final Table table;
	
	public Levenshtein(String s1, String s2) {
		string1 = s1.toLowerCase();
		string2 = s2.toLowerCase();
		length1 = string1.length();
		length2 = string2.length();
		table = new Table(length1+1, length2+1);
		setupMatrix();
		calculateMatrix();
	}
	
	private void setupMatrix()
	{
		for(int i=0; i<=length1; i++) {
			Entry e = new Entry();
			e.setDistance(i);
			if(i > 0) { e.setL(true); }
			table.setEntry(i, 0, e);
		}
		for(int j=0; j<=length2; j++) {
			Entry e = new Entry();
			e.setDistance(j);
			if(j > 0) { e.setU(true); }
			table.setEntry(0, j, e);
		}
	}
	
	private int smallestValue(int v1, int v2, int v3)
	{
		int smallest = v1;
		if(v2 < smallest) { smallest = v2; }
		if(v3 < smallest) { smallest = v3; }
		return smallest;
	}
	
	private void calculateMatrix()
	{
		int insertionValue;
		int deletionValue;
		int substitutionValue;
		
		for(int i=1; i<=length1; i++) {
			for(int j=1; j<=length2; j++) {
				insertionValue = table.getEntry(i, j-1).distance() + 1;
				deletionValue = table.getEntry(i-1, j).distance() + 1;
				substitutionValue = table.getEntry(i-1, j-1).distance();
				if(string1.charAt(i-1) != string2.charAt(j-1)) {
					substitutionValue += 2;
				}
				
				int smallestValue = smallestValue(insertionValue, deletionValue, substitutionValue);
				Entry entry = new Entry();
				entry.setDistance(smallestValue);
				if(insertionValue == smallestValue) 	{ entry.setU(true); }
				if(deletionValue == smallestValue) 		{ entry.setL(true); }
				if(substitutionValue == smallestValue)	{ entry.setD(true); }
				table.setEntry(i, j, entry);
			}
		}
	}
	
	public String[] strings() {
		String[] strings = {string1, string2};
		return strings;
	}
	
	public int[][] distanceTable() {
		int[][] distanceTable = new int[length1+1][length2+1];
		for(int i=0; i<=length1; i++) {
			for(int j=0; j<=length2; j++) {
				distanceTable[i][j] = table.getEntry(i, j).distance();
			}
		}
		return distanceTable;
	}
	
	public int editDistance() {
		return table.getEntry(length1, length2).distance();
	}
	
	private void getBT(BTNode node, int row, int col) {
		for(Map.Entry<Character, BTNode> entry : node.children().entrySet()) {
			if(node.hasChild(entry.getKey())) {
				int eRow = row;
				int eCol = col;
				if(entry.getKey() != 'L') { eCol--; }
				if(entry.getKey() != 'U') { eRow--; }
				Entry current = table.getEntry(eRow, eCol);
				if(current.hasU()) { entry.getValue().addUChild(new BTNode('U')); }
				if(current.hasL()) { entry.getValue().addLChild(new BTNode('L')); }
				if(current.hasD()) { entry.getValue().addDChild(new BTNode('D')); }
				getBT(entry.getValue(), eRow, eCol);
			}
		}
	}
	
	public List<String> backtrace() {
		BTTree backtrace = new BTTree();
		BTNode root = backtrace.root();
		Entry start = table.getEntry(length1, length2);
		if(start.hasU()) { root.addUChild(new BTNode('U')); }
		if(start.hasL()) { root.addLChild(new BTNode('L')); }
		if(start.hasD()) { root.addDChild(new BTNode('D')); }
		
		getBT(root, length1, length2);
		
		return backtrace.backtraces();
	}
	
}


