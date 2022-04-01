class Student extends KeyableMap<Module> {
    Student(String name) {
        super(name);
    }

    Student(String name, ImmutableMap<String, Module> map) {
        super(name, map);
    }

    Student put(Module module) {
        return new Student(super.getKey(),
                           super.getMap().put(module.getKey(), module));
    }
}