package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyCache {
    private final CacheManager cacheManager;

    public Object get(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        assert cache != null;
        return cache.get(key).get();
    }

    public void add(String cacheName, String key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }
}
