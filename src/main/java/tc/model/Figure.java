package tc.model;

public interface Figure {

    enum FirstPlayer implements Figure {
        FIRST_FIGURE("W"),
        SECOND_FIGURE("V"),
        THIRD_FIGURE("M");

        FirstPlayer(String w) {
        }
    }
    enum SecondPlayer implements Figure {
        FIGURE("*");
        SecondPlayer(String w) {
        }
    }

    enum AngleOfField implements Figure {
        ANGLE(" ");
        AngleOfField(String s) {
        }
    }
}
