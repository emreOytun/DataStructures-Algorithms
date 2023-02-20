public class Test {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Emre", 3);
        map.put("Necdet", 58);
        map.put("Emrea", 315);
        map.put("Necdetb", 5814);
        map.put("Emrec", 3113);
        map.put("Necdetd", 5812);
        map.put("Emree", 30);
        map.put("Necdetf", 589);
        map.put("Emreg", 38);
        map.put("Necdeth", 587);
        map.put("Emrez", 36);
        map.put("Necdetk", 585);
        map.put("Emrel", 34);
        map.put("Necdetm", 583);
        map.put("Emren", 32);
        map.put("Necdeto", 581);
        
        map.remove("Emrel");
        
        System.out.println(map.get("Emre"));
        System.out.println(map.get("Necdeto"));
    }
}
