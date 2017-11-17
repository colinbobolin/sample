package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by cscot_000 on 11/15/2017.
 */
public class Piece extends Circle {

    public Piece(Color color) {
        super(22, color);
        this.setOnMousePressed(event -> {
            setFill(Color.DARKBLUE);
        });
        setOnMouseReleased(event -> {
            setFill(color);
        });
    }
}
