
public class Puzzle {
	public static void main(String[] args) {
		State start = new State(4, new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 3, 3 },
				null);
		State goal = new State(4, new int[] { 1, 1 }, new int[] { 2, 1 }, new int[] { 3, 1 }, null, null);
		Node node = new Node(start);
		Algorithms algo = new Algorithms(node, goal, 4);
		algo.IDDFS();
	}
}
