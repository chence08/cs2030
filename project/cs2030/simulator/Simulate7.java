package cs2030.simulator;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cs2030.util.ImList;
import cs2030.util.PQ;
import cs2030.util.Pair;

/*
Test case headers:
numServers maxQueueLength numCustomers restProbability
*/

/**
 * Servers are now allowed to take occassional breaks.
 * When a server finishes serving a customer, there is a chance that the server
 * takes a rest for a certain amount of time. During the break the server DOES
 * NOT SERVE
 * the next waiting customer.
 * 
 * We cannot pre-determine what rest time is accorded to which server at the
 * beginning;
 * it can only be decided during the simulation.
 */
public class Simulate7 {
    private final PQ<Event> events;
    private final Shop shop;
    private final int numCustomers;
    private final Supplier<Double> restTimes;

    public Simulate7(int numOfServers, List<Pair<Double, Supplier<Double>>> inputTimes, int qmax,
            Supplier<Double> restTimes) {
        List<Server> serverList = IntStream.rangeClosed(1, numOfServers)
                .boxed()
                .map(ID -> new Server(ID, qmax))
                .collect(Collectors.toList());
        Shop shop = new Shop(serverList);
        PQ<Event> events = new PQ<>(new EventComparator());
        int customerCount = 0;
        for (Pair<Double, Supplier<Double>> timePair : inputTimes) {
            Customer customer = new Customer(++customerCount, timePair);
            events = events.add(new Arrive(customer));
        }
        this.events = events;
        this.shop = shop;
        numCustomers = customerCount;
        this.restTimes = restTimes;
    }

    public String run() {
        String result = "";
        PQ<Event> forPoll = events;
        Shop mutableShop = shop;
        while (!forPoll.isEmpty()) {
            Pair<Event, PQ<Event>> polled = forPoll.poll();
            Event event = polled.first();
            if (!event.getType().equals("REST")) {
                result += event.toString() + "\n";
            }
            forPoll = polled.second();

            // generate next deterministic event from previous event and push it into PQ.
            Pair<Optional<Event>, Shop> nextPair = event.execute(mutableShop);
            mutableShop = nextPair.second();

            // add new event to PQ
            PQ<Event> tempPQ = new PQ<>(forPoll);
            forPoll = nextPair.first().map(e -> tempPQ.add(e)).orElse(forPoll);
            if (forPoll.isEmpty()) {
                continue;
            }
            // process DONE (out of a need to avoid cyclic dependency with ARRIVE)
            // updateBusyServer
            Event currEvent = forPoll.peek();
            if (currEvent.getType().equals("DONE")) {
                /*
                 * In order to incorporate REST, we need to update the server in the shop such
                 * that:
                 * 1. server is NOT free.
                 */
                double restTime = restTimes.get();
                ImList<Server> servers = mutableShop.getServers();
                int index = ((Done) currEvent).getServerID() - 1;
                Server updatedServer = servers.get(index).rest();
                mutableShop = mutableShop.updateShop(updatedServer);
                forPoll = forPoll.add(new Rest(currEvent.getEventTime() + restTime, updatedServer));
            }
        }
        return result + new Statistic(mutableShop, numCustomers);
    }
}
