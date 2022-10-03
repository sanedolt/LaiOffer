package com.laioffer.Algorithm.math;

public class Goldbach {
    public boolean isPrime(int n) {
        if (n==2) {return true;}
        for (int j=3;j*j<=n;j+=2) {
            if (n%j==0) {
                return false;
            }
        }
        return true;
    }
    public boolean checkGoldbach(int n) {
        if (n<4) {return false;}
        if (n==4) {return true;}
        for (int i=3;i<=n/2;i+=2) {
            if (isPrime(i) && isPrime(n-i)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Goldbach solution = new Goldbach();
        int limit=10000;
        for (int i=4;i<limit;i+=2) {
            if (solution.checkGoldbach(i)) {
                System.out.println(i+" is not a counter example.");
            } else {
                System.out.println("Congrats for the Fields Award in finding "+i);
                break;
            }
        }
    }

}
