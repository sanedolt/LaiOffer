package com.laioffer.math;
import java.util.*;

public class Majority {
    /*
    206. Majority Number I
    Given an integer array of length L, find the number that occurs more than 0.5 * L times.
    Assumptions
    The given array is not null or empty
    It is guaranteed there exists such a majority number
    Examples
    A = {1, 2, 1, 2, 1}, return 1
     */
    public int majority206(int[] array) { // 206
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        int candidate = array[0];
        int count=1;
        for (int i=1;i<len;i++) {
            if (count==0) {
                candidate=array[i];
                count=1;
            } else if (candidate==array[i]) {
                count++;
            } else { // candidate!=array[i]
                count--;
            }
        }
        return candidate;
        // Map<Integer, Integer> count = new HashMap<>();
        // for (int i=0;i<len;i++) {
        //   Integer cn = count.get(array[i]);
        //   if (cn==null) {
        //     count.put(array[i],1);
        //   } else {
        //     if (++cn>len/2) {return array[i];}
        //     count.put(array[i],cn);
        //   }
        // }
        // return -1;
        // Write your solution here
    }
    /*
    207. Majority Number II
    Given an integer array of length L, find all numbers that occur more than 1/3 * L times if any exist.
    Assumptions
    The given array is not null
    Examples
    A = {1, 2, 1, 2, 1}, return [1, 2]
    A = {1, 2, 1, 2, 3, 3, 1}, return [1]
    A = {1, 2, 2, 3, 1, 3}, return []
     */
    public List<Integer> majority207(int[] array) { // 207
        List<Integer> result = new ArrayList<Integer>();
        if (array==null || array.length==0) {return result;}
        int len=array.length;
        int candidate1=0,candidate2=0,count1=0,count2=0;
        for (int i=0;i<len;i++) {
            if (count1>0 && array[i]==candidate1) {
                ++count1;
            } else if (count2>0 && array[i]==candidate2) {
                ++count2;
            } else if (count1==0) {
                candidate1=array[i];
                count1=1;
            } else if (count2==0) {
                candidate2=array[i];
                count2=1;
            } else { // if (count1>0 && count2>0) {
                count1--;
                count2--;
            }
        }
        if (count1==0) {candidate1=Integer.MIN_VALUE;}
        if (count2==0) {candidate2=Integer.MIN_VALUE;}
        count1=count2=0;
        for (int i=0;i<len;i++) {
            if (array[i]==candidate1) {count1++;}
            if (array[i]==candidate2) {count2++;}
        }
        if (count1>len/3) {result.add(candidate1);}
        if (count2>len/3) {result.add(candidate2);}
        // Map<Integer, Integer> count = new HashMap<>();
        // for (int i=0;i<len;i++) {
        //   Integer cn = count.get(array[i]);
        //   if (cn==null) {
        //     count.put(array[i],1);
        //     if (len<3) {result.add(array[i]);}
        //   } else {
        //     if (cn<=len/3 && cn+1>len/3) {result.add(array[i]);}
        //     count.put(array[i],cn+1);
        //   }
        // }
        return result;
        // Write your solution here
    }
    /*
    208. Majority Number III
    Given an integer array of length L, find all numbers that occur more than 1/K * L times if any exist.
    Assumptions
    The given array is not null or empty
    K >= 2
    Examples
    A = {1, 2, 1, 2, 1}, K = 3, return [1, 2]
    A = {1, 2, 1, 2, 3, 3, 1}, K = 4, return [1, 2, 3]
    A = {2, 1}, K = 2, return []
     */
    public List<Integer> majority208(int[] array, int k) { // 208
        List<Integer> result = new ArrayList<Integer>();
        if (array==null || array.length==0) {return result;}
        int len=array.length;
        int[] candidate = new int[k-1];
        int[] count = new int[k-1];
        fori: for (int i=0;i<len;i++) {
            for (int j=0;j<k-1;j++) {
                if (count[j]>0 && array[i]==candidate[j]) {count[j]++;continue fori;}
            }
            for (int j=0;j<k-1;j++) {
                if (count[j]==0) {candidate[j]=array[i];count[j]=1;continue fori;}
            }
            for (int j=0;j<k-1;j++) {
                count[j]--;
            }
        }
        int left=0,right=k-2;
        while (left<=right) {
            if (count[left]>0) {
                count[left++]=0;
            } else if (count[right]==0) {
                right--;
            } else {
                candidate[left]=candidate[right--];
                count[left++]=0;
            }
        }
        for (int i=0;i<len;i++) {
            for (int j=0;j<left;j++) {
                if (array[i]==candidate[j]) {count[j]++;}
            }
        }
        for (int j=0;j<left;j++) {
            if (count[j]>len/k) {result.add(candidate[j]);}
        }
        // Map<Integer, Integer> count = new HashMap<>();
        // for (int i=0;i<len;i++) {
        //   Integer cn = count.get(array[i]);
        //   if (cn==null) {
        //     count.put(array[i],1);
        //     if (len<k) {result.add(array[i]);}
        //   } else {
        //     if (cn<=len/k && cn+1>len/k) {result.add(array[i]);}
        //     count.put(array[i],cn+1);
        //   }
        // }
        return result;
        // Write your solution here
    }
    public static void main(String[] args) {
        Majority solution = new Majority();
    }
}
