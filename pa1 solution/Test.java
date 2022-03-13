abstract class Test {
    private final static String POSITIVE = "+";
    private final static String NEGATIVE = "-";
    private final static String INVALID = "X";
    private final String representation;

    Test(String representation) {
        this.representation = representation;
    }

    abstract boolean isPositive();
    abstract boolean isValid();

    @Override
    public String toString() {
        if (isPositive()) {
            return representation + POSITIVE;
        } else if (isValid()) {
            return representation + NEGATIVE;
        }
        return representation + INVALID;
    }
}
