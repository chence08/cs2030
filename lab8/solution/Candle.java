import java.util.List;

class Candle implements Thing {
    private static final ImList<String> stages =
    ImList.of(List.of("Candle flickers.",
                      "Candle is getting shorter.",
                      "Candle is about to burn out.",
                      "Candle has burned out."));
    private final int currState;

    Candle(int currState) {
        this.currState = currState;
    }

    Candle() {
        this(0);
    }

    @Override
    public String toString() {
        return stages.get(Math.min(stages.size() - 1, currState));
    }

    @Override
    public String identify() {
        return "Candle";
    }

    @Override
    public Thing update() {
        return new Candle(currState + 1);
    }
}
