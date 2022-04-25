package cs2030.simulator;

import java.util.Optional;

import cs2030.util.Pair;

public class Serve implements Event {
    private final Customer customer;
    private final double eventTime;
    private final AbstractServer serverIdentity;

    Serve(Customer customer, double eventTime, AbstractServer serverIdentity) {
        this.customer = customer;
        this.eventTime = eventTime;
        this.serverIdentity = serverIdentity;
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
        return String.format("%.3f %s serves by %s", eventTime, customer, serverIdentity);
    }

    @Override
    public Pair<Optional<Event>, Shop> execute(Shop shop) {
        return Pair.of(
            Optional.of(
                new Done(customer, eventTime + customer.getServiceTime(), serverIdentity)),
                shop);
    }

    @Override
    public String getType() {
        return "SERVE";
    }
}
