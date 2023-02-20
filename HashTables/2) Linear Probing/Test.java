public class Test {
    public static void main(String[] args) {
        HashTable t = new HashTable();
        t.put(0, 1);
        t.put(1, 10);
        t.put(2, 20);
        t.put(3, 30);
        t.put(4, 40);
        t.put(5, 50);
        t.put(6, 60);
        t.put(7, 70);
        t.put(11, 110);
        
        System.out.println(t.get(1));
    }
}
