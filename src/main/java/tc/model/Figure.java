package tc.model;

public interface Figure {

    enum firstPlayer implements Figure {
        FIRST_FIGURE("W"),
        SECOND_FIGURE("V"),
        THIRD_FIGURE("M");

        firstPlayer(String w) {
        }
    }
    enum secondPlayer implements Figure {
        FIGURE("*");
        secondPlayer(String w) {
        }
    }
}
