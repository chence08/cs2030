import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Set;
import java.util.Collection;

class ImmutableMap<K, V> implements Iterable<Map.Entry<K, V>> {
    private final Map<K, V> map;

    ImmutableMap() {
        map = new LinkedHashMap<>();
    }

    ImmutableMap(Map<K, V> otherMap) {
        map = otherMap;
    }

    ImmutableMap<K, V> put(K key, V value) {
        Map<K, V> newMap = new LinkedHashMap<>(map);
        newMap.put(key, value);
        return new ImmutableMap<K, V>(newMap);
    }

    boolean isEmpty() {
        return map.isEmpty();
    }

    Optional<V> get(Object key) {
        return Optional.ofNullable(map.get(key));
    }

    Set<K> keySet() {
        return map.keySet();
    }

    Collection<V> values() {
        return map.values();
    }

    Set<Map.Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return map.entrySet().iterator();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
