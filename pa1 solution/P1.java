class P1 implements Protocol {
    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (!test.isPositive()) {
            return new Discharged("follow up with doctor");
        }
        return this;
    }

    @Override
    public String toString() {
        return "P1";
    }
}
