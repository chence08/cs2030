class P2 implements Protocol {
    private static final int ISOLATION_PERIOD = 3;
    private static final int MAX_VACCINATED = 7;
    private static final int MAX_UNVACCINATED = 14;

    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (numOfDays <= ISOLATION_PERIOD) {
            return this;
        } else if (!test.isPositive() ||
                   (person.isVaccinated() && numOfDays > MAX_VACCINATED) ||
                   (!person.isVaccinated() && numOfDays > MAX_UNVACCINATED)) {
            return new Discharged("complete");
        }
        return this;
    }

    @Override
    public String toString() {
        return "P2";
    }
}
