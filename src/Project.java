public class Project {

	public static void main(String[] args) {
		String s1 = args[0];
		String s2 = args[1];
		
		Levenshtein l = new Levenshtein(s1);
		l.comparisonString(s2);
		LevenshteinPrinter.printDetails(l);
	}
}
