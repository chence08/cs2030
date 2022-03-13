class Person {
    private final String id;
    private final String status;
    private final int score;
    private static final int MIN_VACCINE = 2;
    private static final int RISK_CUTOFF = 8;

    Person(String id, String status, int score) {
        this.id = id;
        this.status = status;
        this.score = score;
    }

    @Override
    public String toString() {
        String result = String.format("%s/%s/", id, status);
        if (isHighRisk()) {
        return result + "^";
        }
        return result + "v";
    }

    boolean isVaccinated() {
        return status.length() >= MIN_VACCINE;
    }

    boolean isHighRisk() {
        return score >= RISK_CUTOFF;
    }
}
