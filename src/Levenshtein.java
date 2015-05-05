
public class Levenshtein {
	
	private static void setupMatrix(MatrixData m)
	{
		for(int i=0; i<=m.length1(); i++) {
			m.setValue(i,0,i);
		}
		for(int j=0; j<=m.length2(); j++) {
			m.setValue(0,j,j);
		}
	}
	
	private static int smallestValue(int v1, int v2, int v3)
	{
		int smallest = v1;
		if(v2 < smallest)
			smallest = v2;
		if(v3 < smallest)
			smallest = v3;
		return smallest;
	}
	
	private static void calculateMatrix(MatrixData m)
	{
		int insertionValue;
		int deletionValue;
		int substitutionValue;
		
		for(int i=1; i<= m.length1(); i++) {
			for(int j=1; j<=m.length2(); j++) {
				insertionValue = m.matrix()[i][j-1] + 1;
				deletionValue = m.matrix()[i-1][j] + 1;
				substitutionValue = m.matrix()[i-1][j-1];
				if(m.string1().charAt(i-1) != m.string2().charAt(j-1)) {
					substitutionValue += 2;
				}
				m.setValue(i,j,smallestValue(insertionValue, deletionValue, substitutionValue));
			}
		}
	}
	
	public static int[][] values(String s1, String s2)
	{
		int length1 = s1.length();
		int length2 = s2.length();
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		int[][] lValues = new int[length1+1][length2+1];
		MatrixData m = new MatrixData(lValues, s1, length1, s2, length2);

		setupMatrix(m);
		calculateMatrix(m);
		return m.matrix();
	}
	
	public static int editDistance(String s1, String s2)
	{
		int length1 = s1.length();
		int length2 = s2.length();
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		if (s1.equals(s2))
			return 0;
		else
		{		
			if (length1 > length2)
				return length1-length2;
			else if (length1 < length2)
				return length2-length1;
			else
				return 2;
		}
	}
}


