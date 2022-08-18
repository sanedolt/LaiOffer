package com.laioffer.DFS;
import java.util.*;

public class Monotone {
    /*
    601. Monotone Increasing Digits
    Given a non-negative integer N, find the largest number that is less than or equal to Nwith monotone increasing digits.
    (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)
    Example 1:
    Input: N = 10
    Output: 9
    Example 2:
    Input: N = 1234
    Output: 1234
    Example 3:
    Input: N = 332
    Output: 299
    Note: N is an integer in the range [0, 10^9].
     */
    public int monotoneIncreasingDigits(int N) { // 601
        if (N<=0) {return 0;}
        if (N<10) {return N;}
        char[] array = String.valueOf(N).toCharArray();
        helper(array,0);
        return Integer.parseInt(String.valueOf(array));
        // Write your solution here
    }
    private void helper(char[] array, int index) {
        int len=array.length;
        if (index==len) {return;}
        int ref=array[index]-'0',sum=0;
        for (int i=index;i<len;i++) {
            sum=sum*10+ref;
        } // current value beyond index
        if (Integer.parseInt(String.valueOf(Arrays.copyOfRange(array,index,len)))<sum) { // to keep the first digit, N must be bigger than 111..1*D
            array[index]--;
            for (int i=index+1;i<len;i++) {
                array[i]='9';
            }
        } else {
            helper(array,index+1);
        }
    }
    public static void main(String[] args) {
        Monotone solution = new Monotone();
    }
}
