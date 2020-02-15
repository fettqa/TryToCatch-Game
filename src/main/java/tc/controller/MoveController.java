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

    public static int countOfMoves;

    public boolean currentMove(){
        return MoveController.countOfMoves % 2 == 0;
    }

    public void moveFigure(final Field field,
                           final Figure figure,
                           final Point point,
                           final Direction direction) throws    InvalidMoveDirection,
                                                                AlreadyOccupiedException,
                                                                InvalidPointException {
        if(Direction.DOWN.equals(direction)){
            if (Figure.FirstPlayer.FIRST_FIGURE.equals(figure) ||
                    Figure.FirstPlayer.SECOND_FIGURE.equals(figure) ||
                    Figure.FirstPlayer.THIRD_FIGURE.equals(figure)) {
                System.out.println("Игрок 2 не может делать ход вниз");
                throw new InvalidMoveDirection();
            }
            playerTurn(field,figure,point,p -> new Point(p.x,p.y + 1),direction);
        }
        if(Direction.UP.equals(direction)){
            playerTurn(field,figure,point,p -> new Point(p.x,p.y - 1),direction);
        }
        if(Direction.LEFT.equals(direction)){
            playerTurn(field,figure,point,p -> new Point(p.x - 1,p.y),direction);
        }
        if(Direction.RIGHT.equals(direction)){
            playerTurn(field,figure,point,p -> new Point(p.x + 1,p.y),direction);
        }
    }

    public void moveFigure(final Field field,
                           final Figure figure,
                           final Direction direction) throws    InvalidMoveDirection,
            AlreadyOccupiedException,
            InvalidPointException{
        Point point = new Point();
        if(Figure.FirstPlayer.FIRST_FIGURE.equals(figure)) point = firstPlayerLocationOf1stFigure;
        if(Figure.FirstPlayer.SECOND_FIGURE.equals(figure)) point = firstPlayerLocationOf2ndFigure;
        if(Figure.FirstPlayer.THIRD_FIGURE.equals(figure)) point = firstPlayerLocationOf3rdFigure;
        if(Figure.SecondPlayer.FIGURE.equals(figure)) point = secondPlayerLocation;
        moveFigure(field,figure,point,direction);
    }

    private void playerTurn(final Field field,
                           final Figure figure,
                           final Point point,
                           final IDirectionPoint directionPoint,
                           final Direction direction) throws AlreadyOccupiedException, InvalidPointException {
            final Point nextPoint = directionPoint.next(point);
            field.setFigure(nextPoint, figure, direction);
            saveFigureLocation(figure, nextPoint);
            field.deleteFigure(point);
            countOfMoves += 1;
    }

    private interface IDirectionPoint {
        Point next(final Point point);
    }

    private void saveFigureLocation(final Figure figure, final Point point) {
        if(Figure.FirstPlayer.FIRST_FIGURE.equals(figure)) firstPlayerLocationOf1stFigure = point;
        if(Figure.FirstPlayer.SECOND_FIGURE.equals(figure)) firstPlayerLocationOf2ndFigure = point;
        if(Figure.FirstPlayer.THIRD_FIGURE.equals(figure)) firstPlayerLocationOf3rdFigure = point;
        if(Figure.SecondPlayer.FIGURE.equals(figure)) secondPlayerLocation = point;
    }
}
