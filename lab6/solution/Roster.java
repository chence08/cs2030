class Roster extends KeyableMap<Student> {
    Roster(String name) {
        super(name);
    }
    
    Roster(String name, ImmutableMap<String, Student> map) {
        super(name, map);
    }

    Roster put(Student student) {
        return new Roster(super.getKey(),
                          super.getMap().put(student.getKey(), student));
    }

    String getGrade(String studentId, String moduleCode, String assessmentTitle) {
        return get(studentId)
               .flatMap(x -> x.get(moduleCode))
               .flatMap(x -> x.get(assessmentTitle))
               .map(x -> x.getGrade())
               .orElse(String.format("No such record: %s %s %s",
                                    studentId, moduleCode, assessmentTitle));
    }

    Roster add(String studentId, String moduleCode, String assessmentTitle, String grade) {
        // Need to send the assessment to the correct module and student!
        // Order matters! Inside out!
        Assessment assessment = new Assessment(assessmentTitle, grade);
        Module module = get(studentId)
                        .flatMap(x -> x.get(moduleCode))
                        .orElse(new Module(moduleCode))
                        .put(assessment);
        Student student = get(studentId)
                          .orElse(new Student(studentId))
                          .put(module);
        return put(student);
    }
}
