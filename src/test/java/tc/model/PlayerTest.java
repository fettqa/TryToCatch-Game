package tc.model;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @org.junit.jupiter.api.Test
    void getName() {
        final String testName = "FirstPlayer";
        final String expectedName = testName;
        final Player player = new Player(testName, null);
        final String actualName = player.getName();
        assertEquals(expectedName,actualName);
    }

    @org.junit.jupiter.api.Test
    void getFigure() {
        final Figure testFigure = Figure.firstPlayer.FIRST_FIGURE;
        final Figure expectedFigure = testFigure;
        final Player player = new Player(null, testFigure);
        final Figure actualFigure = player.getFigure();
        assertEquals(expectedFigure,actualFigure);
    }
}