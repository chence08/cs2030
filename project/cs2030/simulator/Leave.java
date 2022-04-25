package cs2030.simulator;

import java.util.Optional;

import cs2030.util.Pair;

public class Leave implements Event {
    private final Customer customer;
    private final double eventTime;

    public Leave(Customer customer, double eventTime) {
        this.customer = customer;
        this.eventTime = eventTime;
    }

    @Override
    public double getEventTime() {
        return eventTime;
    }

    @Override
    public int getCustomerID() {
        return customer.getID();
    }

    @Override
    public String toString() {
        return String.format("%.3f %s leaves", eventTime, customer);
    }

    @Override
    public Pair<Optional<Event>, Shop> execute(Shop shop) {
        return Pair.of(Optional.empty(), shop);
    }

    @Override
    public String getType() {
        return "LEAVE";
    }
}
