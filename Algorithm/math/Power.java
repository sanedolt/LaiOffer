package com.laioffer.Algorithm.math;

public class Power {
    public int power538(int a, int b) { // 538
        int prd=1;
        for (int i=1;i<=b;i++) {
            prd*=a;
        }
        return prd;
        // Write your solution here
    }
    public long power13(int a, int b) { // 13
        if (b==0) {return 1L;}
        if (a==0) {return 0L;}
        long half=power13(a,b/2);
        if (b%2==1) {
            return half*half*a;
        } else {
            return half*half;
        }
        // Write your solution here
    }
    public boolean isPowerOfTwo(int number) { // 74
        return number>0 && (number&(number-1))==0;
        // Write your solution here
    }
    public boolean isPowerOfThree(int n) { // 460
        if (n<1) {return false;}
        return (int)(Math.pow(3,19))%n==0;
        // Write your solution here
    }
    public static void main(String[] args) {
        Power solution = new Power();
        System.out.println(solution.power13(-2,5));
    }
}
