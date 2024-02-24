package model;

import enumerations.CellState;
import enumerations.GameState;
import exceptions.TicTacToeException;
import interfaces.WinningStrategy;

import java.util.*;
import java.util.stream.Collectors;

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

    public void performMove(int row, int col, Player player)  {

		// validate row and column data
		if (validateCell(row,col)) {

			//mark cell FILLED for move
            Cell cell = board.getBoard().get(row).get(col);
            cell.setPlayer(player);
            cell.setCellState(CellState.FILLED);

			//create a move
			Move move = new Move(cell, player);

            // update frequency counters for winner calculation
            winningStrategies.forEach(t -> t.addMoveFrequency(board.getBoard(),move));

			// add cell to undo list
			addMoveToUndoList(move);

            if(move != null){

                // update game status
                updateGameStatus(move);

                // move to next player if game is still on going
                if(gameState.toString().equals(GameState.ON_GOING.toString())){
                    // move to next player
                    currentPlayersIndex = ((currentPlayersIndex + 1) % players.size());
                }
            }

		} else {
			System.out.println("Invalid move ... Please play again ");
		}
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


	public void updateGameStatus(Move move){

		// check if game has finished or drawn
		if (this.checkWinner(move)) {
            winner = move.getPlayer();
			setGameState(GameState.FINISHED);
		} else if (undoList.size() == Math.pow(board.getBoard().size(), 2)) {
			gameState = GameState.DRAW;
		}
	}

    public boolean checkWinner(Move move) {
        List<Player> winnerList = winningStrategies.stream().map(t -> t.findWinner(board.getBoard(),move))
                .filter(t -> !Objects.isNull(t)).collect(Collectors.toList());

        if (Objects.isNull(winnerList) || winnerList.size() == 0) {
            return false;
        }
        return true;
    }


    public void undoMove() {

        // get move to undo
        Move move = getUndoList().remove(getUndoList().size() - 1);

        //mark cell as EMPTY for the move
        List<Cell> row = board.getBoard().get(move.getCell().getRow());
        Cell cell = row.get(move.getCell().getCol());
        cell.setCellState(CellState.EMPTY);

        // we need to update maps to reduce the frequency
        winningStrategies.forEach(t -> t.removeMoveFrequency(board.getBoard(),move));

        // set player index back to previous player
        currentPlayersIndex = ((currentPlayersIndex -1 + players.size()) % players.size());
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
