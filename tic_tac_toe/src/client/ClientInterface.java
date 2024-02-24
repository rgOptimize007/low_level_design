package client;

import controller.GameController;
import enumerations.GameState;
import enumerations.PlayerType;
import exceptions.TicTacToeException;
import interfaces.WinningStrategy;
import model.Game;
import model.HumanPlayer;
import model.Player;
import strategies.ColumnWinningStrategy;
import strategies.DiagonalWinningStrategy;
import strategies.ReverseDiagonalWinningStrategy;
import strategies.RowWinningStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientInterface {
    /**
     * @param args
     * @throws TicTacToeException
     */
    public static void main(String[] args) throws TicTacToeException {

        // following object to control state of the game (Its a stateless object)
        GameController gameController = new GameController();

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to SUIT's tic-tac-toe" + "\n\n" + "Lets see who wins MARK(X) vs HARVEY(O)");


        // players playing the game
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer("1", "Mark", 'X', PlayerType.HUMAN));
        players.add(new HumanPlayer("2", "Harvey", 'O', PlayerType.HUMAN));

        // winning strategies for the game
        List<WinningStrategy> winningStrategies = Arrays.asList(
                new RowWinningStrategy(3, players),
                new ColumnWinningStrategy(3, players),
                new DiagonalWinningStrategy(3, players),
                new ReverseDiagonalWinningStrategy(3,players));

        // create game object
        Game game = gameController.startGame(3, players, 0, winningStrategies);

        // play game
        while (gameController.checkGameState(game).toString().equals(GameState.ON_GOING.toString())) {

            // take input for move to make
            System.out.println("Current turn : " + gameController.getCurrentPlayer(game).getPlayerName());
            System.out.println("Enter row and column");
            int row = sc.nextInt();
            int col = sc.nextInt();

            // make move on the board
            gameController.makeMove(row, col, gameController.getCurrentPlayer(game), game);

            // check if game has finished
            if (gameController.checkGameState(game).toString().equals(GameState.FINISHED.toString())) {
                break;
            }

            // present the board;
            gameController.showBoard(game);

            // ask if user wants to undo the move
            System.out.println("Want to undo?? [Y/N]");

            // evaluate undo
            String undoFlag = sc.next().toUpperCase();
            if (undoFlag.equals("Y")) {
                // perform undo operation and show board post that
                gameController.undoMove(game);
                gameController.showBoard(game);
            }

        }

        // declare the winner
        if (gameController.checkGameState(game).toString().equals(GameState.FINISHED.toString())) {
            System.out.println("Winner for current game is : " + game.getWinner().getPlayerName());
        }
        // declare that game is Draw
        else if (gameController.checkGameState(game).toString().equals(GameState.DRAW.toString())) {
            System.out.println("Its a draw..");
        }

    }

}