package tc;

import tc.model.Field;
import tc.model.Game;
import tc.model.Player;
import tc.view.ConsoleView;

public class TryToCatchCLI {

    public static void main(String[] args) {
        final String name1 = "name1";
        final String name2 = "name2";

        final Player[] players = new Player[2];
        players[0] = new Player(name1);
        players[0] = new Player(name2);

        final Game gameTC = new Game(players,new Field(),"TC");

        final ConsoleView consoleView = new ConsoleView();
        consoleView.show(gameTC);
        while (consoleView.move(gameTC)){
            consoleView.show(gameTC);
        }
        System.out.println("Player one win!");
    }
}
