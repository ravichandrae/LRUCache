public interface ICache <TKey, TValue>{
    public TValue get(TKey key);
    public void set(TKey key, TValue value);
    public void remove(TKey key);
}
