import java.util.HashMap;
import java.util.Map;

public class MapInJava {
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();

        // Insert into map in O(1) IF THERE IS NO COLLISION
        map1.put("Emre", 2);
        map1.put("necdet", 3);
        map1.put("can", 41);

        // Remote item from map in O(1) IF THERE IS NO COLLISION
        map1.remove("necdet");

        // Get items from map in O(1)
        System.out.println(map1.get("Emre"));

        // Get keyset and iterate over the all keys in the map.
        for (String key : map1.keySet()) {
            System.out.println("Key: " + key);
            System.out.println("Value: " + map1.get(key));
        }

        // Get entryset and iterate over the all pairs in the map.
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

    }
}