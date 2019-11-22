
public class State {
	private int puzzleSize = 4;
	private Block pastMove = null;
	private Block[] positions = null;
	
	public State(int puzzleSize) {
		this.puzzleSize = puzzleSize;
		positions = new Block[puzzleSize];
		positions[0] = new Block(puzzleSize - 1, puzzleSize - 1, '*');
		for (int i = 0; i < puzzleSize - 1; i++) {
			char avatar = (char) ('A' + i);
			positions[i+1] = new Block(i, puzzleSize - 1, avatar);
		}
	}
	
	public State(int puzzleSize, Block[] positions, Block pastMove) {
		this.puzzleSize = puzzleSize;
		this.positions = positions;
		this.pastMove = pastMove;
	}

	public int getPuzzleSize() {
		return puzzleSize;
	}

	public Block getPastMove() {
		return pastMove;
	}
	
	public Block[] getAllPositions() {
		return positions;
	}
	
	public Block[] copyBlockPositions(Block[] blocks) {
		Block[] tmp = new Block[this.puzzleSize];
		for(int i = 0; i < puzzleSize; i++) {
			tmp[i] = new Block(blocks[i]);
		}
		return tmp;
	}
}
