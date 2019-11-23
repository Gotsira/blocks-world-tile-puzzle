
public class Block {
	private int x, y;
	private String character;
	
	public Block(int x, int y, String character) {
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

	public String getCharacter() {
		return character;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (x != block.x) return false;
        if (y != block.y) return false;
        if (!character.equals(block.character)) return false;

        return true;
    }
	
	@Override
    public int hashCode() {
        int result = character.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
	
}
