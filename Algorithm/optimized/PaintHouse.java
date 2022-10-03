package com.laioffer.Algorithm.optimized;

public class PaintHouse {
    /*
    495. Paint House
    There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
    The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
    Note:
    All costs are positive integers.
     */
    public int minCost(int[][] costs) { // 495
        if (costs==null || costs.length==0) {return 0;}
        int[] mincost = new int[3];
        for (int i=0;i<3;i++) {
            mincost[i]=costs[0][i];
        }
        for (int i=1;i<costs.length;i++) {
            int temp0=Math.min(mincost[1],mincost[2])+costs[i][0];
            int temp1=Math.min(mincost[0],mincost[2])+costs[i][1];
            int temp2=Math.min(mincost[0],mincost[1])+costs[i][2];
            mincost[0]=temp0;
            mincost[1]=temp1;
            mincost[2]=temp2;
        }
        return Math.min(Math.min(mincost[0],mincost[1]),mincost[2]);
        // Write your solution here
    }
    /*
    498. Paint House II
    There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
    The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
    Note:
    All costs are positive integers.
     */
    public int minCostII(int[][] costs) { // 498
        if (costs==null || costs.length==0) {return 0;}
        int len=costs.length, k=costs[0].length;
        if (k==0 || k==1 && len>1) {return -1;}
        if (k==1) {return costs[0][0];}
        int[] mincost = new int[k];
        int curmin=0,cursec=0,indm=-1;
        for (int i=0;i<len;i++) { // number of houses
            for (int j=0;j<k;j++) {          // check previous cost
                mincost[j]=costs[i][j]+(j==indm?cursec:curmin);
            }
            curmin=Integer.MAX_VALUE-1;cursec=Integer.MAX_VALUE;
            for (int j=0;j<k;j++) { // loop over colors
                if (mincost[j]<curmin) {
                    cursec=curmin;
                    curmin=mincost[j];
                    indm=j;
                } else if (mincost[j]<cursec) {
                    cursec=mincost[j];
                } // keep minimum and second minimum
            }
        }
        return curmin;
        // Write your solution here
    }
    /*
    506. Paint Fence
    There is a fence with n posts, each post can be painted with one of the k colors.
    You have to paint all the posts such that no more than two adjacent fence posts have the same color.
    Return the total number of ways you can paint the fence.
    Note:
    n and k are non-negative integers.
     */
    public int numWays(int n, int k) {
        if (n<=0 || k<=0) {return 0;}
        if (k==1 && n>2) {return 0;}
        int[] dp = new int[n];
        dp[0]=k;
        if (n==1) {return dp[0];}
        dp[1]=k*k;
        if (n==2) {return dp[1];}
        dp[2]=k*k*k-k;
        for (int i=3;i<n;i++) {
            dp[i]=dp[i-1]*k-dp[i-3]*(k-1);
        }
        return dp[n-1];
        //return helper(n,k);
        // Write your solution here
    }
    private int helper(int n, int k) {
        if (n==1) {return k;}
        if (n==2) {return k*k;}
        if (n==3) {return k*k*k-k;} // subtract k if all 3 posts have same color
        return helper(n-1,k)*k-helper(n-3,k)*(k-1);
    }
    public static void main(String[] args) {
        PaintHouse solution = new PaintHouse();
    }
}
