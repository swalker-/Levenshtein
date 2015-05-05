public class MatrixData {
	private int[][] m;
	private final String s1;
	private final String s2;
	private final int l1;
	private final int l2;
	
	MatrixData(int[][] m, String s1, int l1, String s2, int l2) {
		this.m = m;
		this.s1 = s1;
		this.s2 = s2;
		this.l1 = l1;
		this.l2 = l2;
	}
	
	public int[][] matrix() {
		return m;
	}
	
	public void setValue(int row, int col, int value) {
		m[row][col] = value;
	}
	
	public String string1() {
		return s1;
	}
	
	public String string2() {
		return s2;
	}
	
	public int length1() {
		return l1;
	}
	
	public int length2() {
		return l2;
	}
}
