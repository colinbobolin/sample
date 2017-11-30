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
    ArrayList<Tile> possibleTiles = new ArrayList<>();

    public GameUI() {
        game = Game.getInstance();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(game.getGameBoard());

        stage.setScene(scene);
        stage.show();

        for (Piece piece : game.getTeamOne().getPieceList()) {
            piece.setOnMousePressed(new SelectPieceHandler());
        }
        for (Piece piece : game.getTeamTwo().getPieceList()) {
            piece.setOnMousePressed(new SelectPieceHandler());
        }
        for (Tile[] tileRow : game.getGameBoard().getTiles()) {
            for (Tile tile : tileRow) {
                tile.setOnMousePressed(new SelectTileHandler());
            }
        }
    }

    public void highlightPossibleMoves() {
        Tile currentTile = getSelectedPiece().getTile();
        int currentTileRow = currentTile.getRow();
        int currentTileCol = currentTile.getCol();
        int possTileRow;
        int possTileCol;
        possibleTiles.clear();
        //TODO I would like to split this up into more methods, one for red team and one for black. It's messy now, but its starting to come together.
        try {
            for (Tile[] tileRow : game.getGameBoard().getTiles()) {
                for (Tile tile : tileRow) {
                    if (tile.isValid() && !tile.isOccupied()) {
                        possTileRow = tile.getRow();
                        possTileCol = tile.getCol();

                        if(possTileRow == currentTileRow + 1 && possTileCol == currentTileCol + 1)
                            possibleTiles.add(tile);
                        if(possTileRow == currentTileRow + - 1 && possTileCol == currentTileCol + 1)
                            possibleTiles.add(tile);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        for (Tile tile : possibleTiles) {
            tile.setColor(Color.LIGHTBLUE);
        }

    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
        System.out.println("Piece selected!");
    }

    class SelectPieceHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Piece selectedPiece = ((Piece)event.getSource());
            setSelectedPiece(selectedPiece);
            highlightPossibleMoves();
        }
    }

    class SelectTileHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Tile selectedTile = (Tile)event.getSource();
            if (getSelectedPiece() != null && !selectedTile.isOccupied() && (selectedTile.getColor() == Color.LIGHTBLUE)) {
                selectedTile.setPiece(getSelectedPiece());
                setSelectedPiece(null);
                for (Tile tile : possibleTiles) {
                    tile.setColor(Color.BURLYWOOD);
                }
            }

        }
    }
}
