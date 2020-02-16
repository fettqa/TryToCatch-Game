package tc.model;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @org.junit.jupiter.api.Test
    void getName() {
        final String testName = "FirstPlayer";
        final String expectedName = testName;
        final Player player = new Player(testName);
        final String actualName = player.getName();
        assertEquals(expectedName, actualName);
    }
}