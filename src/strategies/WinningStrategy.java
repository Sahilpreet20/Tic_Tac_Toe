package src.strategies;

import src.models.Board;
import src.models.Move;

public interface WinningStrategy {

    public boolean checkWinner(Board board, Move move);
    public void handleUndo(Board board, Move move);
}
