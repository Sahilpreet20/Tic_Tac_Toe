package src.controller;

import src.models.Game;
import src.models.GameState;
import src.models.Player;
import src.strategies.WinningStrategy;

import java.util.List;


public class GameController {

    // Currently it handles multiple games
    // but if we had use game attribute in this class then it will cater to one game only
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    public void display(Game game){
        game.displayBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void undo(Game game){
        game.undo();
    }

}
