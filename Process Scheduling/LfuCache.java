/*
 * Metoda primeste cache (vector), capacitatea acestuia, tipul procesului
 * numarul procesat, timpul curent si fisierul de output
 * 
 * Cache-ul se parcuge (si stocheaza) de la sfarsit la baza.
 */
public class LfuCache extends Cache {

	static void lfuCache(CacheProcessStructure[] cache, int capacity, String type, int processed_no, int time_used,
			HomeworkWriter out) {
		int result = 0, j = 0, min = 1, delete = capacity - 1;

		for (int i = capacity - 1; i >= -1; i--) {
			/*
			 * daca indexul = -1, inseamna ca s-a umplut Cache-ul; se cauta numarul minim de
			 * folosire si se sterge informatia procesului respectiv
			 */
			if (i == -1) {
				min = cache[capacity - 1].getUsed();
				for (j = capacity - 2; j >= 0; j--)
					if (cache[j].getUsed() < min) {
						min = cache[j].getUsed();
						delete = j;
					}
				cache[delete] = null;
				i = delete;
			}
			/*
			 * daca nu se afla nimic la indexul curent in Cache, se salveaza informatiile
			 * necesare
			 */
			if (cache[i] == null) {
				result = NoCache(type, processed_no);
				cache[i] = new CacheProcessStructure(type, processed_no, result, 1, time_used);
				out.println(processed_no + " " + type + " " + cache[i].getResult() + " " + "Computed");
				return;
			}
			/*
			 * daca tipul procesului si numarul procesat coincid cu cele cautate, se
			 * actualizeaza numarul de dati al folosirii procesului
			 */
			if (cache[i].getType().equals(type))
				if (cache[i].getProcessed_no() == processed_no) {
					cache[i].setUsed();
					out.println(processed_no + " " + type + " " + cache[i].getResult() + " " + "FromCache");
					return;
				}
		}
	}
}
