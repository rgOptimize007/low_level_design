package client;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.GameController;
import enumerations.GameState;
import enumerations.PlayerType;
import interfaces.WinningStrategy;
import model.HumanPlayer;
import model.Player;
import exceptions.TicTacToeException;
import services.ColumnWinningStrategy;
import services.RowWinningStrategy;

public class ClientInterface {
	
	

	/**
	 * @param args
	 * @throws TicTacToeException
	 */
	public static void main(String[] args) throws TicTacToeException {

	//	System.out.println("Welcome to tic-tac-toe \n Its a 2 player game : \n Please enter name for player 1 (Symbol - X): ");

		GameController gameController = new GameController();
		Scanner sc = new Scanner(System.in);
		List<Player> players = new ArrayList<>();
		System.out.println("Peak Player names");
		players.add(new HumanPlayer("1","Mark",'X',PlayerType.HUMAN));
		players.add(new HumanPlayer("2","Harvey",'O',PlayerType.HUMAN));
		
		List<WinningStrategy> winningStrategies = new ArrayList<>();
		winningStrategies.add(new RowWinningStrategy());
		winningStrategies.add(new ColumnWinningStrategy());
		
		gameController.startGame(3, players, 0, winningStrategies);
		
		System.out.println("Game has started , Please see the board below and play...");
		
		//System.out.println("Game state : " + gameController.checkGameState().toString());
		
		while(gameController.checkGameState().toString().equals(GameState.ON_GOING.toString())){
		
		
			System.out.println("Current turn : " + gameController.getCurrentPlayer().getPlayerName());
			System.out.println("Enter row and column");
			int row = sc.nextInt();
			int col = sc.nextInt();
			
			gameController.makeMove(row , col,gameController.getCurrentPlayer());
			if(gameController.checkGameState().toString().equals(GameState.FINISHED.toString())){
				break;
			}
			gameController.showBoard();
			System.out.println("Want to undo?? [Y/N]");
			String undoFlag = sc.next().toUpperCase();
			if(undoFlag.equals("Y")){
				gameController.undoMove();
				gameController.showBoard();
			}
			
		}
		
		if(gameController.checkGameState().toString().equals(GameState.FINISHED.toString())){
			System.out.println("Winner for current game is : " + gameController.getGame().getWinner().getPlayerName());
		}
		else if(gameController.checkGameState().toString().equals(GameState.DRAW.toString())){
			System.out.println("Its a draw..");
		}
	
	}
	
}