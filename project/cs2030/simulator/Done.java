package cs2030.simulator;

import java.util.Optional;

import cs2030.util.Pair;

public class Done implements Event {
    private final Customer customer;
    private final double eventTime;
    private final AbstractServer serverIdentity;

    public Done(Customer customer, double eventTime, AbstractServer serverIdentity) {
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

    int getServerID() {
        return serverIdentity.getID();
    }

    @Override
    public String toString() {
        return String.format("%.3f %s done serving by %s",
            eventTime, customer, serverIdentity);
    }

    @Override
    public Pair<Optional<Event>, Shop> execute(Shop shop) {
        /*
        Next Events:
        SERVE: serve waitingCustomer
        NULL: no waitingCustomer to serve

        ARRIVE -> SERVE -> DONE
        >= Level 7: ARRIVE -> SERVE -> REST
        */
        return Pair.of(Optional.empty(), shop);
    }

    @Override
    public String getType() {
        return "DONE";
    }
}
