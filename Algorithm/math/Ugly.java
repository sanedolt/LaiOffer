package com.laioffer.Algorithm.math;
import java.util.*;

public class Ugly {
    /*
    425. Super Ugly Number
    Write a program to find the nth super ugly number.
    Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
    Note:
    (1) 1 is a super ugly number for any given primes.
    (2) The given numbers in primes are in ascending order.
    (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
    (4) The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
     */
    public int nthSuperUglyNumber(int n, int[] primes) { // 425
        int k=primes.length;
        int firstPrime=primes[0];
        if (n==1) {return 1;}
        if (n==2) {return firstPrime;}
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        pq.offer(1);
        while (n > 1) {
            int cur = pq.poll();
            for (int i=0;i<k;i++) {
                long val=(long) cur*primes[i];
                if (val>Integer.MAX_VALUE) {break;}
                int value=(int) val;
                if (!visited.contains(value)) {
                    pq.offer(value);
                    visited.add(value);
                }
            }
            n--;
        }
        return pq.poll();
        // Write your solution here
    }
    public static void main(String[] args) {
        Ugly solution = new Ugly();
    }
}
