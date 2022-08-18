package com.laioffer.stringtest;
import java.util.*;

public class Substring {
    /*
    85. Determine If One String Is Another's Substring
    Determine if a small string is a substring of another large string.
    Return the index of the first occurrence of the small string in the large string.
    Return -1 if the small string is not a substring of the large string.
    Assumptions
    Both large and small are not null
    If small is empty string, return 0
    Examples
    “ab” is a substring of “bcabc”, return 2
    “bcd” is not a substring of “bcabc”, return -1
    "" is substring of "abc", return 0
     */
    public int strstr(String large, String small) { // 85
        if (large==null || small==null) {return -1;}
        int ll=large.length(),ls=small.length();
        if (ls==0) {return 0;}
        if (ls>ll) {return -1;}
        int smallPrime=31,largePrime=101;
        int seed=1,tarHash=0,curHash=0;
        for (int i=0;i<ls;i++) {
            curHash = moduleHash(curHash,large.charAt(i),smallPrime,largePrime);
            tarHash = moduleHash(tarHash,small.charAt(i),smallPrime,largePrime);
            if (i>0) {seed=moduleHash(seed,0,smallPrime,largePrime);}
        }
        for (int i=0;i<=ll-ls;i++) {
            if (i>0) {
                curHash-=seed*large.charAt(i-1)%largePrime;
                curHash+=curHash<0?largePrime:0;
                curHash=moduleHash(curHash,large.charAt(i+ls-1),smallPrime,largePrime);
            }
            if (curHash==tarHash && equals(large,i,small)) {
                return i;
            }
        }
        return -1;
        // Write your solution here
    }
    private boolean equals(String large, int start, String small) {
        for (int i=0;i<small.length();i++) {
            if (large.charAt(start+i)!=small.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    private int moduleHash(int hash, int addition, int smallPrime, int largePrime) {
        return (hash*smallPrime%largePrime+addition)%largePrime;
    }
    /*
    156. Minimum Window Substring
    Given a string S and a string T, find the minimum window in S which will contain all the characters in T
    Input: S = “ADOBECODEBANC”
          T = “ABC”
    Output: “BANC”
     */
    public String minWindow(String source, String target) { // 156
        if (source==null || target==null) {return "";}
        int ls=source.length(),lt=target.length();
        if (ls<lt || lt==0) {return "";}
        Map<Character,Integer> tt = new HashMap<>();
        for (int i=0;i<lt;i++) {
            addChar(tt,target.charAt(i));
        }
        Map<Character,Integer> ss = new HashMap<>();
        int minLen=ls+1,minLeft=ls,left=0;
        for (int right=0;right<ls;right++) {
            char ch=source.charAt(right);
            if (tt.get(ch)==null) {
                if (ss.isEmpty()) {left++;}
                continue;
            }
            addChar(ss,ch);
            while (compMap(ss,tt)) {
                if (right-left+1<minLen) {
                    minLen=right-left+1;
                    minLeft=left;
                }
                left=delLeft(source,ss,left,right);
            }
        }
        if (minLen>ls) {return "";}
        return source.substring(minLeft,minLeft+minLen);
        // Write your solution here
    }
    private boolean compMap (Map<Character,Integer> map1, Map<Character,Integer> map2) {
        if (map1.size()!=map2.size()) {return false;}
        for (Map.Entry<Character,Integer> entry : map1.entrySet()) {
            if (map2.get(entry.getKey())>entry.getValue()) {return false;}
        }
        return true;
    }
    private void addChar (Map<Character,Integer> map, char ch) {
        Integer cn = map.get(ch);
        map.put(ch,cn==null?1:cn+1);
    }
    private int delLeft (String source, Map<Character,Integer> ss, int left, int i) {
        char cur = source.charAt(left);
        if (ss.get(cur)==1) {
            ss.remove(cur);
        } else {
            ss.put(cur,ss.get(cur)-1);
        }
        do {
            left++;
        } while (left<=i && ss.get(source.charAt(left))==null);
        return left;
    }
    /*
    176. Longest Common Substring
    Find the longest common substring of two given strings.
    Assumptions
    The two given strings are not null
    Examples
    S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”
     */
    public String longestCommon(String source, String target) { // 176
        if (source==null || target == null) {return "";}
        int ls=source.length(),lt=target.length();
        if (ls==0 || lt==0) {return "";}
        int[] curr = new int[lt]; // usbstring ending in index of target
        int max=0,right=-1;
        for (int i=0;i<ls;i++) {
            int pl=0;
            for (int j=0;j<lt;j++) {
                int tmp = curr[j];
                if (source.charAt(i)==target.charAt(j)) {
                    curr[j]=pl+1; // dp[i][j]=dp[i-1][j-1]+1
                    if (max<curr[j]) {
                        max=curr[j];
                        right=i;
                    }
                } else {
                    curr[j]=0;
                }
                pl = tmp; // feed for j=j+1
            }
        }
        return source.substring(right-max+1,right+1);
        // Write your solution here
    }
    /*
    253. Longest Substring Without Repeating Characters
    Given a string, find the longest substring without any repeating characters and return the length of it. The input string is guaranteed to be not null.
    For example, the longest substring without repeating letters for "bcdfbd" is "bcdf", we should return 4 in this case.
     */
    public int longest(String input) { // 253
        if (input==null) {return -1;}
        int len=input.length();
        if (len==0) {return 0;}
        Set<Character> ch = new HashSet<>();
        int max=-1,left=0,right=0;
        while (right<len) {
            if (ch.contains(input.charAt(right))) {
                ch.remove(input.charAt(left++));
            } else {
                ch.add(input.charAt(right++));
                max=Math.max(max,right-left);
            }
        }
        return max;
        // Write your solution here
    }
    /*
    285. Longest Substring With K Typed Characters
    Given a string, return the longest contiguous substring that contains exactly k type of characters.
    Return null if there does not exist such substring.
    Assumptions:
    The given string is not null and guaranteed to have at least k different characters.
    k > 0.
     */
    public String longest(String input, int k) { // 285
        if (input==null) {return input;}
        int len=input.length();
        if (k<=0 && k>len) {return "";}
        Map<Character,Integer> counts = new HashMap<>();
        char[] array = input.toCharArray();
        int left=0,maxleft=-1,maxlen=-1;
        for (int i=0;i<len;i++) {
            Integer cn = counts.get(array[i]);
            counts.put(array[i],cn==null?1:cn+1);
            while (counts.size()>k) {
                int times=counts.get(array[left]);
                if (times==1) {
                    counts.remove(array[left]);
                } else {
                    counts.put(array[left],times-1);
                }
                left++;
            } // end while
            if (counts.size()==k ) {
                int cursize = i-left+1;
                if (cursize>maxlen) {
                    maxlen=cursize;
                    maxleft=left;
                }
            } // end if
        }
        return new String(array,maxleft,maxlen);
        // Write your solution here
    }
    /*
    293. Smallest Substring Containing All Characters Of Another String
    Given two strings s1 and s2, find the shortest substring in s1 containing all characters in s2.
    If there does not exist such substring in s1, return an empty string.
    Assumptions:
    s1 and s2 are not null or empty.
    Examples:
    s1= “The given test strings”
    s2: “itsst”
    Output string: “st stri”
     */
    public String smallest(String s1, String s2) { // 293
        if (s1==null || s2==null) {return null;}
        int l1=s1.length(),l2=s2.length();
        if (l1<l2) {return new String();}
        Map<Character,Integer> cs2 = new HashMap<>();
        Map<Character,Integer> cs1 = new HashMap<>();
        Map<Character,Integer> cs1f= new HashMap<>();
        init(s1,s2,cs2,cs1,cs1f);
        char[] array = s1.toCharArray();
        for (int len=l2;len<=l1;len++) { // length of substring
            if (len>l2) { // adding the last element for length len starting from index 0
                Integer cn = cs1f.get(array[len-1]);
                if (cn!=null) {
                    cs1f.put(array[len-1],cn+1);
                }
            }
            copy(cs1f,cs1);
            for (int i=0;i+len<=l1;i++) { // sliding window starting at i with length len
                Integer cn = i==0?null:cs1.get(array[i-1]);
                if (cn!=null) {
                    cs1.put(array[i-1],cn-1);
                }
                cn = i==0?null:cs1.get(array[i+len-1]);
                if (cn!=null) {
                    cs1.put(array[i+len-1],cn+1);
                }
                if (cover(cs1,cs2)) {return new String(array,i,len);}
            } // end for i
        } // end for len
        return new String();
        // Write your solution here
    }
    private void init(String s1, String s2, Map<Character,Integer> cs2, Map<Character,Integer> cs1, Map<Character,Integer> cs1f) {
        int l2=s2.length();
        for (int i=0;i<l2;i++) {
            char cur = s2.charAt(i);
            Integer cn = cs2.get(cur);
            if (cn==null) {
                cs2.put(cur,1);
                cs1.put(cur,0);
                cs1f.put(cur,0);
            } else {
                cs2.put(cur,cn+1);
            }
        }
        for (int i=0;i<l2;i++) {
            char cur = s1.charAt(i);
            Integer cn = cs1f.get(cur);
            if (cn!=null) {
                cs1f.put(cur,cn+1);
            }
        }
    }
    private void copy(Map<Character,Integer> cs1f, Map<Character,Integer> cs1) {
        for (Map.Entry<Character,Integer> e : cs1f.entrySet()) {
            cs1.put(e.getKey(),e.getValue());
        }
    }
    private boolean cover(Map<Character,Integer> cs1, Map<Character,Integer> cs2) {
        for (Map.Entry<Character,Integer> e : cs2.entrySet()) {
            if (cs1.get(e.getKey())<e.getValue()) {return false;}
        }
        return true;
    }
    /*
    382. Shortest Substring With K Typed Characters
    Given a string, return the shortest contiguous substring that contains exactly k type of characters.
    Return an empty string if there does not exist such substring.
    Assumptions:
    The given string is not null.
    k >= 0.
    Examples:
    input = "aabcc", k = 3, output = "abc".
    input = "aabbbcccc", k = 3, output = "abbbc".
    input = "aabcc", k = 4, output = "".
     */
    public String shortest(String input, int k) { // 382
        if (input==null || input.length()==0 || k<=0) {return "";}
        int len=input.length();
        Map<Character,Integer> counts = new HashMap<>();
        int left=0,minleft=-1,minlen=Integer.MAX_VALUE;
        for (int right=0;right<len;right++) {
            char cur = input.charAt(right);
            Integer cn = counts.get(cur);
            if (cn==null) {
                counts.put(cur,1);
                if (counts.size()>k) {
                    counts.remove(input.charAt(left++));
                }
            } else {
                counts.put(cur,cn+1);
            }
            int times=counts.get(input.charAt(left));
            while (times>1) { // keep only 1 char in letter of left
                counts.put(input.charAt(left++),times-1);
                times=counts.get(input.charAt(left));
            }
            int cursize = right-left+1;
            if (counts.size()==k && cursize<minlen) {
                minlen=cursize;
                minleft=left;
            }
        }
        if (minlen<Integer.MAX_VALUE) {
            return input.substring(minleft,minleft+minlen);
        } else {
            return "";
        }
        // Write your solution here
    }
    /*
    398. All Anagrams
    Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.
    Assumptions
    sh is not null or empty.
    lo is not null.
    Examples
    l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba").
     */
    public List<Integer> allAnagrams(String sh, String lo) { // 398
        if (sh==null || sh.length()==0 || lo==null) {return null;}
        List<Integer> result = new ArrayList<>();
        int ll=lo.length(),ls=sh.length();
        if (ll==0) {return result;}
        Map<Character,Integer> shmap = toMap(sh);
        int count=shmap.size(); // number of letters need to match
        for (int right=0;right<ll;right++) {
            if (right>=ls) {
                char temp = lo.charAt(right-ls);
                Integer ct = shmap.get(temp);
                if (ct!=null) {
                    if (ct==0) {count++;}
                    shmap.put(temp,ct+1);
                }
            }
            char ch = lo.charAt(right);
            Integer cn = shmap.get(ch);
            if (cn==null) {continue;}
            shmap.put(ch,cn-1);
            if (cn==1) {
                if (--count==0) {result.add(right-ls+1);}
            }
        }
        return result;
        // Write your solution here
    }
    private Map<Character,Integer> toMap(String sh) {
        Map<Character,Integer> shmap = new HashMap<>();
        int ls=sh.length();
        for (int i=0;i<ls;i++) {
            char ch = sh.charAt(i);
            Integer cn = shmap.get(ch);
            shmap.put(ch,cn==null?1:cn+1);
        }
        return shmap;
    }
    /*
    476. Valid Anagram
    Given two strings s and t, write a function to determine if t is an anagram of s.
    For example,
    s = "anagram", t = "nagaram", return true.
    s = "rat", t = "car", return false.
    Note:
    You may assume the string contains only lowercase alphabets.
    Follow up:
    What if the inputs contain unicode characters? How would you adapt your solution to such case?
     */
    public boolean isAnagram(String source, String target) { // 476
        Map<Character,Integer> map = getMap(target);
        int ls=source.length(),lt=target.length();
        for (int i=0;i<ls;i++) {
            char cur = source.charAt(i);
            Integer cn = map.get(cur);
            if (cn==null) {return false;}
            map.put(cur,cn-1);
            if (cn==1) {map.remove(cur);}
        }
        return map.isEmpty();
        // Write your solution here
    }
    private Map<Character,Integer> getMap(String input) {
        Map<Character,Integer> result = new HashMap<>();
        for (int i=0;i<input.length();i++) {
            char cur = input.charAt(i);
            Integer cn = result.get(cur);
            result.put(cur,cn==null?1:cn+1);
        }
        return result;
    }
    /*
    473. Longest Substring with At Most K Distinct Characters
    Given a string, find the length of the longest substring T that contains at most k distinct characters.
    For example, Given s = “eceba” and k = 2,
    T is "ece" which its length is 3.
     */
    public int lengthOfLongestSubstringKDistinct(String input, int k) { // 473
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
        if (len<=k) {return len;}
        char[] array = input.toCharArray();
        Map<Character,Integer> visited = new HashMap<>();
        int longest=-1,left=0,right=0;
        while (true) {
            while (right<len) {
                Integer cr = visited.get(array[right]);
                visited.put(array[right],cr==null?1:cr+1);
                if (cr==null && visited.size()==k+1) {break;}
                right++;
            }
            if (right-left>longest) {longest=right-left;}
            if (right++==len) {break;}
            while (left<right && left<len) {
                Integer cl = visited.get(array[left]);
                if (cl==1) {
                    visited.remove(array[left++]);
                    break;
                } else {
                    visited.put(array[left++],cl-1);
                }
            }
        }
        return longest;
        // Write your solution here
    }
    /*
    490. Longest Substring with At Most Two Distinct Characters
    Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
    For example, Given s = “eceba”, T is "ece", return 3
     */
    public int lengthOfLongestSubstringTwoDistinct(String input) { // 490
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
        if (len<3) {return len;}
        char[] array = input.toCharArray();
        Map<Character,Integer> visited = new HashMap<>();
        visited.put(array[0],1);
        int left=0,longest=1;
        for (int right=1;right<len;right++) {
            Integer cn = visited.get(array[right]);
            visited.put(array[right],cn==null?1:cn+1);
            while (visited.size()==3) {
                char prev = array[left++];
                cn = visited.get(prev);
                if (cn==1) {visited.remove(prev);} else {visited.put(prev,cn-1);}
            }
            longest=Math.max(longest,right-left+1);
        } // end for i
        return longest;
        // Write your solution here
    }
    /*
    574. Find Permutation In String
    Suppose there are two strings s1 and s2. If a permutation of s1 is substring of s2, return true, or false otherwise.
    Example 1:
    Input: s1 = "evol", s2 = "ilovegoogle"
    Output: true
    Explanation: s2 contains a substring "love" which is one of permutations of s1.
    Example 2:
    Input: s1 = "evol", s2 = "iloovegoogle"
    Output: false
    Explanation: s2 does not contain any substring equals to any permutation of s1.
    Assumptions:
    Both s1 and s2 are not null and only contain lower case letters.
    Length of given strings are in range of [1, 1000].
     */
    public boolean containsPermutation(String s1, String s2) { // 574
        if (s1==null || s2==null) {return false;}
        int l1=s1.length(),l2=s2.length();
        if (l1==0) {return true;}
        if (l2==0) {return false;}
        Map<Character,Integer> c1 = new HashMap<>();
        for (int i=0;i<l1;i++) {
            Integer cn = c1.get(s1.charAt(i));
            c1.put(s1.charAt(i),cn==null?1:cn+1);
        } // treat this map as the letter need to find in s1
        int countMax=c1.size(),count=0;
        for (int i=0;i<l2;i++) {
            char cur = s2.charAt(i);
            if (i>=l1) {
                char left = s2.charAt(i-l1);
                Integer cn = c1.get(left);
                if (cn!=null) {
                    c1.put(left,cn+1);
                    if (cn==0) {count--;}
                }
            }
            Integer cn = c1.get(cur);
            if (cn!=null) {
                c1.put(cur,cn-1);
                if (cn==1) {
                    count++;
                    if (count==countMax) {return true;}
                }
            }
        }
        return false;
        // Write your solution here
    }
    /*
    617. First Unique Character in a String
    Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
    Examples:
    s = "laicode"
    return 0.
    s = "lovelaicode",
    return 2.
    Note: You may assume the string contain only lowercase letters.
     */
    public int firstUniqChar(String input) { // 617
        if (input==null || input.length()==0) {return -1;}
        Map<Character,Integer> count = new HashMap<>();
        char[] inp = input.toCharArray();
        int len=inp.length;
        for (int i=0;i<len;i++) {
            Integer cn = count.getOrDefault(inp[i],0);
            count.put(inp[i],cn+1);
        }
        for (int i=0;i<len;i++) {
            if (count.get(inp[i])==1) {return i;}
        }
        return -1;
        // Write your solution here
    }
    public static void main(String[] args) {
        Substring solution = new Substring();
        String source="ADOBECODEBANC";
        String target="ABC";
        System.out.println(solution.minWindow(source,target));
    }
}
