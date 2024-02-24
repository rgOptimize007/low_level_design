package strategies;

import interfaces.WinningStrategy;
import model.Cell;
import model.Move;
import model.Player;
import model.Symbol;

import java.util.*;

public class RowWinningStrategy implements WinningStrategy {

    List<Map<Symbol, Integer>> rowFrequencies;

    public RowWinningStrategy(int size, List<Player> player) {
        rowFrequencies = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            rowFrequencies.add(new HashMap<>());
        }
    }

    public Map<Symbol,Integer> getMap(List<List<Cell>> board, Move move){
        Cell cell = move.getCell();
        Symbol symbol = cell.getPlayer().getPlayerSymbol();
        Map<Symbol, Integer> row = rowFrequencies.get(cell.getRow());
        return row;
    }

    @Override
    public void addMoveFrequency(List<List<Cell>> board, Move move){
        Map<Symbol,Integer> row = getMap(board,move);
        Symbol symbol = move.getPlayer().getPlayerSymbol();
        row.put(symbol, row.getOrDefault(symbol, 0) + 1);
    }

    @Override
    public void removeMoveFrequency(List<List<Cell>> board, Move move){
        Map<Symbol,Integer> row = getMap(board,move);
        Symbol symbol = move.getPlayer().getPlayerSymbol();
        if(Objects.isNull(row.get(symbol))){
            System.out.println("Move can't be un-done as move it not captured yet for col : " + move.getCell().getCol());
        }
        else{
            row.put(symbol, row.get(symbol) - 1);
        }
    }

    @Override
    public Player findWinner(List<List<Cell>> board, Move move) {
        Cell cell = move.getCell();
        Symbol symbol = cell.getPlayer().getPlayerSymbol();
        Map<Symbol, Integer> row = rowFrequencies.get(cell.getRow());
        if (row.getOrDefault(symbol, 0) == board.size()) {
            return move.getPlayer();
        }

        return null;
    }

}
