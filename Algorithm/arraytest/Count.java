package com.laioffer.Algorithm.arraytest;

import java.util.ArrayList;
import java.util.List;

public class Count {
    /*
    276. Get Count Array
    Given an array A of length N containing all positive integers from [1...N].
    How to get an array B such that B[i] represents how many elements A[j] (j > i) in array A that are smaller than A[i].
     */
    private void merge(int[] array, int[] index, int[] count, int[] helper, int left, int mid, int right) {
        for (int i=left;i<=right;i++) {
            helper[i]=index[i];
        }
        int leftIndex=left,rightIndex=mid+1,curIndex=left;
        while (leftIndex<=mid) { // sorting on index only based on array[index]
            if (rightIndex>right || array[helper[leftIndex]]<=array[helper[rightIndex]]) {
                count[helper[leftIndex]]+=(rightIndex-mid-1);
                index[curIndex++]=helper[leftIndex++];
            } else {
                index[curIndex++]=helper[rightIndex++];
            }
        }
    }
    private void mergeSort(int[] array, int[] index, int[] count, int[] helper, int left, int right) {
        if (left>=right) {return;}
        int mid=left+(right-left)/2;
        mergeSort(array,index,count,helper,left,mid);
        mergeSort(array,index,count,helper,mid+1,right);
        merge(array,index,count,helper,left,mid,right);
    }
    public int[] countArray(int[] array) { // 276
        if (array==null) {return null;}
        if (array.length==0) {return new int[0];}
        int len=array.length;
        int[] index = new int[len];
        int[] count = new int[len];
        int[] helper = new int[len];
        for (int i=0;i<len;i++) {
            index[i]=i;
        }
        mergeSort(array,index,count,helper,0,len-1);
        return count;
        // Write your solution here
    }
    /*
    277. Restore From Count Array
    Given an original unsorted array A of size n that contains all integers from  [1….n] (no duplicated numbers) but we do not know A.
    Instead, we only know a count-array B, in which B[i] represents the number of elements A[j]  (i < j) that are smaller than A[i].
    How can we re-store A based on B?
     */
    public int[] restore(int[] array) { // 277
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        int[] helper = new int[len*2];
        int[] origIndex = new int[len];
        for (int i=0;i<len;i++) {origIndex[i]=i;}
        mergeSort(array,origIndex,0,len-1,helper);
        int[] output = new int[len];
        for (int i=0;i<len;i++) {
            output[origIndex[i]]=i+1;
        }
        return output;
        // Write your solution here
    }
    private void mergeSort(int[] array, int[] origIndex, int left, int right, int[] helper) {
        if (left==right) {return;}
        int mid=left+(right-left)/2;
        mergeSort(array,origIndex,left,mid,helper);
        mergeSort(array,origIndex,mid+1,right,helper);
        merge(array,origIndex,left,mid,right,helper);
    }
    private void merge(int[] array, int[] origIndex, int left, int mid, int right, int[] helper) {
        int len=array.length;
        for (int i=left;i<=right;i++) {
            helper[i]=array[i];
            helper[i+len]=origIndex[i];
        }
        int leftIndex=left,rightIndex=mid+1,curIndex=left;
        while (leftIndex<=mid && rightIndex<=right) {
            if (helper[leftIndex]+leftIndex-curIndex<=helper[rightIndex]) {
                array[curIndex]=helper[leftIndex]+leftIndex-curIndex; // plus number coming from right side
                origIndex[curIndex++]=helper[leftIndex+++len];
            } else {
                array[curIndex]=helper[rightIndex];
                origIndex[curIndex++]=helper[rightIndex+++len];
            }
        }
        while (leftIndex<=mid) {
            array[curIndex]=helper[leftIndex]+leftIndex-curIndex;
            origIndex[curIndex++]=helper[leftIndex+++len];
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums==null) {return result;}
        int len=nums.length;
        int[] shift = new int[len];
        int[] position = new int[len];
        for (int i=0;i<len;i++) {
            position[i]=i;
            result.add(0);
        }
        countShift(nums,0,len,position,shift);
        for (int i=0;i<len;i++) {
            result.set(position[i],shift[i]);
        }
        return result;
    }
    private void countShift(int[] nums, int beg, int end, int[] position, int[] shift) {
        if (beg+1>=end) {return;}
        int mid=beg+(end-beg)/2;
        countShift(nums,beg,mid,position,shift);
        countShift(nums,mid,end,position,shift);
        int[] cachev = new int[end-beg];
        int[] caches = new int[end-beg];
        int[] cachep = new int[end-beg];
        int left=beg,right=mid,count=0;
        for (int i=beg;i<end;i++) {
            if (right==end || left<mid && nums[left]<=nums[right]) {
                caches[i-beg]=shift[left]+count;
                cachep[i-beg]=position[left];
                cachev[i-beg]=nums[left++];
            } else {
                caches[i-beg]=shift[right];
                cachep[i-beg]=position[right];
                cachev[i-beg]=nums[right++];
                count++;
            }
        }
        for (int i=beg;i<end;i++) {
            nums[i]=cachev[i-beg];
            shift[i]=caches[i-beg];
            position[i]=cachep[i-beg];
        }
    }
    public int countWays(int[] input) {
        if (input==null || input.length==0) {return 0;}
        int len=input.length,num=1,count=0;
        boolean firstOne = false;
        for (int i=0;i<len;i++) {
            if (input[i]==0) {
                count++;
            } else {
                if (!firstOne) {
                    firstOne=true;
                } else {
                    num*=count+1;
                }
                count=0;
            }
            System.out.println(i+" "+count+" "+num);
        }
        return firstOne?num:0;
        // Write your solution here
    }
    public static void main(String[] args) {
        Count solution = new Count();
        int[] nums = new int[] {125,484,15,320,369,123,156,473,221,496,413,369,8,73,256};//,490,40,420,371,16,356,239,395,54,344,363,122,62,33,200,356,462,177,179,54,77,453,13,243,409,382,141,348,342,286,158,89,140,321,31,313,292,283,18,93,77,406,407,94,248};
        System.out.println(solution.countSmaller(nums));
        int[] input = new int[]{0,0,1,1,0,1,0,0,0,0,1,0,1,0,1,0,1,0};
        System.out.println(solution.countWays(input));
    }
}
