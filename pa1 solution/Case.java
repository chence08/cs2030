class Case {
    private final Person person;
    private final ImList<Test> history;
    private final Protocol protocol;
    private final int numOfDays; // since first positive
    private final ImList<Case> lineage;

    Case(Person person, PCR pcr) {
        this(person,
             new ImList<Test>().add(pcr),
             firstProtocol(person, pcr),
             pcr.isPositive() ? 1 : 0,
             new ImList<>());
    }

    Case(Person person, ImList<Test> history, Protocol protocol, int numOfDays,
            ImList<Case> lineage) {
        this.person = person;
        this.history = history;
        this.protocol = protocol;
        this.numOfDays = numOfDays;
        this.lineage = lineage;
    }

    static Protocol firstProtocol(Person person, PCR pcr) {
        if (pcr.isPositive()) {
            return person.isHighRisk() ? new P1() : new P2();
        }
        return new Discharged("follow up with doctor");
    }

    Protocol getCurrentProtocol() {
        return protocol;
    }

    ImList<Test> getTestHistory() {
        return history;
    }
    
    Case test(Test newTest) {
        if (!newTest.isValid()) {
            return this;
        }
        Protocol newProtocol = protocol.next(person, newTest, numOfDays + 1);
        return new Case(person, history.add(newTest), newProtocol, numOfDays + 1, lineage);
    }

    ImList<Case> getLineage() {
        return lineage.add(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", person, history, protocol);
    }
}
