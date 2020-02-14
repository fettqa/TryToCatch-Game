package tc.model;

import tc.model.exceptions.AlreadyOccupiedException;
import tc.model.exceptions.InvalidPointException;

import java.awt.*;

public class Field {

    private static final int LEFT_COLUMN_LIMIT = 0;
    private static final int RIGHT_COLUMN_LIMIT = 2;
    private static final int UPPER_ROW_LIMIT = 0;
    private static final int BOTTOM_ROW_LIMIT = 4;
    private static final int MIDDLE_COLUMN = 1;
    private static final int FIELD_WIDTH = RIGHT_COLUMN_LIMIT + 1;
    private static final int FIELD_HEIGHT = BOTTOM_ROW_LIMIT + 1;

    private final Figure[][] field = new Figure[FIELD_WIDTH][FIELD_HEIGHT];

    public Field() {
        field[1][2] = Figure.secondPlayer.FIGURE;
        field[0][3] = Figure.firstPlayer.FIRST_FIGURE;
        field[1][4] = Figure.firstPlayer.SECOND_FIGURE;
        field[2][3] = Figure.firstPlayer.THIRD_FIGURE;
    }

    public int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public int getFieldHeight() {
        return FIELD_HEIGHT;
    }

    public Figure getFigure(Point point) throws InvalidPointException {

        if(checkValidPoint(point)) {
            throw new InvalidPointException();
        }
        return field[point.x][point.y];
    }

    public void setFigure(final Point point,
                          final Figure figure) throws InvalidPointException,AlreadyOccupiedException {
        if(checkValidPoint(point)) {
            throw new InvalidPointException();
        }
        else if(field[point.x][point.y] != null) {
            throw new AlreadyOccupiedException();
        }
        else if((point.x == LEFT_COLUMN_LIMIT || point.x == RIGHT_COLUMN_LIMIT)) {
            if (point.y == UPPER_ROW_LIMIT) {
                isUpperOrBottomEdge(figure,UPPER_ROW_LIMIT );
            }
            if (point.y == BOTTOM_ROW_LIMIT) {
                isUpperOrBottomEdge(figure,BOTTOM_ROW_LIMIT );
            }
        }
        field[point.x][point.y] = figure;
    }

    public void deleteFigure(final Point point) throws InvalidPointException {
        if(checkValidPoint(point)) {
            throw new InvalidPointException();
        }
        else field[point.x][point.y] = null;
    }

    private void isUpperOrBottomEdge(final Figure figure,final int row ) throws AlreadyOccupiedException {
                if (field[MIDDLE_COLUMN][row] != null) {
                    throw new AlreadyOccupiedException();
                }
                field[MIDDLE_COLUMN][row] = figure;
            }

    private boolean checkValidPoint(final Point point) {
        return !checkRightAndLeftEdges(point.x) || !checkTopAndBottomEdges(point.y);
    }

    private boolean checkTopAndBottomEdges (final int coordinate) {
        return coordinate >= UPPER_ROW_LIMIT && coordinate <= BOTTOM_ROW_LIMIT;
    }

    private boolean checkRightAndLeftEdges(final int coordinate) {
        return coordinate >= LEFT_COLUMN_LIMIT && coordinate <= RIGHT_COLUMN_LIMIT;
    }

}
