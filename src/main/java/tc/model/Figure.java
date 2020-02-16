package tc.model;

public interface Figure {

    String getFigure();

    enum FirstPlayer implements Figure {
        FIRST_FIGURE("[W]"),
        SECOND_FIGURE("[V]"),
        THIRD_FIGURE("[M]");

        String firstPlayerFigure;

        FirstPlayer(String w) {
            firstPlayerFigure = w;
        }

        @Override
        public String getFigure(){
            return firstPlayerFigure;
        }
    }
    enum SecondPlayer implements Figure {
        FIGURE("[*]");

        String secondPlayerFigure;

        SecondPlayer(String s) {
            secondPlayerFigure = s;
        }

        @Override
        public String getFigure() {
            return secondPlayerFigure;
        }
    }

    enum AngleOfField implements Figure {
        ANGLE("   ");

        String angle;

        AngleOfField(String d) {
            angle = d;
        }

        @Override
        public String getFigure() {
            return angle;
        }
    }
}
