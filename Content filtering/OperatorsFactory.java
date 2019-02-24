
public class OperatorsFactory {
	private static OperatorsFactory instance = null;

	OperatorsFactory() {
	}

	/**
	 * @return  only one instance;
	 */
	public static OperatorsFactory getInstance() {
		if (instance == null) {
			instance = new OperatorsFactory();
		}
		return instance;
	}
	/**
	 * 
	 * @param operator
	 * @param feed
	 * @return matched operator 
	 */
	public  Operator createOperator(String[] operator, String[] feed) {
		switch (operator[0]) {
		case "nil":
			return new NilExpression();
		case "ne":
			return new NeOperation();
		case "eq":
			return new EqOperation();
		case "gt":
			return new GtValue();
		case "ge":
			return new GeValue();
		case "lt":
			return new LtValue();
		case "le":
			return new LeValue();
		}
		throw new IllegalArgumentException("Invalid operator");
	}
}
