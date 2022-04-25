package cs2030.simulator;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        if (Double.compare(event1.getEventTime(), event2.getEventTime()) == 0) {
            return Integer.compare(event1.getCustomerID(), event2.getCustomerID());
        }
        return Double.compare(event1.getEventTime(), event2.getEventTime());
    }
}
