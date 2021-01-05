package com.java.study.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.SynchronousQueue;

/**
 * @Author zibiao cao
 * @date 2020/12/10 14:39
 * @Version 1.0
 */
public class CollectionTest {
    public static void main(String[] args) {
        /*List list = new ArrayList<String>(5);

        for(int i=0;i<4; i++) {
            list.add("test" + i);
        }

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            //list.remove(iterator.next());
            iterator.remove();
        }

        LinkedList linkedList = new LinkedList();
        linkedList.add("string");*/

        HashSet hashSet = new HashSet();
        hashSet.add("String");

        TreeSet treeSet = new TreeSet();
        treeSet.add("123");

        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("test");

        HashMap hashMap = new HashMap(64);
        //hashMap.put(new HashOverrideObj(), "test");
        //hashMap.put(new CollectionTest(), "test");
        Set set = hashMap.entrySet();
        for(int i=0; i<15; i++) {
            hashMap.put(new HashOverrideObj(), "test" + i);
        }

        ConcurrentMap concurrentMap = new ConcurrentHashMap();

        LinkedHashMap<Object, Object> objectObjectLinkedHashMap = new LinkedHashMap<>();
        objectObjectLinkedHashMap.put("a", "test");

        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
        for(int i=0; i<15; i++) {
            concurrentSkipListMap.put("test"+ i, "strign"+ i);
        }

        SynchronousQueue synchronousQueue = new SynchronousQueue();
        try {
            synchronousQueue.put(new Object());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
