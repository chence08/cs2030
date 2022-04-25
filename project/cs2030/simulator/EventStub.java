package cs2030.simulator;

import java.util.Optional;

import cs2030.util.Pair;

public class EventStub implements Event {
    private final double eventTime;

    public EventStub(Customer customer, double eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public double getEventTime() {
        return eventTime;
    }

    @Override
    public int getCustomerID() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%.3f", eventTime);
    }

    @Override
    public Pair<Optional<Event>, Shop> execute(Shop shop) {
        return Pair.of(Optional.empty(), shop);
    }

    @Override
    public String getType() {
        return "EventStub";
    }
}