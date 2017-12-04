package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

//TODO Make the interface look nice. Handle exceptions for when a piece tries to access a Tile outside of the array. Fix any bugs.

/**
 * This class contains all of the User Interface logic. The game starts with nextTurn(), and sets up MouseEvent handlers
 * for Pieces and Tiles. When the SelectPieceEvent is triggered, the highlightPossibleMoves() method is run. The
 * highlighted Tiles are the tiles appropriate for movement, and when a highlighted Tile is selected, the movePieceToTile()
 * method is run. The piece is moved, everything is reset, the MouseEvent listener for the team is removed, and the next
 * team is given MouseEvent listeners for their pieces. This continues until the game has a winner.
 */

public class GameUI extends Application {
    private Game game;
    private Piece selectedPiece;
    private Team teamWhoseTurnItIs;
    private ArrayList<Tile> possibleTiles = new ArrayList<>();
    private ArrayList<Tile> priorityTiles = new ArrayList<>();
    private Map<Tile, Piece> victimMap = new HashMap<>(); //HashMap for relating a victim piece to a priority tile

    public GameUI() {
        setGame(Game.getInstance());
        setTeamWhoseTurnItIs(getGame().getTeamRed());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(getGame().getGameBoard());

        stage.setScene(scene);
        stage.show();

        nextTurn(getTeamWhoseTurnItIs());
    }

    //Game loop starts here
    public void nextTurn(Team team) {
        for (Piece piece : team.getPieceList()) {
            piece.setOnMousePressed(new SelectPieceHandler());
        }
    }

    public void highlightPossibleMovesFromTile(Tile currentTile) {
        clearHighlightedTiles();

        if (getTeamWhoseTurnItIs() == getGame().getTeamRed()) {
            lookBottomLeftDiagonal(currentTile);
            lookBottomRightDiagonal(currentTile);
        }
        if (getTeamWhoseTurnItIs() == getGame().getTeamBlack()) {
            lookTopLeftDiagonal(currentTile);
            lookTopRightDiagonal(currentTile);
        }

        if (getPriorityTiles().size() > 0) {
            for (Tile tile : getPriorityTiles()) {
                tile.setOnMousePressed(new SelectTileHandler());
            }

        } else if (getPriorityTiles().size() == 0 && getPossibleTiles().size() > 0) {
            for (Tile tile : getPossibleTiles()) {
                tile.setOnMousePressed(new SelectTileHandler());
            }
        }
        else System.out.println("GAME OVER");
    }

    public void setPossibleMove(Tile tile) {
        tile.setColor(Color.LIGHTBLUE);
        getPossibleTiles().add(tile);
    }

    public void setPriorityMove(Tile tile) {
        tile.setColor(Color.RED);
        getPriorityTiles().add(tile);
    }

    public void clearHighlightedTiles() {
        for (Tile tile : getPossibleTiles()) tile.setColor(Color.BURLYWOOD);
        for (Tile tile : getPriorityTiles()) tile.setColor(Color.BURLYWOOD);
        getPossibleTiles().clear();
        getPriorityTiles().clear();
    }

    public void lookBottomRightDiagonal(Tile currentTile) {
        Tile possibleTile = getBottomRightDiagonal(currentTile);
        if (!possibleTile.isOccupied()) {
            setPossibleMove(possibleTile);
        }
        else if (possibleTile.isOccupied() && !getBottomRightDiagonal(possibleTile).isOccupied()) {
            Tile possibleJumpTile = getBottomRightDiagonal(possibleTile);
            setPriorityMove(possibleJumpTile);
            victimMap.put(possibleJumpTile, possibleTile.getPiece());
        }
    }

    public Tile getBottomRightDiagonal(Tile currentTile) {
        int possTileRow = currentTile.getRow()+1;
        int possTileCol = currentTile.getCol()+1;
        return getGame().getGameBoard().getTileAt(possTileCol, possTileRow);
    }

    public void lookBottomLeftDiagonal(Tile currentTile) {
        Tile possibleTile = getBottomLeftDiagonal(currentTile);
        if (!possibleTile.isOccupied()) {
            setPossibleMove(possibleTile);
        }
        else if (possibleTile.isOccupied() && !getBottomLeftDiagonal(possibleTile).isOccupied()) {
            Tile possibleJumpTile = getBottomLeftDiagonal(possibleTile);
            setPriorityMove(possibleJumpTile);
            victimMap.put(possibleJumpTile, possibleTile.getPiece());
        }
    }

