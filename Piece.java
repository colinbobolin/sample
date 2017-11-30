package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by cscot_000 on 11/15/2017.
 */
public class Piece extends Circle {
    private boolean isOnBoard;
    private Tile tile;
    private Color color;

    public Piece(Color color) {
        super(22, color);
        setColor(color);
        setOnBoard(false);
    }

    public boolean isOnBoard() {
        return isOnBoard;
    }

    public void setOnBoard(boolean onBoard) {
        isOnBoard = onBoard;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        setOnBoard(true);
        this.tile = tile;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
