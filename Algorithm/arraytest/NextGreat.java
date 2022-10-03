package com.laioffer.Algorithm.arraytest;

public class NextGreat {
    public int nextGreaterElement(int n) {
        if (n<10) {return -1;}
        char[] input = String.valueOf(n).toCharArray();
        int len=input.length;
        int index=-1;
        for (int i=len-1;i>0;i--) {
            if (input[i]>input[i-1]) {
                int min=input[i],minIndex=i;
                for (int j=i+1;j<len;j++) {
                    if (input[j]<min && input[j]>input[i-1]) {
                        min=input[j];
                        minIndex=j;
                    }
                }
                swap(input,minIndex,i-1);
                System.out.println(input);
                char[] helper = new char[len];
                mergeSort(input,i,len-1,helper);
                System.out.println(input);
                return Integer.parseInt(String.valueOf(input));
            }
        }
        return -1;
        // Write your solution here
    }
    private void mergeSort(char[] input, int left, int right, char[] helper) {
        if (left==right) {return;}
        int mid=left+(right-left)/2;
        mergeSort(input,left,mid,helper);
        mergeSort(input,mid+1,right,helper);
        merge(input,left,mid,right,helper);
    }
    private void merge(char[] input, int left, int mid, int right, char[] helper) {
        System.out.println(left+" "+mid+" "+right);
        for (int i=left;i<=right;i++) {
            helper[i]=input[i];
        }
        int leftIndex=left, rightIndex=mid+1, curIndex=left;
        while (leftIndex<=mid && rightIndex<=right) {
            System.out.println(helper[leftIndex]);
            System.out.println(helper[rightIndex]);
            if (helper[leftIndex]<=helper[rightIndex]) {
                input[curIndex++]=helper[leftIndex++];
            } else {
                input[curIndex++]=helper[rightIndex++];
            }
        }
        while (leftIndex<=mid) {
            input[curIndex++]=helper[leftIndex++];
        }
    }
    private void swap(char[] input, int left, int right) {
        char tmp = input[left];
        input[left]=input[right];
        input[right]=tmp;
    }
    /*
    238. Next Permutation
    Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers. If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
    The replacement must be in-place, do not allocate extra memory.
     */
    public int[] nextPermutation(int[] num) { // 238
        if (num==null) {return num;}
        int len=num.length;
        if (len<=1) {return num;}
        int node=-1; // find the rightmost node with increasing order
        for (int i=len-1;i>0;i--) {
            if (num[i]>num[i-1]) {node=i-1;break;}
        }
        if (node==-1) { // reverse order to lowest possible order
            reverse(num,0,len-1);
        } else if (node==len-2) { // next larger is just swith last 2 digits
            swap(num,len-1,len-2);
        } else {
            swap(num,node,binarySearch(num,node+1,len-1,num[node])); // after swapping, the right part of the array should be decreasing
            reverse(num,node+1,len-1);
        }
        return num;
        // Write your solution here
    }
    private int binarySearch(int[] array, int left, int right, int target) {
        while (left<right-1) {
            int mid=left+(right-left)/2;
            if (array[mid]>target) {
                left=mid;
            } else { // array[mid]<target
                right=mid;
            }
        }
        if (array[right]>target) {
            return right;
        } else {
            return left;
        }
    }
    private void reverse(int[] array, int left, int right) {
        while (left<right) {
            swap(array,left++,right--);
        }
    }
    private void swap(int[] array, int left, int right) {
        int temp=array[left];
        array[left]=array[right];
        array[right]=temp;
    }
    /*
    487. The Next Permutation
    For this problem, you will write a program that takes a (possibly long) string of decimal digits, and outputs the permutation of those decimal digits that has the next larger value (as a decimal number) than the input number. For example:
    123 -> 132
    279134399742 -> 279134423799
    It is possible that no permutation of the input digits has a larger value. For example, 987.
    Input: A string with up to 80 decimal digits which is the input value.
    Output: If there is no larger permutation of the input digits, the output should be the string BIGGEST. If there is a solution, the output should be the next larger permutation of the input digits.
     */
    public String getNextPermutation(String input) { // 487
        if (input==null || input.length()==0) {return input;}
        char[] array = input.toCharArray();
        int len=array.length;
        if (len<=1) {return input;}
        int node=-1; // find the rightmost node with increasing order
        for (int i=len-1;i>0;i--) {
            if (array[i]>array[i-1]) {node=i-1;break;}
        }
        if (node==-1) { // reverse order in lowest possible order
            return "BIGGEST";
        } else if (node==len-2) { // next larger is just switch last 2 digits
            swap(array,len-1,len-2);
        } else {
            int tar=len; // find the smallest number which is larger than the found node
            for (int i=len-1;i>node;i--) {
                if (array[i]>array[node]) {tar=i;break;}
            }
            swap(array,node,tar); // after swapping, the right part of the array should sort from decreasing to incrasing
            for (int i=node+1;i<=(node+len)/2;i++) {
                swap(array,i,len+node-i);
            }
        }
        return new String(array);
        // Write your solution here
    }
//    private void swap(char[] array, int left, int right) {
//        char temp=array[left];
//        array[left]=array[right];
//        array[right]=temp;
//    }
    public static void main(String[] args) {
        NextGreat solution = new NextGreat();
        System.out.println(solution.nextGreaterElement(384029284));
    }
}
