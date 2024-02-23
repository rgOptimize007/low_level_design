package controller;

import enumerations.GameState;
import exceptions.TicTacToeException;
import interfaces.WinningStrategy;
import model.Game;
import model.Player;

import java.util.List;

public class GameController {

    // method which will initialize the game state
    public Game startGame(int boardSize, List<Player> players, int currentPlayerIndex, List<WinningStrategy> winningStrategies) throws TicTacToeException {

        return new Game.GameBuilder().setBoard(boardSize).setPlayers(players)
                .setWinningStrategies(winningStrategies).setCurrentPlayersIndex(currentPlayerIndex)
                .setGameState(GameState.ON_GOING).build();
    }


    public void makeMove(int row, int col, Player player, Game game) {
        game.performMove(row, col, player);
    }

    // method to undo currentPlayers Move
    public void undoMove(Game game) {
        game.undoMove();
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
