public class CacheItem <TKey, TValue>{
    public TKey key;
    public TValue value;
    public CacheItem(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }
}
