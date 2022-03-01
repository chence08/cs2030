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
ImList list = new ImList();
list.add(1)
list
list = list.add(1)
ImList<Integer> list2 = list;
list2.set(0,99)
list
list2 = list2.set(0, 99).add(2)
new ImList<>().addAll(list2)
ImList<Integer> list3 = new ImList<Integer>().addAll(list2);
class AscendingComp implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
        return a - b;
    }
}
class DescendingComp implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
        return b - a;
    }
}
ImList<Integer> list4 = new ImList<>(List.of(3,6,8,0,99,-5,-1000));
list4.sort(new AscendingComp())
list4.sort(new DescendingComp())
list4.sort(Comparator.reverseOrder())
for (int num: list4) {
    System.out.println(num * num);
}
