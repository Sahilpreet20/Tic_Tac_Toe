package src.strategies;

import src.models.Board;
import src.models.Move;

public interface BotPlayerStrategy {

    public Move makeMove(Board board);
}
