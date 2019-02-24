public class Stock {
	private float lastValue;
	private float fluctuation;
	private float PrintlastValue;
	private int noChanges;
	private int noPrint;
	private int dontPrint;

	public Stock() {
	}

	public float getLastvalue() {
		return lastValue;
	}

	public float getFluctuation() {
		if (this.noChanges == 0)
			this.fluctuation = 0;
		return fluctuation;
	}

	public int getNochanges() {
		return noChanges;
	}

	public int getDontPrint() {
		return dontPrint;
	}

	public float getPrintlastValue() {
		return PrintlastValue;
	}

	public void setPrintlastValue(float value) {
		this.PrintlastValue = value;
	}

	public void setLastvalue(float Lastvalue) {
		this.lastValue = Lastvalue;
	}

	public void setFluctuation(float newValue) {
		if (this.noPrint == 0)
			this.fluctuation = 0;
		else
			this.fluctuation = (newValue - this.PrintlastValue) / this.PrintlastValue;
	}

	public void setNochanges() {
		this.noChanges++;
	}

	public void setChangesZero() {
		this.noChanges = 0;
	}

	public int getNoprint() {
		return this.noPrint;
	}

	public void setNoprint() {
		this.noPrint++;
	}

	public void setDontPrint(int number) {
		this.dontPrint = number;
	}
}
