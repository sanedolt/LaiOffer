package com.laioffer.Algorithm.math;
import java.util.*;

public class Happy {
    /*
    409. Happy Number
    Write an algorithm to determine if a number is "happy".
    A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
    Example: 19 is a happy number
    12 + 92 = 82
    82 + 22 = 68
    62 + 82 = 100
    12 + 02 + 02 = 1
     */
    public boolean isHappy(int n) { // 409
        if (n<1) {return false;}
        if (n==1) {return true;}
        Set<Integer> appeared = new HashSet<>();
        int m=n;
        appeared.add(m);
        while (m!=1) {
            int sum=0;
            while (m>0) {
                int tmp=m%10;
                sum+=tmp*tmp;
                m/=10;
            }
            if (sum==1) {return true;}
            if (!appeared.add(sum)) {return false;}
            m=sum;
        }
        return false;
        // Write your solution here
    }
    public static void main(String[] args) {
        Happy solution = new Happy();
    }
}
