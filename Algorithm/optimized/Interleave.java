package com.laioffer.Algorithm.optimized;

public class Interleave {
    /*
    209. Interleave Strings
    Given three strings A, B and C. Determine if C can be created by merging A and B in a way that maintains the relative order of the characters in A and B.
    Assumptions
    none of A, B, C is null
    Examples
    C = "abcde", A = "acd", B = "be", return true
    C = "abcde", A = "adc", B = "be", return false
     */
    public boolean canMerge(String a, String b, String c) { // 209
        if (c==null) {return a==null && b==null;}
        if (a==null) {return c.equals(b);}
        if (b==null) {return c.equals(a);}
        int la=a.length(),lb=b.length(),lc=c.length();
        if (lc!=la+lb) {return false;}
        boolean[][] dp = new boolean[la+1][lb+1];
        for (int ia=0;ia<=la;ia++) { // length of a
            for (int ib=0;ib<=lb;ib++) { // length of b
                int ic=ia+ib-1;
                if (ia==0 && ib==0) {
                    dp[ia][ib]=true;
                } else if (ia==0) {
                    dp[ia][ib]=dp[ia][ib-1] && b.charAt(ib-1)==c.charAt(ic);
                } else if (ib==0) {
                    dp[ia][ib]=dp[ia-1][ib] && a.charAt(ia-1)==c.charAt(ic);
                } else {
                    dp[ia][ib]=dp[ia][ib-1] && b.charAt(ib-1)==c.charAt(ic) || dp[ia-1][ib] && a.charAt(ia-1)==c.charAt(ic);
                }
            } // end for ib
        } // end for ia
        return dp[la][lb];
        // Write your solution here
    }
    public static void main(String[] args) {
        Interleave solution = new Interleave();
    }
}
