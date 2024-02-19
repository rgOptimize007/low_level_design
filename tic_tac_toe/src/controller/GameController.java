package controller;

import java.util.List;

import enumerations.CellState;
import enumerations.GameState;
import interfaces.WinningStrategy;
import model.Cell;
import model.Game;
import model.Move;
import model.Player;
import exceptions.TicTacToeException;

public class GameController {
	
	private Game game;
	private WinnerFinderService winnerFinder = new WinnerFinderService();
	
	// method which will initialize the game state
	public void startGame(int boardSize , List<Player> players, int currentPlayerIndex , List<WinningStrategy> winningStrategies) throws TicTacToeException{
		
     this.game = new Game.GameBuilder().setBoard(boardSize).setPlayers(players)
    		    .setWinningStrategies(winningStrategies).setCurrentPlayersIndex(currentPlayerIndex)
    		    .setGameState(GameState.ON_GOING).build();

	}


	public void makeMove(int row, int col, Player player) throws TicTacToeException{
		this.game.makeMove(row,col,player);
	}

	// method to undo currentPlayers Move
	public void undoMove(){
		// extract move from undo list
		Move move = this.game.getUndoList().remove(this.game.getUndoList().size()-1);

		//make changes to the board
		List<Cell> row = this.game.getBoard().getBoard().get(move.getCell().getRow());
		row.remove(move.getCell().getCol());

		// set player index back to previous player
		this.game.setCurrentPlayersIndex(this.game.getCurrentPlayersIndex()-1);
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

	public void showBoard() {
		this.game.getBoard().showBoard();
	}
}
