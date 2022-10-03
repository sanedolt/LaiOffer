package com.laioffer.Algorithm.arraytest;

import java.util.HashMap;
import java.util.Map;

public class Strobogrammatic {
    /*
    479. Strobogrammatic Number
    A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
    Write a function to determine if a number is strobogrammatic. The number is represented as a string.
    For example, the numbers "69", "88", and "818" are all strobogrammatic.
     */
    public boolean isStrobogrammatic(String num) { // 479
        char[] inp=num.toCharArray();
        Map<Character, Character> dict = new HashMap<>();
        dict.put('1', '1');
        dict.put('6', '9');
        dict.put('8', '8');
        dict.put('9', '6');
        dict.put('0', '0');
        boolean result=true;
        int left=0,right=num.length()-1;
        while (left<=right) {
            char cur= inp[left++];
            if (cur!='0' && cur!='1' && cur!='6' && cur!='8' && cur!='9') {return false;}
            if (inp[right--]!=dict.get(cur)) {return false;}
        }
        return result;
        // Write your solution here
    }
    /*
    480. Strobogrammatic Number III
    A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
    Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
    For example,
    Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
    Note:
    Because the range might be a large number, the low and high numbers are represented as string.
     */
    public int strobogrammaticInRange(String low, String high) { // 480
        if (low=="" || high=="") {return -1;}
        if (high.length()<low.length()) {return -1;}
        Map<Character, Character> dict = stroMap();
        int count=getTotal(low.length(),high.length());
        count-=removeLow(low,dict);
        count-=removeHigh(high,dict);
        return count;
        // Write your solution here
    }
    private Map<Character,Character> stroMap() {
        Map<Character, Character> dict = new HashMap<>();
        dict.put('1', '1');
        dict.put('6', '9');
        dict.put('8', '8');
        dict.put('9', '6');
        dict.put('0', '0');
        return dict;
    }
    private int getTotal(int ll, int lh) {
        int count=0;
        for (int i=ll;i<=lh;i++) {
            if (i % 2 == 0) {
                count+= 4 * (int) Math.pow(5, i / 2 - 1);
            } else if (i==1) { // 1/8
                count+= 2;
            } else {
                count+= 4 * (int) Math.pow(5, (i - 3) / 2) * 3;
            }
        }
        return count;
    }
    private int removeLow(String low, Map<Character,Character> dict) {
        int res=0,ll=low.length();
        boolean even=ll%2==0,val=true;
        for (int i=0;i<ll;i++) {
            char ch = low.charAt(i);
            int mul=cs(ch);
            if (i==0) {mul--;} // won't have 0 at first digit
            if (i<ll/2) {
                res+=mul*(int) Math.pow(5,ll/2-i-1)*(even?1:3); // head, first half, mid
                val=dict.containsKey(ch);
            } else if (i==ll/2 && !even) {
                res+=mul-(ch>'6'?1:0); // mid digit does not count 6(9)
                val= ch=='0' || ch=='1' || ch=='8';
            } else { // latter half
                if (ch>dict.get(low.charAt(ll-1-i))) {res++;}
                val=ch==dict.get(low.charAt(ll-1-i));
            }
            if (!val) {break;} // no need to check more digits
        }
        return res;
    }
    private int removeHigh(String high, Map<Character,Character> dict) {
        int res=0,lh=high.length();
        boolean even=lh%2==0,val=true;
        for (int i=0;i<lh;i++) {
            char ch = high.charAt(i);
            int mul=cb(ch);
            if (i<lh/2) {
                res+=mul*(int) Math.pow(5,lh/2-i-1)*(even?1:3);
                val=dict.containsKey(ch);
            } else if (i==lh/2 && !even) {
                res+=mul/2; // no physical meaning of /2, just happens to be this way
                val= ch=='0' || ch=='1' || ch=='8';
            } else { // latter half
                if (ch<dict.get(high.charAt(lh-1-i))) {res++;}
                val=ch==dict.get(high.charAt(lh-1-i));
            }
            if (!val) {break;}
        }
        return res;
    }
    private int cs(char input) { // possible normal digit as stro
        int n=input-'0';
        int res=0;
        if (n==0) {res=0;}
        if (n==1) {res=1;} //0
        if (n>=2 && n<=6) {res=2;} //0,1
        if (n>=7 && n<=8) {res=3;} //0,1,6
        if (n==9) {res=4;} // 0,1,6,8
        return res;
    }
    private int cb(char input) { // possible normal digit as stro
        int n=input-'0';
        int res=0;
        if (n==0) {res=4;} //1,6,8,9
        if (n>=1 && n<=5) {res=3;} //6,8,9
        if (n>=6 && n<=7) {res=2;} //8,9
        if (n==8) {res=1;} //9
        if (n==9) {res=0;}
        return res;
    }
    public static void main(String[] args) {
        Strobogrammatic solution = new Strobogrammatic();
//        System.out.println(solution.isStrobogrammatic("25"));
//        System.out.println("count="+solution.strobogrammaticInRange("2","50"));
    }
}
