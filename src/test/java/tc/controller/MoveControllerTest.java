package tc.controller;

import org.junit.jupiter.api.Test;
import tc.model.Direction;
import tc.model.Field;
import tc.model.Figure;
import tc.model.exceptions.AlreadyOccupiedException;
import tc.model.exceptions.InvalidMoveDirection;
import tc.model.exceptions.InvalidPointException;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MoveControllerTest {

    @Test
    void moveUpFigureFirstPlayer() throws InvalidPointException, InvalidMoveDirection, AlreadyOccupiedException {
        final Field field = new Field();
        final Point currentPoint = new Point(0,3);
        final Figure currentFigure = field.getFigure(currentPoint);
        final MoveController moveController = new MoveController();
        moveController.moveFigure(field,currentFigure, currentPoint, Direction.UP);

        final Point expectedPoint = new Point(0,2);
        final Figure expectedFigure = field.getFigure(expectedPoint);

        assertEquals(currentFigure,expectedFigure);
    }
    @Test
    void moveDownFigureFirstPlayer() throws InvalidPointException {
        final Field field = new Field();
        final Point currentPoint = new Point(0,3);
        final Figure currentFigure = field.getFigure(currentPoint);
        final MoveController moveController = new MoveController();

        try {
            moveController.moveFigure(field,currentFigure, currentPoint, Direction.DOWN);
            fail();
        } catch (InvalidMoveDirection | AlreadyOccupiedException | InvalidPointException e) {
        }
        assertEquals(currentFigure,field.getFigure(currentPoint));
    }

    @Test
    void moveRightFigureFirstPlayer() throws InvalidPointException, InvalidMoveDirection, AlreadyOccupiedException {
        final Field field = new Field();
        final Point currentPoint = new Point(0,3);
        final Figure currentFigure = field.getFigure(currentPoint);
        final MoveController moveController = new MoveController();
        moveController.moveFigure(field,currentFigure, currentPoint, Direction.RIGHT);

        final Point expectedPoint = new Point(1,3);
        final Figure expectedFigure = field.getFigure(expectedPoint);

        assertEquals(currentFigure,expectedFigure);
        assertNull(field.getFigure(currentPoint));
    }

    @Test
    void moveLeftFigureFirstPlayer() throws InvalidPointException, InvalidMoveDirection, AlreadyOccupiedException {
        final Field field = new Field();
        final Point currentPoint = new Point(2,3);
        final Figure currentFigure = field.getFigure(currentPoint);
        final MoveController moveController = new MoveController();
        moveController.moveFigure(field,currentFigure, currentPoint, Direction.LEFT);

        final Point expectedPoint = new Point(1,3);
        final Figure expectedFigure = field.getFigure(expectedPoint);

        assertEquals(currentFigure,expectedFigure);
        assertNull(field.getFigure(currentPoint));
    }

    @Test
    void moveLeftFigureFirstPlayerToInvalidPoint() throws InvalidPointException, InvalidMoveDirection, AlreadyOccupiedException {
        final Field field = new Field();
        final Point currentPoint = new Point(0,3);
        final Figure currentFigure = field.getFigure(currentPoint);
        final MoveController moveController = new MoveController();
        try {
            moveController.moveFigure(field,currentFigure, currentPoint, Direction.LEFT);
            fail();
        }catch (InvalidPointException e){}
        assertEquals(currentFigure,field.getFigure(currentPoint));
    }

    @Test
    void moveRightFigureFirstPlayerWhenOccupied() throws InvalidPointException, InvalidMoveDirection, AlreadyOccupiedException {
        final Field field = new Field();
        final Point firstFigurePoint = new Point(0,3);
        final Point secondFigurePoint = new Point(1,4);
        final Figure firstCurrentFigure = field.getFigure(firstFigurePoint);
        final Figure secondCurrentFigure = field.getFigure(secondFigurePoint);
        final MoveController moveController = new MoveController();
        moveController.moveFigure(field,secondCurrentFigure, secondFigurePoint, Direction.UP);
        try {
            moveController.moveFigure(field, firstCurrentFigure, firstFigurePoint, Direction.RIGHT);
            fail();
        }catch (AlreadyOccupiedException e) {}
        assertEquals(firstCurrentFigure, field.getFigure(firstFigurePoint));
        assertNull(field.getFigure(secondFigurePoint));
    }

    @Test
    void moveDownFigureSecondPlayer() throws InvalidPointException, InvalidMoveDirection, AlreadyOccupiedException {
        final Field field = new Field();
        final Point currentPoint = new Point(1,2);
        final Figure currentFigure = field.getFigure(currentPoint);
        final MoveController moveController = new MoveController();
        moveController.moveFigure(field,currentFigure, currentPoint, Direction.DOWN);

        final Point expectedPoint = new Point(1,3);
        final Figure expectedFigure = field.getFigure(expectedPoint);

        assertEquals(currentFigure,expectedFigure);
    }

    @Test
    void moveUpFigureSecondPlayer() throws InvalidPointException, InvalidMoveDirection, AlreadyOccupiedException {
        final Field field = new Field();
        final Point currentPoint = new Point(1,2);
        final Figure currentFigure = field.getFigure(currentPoint);
        final MoveController moveController = new MoveController();
        moveController.moveFigure(field,currentFigure, currentPoint, Direction.UP);

        final Point expectedPoint = new Point(1,1);
        final Figure expectedFigure = field.getFigure(expectedPoint);

        assertEquals(currentFigure,expectedFigure);
    }

    @Test
    void moveLeftFigureSecondPlayer() throws InvalidPointException, InvalidMoveDirection, AlreadyOccupiedException {
        final Field field = new Field();
        final Point currentPoint = new Point(1,2);
        final Figure currentFigure = field.getFigure(currentPoint);
        final MoveController moveController = new MoveController();
        moveController.moveFigure(field,currentFigure, currentPoint, Direction.LEFT);

        final Point expectedPoint = new Point(0,2);
        final Figure expectedFigure = field.getFigure(expectedPoint);

        assertEquals(currentFigure,expectedFigure);
    }
    @Test
    void moveRightFigureSecondPlayer() throws InvalidPointException, InvalidMoveDirection, AlreadyOccupiedException {
        final Field field = new Field();
        final Point currentPoint = new Point(1,2);
        final Figure currentFigure = field.getFigure(currentPoint);
        final MoveController moveController = new MoveController();
        moveController.moveFigure(field,currentFigure, currentPoint, Direction.RIGHT);

        final Point expectedPoint = new Point(2,2);
        final Figure expectedFigure = field.getFigure(expectedPoint);

        assertEquals(currentFigure,expectedFigure);
    }
}


