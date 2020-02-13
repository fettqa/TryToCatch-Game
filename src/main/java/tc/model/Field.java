package tc.model;

import java.awt.*;

public class Field {

    private static final int FIELD_WIDTH = 3;
    private static final int FIELD_HEIGHT = 5;

    private final Figure[][] field = new Figure[FIELD_WIDTH][FIELD_HEIGHT];

    public Field() {
    }

    public int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public int getFieldHeight() {
        return FIELD_HEIGHT;
    }

    public Figure getFigure(Point point) {
        return field[point.x][point.y];
    }

    public void setFigure(final Point point, final Figure figure) {
        field[point.x][point.y] = figure;
    }

    public boolean checkValidPoint(Point point) {
        return false;
    }



}
