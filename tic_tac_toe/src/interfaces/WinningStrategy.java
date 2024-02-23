package interfaces;

import model.Cell;
import model.Move;
import model.Player;

import java.util.List;

public interface WinningStrategy {

    Player findWinner(List<List<Cell>> board, Move move);

    void addMoveFrequency(List<List<Cell>> board,Move move);

    void removeMoveFrequency(List<List<Cell>> board,Move move);

}
