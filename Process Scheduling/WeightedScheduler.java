
/*
 * Primeste datele din fisierul de input si un fisier de output 
 */
import java.io.FileNotFoundException;

public class WeightedScheduler extends Scheduler {
	public void weightedScheduler(ProblemData date, HomeworkWriter out) throws FileNotFoundException {
		ProcessStructure[] process = new ProcessStructure[date.getProcesses().length];
		process = date.getProcesses();
		int[] numere = new int[date.getNumbersToBeProcessed().length];
		numere = date.getNumbersToBeProcessed();
		int result = 0, time_used = 1;

		int cmmdc = process[0].getWeight(), sum = process[0].getWeight(), i = 0;
		CacheProcessStructure[] cache = new CacheProcessStructure[date.getCacheCapacity()];
		/*
		 * se calculeaza cel mai mare divizor comun dintre toate procesele si timpul
		 * (folosit la momentele de timp multiplu de el)
		 */
		for (i = 1; i < date.getProcesses().length; i++) {
			cmmdc = Gcd.gcd(cmmdc, process[i].getWeight());
			sum += process[i].getWeight();
		}
		int time = sum / cmmdc, no = 0;
		int execute_no = 0;
		for (i = 0; i < numere.length; i++) {
			/*
			 * daca momentul de timp e multiplu de t, se reiau procesele
			 */
			if (i % time == 0) {
				no = 0;
				execute_no = (process[no].getWeight() * time / sum);
			}
			/*
			 * se verifica daca procesul curent s-a executat de numarul de dati aferent daca
			 * da, se trece la urmatorul proces si i se calculeaza numarul de executii
			 */
			if (execute_no == 0) {
				no++;
				execute_no = (process[no].getWeight() * time / sum);
			}
			/*
			 * se verifica ce tip de Cache trebuie folosit si se apeleaza corespunzator; se
			 * incrementeaza timpul curent si numarul de executii al unui proces
			 */
			if (date.getCacheType().equals("NoCache")) {
				result = Cache.NoCache(process[no].getType(), numere[i]);
				out.println(numere[i] + " " + process[no].getType() + " " + result + " " + "Computed");
			} else {

				if (date.getCacheType().equals("LruCache")) {
					LruCache.lruCache(cache, date.getCacheCapacity(), process[no].getType(), numere[i], time_used, out);
				} else
					LfuCache.lfuCache(cache, date.getCacheCapacity(), process[no].getType(), numere[i], time_used, out);
			}
			execute_no--;
			time_used++;
		}
		out.close();
	}
}
