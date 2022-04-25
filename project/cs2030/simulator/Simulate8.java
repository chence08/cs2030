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
numServers numSelfCheck maxQueueLength numCustomers restProbability

FACT: numSelfCheck <= maxQueueLength
*/

/**
 * SELF-CHECKOUT COUNTERS, basically a special Server
 * <p>
 * <ul>
 * <li>All self-checkout counters share the same queue.
 * <li>Unlike human servers, self-checkout counters do not rest.
 * <li>When we print out the wait event, we always say that the customer is
 * waiting
 * for the self-checkout counter k + 1, even though this customer may eventually
 * be
 * served by another self-checkout counter.
 * </ul>
 * <p>
 */
public class Simulate8 {
    private final PQ<Event> events;
    private final Shop shop;
    private final int numCustomers;
    private final Supplier<Double> restTimes;

    public Simulate8(int numOfServers, int numOfSelfChecks, List<Pair<Double,
        Supplier<Double>>> inputTimes, int qmax, Supplier<Double> restTimes) {
        List<Server> serverList = IntStream.rangeClosed(1, numOfServers)
                .boxed()
                .map(ID -> new Server(ID, qmax))
                .collect(Collectors.toList());
        int firstSelfCheckID = numOfServers + 1;
        List<SelfCheck> selfCheckCounters = IntStream
                .rangeClosed(numOfServers + 1, numOfServers + numOfSelfChecks)
                .boxed()
                .map(ID -> new SelfCheck(ID, qmax, firstSelfCheckID))
                .collect(Collectors.toList());
        Shop shop = new Shop(ImList.of(serverList), ImList.of(selfCheckCounters));
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
                ImList<Server> servers = mutableShop.getServers();
                int index = ((Done) currEvent).getServerID() - 1;
                if (index >= servers.size()) { // is selfCheck
                    ImList<SelfCheck> selfCheckCounters = mutableShop.getSelfCheck();
                    index -= servers.size();
                    SelfCheck updatedSelfCheck = selfCheckCounters.get(index)
                            .serveWaitingCustomer(currEvent.getEventTime());
                    mutableShop = mutableShop.updateShop(updatedSelfCheck);
                    if (!updatedSelfCheck.isFree()) {
                        Customer newServingCustomer = updatedSelfCheck.getLastServed();
                        forPoll = forPoll.add(
                        new Serve(newServingCustomer, currEvent.getEventTime(), updatedSelfCheck));
                    }
                } else {
                    double restTime = restTimes.get();
                    Server updatedServer = servers.get(index).rest();
                    mutableShop = mutableShop.updateShop(updatedServer);
                    forPoll = forPoll.add(
                            new Rest(currEvent.getEventTime() + restTime, updatedServer));
                }
            }
        }
        return result + new Statistic(mutableShop, numCustomers);
    }
}
