package com.laioffer.optimized;
import java.util.*;

public class LargestSquare {
    public int largest101(int[][] matrix) { // 101
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return 0;}
        int len=matrix.length;
        int[] con = new int[len];
        int ans=0;
        for (int i=0;i<len;i++) {
            int tl=0; // topleft
            for (int j=0;j<len;j++) {
                int tmp=con[j];
                if (matrix[i][j]==0) {
                    con[j]=0;
                } else if (j==0) {
                    con[j]=Math.min(con[j],tl)+1;
                } else {
                    con[j]=Math.min(Math.min(con[j],tl),con[j-1])+1;
                }
                ans=Math.max(ans,con[j]);
                tl=tmp;
            }
        }
        return ans;
        // Write your solution here
    }
    public int larges102(int[][] matrix) { // 102
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return 0;}
        int rows=matrix.length,cols=matrix[0].length;
        int[] dp = new int[cols]; // consecutive 1s from above
        int max=0;
        for (int i=0;i<rows;i++) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (int j=0;j<=cols;j++) {
                int cur=0;
                if (j<cols) {
                    cur=dp[j]=matrix[i][j]==0?0:dp[j]+1;
                }
                while (!stack.isEmpty() && cur<dp[stack.peek()]) {
                    int height=dp[stack.pop()];
                    int left=stack.isEmpty()?0:stack.peek()+1;
                    max=Math.max(max,height*(j-left));
                }
                stack.push(j);
            }
        }
        return max;
        // Write your solution here
    }
    public int longest103(int[] array) { // 103
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int cur=0,max=0;
        for (int i=0;i<len;i++) {
            if (array[i]==0) {
                cur=0;
            } else {
                cur++;
                max=Math.max(max,cur);
            }
        }
        return max;
        // Write your solution here
    }
    public int largest104(int[][] matrix) { // 104
        if (matrix==null || matrix.length==0) {return 0;}
        int rows=matrix.length,cols=matrix[0].length;
        int[][] dpu = new int[rows][cols];
        int[][] dpl = new int[rows][cols];
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                dpu[i][j]=matrix[i][j]+((matrix[i][j]==1 && i>0)?dpu[i-1][j]:0);
                dpl[i][j]=matrix[i][j]+((matrix[i][j]==1 && j>0)?dpl[i][j-1]:0);
            }
        }
        int[][] dpd = new int[rows][cols];
        int[][] dpr = new int[rows][cols];
        for (int i=rows-1;i>=0;i--) {
            for (int j=cols-1;j>=0;j--) {
                dpd[i][j]=matrix[i][j]+((matrix[i][j]==1 && i<rows-1)?dpd[i+1][j]:0);
                dpr[i][j]=matrix[i][j]+((matrix[i][j]==1 && j<cols-1)?dpr[i][j+1]:0);
            }
        }
        int max=0;
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                int cur = Math.min(Math.min(dpu[i][j],dpl[i][j]),Math.min(dpd[i][j],dpr[i][j]));
                max=Math.max(max,cur);
            }
        }
        return max;
        // Write your solution here
    }
    public int largest105(int[][] matrix) { // 105
        if (matrix==null || matrix.length==0) {return 0;}
        int rows=matrix.length,cols=matrix[0].length;
        int[][] dpul = new int[rows][cols];
        int[][] dpur = new int[rows][cols];
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                dpul[i][j]=matrix[i][j]+((matrix[i][j]==1 && i>0 && j>0)?dpul[i-1][j-1]:0);
                dpur[i][j]=matrix[i][j]+((matrix[i][j]==1 && i>0 && j<cols-1)?dpur[i-1][j+1]:0);
            }
        }
        int[][] dpdl = new int[rows][cols];
        int[][] dpdr = new int[rows][cols];
        for (int i=rows-1;i>=0;i--) {
            for (int j=cols-1;j>=0;j--) {
                dpdr[i][j]=matrix[i][j]+((matrix[i][j]==1 && i<rows-1 && j<cols-1)?dpdr[i+1][j+1]:0);
                dpdl[i][j]=matrix[i][j]+((matrix[i][j]==1 && i<rows-1 && j>0)?dpdl[i+1][j-1]:0);
            }
        }
        int max=0;
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                int cur = Math.min(Math.min(dpul[i][j],dpur[i][j]),Math.min(dpdl[i][j],dpdr[i][j]));
                max=Math.max(max,cur);
            }
        }
        return max;
        // Write your solution here
    }
    public int largest106(int[][] matrix) { // 106
        if (matrix==null || matrix.length==0) {return 0;}
        int rows=matrix.length;
        int cols=matrix[0].length;
        int[][] preSum = new int[rows][cols];
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                preSum[i][j]=matrix[i][j]+(i>0?preSum[i-1][j]:0);
            }
        }
        int max=Integer.MIN_VALUE;
        for (int i=0;i<rows;i++) { // from row i to row j
            for (int j=i;j<rows;j++) {
                int cur=0;
                for (int k=0;k<cols;k++) {
                    cur=preSum[j][k]-preSum[i][k]+matrix[i][k]+(cur<0?0:cur);
                    max=Math.max(max,cur);
                }
            }
        }
        return max;
        // Write your solution here
    }
    public double largest107(double[][] matrix) { // 107
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return 0.0;}
        int rows=matrix.length,cols=matrix[0].length;
        double max = Integer.MIN_VALUE;
        double[] product = new double[cols];
        for (int i=0;i<rows;i++) {
            Arrays.fill(product,1.0);
            for (int k=i;k<rows;k++) {
                for (int j=0;j<cols;j++) {
                    product[j]*=matrix[k][j];
                }
                max=Math.max(max,findMax(product));
            }
        }
        return max;
        // Write your solution here
    }
    /*
    443. Maximal Square
    Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     */
    public int maximalSquare(int[][] matrix) { // 443
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return 0;}
        int rows=matrix.length,cols=matrix[0].length;
        // int[][] dp = new int[rows+1][cols+1];
        // int max=0; // length
        // for (int i=0;i<rows;i++) {
        //   for (int j=0;j<cols;j++) {
        //     if (matrix[i][j]==0) {
        //       dp[i+1][j+1]=0;
        //     } else {
        //       dp[i+1][j+1]=Math.min(dp[i][j],Math.min(dp[i][j+1],dp[i+1][j]))+1;
        //     }
        //     max=Math.max(max,dp[i+1][j+1]);
        //   }
        // }
        int[] dp = new int[cols+1];
        int max=0; // length
        for (int i=0;i<rows;i++) {
            int tl = 0;
            for (int j=0;j<cols;j++) {
                int tmp = dp[j+1];
                if (matrix[i][j]==0) {
                    dp[j+1]=0;
                } else {
                    dp[j+1]=Math.min(tl,Math.min(dp[j+1],dp[j]))+1;
                }
                tl=tmp;
                max=Math.max(max,dp[j+1]);
            }
        }
        return max*max;
        // Write your solution here
    }
    private double findMax(double[] array) {
        double max=array[0];
        double curMin=max;
        double curMax=max;
        for (int j=1;j<array.length;j++) {
            double cur1 = curMin*array[j];
            double cur2 = curMax*array[j];
            curMin = Math.min(Math.min(cur1,cur2),array[j]);
            curMax = Math.max(Math.max(cur1,cur2),array[j]);
            max = Math.max(max,curMax);
        }
        return max;
    }
    /*
    637. Largest Square Surrounded By One
    Determine the largest square surrounded by 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.
    Assumptions
    The given matrix is guaranteed to be of size M * N, where M, N >= 0
    Examples
    {{1, 0, 1, 1, 1},
     {1, 1, 1, 1, 1},
     {1, 1, 0, 1, 0},
     {1, 1, 1, 1, 1},
     {1, 1, 1, 0, 0}}
    The largest square surrounded by 1s has length of 3.
     */
    public int largestSquareSurroundedByOne(int[][] matrix) { // 637
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return 0;}
        int m=matrix.length,n=matrix[0].length;
        int[][] fromTop = new int[m][n];
        int[][] fromLeft = new int[m][n];
        int max=0;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (matrix[i][j]==1) {
                    fromTop[i][j]=1+(i==0?0:fromTop[i-1][j]);
                    fromLeft[i][j]=1+(j==0?0:fromLeft[i][j-1]);
                }
                int maxTry=Math.min(fromTop[i][j],fromLeft[i][j]);
                for (int k=maxTry;k>0;k--) {
                    if (fromTop[i][j-k+1]>=k && fromLeft[i-k+1][j]>=k) {
                        max=Math.max(max,k);
                    }
                }
            }
        }
        return max;
        // Write your solution here
    }
    /*
    638. Largest Square Of Matches
    Determine the largest square surrounded by a bunch of matches (each match is either horizontal or vertical), return the length of the largest square.
    The input is a matrix of points. Each point has one of the following values:
    0 - there is no match to its right or bottom.
    1 - there is a match to its right.
    2 - there is a match to its bottom.
    3 - there is a match to its right, and a match to its bottom.
     */
    public int largestSquareOfMatches(int[][] matrix) { // 638
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return 0;}
        int m=matrix.length,n=matrix[0].length;
        int[][] fromBottom = new int[m][n];
        int[][] fromRight = new int[m][n];
        int max=0;
        for (int i=m-1;i>=0;i--) {
            for (int j=n-1;j>=0;j--) {
                if (i==m-1 || matrix[i][j]<2) {
                    fromBottom[i][j]=0;
                } else {
                    fromBottom[i][j]=fromBottom[i+1][j]+1;
                }
                if (j==n-1 || matrix[i][j]%2==0) {
                    fromRight[i][j]=0;
                } else {
                    fromRight[i][j]=fromRight[i][j+1]+1;
                }
                int maxTry = Math.min(fromBottom[i][j],fromRight[i][j]);
                for (int k=maxTry;k>0;k--) {
                    if (fromBottom[i][j+k]>=k && fromRight[i+k][j]>=k) {
                        max=Math.max(max,k);
                    }
                }
            }
        }
        return max;
        // Write your solution here
    }
    public static void main(String[] args) {
        LargestSquare solution = new LargestSquare();
        int[][] matrix = {{1,1,1,1,1,1,0,1,1,1,0,0,1,1,1,0,0,1},
                          {0,0,0,1,0,1,1,0,1,1,1,1,0,1,0,1,0,1},
                          {1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1},
                          {1,1,1,0,1,0,0,1,0,0,1,1,1,1,1,1,1,1},
                          {1,0,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1},
                          {0,0,0,0,0,1,1,1,1,0,1,1,1,1,1,1,0,1},
                          {0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1},
                          {1,1,1,1,1,1,0,1,0,1,1,1,0,0,0,0,0,1},
                          {0,1,0,0,1,1,0,0,0,1,0,0,0,0,0,1,1,1},
                          {0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,0,0,1},
                          {1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1}};
        System.out.println(solution.largestSquareSurroundedByOne(matrix));
        int[][] matrix2 = {{2,3,3,2},{3,3,3,0},{3,3,3,2},{3,3,3,2},{1,3,1,2},{3,3,3,2},{1,1,3,0},{3,3,3,2},{3,3,3,2},{1,1,1,0}};
        System.out.println(solution.largestSquareOfMatches(matrix2));
    }
}
