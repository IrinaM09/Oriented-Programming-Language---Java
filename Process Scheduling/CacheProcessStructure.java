/*
 * Structura unui obiect de tipul Cache are:
 * tipul procesului, numarul procesat, rezulatul,
 * timpul curent(la care a fost apelat procesul), de cate ori a fost folosit procesul pe un numar 
 */
public class CacheProcessStructure {
	private String type;
	private int processed_no;
	private int result;
	private int used;
	private int time_used;

	public CacheProcessStructure(String type, int processed_no, int result, int used, int time_used) {
		this.type = type;
		this.processed_no = processed_no;
		this.result = result;
		this.used = used;
		this.time_used = time_used;

	}

	public String getType() {
		return type;
	}

	public int getProcessed_no() {
		return processed_no;
	}

	public int getResult() {
		return result;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed() {
		used++;
	}

	public int getTimeUsed() {
		return time_used;
	}

	public void setTimeUsed(int time_used) {
		this.time_used = time_used;
	}
}
