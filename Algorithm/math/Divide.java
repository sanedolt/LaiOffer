package com.laioffer.Algorithm.math;
import java.util.*;

public class Divide {
    /*
    529. Division without loss of precision
    Given two integer a and b, calculate a divide by b but maintain the precision.
     */
    public double divide() { // 529
        int a = 3;
        int b = 7;
        double c = (double)a/b; //Complete the expression
        return c;
    }
    /*
    318. Compact Divide
    Given two integers a and b, return the result of a / b in String with compact format. The repeated decimal part should be identified and enclosed by "()".
    Examples
    0 / 2 = "0"
    4 / 2 = "2"
    1 / 2 = "0.5"
    -14 / 12 = "-1.1(6)"
    1 / 11 = "0.(09)"
    1 / 0 = "NaN"
    -1 / 0 = "NaN"
     */
    public String divide(int a, int b) { // 318
        if (b == 0) {return "NaN";}
        if (a == 0) {return "0";}
        if (a==b) {return "1";}
        long aa=a;
        long bb=b;
        StringBuilder result = new StringBuilder();
        if (aa<0^bb<0) {result.append('-');}
        aa=Math.abs(aa);
        bb=Math.abs(bb);
        result.append(String.valueOf(aa/bb));
        aa%=bb;
        if (aa==0) {return new String(result);}
        result.append('.');
        while (aa%2==0 && bb%2==0) {aa/=2;bb/=2;}
        while (aa%5==0 && bb%5==0) {aa/=5;bb/=5;}
        int t = (int) bb, c2=0, c5=0; // if the only factors of b are 2&5, then it is finite decimal number
        while (t % 2 == 0) {t/=2;c2++;}
        while (t % 5 == 0) {t/=5;c5++;}
        if (t==1 || t==-1) {
            while (aa!=0) {
                aa*=10;
                result.append((char)(aa/bb+'0'));
                aa%=bb;
            }
            return new String(result);
        }
        int m=c5>=c2?c5:c2;
        while (m-->0) {
            aa*=10;
            result.append((char)(aa/bb+'0'));
            aa%=bb;
        }
        long r = aa;
        result.append('('); // add repeated part
        for (int i=0;i<bb;i++) {
            if (aa==r && i>0) {
                result.append(')');
                return new String(result);
            }
            aa*=10;
            result.append((char)(aa/bb+'0'));
            aa%=bb;
        }
        return "";
        // Write your solution here
    }
    /*
    606. Maximal Division
    Given a list of positive integers, the adjacent integers will perform a float division. For example, [2,3,4] -> 2 / 3 / 4.
    However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.
    Example:
    Input: [1000,100,10,2]
    Output: "1000/(100/10/2)"
    Explanation:
    1000/(100/10/2) = 1000/((100/10)/2) = 200
    However, the bold parenthesis in "1000/((100/10)/2)" are redundant,
    since they don't influence the operation priority. So you should return "1000/(100/10/2)".
    Other cases:
    1000/(100/10)/2 = 50
    1000/(100/(10/2)) = 50
    1000/100/10/2 = 0.5
    1000/100/(10/2) = 2
    Note:
    The length of the input array is [1, 10].
    Elements in the given array will be in range [2, 1000].
    There is only one optimal division for each test case.
     */
    public String maxDivision(int[] nums) { // 606
        if (nums==null || nums.length<1 || nums.length>10) {return "";}
        int len=nums.length;
        double[][] max = new double[len+1][len+1];
        double[][] min = new double[len+1][len+1];
        String[][] maxPath = new String[len+1][len+1];
        String[][] minPath = new String[len+1][len+1];
        for (int j=0;j<len;j++) {
            for (int i=j;i>=0;i--) {
                if (i==j) {
                    max[i+1][j+1]=min[i+1][j+1]=nums[i];
                    maxPath[i+1][j+1]=minPath[i+1][j+1]=String.valueOf(nums[i]);
                    continue;
                }
                double t1=max[i+1][j]/nums[j];
                double t2=min[i+1][j]/nums[j];
                double t3=nums[i]/max[i+2][j+1];
                double t4=nums[i]/min[i+2][j+1];
                max[i+1][j+1]=Math.max(Math.max(t1,t2),Math.max(t3,t4));
                min[i+1][j+1]=Math.min(Math.min(t1,t2),Math.min(t3,t4));
                if (max[i+1][j+1]==t1) {
                    maxPath[i+1][j+1]=maxPath[i+1][j]+"/"+String.valueOf(nums[j]);
                } else if (max[i+1][j+1]==t2) {
                    maxPath[i+1][j+1]=minPath[i+1][j]+"/"+String.valueOf(nums[j]);
                } else if (max[i+1][j+1]==t3) {
                    maxPath[i+1][j+1]=String.valueOf(nums[i])+"/("+maxPath[i+2][j+1]+")";
                } else { // (max[i+1][j+1]==t4)
                    maxPath[i+1][j+1]=String.valueOf(nums[i])+"/("+minPath[i+2][j+1]+")";
                }
                if (min[i+1][j+1]==t1) {
                    minPath[i+1][j+1]=maxPath[i+1][j]+"/"+String.valueOf(nums[j]);
                } else if (min[i+1][j+1]==t2) {
                    minPath[i+1][j+1]=minPath[i+1][j]+"/"+String.valueOf(nums[j]);
                } else if (min[i+1][j+1]==t3) {
                    minPath[i+1][j+1]=String.valueOf(nums[i])+"/("+maxPath[i+2][j+1]+")";
                } else { // (min[i+1][j+1]==t4)
                    minPath[i+1][j+1]=String.valueOf(nums[i])+"/("+minPath[i+2][j+1]+")";
                }
            }
        }
        return maxPath[1][len];
        // Write your solution here
    }
    /*
    622. Self-Divided Numbers
    Self-divided numbers are numbers that are divisible by every digit in it.
    For example, 24 is divisible because 24 % 2 = 0 and 24 % 4 = 0.
    Obviously a self-divided number should not contain zero digit.
    Given a range [low, high] (both low and high are inclusive), return all self-divided numbers in the range.
    Example:
    Input: low = 1; high = 15
    Output: [1,2,3,4,5,6,7,8,9,11,12,15]
     */
    public List<Integer> selfDivideNumbers(int low, int high) { // 622
        List<Integer> result = new ArrayList<>();
        for (int i=low;i<=high;i++) {
            int n=i,m=n%10;
            boolean find=true;
            while (find) {
                if (m==0 && n==0) {break;}
                if (m==0 || i%m>0) {find=false;}
                n/=10;m=n%10;
            }
            if (find) {result.add(i);}
        }
        return result;
        // Write your solution here
    }
    public static void main(String[] args) {
        Divide solution = new Divide();
    }
}
