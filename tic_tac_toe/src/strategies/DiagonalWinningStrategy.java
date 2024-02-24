package strategies;

import interfaces.WinningStrategy;
import model.Cell;
import model.Move;
import model.Player;
import model.Symbol;

import java.util.*;

public class DiagonalWinningStrategy implements WinningStrategy {
    Map<Symbol, Integer> diagonalFrequncies;

    public DiagonalWinningStrategy(int size, List<Player> player) {
        diagonalFrequncies = new HashMap<>();
    }


    @Override
    public void addMoveFrequency(List<List<Cell>> board, Move move){
        Cell cell = move.getCell();
        Symbol symbol = move.getPlayer().getPlayerSymbol();
        if(cell.getRow() == cell.getCol()) {
            diagonalFrequncies.put(symbol, diagonalFrequncies.getOrDefault(symbol, 0) + 1);
        }
    }

    @Override
    public void removeMoveFrequency(List<List<Cell>> board, Move move){

        Cell cell = move.getCell();
        Symbol symbol = move.getPlayer().getPlayerSymbol();
        if(cell.getRow() == cell.getCol()) {
            Integer frequency = diagonalFrequncies.get(symbol);
            if(Objects.isNull(frequency)){
                System.out.println("Move can't be un-done as move it not captured yet");
                return;
            }
            diagonalFrequncies.put(symbol, diagonalFrequncies.get(symbol) - 1);
        }
    }

    @Override
    public Player findWinner(List<List<Cell>> board, Move move) {
        Cell cell = move.getCell();
        Symbol symbol = move.getPlayer().getPlayerSymbol();
        if(cell.getRow() == cell.getCol()) {
            if (diagonalFrequncies.getOrDefault(symbol,0) == board.size()) {
                return move.getPlayer();
            }
        }

        return null;
    }

}
