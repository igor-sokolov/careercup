import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by sis on 6/4/15.
 */
public class CareerCup_5735022524891136 {
    public static void main(String[] args) {
        LRU<Integer, Integer> cache = new LRU<>(2, s -> {
            System.out.println(s);
            return s;
        });

        // new value
        cache.apply(1);
        // use cache
        cache.apply(1);
        // new value
        cache.apply(2);
        // new value, 1 is pushed from cache
        cache.apply(3);
        // use cache
        cache.apply(2);
        // new value because we removed 1 previously
        cache.apply(1);
    }

    /**
     * LRU implementation as a pattern 'Proxy'.
     * @param <K> key type
     * @param <V> value type
     */
    public static final class LRU<K, V> implements Function<K, V> {
        private final LinkedHashMap<K, V> map;
        private final Function<K, V> realSubject;

        public LRU(final int size, Function<K, V> realSubject) {
            this.realSubject = realSubject;
            this.map = new LinkedHashMap(16, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return this.size() == size + 1;
                }
            };
        }

        @Override
        public V apply(K k) {
            V v = map.get(k);
            if (v == null) {
                v = realSubject.apply(k);
                map.put(k, v);
            }

            return v;
        }
    }
}