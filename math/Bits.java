package com.laioffer.math;

import java.util.Arrays;

public class Bits {
    public int diffBits(int a, int b) { // 75
        a^=b;
        int c=0;
        while (a!=0) {
            c+=a&1;
            a>>>=1;
        }
        return c;
        // Write your solution here
    }
    public boolean allUnique76(String word) { // 76
        if (word==null || word.length()==0) {return true;}
        char[] array = word.toCharArray();
        int rec=0;
        for (char c : array) {
            if ((rec>>>(c-'a')&1)==1) {return false;}
            rec|=1<<(c-'a');
        }
        return true;
        // Write your solution here
    }
    public boolean allUnique77(String word) { // 77
        if (word==null || word.length()==0) {return true;}
        char[] array = word.toCharArray();
        int[] visited = new int[8];
        for (char c : array) {
            int group=c/32;
            int index=c%32;
            if ((visited[group]>>>index&1)==1) {return false;}
            visited[group]|=1<<index;
        }
        return true;
        // Write your solution here
    }
    /*
    408. Bitwise AND of Numbers Range
    Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
    For example, given the range [5, 7], you should return 4.
     */
    public int rangeBitwiseAnd(int m, int n) { // 408
        // if there are 1+1 consecutive numbers, then the last bit will be 0 after bitwise AND
        // if there are 2+1 consecutive numbers, then the next-to-last bit will be 0 after bitwise AND
        int p = n-m;
        int c=0;
        while (p>=1) {
            c++;
            p>>>=1;
        }
        m>>>=c;
        n>>>=c;
        int ba=-1;
        for (int i=m;i<=n;i++) {
            ba&=i;
        }
        return ba<<c;
        // Write your solution here
    }
    /*
    471. Counting Bits
    Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
    Example:
    For num = 5 you should return [0,1,1,2,1,2].
    Follow up:
    It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
    Space complexity should be O(n).
    Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
     */
    public int[] countBits(int num) { // 471
        int[] cb = new int[num+1];
        cb[0]=0;
        if (num==0) {return cb;}
        cb[1]=1;
        if (num==1) {return cb;}
        int ct=1,md=1; // ct is the the number being processed, md si the smaller number as power of 2
        while (++ct<=num) {
            if (ct-md==md) { // md should have 1 more digit
                md<<=1;
                cb[ct]=1;
            } else {
                cb[ct]=cb[ct-md]+cb[md];
            }
        }
        return cb;
        // Write your solution here
    }
    /*
    603. Binary Number with Alternating Bits
    Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
    Example 1:
    Input: 5
    Output: True
    Explanation:
    The binary representation of 5 is: 101
    Example 2:
    Input: 7
    Output: False
    Explanation:
    The binary representation of 7 is: 111.
    Example 3:
    Input: 11
    Output: False
    Explanation:
    The binary representation of 11 is: 1011.
    Example 4:
    Input: 10
    Output: True
    Explanation:
    The binary representation of 10 is: 1010.
     */
    public boolean hasAlternatingBits(int n) { // 603
        int temp = (n + (n>>>1));
        return (temp & (temp+1))==0;
        // Write your solution here
    }
    /*
    626. Reverse Bits of 32-bit Integer
    Reverse bits of a 32-bit integer.
    Note: In different programming languages, integers might be implemented differently in terms of number of bits, signed, unsigned, etc. However it should not affect your implementation on this problem. In java, the type of input is long, but you just need to work on the last 32-bit and consider it as an unsigned 32-bit integer.
    Example 1:
    Input: 1234 (0b'00000000000000000000010011010010)
    Output: 1260388352 (0b'01001011001000000000000000000000)
     */
    public long reverseBits1(long n) { // 626
        long m=0;
        for (int i=0;i<16;i++) {
            m += ((1L << i) & n) << (31 - i - i);
            System.out.println(i+" "+(1L<<i)+" "+((1L<<i)^n)+" "+(31-i-i)+" "+m);
        }
        for (int i=16;i<32;i++) {
            m+=((1L<<i)&n)>>(i+i-31);
            System.out.println(m);
        }
        return m;
        // Write your solution here
    }
    public long reverseBits(long n) { // 626
        for (int i=0;i<16;i++) {
            long right=(n>>i)&1L;
            long left=(n>>(31-i))&1L;
            if (left!=right) {
                n^=(1L<<i);
                n^=(1L<<(31-i));
            }
        }
        return n;
        // Write your solution here
    }
    public long reverseBits2(long n) { // 626
        n=((n&0xFFFF0000) >>> 16) | ((n&0x0000FFFF)<<16);
        n=((n&0xFF00FF00) >>> 8) | ((n&0x00FF00FF)<<8);
        n=((n&0xF0F0F0F0) >>> 4) | ((n&0x0F0F0F0F)<<4);
        n=((n&0xCCCCCCCC) >>> 2) | ((n&0x33333333)<<2);
        n=((n&0xAAAAAAAA) >>> 1) | ((n&0x55555555)<<1);
        return n;
        // Write your solution here
    }
    public int xorbtand(int[] input) {
        System.out.println(Arrays.toString(input));
        int len=input.length,res=0;
        double ln2 = Math.log(2);
        int[] count = new int[31];
        for (int i=0;i<len;i++) {
            int inp =input[i];
            int digit = (int) (Math.log(inp)/ln2);
            System.out.println(inp+" "+digit);
            res+=i-count[digit]++;
        }
        return res;
    }
    public static void main(String[] args) {
        Bits solution = new Bits();
        System.out.println(solution.diffBits(5,8));
        long n = 3081669670L;
        System.out.println(solution.reverseBits(n));
        int[] input = new int[]{4,3,5,2};
        System.out.println(solution.xorbtand(input));
    }

}
