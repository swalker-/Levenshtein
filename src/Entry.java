
public class Entry {
	private boolean u, l, d;
	private int distance;
	
	public Entry() {
		u=false;
		l=false;
		d=false;
		distance=0;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int distance() {
		return distance;
	}
	
	public void setU(boolean val) {
		u = val;
	}

	public boolean u() {
		return u;
	}
	
	public void setL(boolean val) {
		l = val;
	}
	
	public boolean l() {
		return l;
	}
	
	public void setD(boolean val) {
		d = val;
	}
	
	public boolean d() {
		return d;
	}
	
	public String steps() {
		String result = "";
		result += (u) ? 'U' : '-';
		result += (l) ? 'L' : '-';
		result += (d) ? 'D' : '-';
		return result;
	}
	public void setSteps(boolean u, boolean l, boolean d) {
		setU(u);
		setL(l);
		setD(d);
	}
}
