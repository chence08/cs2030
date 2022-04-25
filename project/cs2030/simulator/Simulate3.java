package cs2030.simulator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cs2030.util.ImList;
import cs2030.util.PQ;
import cs2030.util.Pair;

/*
ARRIVE event at time t.
- execute method takes in a shop with all servers free
- shop will return a serve event (to be served by 1 at time t)

SERVE event at time t at i.
- returns a DONE event at time t + 1.0 (assuming service time of 1.0)
- together with the updated shop with server i tagged as busy.
*/

/**
 * For each event, you will need to define an appropriate execute method that takes
 * in the current state of the shop, and return the appropriate next event,
 * as well as the updated shop as a pair of values.
 */
public class Simulate3 {
    private final PQ<Event> events;
    private final Shop shop;

    /**
     * @param arrivalTimes assumed to be in chronological order.
     */
    public Simulate3(int numOfServers, List<Double> arrivalTimes) {
        List<Server> serverList = IntStream.rangeClosed(1, numOfServers)
                                           .boxed()
                                           .map(ID -> new Server(ID))
                                           .collect(Collectors.toList());
        Shop shop = new Shop(serverList);
        PQ<Event> events = new PQ<>(new EventComparator());
        int customerCount = 1;
        for (double arrivalTime: arrivalTimes) {
            Customer customer = new Customer(customerCount++, arrivalTime);
            events = events.add(new Arrive(customer));
        }
        this.events = events;
        this.shop = shop;
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
        return result + "-- End of Simulation --";
    }

    PQ<Event> getEvents() {
        return events;
    }

    Shop getShop() {
        return shop;
    }
}
