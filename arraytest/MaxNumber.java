package com.laioffer.arraytest;
import java.util.Arrays;

public class MaxNumber {
    /*
    287. Form Largest Number
    Given an array of numbers in string type, sort them in a way that the concatenation of them yields the largest value. Return the largest number in string type.
    Assumptions:
    The given array is not null.
    Each of the strings in the array is not null and they are all numbers.
    Examples:
    {“54”, “546”, “648”, “88”}, the arrangement “8864854654” gives the largest value.
     */
    private boolean biggerStr(String a, String b) {
        if (b==null || b.length()==0) {return true;}
        if (a==null || a.length()==0) {return false;}
        int la=a.length(),lb=b.length(),index=0;
        while (true) {
            char ca=index<la?a.charAt(index):b.charAt(index-la);
            char cb=index<lb?b.charAt(index):a.charAt(index-lb);
            if (ca>cb) {return true;}
            if (cb>ca) {return false;}
            // ca==cb
            index++;
            if (index==la && index==lb || index==la+lb) {return true;} // a&b are identical or a/b combination are identical
        }
    }
    private void sortStr(String[] input, String[] helper, int left, int right) {
        if (left==right) {return;}
        int mid=left+(right-left)/2;
        sortStr(input,helper,left,mid);
        sortStr(input,helper,mid+1,right);
        merge(input,helper,left,mid,right);
    }
    private void merge(String[] input, String[] helper, int left, int mid, int right) {
        for (int i=left;i<=right;i++) {
            helper[i]=input[i];
        }
        int leftIndex=left,rightIndex=mid+1,curIndex=left;
        while (leftIndex<=mid && rightIndex<=right) {
            if (biggerStr(helper[leftIndex],helper[rightIndex])) {
                input[curIndex++]=helper[leftIndex++];
            } else {
                input[curIndex++]=helper[rightIndex++];
            }
        }
        while (leftIndex<=mid) {
            input[curIndex++]=helper[leftIndex++];
        }
    }
    public String largestNumber(String[] input) { // 287
        if (input==null || input.length==0) {return null;}
        int len=input.length;
        String[] helper = new String[len];
        sortStr(input,helper,0,len-1);
        StringBuilder result = new StringBuilder();
        boolean nz=false;
        for (int i=0;i<len;i++) {
            result.append(input[i]);
            if (Integer.valueOf(input[i])>0) {nz=true;}
        }
        if (nz) {return new String(result);} else {return "0";}
        // Write your solution here
    }
    /*
    455. Create Maximum Number
    Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.
    Example 1:
    nums1 = [3, 4, 6, 5]
    nums2 = [9, 1, 2, 5, 8, 3]
    k = 5
    return [9, 8, 6, 5, 3]
    Example 2:
    nums1 = [6, 7]
    nums2 = [6, 0, 4]
    k = 5
    return [6, 7, 6, 0, 4]
    Example 3:
    nums1 = [3, 9]
    nums2 = [8, 9]
    k = 3
    return [9, 8, 9]
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if (nums1==null || nums2==null) {return null;}
        int l1=nums1.length,l2=nums2.length;
        if (k<=0 || k>l1+l2) {return new int[0];}
        int beg = Math.max(0,k-l2);
        int end = Math.min(k,l1);
        int[] result = new int[k];
        int[] tmp=null;
        for (int i=beg;i<=end;i++) { // numbre of digit from first array
            //find max subseqences
            int[] sub1 = getMaxSubsequence(nums1,i);
            int[] sub2 = getMaxSubsequence(nums2,k-i);
            //merge array
            tmp = merge(sub1,sub2);
            //store max in result
            if (isGreater(tmp,0,result,0)) {
                result = tmp;
            }
        }
        return result;
    }

    //decreasing Monostack here max odering we can do is numlen-i-1>=remain keeping rest untouched
    private int[] getMaxSubsequence(int[] num, int maxSubArrCnt){
        Deque<Integer> stack = new ArrayDeque<>();
        int remain=maxSubArrCnt, len=num.length;
        for (int i=0;i<len;i++){
            while (!stack.isEmpty() && stack.peek()<num[i] && len-1-i>=remain){
                stack.pop();
                remain++;
            }
            if (remain>0) {
                stack.push(num[i]);
                remain--;
            }
        }

        int[] maxSubArr = new int[maxSubArrCnt];
        len = maxSubArrCnt-1;
        while (!stack.isEmpty()) {
            maxSubArr[len--] = stack.pop();
        }
        return maxSubArr;
    }

    private int[] merge(int[] num1, int[] num2){
        int[] res = new int[num1.length+num2.length];
        int l1=0,l2=0,l3=0;
        while (l3<res.length) {
            res[l3++] = isGreater(num1,l1,num2,l2)? num1[l1++]:num2[l2++];
        }
        return res;
    }

    private boolean isGreater(int[] num1, int l1, int[] num2, int l2){
        while (l1<num1.length && l2<num2.length) {
            if (num1[l1]==num2[l2]) {
                l1++;l2++;
            } else {
                return num1[l1]>num2[l2];
            }
        }
        return l1!=num1.length;
    }
    public static void main(String[] args) {
        MaxNumber solution = new MaxNumber();
        //int[] nums1 = new int[]{6,3,8,9,1,2,1,7,8,5,2,8,6,3,2};
        //int[] nums2 = new int[]{3,4,0,6,6,9,5,7,0,2,5,8,7,9,4,0,7,0,6,2,2,0,8,1,6,8,2,9,2,7,6,2,7,5,3,1,6,4,6,8,2,0,1,7,7,6};
        int[] nums1 = new int[]{9,4,6,7,2,0,6,8,5,3,0,6,6,0,9,5,7,5,2};
        int[] nums2 = new int[]{9,3,3,9,0,6,2,3,1,8};
        System.out.println(Arrays.toString(solution.maxNumber(nums1,nums2,4)));
    }
}
