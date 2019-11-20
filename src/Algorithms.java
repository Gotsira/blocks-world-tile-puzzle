import java.util.*;

public class Algorithms {
	private Node root;
	private State goal;
	private int nodeExpanded, puzzleSize;
	private Move puzzleMove = new Move();
	private ArrayList<Node> children = new ArrayList<Node>();
	private String[][] puzzle;

	public Algorithms(Node root, State goal, int puzzleSize) {
		this.root = root;
		this.goal = goal;
		this.puzzleSize = puzzleSize;
		this.puzzle = new String[this.puzzleSize][this.puzzleSize];
	}

	public void BFS() {
		nodeExpanded = 0;
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);
		System.out.println("BFS");

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (checkGoal(current.getPuzzleState(), goal)) {
				printPuzzle(this.getNodeSequence(current));
				System.out.println("complete");
				return;
			}
			nodeExpanded++;
			children.add(puzzleMove.moveUp(current));
			children.add(puzzleMove.moveRight(current));
			children.add(puzzleMove.moveDown(current));
			children.add(puzzleMove.moveLeft(current));
			for (Node child : children) {
				if (child != null) {
					queue.add(child);
				}
			}
			children.clear();
		}
	}

	public void DFS() {
		nodeExpanded = 0;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		System.out.println("DFS");

		while (!stack.isEmpty()) {
			Node current = stack.pop();
			if (checkGoal(current.getPuzzleState(), goal)) {
				printPuzzle(this.getNodeSequence(current));
				System.out.println(current.getLevel());
				System.out.println("complete");
				return;
			}
			nodeExpanded++;
			children.add(puzzleMove.moveUp(current));
			children.add(puzzleMove.moveRight(current));
			children.add(puzzleMove.moveDown(current));
			children.add(puzzleMove.moveLeft(current));
			Collections.shuffle(children);
			for (Node child : children) {
				if (child != null) {
					stack.push(child);
				}
			}
			children.clear();
		}
	}

	public void IDDFS() {
		nodeExpanded = 0;
		int maxDept = 0;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		System.out.println("IDDFS");

		while (!stack.isEmpty()) {
			Node current = stack.pop();
			if (checkGoal(current.getPuzzleState(), goal)) {
				printPuzzle(this.getNodeSequence(current));
				System.out.println("complete");
				System.out.println(maxDept);
				return;
			}
			if (current.getLevel() < maxDept) {
				nodeExpanded++;
				children.add(puzzleMove.moveUp(current));
				children.add(puzzleMove.moveRight(current));
				children.add(puzzleMove.moveDown(current));
				children.add(puzzleMove.moveLeft(current));
				Collections.shuffle(children);
				for (Node child : children) {
					if (child != null) {
						stack.push(child);
					}
				}
				children.clear();
			}
			if (stack.size() == 0) {
				stack.push(root);
				maxDept++;
			}
		}
	}
	
	public void AStar() {
		
	}

	private boolean checkGoal(State current, State goal) {
		if (Arrays.equals(current.getPositionA(), goal.getPositionA())
				&& Arrays.equals(current.getPositionB(), goal.getPositionB())
				&& Arrays.equals(current.getPositionC(), goal.getPositionC())) {
			return true;
		}
		return false;
	}

	private ArrayList<Node> getNodeSequence(Node node) {
		ArrayList<Node> nodeSequence = new ArrayList<Node>();
		while (node != null) {
			nodeSequence.add(0, node);
			node = node.getParent();
		}
		return nodeSequence;
	}

	private void printPuzzle(ArrayList<Node> puzzleSolution) {
		for (Node node : puzzleSolution) {
			State state = node.getPuzzleState();
			resetPuzzle();
			puzzle[state.getPositionA()[0]][state.getPositionA()[1]] = "A";
			puzzle[state.getPositionB()[0]][state.getPositionB()[1]] = "B";
			puzzle[state.getPositionC()[0]][state.getPositionC()[1]] = "C";
			puzzle[state.getPositionAgent()[0]][state.getPositionAgent()[1]] = "X";
			for (String[] x : puzzle) {
				for (int i = 0; i < puzzleSize; i++) {
					System.out.print(x[i] + "  ");
				}
				System.out.println();
			}
			System.out.println("============");
		}
	}

	private void resetPuzzle() {
		for (int i = 0; i < puzzleSize; i++) {
			for (int j = 0; j < puzzleSize; j++) {
				puzzle[i][j] = "-";
			}
		}
	}
}
