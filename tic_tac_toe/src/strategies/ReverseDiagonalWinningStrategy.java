package strategies;

import interfaces.WinningStrategy;
import model.Cell;
import model.Move;
import model.Player;
import model.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseDiagonalWinningStrategy implements WinningStrategy {

    Map<Symbol, Integer> revDiagonalFrequencies;

    public ReverseDiagonalWinningStrategy(int size, List<Player> player) {
            revDiagonalFrequencies = new HashMap<>();
    }

    @Override
    public Player findWinner(List<List<Cell>> board, Move move) {
        Cell cell = move.getCell();
        Symbol symbol = cell.getPlayer().getPlayerSymbol();

        if(cell.getRow() + cell.getCol() == board.size() - 1) {
            revDiagonalFrequencies.put(symbol, revDiagonalFrequencies.getOrDefault(symbol, 0) + 1);

            if (revDiagonalFrequencies.get(symbol) == board.size()) {
                return move.getPlayer();
            }
        }

        return null;
    }

}
