package com.java.study.algorithm.sort;

/**
 * @Author zibiao cao
 * @date 2021/1/5 15:14
 * @Version 1.0
 */
public class ShellSort {
    public static void main(String[] args) {
        int [] arr = {2,5,7,9,4,3,6,12,87,23,45};
        int[] arr2 = ShellSort.shellSort(arr);
        System.out.println(arr2);
    }

    private static int [] shellSort(int [] arr) {
        int length = arr.length;
        int gap = length / 2;
        while(gap >= 1) {
            for(int m = gap; m < length; m++) {
                if(arr[m] < arr[m - gap]) {
                   int j;
                   int insertValue = arr[m];
                   arr[m] = arr[m - gap];
                   for(j = m - gap; j > 0 && insertValue < arr[j]; j = j - gap) {
                       arr[j + gap] = arr[j];
                   }
                   arr[j + gap] = insertValue;
                }
            }
            gap = gap / 2;
        }
        return arr;
    }
}
