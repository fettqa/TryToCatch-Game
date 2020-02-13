package tc.model;

import org.junit.jupiter.api.Test;
import tc.model.exceptions.AlreadyOccupiedException;
import tc.model.exceptions.InvalidPointException;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void getFieldWidth() {
        final Field field = new Field();

        assertEquals(3,field.getFieldWidth());
    }

    @Test
    void getFieldHeight() {
        final Field field = new Field();

        assertEquals(5,field.getFieldHeight());
    }

    @Test
    void setFigure() throws AlreadyOccupiedException, InvalidPointException {
        final Field field = new Field();
        final Point expectedPoint = new Point(2,2);
        final Figure expectedFigure = Figure.P1F1;

        field.setFigure(expectedPoint,expectedFigure);
        final Figure actualFigure = field.getFigure(expectedPoint);

        assertEquals(expectedFigure,actualFigure);
    }

    @Test
    void setFigureWhenPointAlreadyOccupied() throws AlreadyOccupiedException, InvalidPointException {
        final Field field = new Field();
        final Point expectedPoint = new Point(2,2);
        final Figure expectedFigure = Figure.P1F1;
        field.setFigure(expectedPoint,expectedFigure);
        try {
            field.setFigure(expectedPoint,expectedFigure);
            fail();
        }catch (final AlreadyOccupiedException e){}
    }

    @Test
    void setFigureToBottomRow() throws AlreadyOccupiedException, InvalidPointException {
        final Field field = new Field();
        final Point expectedPoint = new Point(0,4);
        final Figure expectedFigure = Figure.P1F1;

        field.setFigure(expectedPoint,expectedFigure);
        final Figure actualFigure = field.getFigure(expectedPoint);

        assertEquals(expectedFigure,actualFigure);
    }

    @Test
    void setLessPointX() {
        final Field field = new Field();
        final Point expectedPoint = new Point(-1,0);
        try {
            field.getFigure(expectedPoint);
            fail();
        }catch (final InvalidPointException e){}
    }

    @Test
    void setMorePointX() {
        final Field field = new Field();
        final Point expectedPoint = new Point(field.getFieldWidth() + 1,0);
        try {
            field.getFigure(expectedPoint);
            fail();
        }catch (final InvalidPointException e){}
    }

    @Test
    void setLessPointY() {
        final Field field = new Field();
        final Point expectedPoint = new Point(0,-1);
        try {
            field.getFigure(expectedPoint);
            fail();
        }catch (final InvalidPointException e){}
    }

    @Test
    void setMorePointY() {
        final Field field = new Field();
        final Point expectedPoint = new Point(-1,field.getFieldHeight() + 1);
        try {
            field.getFigure(expectedPoint);
            fail();
        }catch (final InvalidPointException e){}
    }
}