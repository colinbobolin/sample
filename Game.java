package sample;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
            for (Tile[] col : getGameBoard().getTiles()) {
                for (Tile tile : col) {
                    if (tile.getFill().equals(Color.BURLYWOOD) && !tile.isOccupied()) {
                        //TODO place piece on this tile
                        tile.setPiece(piece);
                    }
                }
            }
        }
     /*   int y = 0;
        int x = 0;
        for (Piece piece : getTeamOne().getPieceList()) {
            getTiles().add(piece, x, y);
            if (x + 1 < gameBoard.COLS) x++;
            else {
                y++;
                x = 0;
            }
        }

        y = 6;
        x = 0;
        for (Piece piece : getTeamTwo().getPieceList()) {
            getTiles().add(piece, x, y);
            if (x + 1 < gameBoard.COLS) x++;
            else {
                y++;
                x = 0;
            }
        }
        */
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
