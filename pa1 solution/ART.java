class ART extends Test {
    private final String result;

    ART(String result) {
        super("A");
        this.result = result;
    }

    @Override
    public boolean isValid() {
        return !result.equals("T");
    }

    @Override
    public boolean isPositive() {
        return result.equals("CT");
    }
}
