package com.laioffer.Algorithm.optimized;
import java.util.*;

public class Dictionary {
    public boolean canBreak(String input, String[] dict) { // 99
        if (dict==null || input==null) {return false;}
        Set<String> dictSet = getSet(dict);
        int len=input.length();
        if (len==0) {return dictSet.contains("");}
        boolean[] dp = new boolean[len+1]; // whether can be composed after length index
        dp[0]=true; // length=0 ""
        for (int i=1;i<=len;i++) { // length
            for (int j=0;j<i;j++) {
                if (dp[j] && dictSet.contains(input.substring(j,i))) {
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[len];
        // Write your solution here
    }
    private Set<String> getSet(String[] dictionary) {
        Set<String> dict = new HashSet<>();
        for (String d : dictionary) {
            dict.add(d);
        }
        return dict;
    }
    public int minCuts(String input) { // 143
        if (input==null || input.length()==0) {return 0;}
        char[] array = input.toCharArray();
        int len=array.length;
        int[][] dp = new int[len][len]; // record the fewest cuts from first index to second index
        for (int j=0;j<len;j++) { // second index
            for (int i=j;i>=0;i--) { // first index
                if (i==j) {
                    dp[i][j]=0;
                } else if (i==j-1) {
                    dp[i][j]=array[i]==array[j]?0:1;
                } else if (isPalindrome(array,i,j)) {
                    dp[i][j]=0;
                } else { // from i to j is not a palindrome, must have cut
                    dp[i][j]=j-i;
                    for (int k=i+1;k<=j;k++) { // whether to have a cut there
                        if (isPalindrome(array,k,j)) {
                            dp[i][j]=Math.min(dp[i][j],dp[i][k-1]+1);
                            // the left side is the fewest cut between index i and index k-1 which can be refer to dp[i][k-1]
                            // the right part==1 is the cut on the left of k and the substring k to j is a palindrome
                        } // end if
                    } // end for k
                } // end if
            } // end for i
        } // end for j
        System.out.println(Arrays.deepToString(dp));
        return dp[0][len-1];
    }
    private boolean isPalindrome(char[] array, int left, int right) {
        while (left<right) {
            if (array[left++]!=array[right--]) {return false;}
        }
        return true;
    }
    /*
    308. Least Insertions To Form A Palindrome
    Insert the least number of characters to a string in order to make the new string a palindrome. Return the least number of characters should be inserted.
     */
    public int leastInsertion(String input) { // 308
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
        int[][] dp = new int[len][len]; // how many characters in palindrome pattern between index i and j
        for (int i=1;i<=len;i++) { // len
            for (int left=0;left<=len-i;left++) {
                int right=left+i-1;
                if (i==1) {
                    dp[left][right]=1;
                } else if (input.charAt(left)==input.charAt(right)) {
                    dp[left][right]=dp[left+1][right-1]+2;
                } else {
                    dp[left][right]=Math.max(dp[left][right-1],dp[left+1][right]);
                }
            } // end for left
        } // end for i
        return len-dp[0][len-1];
        // Write your solution here
    }
    public static void main(String[] args) {
        Dictionary solution = new Dictionary();
        String input="aaaaaabbabb";
        System.out.println(solution.minCuts(input));
    }
}
