package tc.view;

import tc.model.Field;
import tc.model.Figure;
import tc.model.Game;
import tc.model.exceptions.InvalidPointException;

import java.awt.*;

public class ConsoleView {

    public void show(final Game game) {
        System.out.format("Game name: %s\n", game.getName());
        final Field field = game.getField();
        for(int y = 0; y < field.getFieldHeight(); y++){
            printLine(field,y);
        }
    }

    private void printLine(final Field field,final int y)  {
        System.out.print("  ");
        for(int x = 0; x < field.getFieldWidth(); x++){
            final Figure figure;
            try {
                figure = field.getFigure(new Point(x,y));
            }catch (final InvalidPointException e){
                e.printStackTrace();
                throw new RuntimeException();
            }
            System.out.print(figure != null ? figure.getFigure() : "[ ]");
            System.out.print("  ");
        }
        System.out.println();
    }
}
