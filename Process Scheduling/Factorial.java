/*
 * Foloseste %9973 la fiecare actualizare a rezultatului pentru ca valoarea sa
 * nu iasa din range.
 */
public class Factorial extends Process {
	int calculate(int number) {
		if (number < 0)
			return 0;
		if (number == 0)
			return 1;
		int result = 1;
		while (number != 0) {
			result = (result * number) % 9973;
			number--;
		}
		return result;
	}

}
