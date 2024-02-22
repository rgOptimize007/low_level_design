package strategies;

import interfaces.WinningStrategy;
import model.Cell;
import model.Move;
import model.Player;
import model.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {

    List<Map<Symbol, Integer>> colFrequencies;

    public ColumnWinningStrategy(int size, List<Player> player) {
        colFrequencies = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            colFrequencies.add(new HashMap<>());
        }
    }

    @Override
    public Player findWinner(List<List<Cell>> board, Move move) {
        Cell cell = move.getCell();
        Symbol symbol = cell.getPlayer().getPlayerSymbol();
        Map<Symbol, Integer> col = colFrequencies.get(cell.getCol());
        col.put(symbol, col.getOrDefault(symbol, 0) + 1);

        if (col.get(symbol) == board.size()) {
            return move.getPlayer();
        }

        return null;
    }


}
