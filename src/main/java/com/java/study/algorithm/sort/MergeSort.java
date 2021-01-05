package com.java.study.algorithm.sort;

import java.util.Arrays;

/**
 * @Author zibiao cao
 * @date 2021/1/5 16:21
 * @Version 1.0
 */
public class MergeSort {
    public static void main(String[] args) {

    }

    private static int [] sort(int [] arr) {
        int [] arr2 = Arrays.copyOf(arr, arr.length);
        if(arr2.length < 2) {
            return arr2;
        }
        int middle = arr2.length / 2;
        int [] left = Arrays.copyOfRange(arr2, 0, middle);
        int [] right = Arrays.copyOfRange(arr2, middle, arr2.length);
        return merge(sort(left), sort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int [] result = new int[left.length + right.length];
        int i = 0;
        while(left.length > 0 && right.length > 0) {

            if(left[0] < right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                right[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }
        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }
}
