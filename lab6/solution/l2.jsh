new Module("CS2040").put(new Assessment("Lab1", "B")).get("Lab1")
new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B")))
new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))).get("CS2040")
Student natasha = new Student("Natasha");
natasha = natasha.put(new Module("CS2040").put(new Assessment("Lab1", "B")))
natasha.put(new Module("CS2030").put(new Assessment("PE", "A+")).put(new Assessment("Lab2", "C")))
Student tony = new Student("Tony");
tony = tony.put(new Module("CS1231").put(new Assessment("Test", "A-")))
tony.put(new Module("CS2100").put(new Assessment("Test", "B")).put(new Assessment("Lab1", "F")))
new Module("CS1231").put(new Assessment("Test", "A-")) instanceof KeyableMap
new Student("Tony").put(new Module("CS1231")) instanceof KeyableMap
