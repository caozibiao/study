package com.java.study.algorithm.sort;

/**
 * @Author zibiao cao
 * @date 2021/1/5 13:50
 * @Version 1.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        int [] arr = {2,5,7,9,4,3,6};
        int[] arr2 = new BubbleSort().bubbleSort(arr);
        System.out.println(arr2);
    }

    private int[] bubbleSort(int [] arr) {
        int len = arr.length;
        for(int m = 0; m < len - 1; m++) {
            for(int n = 0; n < len - 1; n++) {
                if(arr[n] > arr[n + 1]) {
                    int temp = arr[n];
                    arr[n] = arr[n + 1];
                    arr[n+1] = temp;
                }
            }
        }
        return arr;
    }
}
