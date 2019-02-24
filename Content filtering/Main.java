
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		HashMapObserver observers = new HashMapObserver();
		StoreFeed inputFeed = new StoreFeed();
		String line = new String();
		String[] words;

		if (scan.nextLine().equals("begin"))
			while (scan.hasNext()) {
				line = scan.nextLine();
				/* end - terminare */
				if (line.equals("end"))
					break;
				/* delimitare string citit */
				words = line.split(" ");

				switch (words[0]) {
				/*
				 * mai intai aduce expresia sub un format corect si apoi se adauga in hashMmap
				 */
				case "create_obs": {
					Observer observer = new Observer();
					line = StringFormat.convertStringFormat(line);
					words = line.split(" ");
					observer.setString(words);
					observers.put(Integer.parseInt(words[1]), observer);

					/* se adauga feed-urile vechi la noul observator, daca e cazul */
					GetFeed.pastFeed(inputFeed, words, observers);
					break;
				}

				/*
				 * se adauga feed-urile primite si intr-o colectie separata si apoi observatorul
				 * primeste noul feed, daca verifica expresia
				 */
				case "feed": {
					inputFeed = inputFeed.storeFeed(inputFeed, words);
					GetFeed.currentFeed(inputFeed, words, observers);
					break;
				}
				/* sterge observatorul */
				case "delete_obs": {
					observers.remove(Integer.parseInt(words[1]));
					break;
				}
				/* printeaza observatorul */
				case "print": {
					if (observers.get(Integer.parseInt(words[1])) == null)
						System.out.println("ID invalid");
					else
						observers.get(Integer.parseInt(words[1])).printFeed(Integer.parseInt(words[1]));
					break;
				}
				}
			}
		scan.close();
	}
}
