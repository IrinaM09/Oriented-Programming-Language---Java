
public class OrNode extends Node implements Visitable {

    public OrNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
