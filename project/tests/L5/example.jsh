import cs2030.util.Pair
Scanner sc = new Scanner(System.in)
List<Pair<Double, Supplier<Double>>> list = Stream.<Scanner>generate(() -> sc).
limit(3).
map(x -> Pair.<Double, Supplier<Double>>of(sc.nextDouble(), () -> sc.nextDouble())).
collect(Collectors.toList())
0.5
0.6
0.7
list.get(0)
list.get(1)
list.get(2)
list.get(0).first()
list.get(1).first()
list.get(2).first()
list.get(1).second()
list.get(1).second().get()
1.0
list.get(1).second().get() // same test
2.0
