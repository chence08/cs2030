class Assessment implements Keyable {
    private final String key;
    private final String grade;

    Assessment(String key, String grade) {
        this.key = key;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("{%s: %s}", key, grade);
    }

    @Override
    public String getKey() {
        return key;
    }

    String getGrade() {
        return grade;
    }
}
