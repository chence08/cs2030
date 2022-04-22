import cs2030.util.*
Comparator<String> cmp = (x, y) -> x.compareTo(y)
PQ<String> pq = new PQ<String>(cmp)
pq.add("one").add("two").add("three")
pq
pq.isEmpty()
pq = pq.add("one").add("two").add("three")
pq.poll()
pq
pq.isEmpty()
pq = pq.poll().second().poll().second().poll().second()
pq.isEmpty()
Comparator<Object> c = (x, y) -> x.hashCode() - y.hashCode()
pq = new PQ<String>(c)
pq.add("one").poll()
