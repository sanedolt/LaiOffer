package com.laioffer.Algorithm.optimized;

public class CutWood {
    /*
    137. Cutting Wood I
    There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A. The positions are guaranteed to be in ascending order in the range of [1, L - 1]. The cost of each cut is the length of the stick segment being cut. Determine the minimum total cost to cut the stick into the defined pieces.
    Examples
    L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)
     */
    public int minCost(int[] cuts, int length) { // 137
        if (cuts==null || cuts.length==0) {return 0;}
        if (length<=0) {return 0;}
        int len = cuts.length;
        int[][] dp = new int[len+2][len+2]; // length at left edge, length at right edge
        for (int j=0;j<len+2;j++) {
            int valj = j==0?0:(j==len+1?length:cuts[j-1]);
            for (int i=j-2;i>=0;i--) {
                int vali = i==0?0:(i==len+1?length:cuts[i-1]);
                dp[i][j]=Integer.MAX_VALUE;
                for (int k=j-1;k>i;k--) { // k in (i,j) k is first cut
                    dp[i][j]=Math.min(dp[i][j],valj-vali+dp[i][k]+dp[k][j]);
                }
            }
        }
        return dp[0][len+1];
        // Write your solution here
    }
    public static void main(String[] args) {
        CutWood solution = new CutWood();
    }
}
