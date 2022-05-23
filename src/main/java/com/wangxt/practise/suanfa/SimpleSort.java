package com.wangxt.practise.suanfa;

import java.util.Arrays;

public class SimpleSort {

    public static void main(String[] args) {
        int[] arr = {3,4,78,1,3,7,4,3,6,1};

        print(arr);
        insertSort(arr);
        print(arr);
    }

    /**
     * 选择排序
     * 0~length-1选出最小的放index=0
     * 1~length-1选出最小的放index=1
     * n~length-1选出最小的放index=n
     * @param arr
     */
    private static void selectSort(int[] arr) {
        for(int n=0;n<arr.length;n++){
            int minIndex = n;
            for(int findIndex = n+1;findIndex<arr.length;findIndex++){
                if(arr[findIndex] < arr[minIndex]){
                    minIndex = findIndex;
                }
            }
            swap(arr,n,minIndex);
        }
    }

    /**
     * 冒泡排序
     * 0~length-1 两两比较找出大值放index=length-1
     * 0~length-2 两两比较找出最大值放index=length-2
     * 0~n 两两比较找出最大值放index=n
     * @param arr
     */
    private static void maoPaoSort(int[] arr){
        for(int n=arr.length; n > 0;n--){
            for(int sortIndex=0;sortIndex<n-1;sortIndex++){
                if(arr[sortIndex] > arr[sortIndex+1]){
                    swap(arr,sortIndex,sortIndex+1);
                }
            }
        }
    }

    /**
     * 插入排序
     * 0~0排序
     * 0~1排序 index=1往左看
     * 0~2排序
     * 0~length-1排序
     *
     */
    private static void insertSort(int[] arr){
        for(int n=0;n<arr.length;n++){
            int curIndex = n;
            while(curIndex > 0 && arr[curIndex-1] > arr[curIndex]){
                swap(arr,curIndex,curIndex-1);
                curIndex--;
            }
        }
    }

    private static void swap(int[] arr, int n, int minIndex) {
        int temp = arr[n];
        arr[n] = arr[minIndex];
        arr[minIndex] = temp;
    }

    private static void print(int[] arr){
        System.out.println(Arrays.toString(arr));
    }
}
