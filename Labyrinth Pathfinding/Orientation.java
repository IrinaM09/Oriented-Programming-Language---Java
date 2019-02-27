
public class Orientation {
	/**
	 * 
	 * @param orientation
	 * @param index
	 * @return - returneaza orientarea pentru E S V N index : 0 : dreapta, 1 : sus,
	 *         2: stanga, 3 : jos
	 */
	static char rightOrientation(char orientation, int index) {

		if (index == 0) {
			if (orientation == 'A')
				return 'D';
			else if (orientation == 'B')
				return 'A';
			else if (orientation == 'C')
				return 'B';
			else if (orientation == 'D')
				return 'C';
		}
		if (index == 1) {
			if (orientation == 'A')
				return 'A';
			else if (orientation == 'B')
				return 'B';
			else if (orientation == 'C')
				return 'C';
			else if (orientation == 'D')
				return 'D';
		}
		if (index == 2) {
			if (orientation == 'A')
				return 'B';
			else if (orientation == 'B')
				return 'C';
			else if (orientation == 'C')
				return 'D';
			else if (orientation == 'D')
				return 'A';
		}
		if (index == 3) {
			if (orientation == 'A')
				return 'C';
			else if (orientation == 'C')
				return 'A';
			else if (orientation == 'B')
				return 'D';
			else if (orientation == 'D')
				return 'B';
		}
		return 0;
	}

	/**
	 * 
	 * @param orientation
	 * @param index
	 * @return - returneaza orientarea pentru V S E N index : 0 : stanga, 1 : sus,
	 *         2: dreapta, 3 : jos
	 */
	static char leftOrientation(char orientation, int index) {
		if (index == 0) {
			if (orientation == 'A')
				return 'B';
			else if (orientation == 'B')
				return 'C';
			else if (orientation == 'C')
				return 'D';
			else if (orientation == 'D')
				return 'A';
		}
		if (index == 2) {
			if (orientation == 'A')
				return 'D';
			else if (orientation == 'B')
				return 'A';
			else if (orientation == 'C')
				return 'B';
			else if (orientation == 'D')
				return 'C';
		}
		return 0;
	}

	/**
	 * 
	 * @param orientation
	 * @return - returneaza orientarea inversa
	 */
	static char reverseOrientation(char orientation) {
		if (orientation == 'A')
			return 'C';

		else if (orientation == 'B')
			return 'D';

		else if (orientation == 'C')
			return 'A';

		else if (orientation == 'D')
			return 'B';

		return 0;
	}

}
