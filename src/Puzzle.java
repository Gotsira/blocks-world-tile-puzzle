import java.util.Scanner;

public class Puzzle {
	public static void main(String[] args) {
		System.out.print("Select the board size (3-8): ");
		Scanner input = new Scanner(System.in);
		int puzzleSize = input.nextInt();
		
		State start = new State(puzzleSize);
		
		State goal = new State(puzzleSize, 1, 1, 1, 2, 1, 3, 0, 0);
		Node node = new Node(start);
		Algorithms algo = new Algorithms(node, goal, puzzleSize);
		
		System.out.println("What search algorithm do you want to use?");
		System.out.print("Breadth-First-Search (B), Dept-First-Search (D), Iterative-Deepining-Dept-First-Search (I), A* Heuristic Search (A): ");
		input = new Scanner(System.in);
		String searchType = input.next();
		
		if(searchType.equals("B")) {
			algo.BFS();
		} else if(searchType.equals("D")) {
			algo.DFS();
		} else if(searchType.equals("I")) {
			algo.IDDFS();
		} else if(searchType.equals("A")) {
			algo.AStar();
		}
	}
}
