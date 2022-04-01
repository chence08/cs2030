Main.gameOfLife(List.of(0,0,0,0,1,0,0,0,0), Main.generateRule(), 4).
   forEach(System.out::println)
int[] arr = new int[63]
arr[31] = 1
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList())
Main.gameOfLife(list, Main.generateRule(), 32).forEach(System.out::println)
