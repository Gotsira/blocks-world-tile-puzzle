
public class Puzzle {
	public static void main(String[] args) {
		State start = new State(4);
		Block[] goalBlocks = new Block[3];
		goalBlocks[0] = new Block(1,1,'A');
		goalBlocks[1] = new Block(1,2,'B');
		goalBlocks[2] = new Block(1,3,'C');
		State goal = new State(4, goalBlocks, null);
		Node node = new Node(start);
		Algorithms algo = new Algorithms(node, goal, 4);
		algo.AStar();
	}
}
