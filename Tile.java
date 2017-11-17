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
    private int xPos;
    private int yPos;
    private Piece piece;

    public Tile(int x, int y, Color color) {
        super();
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setxPos(x);
        setyPos(y);
        setFill(color);
        setOccupied(false);
        //TODO set onClickListener to light up adjacent unoccupied tiles
        setOnMouseClicked(event -> {
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
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
