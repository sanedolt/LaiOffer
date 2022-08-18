package com.laioffer.math;

public class Permutation {
    /*
    167. Permutation Sequence
    The set [1,2,3,…,n] contains a total of n! unique permutations, return kth smallest permutation.
    If k > n! return "";
     */
    public String getPermutation(int n, int k) { // 167
        char[] result = new char[n];
        int fac=1;
        for (int i=1;i<=n;i++) {
            fac*=i;
            result[i-1]=(char)(i+48);
        }
        if (k>fac) {return "";}
        helper(k,result,0,fac/n);
        return new String(result);
        // Write your solution here
    }
    private void helper(int k, char[] array, int index, int fac) {
        int n=array.length;
        if (index==n-1) {return;}
        int num = (k-1)/fac;
        char temp = array[index+num];
        for (int i=index+num;i>index;i--) {
            array[i]=array[i-1];
        }
        array[index++]=temp;
        helper(k-num*fac,array,index,fac/(n-index));
    }
    public static void main(String[] args) {
        Permutation solution = new Permutation();
    }
}
