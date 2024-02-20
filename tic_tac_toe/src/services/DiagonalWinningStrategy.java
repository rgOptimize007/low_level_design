package services;

import interfaces.WinningStrategy;
import model.Cell;
import model.Move;
import model.Player;

import java.util.List;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public Player findWinner(List<List<Cell>> board,Move move) {
        return null;
    }
}
