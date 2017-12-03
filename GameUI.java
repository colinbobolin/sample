package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameUI extends Application {
    Game game;
    private Piece selectedPiece;
    private Team teamWhoseTurnItIs;
    ArrayList<Tile> possibleTiles = new ArrayList<>();

    public GameUI() {
        game = Game.getInstance();
        setTeamWhoseTurnItIs(game.getTeamRed());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(game.getGameBoard());

        stage.setScene(scene);
        stage.show();

        nextTurn(getTeamWhoseTurnItIs());
    }

    public void highlightPossibleMovesFromTile(Tile currentTile) {
        possibleTiles.clear();

        if (getTeamWhoseTurnItIs()==game.getTeamRed()) {
            lookBottomLeftDiagonal(currentTile);
            lookBottomRightDiagonal(currentTile);
        }
        if (getTeamWhoseTurnItIs()==game.getTeamBlack()) {
            lookTopLeftDiagonal(currentTile);
            lookTopRightDiagonal(currentTile);
        }
        for (Tile tile : possibleTiles) {
            tile.setColor(Color.LIGHTBLUE);
        }
    }

    public void clearPossibleTiles() {
        for (Tile tile : possibleTiles) {
            tile.setColor(Color.BURLYWOOD);
        }
        possibleTiles.clear();
    }

    public void lookBottomRightDiagonal(Tile currentTile) {
        int possTileRow = currentTile.getRow()+1;
        int possTileCol = currentTile.getCol()+1;
        Tile possibleTile = game.getGameBoard().getTileAt(possTileCol, possTileRow);
        if (!possibleTile.isOccupied()) {
            possibleTiles.add(possibleTile);
        }
    }

    public void lookBottomLeftDiagonal(Tile currentTile) {
        int possTileRow = currentTile.getRow()+1;
        int possTileCol = currentTile.getCol()-1;
        Tile possibleTile = game.getGameBoard().getTileAt(possTileCol, possTileRow);
        if (!possibleTile.isOccupied()) {
            possibleTiles.add(possibleTile);
        }
    }

    public void lookTopRightDiagonal(Tile currentTile) {
        int possTileRow = currentTile.getRow()-1;
        int possTileCol = currentTile.getCol()+1;
        Tile possibleTile = game.getGameBoard().getTileAt(possTileCol, possTileRow);
        if (!possibleTile.isOccupied()) {
            possibleTiles.add(possibleTile);
        }
    }

    public void lookTopLeftDiagonal(Tile currentTile) {
        int possTileRow = currentTile.getRow()-1;
        int possTileCol = currentTile.getCol()-1;
        Tile possibleTile = game.getGameBoard().getTileAt(possTileCol, possTileRow);
        if (!possibleTile.isOccupied()) {
            possibleTiles.add(possibleTile);
        }
    }

    //Game loop
    public void nextTurn(Team team) {
        for (Piece piece : team.getPieceList()) {
            piece.setOnMousePressed(new SelectPieceHandler());
        }
        for (Tile[] tileRow : game.getGameBoard().getTiles()) {
            for (Tile tile : tileRow) {
                tile.setOnMousePressed(new SelectTileHandler());
            }
        }
    }

    public void alternateTeamWhoseTurnItIs() {
        if (getTeamWhoseTurnItIs() == game.getTeamRed()) {
            setTeamWhoseTurnItIs(game.getTeamBlack());
        }
        else setTeamWhoseTurnItIs(game.getTeamRed());
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
        System.out.println("Piece selected!");
    }

    public Team getTeamWhoseTurnItIs() {
        return teamWhoseTurnItIs;
    }

    public void setTeamWhoseTurnItIs(Team teamWhoseTurnItIs) {
        this.teamWhoseTurnItIs = teamWhoseTurnItIs;
    }

    class SelectPieceHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            clearPossibleTiles();
            Piece selectedPiece = ((Piece)event.getSource());
            setSelectedPiece(selectedPiece);
            highlightPossibleMovesFromTile(selectedPiece.getTile());
        }
    }

    class SelectTileHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Tile selectedTile = (Tile)event.getSource();
            if (getSelectedPiece() != null && !selectedTile.isOccupied() && (selectedTile.getColor() == Color.LIGHTBLUE)) {
                selectedTile.setPiece(getSelectedPiece());
                setSelectedPiece(null);
                alternateTeamWhoseTurnItIs();
                nextTurn(getTeamWhoseTurnItIs());
                clearPossibleTiles();
            }

        }
    }
}
