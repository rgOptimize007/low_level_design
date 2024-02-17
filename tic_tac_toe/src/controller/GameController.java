package controller;

import java.util.List;

import enumerations.GameState;
import interfaces.WinningStrategy;
import model.Game;
import model.Move;
import model.Player;
import model.TicTacToeException;

public class GameController {
	
	private Game game;
	private WinnerFinderService winnerFinder = new WinnerFinderService();
	
	// method which will initialize the game state
	public void startGame(int boardSize , List<Player> players, int currentPlayerIndex , List<WinningStrategy> winningStrategies) throws TicTacToeException{
		
     this.game = new Game.GameBuilder().setBoard(boardSize).setPlayers(players)
    		    .setWinningStrategies(winningStrategies).setCurrentPlayersIndex(currentPlayerIndex)
    		    .setGameState(GameState.ON_GOING).build();

	}
     
	// method which will show case the board on CLI
	public void showBoard(){
		Move[][] board = this.game.getBoard().getBoard();
		int boardSize = this.game.getBoard().getBoardSize();
		for(int i=0;i<boardSize;i++){
			for(int j=0;j<boardSize;j++){
				if(board[i][j] != null){
				System.out.print("  " + board[i][j].getPlayer().getPlayerSymbol().getSymbol());
				}
				else{
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		
	}
	// method to make currentPlayers Move
	public void makeMove(int row, int col, Player player) throws TicTacToeException{
		validateMove(row,col);
		Move move = new Move(row,col,player);
		addMoveToTheBoard(move);
		addMoveToUndoList(move);
		if(this.checkWinner(move)){
			this.game.setGameState(GameState.FINISHED);
			return;
		}
		this.game.setCurrentPlayersIndex((this.game.getCurrentPlayersIndex()+1)%this.game.getPlayers().size());
	}
	private void validateMove(int row, int col) throws TicTacToeException {
		
		if(row >= this.game.getBoard().getBoardSize() || col >= this.game.getBoard().getBoardSize()
		   || row < 0 || col < 0){
			throw new TicTacToeException("Row and Column entered are not valid");
		}
		
	}

	private void addMoveToUndoList(Move move) {
		this.game.getUndoList().add(move);
	}

	private void addMoveToTheBoard(Move move) throws TicTacToeException {
		Move[][] board = this.game.getBoard().getBoard();
		//System.out.println("Row : " + move.getRow() + " Col : " + move.getCol());
		if(board[move.getRow()][move.getCol()] == null){
		board[move.getRow()][move.getCol()] = move;
		}
		else{
			System.out.println(board[move.getRow()][move.getCol()].toString());
			throw new TicTacToeException("Position already used : ROW = " + move.getRow() + " COL : " + move.getCol());
		}
		
	}

	// method to undo currentPlayers Move
	public void undoMove(){
		Move move = this.game.getUndoList().remove(this.game.getUndoList().size()-1);
		Move[][] board = this.game.getBoard().getBoard();
		board[move.getRow()][move.getCol()] = null;
		this.game.setCurrentPlayersIndex(this.game.getCurrentPlayersIndex()-1);
	}
	// method to check winner
	public boolean checkWinner(Move move){
		
		Player winner = null;
		winner = winnerFinder.findWinner(move,this.game.getBoard().getBoard(),this.game.getWinningStrategies());
		
		if(winner != null){
			this.game.setWinner(winner);
			return true;
		}
		return false;
	}
	
	public GameState checkGameState(){
		return this.game.getGameState();
	}
	
	public Player getCurrentPlayer(){
		return game.getPlayers().get(game.getCurrentPlayersIndex());
	}


	public Game getGame() {
		// TODO Auto-generated method stub
		return this.game;
	}

}
