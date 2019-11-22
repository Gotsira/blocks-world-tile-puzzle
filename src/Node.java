
public class Node implements Comparable<Node> {
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

	private boolean checkMove(int x, int y, int puzzleSize, Block pastMove) {
		if (x < puzzleSize && y < puzzleSize && x != -1 && y != -1) {
			if (pastMove != null) {
				if (x == pastMove.getX() && y == pastMove.getY()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private Node moveAgent(int agentNewX, int agentNewY) {
		State currentState = this.getPuzzleState();
		Block[] currentPositions = currentState.copyBlockPositions(currentState.getAllPositions());
		State newState = null;
		int[] newAgentPosition = new int[] { agentNewY, agentNewX };
		for (int i = 1; i < currentState.getPuzzleSize(); i++) {
			if (currentPositions[i].getX() == agentNewX && currentPositions[i].getY() == agentNewY) {
				currentPositions[i].setX(currentPositions[0].getX());
				currentPositions[i].setY(currentPositions[0].getY());
				Block past = new Block(currentPositions[0].getX(), currentPositions[0].getY(), '*');
				currentPositions[0].setX(agentNewX);
				currentPositions[0].setY(agentNewY);
				newState = new State(currentState.getPuzzleSize(), currentPositions, past);
			}
		}
		if (newState == null) {
			Block past = new Block(currentPositions[0].getX(), currentPositions[0].getY(), '*');
			currentPositions[0].setX(agentNewX);
			currentPositions[0].setY(agentNewY);
			newState = new State(currentState.getPuzzleSize(), currentPositions, past);
		}
		return new Node(newState, this.getLevel() + 1, this.getCost() + 1, this);
	}

	public Node moveUp() {
		State nodeState = this.getPuzzleState();
		int agentNewX = nodeState.getAllPositions()[0].getX();
		int agentNewY = nodeState.getAllPositions()[0].getY() - 1;
		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
			return moveAgent(agentNewX, agentNewY);
		}
		return null;
	}

	public Node moveDown() {
		State nodeState = this.getPuzzleState();
		int agentNewX = nodeState.getAllPositions()[0].getX();
		int agentNewY = nodeState.getAllPositions()[0].getY() + 1;
		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
			return moveAgent(agentNewX, agentNewY);
		}
		return null;
	}

	public Node moveLeft() {
		State nodeState = this.getPuzzleState();
		int agentNewX = nodeState.getAllPositions()[0].getX() - 1;
		int agentNewY = nodeState.getAllPositions()[0].getY();
		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
			return moveAgent(agentNewX, agentNewY);
		}
		return null;
	}

	public Node moveRight() {
		State nodeState = this.getPuzzleState();
		int agentNewX = nodeState.getAllPositions()[0].getX() + 1;
		int agentNewY = nodeState.getAllPositions()[0].getY();
		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
			return moveAgent(agentNewX, agentNewY);
		}
		return null;
	}

	public void calculateDistanceToGoal(State goal) {
		Block[] positions = puzzleState.getAllPositions();
		Block[] goalPositions = goal.getAllPositions();
		for (int i = 1; i < puzzleState.getPuzzleSize(); i++) {
			cost += Math.abs(positions[i].getX() - goalPositions[i - 1].getX())
					+ Math.abs(positions[i].getY() - goalPositions[i - 1].getY());
		}
	}

	@Override
	public int compareTo(Node nodeInQueue) {
		int cost = this.cost - nodeInQueue.getCost();
		if (cost == 0) {
			if (this.getLevel() > nodeInQueue.getLevel()) {
				return -1;
			} else if (this.getLevel() == nodeInQueue.getLevel()) {
				return 0;
			} else {
				return 1;
			}
		}
		return cost;
	}

}
