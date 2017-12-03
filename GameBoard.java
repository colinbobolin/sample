package sample;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

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
        tiles = new Tile[ROWS][COLS];
        setUpGameBoard();
    }

    public void setUpGameBoard() {
        for (int y = 0; y < COLS; y++) {
            alternateBoardColors();
            for (int x = 0; x < ROWS; x++) {
                alternateBoardColors();
                Tile tile = new Tile(x, y, color);
                getTiles()[x][y] = tile;
                super.add(tile, x, y);
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

    public Tile getTileAt(int col, int row) throws IndexOutOfBoundsException{
        if (col > COLS + 1 || row > ROWS + 1) {
            throw new IndexOutOfBoundsException("Program tried to access a tile that doesn't exist.");
        }
        else return getTiles()[col][row];
    }
}
