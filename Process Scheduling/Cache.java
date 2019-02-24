/*
 * In metoda NoCache se apeleaza un proces cu numarul care trebuie procesat
 * si se returneaza rezultatul. 
 */
public class Cache {

	static int NoCache(String type, int processed_no) {

		if (type.equals("CheckPrime")) {

			CheckPrime no = new CheckPrime();
			return no.calculate(processed_no);
		}
		if (type.equals("NextPrime")) {

			NextPrime no = new NextPrime();
			return no.calculate(processed_no);
		}
		if (type.equals("Fibonacci")) {

			Fibonacci no = new Fibonacci();
			return no.calculate(processed_no);
		}
		if (type.equals("Sqrt")) {

			Sqrt no = new Sqrt();
			return no.calculate(processed_no);
		}
		if (type.equals("Square")) {

			Square no = new Square();
			return no.calculate(processed_no);
		}
		if (type.equals("Cube")) {

			Cube no = new Cube();
			return no.calculate(processed_no);
		} else {

			Factorial no = new Factorial();
			return no.calculate(processed_no);
		}
	}
}
