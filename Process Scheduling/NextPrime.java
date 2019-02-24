/*
 * Foloseste algoritmul din metoda CheckPrime pana cand gaseste un numar prim.
 */
public class NextPrime extends Process {
	CheckPrime check_no = new CheckPrime();

	int calculate(int number) {
		if (number <= 1)
			return 2;
		if (number == 2)
			return 3;
		int i = number + 1;

		while (check_no.calculate(i) == 0) {
			number++;
			i = number;
		}

		return i;
	}

}
