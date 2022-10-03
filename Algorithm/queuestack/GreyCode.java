package com.laioffer.Algorithm.queuestack;
import java.util.*;

public class GreyCode {
    /*
    169. Gray Code
    The gray code is a binary numeral system where two successive values differ in only one bit.
    For example: given n = 2, return [0,1,3,2], the gray code sequence is:
         00 - 0
         01 - 1
         11 - 3
         10 - 2
         Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
    E.g.    Input: n = 2
          Output: [0,1,3,2]
     */
    public List<Integer> graycode(int n) { // 169
        if (n<=0) {return null;}
        //Input your code here
        List<Integer> result = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        result.add(0);
        for (int i=1,pow=1;i<=n;i++,pow*=2) {
            for (int res : result) {
                stack.offerFirst(res);
            }
            while (!stack.isEmpty()) {
                result.add(stack.pollFirst()+pow);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        GreyCode solution = new GreyCode();
    }
}
