
public interface Visitor {
	    int visit(AndNode operatorNode);

	    int visit(OrNode operatorNode);

	    int visit(OperandNode operandNode);
}
