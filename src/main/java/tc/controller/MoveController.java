package tc.controller;

import tc.model.Direction;
import tc.model.Field;
import tc.model.Figure;
import tc.model.exceptions.AlreadyOccupiedException;
import tc.model.exceptions.InvalidMoveDirection;
import tc.model.exceptions.InvalidPointException;

import java.awt.*;

public class MoveController {

    public void moveFigure(final Field field,
                           final Figure figure,
                           final Point point,
                           final Direction direction) throws InvalidMoveDirection {
        if(Direction.UP.equals(direction)){
            playerTurn(field,figure,point,p -> new Point(p.x,p.y + 1));
        }
        if(Direction.DOWN.equals(direction)){
            if (Figure.firstPlayer.FIRST_FIGURE.equals(figure) ||
                    Figure.firstPlayer.SECOND_FIGURE.equals(figure) ||
                    Figure.firstPlayer.THIRD_FIGURE.equals(figure)) {
                System.out.println("Игрок 2 не может делать ход вниз");
                throw new InvalidMoveDirection();
            }
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
                           final IDirectionPoint directionPoint) {
            final Point nextPoint = directionPoint.next(point);
            try {
                field.setFigure(nextPoint, figure);
                field.setFigure(point, null);
            }
            catch (InvalidPointException e) {
                    System.out.println("Нельзя ходить за пределы поля!");
                } catch (AlreadyOccupiedException e) {
                    System.out.println("Ход невозможен, клетка занята!");
                }
            }

        private interface IDirectionPoint {
            Point next(final Point point);
        }


}
