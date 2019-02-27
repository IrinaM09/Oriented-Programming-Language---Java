
public class Pair {
	private int noVisited;
	private char position;

	/**
	 * 
	 * @param noVisited
	 * @param position
	 *            salveaza de cate ori a fost vizitata o celula 
	 *            si pozitia (E / S / V / N)
	 */
	Pair(int noVisited, char position) {
		this.noVisited = noVisited;
		this.position = position;
	}

	public int getNoVisited() {
		return noVisited;
	}

	public void setNoVisited(int noVisited) {
		this.noVisited = noVisited;
	}

	public char getPosition() {
		return position;
	}

	public void setPosition(char position) {
		this.position = position;
	}
}
