package cs2030.simulator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cs2030.util.PQ;
import cs2030.util.Pair;

public class Simulate2 {
    private final PQ<EventStub> events;
    private final Shop shop;

    /**
     * Construct a priority queue of EventStub events.
     * @param numOfServers number of shops
     * @param arrivalTimes use the arrival as the event time
     */
    public Simulate2(int numOfServers, List<Double> arrivalTimes) {
        PQ<EventStub> events = new PQ<>(new EventComparator());
        int customerCount = 1;
        for (double arrivalTime: arrivalTimes) {
            Customer customer = new Customer(customerCount++, arrivalTime);
            events = events.add(new EventStub(customer, arrivalTime));
        }
        this.events = events;
        List<Server> serverList = IntStream.rangeClosed(1, numOfServers)
                                           .boxed()
                                           .map(ID -> new Server(ID))
                                           .collect(Collectors.toList());
        shop = new Shop(serverList);
    }

    @Override
    public String toString() {
        return String.format("Queue: %s; Shop: %s", events, shop);
    }

    public String run() {
        String result = "";
        PQ<EventStub> forPoll = events;
        while (!forPoll.isEmpty()) {
            Pair<EventStub, PQ<EventStub>> polled = forPoll.poll();
            result += polled.first().toString() + "\n";
            forPoll = polled.second();
        }
        return result + "-- End of Simulation --";
    }
}
