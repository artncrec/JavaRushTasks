package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    private OriginalRetriever originalRetriever;
    private LRUCache<Long, Object> cache = new LRUCache<>(1<<4);

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object o = cache.find(id);
        if (o == null) {
            if ((o = originalRetriever.retrieve(id)) != null)
                cache.set(id, o);
        }
        return o;
    }
}