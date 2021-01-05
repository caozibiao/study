package com.java.study.algorithm.sort;

/**
 * @Author zibiao cao
 * @date 2021/1/5 14:26
 * @Version 1.0
 */
public class SelectSort {
    public static void main(String[] args) {
        int [] arr = {2,5,7,9,4,3,6};
        int[] arr2 = new SelectSort().selectSort(arr);
        System.out.println(arr2);
    }

    private int[] selectSort(int [] arr) {
        int len = arr.length;
        for(int m = 0; m < len - 1; m++) {
            int min = arr[m];
            int num = m;
            for(int n = m; n < len; n++) {
                if(arr[n] < min) {
                    num = n;
                    min = arr[n];
                }
            }
            if(num != m) {
                arr[num] = arr[m];
                arr[m] = min;
            }
        }
        return arr;
    }
}
