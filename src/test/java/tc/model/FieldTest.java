package tc.model;

import com.sun.media.sound.FFT;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void getFieldWidth() {
        final Field field = new Field();
        assertEquals(3, field.getFieldWidth());
    }

    @Test
    void getFieldHeight() {
        final Field field = new Field();
        assertEquals(5, field.getFieldHeight());
    }

    @Test
    void setFigure() {
        final Field field = new Field();
        final Point testPoint = new Point(1, 0);
        final Figure testFigure = Figure.P1;

        field.setFigure(testPoint,testFigure);
        final Figure actualFigure = field.getFigure(testPoint);

        assertEquals(testFigure,actualFigure);
    }
}