package cs2030.simulator;

import java.util.Optional;

import cs2030.util.Pair;

public interface Event {
    double getEventTime();

    int getCustomerID();

    String getType();

    /**
     * Makes changes to the state of the shop based on the event, and returns
     * the next event (if any) and updated shop status as a pair of values.
     */
    Pair<Optional<Event>, Shop> execute(Shop shop);
}
