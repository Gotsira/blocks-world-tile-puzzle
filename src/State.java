
public class State {
	private int puzzleSize = 4;
	private Block agent, a, b, c;
	private Block[][] blocks;
	private int numberOfBlocks = 3;

	public State(int puzzleSize) {
		this.puzzleSize = puzzleSize;
		createInitialState(0, puzzleSize - 1, 1, puzzleSize - 1, 2, puzzleSize - 1, puzzleSize - 1, puzzleSize - 1);
//		System.out.println("a: " + a.getX() + ", " + a.getY());
//		System.out.println("b: " + b.getX() + ", " + b.getY());
//		System.out.println("c: " + c.getX() + ", " + c.getY());
//		System.out.println("agent: " + agent.getX() + ", " + agent.getY());
//		System.out.println("======================");
	}

	public State(int puzzleSize, int aX, int aY, int bX, int bY, int cX, int cY, int agentX, int agentY) {
		this.puzzleSize = puzzleSize;
		createInitialState(aX, aY, bX, bY, cX, cY, agentX, agentY);
	}

	private void createInitialState(int aX, int aY, int bX, int bY, int cX, int cY, int agentX, int agentY) {
		blocks = new Block[puzzleSize][puzzleSize];
		for (int i = 0; i < puzzleSize; i++) {
			for (int j = 0; j < puzzleSize; j++) {
				setPosition(i, j, new Block(i, j, "-"));
			}
		}
		agent = new Block(agentX, agentY, "*");
		a = new Block(aX, aY, "A");
		b = new Block(bX, bY, "B");
		c = new Block(cX, cY, "C");

		setPosition(agentX, agentY, agent);
		setPosition(aX, aY, a);
		setPosition(bX, bY, b);
		setPosition(cX, cY, c);
	}

	public void switchBlock(int currentX, int currentY, int newX, int newY) {
		Block old = blocks[newX][newY];
		blocks[currentX][currentY] = old;
		old.setX(agent.getX());
		old.setY(agent.getY());
		blocks[newX][newY] = agent;
		agent.setX(newX);
		agent.setY(newY);

//		System.out.println("a: " + a.getX() + ", " + a.getY());
//		System.out.println("b: " + b.getX() + ", " + b.getY());
//		System.out.println("c: " + c.getX() + ", " + c.getY());
//		System.out.println("agent: " + newX + ", " + newY);
//		System.out.println("======================");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		State state = (State) o;
		if (state.getPuzzleSize() != this.puzzleSize) {
			return false;
		}
		if (a != null ? !a.equals(state.a) : state.a != null)
			return false;
		if (agent != null ? !agent.equals(state.agent) : state.agent != null)
			return false;
		if (b != null ? !b.equals(state.b) : state.b != null)
			return false;
		if (c != null ? !c.equals(state.c) : state.c != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = puzzleSize;
		result = 31 * result + (agent != null ? agent.hashCode() : 0);
		result = 31 * result + (a != null ? a.hashCode() : 0);
		result = 31 * result + (b != null ? b.hashCode() : 0);
		result = 31 * result + (c != null ? c.hashCode() : 0);
		return result;
	}

	public int getBlockPositions() {
		return numberOfBlocks + 1;
	}

	public int getPuzzleSize() {
		return puzzleSize;
	}

	public Block getAgent() {
		return agent;
	}

	public Block getA() {
		return a;
	}

	public Block getB() {
		return b;
	}

	public Block getC() {
		return c;
	}

	public int getNumberOfBlocks() {
		return numberOfBlocks;
	}

	public void setPosition(int x, int y, Block block) {
		blocks[x][y] = block;
	}

	public Block[] copyBlockPositions(Block[] blocks) {
		Block[] tmp = new Block[numberOfBlocks + 1];
		for (int i = 0; i <= numberOfBlocks; i++) {
			tmp[i] = new Block(blocks[i]);
		}
		return tmp;
	}
}
