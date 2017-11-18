package sample;


import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by cscot_000 on 11/16/2017.
 */
public class Game {
    private Team teamOne;
    private Team teamTwo;
    private GameBoard gameBoard;
    private Piece pieceInHand;

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
        setUpTeamOne();
        setUpTeamTwo();
    }

    public void setUpTeamOne() {
        for (Piece piece : getTeamOne().getPieceList()) {
            for (int m = 0; m < getGameBoard().getTiles().length; m++) {
                for (int n = 0; n < getGameBoard().getTiles()[m].length; n++) {
                    try {
                        Tile tile = getGameBoard().getTileAt(n, m);
                        if (tile.getFill().equals(Color.BURLYWOOD) && !tile.isOccupied()) {
                            placePieceOnTile(piece, tile);
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
                        if (tile.getFill().equals(Color.BURLYWOOD) && !tile.isOccupied()) {
                            placePieceOnTile(piece, tile);
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

    public void moveFromTileToTile(Tile startingTile, Tile requestedTile) {

        removePieceFromTile(startingTile);
        placePieceOnTile(getPieceInHand(), requestedTile);
    }

    public void removePieceFromTile(Tile tile) {
        Piece piece = tile.getPiece();
        setPieceInHand(piece);
        getGameBoard().getChildren().remove(tile);
        piece.setOnBoard(false);
        tile.setOccupied(false);
    }

    public void placePieceOnTile(Piece piece, Tile tile) {
        tile.setPiece(piece);
        getGameBoard().add(piece, tile.getRow(), tile.getCol());
        getGameBoard().getTiles()[tile.getRow()][tile.getCol()] = tile;
        tile.setOccupied(true);
        piece.setOnBoard(true);
    }

    public void showMoveOptions(Tile tile) {
        ArrayList<Tile> moveOptions = new ArrayList<>();
        int row = tile.getRow();
        int col = tile.getCol();
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

    public Piece getPieceInHand() {
        return pieceInHand;
    }

    public void setPieceInHand(Piece pieceInHand) {
        this.pieceInHand = pieceInHand;
    }
}
