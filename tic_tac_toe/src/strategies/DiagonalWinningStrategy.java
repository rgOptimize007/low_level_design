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

public class DiagonalWinningStrategy implements WinningStrategy {
    Map<Symbol, Integer> diagonalFrequncies;

    public DiagonalWinningStrategy(int size, List<Player> player) {
        diagonalFrequncies = new HashMap<>();
    }

    @Override
    public Player findWinner(List<List<Cell>> board, Move move) {
        Cell cell = move.getCell();
        Symbol symbol = cell.getPlayer().getPlayerSymbol();

        if(cell.getRow() == cell.getCol()) {
            diagonalFrequncies.put(symbol, diagonalFrequncies.getOrDefault(symbol, 0) + 1);
            if (diagonalFrequncies.get(symbol) == board.size()) {
                return move.getPlayer();
            }
        }

        return null;
    }

}
