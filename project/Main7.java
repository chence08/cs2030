import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Supplier;
import java.util.Random;
import cs2030.util.Pair;
import cs2030.simulator.Simulate7;

class Main7 {
    private static final Random rngRest = new Random(3);
    private static final Random rngRestPeriod = new Random(4);

    static double genRestPeriod() {
        return -Math.log(rngRestPeriod.nextDouble()) / 0.1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Pair<Double, Supplier<Double>>> inputTimes;

        int numOfServers = sc.nextInt();
        int qmax = sc.nextInt();
        int numOfCustomers = sc.nextInt();
        double probRest = sc.nextDouble();

        inputTimes = Stream.<Pair<Double, Supplier<Double>>>generate(() -> 
                Pair.of(sc.nextDouble(), () -> sc.nextDouble()))
            .limit(numOfCustomers)
            .collect(Collectors.toUnmodifiableList());

        Supplier<Double> restTimes = () ->
            rngRest.nextDouble() < probRest ? genRestPeriod() : 0.0;

        Simulate7 sim = new Simulate7(numOfServers, inputTimes, qmax, restTimes);
        System.out.println(sim.run());
    }
}
