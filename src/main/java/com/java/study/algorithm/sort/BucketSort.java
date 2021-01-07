package com.java.study.algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {
    public static void main(String[] args) {

    }

    private static void bucketSort(int [] arr) {
        int max = arr[0];
        int min = arr[0];
        for(int i = 0; i< arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>>  bucketArr = new ArrayList<>(bucketNum);
        for(int i = 0; i< bucketNum; i++ ){
            bucketArr.add(new ArrayList<>());
        }

        for(int i =0; i< arr.length; i++) {
            int num = (arr[i]-min) /arr.length;
            bucketArr.get(num).add(arr[i]);
        }

        for(int i =0;i<bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }
    }
}
