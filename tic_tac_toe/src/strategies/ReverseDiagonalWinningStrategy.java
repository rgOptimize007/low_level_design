package strategies;

import interfaces.WinningStrategy;
import model.Cell;
import model.Move;
import model.Player;
import model.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReverseDiagonalWinningStrategy implements WinningStrategy {

    Map<Symbol, Integer> revDiagonalFrequencies;

    public ReverseDiagonalWinningStrategy(int size, List<Player> player) {
            revDiagonalFrequencies = new HashMap<>();
    }

    @Override
    public void addMoveFrequency(List<List<Cell>> board, Move move){
        Cell cell = move.getCell();
        Symbol symbol = move.getPlayer().getPlayerSymbol();
        if(cell.getRow() + cell.getCol() == board.size() - 1) {
            revDiagonalFrequencies.put(symbol, revDiagonalFrequencies.getOrDefault(symbol, 0) + 1);
        }
    }

    @Override
    public void removeMoveFrequency(List<List<Cell>> board, Move move){

        Cell cell = move.getCell();
        Symbol symbol = move.getPlayer().getPlayerSymbol();
        if(cell.getRow() + cell.getCol() == board.size() - 1) {
            Integer frequency = revDiagonalFrequencies.get(symbol);
            if(Objects.isNull(frequency)){
                System.out.println("Move can't be un-done as move it not captured yet");
                return;
            }
            revDiagonalFrequencies.put(symbol, revDiagonalFrequencies.get(symbol) - 1);
        }
    }

    @Override
    public Player findWinner(List<List<Cell>> board, Move move) {
        Cell cell = move.getCell();
        Symbol symbol = cell.getPlayer().getPlayerSymbol();

        if(cell.getRow() + cell.getCol() == board.size() - 1) {
            if (revDiagonalFrequencies.get(symbol) == board.size()) {
                return move.getPlayer();
            }
        }

        return null;
    }

}
