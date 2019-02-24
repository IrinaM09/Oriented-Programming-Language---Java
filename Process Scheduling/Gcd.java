/*
 * Returneaza cel mai mare divizor comun dintre doua numere
 */
public class Gcd {

	static int gcd(int first, int sec) {
		while (first != 0) {
			int temp = first;
			first = sec % first;
			sec = temp;
		}
		return sec;
	}
}
