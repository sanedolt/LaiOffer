package com.laioffer.optimized;
import java.util.*;

public class Match {
    public int editDistance(String one, String two) { // 100
        if (one==null || two==null) {return 0;}
        int lo=one.length(),lt=two.length();
        int[] dp = new int[lt+1]; // length of two to save SC, first index should be length in one, second index is index in two
        for (int i=lo;i>=0;i--) {
            int br = lo-(i+1); // from ending of word, need [i+1][j+1]
            for (int j=lt;j>=0;j--) {
                if (i==lo) {dp[j]=lt-j;continue;}
                if (j==lt) {dp[j]=lo-i;continue;}
                int temp = dp[j];
                dp[j]=Math.min(Math.min(dp[j],dp[j+1])+1,br+(one.charAt(i)==two.charAt(j)?0:1));
                br = temp;
            }
        }
        return dp[0];
        // Write your solution here
    }
    public int editDistance2(String one, String two) { // 100
        if (one==null || two==null) {return 0;}
        int lo=one.length(),lt=two.length();
        int[] dp = new int[lt+1]; // length of two to save SC, first index should be length in one, second index is index in two
        for (int i=0;i<=lo;i++) {
          int tl = i-1; // from begining of word, need [i-1][j-1]
          for (int j=0;j<=lt;j++) {
            if (i==0) {dp[j]=j;continue;}
            if (j==0) {dp[j]=i;continue;}
            int temp = dp[j];
            dp[j]=Math.min(Math.min(dp[j],dp[j-1])+1,tl+(one.charAt(i-1)==two.charAt(j-1)?0:1));
            tl = temp;
          }
        }
        return dp[lt];
        // Write your solution here
    }
    /*
    290. Wildcard Matching
    Given two strings where first string is a normal string and second string may contain wild card characters. Write a function that returns true if the two strings match. The following are allowed wildcard characters in first string.
    * --> Matches with 0 or more instances of any character or set of characters.
    ? --> Matches with any one character.
    Assumptions:
    The two strings are both not null.
    Assume there is no more than one '*' adjacent to each other.
    Examples:
    input = "abc", pattern = "?*", return true.
     */
    public boolean match(String input, String pattern) { // 290
        if (input==null && pattern==null) {return true;}
        if (input==null || pattern==null) {return false;}
        int li=input.length(),lp=pattern.length();
        if (li==0 && lp==0) {return true;}
        if (lp==0) {return false;}
        boolean[] dp = new boolean [lp+1]; // matchable beyond index
        dp[lp]=true; // only true after the end of both input and pattern
        for (int j=lp-1;j>=0 && pattern.charAt(j)=='*';j--) {
            dp[j]=true;
        }
        for (int i=li-1;i>=0;i--) {
            boolean tr = i==li-1; // dp[i+1][j+1]
            for (int j=lp-1;j>=0;j--) {
                boolean rec = dp[j];
                if (pattern.charAt(j)=='*') {
                    dp[j]=dp[j] || dp[j+1] || tr; // dp[i+1][j] || dp[i][j+1] || dp[i+1][j+1]
                } else {
                    dp[j]=tr && (pattern.charAt(j)=='?' || pattern.charAt(j)==input.charAt(i));
                }
                tr=rec;
            } // end for j
        } // end for i
        return dp[0];
        // Write your solution here
    }
    /*
    249. Regular Expression Matching
    Implement regular expression matching with support for '.' and '*'. '.' Matches any single character. '*' Matches zero or more of the preceding element. The matching should cover the entire input string (not partial).
     */
    public boolean isMatch(String input, String pattern) { // 249
        if (input==null) {return true;}
        if (pattern==null) {return false;}
        int li=input.length(),lp=pattern.length();
        if (li==0 && lp==0) {return true;}
        char[] pat = pattern.toCharArray();
        for (int j=1;j<lp;j++) {
            if (pat[j]=='*' && pat[j-1]!=' ') { // the first * might be real letter
                pat[j]=' '; // replace the second * to space
            }
        }
        boolean [][] dp = new boolean [li+1][lp+1]; // length
        dp[li][lp]=true;
        for (int i=li;i>=0;i--) {
            for (int j=lp-1;j>=0;j--) {
                if (pat[j]==' ') {      // zero times                                           // at least 1 time of pat[j-1]
                    dp[i][j]=dp[i][j-1]= dp[i][j+1] || i<li && (dp[i+1][j-1] || dp[i+1][j+1]) && (pat[j-1]==input.charAt(i) || pat[j-1]=='.');
                    j--;
                } else {
                    dp[i][j]=i<li && dp[i+1][j+1] && (pat[j]=='.' || pat[j]==input.charAt(i));
                }
            } // end for j
        } // end for i
        return dp[0][0];
        // Write your solution here
    }
    /*
    330. One Edit Distance
    Determine if two given Strings are one edit distance.
    One edit distance means you can only insert one character/delete one character/replace one character to another character in one of the two given Strings and they will become identical.
    Assumptions:
    The two given Strings are not null
    Examples:
    s = "abc", t = "ab" are one edit distance since you can remove the trailing 'c' from s so that s and t are identical
    s = "abc", t = "bcd" are not one edit distance
     */
    public boolean oneEditDistance(String source, String target) { // 330
        if (source==null || target==null) {return false;}
        int lens=source.length();
        int lent=target.length();
        if (lens==0 && lent==0) {return false;}
        if (lens+lent==1) {return true;}
        if (lens==1 && lent==1) {return source.charAt(0)!=target.charAt(0);}
        if (lens-lent>1 || lent-lens>1) {return false;}
        int dif=lens-lent,count=0,offset=0; // dif is -1/0/1
        for (int i=0;i<lent;i++) {
            if (i+offset==lens || target.charAt(i)!=source.charAt(i+offset)) {
                count++;
                offset+=dif; // offset for s
                if (dif>0) {i--;} // if s is longer character need to be dropped from s, need to recheck current t element
                if (count>1) {break;}
            } // end if
        } // end for
        return count==1 || count==0 && dif==1;
        // Write your solution here
    }
    /*
    576. Delete Distance of Two Strings
    Given two strings of alphanumeric characters. Each time you can delete one character from one of them. Determine the minimum number of delete operations needed to make the two strings equal to each other.
    Example 1:
    Input: s1 = "laioffer", s2="xaioffey"
    Output: 4
    Explanation: To make s1 equals to s2, we need to delete 'l' and 'r' in s1, and 'x' and 'y' in s2. 4 times of delete operation in total.
    Assumptions:
    Input strings only contain lower case characters.
    The length of input strings is equals or less than 500.
     */
    public int minNumOfDelete(String s1, String s2) { // 576
        if (s1==null || s2==null) {return 0;}
        int l1=s1.length(),l2=s2.length();
        int[] dp = new int[l2+1];
        for (int i=0;i<=l1;i++) {
            int tl = i-1;
            for (int j=0;j<=l2;j++) {
                if (i==0 || j==0) {dp[j]=Math.max(i,j);continue;}
                int tmp = dp[j];
                dp[j]=Math.min(1+Math.min(dp[j],dp[j-1]),tl+(s1.charAt(i-1)==s2.charAt(j-1)?0:2));
                tl = tmp;
            }
        }
        return dp[l2];
        // int[][] dp = new int[l1+1][l2+1];
        // for (int i=0;i<=l1;i++) {
        //   for (int j=0;j<=l2;j++) {
        //     if (i==0 || j==0) {dp[i][j]=Math.max(i,j);continue;}
        //     dp[i][j]=Math.min(1+Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]+(s1.charAt(i-1)==s2.charAt(j-1)?0:2));
        //   }
        // }
        // return dp[l1][l2];
        // Write your solution here
    }
    public static void main(String[] args) {
        Match solution = new Match();
        String input = "apple";
        String pattern = "a4e";
        System.out.println(solution.match(input,pattern));
//        String input = "abc";
//        String pattern="abc*a.*";
//        System.out.println(solution.isMatch(input,pattern));
    }
}
