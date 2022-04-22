import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import cs2030.simulator.Simulate2;

class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> arrivalTimes;

        int numOfServers = sc.nextInt();
        arrivalTimes = sc.tokens().map(x -> Double.parseDouble(x)).
            collect(Collectors.toUnmodifiableList());

        Simulate2 sim = new Simulate2(numOfServers, arrivalTimes);
        System.out.println(sim.run());
    }
}
