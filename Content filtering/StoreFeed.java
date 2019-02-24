import java.util.HashMap;

/**
 * StoreFeed has a HashMap of every stock from input defined so: HashMap (key,
 * value) = HashMap(Stock name, Stock info)
 */
public class StoreFeed extends HashMap<String, Stock> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StoreFeed storeFeed(StoreFeed inputFeed, String[] expression) {
		Stock stock = inputFeed.get(expression[1]);
		if (stock == null)
			stock = new Stock();
		stock.setLastvalue(Float.parseFloat(expression[2]));
		inputFeed.put(expression[1], stock);
		
		return inputFeed;
	}
}
