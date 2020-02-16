package tc.view;

import tc.controller.MoveController;
import tc.controller.WinnerController;
import tc.model.Direction;
import tc.model.Field;
import tc.model.Figure;
import tc.model.Game;
import tc.model.exceptions.AlreadyOccupiedException;
import tc.model.exceptions.InvalidMoveDirection;
import tc.model.exceptions.InvalidPointException;

import java.awt.*;
import java.util.Scanner;

public class ConsoleView {

    private int numberOfFigure;

    private int numberOfMoveDirection;

    private Figure inputFigure;

    private Direction inputDirection;

    private final MoveController moveController = new MoveController();

    private final WinnerController winnerController = new WinnerController();


    public void show(final Game game) {
        System.out.format("Game name: %s\n", game.getName());
        final Field field = game.getField();
        for (int y = 0; y < field.getFieldHeight(); y++) {
            printLine(field, y);
        }
    }

    private void printLine(final Field field, final int y) {
        System.out.print("  ");
        for (int x = 0; x < field.getFieldWidth(); x++) {
            final Figure figure;
            try {
                figure = field.getFigure(new Point(x, y));
            } catch (final InvalidPointException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
            System.out.print(figure != null ? figure.getFigure() : "[ ]");
            System.out.print("  ");
        }
        System.out.println();
    }

    public boolean move(final Game game) {
        final Field field = game.getField();
        if (MoveController.currentMove()) {
            System.out.println("First player turn");
            System.out.println("Please input number of figure\n 1 - W \n 2 - V \n 3 - M");
            numberOfFigure = figureInput();
            switch (numberOfFigure) {
                case 1:
                    inputFigure = Figure.FirstPlayer.FIRST_FIGURE;
                    break;
                case 2:
                    inputFigure = Figure.FirstPlayer.SECOND_FIGURE;
                    break;
                default:
                    inputFigure = Figure.FirstPlayer.THIRD_FIGURE;
            }
        } else {
            System.out.println("Second player turn");
            System.out.println("Please choose direction \n 1 - UP \n 2 - LEFT \n 3 - RIGHT \n 4 - DOWN");
            inputFigure = Figure.SecondPlayer.FIGURE;
        }
        numberOfMoveDirection = directionInput(MoveController.currentMove());
        switch (numberOfMoveDirection) {
            case 1:
                inputDirection = Direction.UP;
                break;
            case 2:
                inputDirection = Direction.LEFT;
                break;
            case 3:
                inputDirection = Direction.RIGHT;
                break;
            default:
                inputDirection = Direction.DOWN;
        }
        try {
            moveController.moveFigure(field, inputFigure, inputDirection);

        } catch (AlreadyOccupiedException e) {
            System.out.println("Клетка занята!");
            show(game);
            move(game);
        } catch (InvalidMoveDirection e) {
            System.out.println("Первый игрок не может ходить вниз!");
            show(game);
            move(game);
        } catch (InvalidPointException e) {
            System.out.println("Нельзя ходить за пределы поля!");
            show(game);
            move(game);
        }
        return winnerController.winnerIsSecondPlayer(field);
    }

    private int directionInput(Boolean currentMove){
        if (currentMove){
            System.out.println("Please choose direction \n 1 - UP \n 2 - LEFT \n 3 - RIGHT");
        }
        final Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private int figureInput(){
            final Scanner in = new Scanner(System.in);
            return in.nextInt();

    }
}
