package com.java.study.algorithm.structure;

import java.util.*;

public class LinkedQueue {
    public static void main(String[] args) {
       // Queue
        List list = new ArrayList();
        list.add("test");
        list.add("test1");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Map<String, String> map = new HashMap<>();
        map.put("test", "test");
        map.put("test", "test");
        Iterator<Map.Entry<String, String>> iterator2 = map.entrySet().iterator();
        Iterator iterator1 = map.keySet().iterator();
        while(iterator2.hasNext()) {
            Map.Entry key = iterator2.next();
            //String key2 = key.getKey();
            String value = map.get(key);

        }
    }
}
