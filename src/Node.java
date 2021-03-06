import java.util.ArrayList;

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

//	private Node moveAgent(int agentNewX, int agentNewY) {
//		State currentState = this.getPuzzleState();
//		Block[] currentPositions = currentState.copyBlockPositions(currentState.getAllPositions());
//		State newState = null;
//		for (int i = 1; i < currentState.getBlockPositions(); i++) {
//			if (currentPositions[i].getX() == agentNewX && currentPositions[i].getY() == agentNewY) {
//				currentPositions[i].setX(currentPositions[0].getX());
//				currentPositions[i].setY(currentPositions[0].getY());
//				Block past = new Block(currentPositions[0].getX(), currentPositions[0].getY(), '*');
//				currentPositions[0].setX(agentNewX);
//				currentPositions[0].setY(agentNewY);
//				newState = new State(currentState.getPuzzleSize(), currentPositions, past,
//						currentState.getNumberOfBlocks());
//			}
//		}
//		if (newState == null) {
//			Block past = new Block(currentPositions[0].getX(), currentPositions[0].getY(), '*');
//			currentPositions[0].setX(agentNewX);
//			currentPositions[0].setY(agentNewY);
//			newState = new State(currentState.getPuzzleSize(), currentPositions, past,
//					currentState.getNumberOfBlocks());
//		}
//		return new Node(newState, this.getLevel() + 1, this.getCost() + 1, this);
//	}

	public ArrayList<Node> moveNode() {
		ArrayList<Node> children = new ArrayList<>();
		Block agent = puzzleState.getAgent();
		Block a = puzzleState.getA();
		Block b = puzzleState.getB();
		Block c = puzzleState.getC();

		if (agent.getY() > 0) {
			State state = new State(puzzleState.getPuzzleSize(), a.getX(), a.getY(), b.getX(), b.getY(), c.getX(),
					c.getY(), agent.getX(), agent.getY());
			state.switchBlock(agent.getX(), agent.getY(), agent.getX(), agent.getY() - 1);
			Node node = new Node(state, this.level + 1, this.cost + 1, this);
			children.add(node);
		}
		if (agent.getY() < puzzleState.getPuzzleSize() - 1) {
			State state = new State(puzzleState.getPuzzleSize(), a.getX(), a.getY(), b.getX(), b.getY(), c.getX(),
					c.getY(), agent.getX(), agent.getY());
			state.switchBlock(agent.getX(), agent.getY(), agent.getX(), agent.getY() + 1);
			Node node = new Node(state, this.level + 1, this.cost + 1, this);
			children.add(node);
		}
		if (agent.getX() > 0) {
			State state = new State(puzzleState.getPuzzleSize(), a.getX(), a.getY(), b.getX(), b.getY(), c.getX(),
					c.getY(), agent.getX(), agent.getY());
			state.switchBlock(agent.getX(), agent.getY(), agent.getX() - 1, agent.getY());
			Node node = new Node(state, this.level + 1, this.cost + 1, this);
			children.add(node);
		}
		if (agent.getX() < puzzleState.getPuzzleSize() - 1) {
			State state = new State(puzzleState.getPuzzleSize(), a.getX(), a.getY(), b.getX(), b.getY(), c.getX(),
					c.getY(), agent.getX(), agent.getY());
			state.switchBlock(agent.getX(), agent.getY(), agent.getX() + 1, agent.getY());
			Node node = new Node(state, this.level + 1, this.cost + 1, this);
			children.add(node);
		}
		return children;
	}

//	public Node moveUp() {
//		State nodeState = this.getPuzzleState();
//		int agentNewX = nodeState.getAllPositions()[0].getX();
//		int agentNewY = nodeState.getAllPositions()[0].getY() - 1;
//		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
//			return moveAgent(agentNewX, agentNewY);
//		}
//		return null;
//	}
//
//	public Node moveDown() {
//		State nodeState = this.getPuzzleState();
//		int agentNewX = nodeState.getAllPositions()[0].getX();
//		int agentNewY = nodeState.getAllPositions()[0].getY() + 1;
//		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
//			return moveAgent(agentNewX, agentNewY);
//		}
//		return null;
//	}
//
//	public Node moveLeft() {
//		State nodeState = this.getPuzzleState();
//		int agentNewX = nodeState.getAllPositions()[0].getX() - 1;
//		int agentNewY = nodeState.getAllPositions()[0].getY();
//		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
//			return moveAgent(agentNewX, agentNewY);
//		}
//		return null;
//	}
//
//	public Node moveRight() {
//		State nodeState = this.getPuzzleState();
//		int agentNewX = nodeState.getAllPositions()[0].getX() + 1;
//		int agentNewY = nodeState.getAllPositions()[0].getY();
//		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
//			return moveAgent(agentNewX, agentNewY);
//		}
//		return null;
//	}

	public boolean isGoal(State goal) {
		if (puzzleState.getA().getX() == goal.getA().getX() && puzzleState.getA().getY() == goal.getA().getY()
				&& puzzleState.getB().getX() == goal.getB().getX() && puzzleState.getB().getY() == goal.getB().getY()
				&& puzzleState.getC().getX() == goal.getC().getX() && puzzleState.getC().getY() == goal.getC().getY()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object o) { 
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Node node = (Node) o;

		if (puzzleState != null ? !puzzleState.equals(node.puzzleState) : node.puzzleState != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() { 
		int result = (puzzleState != null ? puzzleState.hashCode() : 0);
		return result;
	}

	public void calculateDistanceToGoal(State goal) {
		int distanceFromA = Math.abs(puzzleState.getA().getX() - goal.getA().getX())
				+ Math.abs(puzzleState.getA().getY() + goal.getA().getY());
		int distanceFromB = Math.abs(puzzleState.getB().getX() - goal.getB().getX())
				+ Math.abs(puzzleState.getB().getY() + goal.getB().getY());
		int distanceFromC = Math.abs(puzzleState.getC().getX() - goal.getC().getX())
				+ Math.abs(puzzleState.getC().getY() + goal.getC().getY());
		cost += distanceFromA + distanceFromB + distanceFromC;
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
