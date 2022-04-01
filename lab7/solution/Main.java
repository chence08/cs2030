import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.function.UnaryOperator;

class Main {
    
    // Task 1: Twin Primes
    /** 
     * @param n greater or equal to 3
     * @return IntStream
     */
    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(3, n)
               .filter(x -> isPrime(x))
               .filter(x -> isPrime(x + 2) || isPrime(x - 2));
    }

    static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(number))
               .noneMatch(x -> number % x == 0);
    }

    // Task 2: Reverse String
    static String reverse(String str) {
        return Stream.<String>of(str.split(""))
               .reduce("", (x, y) -> y + x);
    }

    /* 
    Task 3: Counting Repeats
    Definition of a new Repeat:
    1. current number different from the previous number
    2. current number same as the next number
    */

    /** 
     * Common solution 🤨
     * 1. Check if current and next number are same;
     * 2. If x + 2 is out of bounds OR x + 2 is a different number;
     * Then considered valid repeat. Filter then count.
     */
    static long countRepeats2(List<Integer> list) {
        return IntStream.range(0, list.size() - 1)
               .filter(x -> list.get(x).equals(x + 1)
                            && (
                                x + 2 >= list.size()
                                || !list.get(x).equals(list.get(x + 2))
                               )
               ).count();
    }

    // My old solution
    static long countRepeats(List<Integer> list) {
        int zeroIndex = Optional.of(1)
                        .filter(one -> list.get(0).equals(list.get(1)))
                        .orElse(0);
        return zeroIndex + IntStream.range(1, list.size() - 1)
                           .map(idx -> hasRepeats(idx, list))
                           .sum();
    } 

    static int hasRepeats(int idx, List<Integer> list) {
        int previous = list.get(idx - 1);
        int current = list.get(idx);
        int after = list.get(idx + 1);
        return Optional.of(1)
               .filter(one -> previous != current && current == after)
               .orElse(0);
    }

    // Task 4: One-Dimensional Game of Life
    static UnaryOperator<List<Integer>> generateRule() {
        return list -> IntStream.range(0, list.size())
                       .mapToObj(idx -> updateCell(list, idx))
                       .collect(Collectors.toList());
    }

    static int updateCell(List<Integer> list, int idx) {
        int current = list.get(idx);
        if (current == 1) {
            return 0;
        } else {
            int left = list.get(Math.max(0, idx - 1));
            int right = list.get(Math.min(list.size() - 1, idx + 1));
            if (left == 1 ^ right == 1) {
                return 1;
            }
            return 0;
        }
    }

    static Stream<String> gameOfLife(List<Integer> list, UnaryOperator<List<Integer>> rule, int n) {
        return Stream.iterate(list, rule)
               .limit(n)
               .map(x -> toAsterik(x));
    }

    static String toAsterik(List<Integer> list) {
        return list.stream().map(x -> x == 0 ? " " : "*").collect(Collectors.joining());
    }
}
