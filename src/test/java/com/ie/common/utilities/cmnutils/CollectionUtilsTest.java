package com.ie.common.utilities.cmnutils;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CollectionUtilsTest {

    @Test
    public void testSize(){
        int size = IECollectionUtils.size((Collection) null);
        Assert.assertEquals(0, size);

        size = IECollectionUtils.size((Map) null);
        Assert.assertEquals(0, size);

        size = IECollectionUtils.size(Collections.emptyMap());
        Assert.assertEquals(0, size);

        size = IECollectionUtils.size(Collections.emptySet());
        Assert.assertEquals(0, size);

        size = IECollectionUtils.size(Collections.emptyList());
        Assert.assertEquals(0,size);

        Map<String, String> map = new HashMap<>();
        map.put("2", "2");
        map.put("3", "3");
        size = IECollectionUtils.size(map);
        Assert.assertEquals(2, size);

        Set<String> set = new HashSet<>();
        set.add("2");
        set.add("3");
        size = IECollectionUtils.size(set);
        Assert.assertEquals(2, size);

        List<String> list = new LinkedList<>();
        list.add("2");
        list.add("3");
        size = IECollectionUtils.size(list);
        Assert.assertEquals(2, size);
    }

    @Test
    public void testIsEmpty(){

        boolean flag = IECollectionUtils.isEmpty((Map) null);
        Assert.assertTrue(flag);

        flag = IECollectionUtils.isEmpty((Collection) null);
        Assert.assertTrue(flag);

        flag = IECollectionUtils.isEmpty(Collections.emptyMap());
        Assert.assertTrue(flag);

        flag = IECollectionUtils.isEmpty(Collections.emptyList());
        Assert.assertTrue(flag);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        flag = IECollectionUtils.isEmpty(map);
        Assert.assertFalse(flag);

        List<Integer> list = new LinkedList<>();
        list.add(1);
        flag = IECollectionUtils.isEmpty(list);
        Assert.assertFalse(flag);

    }

    @Test
    public void testIsNotEmpty(){
        boolean flag = IECollectionUtils.isNotEmpty((Map) null);
        Assert.assertFalse(flag);

        flag = IECollectionUtils.isNotEmpty((Collection) null);
        Assert.assertFalse(flag);

        flag = IECollectionUtils.isNotEmpty(Collections.emptyMap());
        Assert.assertFalse(flag);

        flag = IECollectionUtils.isNotEmpty(Collections.emptyList());
        Assert.assertFalse(flag);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        flag = IECollectionUtils.isNotEmpty(map);
        Assert.assertTrue(flag);

        List<Integer> list = new LinkedList<>();
        list.add(1);
        flag = IECollectionUtils.isNotEmpty(list);
        Assert.assertTrue(flag);
    }

    @Test
    public void testIsBlank(){

        boolean flag = IECollectionUtils.isBlank((List<String>) null);
        Assert.assertTrue(flag);

        flag = IECollectionUtils.isBlank(Collections.emptyList());
        Assert.assertTrue(flag);

        List<String> list = new LinkedList<>();
        list.add("");
        list.add("");
        flag = IECollectionUtils.isBlank(list);
        Assert.assertTrue(flag);

        list.add("3");
        flag = IECollectionUtils.isBlank(list);
        Assert.assertFalse(flag);
    }

    @Test
    public void testIsNotBlank() {
        boolean flag = IECollectionUtils.isNotBlank((List<String>) null);
        Assert.assertFalse(flag);

        flag = IECollectionUtils.isNotBlank(Collections.emptyList());
        Assert.assertFalse(flag);

        List<String> list = new LinkedList<>();
        list.add("");
        list.add("");
        flag = IECollectionUtils.isNotBlank(list);
        Assert.assertFalse(flag);

        list.add("3");
        flag = IECollectionUtils.isNotBlank(list);
        Assert.assertTrue(flag);
    }

    @Test
    public void testFetchList() {
        List<String> list = null;
        String value = IECollectionUtils.fetch(list, 1);
        Assert.assertNull(value);

        list = new ArrayList<>();
        value = IECollectionUtils.fetch(list,1);
        Assert.assertNull(value);

        list.add("1");
        value = IECollectionUtils.fetch(list,1);
        Assert.assertNull(value);

        list.add("2");
        value = IECollectionUtils.fetch(list, 1);
        Assert.assertEquals("2", value);

    }

    @Test
    public void testFetchMap() {
        Map<Integer, Integer> map = null;
        Integer value = IECollectionUtils.fetch(map, 2);
        Assert.assertNull(value);

        map = new HashMap<>();
        value = IECollectionUtils.fetch(map, 2);
        Assert.assertNull(value);

        map.put(1,3);
        value = IECollectionUtils.fetch(map, 2);
        Assert.assertNull(value);

        map.put(2, 4);
        value = IECollectionUtils.fetch(map, 2);
        Assert.assertEquals(Integer.valueOf(4), value);

    }

}
