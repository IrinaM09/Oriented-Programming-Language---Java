
public class Calculator implements Visitor {

	@Override
	public int visit(AndNode operatorNode) {
		if (operatorNode.Left.accept(this) == 1 && operatorNode.Right.accept(this) == 1)
			return 1;
		return 0;
	}

	@Override
	public int visit(OrNode operatorNode) {
		if (operatorNode.Left.accept(this) == 0 && operatorNode.Right.accept(this) == 0)
			return 0;
		return 1;
	}

	@Override
	public int visit(OperandNode operandNode) {
		return operandNode.Value;
	}
}