import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParseString {
	/**
	 * 
	 * @param string
	 *            - input ul observatorului
	 * @param feed
	 *            - feed ul
	 * @return outputStack - coada rezultata din Shunting - Yard Algorithm
	 */
	public static Stack<String> parseString(String[] string, String[] feed) {
		Stack<String> operatorStack = new Stack<String>();
		Stack<String> outputStack = new Stack<String>();
		Stack<String> reverseStack = new Stack<String>();
		List<String> currentString = new ArrayList<String>();

		int i = 0;
		for (int index = 0; index < string.length; index++) {
			/* '(' sunt puse in operatorStack */
			if (string[index].equals("("))
				operatorStack.push("(");
			/*
			 * pentru ')' , se scoate din stiva si se pune in alta stiva pana la '('
			 */
			else if (string[index].equals(")")) {
				while (!operatorStack.peek().equals("("))
					outputStack.push(operatorStack.pop());

				operatorStack.pop();

				/* daca s-a gasit o expresie, se verifica rezultatul */
				if (currentString.isEmpty() == false) {
					String[] newArray = currentString.toArray(new String[currentString.size()]);
					if (Observer.notify(newArray, feed) == true)
						outputStack.push("1");
					else
						outputStack.push("0");
					i = 0;
					currentString.removeAll(currentString);
					continue;
				}
				/* '||' si '&&' se pun in stiva */
			} else if (string[index].equals("||") || string[index].equals("&&"))
				operatorStack.push(string[index]);

			/* adauga in lista cuvantul, pentru a putea forma expresia */
			else {
				currentString.add(i, string[index]);
				i++;
			}
		}
		/* daca a mai ramas in stiva ceva, se muta in cealalta stiva */
		if (operatorStack.isEmpty() == false) {
			while (operatorStack.isEmpty() == false)
				outputStack.push(operatorStack.pop());
		}
		/* se inverseaza stiva, pentru a returna o coada */
		while (outputStack.isEmpty() == false) {
			reverseStack.push(outputStack.pop());
		}
		return reverseStack;
	}
}
