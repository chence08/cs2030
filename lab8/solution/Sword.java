class Sword implements Thing {
    private static final String stage = "Sword is shimmering.";
    private final boolean taken;

    Sword(boolean taken) {
        this.taken = taken;
    }

    Sword() {
        this(false);
    }

    @Override
    public String toString() {
        return stage;
    }

    @Override
    public String identify() {
        return "Sword";
    }

    @Override
    public Thing update() {
        return this;
    }

    Sword take() {
        return new Sword(true);
    }

    boolean isTaken() {
        return taken;
    }

    Sword drop() {
        return new Sword();
    }
}
