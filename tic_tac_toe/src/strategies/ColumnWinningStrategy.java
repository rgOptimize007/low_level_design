package strategies;

import interfaces.WinningStrategy;
import model.Cell;
import model.Move;
import model.Player;
import model.Symbol;

import java.util.*;

public class ColumnWinningStrategy implements WinningStrategy {

    List<Map<Symbol, Integer>> colFrequencies;

    public ColumnWinningStrategy(int size, List<Player> player) {
        colFrequencies = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            colFrequencies.add(new HashMap<>());
        }
    }

    @Override
    public void addMoveFrequency(List<List<Cell>> board, Move move){
        Cell cell = move.getCell();
        Map<Symbol, Integer> col = colFrequencies.get(cell.getCol());
        Symbol symbol = move.getPlayer().getPlayerSymbol();
        col.put(symbol, col.getOrDefault(symbol, 0) + 1);
    }

    @Override
    public void removeMoveFrequency(List<List<Cell>> board, Move move){
        Cell cell = move.getCell();
        Map<Symbol, Integer> col = colFrequencies.get(cell.getCol());
        Symbol symbol = move.getPlayer().getPlayerSymbol();
        if(Objects.isNull(col.get(symbol))){
            System.out.println("Move can't be un-done as move it not captured yet for col : " + move.getCell().getCol());
        }
        else{
            col.put(symbol, col.get(symbol) - 1);
        }
    }

    @Override
    public Player findWinner(List<List<Cell>> board, Move move) {
        Cell cell = move.getCell();
        Map<Symbol, Integer> col = colFrequencies.get(cell.getCol());
        Symbol symbol = move.getPlayer().getPlayerSymbol();
        if (col.get(symbol) == board.size()) {
            return move.getPlayer();
        }

        return null;
    }

}
