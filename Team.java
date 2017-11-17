package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by cscot_000 on 11/16/2017.
 */
public class Team {
    private final int MAX_SIZE = 12;

    private Color color;
    private ArrayList<Piece> pieceList = new ArrayList<>();

    public Team(Color color) {
        setColor(color);
        for (int n = 0; n < MAX_SIZE; n++) {
            getPieceList().add(new Piece(color));
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Piece> getPieceList() {
        return pieceList;
    }

    public void setPieceList(ArrayList<Piece> pieceList) {
        this.pieceList = pieceList;
    }
}
