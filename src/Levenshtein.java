import java.util.ArrayList;


public class Levenshtein {
	
	private final int length1;
	private final int length2;
	private final String string1;
	private final String string2;
	private final int[][] table;
	
	public Levenshtein(String s1, String s2) {
		string1 = s1.toLowerCase();
		string2 = s2.toLowerCase();
		length1 = string1.length();
		length2 = string2.length();
		table = new int[length1+1][length2+1];
		setupMatrix();
		calculateMatrix();
		
	}
	
	private void setupMatrix()
	{
		for(int i=0; i<=length1; i++) {
			table[i][0] = i;
		}
		for(int j=0; j<=length2; j++) {
			table[0][j] = j;
		}
	}
	
	private int smallestValue(int v1, int v2, int v3)
	{
		int smallest = v1;
		if(v2 < smallest)
			smallest = v2;
		if(v3 < smallest)
			smallest = v3;
		return smallest;
	}
	
	private void calculateMatrix()
	{
		int insertionValue;
		int deletionValue;
		int substitutionValue;
		
		for(int i=1; i<= length1; i++) {
			for(int j=1; j<=length2; j++) {
				insertionValue = table[i][j-1] + 1;
				deletionValue = table[i-1][j] + 1;
				substitutionValue = table[i-1][j-1];
				if(string1.charAt(i-1) != string2.charAt(j-1)) {
					substitutionValue += 2;
				}
				table[i][j] = smallestValue(insertionValue, deletionValue, substitutionValue);
			}
		}
	}
	
	public ArrayList<String> strings() {
		ArrayList<String> strings = new ArrayList<String>();
		strings.add(string1);
		strings.add(string2);
		return strings;
	}
	
	public int[][] table() {
		return table;
	}
	
	public int editDistance() {
		return table[length1][length2];
	}
}


