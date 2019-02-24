/*
 * Verifica pana la jumatatea numarului daca acesta este prim.
 */
public class CheckPrime extends Process {
	int calculate(int number) {

		if (number <= 1)
			return 0; // false

		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0)
				return 0;
		}
		return 1;
	}
}
