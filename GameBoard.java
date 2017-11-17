package sample;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Created by cscot_000 on 11/15/2017.
 */
public class GameBoard extends GridPane {
    final int ROWS = 8;
    final int COLS = 8;
    private Tile[][] tiles;
    private Color color = Color.BEIGE;

    public GameBoard() {
        super();
        setTiles(new Tile[ROWS][COLS]);
        setUpGameBoard();
    }

    public void setUpGameBoard() {
        for (int y = 0; y < COLS; y++) {
            alternateBoardColors();
            for (int x = 0; x < ROWS; x++) {
                alternateBoardColors();
                getTiles()[x][y] = new Tile(color);
                super.add(getTiles()[x][y], x, y);
            }
        }
    }

    public void alternateBoardColors() {
        if (getColor().equals(Color.BEIGE))
            setColor(Color.BURLYWOOD);
        else setColor(Color.BEIGE);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
}
