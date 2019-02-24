import java.util.*;

public class GetFeed {

	/**
	 * @param inputFeed
	 *            - colectia de feed-uri
	 * @param words
	 *            - linia cu feed-ul de la stdin
	 * @param observers
	 *            - colectia de observatori
	 * 
	 *            adauga feed-urile vechi la noul observator, daca e cazul
	 */
	static void pastFeed(StoreFeed inputFeed, String[] words, HashMapObserver observers) {
		if (inputFeed.isEmpty() == false) {
			Set<String> keys = inputFeed.keySet();
			for (String currKey : keys) {

				Integer ID = Integer.parseInt(words[1]);

				if (observers.get(ID).getString().equals("nil"))
					observers.get(ID).addFeedFromFeedMap(inputFeed, currKey);
				else {
					Stack<String> queue = new Stack<String>();
					String[] newInfo = new String[3];
					newInfo[1] = currKey;
					newInfo[2] = Float.toString(inputFeed.get(currKey).getLastvalue());
					queue = observers.get(ID).parseString(newInfo);
					if (observers.get(ID).getQueueResult(queue) == 1)
						observers.get(ID).addFeedFromFeedMap(inputFeed, currKey);
				}
			}
		}
	}

	/**
	 * 
	 * @param inputFeed
	 * @param words
	 * @param observers
	 * 
	 *            daca operatorul este nil, se adauga feed ul direct, altel se
	 *            verifica prin Shunting-Yard rezultatul expresiei
	 */
	static void currentFeed(StoreFeed inputFeed, String[] words, HashMapObserver observers) {
		Set<Integer> keys = observers.keySet();
		for (Integer key : keys) {
			if (observers.get(key).getString().equals("nil"))
				observers.get(key).addFeed(words);
			else {
				Stack<String> queue = new Stack<String>();
				queue = observers.get(key).parseString(words);
				if (observers.get(key).getQueueResult(queue) == 1)
					observers.get(key).addFeed(words);
				else
					observers.get(key).badFeed(words);
			}
		}
	}
}
