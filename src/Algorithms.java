import java.util.*;

public class Algorithms {
	private Node root;
	private State goal;
	private int nodeExpanded = 0;
	private Move puzzleMove = new Move();

	public Algorithms(Node root, State goal) {
		this.root = root;
		this.goal = goal;
	}

	public void BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
	}
}
