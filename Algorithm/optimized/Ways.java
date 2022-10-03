package com.laioffer.Algorithm.optimized;
import java.util.*;

public class Ways {
    /*
    146. Find Number of BSTs Generated
    Find the number of different Binary Search Trees generated from 1-n.
    */
    public int numOfTrees(int n) { // 146
        if (n<0) {return 0;}
        if (n<2) {return 1;}
        int[] dp = new int[n+1];
        dp[0]=1;
        for (int i=1;i<=n;i++) {
            for (int j=0;j<i;j++) {
                dp[i]+=dp[j]*dp[i-j-1]; // smaller & larger
            }
        }
        return dp[n];
        // Write your solution here
    }
    /*
    332. Ways Of Expressions To Target
    Given an expression as a String array, the only possible elements are "0", "1", "|", "&", "^".
    You can add parentheses to let the execution sequence be changed (the sequence is guaranteed to be executable without parentheses).
    Given a target number(either 0 or 1), how many different ways of execution sequence are there to let the result be the target number?
    Assumptions:
    The given String array is not null or empty, and it is guaranteed to be valid.
    Examples:
    {"0", "&", "1", "|", "1"}, if target is 1, there is only one way (0 & 1) | 1 == 1
     */
    public int ways(String[] exp, int target) { // 332
        if (exp==null || exp.length==0) {return 0;}
        int len=exp.length;
        int num=(len+1)/2;
        int[] fact = getFact(num);
        int[][] getOne = new int[num+1][num+1];
        for (int j=0;j<num;j++) { // j is right index
            int cur = Integer.parseInt(exp[j*2]);
            for (int i=j;i>=0;i--) { // i is left index
                if (i==j) {
                    getOne[i+1][j+1]=cur;
                    continue;
                }
                String op = exp[j*2-1];
                if (op.equals("&")) {
                    getOne[i+1][j+1]=getOne[i+1][j]*cur;
                } else if (op.equals("|")) {
                    getOne[i+1][j+1]=getOne[i+1][j]+(fact[j-i-1]-getOne[i+1][j])*cur;
                } else { // op.equals("^")
                    getOne[i+1][j+1]=getOne[i+1][j]*(1-cur)+(fact[j-i-1]-getOne[i+1][j])*cur;
                }
            }
        }
        return target==1?getOne[1][num]:(fact[num-1]-getOne[1][num]);
        // Write your solution here
    }
    private int[] getFact(int n) {
        int[] fact = new int[n];
        fact[0]=fact[1]=1;
        for (int i=2;i<n;i++) {
            fact[i]=fact[i-1]*i;
        }
        return fact;
    }
    /*
    475. Different Ways to Add Parentheses
    Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators in Ascending order. The valid operators are +, - and *.
    Example 1
    Input: "2-1-1".
    ((2-1)-1) = 0
    (2-(1-1)) = 2
    Output: [0, 2]
    Example 2
    Input: "2\*3-4\*5"
    (2*(3-(4*5))) = -34
    ((2*3)-(4*5)) = -14
    ((2*(3-4))*5) = -10
    (2*((3-4)*5)) = -10
    (((2*3)-4)*5) = 10
    Output: [-34, -14, -10, -10, 10]
     */
    public List<Integer> diffWaysToCompute(String input) {
        if (input==null) {return null;}
        List<Integer> result=helper(input,0,input.length());
        Collections.sort(result);
        return result;
        // Write your solution here
    }
    private List<Integer> helper (String input, int left, int right) { // right is exclusive
        List<Integer> result = new ArrayList<>();
        if (left>=right) {return result;}
        for (int i=left;i<right;i++) {
            char c =input.charAt(i);
            if (c=='+' || c=='-' || c=='*') {
                List<Integer> resultLeft = helper(input,left,i);
                List<Integer> resultRight = helper(input,i+1,right);
                for (int x : resultLeft) {
                    for (int y : resultRight) {
                        if (c=='+') {
                            result.add(x+y);
                        } else if (c=='-') {
                            result.add(x-y);
                        } else if (c=='*') {
                            result.add(x*y);
                        }
                    }
                }
            } // end if
        } // end for i
        if (result.isEmpty()) {
            int num=0;
            for (int i=left;i<right;i++) {
                num=num*10+(input.charAt(i)-'0');
            }
            result.add(num);
        }
        return result;
    }
    /*
    590. Count Paths Going Out of Matrix
    Given an m * n matrix and an integer num, imagine that you were standing at cell (i, j) at the beginning. Each step you can move up, down, left or right to adjacent cell or across the boundary to go out of the matrix. If you go out, you cannot go back into the matrix. You can only move at most num steps. Calculate how many paths there are that you could go out of the matrix.
    The result could be very large, so return the result after mod 10^9 + 7.
    Example 1:
    Input: m = 2, n = 2, i = 0, j = 0, n = 1
    Output: 2
    Explanation: You can go out of the matrix by moving up or left using 1 step.
    Example 2:
    Input: m = 2, n = 2, i = 0, j = 0, n = 2
    Output: 6
    Explanation:
    You can go out of the matrix by moving up or left using 1 step.
    You can move to [0, 1] first, then go out of the matrix by moving up or left using 2 steps in total.
    You can move to [1, 0] first, then go out of the matrix by moving down or left using 2 steps in total.
    Assumptions:
    1 <= m, n <= 50
    0 <= i < m, 0 <= j < n
    0 <= num <= 50
     */
    public int countPaths(int m, int n, int num, int i, int j) { // 590
        if (m<1 || m>50 || n<1 || n>50 || i<0 || i>=m || j<0 || j>=n || num<0 || num>50) {return 0;}
        int[][] prev = new int[m][n];
        int[][] curr = new int[m][n];
        int[][] init = new int[m][n];
        for (int k=0;k<n;k++) {
            curr[0][k]++;
            curr[m-1][k]++;
        }
        for (int k=0;k<m;k++) {
            curr[k][0]++;
            curr[k][n-1]++;
        }
        if (num==1) {return curr[i][j];}
        for (int k=0;k<m;k++) {
            for (int l=0;l<n;l++) {
                init[k][l]=prev[k][l]=curr[k][l];
            }
        }
        for (int s=1;s<num;s++) {
            for (int k=0;k<m;k++) {
                for (int l=0;l<n;l++) {
                    curr[k][l]=init[k][l];
                    if (l>=1) {curr[k][l]+=prev[k][l-1];}
                    if (l<n-1) {curr[k][l]+=prev[k][l+1];}
                    if (k>=1) {curr[k][l]+=prev[k-1][l];}
                    if (k<m-1) {curr[k][l]+=prev[k+1][l];}
                }
            }
            for (int k=0;k<m;k++) {
                for (int l=0;l<n;l++) {
                    prev[k][l]=curr[k][l];
                }
            }
        }
        return curr[i][j];
        // Write your solution here
    }
    /*
    593. Count Permutations with K Inversions
    Given an integer array with length of L, an inversion is defined as following:
    For any 0 <= i < j < L, if a[i] > a[j], then we call a[i] and a[j] is an inversion.
    Given two integers n and k, count how many permutations consisting of numbers (1, 2, 3, .....n) that have exactly k inversions. The result may be very large, so return the result after mod 10^9 + 7.
    Example 1:
    Input: n = 3, k = 0
    Output: 1
    Explanation: [1,2,3]
    Example 2:
    Input: n = 3, k = 1
    Output: 2
    Explanation:[1,3,2], [2,1,3]
    Example 3:
    Input: n = 3, k = 2
    Output: 2
    Explanation:[3,1,2], [2,3,1]
    Example 4:
    Input: n = 3, k = 3
    Output: 1
    Explanation: [3,2,1]
    Assumptions:
    1 <= n <= 1000
    0 <= k <= 1000
     */
    public int countPermutation(int n, int k) { // 593
        if (n<=0 || n>1000 || k<0 || k>1000) {return 0;}
        int[][] dp = new int[2][k+1]; // dp[0] is previous, dp[1] is current
        int mod=1000000007;
        dp[0][0]=1;
        for (int i=1;i<=n;i++) {
            dp[1][0]=1;
            // dp[1][j]=dp[0][j-i]+dp[0][j-i+1]+...+dp[0][j]
            for (int j=1;j<=k;j++) {
                dp[1][j]=dp[1][j-1]+dp[0][j]; // either at the second from last or just putting at the end
                dp[1][j]%=mod;
                if (j>=i) {
                    dp[1][j]+=mod-dp[0][j-i]; // impossible for move than i difference in number of inversion
                    dp[1][j]%=mod;
                }
            }
            int[] tmp = dp[0];
            dp[0] = dp[1];
            dp[1] = tmp;
        }
        return dp[0][k];
        // Write your solution here
    }
    /*
    596. Number of Ways to Decode
    Given an encrypted message that contains only digits and character '*' where character '*' could represent any digit from 1 to 9. We want to decode the given message to string of capital letters following the rule that A->1, B->2, C->3....Z->26.
    Count how many ways there are to decode the given message. You could assume that the length of given message is always > 0. The result may be very large, so return the result after mod 10^9 + 7.
    Example 1:
    Input = "0"
    Output = 0
    Explanation: There is no way do decode the given message.
    Example 2:
    Input = "1"
    Output = 1
    Explanation: The given message could only be decoded to "A"
    Example 3:
    Input = "*"
    Output = 9
    Explanation: The given message could be any letter in [A,B,C,D,E,F,G,H,I]
    Example 4:
    Input = "2*"
    Output = 15
    Explanation: 9 + 6 = 15. 21~29 could be decoded as BA, BB, ..., BI, and 21~26 could be decoded as U, V, W, X, Y and Z.
     */
    public int countWaysToDecode(String message) { // 596
        if (message==null || message.length()==0) {return 0;}
        int len=message.length();
        int[] dp = new int[len+1];
        dp[0]=1;
        for (int i=0;i<len;i++) {
            char cur=message.charAt(i);
            char pre=i==0?':':message.charAt(i-1);
            if (cur=='0') {
                if (pre>'2' || pre=='0') {return 0;}
                if (pre=='*') { // 1 || 2
                    dp[i+1]=dp[i-1]*2;
                } else { // 1 || 2
                    dp[i+1]=dp[i-1];
                }
            } else if (cur>='1' && cur<='6') {
                dp[i+1]=dp[i];
                if (pre>'2' || pre=='0') {continue;}
                if (pre=='*') { // 1 || 2
                    dp[i+1]+=dp[i-1]*2;
                } else { // 1 || 2
                    dp[i+1]+=dp[i-1];
                }
            } else if (cur>='7' && cur<='9') {
                dp[i+1]=dp[i];
                if (pre=='1' || pre=='*') {
                    dp[i+1]+=dp[i-1];
                }
            } else { // cur=='*'
                dp[i+1]=dp[i]*9;
                if (pre=='1') {
                    dp[i+1]+=dp[i-1]*9; // 11~19
                } else if (pre=='2') {
                    dp[i+1]+=dp[i-1]*6; // 21~26
                } else if (pre=='*') {
                    dp[i+1]+=dp[i-1]*15; //11~19 && 21~26
                }
            }
        }
        return dp[len];
        // Write your solution here
    }
    /*
    610. Mininum Number of Operations
    Suppose that there are only two operations can be used in a simple text editor:
    Copy-All: copy all current content
    Paste: Paste the content of last Copy-All operation.
    Originally there is only one character 'A' in the text editor. Given an integer n (1 <= n <= 1000), find the minimum number of operations to get n character 'A'.
    Example 1:
    Input: n = 1
    Output: 0
    Explanation: There is one 'A' at the beginning.
    Example 2:
    Input: n = 6
    Output = 5
    Explanation: The are two ways to get 6 'A' with the minimum number of steps of 5.
    Copy-All -> Paste -> ('AA') -> Copy-All -> Paste -> ('AAAA') -> Paste -> ('AAAAAA')
    Copy-All -> Paste -> ('AA') -> Paste -> ('AAA') -> Copy-All -> Paste -> ('AAAAAA')
     */
    public int minOps(int n) { // 610
        if (n<=1) {return 0;}
        int[] dp = new int[n+1];
        for (int i=2;i<=n;i++) { // 0 output for n==1
            dp[i]=Integer.MAX_VALUE;
            for (int j=2;j<=i;j++) {
                if (!isPrime(j) || i%j>0) {continue;}
                dp[i]=Math.min(dp[i],dp[i/j]+j); // paste j times last
            }
        }
        return dp[n];
        // Write your solution here
    }
    private boolean isPrime(int n) {
        if (n<2) {return false;}
        if (n==2) {return true;}
        for (int i=3;i*i<=n;i+=2) {
            if (n%i==0) {return false;}
        }
        return true;
    }
    public static void main(String[] args) {
        Ways solution = new Ways();
        System.out.println(solution.numOfTrees(3));
    }
}
