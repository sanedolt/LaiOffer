package com.laioffer.Algorithm.optimized;

public class Scramble {
    /*
    150. Scramble String
    Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
     */
    public boolean isScramble(String s1, String s2) { // 150
        if (s1==null && s2==null) {return true;}
        if (s1==null || s2==null) {return false;}
        if (s1.length()!=s2.length()) {return false;}
        int len=s1.length();
        if (len==0) {return true;}
        if (len==1) {return s1.equals(s2);}
        boolean[][][][] dp = new boolean[len][len][len][len];
        for (int l=0;l<len;l++) {
            for (int i1=0,j1=i1+l;j1<len;i1++,j1++) {
                for (int i2=0,j2=i2+l;j2<len;i2++,j2++) {
                    if (i1==j1 && i2==j2 && s1.charAt(i1)==s2.charAt(i2)) {
                        dp[i1][j1][i2][j2]=true;
                        continue;
                    }
                    for (int m=0;m<l;m++) {
                        dp[i1][j1][i2][j2] = dp[i1][j1][i2][j2] || dp[i1][i1+m][i2][i2+m] && dp[i1+m+1][j1][i2+m+1][j2] || dp[i1][i1+m][j2-m][j2] && dp[i1+m+1][j1][i2][j2-m-1];
                    }
                }
            }
        }
        return dp[0][len-1][0][len-1];
        // Write your solution here
    }
    public static void main(String[] args) {
        Scramble solution = new Scramble();
        String s1="great";
        String s2="rgtae";
        System.out.println(solution.isScramble(s1,s2));
    }

}
