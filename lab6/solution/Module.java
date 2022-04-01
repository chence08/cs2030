class Module extends KeyableMap<Assessment> {
    Module(String name) {
        super(name);
    }
    
    Module(String name, ImmutableMap<String, Assessment> map) {
        super(name, map);
    }

    Module put(Assessment assessment) {
        return new Module(super.getKey(),
                          super.getMap().put(assessment.getKey(), assessment));
    }
}
