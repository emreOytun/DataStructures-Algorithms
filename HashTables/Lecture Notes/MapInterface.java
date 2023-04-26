import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class MapInterface {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        
        // V put(K key, V value);
        map.put("Emre", 3);
        map.put("Ali", 2);

        // V get(Object key);
        map.get("Emre"); // 3
        map.get("Ali"); // 2

        // V remove(Object key);
        map.remove("Emre");

        // size() and isEmpty() methods:
        map.size();
        map.isEmpty();

        // IMPORTANT**: Map'in iterator methodu yoktur !!!!
        // Iterate edilmek isteniyorsa once set olusturulur bunlar uzerinde iterate edilir !!!
        Set<String> keySet =  map.keySet(); // returns the keyset
        Collection<Integer> valueSet = map.values(); // returns the value set
        Set<Entry<String,Integer>> entrySet = map.entrySet(); // returns the entry set

    }    
}
