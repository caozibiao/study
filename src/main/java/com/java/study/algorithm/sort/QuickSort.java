package com.java.study.algorithm.sort;

/**
 * @Author zibiao cao
 * @date 2021/1/5 17:04
 * @Version 1.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int arr[] = new int[]{3,3,3,7,9,122344,4656,34,34,4656,5,6,7,8,9,343,57765,23,12321};
        int len = arr.length-1;
        arr=quickSort(arr,0,len);
        for (int i:arr) {
            System.out.print(i+"\t");
        }
    }

    private static int [] quickSort(int [] arr, int start, int end) {
        int pivot = arr[start];
        int left = start;
        int right = end;
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
        if(left - 1 > start) {
            arr = quickSort(arr, start, left - 1);
        }
        if(right + 1 < end) {
            arr = quickSort(arr, right+1, end);
        }
        return arr;
    }
}
