public class Test {
    public static void main(String []args) {
        ICache<String, String> cache = new LRUCache<>(3);
        cache.set("1", "I");
        System.out.println(cache.get("1")); //Expect I
        cache.set("2", "II");
        cache.set("3", "III");
        System.out.println(cache.get("2")); //Expect II
        cache.set("4", "IV");
        try {
            cache.get("1");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        cache.set("5", "V");
        System.out.println(cache.get("2")); //Print II
        try {
            cache.get("3");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
