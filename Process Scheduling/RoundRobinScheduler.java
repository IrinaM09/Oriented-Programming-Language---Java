
/*
 * Primeste datele din fisierul de input si un fisier de output 
 */
import java.io.FileNotFoundException;

public class RoundRobinScheduler extends Scheduler {

	public void roundrobinScheduler(ProblemData date, HomeworkWriter out) throws FileNotFoundException {

		ProcessStructure[] p = new ProcessStructure[date.getProcesses().length];
		p = date.getProcesses();
		int[] numere = new int[date.getNumbersToBeProcessed().length];
		numere = date.getNumbersToBeProcessed();
		int proc = 0;
		int result = 0, time = 1;
		CacheProcessStructure[] cache = new CacheProcessStructure[date.getCacheCapacity()];

		for (int i = 0; i < numere.length; i++) {
			/*
			 * se alege un proces astfel incat timpul de executie dintre doua procese sa nu
			 * depaseasca o unitate astfel:
			 */
			if (proc % p.length == 0) {
				proc = 0;
			}
			/*
			 * se verifica ce tip de Cache trebuie folosit si se apeleaza corespunzator; se
			 * incrementeaza timpul curent si numarul procesului
			 */
			if (date.getCacheType().equals("NoCache")) {
				result = Cache.NoCache(p[proc].getType(), numere[i]);
				out.println(numere[i] + " " + p[proc].getType() + " " + result + " " + "Computed");
			} else {

				if (date.getCacheType().equals("LruCache")) {
					LruCache.lruCache(cache, date.getCacheCapacity(), p[proc].getType(), numere[i], time, out);
				} else
					LfuCache.lfuCache(cache, date.getCacheCapacity(), p[proc].getType(), numere[i], time, out);
			}
			proc++;
			time++;
		}
		out.close();
	}
}
