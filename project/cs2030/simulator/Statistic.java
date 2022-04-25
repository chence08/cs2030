package cs2030.simulator;

/*
1. averageWaitingTime = totalWaitingTime / numServed
2. numServed
3. numLeft
*/

public class Statistic {
    private final double totalWaitingTime;
    private final int numServed;
    private final int numLeft;

    Statistic(Shop shop, int numCustomers) {
        totalWaitingTime = shop.getTotalWaitingTime();
        numServed = shop.getNumServed();
        numLeft = numCustomers - numServed;
    }

    @Override
    public String toString() {
        return String.format("[%.3f %d %d]",
            totalWaitingTime / numServed,
            numServed, numLeft);
    }
}
