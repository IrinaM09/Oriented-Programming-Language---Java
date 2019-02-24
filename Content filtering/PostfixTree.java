import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostfixTree {

	public Node root;
	List<String> array = new ArrayList<String>();

	/**
	 * 
	 * @param outputQueue
	 *            pune elementele din coada intr-un vector
	 */
	PostfixTree(Stack<String> outputQueue) {
		while (outputQueue.size() != 0)
			array.add(outputQueue.pop());
	}

	void createTree() {
		/* stiva de noduri */
		final Stack<Node> nodes = new Stack<Node>();
		int i = 0;
		for (i = 0; i < array.size(); i++) {

			/* daca e operator, se pun in stiva rightNode si leftNode */
			/* folosindu-se si de VisitorPattern la creare */
			if (array.get(i).equals("||")) {
				Node rightNode = nodes.pop();
				Node leftNode = nodes.pop();
				nodes.push(new OrNode(leftNode, rightNode));
			}

			else if (array.get(i).equals("&&")) {
				Node rightNode = nodes.pop();
				Node leftNode = nodes.pop();
				nodes.push(new AndNode(leftNode, rightNode));
			}
			/* daca e operand, se adauga in stiva de noduri */
			/* folosindu-se si de VisitorPattern la creare */
			else
				nodes.push(new OperandNode(Integer.parseInt(array.get(i))));
		}
		root = nodes.pop();
	}
}
