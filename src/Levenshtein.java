
public class Levenshtein {
	
	private static void setupArray(int[][] lValues, int length1, int length2)
	{
		for(int i=0; i<=length1; i++) {
			lValues[i][0] = i;
		}
		for(int j=0; j<=length2; j++) {
			lValues[0][j] = j;
		}
	}
	
	public static int[][] values(String s1, String s2)
	{
		int length1 = s1.length();
		int length2 = s2.length();
		int[][] lValues = new int[length1+1][length2+1];
		return lValues;
	}
	
	public static int editDistance(String s1, String s2)
	{
		int length1 = s1.length();
		int length2 = s2.length();
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
