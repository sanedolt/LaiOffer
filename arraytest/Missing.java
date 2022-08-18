package com.laioffer.arraytest;
import java.util.*;

public class Missing {
    public int missing(int[] array) { // 68
        if (array==null || array.length==0) {return 1;}
        int len=array.length;
        int xc = 0;
        for (int i=0;i<len;i++) {
            xc^=array[i];
        }
        for (int i=1;i<=len+1;i++) {
            xc^=i;
        }
        return xc;
        // Write your solution here
    }
    public int missing2(int[] array) { // 69
        if (array==null || array.length==0) {return 1;}
        int len=array.length;
        int left=0,right=len-1;
        while (left<right-1) {
            int mid=left+(right-left)/2;
            if (array[mid]-mid==1) {
                left=mid+1;
            } else {
                right=mid;
            }
        }
        if (array[left]-left==2) {
            return left+1;
        } else if (array[right]-right==2) {
            return right+1;
        } else {
            return right+2;
        }
        // Write your solution here
    }
    /*
    597. Missing Number III
    Given an integer array supposedly contains numbers of (1, 2, 3....N) where 2 <= N <= 10000. The numbers are unordered. However there is one number occurs twice so that another number is missing. Return an array these two numbers where the first element is the duplicated number, and the second element is the missing number.
    Example:
    Input = [1, 1, 3, 4]
    Output = [1, 2]
     */
    public int[] missingNumber(int[] nums) { // 597
        if (nums==null) {return null;}
        int[] result = new int[2];
        int len=nums.length;
        if (len==0) {return result;}
        Set<Integer> appeared = new HashSet<>();
        int sum=0;
        for (int i=0;i<len;i++) {
            if (!appeared.contains(nums[i])) {
                appeared.add(nums[i]);
            } else {
                result[0]=nums[i];
            }
            sum+=nums[i];
        }
        result[1]=(1+len)*len/2-(sum-result[0]);
        return result;
        // Write your solution here
    }
    public int findFirstMissingElement(int[] array) {
        if (array==null || array.length==0) {return 0;}
        int left=0,right=array.length-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]==mid) {
                left=mid+1;
            } else { //array[mid]>mid
                right=mid;
            }
        }
        if (array[left]==left) {
            return array.length;
        } else {
            return left;
        }
    }
    /*
    227. First Missing Positive II
    Given an unsorted integer array, find the first missing positive integer.
    Example
    Given [0, 2, 3, 1], return 4,
    and [3, 4, -2, 1, -4] return 2.
     */
    public int firstMissingPositive(int[] input) { // 227
        if (input==null || input.length==0) {return 1;}
        Arrays.sort(input);
        int index = binarySearch(input,0);
        if (index==-1 || input[index]>1) {return 1;}
        int index2 = binarySearch2(input,index);
        return index2>-1?input[index2-1]+1:input[input.length-1]+1;
        // Write your solution here
    }
    private int binarySearch(int[] array, int target) {
        int left=0,right=array.length-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]<=target) { // bigger than target
                left=mid+1;
            } else { // array[mid]>target
                right=mid;
            }
        }
        return array[left]>target?left:-1;
    }
    private int binarySearch2(int[] array, int index) {
        int left=index,right=array.length-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]==mid-index+1) { // array[index]=1, is array[mid]=mid?
                left=mid+1;
            } else { // array[mid]>mid-index+1
                right=mid;
            }
        }
        return array[left]>left-index+1?left:-1;
    }
    /*
    464. Patching Array
    Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n]inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
    Example 1:
    nums = [1, 3], n = 6
    Return 1.
    Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
    Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
    Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
    So we only need 1 patch.
    Example 2:
    nums = [1, 5, 10], n = 20
    Return 2.
    The two patches can be [2, 4].
    Example 3:
    nums = [1, 2, 2], n = 5
    Return 0.
     */
    public int minPatches(int[] nums, int n) { 、、 464
        if (nums==null || nums.length==0) {return (int) (Math.log(n+1)/Math.log(2));}
        int count = 0, i = 0;
        long miss = 1; // use long to avoid integer overflow error
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) // miss is covered
                miss += nums[i++];
            else { // patch miss to the array
                miss += miss;
                count++; // increase the answer
            }
        }
        return count;
        // Write your solution here
    }
    public static void main(String[] args) {
        Missing solution = new Missing();
//        int[] a=new int[] {12,11,10,9,4,5,6,7,2,3,8};
//        System.out.println(solution.missing(a));
        int[] input = new int[]{1,-2,3,5,2};
        System.out.println(solution.firstMissingPositive(input));
    }
}
