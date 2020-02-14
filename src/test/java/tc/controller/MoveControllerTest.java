package tc.controller;

import org.junit.jupiter.api.Test;
import tc.model.Direction;
import tc.model.Field;
import tc.model.Figure;
import tc.model.exceptions.InvalidMoveDirection;
import tc.model.exceptions.InvalidPointException;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MoveControllerTest {

    @Test
    void moveFigureFirstPlayerTurn() throws InvalidPointException, InvalidMoveDirection {
        final Field field = new Field();
        final Point currentPoint = new Point(0,3);
        final Figure currentFigure = field.getFigure(currentPoint);
        final MoveController moveController = new MoveController();
        moveController.moveFigure(field,currentFigure, currentPoint, Direction.UP);

        final Point expectedPoint = new Point(0,2);
        final Figure expectedFigure = field.getFigure(expectedPoint);

        assertEquals(currentFigure,expectedFigure);
    }
}