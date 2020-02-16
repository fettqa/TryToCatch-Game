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

class WinnerControllerTest {

    @Test
    void getWinnerWhenSecondPlayerCanNotMove() throws InvalidPointException, AlreadyOccupiedException, InvalidMoveDirection {
        Field field = new Field();
        MoveController moveController = new MoveController();
        WinnerController winnerController = new WinnerController();
        for (int i = 0; i < 2; i++){
            moveController.moveFigure(field, Figure.SecondPlayer.FIGURE, Direction.UP);
            moveController.moveFigure(field, Figure.FirstPlayer.FIRST_FIGURE, Direction.UP);
            moveController.moveFigure(field, Figure.FirstPlayer.SECOND_FIGURE, Direction.UP);
            moveController.moveFigure(field, Figure.FirstPlayer.THIRD_FIGURE, Direction.UP);
        }
        moveController.moveFigure(field, Figure.FirstPlayer.SECOND_FIGURE, Direction.UP);
        assertFalse(winnerController.winnerIsSecondPlayer(field));
        assertEquals(Figure.SecondPlayer.FIGURE,field.getFigure(new Point(1,0)));
    }

    @Test
    void getWinnerWhenSecondPlayerBelowThanFirstPlayer() throws InvalidPointException, AlreadyOccupiedException, InvalidMoveDirection {
        Field field = new Field();
        MoveController moveController = new MoveController();
        WinnerController winnerController = new WinnerController();
        moveController.moveFigure(field, Figure.FirstPlayer.FIRST_FIGURE, Direction.UP);
        moveController.moveFigure(field, Figure.FirstPlayer.FIRST_FIGURE, Direction.UP);
        moveController.moveFigure(field, Figure.FirstPlayer.SECOND_FIGURE, Direction.UP);
        moveController.moveFigure(field, Figure.FirstPlayer.THIRD_FIGURE, Direction.UP);
        moveController.moveFigure(field, Figure.FirstPlayer.SECOND_FIGURE, Direction.LEFT);
        moveController.moveFigure(field, Figure.FirstPlayer.SECOND_FIGURE, Direction.UP);
        moveController.moveFigure(field, Figure.SecondPlayer.FIGURE, Direction.DOWN);
        assertTrue(winnerController.winnerIsSecondPlayer(field));
    }

    @Test
    void checkCurrentMove() throws InvalidPointException, AlreadyOccupiedException, InvalidMoveDirection {
        Field field = new Field();
        MoveController moveController = new MoveController();
        moveController.moveFigure(field, Figure.FirstPlayer.FIRST_FIGURE, Direction.UP);
        try {
            moveController.moveFigure(field, Figure.FirstPlayer.FIRST_FIGURE, Direction.LEFT);
        }catch (InvalidPointException e){}
        moveController.moveFigure(field, Figure.SecondPlayer.FIGURE, Direction.UP);
        moveController.moveFigure(field, Figure.FirstPlayer.FIRST_FIGURE, Direction.UP);
        moveController.moveFigure(field, Figure.SecondPlayer.FIGURE, Direction.UP);
        assertTrue(MoveController.currentMove());
    }
}