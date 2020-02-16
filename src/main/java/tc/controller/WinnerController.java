package tc.controller;

import tc.model.Direction;
import tc.model.Field;
import tc.model.Figure;
import tc.model.exceptions.AlreadyOccupiedException;
import tc.model.exceptions.InvalidPointException;

import java.awt.*;

public class WinnerController {

    public boolean winnerIsSecondPlayer(Field field) {
        Figure PlayerTwoFigure = Figure.SecondPlayer.FIGURE;
        if (MoveController.firstPlayerLocationOf1stFigure.y < MoveController.secondPlayerLocation.y &&
                MoveController.firstPlayerLocationOf2ndFigure.y < MoveController.secondPlayerLocation.y &&
                MoveController.firstPlayerLocationOf3rdFigure.y < MoveController.secondPlayerLocation.y) {
            return false;
        }
        try {
            if (field.getFigure(new Point(MoveController.secondPlayerLocation.x, MoveController.secondPlayerLocation.y + 1)) != null &&
                    field.getFigure(new Point(MoveController.secondPlayerLocation.x, MoveController.secondPlayerLocation.y - 1)) != null &&
                    field.getFigure(new Point(MoveController.secondPlayerLocation.x + 1, MoveController.secondPlayerLocation.y)) != null &&
                    field.getFigure(new Point(MoveController.secondPlayerLocation.x - 1, MoveController.secondPlayerLocation.y)) != null) {
            }
            return true;
        } catch (InvalidPointException e) {
            try {
                if (field.getFigure(new Point(MoveController.secondPlayerLocation.x, MoveController.secondPlayerLocation.y - 1)) != null &&
                        field.getFigure(new Point(MoveController.secondPlayerLocation.x + 1, MoveController.secondPlayerLocation.y)) != null &&
                        field.getFigure(new Point(MoveController.secondPlayerLocation.x - 1, MoveController.secondPlayerLocation.y)) != null)
                    return true;
            } catch (InvalidPointException r) {
                try {
                    if (field.getFigure(new Point(MoveController.secondPlayerLocation.x + 1, MoveController.secondPlayerLocation.y)) != null &&
                            field.getFigure(new Point(MoveController.secondPlayerLocation.x - 1, MoveController.secondPlayerLocation.y)) != null)
                        return true;
                } catch (InvalidPointException t) {
                    try {
                        if (field.getFigure(new Point(MoveController.secondPlayerLocation.x - 1, MoveController.secondPlayerLocation.y)) != null)
                            return true;
                    } catch (InvalidPointException y) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
