import java.util.Stack;
import java.util.Arrays;

public class Observer {
	private Feed feed;
	private String[] string;

	public Observer() {
		feed = new Feed();
	}

	/**
	 * 
	 * @param expression
	 *            acest feed nu va fi afisat la printare.
	 */
	public void badFeed(String[] expression) {
		Stock stock = feed.get(expression[1]);
		if (stock != null) {
			stock.setDontPrint(1);
			stock.setNochanges();
			feed.put(expression[1], stock);
			return;
		}
		stock = new Stock();
		stock.setNochanges();
		stock.setDontPrint(1);
		feed.put(expression[1], stock);
	}

	/**
	 * 
	 * @param expression
	 *            adauga un feed nou; daca exista deja, se fac modificari si se
	 *            adauga in feedMap
	 */
	public void addFeed(String[] expression) {
		Stock stock = feed.get(expression[1]);

		if (stock == null)
			stock = new Stock();
		stock.setFluctuation(Float.parseFloat(expression[2]));
		stock.setLastvalue(Float.parseFloat(expression[2]));
		stock.setNochanges();
		stock.setDontPrint(0);
		feed.put(expression[1], stock);
	}
	
	public void addFeedFromFeedMap(StoreFeed inputFeed, String key) {
		Stock stock = feed.get(key);

		if (stock == null)
			stock = new Stock();
		
		stock.setLastvalue(inputFeed.get(key).getLastvalue());	
		stock.setChangesZero();
		stock.setDontPrint(0);
		feed.put(key, stock);
	}
	
	/**
	 * 
	 * @param expression
	 * @param feed
	 * @return true/false ; verifica daca accepta feed-ul sau nu, prin intermediul
	 *         lui OperatorsFactory
	 */
	public static boolean notify(String[] expression, String[] feed) {
		OperatorsFactory opFactoryInstance = OperatorsFactory.getInstance();
		Operator op = opFactoryInstance.createOperator(expression, feed);
		return op.expression(expression, feed);
	}

	/**
	 * 
	 * @param expression
	 *            copiaza expresia, fara tipul operatiei si ID-ul
	 */
	public void setString(String[] expression) {
		String[] newStr = new String[expression.length - 2];
		newStr = Arrays.copyOfRange(expression, 2, expression.length);
		this.string = newStr;
	}

	public String getString() {
		return this.string[0];
	}

	/**
	 * 
	 * @param ID
	 *            printeaza feed-urile unui observator;
	 */
	public void printFeed(int ID) {
		/* sortare */
		Object[] keys = feed.keySet().toArray();
		Arrays.sort(keys);
	
		
		for (Object name : keys) {
			Stock value = feed.get(name);
			/* daca nu verifica expresia, nu se printeaza */
			if (value.getDontPrint() == 1)
				continue;
			/* daca e prima oara cand se printeaza */
			if (value.getNoprint() == 0) {
				System.out.println("obs " + ID + ": " + name + " " + String.format("%.02f", value.getLastvalue()- 0.005)
						+ " " + "0.00% " + Math.round(value.getNochanges()) );
				value.setNoprint();
				value.setChangesZero();
				value.setPrintlastValue(value.getLastvalue());
			} else {
				/* daca a mai fost printat */
				System.out.println("obs " + ID + ": " + name + " " + String.format("%.02f", value.getLastvalue()- 0.005)
						+ " " + String.format("%.02f",value.getFluctuation() * 100.0)  + "% "
						+ Math.round(value.getNochanges()) );
				value.setChangesZero();
				value.setPrintlastValue(value.getLastvalue());
			}
		}

	}

	/**
	 * 
	 * @param feed
	 * @return ; aduce expresia sub forma poloneza inversa - prima parte din
	 *         Shunting-Yard Algorithm
	 */
	public Stack<String> parseString(String[] feed) {
		return ParseString.parseString(string, feed);
	}

	/**
	 * 
	 * @param queue
	 * @return 1/0; construieste un arbore din prima parte a algoritmului , asupra
	 *         caruia se aplica Visitor Pattern
	 */
	public int getQueueResult(Stack<String> queue) {
		PostfixTree tree = new PostfixTree(queue);
		tree.createTree();
		return tree.root.accept(new Calculator());
	}
}
