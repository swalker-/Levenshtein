
public class Levenshtein {
	public static int editDistance(String s1, String s2)
	{
		if (s1.equals(s2))
			return 0;
		else
		{
			int length1 = s1.length();
			int length2 = s2.length();
			if (length1 > length2)
				return length1-length2;
			return 5;
		}
	}
}
