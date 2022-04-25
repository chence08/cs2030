package cs2030.simulator;

import cs2030.util.PQ;
import cs2030.util.ImList;

public class SelfCheck extends AbstractServer {
    private final int waitID;

    SelfCheck(int serverID, boolean isFree, ImList<Customer> servedCustomers,
        PQ<Customer> waitingCustomers, double totalWaitTime, int qmax,
        boolean isDone, int waitID) {
        super(serverID, isFree, servedCustomers, waitingCustomers, totalWaitTime, qmax, isDone);
        this.waitID = waitID;
    }

    SelfCheck(int serverID, int qmax, int waitID) {
        super(serverID, qmax);
        this.waitID = waitID;
    }

    SelfCheck(SelfCheck old, PQ<Customer> sharedQueue) {
        super(old.serverID, old.isFree, old.servedCustomers, sharedQueue,
            old.totalWaitTime, old.qmax, old.isDone);
        waitID = old.waitID;
    }

    SelfCheck updateQueue(PQ<Customer> sharedQueue) {
        return new SelfCheck(this, sharedQueue);
    }

    @Override
    public String toString() {
        return String.format("self-check %d", serverID);
    }

    @Override
    String getType() {
        return "SelfCheck";
    }

    @Override
    SelfCheck serve(Customer customer) {
        return new SelfCheck(serverID, false, servedCustomers.add(customer),
            waitingCustomers, totalWaitTime, qmax, false, waitID);
    }

    @Override
    SelfCheck wait(Customer customer) {
        return new SelfCheck(serverID, isFree, servedCustomers,
            waitingCustomers.add(customer), totalWaitTime, qmax, isDone, waitID);
    }

    @Override
    SelfCheck serveWaitingCustomer(double eventTime) {
        if (hasWaitingCustomers()) { // there's someone waiting
            PQ<Customer> newPQ = waitingCustomers.poll().second();
            Customer nextCustomer = waitingCustomers.poll().first();
            double waitTime = eventTime - nextCustomer.getArrivalTime();
            return new SelfCheck(serverID, false, servedCustomers.add(nextCustomer),
                newPQ, totalWaitTime + waitTime, qmax, false, waitID);
        }
        return new SelfCheck(serverID, true, servedCustomers, waitingCustomers,
            totalWaitTime, qmax, true, waitID);
    }
}
