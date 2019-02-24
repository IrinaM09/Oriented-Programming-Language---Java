/*
 * Foloseste %9973 la fiecare actualizare a rezultatului pentru ca valoarea sa
 * nu iasa din range.
 */
public class Fibonacci extends Process {
	int calculate(int number) {
		if (number < 0)
			return -1;
		if (number == 0)
			return 0;

		int first = 0, second = 1, third = 0;
		while (number != 0) {
			third = second;
			second = (second + first) % 9973;
			first = third;
			number--;
		}
		return third;
	}
}
