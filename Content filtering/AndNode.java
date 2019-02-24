
public class AndNode extends Node implements Visitable {

    public AndNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}