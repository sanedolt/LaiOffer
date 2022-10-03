package com.laioffer.Algorithm.stringtest;
import java.util.*;

public class Isomorphic {
    /*
    342. Isomorphic String I
    Two Strings are called isomorphic if the letters in one String can be remapped to get the second String. Remapping a letter means replacing all occurrences of it with another letter. The ordering of the letters remains unchanged. The mapping is two way and no two letters may map to the same letter, but a letter may map to itself. Determine if two given String are isomorphic.
    Assumptions:
    The two given Strings are not null.
    Examples:
    "abca" and "xyzx" are isomorphic since the mapping is 'a' <-> 'x', 'b' <-> 'y', 'c' <-> 'z'.
    "abba" and "cccc" are not isomorphic.
     */
    public boolean isomorphic(String source, String target) { // 342
        if (source==null || target==null || source.length()!=target.length()) {return false;}
        int len=source.length();
        if (len<=1) {return true;}
        Map<Character,Character> s2t = new HashMap<>();
        Map<Character,Character> t2s = new HashMap<>();
        for (int i=0;i<len;i++) {
            char cs=source.charAt(i);
            char ct=target.charAt(i);
            if (s2t.get(cs)==null && t2s.get(ct)==null) {
                s2t.put(cs,ct);
                t2s.put(ct,cs);
            } else if (s2t.get(cs)==null || t2s.get(ct)==null || s2t.get(cs)!=ct || t2s.get(ct)!=cs) {
                return false;
            }
        }
        return true;
        // Write your solution here
    }
    public static void main(String[] args) {
        Isomorphic solution = new Isomorphic();
    }
}
