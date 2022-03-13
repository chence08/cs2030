class ContactCase extends Case {
    ContactCase(Person person, Test test, Case contact) {
        super(person,
              new ImList<Test>().add(test),
              firstProtocol(person, test),
              1,
              contact.getLineage());
    }

    static Protocol firstProtocol(Person person, Test test) {
        if (test.isPositive()) {
            return person.isHighRisk() ? new P1() : new P2();
        }
        return new P3();
    }
}
