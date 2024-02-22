package services;

import interfaces.WinningStrategy;
import model.Cell;
import model.Move;
import model.Player;

import java.util.List;

public class WinnerManagementService {

    public Player searchWinner(Move move, List<List<Cell>> board, List<WinningStrategy> list) {

        for (WinningStrategy strategy : list) {
            Player winner = strategy.findWinner(board, move);
            if (winner != null) {
                return winner;
            }
        }
        return null;
    }

    public Player updateFrequencies(Move move , List<List<Cell>> board, List<WinningStrategy> list ){
        return null;
    }

}
