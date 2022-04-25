package cs2030.simulator;

import java.util.function.Supplier;

import cs2030.util.Pair;

public class Customer implements Comparable<Customer> {
    private final int customerID;
    /**
     * Pair of arrivalTime and serviceTime.
     * serviceTime is obtained from a Supplier.
     */
    private final Pair<Double, Supplier<Double>> timePair;

    public Customer(int customerID, double arrivalTime) {
        this(customerID, Pair.of(arrivalTime, () -> 1.0));
    }

    Customer(int customerID, Pair<Double, Supplier<Double>> timePair) {
        this.customerID = customerID;
        this.timePair = timePair;
    }

    int getID() {
        return customerID;
    }

    public double getArrivalTime() {
        return timePair.first();
    }

    public double getServiceTime() {
        return timePair.second().get();
    }

    @Override
    public String toString() {
        return Integer.toString(customerID);
    }

    @Override
    public int compareTo(Customer otherCustomer) {
        return Double.compare(getArrivalTime(), otherCustomer.getArrivalTime());
    }
}
