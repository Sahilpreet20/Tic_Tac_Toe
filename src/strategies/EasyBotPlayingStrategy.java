package src.strategies;

import src.models.Board;
import src.models.Cell;
import src.models.CellState;
import src.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayerStrategy{

    @Override
    public Move makeMove(Board board) {
        for(List<Cell> row:board.getGrid()){
            for(Cell cell:row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(new Cell(cell.getRow(),cell.getCol()),null);
                }
            }
        }

        return null;
    }
}
