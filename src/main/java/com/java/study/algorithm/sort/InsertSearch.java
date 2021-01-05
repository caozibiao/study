package com.java.study.algorithm.sort;

/**
 * @Author zibiao cao
 * @date 2021/1/5 14:44
 * @Version 1.0
 */
public class InsertSearch {
    public static void main(String[] args) {
        int [] arr = {2,5,7,9,4,3,6};
        int[] arr2 = new InsertSearch().insertSearch(arr);
        System.out.println(arr2);
    }

    private int [] insertSearch(int [] arr) {
        int length = arr.length;
        for(int m = 1; m < length; m++) {
            int insertValue = arr[m];
            int index = m - 1;
            while(index >= 0 && insertValue < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = insertValue;
        }
        return arr;
    }
}
