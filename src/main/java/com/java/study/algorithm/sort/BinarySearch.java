package com.java.study.algorithm.sort;

/**
 * @Author zibiao cao
 * @date 2021/1/5 14:06
 * @Version 1.0
 */
public class BinarySearch {
    public static void main(String[] args) {
        int [] arr = {2,5,7,9,12,15,16};
        int i = new BinarySearch().binarySearch(arr, 15);
        System.out.println(i);
    }

    private int binarySearch(int [] arr, int target) {
        int mid ;
        int right = arr.length - 1;
        int left = 0;
        while(left <= right) {
            mid = (left + right) / 2;
            if(target < arr[mid]) {
                right = mid - 1;
            } else if(target > arr[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
