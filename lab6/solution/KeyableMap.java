import java.util.Map;
import java.util.Optional;

class KeyableMap<V extends Keyable> implements Keyable {
    private final String key;
    private final ImmutableMap<String, V> map;
    
    KeyableMap(String key, ImmutableMap<String, V> map) {
        this.key = key;
        this.map = map;
    }

    KeyableMap(String key) {
        this(key, new ImmutableMap<>());
    }
    
    @Override
    public String getKey() {
        return key;
    }

    KeyableMap<V> put(V item) {
        return new KeyableMap<>(key, map.put(item.getKey(), item));
    }

    Optional<V> get(Object key) {
        return map.get(key);
    }

    @Override
    public String toString() {
        String values = "";
        for (Map.Entry<String, V> entry: map) {
            values += entry.getValue().toString() + ", ";
        }
        if (!map.isEmpty()) {
            values = values.substring(0, values.length() - 2); // remove comma
        }
        return String.format("%s: {%s}", key, values);
    }

    ImmutableMap<String, V> getMap() {
        return map;
    }
}
