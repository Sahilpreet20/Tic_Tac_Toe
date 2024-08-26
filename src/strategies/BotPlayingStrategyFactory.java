package src.strategies;

import src.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayerStrategy getBotPlayingStrategy(BotDifficultyLevel difficulty){
        if(difficulty.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }

        return null;
    }
}