    public Tile getBottomLeftDiagonal(Tile currentTile) {
        int possTileRow = currentTile.getRow()+1;
        int possTileCol = currentTile.getCol()-1;
        return getGame().getGameBoard().getTileAt(possTileCol, possTileRow);
    }

    public void lookTopRightDiagonal(Tile currentTile) {
        Tile possibleTile = getTopRightDiagonal(currentTile);
        if (!possibleTile.isOccupied()) {
            setPossibleMove(possibleTile);
        }
        else if (possibleTile.isOccupied() && !getTopRightDiagonal(possibleTile).isOccupied()) {
            Tile possibleJumpTile = getTopRightDiagonal(possibleTile);
            setPriorityMove(possibleJumpTile);
            victimMap.put(possibleJumpTile, possibleTile.getPiece());
        }
    }

    public Tile getTopRightDiagonal(Tile currentTile) {
        int possTileRow = currentTile.getRow()-1;
        int possTileCol = currentTile.getCol()+1;
        return getGame().getGameBoard().getTileAt(possTileCol, possTileRow);
    }

    public void lookTopLeftDiagonal(Tile currentTile) {
        Tile possibleTile = getTopLeftDiagonal(currentTile);
        if (!possibleTile.isOccupied()) {
            setPossibleMove(possibleTile);
        }
        else if (possibleTile.isOccupied() && !getTopLeftDiagonal(possibleTile).isOccupied()) {
            Tile possibleJumpTile = getTopLeftDiagonal(possibleTile);
            setPriorityMove(possibleJumpTile);
            victimMap.put(possibleJumpTile, possibleTile.getPiece());
        }
    }

    public Tile getTopLeftDiagonal(Tile currentTile) {
        int possTileRow = currentTile.getRow()-1;
        int possTileCol = currentTile.getCol()-1;
        return getGame().getGameBoard().getTileAt(possTileCol, possTileRow);
    }

    public void alternateTeamWhoseTurnItIs() {
        if (getTeamWhoseTurnItIs() == getGame().getTeamRed()) {
            setTeamWhoseTurnItIs(getGame().getTeamBlack());
        }
        else setTeamWhoseTurnItIs(getGame().getTeamRed());
    }

    public void movePieceToTile(Piece piece, Tile tile) {
        tile.setPiece(piece);
        resetForNextTurn();
    }

    public void resetForNextTurn() {
        setSelectedPiece(null);
        turnOffListeners(getTeamWhoseTurnItIs());
        clearHighlightedTiles();
        alternateTeamWhoseTurnItIs();
        nextTurn(getTeamWhoseTurnItIs());
    }

    public void turnOffListeners(Team team) {
        for (Piece piece : team.getPieceList()) piece.setOnMousePressed(null);
        for (Tile tile : getPossibleTiles()) tile.setOnMouseClicked(null);
        for (Tile tile : getPriorityTiles()) tile.setOnMouseClicked(null);
    }

    public void remove(Piece piece) {
        piece.getTile().removePiece(piece);
        piece.remove();
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public Team getTeamWhoseTurnItIs() {
        return teamWhoseTurnItIs;
    }

    public void setTeamWhoseTurnItIs(Team teamWhoseTurnItIs) {
        this.teamWhoseTurnItIs = teamWhoseTurnItIs;
    }

    public ArrayList<Tile> getPriorityTiles() {
        return priorityTiles;
    }

    public ArrayList<Tile> getPossibleTiles() {
        return possibleTiles;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    class SelectPieceHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            clearHighlightedTiles();
            Piece selectedPiece = ((Piece)event.getSource());
            setSelectedPiece(selectedPiece);
            highlightPossibleMovesFromTile(selectedPiece.getTile());
        }
    }

    class SelectTileHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Tile selectedTile = (Tile)event.getSource();
            if (getSelectedPiece() != null && !selectedTile.isOccupied()) {
                if (selectedTile.getColor() == Color.RED) {
                    movePieceToTile(getSelectedPiece(), selectedTile);
                    Piece victim = victimMap.get(selectedTile);
                    remove(victim);
                }
                else if (selectedTile.getColor() == Color.LIGHTBLUE)  {
                    movePieceToTile(getSelectedPiece(), selectedTile);
                }
            }
        }
    }
}
