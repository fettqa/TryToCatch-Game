package tc.model;

import tc.controller.MoveController;
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
        field[1][2] = Figure.SecondPlayer.FIGURE;
        MoveController.secondPlayerLocation.x = 1;
        MoveController.secondPlayerLocation.y = 2;
        field[0][3] = Figure.FirstPlayer.FIRST_FIGURE;
        MoveController.firstPlayerLocationOf1stFigure.x = 0;
        MoveController.firstPlayerLocationOf1stFigure.y = 3;
        field[1][4] = Figure.FirstPlayer.SECOND_FIGURE;
        MoveController.firstPlayerLocationOf2ndFigure.x = 1;
        MoveController.firstPlayerLocationOf2ndFigure.y = 4;
        field[2][3] = Figure.FirstPlayer.THIRD_FIGURE;
        MoveController.firstPlayerLocationOf3rdFigure.x = 2;
        MoveController.firstPlayerLocationOf3rdFigure.y = 3;
        field[0][0] = Figure.AngleOfField.ANGLE;
        field[2][0] = Figure.AngleOfField.ANGLE;
        field[0][4] = Figure.AngleOfField.ANGLE;
        field[2][4] = Figure.AngleOfField.ANGLE;
    }

    public int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public int getFieldHeight() {
        return FIELD_HEIGHT;
    }

    public Figure getFigure(Point point) throws InvalidPointException {

        if(!checkTopAndBottomEdges(point.y) || !checkRightAndLeftEdges(point.x) ) {
            throw new InvalidPointException();
        }
        return field[point.x][point.y];
    }

    public void setFigure(final Point point,
                          final Figure figure,
                          final Direction direction) throws InvalidPointException,AlreadyOccupiedException {
        if(checkValidPoint(point)) {
            throw new InvalidPointException();
        }
        if (Figure.AngleOfField.ANGLE.equals(getFigure(point))){
                if (Direction.UP.equals(direction)) {
                    isUpperOrBottomEdge(figure, UPPER_ROW_LIMIT);
                    return;
                }
                if (Direction.DOWN.equals(direction)) {
                    isUpperOrBottomEdge(figure, BOTTOM_ROW_LIMIT);
                    return;
                }
                throw new InvalidPointException();
        }
        if(field[point.x][point.y] != null) {
            throw new AlreadyOccupiedException();
        }
        field[point.x][point.y] = figure;
    }

    public void setFigure(final Point point,
                          final Figure figure) throws InvalidPointException,AlreadyOccupiedException {
        setFigure(point, figure, null);
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

    private boolean checkValidPoint(final Point point) throws InvalidPointException {
        return (!checkRightAndLeftEdges(point.x) || !checkTopAndBottomEdges(point.y)) &&
                !Figure.AngleOfField.ANGLE.equals(getFigure(point));
    }

    private boolean checkTopAndBottomEdges (final int coordinate) {
        return coordinate >= UPPER_ROW_LIMIT && coordinate <= BOTTOM_ROW_LIMIT;
    }

    private boolean checkRightAndLeftEdges(final int coordinate) {
        return coordinate >= LEFT_COLUMN_LIMIT && coordinate <= RIGHT_COLUMN_LIMIT;
    }

}
