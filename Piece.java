package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by cscot_000 on 11/15/2017.
 */
public class Piece extends Circle {
    private boolean isOnBoard;

    public Piece(Color color) {
        super(22, color);
        setOnBoard(false);
        this.setOnMousePressed(event -> {
            setFill(Color.BLUE);
        });
        setOnMouseReleased(event -> {
            setFill(color);
        });
    }


    public boolean isOnBoard() {
        return isOnBoard;
    }

    public void setOnBoard(boolean onBoard) {
        isOnBoard = onBoard;
    }
}
