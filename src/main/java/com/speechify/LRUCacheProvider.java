package com.speechify;

/**
 *
 * Use the provided com.speechify.LRUCacheProviderTest in `src/test/java/LruCacheTest.java` to validate your
 * implementation.
 *
 * You may:
 *  - Read online API references for Java standard library or JVM collections.
 * You must not:
 *  - Read guides about how to code an LRU cache.
 */

public class LRUCacheProvider {
    public static <T> LRUCache<T> createLRUCache(CacheLimits options) {
        return new LRUCacheImpl<>(options);
    }
    
    private static class LRUCacheImpl<T> implements LRUCache<T> {
        private final int maxSize;
        private final java.util.LinkedHashMap<String, T> cache;
        
        public LRUCacheImpl(CacheLimits limits) {
            this.maxSize = limits.getMaxItemsCount();
            this.cache = new java.util.LinkedHashMap<String, T>(16, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(java.util.Map.Entry<String, T> eldest) {
                    return size() > maxSize;
                }
            };
        }
        
        @Override
        public T get(String key) {
            return cache.get(key);
        }
        
        @Override
        public void set(String key, T value) {
            cache.put(key, value);
        }
    }
}
