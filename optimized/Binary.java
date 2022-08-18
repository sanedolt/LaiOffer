package com.laioffer.optimized;

import java.util.ArrayList;
import java.util.List;

public class Binary {
    /*
    509. Flip Game
    You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
    Write a function to compute all possible states of the string after one valid move.
    For example, given s = "++++", after one move, it may become one of the following states:
    [
    "--++",
    "+--+",
    "++--"
    ]
    If there is no valid move, return an empty list [].
     */
    public List<String> generatePossibleNextMoves(String input) {
        List<String> result = new ArrayList<>();
        if (input==null || input.length()==0) {return result;}
        int len=input.length();
        char[] cur = input.toCharArray();
        for (int i=0;i<len-1;i++) {
            if (cur[i]=='+' && cur[i+1]=='+') {
                cur[i]=cur[i+1]='-';
                result.add(new String(cur));
                cur[i]=cur[i+1]='+';
            }
        }
        return result;
        // Write your solution here
    }
    /*
    510. Flip Game II
    You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
    Write a function to determine if the starting player can guarantee a win.
    For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
     */
    public boolean canWin(String input) { // 510
        if (input==null || input.length()==0) {return false;}
        int len=input.length();
        int[] num = new int[8]; // save appearence of consecutive +, no need to save mroe
        int count=0;
        for (int i=0;i<len;i++) {
            if (input.charAt(i)=='+') {
                count++;
            }
            if (input.charAt(i)=='-' || i==len-1) {
                num[count<7?count:7]++;
                count=0;
            } // end if
        } // end for
        // either consecutive 4 or 6 (select 2&3 from one side), or number of consecutive 2 or 3
        return num[4]+num[6]==1 || (num[2]+num[3])%2==1;
        // Write your solution here
    }
    /*
    488. Adjacent Bit Count
    For a string of n bits x1, x2, x3, …, xn the adjacent bit count of the string (AdjBC(x)) is given by
    x1*x2 + x2*x3 + x3*x4 + … + xn-1*xn
    which counts the number of times a 1 bit is adjacent to another 1 bit. For example:
    AdjBC(011101101) = 3
    AdjBC(111101101) = 4
    AdjBC(010101010) = 0
    Write a program which takes as input integers n and k and returns the number of bit strings x of n bits (out of 2ⁿ) that satisfy AdjBC(x) = k. For example, for 5 bit strings, there are 6 ways of getting AdjBC(x) = 2:
    11100, 01110, 00111, 10111, 11101, 11011
    Input: A decimal integer giving the number (n) of bits in the bit strings, and a decimal integer (k) giving the desired adjacent bit count. The number of bits (n) will not be greater than 100 and the parameters n and k will be chosen so that the result will fit in a signed 32-bit integer.
    Output: the number of n-bit strings with adjacent bit count equal to k.
     */
    public int getAdjBCStringCount(int n, int k) { // 488
        if (n<=0 || k>n-1 || k<0) {return 0;}
        if (n==1) {return 2;}
        int[][] count = new int[n+1][k+1];
        count[0][0]=1;
        count[1][0]=2;
        // considering adding 0 or 10 or 110 or 1110 ... or 111..1 in front of previous strings
        for (int i=2;i<=n;i++) {
            if (i-1<=k) {count[i][i-1]=1;} // all 1
            for (int j=0;j<=k&&j<i-1;j++) {
                count[i][j]=count[i-1][j]; // just put 0 in front of previous string
                for (int l=j;l>=0 && i+l-j>=2;l--) { // put 10, 110... in front, so there won't be overlap either
                    count[i][j]+=count[i+l-j-2][l];
                }
            }
        }
        return count[n][k];
        // Write your solution here
    }
    /*
    580. Replace 0 with 1
    Given an integer array contains only 0s and 1s and a non-negative number n. The given array strictly follows the rule that there is no consecutive 1s in this array. In other word, 1 cannot be placed next to 1.
    Your job is to find out whether we could replace n 0s with 1s and still keep the same rule. Return true if we could otherwise false.
    Example 1:
    Input: array = [0,0,0,1,0,1]; n = 1
    Output: True
    Explanation: We can replace one 0 (the first or the second 0) with 1 and still follow the rule.
    Example 2:
    Input: array = [0,1,0,1,0,1]; n = 1
    Output: False
    Explanation: We cannot replace any 0 with 1 and still follow the rule.
    Assumptions:
    0 <= n <= length of given array.
     */
    public boolean canReplace(int[] array, int n) { // 580
        if (array==null || array.length<n*2) {return false;}
        int len=array.length;
        int left=-1;
        for (int right=0;right<=len;right++) {
            if (right==len || array[right]==1) {
                if (right==len) {right++;} // get 2 space to create 1 spot
                n-=(right-left-1)/2;
                left=right+1;
            }
        }
        return n<=0;
        // Write your solution here
    }
    public static void main(String[] args) {
        Binary solution = new Binary();
    }
}
