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
        if(MoveController.firstPlayerLocationOf1stFigure.y < MoveController.secondPlayerLocation.y ||
        MoveController.firstPlayerLocationOf2ndFigure.y < MoveController.secondPlayerLocation.y ||
        MoveController.firstPlayerLocationOf3rdFigure.y < MoveController.secondPlayerLocation.y) {
            return true;
        }
        try{
            field.setFigure(new Point(MoveController.secondPlayerLocation.x,MoveController.secondPlayerLocation.y + 1),PlayerTwoFigure,Direction.DOWN);
            return true;
        } catch (AlreadyOccupiedException | InvalidPointException e){
            try {
                field.setFigure(new Point(MoveController.secondPlayerLocation.x,MoveController.secondPlayerLocation.y - 1),PlayerTwoFigure,Direction.UP);
                return true;
            } catch (AlreadyOccupiedException | InvalidPointException r) {
                try {
                    field.setFigure(new Point(MoveController.secondPlayerLocation.x + 1,MoveController.secondPlayerLocation.y),PlayerTwoFigure, Direction.RIGHT);
                    return true;
                }catch (AlreadyOccupiedException | InvalidPointException t) {
                    try{
                        field.setFigure(new Point(MoveController.secondPlayerLocation.x - 1,MoveController.secondPlayerLocation.y),PlayerTwoFigure, Direction.LEFT);
                        return true;
                    }catch (AlreadyOccupiedException | InvalidPointException y){
                        return false;
                    }
                }
            }
        }
    }
}
