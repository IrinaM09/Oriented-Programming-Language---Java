
/*
 * Primeste datele din fisierul de input si un fisier de output 
 */
import java.io.FileNotFoundException;

public class Scheduler {
	public void randomScheduler(ProblemData date, HomeworkWriter out) throws FileNotFoundException {
		int value = 0;
		int result = 0, time = 1;
		ProcessStructure[] p = new ProcessStructure[date.getProcesses().length];
		p = date.getProcesses();

		int[] numere = new int[date.getNumbersToBeProcessed().length];
		numere = date.getNumbersToBeProcessed();
		CacheProcessStructure[] cache = new CacheProcessStructure[date.getCacheCapacity()];

		for (int i = 0; i < numere.length; i++) {
			/*
			 * se genereaza o valoare random, pentru a apela aleator un proces
			 */
			value = 0 + (int) (Math.random() * ((date.getProcesses().length - 1) + 1));

			/*
			 * se verifica ce tip de Cache trebuie folosit si se apeleaza corespunzator; se
			 * incrementeaza timpul curent (al folosirii unui proces)
			 */
			if (date.getCacheType().equals("NoCache")) {
				result = Cache.NoCache(p[value].getType(), numere[i]);
				out.println(numere[i] + " " + p[value].getType() + " " + result + " " + "Computed");
			} else {

				if (date.getCacheType().equals("LruCache")) {
					LruCache.lruCache(cache, date.getCacheCapacity(), p[value].getType(), numere[i], time, out);
				} else
					LfuCache.lfuCache(cache, date.getCacheCapacity(), p[value].getType(), numere[i], time, out);
				time++;
			}
		}
		out.close();
	}
}
