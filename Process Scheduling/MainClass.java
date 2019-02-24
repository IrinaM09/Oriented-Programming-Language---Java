import java.io.FileNotFoundException;

public class MainClass {

	public static void main(String[] args) throws FileNotFoundException {

		HomeworkReader fileReader = new HomeworkReader(args[0]);
		HomeworkWriter out = new HomeworkWriter(args[1]);
		ProblemData date = fileReader.readData();
		/**
		 * In functie de tipul de planificator, se apeleaza metoda asociata acestuia.
		 */
		if (date.getSchedulerType().equals("RandomScheduler")) {
			Scheduler rand_schedule = new Scheduler();
			rand_schedule.randomScheduler(date, out);
		}

		if (date.getSchedulerType().equals("RoundRobinScheduler")) {
			RoundRobinScheduler round_schedule = new RoundRobinScheduler();
			round_schedule.roundrobinScheduler(date, out);
		}

		if (date.getSchedulerType().equals("WeightedScheduler")) {
			WeightedScheduler weighted_schedule = new WeightedScheduler();
			weighted_schedule.weightedScheduler(date, out);
		}
	}
}