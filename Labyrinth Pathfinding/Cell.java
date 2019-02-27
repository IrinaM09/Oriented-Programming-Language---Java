
public abstract class Cell {
	private boolean visitable;
	private int noVisited;
	private int i;
	private int j;

	public boolean getVisitable() {
		return visitable;
	}

	public void setVisitable(boolean visitable) {
		this.visitable = visitable;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getNoVisited() {
		return noVisited;
	}

	public void setNoVisited(int noVisited) {
		this.noVisited = noVisited;
	}
}
