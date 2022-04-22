import cs2030.util.*
import cs2030.simulator.*
new Customer(1, 0.5)
new Customer(2, 0.6)
new Customer(3, 0.7)
Event event = new EventStub(new Customer(1, 0.5), 3.7) // event time of 3.7
Comparator<Event> cmp = new EventComparator()
PQ<Event> pq = new PQ<Event>(cmp)
pq = pq.add(new EventStub(new Customer(1, 0.5), 3.5))
pq = pq.add(new EventStub(new Customer(2, 0.6), 2.6))
pq = pq.add(new EventStub(new Customer(3, 0.7), 1.7))
pq.poll().first()
pq.poll().second().poll().first()
pq.poll().second().poll().second().poll().first()
pq.poll().second().poll().second().poll().second().isEmpty()
