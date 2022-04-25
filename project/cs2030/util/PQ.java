package cs2030.util;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PQ<T> {
    private final PriorityQueue<T> pq;

    public PQ(Comparator<? super T> cmp) {
        pq = new PriorityQueue<T>(cmp);
    }

    public PQ(PriorityQueue<T> pq) {
        this.pq = pq;
    }

    public PQ(PQ<T> otherPQ) {
        pq = new PriorityQueue<T>(otherPQ.pq);
    }

    public static <T> PQ<T> of() {
        return new PQ<>(new PriorityQueue<>());
    }

    public PQ<T> add(T elem) {
        PriorityQueue<T> newPQ = new PriorityQueue<>(pq);
        newPQ.add(elem);
        return new PQ<>(newPQ);
    }

    public Pair<T, PQ<T>> poll() {
        PriorityQueue<T> newPQ = new PriorityQueue<>(pq);
        T elem = newPQ.poll();
        return Pair.of(elem, new PQ<>(newPQ));
    }

    public boolean isEmpty() {
        return pq.isEmpty();
    }

    @Override
    public String toString() {
        return pq.toString();
    }

    public T peek() {
        return pq.peek();
    }

    public int size() {
        return pq.size();
    }
}