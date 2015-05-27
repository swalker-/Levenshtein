
public class OperationCosts {
	private int insertion;
	private int deletion;
	private int substitution;
	
	public void set(int insertion, int deletion, int substitution) {
		this.insertion = insertion;
		this.deletion = deletion;
		this.substitution = substitution;
	}
	
	public int insertion() {
		return insertion;
	}
	
	public int deletion() {
		return deletion;
	}
	
	public int substitution() {
		return substitution;
	}
}
