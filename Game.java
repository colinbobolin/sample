package sample;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by cscot_000 on 11/16/2017.
 */
public class Game {
    private Team teamOne;
    private Team teamTwo;
    private GameBoard gameBoard;

    public Game() {
        setGameBoard(new GameBoard());
        setTeamOne(new Team(Color.DARKRED));
        setTeamTwo(new Team(Color.BLACK));

        setUpPieces();

        Stage stage = new Stage();
        Scene scene = new Scene(getGameBoard());

        stage.setScene(scene);
        stage.show();
    }

    public void setUpPieces() {

        for (Piece piece : getTeamOne().getPieceList()) {
            for (int i = 1; i<getGameBoard().getTiles().size(); i++) {
                Tile tile = getGameBoard().getTiles().get(i-1);
                if (tile.getFill().equals(Color.BURLYWOOD) && !tile.isOccupied()) {
                    getGameBoard().add(piece, tile.getxPos(), tile.getyPos());
                    tile.setOccupied(true);
                    break;
                }
            }
        }

        for (Piece piece : getTeamTwo().getPieceList()) {
            for (int i = getGameBoard().getTiles().size(); i>0; i--) {
                Tile tile = getGameBoard().getTiles().get(i-1);
                if (tile.getFill().equals(Color.BURLYWOOD) && !tile.isOccupied()) {
                    getGameBoard().add(piece, tile.getxPos(), tile.getyPos());
                    tile.setOccupied(true);
                    break;
                }
            }
        }
    }

    public void showMoveOptions(Tile tile) {
        ArrayList<Tile> moveOptions = new ArrayList<>();
        int row = tile.getxPos();
        int col = tile.getyPos();
        try {
            moveOptions.add(getGameBoard().getTileAt(row+1, col+1));
            moveOptions.add(getGameBoard().getTileAt(row-1, col+1));
        }
        catch (Exception e) {
            e.getMessage();
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
