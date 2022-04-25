package cs2030.simulator;

import java.util.List;

import cs2030.util.ImList;
import cs2030.util.PQ;

public class Shop {
    private final ImList<Server> servers;
    private final ImList<SelfCheck> selfCheckCounters;

    public Shop(List<Server> servers) {
        this(ImList.of(servers));
    }

    Shop(ImList<Server> servers) {
        this(servers, ImList.of());
    }

    Shop(ImList<Server> servers, ImList<SelfCheck> selfCheckCounters) {
        this.servers = servers;
        this.selfCheckCounters = selfCheckCounters;
    }

    /**
     * For human servers.
     */
    Shop updateShop(Server newServer) {
        int index = newServer.getID() - 1;
        return new Shop(servers.set(index, newServer), selfCheckCounters);
    }

    /**
     * When a selfCheck is updated, we need to update the queues of all selfChecks.
     */
    Shop updateShop(SelfCheck newServer) {
        int index = newServer.getID() - 1 - servers.size();
        ImList<SelfCheck> newCounters = selfCheckCounters.set(index, newServer);
        PQ<Customer> latestQueue = newServer.getWaitingCustomers();
        for (int i = 0; i < selfCheckCounters.size(); i++) {
            if (i != index) {
                SelfCheck updated = newCounters.get(i).updateQueue(latestQueue);
                newCounters = newCounters.set(i, updated);
            }
        }
        return new Shop(servers, newCounters);
    }

    ImList<Server> getServers() {
        return servers;
    }

    ImList<SelfCheck> getSelfCheck() {
        return selfCheckCounters;
    }

    @Override
    public String toString() {
        return servers.toString();
    }

    double getTotalWaitingTime() {
        double totalWaitingTime = 0;
        for (Server server: servers) {
            totalWaitingTime += server.getTotalWaitTime();
        }
        for (SelfCheck counter: selfCheckCounters) {
            totalWaitingTime += counter.getTotalWaitTime();
        }
        return totalWaitingTime;
    }

    int getNumServed() {
        int numServed = 0;
        for (Server server: servers) {
            numServed += server.getNumServed();
        }
        for (SelfCheck counter: selfCheckCounters) {
            numServed += counter.getNumServed();
        }
        return numServed;
    }
}
