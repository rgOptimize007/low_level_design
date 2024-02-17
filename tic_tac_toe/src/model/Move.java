package model;

public class Move {
	
	private Player player;
	private int row;
	private int col;

	public Move(int row, int col, Player player) {
		this.row = row;
		this.col = col;
		this.player = player;
	}
	
	

	public int getRow() {
		return row;
	}



	public void setRow(int row) {
		this.row = row;
	}



	public int getCol() {
		return col;
	}



	public void setCol(int col) {
		this.col = col;
	}



	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}



	@Override
	public String toString() {
		return "Move [player=" + player + ", row=" + row + ", col=" + col + "]";
	}
	
	
	
	

}
