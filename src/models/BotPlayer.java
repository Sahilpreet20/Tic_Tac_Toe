package src.models;

import src.strategies.BotPlayerStrategy;
import src.strategies.BotPlayingStrategyFactory;

public class BotPlayer extends Player{
    private BotDifficultyLevel difficultyLevel;
    private BotPlayerStrategy botPlayerStrategy;

    public BotPlayer(int id, String name, PlayerType playerType, Symbol symbol,BotDifficultyLevel difficultyLevel) {
        super(id, name, playerType, symbol);
        this.difficultyLevel = difficultyLevel;
        this.botPlayerStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(difficultyLevel);
    }

    public Move makeMove(Board board){
        return botPlayerStrategy.makeMove(board);
    }

}
