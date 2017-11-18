package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by cscot_000 on 11/16/2017.
 */
public class Tile extends Rectangle {
    final double HEIGHT = 50;
    final double WIDTH = 50;
    private boolean isOccupied;
    private int row;
    private int col;
    private Piece piece;

    public Tile(int x, int y, Color color) {
        super();
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setRow(x);
        setCol(y);
        setFill(color);
        setOccupied(false);
        //TODO set onClickListener to light up adjacent unoccupied tiles
        setOnMousePressed(event -> {
            System.out.println("Tile event initiated");
            if (Game.getInstance().getPieceInHand() != null && !isOccupied()) {
                Game.getInstance().placePieceOnTile(Game.getInstance().getPieceInHand(), this);
                Game.getInstance().emptyHand();
            }
        });
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        setOccupied(true);
        piece.setTile(this);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
