import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

class ImList<T> implements Iterable<T> {
    private final ArrayList<T> elems;

    ImList() {
        this.elems = new ArrayList<T>();
    }

    ImList(List<T> list) {
        this.elems = new ArrayList<T>(list);
    }

    ImList<T> add(T elem) {
        ImList<T> newList = new ImList<T>(this.elems);
        newList.elems.add(elem);
        return newList;
    }

    ImList<T> set(int index, T elem) {
        ImList<T> newList = new ImList<T>(this.elems);
        newList.elems.set(index, elem);
        return newList;
    }
    
    ImList<T> addAll(ImList<? extends T> list) {
        ImList<T> newList = new ImList<T>(this.elems);
        newList.elems.addAll(list.elems);
        return newList;
    }

    ImList<T> sort(Comparator<? super T> cmp) {
        ImList<T> newList = new ImList<T>(this.elems);
        newList.elems.sort(cmp);
        return newList;
    }

    public Iterator<T> iterator() {
        return this.elems.iterator();
    }

    T get(int index) {
        return this.elems.get(index);
    }

    int size() {
        return this.elems.size();
    }

    public String toString() {
        return this.elems.toString();
    }
}
