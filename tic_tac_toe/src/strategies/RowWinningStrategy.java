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

public class RowWinningStrategy implements WinningStrategy {

    List<Map<Symbol, Integer>> rowFrequencies;

    public RowWinningStrategy(int size, List<Player> player) {
        rowFrequencies = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            rowFrequencies.add(new HashMap<>());
        }
    }

    @Override
    public Player findWinner(List<List<Cell>> board, Move move) {
        Cell cell = move.getCell();
        Symbol symbol = cell.getPlayer().getPlayerSymbol();
        Map<Symbol, Integer> row = rowFrequencies.get(cell.getRow());
        row.put(symbol, row.getOrDefault(symbol, 0) + 1);

        if (row.get(symbol) == board.size()) {
            return move.getPlayer();
        }

        return null;
    }

}
