package cs2030.simulator;

import java.util.List;
import java.util.Optional;

import cs2030.util.ImList;
import cs2030.util.PQ;
import cs2030.util.Pair;

/**
 * Include Statistic.
 */
public class Simulate4 extends Simulate3 {
    private final int numCustomers;

    public Simulate4(int numOfServers, List<Double> arrivalTimes) {
        super(numOfServers, arrivalTimes);
        numCustomers = arrivalTimes.size();
    }

    @Override
    public String run() {
        String result = "";
        PQ<Event> forPoll = getEvents();
        Shop mutableShop = getShop();
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
