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

	private WinnerFinderService winnerFinder = new WinnerFinderService();
	
	// method which will initialize the game state
	public Game startGame(int boardSize , List<Player> players, int currentPlayerIndex , List<WinningStrategy> winningStrategies) throws TicTacToeException{
		
     return new Game.GameBuilder().setBoard(boardSize).setPlayers(players)
    		    .setWinningStrategies(winningStrategies).setCurrentPlayersIndex(currentPlayerIndex)
    		    .setGameState(GameState.ON_GOING).build();
	}


	public void makeMove(int row, int col, Player player,Game game) throws TicTacToeException{
		game.makeMove(row,col,player);
	}

	// method to undo currentPlayers Move
	public void undoMove(Game game){
		// extract move from undo list
		Move move = game.getUndoList().remove(game.getUndoList().size()-1);

		//make changes to the board
		List<Cell> row = game.getBoard().getBoard().get(move.getCell().getRow());
		row.remove(move.getCell().getCol());

		// set player index back to previous player
		game.setCurrentPlayersIndex(game.getCurrentPlayersIndex()-1);
	}

	
	public GameState checkGameState(Game game){
		return game.getGameState();
	}
	
	public Player getCurrentPlayer(Game game){
		return game.getPlayers().get(game.getCurrentPlayersIndex());
	}


	public void showBoard(Game game) {
		game.getBoard().showBoard();
	}
}
