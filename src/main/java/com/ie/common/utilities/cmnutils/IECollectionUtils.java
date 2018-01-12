package com.ie.common.utilities.cmnutils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * common collection utilities
 *
 * @author bradly
 * @version 1.0
 */
public class IECollectionUtils {
    
    private IECollectionUtils() {
    }
    
    /**
     * fetch collection size
     *
     * @param collection
     * @return it will be 0 when collection is null
     */
    public static int size(final Collection<?> collection) {
        return collection != null ? collection.size() : 0;
    }
    
    /**
     * fetch map size
     *
     * @param map
     * @return it will be 0 when map is null
     */
    public static int size(final Map<?, ?> map) {
        return map != null ? map.size() : 0;
    }
    
    /**
     * check whether collection is null or size is zero
     *
     * @param collection
     * @return it will be true when collection is null or the size of collection is zero
     */
    public static boolean isEmpty(final Collection<?> collection) {
        return size(collection) == 0;
    }
    
    /**
     * check whether map is null or size or size is zero
     *
     * @param map
     * @return it will be true when map is null or the size of map is 0
     */
    public static boolean isEmpty(final Map<?, ?> map) {
        return size(map) == 0;
    }
    
    /**
     * check whether collection is not null and the size of collection is greater than 0
     *
     * @param collection
     * @return it will be true when collection is not null and the size of collection is greater than 0
     */
    public static boolean isNotEmpty(final Collection<?> collection) {
        return size(collection) > 0;
    }
    
    /**
     * check whether map is not null and the size of collection is greater than 0
     *
     * @param map
     * @return it will be true when map is not null and the size of map is greater than 0
     */
    public static boolean isNotEmpty(final Map<?, ?> map) {
        return size(map) > 0;
    }
    
    /**
     * check whether collection is null or the size of collection is 0 ,or the each element is blank
     *
     * @param collection
     * @return it will be true when collection is null or size is 0 or each element in collection is blank
     */
    public static boolean isBlank(final Collection<? extends CharSequence> collection) {
        if (collection != null && collection.size() > 0) {
            for (CharSequence c : collection) {
                if (IEStringUtils.isNotBlank(c)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isNotBlank(final Collection<? extends CharSequence> collection) {
        return !isBlank(collection);
    }
    
    /**
     * fetch element from list
     *
     * @param list
     * @param index
     * @param <T>
     * @return it will be null when list is null or index is greater than the size of collection
     */
    public static <T> T fetch(final List<T> list, final int index) {
        return list != null && list.size() > index ? list.get(index) : null;
    }
    
    /**
     * fetch value from map by key
     *
     * @param map
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> V fetch(final Map<K, V> map, final K key) {
        return map != null ? map.get(key) : null;
    }
    
    /**
     * slight copy HashMap
     *
     * @param value
     * @param <K>
     * @param <V>
     * @return null when value is null;
     */
    public static <K, V> HashMap<K, V> copy(HashMap<K, V> value) {
        if (value == null) {
            return null;
        }
        HashMap<K, V> result = new HashMap<>(value.size());
        value.forEach((k, v) -> result.put(k, v));
        return result;
    }
    
}
