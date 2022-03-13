class Discharged implements Protocol {
    private final String message;

    Discharged(String message) {
        this.message = message;
    }

    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isPositive()) {
            return new Discharged("seek medical attention");
        }
        return this;
    }

    @Override
    public String toString() {
        return "Discharged: " + message;
    }
}
