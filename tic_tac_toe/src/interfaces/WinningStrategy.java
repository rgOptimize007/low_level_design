package interfaces;

import java.util.List;

import model.Cell;
import model.Move;
import model.Player;

public interface WinningStrategy {
	
	Player findWinner(List<List<Cell>> board,Move move);

}
