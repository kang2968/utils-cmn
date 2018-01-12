package com.ie.common.utilities.cmnutils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A two-dimensional hashMap, is a HashMap that enables you to refer to values via two keys rather than one. The
 * underlying implementation is simply a HashMap containing HashMap, each of which maps to values.
 *
 * @param <K1>
 *         the first key type
 * @param <K2>
 *         the second key type
 * @param <V>
 *         the value type
 * @author Kasper B. Graversen
 * @see java.util.HashMap
 * @since 2.0.0 (migrated from Spiffy 0.5)
 */
public class IE2DHashMap<K1, K2, V> {
    
    private final HashMap<K1, HashMap<K2, V>> map;
    
    /**
     * Constructs a new <tt>IE2DHashMap</tt>.
     */
    public IE2DHashMap() {
        map = new HashMap<>();
    }
    
    /**
     * Constructs a new <tt>IE2DHashMap</tt>.
     */
    public IE2DHashMap(int initial) {
        map = new HashMap<>(initial);
    }
    
    /**
     * Constructs a new <tt>IE2DHashMap</tt> using the supplied map.
     *
     * @param map
     *         the map
     * @throws NullPointerException
     *         if map is null
     */
    public IE2DHashMap(final HashMap<K1, HashMap<K2, V>> map) {
        if (map == null) {
            throw new NullPointerException("map should not be null");
        }
        this.map = map;
    }
    
    /**
     * Existence check of a value (or <tt>null</tt>) mapped to the keys.
     *
     * @param firstKey
     * @return true when an element (or <tt>null</tt>) has been stored with the keys
     */
    public boolean containsKey(final K1 firstKey) {
        return map.containsKey(firstKey);
    }
    
    /**
     * Existence check of a value (or <tt>null</tt>) mapped to the keys.
     *
     * @param firstKey
     *         first key
     * @param secondKey
     *         second key
     * @return true when an element (or <tt>null</tt>) has been stored with the keys
     */
    public boolean containsKey(final K1 firstKey, final K2 secondKey) {
        // existence check on inner map
        final HashMap<K2, V> innerMap = map.get(firstKey);
        if (innerMap == null) {
            return false;
        }
        return innerMap.containsKey(secondKey);
    }
    
    /**
     * Fetch a value from the HashMap
     *
     * @param firstKey
     * @return the element or null.
     */
    public Map<K2, V> get(final K1 firstKey) {
        return map.get(firstKey);
    }
    
    /**
     * Fetch a value from the HashMap .
     *
     * @param firstKey
     *         first key
     * @param secondKey
     *         second key
     * @return the element or null.
     */
    public V get(final K1 firstKey, final K2 secondKey) {
        // existence check on inner map
        final HashMap<K2, V> innerMap = map.get(firstKey);
        if (innerMap == null) {
            return null;
        }
        return innerMap.get(secondKey);
    }
    
    /**
     * put a value
     *
     * @param firstKey
     * @param value
     * @return
     */
    public Map<K2, V> put(final K1 firstKey, final HashMap<K2, V> value) {
        return map.put(firstKey, value);
    }
    
    /**
     * Insert a value
     *
     * @param firstKey
     *         first key
     * @param secondKey
     *         second key
     * @param value
     *         the value to be inserted. <tt>null</tt> may be inserted as well.
     * @return null or the value the insert is replacing.
     */
    public V put(final K1 firstKey, final K2 secondKey, final V value) {
        return this.put(firstKey, secondKey, value, -1);
    }
    
    /**
     * put a value
     *
     * @param firstKey
     *         first key
     * @param secondKey
     *         second key
     * @param value
     *         the value to be inserted. <tt>null</tt> may be inserted as well.
     * @param innerInitial
     *         the initial size if innerMap dose not exist, Ignore when value is little than 1
     * @return null or the value the insert is replacing.
     */
    public V put(final K1 firstKey, final K2 secondKey, final V value, final int innerInitial) {
        // existence check on inner map
        HashMap<K2, V> innerMap = map.get(firstKey);
        
        if (innerMap == null) {
            // no inner map, create it
            innerMap = innerInitial > 0 ? new HashMap<>(innerInitial) : new HashMap<>();
            map.put(firstKey, innerMap);
        }
        
        return innerMap.put(secondKey, value);
    }
    
    /**
     * Returns the number of key-value mappings in this map for the first key.
     *
     * @return Returns the number of key-value mappings in this map for the first key.
     */
    public int size() {
        return map.size();
    }
    
    /**
     * Returns the number of key-value mappings in this map for the second key.
     *
     * @param firstKey
     *         the first key
     * @return Returns the number of key-value mappings in this map for the second key.
     */
    public int size(final K1 firstKey) {
        // existence check on inner map
        final HashMap<K2, V> innerMap = map.get(firstKey);
        if (innerMap == null) {
            return 0;
        }
        return innerMap.size();
    }
    
    /**
     * Returns a set of the keys of the outermost map.
     *
     * @return the key set for the outermost map
     */
    public Set<K1> keySet() {
        return map.keySet();
    }
    
    /**
     * Returns a set of the keys of the inner map
     *
     * @param firstKey
     * @return the key set for the inner map, it will be null when outer map dose not contain the firstKey
     */
    public Set<K2> keySet(final K1 firstKey) {
        final HashMap<K2, V> innerMap = map.get(firstKey);
        return innerMap != null ? innerMap.keySet() : null;
    }
    
    public void clear() {
        this.map.clear();
    }
    
    public void clear(K1 firstKey) {
        final HashMap<K2, V> innerMap = map.get(firstKey);
        if (innerMap != null) {
            innerMap.clear();
        }
    }
    
    /**
     * slight copy whole hashMap
     * @return
     */
    public IE2DHashMap<K1, K2, V> copy() {
        IE2DHashMap<K1, K2, V> result = new IE2DHashMap<>(this.map.size());
        this.map.forEach((k,v) -> result.put(k, IECollectionUtils.copy(v)));
        return result;
    }
    
    /**
     * slight copy innerMap
     * @param firstKey
     * @return
     */
    public HashMap<K2, V> copy(final K1 firstKey){
        return IECollectionUtils.copy(this.map.get(firstKey));
    }
    
}
