
public class Block {
	private int x, y;
	private char character;
	
	public Block(int x, int y, char character) {
		this.x = x;
		this.y = y;
		this.character = character;
	}
	
	public Block(Block copyBlock) {
		this.x = copyBlock.getX();
		this.y = copyBlock.getY();
		this.character = copyBlock.getCharacter();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getCharacter() {
		return character;
	}
	
}
