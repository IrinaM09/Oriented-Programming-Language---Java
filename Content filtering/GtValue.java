
public class GtValue extends Operator{
	@Override
	boolean expression(String[] expression, String[] feed) {
		if (Float.parseFloat(expression[2]) < Float.parseFloat(feed[2]))
			return true;
		else
			return false;

	}

}
