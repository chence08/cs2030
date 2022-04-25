package cs2030.simulator;

import java.util.Optional;

import cs2030.util.ImList;
import cs2030.util.Pair;

public class Arrive implements Event {
    private final Customer customer;

    Arrive(Customer customer) {
        this.customer = customer;
    }

    @Override
    public double getEventTime() {
        return customer.getArrivalTime();
    }

    @Override
    public int getCustomerID() {
        return customer.getID();
    }

    @Override
    public String toString() {
        return String.format("%.3f %s arrives", customer.getArrivalTime(), customer);
    }

    @Override
    public Pair<Optional<Event>, Shop> execute(Shop shop) {
        /*
        Next Events:
        SERVE: if some server is idle.
        WAIT: if all servers are busy and some server no customer waiting in queue.
        LEAVE: if all servers are busy and each have a customer waiting in queue.
        */
        ImList<Server> servers = shop.getServers();
        ImList<SelfCheck> selfCheckCounters = shop.getSelfCheck();
        for (Server server: servers) {
            if (server.isFree()) {
                Server busyServer = server.serve(customer);
                return Pair.of(
                        Optional.of(new Serve(customer, customer.getArrivalTime(), server)),
                        shop.updateShop(busyServer));
            }
        }
        for (SelfCheck counter: selfCheckCounters) {
            if (counter.isFree()) {
                SelfCheck busyServer = counter.serve(customer);
                return Pair.of(
                        Optional.of(new Serve(customer, customer.getArrivalTime(), counter)),
                        shop.updateShop(busyServer));
            }
        }

        // Check empty queues, if empty return WAIT
        for (Server server: servers) {
            if (server.isQueueAvailable()) {
                Server busyServer = server.wait(customer);
                return Pair.of(
                        Optional.of(new Wait(customer, customer.getArrivalTime(), server)),
                        shop.updateShop(busyServer));
            }
        }
        for (SelfCheck counter: selfCheckCounters) {
            if (counter.isQueueAvailable()) {
                SelfCheck busyServer = counter.wait(customer);
                return Pair.of(
                        Optional.of(new Wait(customer, customer.getArrivalTime(), counter)),
                        shop.updateShop(busyServer));
            }
        }
        // LEAVE
        return Pair.of(Optional.of(new Leave(customer, customer.getArrivalTime())), shop);
    }
    
    @Override
    public String getType() {
        return "ARRIVE";
    }
}
