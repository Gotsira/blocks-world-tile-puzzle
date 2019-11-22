import java.util.*;

public class Algorithms {
	private Node root;
	private State goal;
	private int nodeExpanded, puzzleSize;
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
				System.out.println(nodeExpanded);
//				printPuzzle(this.getNodeSequence(current));
				System.out.println("complete");
				return;
			}
			nodeExpanded++;
			children.add(current.moveUp());
			children.add(current.moveRight());
			children.add(current.moveDown());
			children.add(current.moveLeft());
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
//				printPuzzle(this.getNodeSequence(current));
				System.out.println(current.getLevel());
				System.out.println("complete");
				return;
			}
			nodeExpanded++;
			children.add(current.moveUp());
			children.add(current.moveRight());
			children.add(current.moveDown());
			children.add(current.moveLeft());
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
//				printPuzzle(this.getNodeSequence(current));
				System.out.println("complete");
				System.out.println(maxDept);
				return;
			}
			if (current.getLevel() < maxDept) {
				nodeExpanded++;
				children.add(current.moveUp());
				children.add(current.moveRight());
				children.add(current.moveDown());
				children.add(current.moveLeft());
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
		Queue<Node> queue = new PriorityQueue<>();
		
		queue.add(root);
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			if (checkGoal(current.getPuzzleState(), goal)) {
				System.out.println(nodeExpanded);
				System.out.println("complete");
				return;
			}
			nodeExpanded++;
			children.add(current.moveUp());
			children.add(current.moveRight());
			children.add(current.moveDown());
			children.add(current.moveLeft());
			for(Node child : children) {
				if(child != null) {
					child.calculateDistanceToGoal(goal);
					queue.add(child);
				}				
			}
			children.clear();
		}
	}

	private boolean checkGoal(State current, State goal) {
		for(int i = 1; i <= current.getPuzzleSize() - 1; i++) {
			if(current.getAllPositions()[i].getX() != goal.getAllPositions()[i-1].getX() || current.getAllPositions()[i].getY() != goal.getAllPositions()[i-1].getY()) {
				return false;
			}
		}
		return true;
	}

	private ArrayList<Node> getNodeSequence(Node node) {
		ArrayList<Node> nodeSequence = new ArrayList<Node>();
		while (node != null) {
			nodeSequence.add(0, node);
			node = node.getParent();
		}
		return nodeSequence;
	}

//	private void printPuzzle(ArrayList<Node> puzzleSolution) {
//		for (Node node : puzzleSolution) {
//			State state = node.getPuzzleState();
//			resetPuzzle();
//			puzzle[state.getPositionA()[0]][state.getPositionA()[1]] = "A";
//			puzzle[state.getPositionB()[0]][state.getPositionB()[1]] = "B";
//			puzzle[state.getPositionC()[0]][state.getPositionC()[1]] = "C";
//			puzzle[state.getPositionAgent()[0]][state.getPositionAgent()[1]] = "X";
//			for (String[] x : puzzle) {
//				for (int i = 0; i < puzzleSize; i++) {
//					System.out.print(x[i] + "  ");
//				}
//				System.out.println();
//			}
//			System.out.println("============");
//		}
//	}

	private void resetPuzzle() {
		for (int i = 0; i < puzzleSize; i++) {
			for (int j = 0; j < puzzleSize; j++) {
				puzzle[i][j] = "-";
			}
		}
	}
}
