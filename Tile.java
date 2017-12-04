package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by cscot_000 on 11/16/2017.
 */
public class Tile extends StackPane {
    final double HEIGHT = 50;
    final double WIDTH = 50;
    private Rectangle rectangle;
    private boolean isOccupied;
    private int row;
    private int col;
    private Piece piece;

    public Tile(int col, int row, Color color) {
        super();
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setRow(row);
        setCol(col);
        setRectangle(new Rectangle(WIDTH, HEIGHT, color));
        setAlignment(Pos.CENTER);
        setOccupied(false);
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
        if (piece.getTile() != null) {
            piece.getTile().removePiece(piece);
        }
        this.piece = piece;
        getChildren().add(piece);
        setOccupied(true);
        piece.setTile(this);
    }

    public void removePiece(Piece piece) {
        getChildren().remove(piece);
        setOccupied(false);
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

    public Color getColor() {
        return (Color)getRectangle().getFill();
    }

    public void setColor(Color color) {
        getRectangle().setFill(color);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
        getChildren().add(rectangle);
    }
}
