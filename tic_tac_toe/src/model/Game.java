package model;

import enumerations.CellState;
import enumerations.GameState;
import exceptions.TicTacToeException;
import interfaces.WinningStrategy;
import services.WinnerManagementService;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private Board board;
    private List<Player> players;
    private int currentPlayersIndex;
    private Player winner;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;
    private List<Move> undoList;

    private Game(Board board, List<Player> players, int currentPlayersIndex, GameState gameState, List<WinningStrategy> winningStrategies) {
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


    private void addMoveToUndoList(Move move) {
        undoList.add(move);
    }

    public Move performAndGetMove(int row, int col, Player player) throws TicTacToeException {

		// validate row and column data
		if (validateCell(row,col)) {

			//update the cell
            Cell cell = board.getBoard().get(row).get(col);
			cell.setPlayer(player);
			cell.setCellState(CellState.FILLED);

			//create a move
			Move move = new Move(cell, player);

			// add cell to undo list
			addMoveToUndoList(move);

			return move;

		} else {
			System.out.println("Invalid move ... ");
		}

		return null;

	}

    private boolean validateCell(int row , int col) {

        if (row < 0 || row > this.board.getBoard().size() || col < 0 || col > this.board.getBoard().size()) {
            return false;
        }
        else {
            Cell cell = board.getBoard().get(row).get(col);
            if(!cell.getCellState().toString().equals(CellState.EMPTY.toString())){
                return false;
            }
        }
        return true;
    }

    public void updatePlayerIndex(){
		// move to next player
		currentPlayersIndex = (currentPlayersIndex + 1) % players.size();
	}

	public void updateGameStatus(Move move){

		// check if game has finished or drawn
		if (this.checkWinner(move)) {
			setGameState(GameState.FINISHED);
		} else if (undoList.size() == Math.pow(board.getBoard().size(), 2)) {
			gameState = GameState.DRAW;
		}
	}

    public boolean checkWinner(Move move) {

        Player winner = null;
        WinnerManagementService winnerFinder = new WinnerManagementService();
        winner = winnerFinder.searchWinner(move, board.getBoard(), winningStrategies);

        if (winner != null) {
            this.winner = winner;
            return true;
        }
        return false;
    }

    public static class GameBuilder {
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

        public Game build() throws TicTacToeException {

            boolean isDataValid = validateGameObjectCreationData();
            if (isDataValid) {
                Board board = createBoard(this.boardSize);
                return new Game(board, this.players, this.currentPlayersIndex, this.gameState, this.winningStrategies);
            } else {
                throw new TicTacToeException("boardSize is Invalid");
            }
        }

        private Board createBoard(int boardSize) {
            List<List<Cell>> board = new ArrayList<>(boardSize);
            for (int i = 0; i < boardSize; i++) {
                List<Cell> row = new ArrayList<>(boardSize);
                for (int j = 0; j < boardSize; j++) {
                    Cell cell = new Cell(i, j, null, CellState.EMPTY);
                    row.add(cell);
                }
                board.add(row);
            }
            return new Board(board);
        }

        private boolean validateGameObjectCreationData() {

            if (boardSize < 0 || players.size() + 1 != boardSize) {
                return false;
            } else if (this.currentPlayersIndex < 0 || this.currentPlayersIndex > boardSize - 1) {
                return false;
            }

            return true;
        }


    }

}
