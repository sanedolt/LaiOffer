package com.laioffer.math;

public class CountDigitOne {
    /*
    453. Number of Digit One
    Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
    For example:
    Given n = 13,
    Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
     */
    public int countDigitOne(int n) { // 453
        if (n<=0) {return 0;}
        int num=0,pw=1,pp=1;
        while (pp<=n) {
            pw=pp*10;
            num+=(int)(n/pw)*pp; // each digit from last
            if (n%pw>=pp) {num+=Math.min(pp,n%pw-pp+1);} // the checking digit is at least 1, either entire or part of 1****
            pp=pw;
        }
        return num;
        // Write your solution here
    }
    public static void main(String[] args) {
        CountDigitOne solution = new CountDigitOne();
        System.out.println(solution.countDigitOne(10));
    }
}
