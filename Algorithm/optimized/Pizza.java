package com.laioffer.Algorithm.optimized;

public class Pizza {
    /*
    632. Can I Win
    There is an array of positive integers, in which each integer represents a piece of Pizza’s size, you and your friend take turns to pick pizza from either end of the array.  The winner is the one who gets larger total sum of all pizza or whoever starts first if there is a tie. Return whether you will win the game if you start first.
    Example:
    Input: [2,1,100,3]
    Output: True
    Explanation: To win the game, you pick 2 first, then your friend will pick either 1 or 3, after that you could pick 100. In the end you could get 2 + 100 = 102, while your friend could only get 1 + 3 = 4.
     */
    public boolean canWin1(int[] nums) { // 632
        if (nums==null || nums.length==0) {return true;}
        int len=nums.length;
        int [][] dp = new int[len][len];
        int total = 0;
        for (int i = len - 1; i >= 0; i--){
            for (int j = i; j < len;j++){
                if (i == j){
                    dp[i][j] = nums[i];
                    total += nums[i];
                } else if (i + 1 == j){
                    dp[i][j] = Math.max(nums[i],nums[j]);
                } else {
                    int left = nums[i] + Math.min(dp[i+2][j],dp[i+1][j-1]);
                    int right = nums[j] + Math.min(dp[i+1][j-1],dp[i][j-2]);
                    dp[i][j] = Math.max(left,right);
                }
            }
        }
        return dp[0][len-1] >= total - dp[0][len-1];
        // Write your solution here
    }
    public boolean canWin2(int[] nums) { // 632
        if (nums==null || nums.length==0) {return true;}
        int len=nums.length;
        int[][] dp = new int[2][len]; // 0 means first guy, 1 means second guy, second index is starting index for "current" length
        for (int i=0;i<len;i++) {
          dp[0][i]=nums[i]; // if only 1 piece, the first guy wins
        }
        for (int size=2;size<=len;size++) {
          for (int left=0;left<=len-size;left++) {
            int right=left+size-1;
            int chooseFirst = nums[left]+dp[1][left+1]; // first player choose left
            int chooseLast = nums[right]+dp[1][left]; // first player choose right
            dp[1][left]=chooseFirst>=chooseLast?dp[0][left+1]:dp[0][left]; // first guy has picked best for him
            dp[0][left]=chooseFirst>=chooseLast?chooseFirst:chooseLast; // first guy is picking best for him
          }
        }
        return dp[0][0]>=dp[1][0];
        // Write your solution here
    }
    /*
    657. Can I Win II
    There is an array of positive integers, in which each integer represents a piece of Pizza’s size, you and your friend take turns to pick pizza from either end of the array.  Your friend follows a simple strategy: He will always pick the larger one he could get during his turn. The winner is the one who gets the larger total sum of all pizza. Return the max amount of pizza you can get.
    Assumption: If during your friend's turn, the leftmost pizza has the same size as the rightmost pizza, he will pick the rightmost one.
    Example:
    Input: [2,1,100,3]
    Output: 102
    Explanation: To win the game, you pick 2 first, then your friend will pick either 3, after that you could pick 100. In the end you could get 2 + 100 = 102, while your friend could only get 1 + 3 = 4.
     */
    public int canWin(int[] nums) { // 657
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        int[][] dp = new int[len][len];
        for (int i=0;i<len;i++) {
            for (int j=i;j>=0;j--) { // j<=i
                if (i-j==0) {
                    dp[i][j]=nums[j];
                    continue;
                }
                if (i-j==1) {
                    dp[j][i]=Math.max(nums[j],nums[i]);
                    continue;
                }
                int chooseFirst,chooseLast=0;
                if (nums[j+1]>nums[i]) {
                    chooseFirst=nums[j]+dp[j+2][i];
                } else {
                    chooseFirst=nums[j]+dp[j+1][i-1];
                }
                if (nums[j]>nums[i-1]) {
                    chooseLast=nums[i]+dp[j+1][i-1];
                } else {
                    chooseLast=nums[i]+dp[j][i-2];
                }
                dp[j][i]=Math.max(chooseFirst,chooseLast);
            }
        }
        return dp[0][len-1];
        // Write your solution here
    }
    public int canWin4(int[] nums) { // 657
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        int[] dp = new int[len];
        int size=(len-1)%2+1;
        for (int i=0;i<len;i++) {
          if (size==1) {
            dp[i] = nums[i];
          } else {
            if (i==len-1) {break;}
            dp[i] = Math.max(nums[i], nums[i + 1]);
          }
        }
        for (size+=2;size<=len;size+=2) {
          for (int left=0;left<=len-size;left++) {
            int right=left+size-1;
            int chooseFirst = nums[left] + (nums[left+1]<=nums[right]?dp[left+1]:dp[left+2]);
            int chooseLast = nums[right] + (nums[left]<=nums[right-1]?dp[left]:dp[left+1]);
            dp[left] = chooseFirst >= chooseLast ? chooseFirst : chooseLast;
          }
        }
        return dp[0];
        // Write your solution here
    }
    public static void main(String[] args) {
        Pizza solution = new Pizza();
    }
}
