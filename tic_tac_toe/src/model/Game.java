package model;

import java.util.ArrayList;
import java.util.List;

import enumerations.GameState;
import interfaces.WinningStrategy;

public class Game {
	
	
	private Board board;
	private List<Player> players;
	private int currentPlayersIndex;
	private Player winner;
	private GameState gameState;
	private List<WinningStrategy> winningStrategies;
	private List<Move> undoList;
	
	private Game(Board board, List<Player> players, int currentPlayersIndex, GameState gameState,List<WinningStrategy> winningStrategies) {
		 this.board = board;
		 this.players = players;
		 this.currentPlayersIndex = currentPlayersIndex;
		 this.gameState = gameState;
		 this.winner = null;
		 this.winningStrategies = winningStrategies;
		 this.undoList = new ArrayList<>();
	}
	

	
	public List<Move> getUndoList() {
		return undoList;
	}




	public void setUndoList(List<Move> undoList) {
		this.undoList = undoList;
	}




	public Board getBoard() {
		return board;
	}






	public void setBoard(Board board) {
		this.board = board;
	}






	public List<Player> getPlayers() {
		return players;
	}






	public void setPlayers(List<Player> players) {
		this.players = players;
	}






	public int getCurrentPlayersIndex() {
		return currentPlayersIndex;
	}






	public void setCurrentPlayersIndex(int currentPlayersIndex) {
		this.currentPlayersIndex = currentPlayersIndex;
	}






	public Player getWinner() {
		return winner;
	}






	public void setWinner(Player winner) {
		this.winner = winner;
	}






	public GameState getGameState() {
		return gameState;
	}






	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}






	public List<WinningStrategy> getWinningStrategies() {
		return winningStrategies;
	}






	public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
		this.winningStrategies = winningStrategies;
	}






	public static class GameBuilder{
		private int boardSize;
		private List<Player> players;
		private int currentPlayersIndex;
		private GameState gameState;
		private List<WinningStrategy> winningStrategies;
		
		public GameBuilder setBoard(int boardSize) {
			this.boardSize = boardSize;
			return this;
		}
		public GameBuilder setPlayers(List<Player> players) {
			this.players = players;
			return this;
		}
		
		public GameBuilder setCurrentPlayersIndex(int currentPlayersIndex) {
			this.currentPlayersIndex = currentPlayersIndex;
			return this;
		}

		public GameBuilder setGameState(GameState gameState) {
			this.gameState = gameState;
			return this;
		}
		
		
		public GameBuilder setWinningStrategies(List<WinningStrategy> winningStrategies) {
			this.winningStrategies = winningStrategies;
			return this;
		}
		public Game build() throws TicTacToeException{
			
			boolean isDataValid = validateData();
			if(isDataValid) {
			Board board = new Board(new Move[boardSize][boardSize],boardSize);
			return new Game(board,this.players,this.currentPlayersIndex,this.gameState,this.winningStrategies);
			}
			else {
				throw new TicTacToeException("boardSize is Invalid");
			}
		}
		private boolean validateData() {
			
			if(boardSize < 0 || players.size() + 1 != boardSize){
				return false;
			}
			else if(this.currentPlayersIndex < 0 || this.currentPlayersIndex > boardSize -1) {
				return false;
			}
			
			return true;
		}
		
		
	}

}
