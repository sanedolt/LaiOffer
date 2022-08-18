package com.laioffer.math;
import java.util.*;

public class Factors {
    /*
    192. Kth Smallest With Only 2, 3 As Factors
    Find the Kth smallest number s such that s = 2 ^ x * 3 ^ y, x >= 0 and y >= 0, x and y are all integers.
    Assumptions
    K >= 1
    Examples
    the smallest is 1
    the 2nd smallest is 2
    the 3rd smallest is 3
    the 5th smallest is 2 * 3 = 6
    the 6th smallest is 2 ^ 3 * 3 ^ 0 = 8
    */
    public int kth192(int k) { // 192
        if (k<0) {return -1;}
        if (k<5) {return k;}
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        int[] primes = new int[]{2,3};
        pq.offer(1);
        while (--k>0) {
            int cur = pq.poll();
            for (int i=0;i<2;i++) {
                int tmp = cur*primes[i];
                if (!visited.contains(tmp)) {
                    pq.offer(tmp);
                    visited.add(tmp);
                }
            }
        }
        return pq.poll();
        // Write your solution here
    }
    /*
    193. Kth Smallest With Only 3, 5, 7 As Factors
    Find the Kth smallest number s such that s = 3 ^ x * 5 ^ y * 7 ^ z, x > 0 and y > 0 and z > 0, x, y, z are all integers.
    Assumptions
    K >= 1
    Examples
    the smallest is 3 * 5 * 7 = 105
    the 2nd smallest is 3 ^ 2 * 5 * 7 = 315
    the 3rd smallest is 3 * 5 ^ 2 * 7 = 525
    the 5th smallest is 3 ^ 3 * 5 * 7 = 945
     */
    public long kth193(int k) { // 193
        if (k==1) {return 105L;}
        // PriorityQueue<Integer> pq = new PriorityQueue<>();
        // Set<Integer> visited = new HashSet<>();
        // int[] primes = new int[]{3,5,7};
        // pq.offer(1);
        // while (--k > 0) {
        //   int cur = pq.poll();
        //   for (int i=0;i<3;i++) {
        //     int value=cur*primes[i];
        //     if (visited.add(value)) {
        //       pq.offer(value);
        //     }
        //   }
        // }
        // return (long) pq.poll()*105;
        Deque<Long> three = new ArrayDeque<>(); // 105*3^x
        Deque<Long> five = new ArrayDeque<>(); // 105*3^x*5^y
        Deque<Long> seven = new ArrayDeque<>(); // 105*3^x*5^y*7^z
        three.add(3L);
        five.add(5L);
        seven.add(7L);
        long result = 1L;
        while (--k>0) {
            if (three.peekFirst()<five.peekFirst() && three.peekFirst()<seven.peekFirst()) {
                result=three.pollFirst();
                three.offerLast(result*3);
                five.offerLast(result*5);
                seven.offerLast(result*7);
            } else if (five.peekFirst()<three.peekFirst() && five.peekFirst()<seven.peekFirst()) {
                result=five.pollFirst();
                five.offerLast(result*5);
                seven.offerLast(result*7);
            } else {
                result=seven.pollFirst();
                seven.offerLast(result*7);
            }
        }
        return result*105;
        // Write your solution here
    }
    /*
    311. Prime Factors
    Each positive integer larger than 1 is the production of several prime numbers. Return the list of prime factors in ascending order.
    Assumptions:
    The given number is >= 2.
     */
    public List<Integer> factors311(int target) { // 311
        List<Integer> result = new ArrayList<Integer>(1);
        if (target<=1) {return result;}
        int n=2;
        while (n<=target) {
            if (target%n==0) {
                result.add(n);
                target/=n;
            } else {
                if (n==2) {n++;} else {n+=2;};
            }
        }
        return result;
        // Write your solution here
    }
    public List<Integer> primes(int target) { // 312
        List<Integer> result = new ArrayList<Integer>();
        if (target<2) {return result;}
        result.add(2);
        boolean[] prim=new boolean[(target-1)/2];
        Arrays.fill(prim,true);
        for (int i=3;i<=target/3;i+=2) {
            int j=i*3;
            while (j<=target) {
                prim[(j-3)/2]=false;
                j+=i*2;
            } // end while
        } // end for
        for (int i=3;i<=target;i+=2) {
            if (prim[(i-3)/2]) {result.add(i);}
        }
        return result;
        // Write your solution here
    }
    /*
    415. Count Primes
    Count the number of prime numbers less than a non-negative number, n.
     */
    public int countPrimes(int n) { // 415
        if (n<=2) {return 0;}
        int count=1;
        for (int i=3;i<n;i+=2) {
            boolean factor=false;
            for (int j=3;j*j<=i;j+=2) {
                if (i%j==0) {factor=true;break;}
            }
            if (!factor) {count++;}
        }
        return count;
        // Write your solution here
    }
    /*
    313. Greatest Common Factor
    Find the greatest common factor of two positive integers.
    Examples:
    a = 12, b = 18, the greatest common factor is 6, since 12 = 6 * 2, 18 = 6 * 3.
    a = 5, b = 16, the greatest common factor is 1.
     */
    public int gcf(int a, int b) { // 313
        int s = Math.min(a,b);
        int l = a+b-s; // larger one
        int e = Math.min(s,l-s);
        for (int i=1;i<e;i++) {
            if (e%i>0) {continue;}
            int t=e/i; // t is the factor
            if (a%t==0 && b%t==0) {return t;}
        }
        return e==0?s:1;
        // Write your solution here
    }
    public boolean isPrime(int n) { // 535
        if (n<2) {return false;}
        if (n%2==0 && n>2) return false;
        int m=3;
        while (m*m<=n) {// Write your solution here
            if (n%m==0) return false;
            m+=2;
        }
        return true;
    }

    public List<List<Integer>> factors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n<2) {return result;}
        result = helper(n,2);
        return result;
    }
    private List<List<Integer>> helper (int n, int start) {
        List<List<Integer>> output = new ArrayList<>();
        if (n==1) {return output;}
        for (int i=start;i<=n;i++) {
            if (n%i>0) {continue;}
            List<List<Integer>> result = helper(n/i,i);
            if (i==n) {
                List<Integer> upd = new ArrayList<>();
                upd.add(i);
                output.add(upd);
            } else {
                for (List<Integer> cur : result) {
                    List<Integer> upd = new ArrayList<>();
                    upd.add(i);
                    upd.addAll(cur);
                    output.add(upd);
                } // end for
            }
        } // end for
        System.out.println(n+" "+start);
        System.out.println(output.toString());
        return output;
    }
    public static void main(String[] args) {
        Factors solution = new Factors();
        //System.out.println(solution.factors1(5).toString());
        System.out.println(solution.factors(100).toString());
    }
}
