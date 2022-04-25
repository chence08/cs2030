package cs2030.simulator;

import cs2030.util.PQ;
import cs2030.util.ImList;

abstract class AbstractServer {
    protected final int serverID;
    protected final boolean isFree;
    protected final ImList<Customer> servedCustomers;
    protected final PQ<Customer> waitingCustomers;
    protected final int qmax;
    protected final boolean isDone;

    // Statistic
    protected final double totalWaitTime;

    AbstractServer(int serverID, boolean isFree, ImList<Customer> servedCustomers,
        PQ<Customer> waitingCustomers, double totalWaitTime, int qmax,
        boolean isDone) {
        this.serverID = serverID;
        this.isFree = isFree;
        this.servedCustomers = servedCustomers;
        this.waitingCustomers = waitingCustomers;
        this.totalWaitTime = totalWaitTime;
        this.qmax = qmax;
        this.isDone = isDone;
    }

    AbstractServer(int serverID, int qmax) {
        this(serverID, true, ImList.of(), PQ.of(), 0, qmax, true);
    }

    // Actions
    abstract AbstractServer serve(Customer customer);

    abstract AbstractServer wait(Customer customer);
    
    abstract AbstractServer serveWaitingCustomer(double eventTime);

    // Getters
    abstract String getType();

    Customer getLastServed() {
        return servedCustomers.get(servedCustomers.size() - 1);
    }

    int getID() {
        return serverID;
    }

    double getTotalWaitTime() {
        return totalWaitTime;
    }

    int getNumServed() {
        return servedCustomers.size();
    }

    PQ<Customer> getWaitingCustomers() {
        return waitingCustomers;
    }

    // Checkers
    boolean isFree() {
        return isFree;
    }

    boolean isQueueAvailable() {
        return waitingCustomers.size() < qmax;
    }

    boolean hasWaitingCustomers() {
        return !waitingCustomers.isEmpty();
    }
}
