package tc.model;

public interface Figure {

    public enum firstPlayer implements Figure {
        FIRST_FIGURE("W"),
        SECOND_FIGURE("V"),
        THIRD_FIGURE("M");

        firstPlayer(String w) {
        }
    }
    public enum secondPlayer implements Figure {
        FIGURE("*");
        secondPlayer(String w) {
        }
    }
}
