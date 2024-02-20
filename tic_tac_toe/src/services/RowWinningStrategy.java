package services;

import interfaces.WinningStrategy;
import model.Cell;
import model.Move;
import model.Player;
import model.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {

	List<Map<Symbol,Integer>> rowFrequencies = new ArrayList<>();

	@Override
	public Player findWinner(List<List<Cell>> board, Move move) {
		Cell cell = move.getCell();
		Symbol symbol = cell.getPlayer().getPlayerSymbol();
		return null;
	}

}
