
public class Node implements Visitable {

    public Node Left;
    public Node Right;

    public Node(Node left, Node right) {
        Left = left;
        Right = right;
    }

    @Override
    public int accept(Visitor visitor) {
       return -1;
    	
    }
}
