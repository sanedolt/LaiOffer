package com.laioffer.math;
import java.util.*;

public class Plus {
    /*
    162. Plus One
    Given a non-negative number represented as an array of digits, plus one to the number.
    Input: [2, 5, 9]
    Output: [2, 6, 0]
     */
    public int[] plus(int[] digits) { // 162
        if (digits==null || digits.length==0) {return digits;}
        int len=digits.length;
        List<Integer> ans = new ArrayList<>();
        int carry=1;
        for (int i=len-1;i>=0;i--) {
            int tmp=digits[i]+carry;
            if (tmp<10) {
                ans.add(tmp);carry=0;
            } else {
                ans.add(0);
            }
        }
        if (carry==1) {ans.add(1);}
        int size=ans.size();
        int[] result = new int[size];
        for (int i=0;i<size;i++) {
            result[size-1-i]=ans.get(i);
        }
        // String input = Arrays.toString(digits).replaceAll("\\[|\\]|,|\\s", "");
        // int num = Integer.valueOf(input);
        // len+=(++num)/(int)Math.pow(10,len);
        // int[] result = new int[len];
        // for (int i=len-1;i>=0;i--) {
        //   result[i]=num%10;
        //   num/=10;
        // }
        return result;
        // Write your solution here
    }

    public static void main(String[] args) {
        Plus solution = new Plus();
    }
}
