package model;

import enumerations.CellState;

import java.util.List;

public class Board {
    private List<List<Cell>> board;

    public Board(List<List<Cell>> board) {
        this.board = board;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }


    public void addCellToTheBoard(Cell cell) {
        List<Cell> row = board.get(cell.getRow());
        if (cell.getCellState().equals(CellState.EMPTY)) {
            cell.setCellState(CellState.FILLED);
        } else {
            System.out.println("Cell is FILLED by player " + cell.getPlayer().getPlayerName());
        }
    }


    public void showBoard() {

        for (int i = 0; i < this.board.size(); i++) {
            for (int j = 0; j < this.board.get(0).size(); j++) {
                Cell cell = board.get(i).get(j);
                if (cell.getCellState().equals(CellState.FILLED)) {
                    System.out.print(cell.getPlayer().getPlayerSymbol().getSign());
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }


}
