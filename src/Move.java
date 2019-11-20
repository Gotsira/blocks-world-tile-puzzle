
public class Move {
	private boolean checkMove(int x, int y, int puzzleSize, int[] pastMove) {
		if (x < puzzleSize && y < puzzleSize && x != -1 && y != -1) {
			if (x != pastMove[0] && y != pastMove[1]) {
				return true;
			}
		}
		return false;
	}

	private Node moveAgent(Node node, int agentNewX, int agentNewY) {
		State currentState = node.getPuzzleState();
		State newState = null;
		int[] newAgentPosition = new int[] { agentNewX, agentNewY };
		if (currentState.getPositionA()[0] == agentNewX && currentState.getPositionA()[1] == agentNewY) {
			newState = new State(currentState.getPuzzleSize(), currentState.getPositionAgent(),
					currentState.getPositionB(), currentState.getPositionC(), newAgentPosition,
					currentState.getPositionAgent());
		} else if (currentState.getPositionB()[0] == agentNewX && currentState.getPositionB()[1] == agentNewY) {
			newState = new State(currentState.getPuzzleSize(), currentState.getPositionA(),
					currentState.getPositionAgent(), currentState.getPositionC(), newAgentPosition,
					currentState.getPositionAgent());
		} else if (currentState.getPositionC()[0] == agentNewX && currentState.getPositionC()[1] == agentNewY) {
			newState = new State(currentState.getPuzzleSize(), currentState.getPositionA(), currentState.getPositionB(),
					currentState.getPositionAgent(), newAgentPosition, currentState.getPositionAgent());
		} else {
			newState = new State(currentState.getPuzzleSize(), currentState.getPositionA(), currentState.getPositionB(),
					currentState.getPositionC(), newAgentPosition, currentState.getPositionAgent());
		}
		return new Node(newState, node.getLevel() + 1, node.getCost() + 1, node);
	}

	public Node moveUp(Node node) {
		State nodeState = node.getPuzzleState();
		int agentNewX = nodeState.getPositionAgent()[0];
		int agentNewY = nodeState.getPositionAgent()[1] - 1;
		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
			return moveAgent(node, agentNewX, agentNewY);
		}
		return null;
	}
	
	public Node moveDown(Node node) {
		State nodeState = node.getPuzzleState();
		int agentNewX = nodeState.getPositionAgent()[0];
		int agentNewY = nodeState.getPositionAgent()[1] + 1;
		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
			return moveAgent(node, agentNewX, agentNewY);
		}
		return null;
	}
	
	public Node moveLeft(Node node) {
		State nodeState = node.getPuzzleState();
		int agentNewX = nodeState.getPositionAgent()[0] - 1;
		int agentNewY = nodeState.getPositionAgent()[1];
		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
			return moveAgent(node, agentNewX, agentNewY);
		}
		return null;
	}
	
	public Node moveRight(Node node) {
		State nodeState = node.getPuzzleState();
		int agentNewX = nodeState.getPositionAgent()[0] + 1;
		int agentNewY = nodeState.getPositionAgent()[1];
		if (checkMove(agentNewX, agentNewY, nodeState.getPuzzleSize(), nodeState.getPastMove())) {
			return moveAgent(node, agentNewX, agentNewY);
		}
		return null;
	}
}
