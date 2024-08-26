package src.models;

import src.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game{
    private Board board;
    private List<Player> players;
    private Player winner;
    private int nextPlayerIndex;
    private List<Move> moves; //WHY MOVES IN GAMES??
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;

    private Game(Builder builder) {
        this.board = new Board(builder.dimension);
        this.players = builder.players;
        this.winner = null;
        this.nextPlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winningStrategies = builder.winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public void displayBoard(){
        board.display();
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    private boolean validateMove(Move move){
        if(!move.getCell().getCellState().equals(CellState.EMPTY)){
            return false;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row < 0 || col < 0 || row > board.getSize() - 1 || col > board.getSize() - 1){
            return false;
        }

        return true;
    }

    public boolean checkWinner(Move move){
        return false;
    }
    public void makeMove(){
        Player currentPlayer = players.get(nextPlayerIndex);

        System.out.println(" It's " + currentPlayer.getName() + "'s turn. Please make the move");

        Move move = currentPlayer.makeMove(board);

        if(!validateMove(move)){
            System.out.println("Not a valid move");
            return;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cellToChange = board.getGrid().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setSymbol(currentPlayer.getSymbol());
        move.setPlayer(currentPlayer);
        move.setCell(cellToChange);


        moves.add(move);

        nextPlayerIndex++;
        nextPlayerIndex %= players.size();

//        confirm if there is change in state
        if(checkWinner(move)){
            setWinner(currentPlayer);
            setGameState(GameState.SUCCESS);
        }
        else if(moves.size() == board.getSize() * board.getSize()){
            setWinner(null);
            setGameState(GameState.DRAW);
        }

    }
    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validate(){

            if (players.size() != dimension - 1){
                throw new RuntimeException("Invalid Player Count");
            }

            //Only one Bot player check
            int botCount = 0;
            for(Player player:players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount ++;

                }

                if (botCount > 1){
                    throw new RuntimeException("More than one bot is not allowed");
                }
            }

            //Each Player has separate symbol check
            Set<Character> symbolSet = new HashSet<>();

            for(Player player:players){
                if(symbolSet.contains(player.getSymbol().getSym())){
                    throw new RuntimeException("Multiple Players Have Same Symbol");
                }

                symbolSet.add(player.getSymbol().getSym());

            }

        }
        public Game build(){
            validate();
            return new Game(this);
        }
    }
}