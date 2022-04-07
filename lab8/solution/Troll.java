import java.util.List;

class Troll implements Thing {
    private static final ImList<String> stages =
    ImList.of(List.of("Troll lurks in the shadows.",
                      "Troll is getting hungry.",
                      "Troll is VERY hungry.",
                      "Troll is SUPER HUNGRY and is about to ATTACK!",
                      "Troll attacks!"));
    private final int currState;

    Troll(int currState) {
        this.currState = currState;
    }

    Troll() {
        this(0);
    }

    @Override
    public String toString() {
        return stages.get(Math.min(stages.size() - 1, currState));
    }

    @Override
    public String identify() {
        return "Troll";
    }

    @Override
    public Thing update() {
        return new Troll(currState + 1);
    }

    int getState() {
        return currState;
    }
}
