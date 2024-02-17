package interfaces;

import java.util.List;

import model.Move;
import model.Player;

public interface WinningStrategy {
	
	Player findWinner(Move[][] board);

}
