
public class EqOperation extends Operator {
	@Override
	boolean expression(String[] expression, String[] feed) {
		if (expression[1].equals("name")) {
			if (expression[2].equals(feed[1]))
				return true;
			else
				return false;
		} else if (expression[1].equals("value")) {
			if (Float.parseFloat(expression[2]) == Float.parseFloat(feed[2]))
				return true;
		}

		return false;
	}
}
