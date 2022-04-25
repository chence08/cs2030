package cs2030.simulator;

import cs2030.util.ImList;
import cs2030.util.PQ;

public class Server extends AbstractServer {
    Server(int serverID, boolean isFree, ImList<Customer> servedCustomers,
        PQ<Customer> waitingCustomers, double totalWaitTime, int qmax,
        boolean isDone) {
        super(serverID, isFree, servedCustomers, waitingCustomers, totalWaitTime, qmax, isDone);
    }

    Server(int serverID, int qmax) {
        this(serverID, true, ImList.of(), PQ.of(), 0, qmax, true);
    }

    public Server(int serverID) {
        this(serverID, 1);
    }

    @Override
    public String toString() {
        return Integer.toString(serverID);
    }

    // Actions
    @Override
    Server serve(Customer customer) {
        return new Server(serverID, false, servedCustomers.add(customer),
            waitingCustomers, totalWaitTime, qmax, false);
    }

    @Override
    Server wait(Customer customer) {
        return new Server(serverID, isFree, servedCustomers,
            waitingCustomers.add(customer), totalWaitTime, qmax, isDone);
    }

    @Override
    Server serveWaitingCustomer(double eventTime) {
        if (hasWaitingCustomers()) { // there's someone waiting
            PQ<Customer> newPQ = waitingCustomers.poll().second();
            Customer nextCustomer = waitingCustomers.poll().first();
            double waitTime = eventTime - nextCustomer.getArrivalTime();
            return new Server(serverID, false, servedCustomers.add(nextCustomer),
                newPQ, totalWaitTime + waitTime, qmax, false);
        }
        return new Server(serverID, true, servedCustomers, waitingCustomers,
            totalWaitTime, qmax, true);
    }
    
    Server rest() {
        return new Server(serverID, false, servedCustomers, waitingCustomers,
            totalWaitTime, qmax, isDone);
    }

    Server resume() {
        return new Server(serverID, isDone, servedCustomers, waitingCustomers,
            totalWaitTime, qmax, isDone);
    }
}
