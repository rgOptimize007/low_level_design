package controller;

import enumerations.CellState;
import enumerations.GameState;
import exceptions.TicTacToeException;
import interfaces.WinningStrategy;
import model.Cell;
import model.Game;
import model.Move;
import model.Player;
import services.WinnerManagementService;

import java.util.List;

public class GameController {

    private WinnerManagementService winnerFinder = new WinnerManagementService();

    // method which will initialize the game state
    public Game startGame(int boardSize, List<Player> players, int currentPlayerIndex, List<WinningStrategy> winningStrategies) throws TicTacToeException {

        return new Game.GameBuilder().setBoard(boardSize).setPlayers(players)
                .setWinningStrategies(winningStrategies).setCurrentPlayersIndex(currentPlayerIndex)
                .setGameState(GameState.ON_GOING).build();
    }


    public void makeMove(int row, int col, Player player, Game game) throws TicTacToeException {

        // performing and validating the move
        Move move = game.performAndGetMove(row, col, player);

        if(move != null){
            // update game status
            game.updateGameStatus(move);

            // move to next player if game is still on going
            if(game.getGameState().toString().equals(GameState.ON_GOING.toString())){
                game.updatePlayerIndex();
            }
        }

    }

    // method to undo currentPlayers Move
    public void undoMove(Game game) {
        // extract move from undo list
        Move move = game.getUndoList().remove(game.getUndoList().size() - 1);

        //make changes to the board
        List<Cell> row = game.getBoard().getBoard().get(move.getCell().getRow());
        Cell cell = row.get(move.getCell().getCol());
        cell.setCellState(CellState.EMPTY);

        // we need to update maps to reduce the frequency

        // set player index back to previous player
        game.setCurrentPlayersIndex(game.getCurrentPlayersIndex() - 1);
    }


    public GameState checkGameState(Game game) {
        return game.getGameState();
    }

    public Player getCurrentPlayer(Game game) {
        return game.getPlayers().get(game.getCurrentPlayersIndex());
    }


    public void showBoard(Game game) {
        game.getBoard().showBoard();
    }
}
