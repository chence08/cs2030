package cs2030.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;

public class ImList<T> implements Iterable<T> {
    private final List<T> elems;

    private ImList() {
        this.elems = new ArrayList<T>();
    }

    private ImList(List<? extends T> list) {
        this.elems = new ArrayList<T>(list);
    }

    public static <T> ImList<T> of() {
        return new ImList<T>();
    }

    public static <T> ImList<T> of(List<? extends T> list) {
        return new ImList<T>(list);
    }

    public static <T> ImList<T> of(ImList<? extends T> list) {
        ImList<T> newList = new ImList<T>();
        newList = newList.addAll(list);
        return newList;
    }

    public ImList<T> add(T elem) {
        ImList<T> newList = new ImList<T>(this.elems);
        newList.elems.add(elem);
        return newList;
    }

    public ImList<T> set(int index, T elem) {
        ImList<T> newList = new ImList<T>(this.elems);
        newList.elems.set(index, elem);
        return newList;
    }

    public ImList<T> addAll(ImList<? extends T> list) {
        ImList<T> newList = new ImList<T>(this.elems);
        newList.elems.addAll(list.elems);
        return newList;
    }

    public Pair<T,ImList<T>> remove(int index) {
        ImList<T> newList = new ImList<T>(this.elems);
        T removedElement = newList.elems.remove(index);
        return Pair.<T,ImList<T>>of(removedElement, newList);
    }

    public ImList<T> sort(Comparator<? super T> cmp) {
        ImList<T> newList = new ImList<T>(this.elems);
        newList.elems.sort(cmp);
        return newList;
    }

    public Iterator<T> iterator() {
        return this.elems.iterator();
    }

    public int indexOf(T elem) {
        return this.elems.indexOf(elem);
    }

    public int size() {
        return this.elems.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public <R> ImList<R> map(Function<? super T, ? extends R> mapper) {
        ImList<R> newList = new ImList<R>();
        for (T t : this.elems) {
            newList = newList.add(mapper.apply(t));
        }
        return newList;
    }

    public ImList<T> peek(Consumer<? super T> consumer) {
        for (T t : this.elems) {
            consumer.accept(t);
        }
        return this;
    }

    public ImList<T> filter(Predicate<? super T> pred) {
        ImList<T> newList = new ImList<T>();
        for (T t : this.elems) {
            if (pred.test(t)) {
                newList = newList.add(t);
            }
        }
        return newList;
    }

    public <U> U reduce(U identity, BiFunction<U, ? super T, U> mapper) {
        U result = identity;
        for (T t : this.elems) {
            result = mapper.apply(result, t);
        }
        return result;
    }
   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ImList) {
            ImList<?> otherlist = (ImList<?>) obj;
            if (this.size() != otherlist.size()) {
                return false;
            } else {
                for (int i = 0; i < this.size(); i++) {
                    if (!otherlist.get(i).equals(this.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    public T get(int index) {
        return this.elems.get(index);
    }

    public String toString() {
        return this.elems.toString();
    }
}