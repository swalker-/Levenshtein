
public class Entry {
	private boolean uVal, lVal, dVal;
	private int distance;
	
	public Entry() {
		uVal=false;
		lVal=false;
		dVal=false;
		distance=0;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int distance() {
		return distance;
	}
	
	public void setUVal(boolean val) {
		uVal = val;
	}

	public char uVal() {
		if(uVal) { return 'U'; }
		return '-';
	}
	
	public void setLVal(boolean val) {
		lVal = val;
	}
	
	public char lVal() {
		if(lVal) { return 'L'; }
		return '-';
	}
	
	public void setDVal(boolean val) {
		dVal = val;
	}
	
	public char dVal() {
		if(dVal) { return 'D'; }
		return '-';
	}
	
	public void setValues(boolean u, boolean l, boolean d) {
		setUVal(u);
		setLVal(l);
		setDVal(d);
	}
}
