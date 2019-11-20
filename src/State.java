
public class State {
	private int[] positionA, positionB, positionC, positionAgent;
	private int puzzleSize = 4;
	private int[] pastMove = null;

	public State(int puzzleSize, int[] positionA, int[] positionB, int[] positionC, int[] positionAgent,
			int[] pastMove) {
		this.positionA = positionA;
		this.positionB = positionB;
		this.positionC = positionC;
		this.puzzleSize = puzzleSize;
		this.pastMove = pastMove;
	}

	public int[] getPositionA() {
		return positionA;
	}

	public int[] getPositionB() {
		return positionB;
	}

	public int[] getPositionC() {
		return positionC;
	}

	public int[] getPositionAgent() {
		return positionAgent;
	}

	public int getPuzzleSize() {
		return puzzleSize;
	}

	public int[] getPastMove() {
		return pastMove;
	}
}
