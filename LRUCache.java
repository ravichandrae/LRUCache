import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache<TKey, TValue> implements ICache<TKey, TValue> {
    private final int capacity;
    private LinkedList<CacheItem<TKey, TValue>> cacheItems;
    private Map<TKey, CacheItem<TKey, TValue>> cacheItemMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheItems = new LinkedList<>();
        cacheItemMap = new HashMap<>(capacity);
    }

    @Override
    public TValue get(TKey key) {
        if(cacheItemMap.containsKey(key)) {
            markUsage(key);
            return cacheItemMap.get(key).value;
        }
        throw new RuntimeException("key " + key + " is not found");
    }

    @Override
    public void set(TKey key, TValue value) {
        CacheItem<TKey, TValue> cacheItem = new CacheItem<>(key, value);
        //If key exists in the cache, remove it from the list;
        // no need to remove it from the map, as we are going to replace it with new item at the end
        if(cacheItemMap.containsKey(key)) {
            remove(key);
        }
        //If cache is full, remove the last item from the list and the map
        if(cacheItems.size() == capacity) {
            CacheItem<TKey, TValue> lastItem = cacheItems.removeLast();
            cacheItemMap.remove(lastItem.key);
        }

        cacheItems.addFirst(cacheItem); //Add the new cache item at the beginning of the list
        cacheItemMap.put(key, cacheItem);
    }

    @Override
    public void remove(TKey key) {
        if(cacheItemMap.containsKey(key)) {
            cacheItems.removeIf(cacheItem -> cacheItem.key == key);
            cacheItemMap.remove(key);
        }
        else
            throw new RuntimeException("key " + key + " is not found");
    }

    private void markUsage(TKey key) {
        Iterator<CacheItem<TKey, TValue>> iterator = cacheItems.iterator();
        CacheItem<TKey, TValue> item = null;
        while(iterator.hasNext()) {
             item = iterator.next();
            if(item.key == key) {
                iterator.remove();
            }
        }
        cacheItems.addFirst(item);
    }
}
