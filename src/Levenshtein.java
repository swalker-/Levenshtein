import java.util.ArrayList;
import java.util.List;



public final class Levenshtein {
	
	private final String baseString;
	private final int baseStringLength;
	private String comparisonString;
	private int comparisonStringLength;
	private Entry[][] table;
	
	public Levenshtein(String s1) {
		baseString = s1.toLowerCase();
		baseStringLength = baseString.length();
	}
	
	public void comparisonString(String s2) {
		comparisonString = s2.toLowerCase();
		comparisonStringLength = comparisonString.length();
		table = new Entry[baseStringLength+1][comparisonStringLength+1];
		setupTable();
		calculateTable();
	}
	
	private void setupTable()
	{
		for(int baseStringIndex=0; baseStringIndex<=baseStringLength; baseStringIndex++) {
			List<Character> steps = new ArrayList<Character>();
			if(baseStringIndex > 0) { steps.add('L'); }
			table[baseStringIndex][0] = new Entry(baseStringIndex, steps);
		}
		for(int comparisonStringIndex=0; comparisonStringIndex<=comparisonStringLength; comparisonStringIndex++) {
			List<Character> steps = new ArrayList<Character>();
			if(comparisonStringIndex > 0) { steps.add('U'); }
			table[0][comparisonStringIndex] = new Entry(comparisonStringIndex, steps);
		}
	}
	
	private int smallestValue(int value1, int value2, int value3)
	{
		return Math.min(value1, Math.min(value2,value3));
	}
	
	private void calculateTable()
	{		
		int insertionCost, deletionCost, substitutionCost;
		OperationCosts costs = new OperationCosts();
		for(int baseStringIndex=1; baseStringIndex<=baseStringLength; baseStringIndex++) {
			for(int comparisonStringIndex=1; comparisonStringIndex<=comparisonStringLength; comparisonStringIndex++) {
				insertionCost = table[baseStringIndex][comparisonStringIndex-1].distance()+1;
				deletionCost = table[baseStringIndex-1][comparisonStringIndex].distance()+1;
				substitutionCost = table[baseStringIndex-1][comparisonStringIndex-1].distance();
				if(!sameCharacter(baseStringIndex, comparisonStringIndex)) {
					substitutionCost+=2;
				}
				costs.set(insertionCost, deletionCost, substitutionCost);
				table[baseStringIndex][comparisonStringIndex] = cheapestOperation(costs);
			}
		}
	}
	
	private boolean sameCharacter(int baseStringIndex, int comparisonStringIndex) {
		return baseString.charAt(baseStringIndex-1) == comparisonString.charAt(comparisonStringIndex-1);
	}
	
	private Entry cheapestOperation(OperationCosts costs) {
		int smallestValue = smallestValue(costs.insertion(), costs.deletion(), costs.substitution());
		List<Character> steps = new ArrayList<Character>();
		if(costs.insertion() == smallestValue) 		{ steps.add('U'); }
		if(costs.deletion() == smallestValue) 		{ steps.add('L'); }
		if(costs.substitution() == smallestValue)	{ steps.add('D'); }
		return new Entry(smallestValue, steps);
	}
	
	public String[] strings() {
		String[] strings = {baseString, comparisonString};
		return strings;
	}
	
	public int[][] distanceTable() {
		int[][] distanceTable = new int[baseStringLength+1][comparisonStringLength+1];
		for(int i=0; i<=baseStringLength; i++) {
			for(int j=0; j<=comparisonStringLength; j++) {
				distanceTable[i][j] = (table[i][j]).distance();
			}
		}
		return distanceTable;
	}
	
	public int editDistance() {
		return (table[baseStringLength][comparisonStringLength]).distance();
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
		Entry start = table[baseStringLength][comparisonStringLength];
		List<BTNode> steps = new ArrayList<BTNode>();
		if(start.hasU()) { steps.add(new BTNode('U', new ArrayList<BTNode>())); }
		if(start.hasL()) { steps.add(new BTNode('L', new ArrayList<BTNode>())); }
		if(start.hasD()) { steps.add(new BTNode('D', new ArrayList<BTNode>())); }
		root = new BTNode(' ', steps);
		getBT(root, baseStringLength, comparisonStringLength);
		
		BTTree backtrace = new BTTree(root);
		return backtrace.backtraces();
	}
	
}


