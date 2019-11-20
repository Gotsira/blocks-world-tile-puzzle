
public class Node {
	private int level, cost;
	private Node parent = null;
	private State puzzleState;

	/**
	 * Use to instantiate a parent node where the parent will be null and the cost
	 * and level is 0.
	 * 
	 * @param puzzleState state of the puzzle
	 */
	public Node(State puzzleState) {
		this.level = 0;
		this.cost = 0;
		this.puzzleState = puzzleState;
	}

	/**
	 * Use to instantiate a child node which contains a parent, level, cost and the
	 * state of the puzzle.
	 * 
	 * @param puzzleState
	 * @param level
	 * @param cost
	 * @param parent
	 */
	public Node(State puzzleState, int level, int cost, Node parent) {
		this.puzzleState = puzzleState;
		this.level = level;
		this.cost = cost;
		this.parent = parent;
	}

	public int getLevel() {
		return level;
	}

	public int getCost() {
		return cost;
	}

	public State getPuzzleState() {
		return puzzleState;
	}

	public Node getParent() {
		return parent;
	}

}
