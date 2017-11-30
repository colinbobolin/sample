package sample;

import javafx.scene.paint.Color;


public class Game {
    private Team teamOne;
    private Team teamTwo;
    private GameBoard gameBoard;
    private static Game instance = null;

    private Game() {
        setGameBoard(new GameBoard());
        setTeamOne(new Team(Color.DARKRED));
        setTeamTwo(new Team(Color.BLACK));

        setUpPieces();
    }

    public static Game getInstance() {
        if(instance == null) instance = new Game();
        return instance;
    }

    public void setUpPieces() {
        setUpTeamOne();
        setUpTeamTwo();
    }

    public void setUpTeamOne() {
        for (Piece piece : getTeamOne().getPieceList()) {
            for (int m = 0; m < getGameBoard().getTiles().length; m++) {
                for (int n = 0; n < getGameBoard().getTiles()[m].length; n++) {
                    try {
                        Tile tile = getGameBoard().getTileAt(n, m);
                        if (tile.getColor().equals(Color.BURLYWOOD) && !tile.isOccupied()) {
                            tile.setPiece(piece);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (piece.isOnBoard())
                        break;
                }
                if (piece.isOnBoard())
                    break;
            }
        }
    }

    public void setUpTeamTwo() {
        for (Piece piece : getTeamTwo().getPieceList()) {
            for (int m = getGameBoard().getTiles().length - 1; m >= 0; m--) {
                for (int n = getGameBoard().getTiles()[m].length - 1; n >= 0; n--) {
                    try {
                        Tile tile = getGameBoard().getTileAt(n, m);
                        if (tile.getColor().equals(Color.BURLYWOOD) && !tile.isOccupied()) {
                            tile.setPiece(piece);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (piece.isOnBoard())
                        break;
                }
                if (piece.isOnBoard())
                    break;
            }
        }
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }
}
