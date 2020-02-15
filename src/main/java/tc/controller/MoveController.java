package tc.controller;

import tc.model.Direction;
import tc.model.Field;
import tc.model.Figure;
import tc.model.exceptions.AlreadyOccupiedException;
import tc.model.exceptions.InvalidMoveDirection;
import tc.model.exceptions.InvalidPointException;

import java.awt.*;

public class MoveController {

    public static Point secondPlayerLocation = new Point();

    public static Point firstPlayerLocationOf1stFigure = new Point();

    public static Point firstPlayerLocationOf2ndFigure = new Point();

    public static Point firstPlayerLocationOf3rdFigure = new Point();

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

    public void moveFigure(final Field field,
                           final Figure figure,
                           final Direction direction) throws    InvalidMoveDirection,
            AlreadyOccupiedException,
            InvalidPointException{
        Point point = new Point();
        if(Figure.firstPlayer.FIRST_FIGURE.equals(figure)) point = MoveController.firstPlayerLocationOf1stFigure;
        if(Figure.firstPlayer.SECOND_FIGURE.equals(figure)) point = MoveController.firstPlayerLocationOf2ndFigure;
        if(Figure.firstPlayer.THIRD_FIGURE.equals(figure)) point = MoveController.firstPlayerLocationOf3rdFigure;
        if(Figure.secondPlayer.FIGURE.equals(figure)) point = MoveController.secondPlayerLocation;
        moveFigure(field,figure,point,direction);
    }

    private void playerTurn(final Field field,
                           final Figure figure,
                           final Point point,
                           final IDirectionPoint directionPoint) throws AlreadyOccupiedException, InvalidPointException {
            final Point nextPoint = directionPoint.next(point);
            field.setFigure(nextPoint, figure);
            saveFigureLocation(figure, nextPoint);
            field.deleteFigure(point);
    }

    private interface IDirectionPoint {
        Point next(final Point point);
    }

    private void saveFigureLocation(final Figure figure, final Point point) {
        if(Figure.secondPlayer.FIGURE.equals(figure)) {
            secondPlayerLocation.y = point.y;
            secondPlayerLocation.x = point.x;
        }

        if(Figure.firstPlayer.FIRST_FIGURE.equals(figure)) {
            firstPlayerLocationOf1stFigure.y = point.y;
            firstPlayerLocationOf1stFigure.x = point.x;
        }

        if(Figure.firstPlayer.SECOND_FIGURE.equals(figure)) {
            firstPlayerLocationOf2ndFigure.y = point.y;
            firstPlayerLocationOf2ndFigure.x = point.x;
        }

        if(Figure.firstPlayer.THIRD_FIGURE.equals(figure)) {
            firstPlayerLocationOf3rdFigure.y = point.y;
            firstPlayerLocationOf3rdFigure.x = point.x;
        }
    }
}
