package cs2030.simulator;

import java.util.Optional;

import cs2030.util.ImList;
import cs2030.util.Pair;

public class Rest implements Event {
    private final double endOfRest;
    private final Server serverIdentity;

    Rest(double endOfRest, Server serverIdentity) {
        this.endOfRest = endOfRest;
        this.serverIdentity = serverIdentity;
    }

    @Override
    public double getEventTime() {
        return endOfRest;
    }

    @Override
    public int getCustomerID() {
        return serverIdentity.getLastServed().getID();
    }

    @Override
    public String getType() {
        return "REST";
    }

    @Override
    public Pair<Optional<Event>, Shop> execute(Shop shop) {
        /*
        Next Events:
        SERVE
        NULL
        */
        ImList<Server> servers = shop.getServers();
        int index = serverIdentity.getID() - 1;
        Server updatedServer = servers.get(index).serveWaitingCustomer(endOfRest).resume();
        if (!updatedServer.isFree()) {
            Customer newServingCustomer = updatedServer.getLastServed();
            return Pair.of(
                Optional.of(new Serve(newServingCustomer, endOfRest, updatedServer)),
                shop.updateShop(updatedServer));
        }
        return Pair.of(Optional.empty(), shop.updateShop(updatedServer));
    }
}
