package com.laioffer.Algorithm.DFS;
import java.util.*;

public class Pattern {
    /*
    508. Word Pattern
    Given a pattern and a string str, find if str follows the same pattern.
    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
    Examples:
    pattern = "abab", str = "redblueredblue" should return true.
    pattern = "aaaa", str = "asdasdasdasd" should return true.
    pattern = "aabb", str = "xyzabcxzyabc" should return false.
    Notes:
    You may assume both pattern and str contains only lowercase letters.
     */
    public boolean wordPatternMatch(String pattern, String input) { // 508
        if (pattern==null || input==null) {return false;}
        int li=input.length(),lp=pattern.length();
        if (lp>li) {return false;}
        Map<Character,String> map = new HashMap<>();
        Set<String> used = new HashSet<>();
        return dfs(pattern,input,0,0,map,used);
        // Write your solution here
    }
    private boolean dfs (String pattern, String input, int pindex, int iindex, Map<Character,String> map, Set<String> used) {
        if (pindex==pattern.length() && iindex==input.length()) {return true;}
        if (pindex==pattern.length() || iindex==input.length()) {return false;}
        char cp = pattern.charAt(pindex);
        String sp = map.get(cp);
        boolean result = false;
        int imax = input.length()-(pattern.length()-1-pindex);
        for (int i=iindex;i<imax;i++) {
            String cur = input.substring(iindex,i+1);
            if (sp==null && used.contains(cur) || sp!=null && !sp.equals(cur)) {continue;}
            if (sp==null) {  // no mapping yet
                map.put(cp,cur);
                used.add(cur);
                result = dfs (pattern, input, pindex+1, i+1, map, used);
                if (result) {return true;}
                used.remove(cur);
                map.remove(cp);
            } else {
                result = dfs (pattern, input, pindex+1, i+1, map, used);
                if (result) {return true;}
            }
        }
        return result;
    }
    /*
    628. Is the Last Character One-bit or Not
    Suppose we present three special characters using binary number 0, 10, 11.
    Given an array of integers containing only 1s and 0s to present a string that made up with the three special characters.
    Return the whether the last character of the string represented by the given array is a one-bit character or not.
    Assumptions:
    1. The given array is not null and its length is always >= 1.
    2. The last number of the given array is always 0.
    Example 1:
    Input:[1,1,0,0]
    Output: True
    Explanation: the given string is (11, 0, 0) then the last character can be one-bit
    Example 2:
    Input: [1,1,1,0]
    Output: False
    Explanation: The given string is (11, 10) then the last character cannot be one-bit to make it a valid string.
     */
    public boolean isOneBit(int[] bits) { // 628
        if (bits==null || bits.length==0) {return false;}
        int len = bits.length;
        return isOneBit(bits,0,len-1);
        // Write your solution here
    }
    private boolean isOneBit(int[] bits, int left, int right) {
        if (left==right) {return true;} // no single 1 should exist
        if (bits[right-1]==0) {return bits[right]==0;}
        if (left+1==right) {return false;}
        if (bits[left]==0) {
            return isOneBit(bits,left+1,right);
        } else { // bits[left]==1
            return isOneBit(bits,left+2,right);
        }
    }
    public static void main(String[] args) {
        Pattern solution = new Pattern();
    }
}
