package tc.controller;

import tc.model.Direction;
import tc.model.Field;
import tc.model.Figure;
import tc.model.exceptions.AlreadyOccupiedException;
import tc.model.exceptions.InvalidMoveDirection;
import tc.model.exceptions.InvalidPointException;

import java.awt.*;

public class MoveController {

    public static int ROW_OF_SECOND_PLAYER_LOCATION;

    public static int ROW_OF_FIRST_PLAYER_LOCATION_OF_FIRST_FIGURE;

    public static int ROW_OF_FIRST_PLAYER_LOCATION_OF_SECOND_FIGURE;

    public static int ROW_OF_FIRST_PLAYER_LOCATION_OF_THIRD_FIGURE;

    public void moveFigure(final Field field,
                           final Figure figure,
                           final Point point,
                           final Direction direction) throws    InvalidMoveDirection,
                                                                AlreadyOccupiedException,
                                                                InvalidPointException {
        if(Direction.DOWN.equals(direction)){
            if (Figure.firstPlayer.FIRST_FIGURE.equals(figure) ||
                    Figure.firstPlayer.SECOND_FIGURE.equals(figure) ||
                    Figure.firstPlayer.THIRD_FIGURE.equals(figure)) {
                System.out.println("Игрок 2 не может делать ход вниз");
                throw new InvalidMoveDirection();
            }
            playerTurn(field,figure,point,p -> new Point(p.x,p.y + 1));
        }
        if(Direction.UP.equals(direction)){
            playerTurn(field,figure,point,p -> new Point(p.x,p.y - 1));
        }
        if(Direction.LEFT.equals(direction)){
            playerTurn(field,figure,point,p -> new Point(p.x - 1,p.y));
        }
        if(Direction.RIGHT.equals(direction)){
            playerTurn(field,figure,point,p -> new Point(p.x + 1,p.y));
        }
    }

    private void playerTurn(final Field field,
                           final Figure figure,
                           final Point point,
                           final IDirectionPoint directionPoint) throws AlreadyOccupiedException, InvalidPointException {
            final Point nextPoint = directionPoint.next(point);
            field.setFigure(nextPoint, figure);
            saveFigureLocation(figure, nextPoint.y);
            field.deleteFigure(point);
    }

    private interface IDirectionPoint {
        Point next(final Point point);
    }

    private void saveFigureLocation(final Figure figure, final int numberOfRow) {
        if(Figure.secondPlayer.FIGURE.equals(figure)) {
            ROW_OF_SECOND_PLAYER_LOCATION = numberOfRow;
        }

        if(Figure.firstPlayer.FIRST_FIGURE.equals(figure)) {
            ROW_OF_FIRST_PLAYER_LOCATION_OF_FIRST_FIGURE = numberOfRow;
        }

        if(Figure.firstPlayer.SECOND_FIGURE.equals(figure)) {
            ROW_OF_FIRST_PLAYER_LOCATION_OF_SECOND_FIGURE = numberOfRow;
        }

        if(Figure.firstPlayer.THIRD_FIGURE.equals(figure)) {
            ROW_OF_FIRST_PLAYER_LOCATION_OF_THIRD_FIGURE = numberOfRow;
        }
    }
}
