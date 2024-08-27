import src.controller.GameController;
import src.models.*;
import src.strategies.ColumnWInningStrategy;
import src.strategies.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        GameController gc = new GameController();
        Scanner sc = new Scanner(System.in);

        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(1, "Sahil", PlayerType.HUMAN, new Symbol('O')));
        players.add(new BotPlayer(2, "Bot", PlayerType.BOT, new Symbol('X'), BotDifficultyLevel.EASY));

        Game g = gc.startGame(3, players, List.of(new RowWinningStrategy(), new ColumnWInningStrategy()));

        gc.display(g);

        while (gc.checkState(g).equals(GameState.IN_PROGRESS)) {
            gc.makeMove(g);
            gc.display(g);
            System.out.println("Do you want to Undo ? [Y/N]");
            String undoAnswer = sc.nextLine();
            if (undoAnswer.equals("Y")) {
                gc.undo(g);
                System.out.println("Undo is successfull!");
                gc.display(g);
            }
        }


            if (gc.checkState(g).equals(GameState.SUCCESS)) {
                System.out.println(gc.getWinner(g).getName() + " won the game");
            } else if (gc.checkState(g).equals(GameState.DRAW)) {
                System.out.println("Game is Draw");
            }


        }
    }

