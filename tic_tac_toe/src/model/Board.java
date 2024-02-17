package model;

public class Board {
	
	private Move[][] board;
	private int boardSize;
	
	public Board(Move[][] board,int boardSize) {
		this.board = new Move[boardSize][boardSize];
		this.boardSize = boardSize;
	}
	
	

	public int getBoardSize() {
		return boardSize;
	}



	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}



	public Move[][] getBoard() {
		return board;
	}

	public void setBoard(Move[][] board) {
		this.board = board;
	}
	
	
	
	
	
	
}
