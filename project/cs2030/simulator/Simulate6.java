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
Test case header:
numServers maxQueueLength numCustomers
*/

/**
 * Each server now has a queue of customers to queue up.
 * A customer chooses the first queue available and joins at the tail.
 * When a server is done serving a customer, it serves the next waiting customer
 * at the head
 * of the queue.
 */
public class Simulate6 {
    private final PQ<Event> events;
    private final Shop shop;
    private final int numCustomers;

    public Simulate6(int numOfServers, List<Pair<Double, Supplier<Double>>> inputTimes, int qmax) {
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
    }

    public String run() {
        String result = "";
        PQ<Event> forPoll = events;
        Shop mutableShop = shop;
        while (!forPoll.isEmpty()) {
            Pair<Event, PQ<Event>> polled = forPoll.poll();
            Event event = polled.first();
            result += event.toString() + "\n";
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
                ImList<Server> servers = mutableShop.getServers();
                int index = ((Done) currEvent).getServerID() - 1;
                Server updatedServer = servers.get(index)
                        .serveWaitingCustomer(currEvent.getEventTime());
                mutableShop = mutableShop.updateShop(updatedServer);
                if (!updatedServer.isFree()) {
                    Customer newServingCustomer = updatedServer.getLastServed();
                    forPoll = forPoll.add(
                            new Serve(newServingCustomer, currEvent.getEventTime(), updatedServer));
                }
            }
        }
        return result + new Statistic(mutableShop, numCustomers);
    }
}
