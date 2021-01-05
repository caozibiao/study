package com.java.study.algorithm.sort;

/**
 * @Author zibiao cao
 * @date 2021/1/5 17:04
 * @Version 1.0
 */
public class QuickSort {
    public static void main(String[] args) {

    }

    private int [] quickSort(int [] arr) {
        int pivot = arr[0];
        int left = 0;
        int right = arr.length - 1;
        while(left < right) {
            while((left< right && arr[right] > pivot)) {
                right--;
            }
            while ((left< right) && arr[left] < pivot) {
                left++;
            }
            if(arr[left] == arr[right] && left< right) {
                left++;
            } else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        if(left - 1 > 0) {

        }
    }
}
