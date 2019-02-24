
public class OperandNode extends Node implements Visitable {

    public int Value;

    public OperandNode(int value) {
        super(null, null);
        Value = value;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}