package com.laioffer.DFS;
import java.util.*;

public class Subsets {
    public List<String> subSets(String set) { // 62
        // Write your solution here.
        List<String> result = new ArrayList<>();
        if (set==null) {return result;}
        StringBuilder sb = new StringBuilder();
        helper(set,sb,0,result);
        return result;
    }
    private void helper(String set, StringBuilder sb, int index, List<String> result) {
        int len=set.length();
        if (index==len) {
            result.add(new String(sb));
            return;
        }
        helper(set,sb.append(set.charAt(index)),index+1,result);
        sb.setLength(sb.length()-1);
        helper(set,sb,index+1,result);
    }
    public List<String> subSets1(String set) { // 62
        List<String> result = new ArrayList<>();
        if (set==null) {return result;}
        char[] aset = set.toCharArray();
        StringBuilder sb = new StringBuilder();
        helper1(aset,sb,0,result);
        return result;
    }
    private void helper1(char[] aset, StringBuilder sb, int index, List<String> result) {
        if (index==aset.length) {
            result.add(sb.toString());
            return;
        }
        helper1(aset,sb,index+1,result);
        helper1(aset,sb.append(aset[index]),index+1,result);
        sb.deleteCharAt(sb.length()-1);
    }
    public List<String> subSets2(String set) { // 63
        List<String> result = new ArrayList<>();
        if (set==null) {return result;}
        if (set.length()==0) {result.add("");return result;}
        char[] array = set.toCharArray();
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        helper2(array,sb,0,result);
        // Write your solution here.
        return result;
    }
    private void helper2(char[] array, StringBuilder sb, int index, List<String> result) {
        int len=array.length;
        if (index==len) {
            result.add(new String(sb));
            return;
        }
        helper2(array,sb.append(array[index]),index+1,result);
        sb.setLength(sb.length()-1);
        while (index<len-1 && array[index]==array[index+1]) {index++;}
        helper2(array,sb,index+1,result);
    }
    private int stringCompare(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);
            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
        if (l1 != l2) {
            return l1 - l2;
        } else {
            return 0;
        }
    }
    /*
    607. Longest Cycle
    A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of cycle C, where C[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.
    Suppose the first element in C starts with the selection of element A[i] of index = i, the next element in C should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in C.
    Example 1:
    Input: A = [5,4,0,3,1,6,2]
    Output: 4
    Explanation:
    A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
    One of the longest C[K]:
    C[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
    Note:
    N is an integer within the range [1, 20,000].
    The elements of A are all distinct.
    Each element of A is an integer within the range [0, N-1].
     */
    public int longest(int[] nums) { // 607
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        Set<Integer> visited = new HashSet<>();
        int max=-1;
        for (int i=0;i<len;i++) {
            if (visited.contains(i)) {continue;}
            int count=0,j=i;
            while (!visited.contains(j)) {
                visited.add(j);
                j=nums[j];
                count++;
            }
            if (count>max) {
                max=count;
                if (max>(len/2+1)) {return max;}
            }
        } // end for
        return max;
        // Write your solution here
    }
    /*
    640. All Subsets of Size K
    Given a set of characters represented by a String, return a list containing all subsets of the characters whose size is K.
    Assumptions
    There are no duplicate characters in the original set.
    Examples
    Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].
    Set = "", K = 0, all the subsets are [""].
    Set = "", K = 1, all the subsets are [].
     */
    public List<String> subSetsOfSizeK(String set, int k) { // 640
        if (set==null) {return null;}
        List<String> result = new ArrayList<>();
        if (set.length()<k) {return result;}
        helper(set.toCharArray(),result,new StringBuilder(),k,0);
        return result;
        // Write your solution here
    }
    private void helper (char[] array, List<String> result, StringBuilder sb, int k, int index) {
        if (k==0) {result.add(sb.toString());return;}
        int len = array.length;
        for (int i=index;i<=len-k;i++) {
            sb.append(array[i]);
            helper(array,result,sb,k-1,i+1);
            sb.setLength(sb.length()-1);
        }
    }
    /*
    641. All Subsets II of Size K
    Given a set of characters represented by a String, return a list containing all subsets of the characters whose size is K. Notice that each subset returned will be sorted for deduplication.
    Assumptions
    There could be duplicate characters in the original set.
    Examples
    Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].
    Set = "abb", K = 2, all the subsets are [“ab”, “bb”].
    Set = "abab", K = 2, all the subsets are [“aa”, “ab”, “bb”].
    Set = "", K = 0, all the subsets are [""].
    Set = "", K = 1, all the subsets are [].
    Set = null, K = 0, all the subsets are [].
     */
    public List<String> subSetsIIOfSizeK(String set, int k) { // 641
        if (set==null) {return null;}
        List<String> result = new ArrayList<>();
        if (k>set.length()) {return result;}
        if (k==0) {result.add("");return result;}
        char[] array = set.toCharArray();
        Arrays.sort(array);
        helper(array,k,0,result,new StringBuilder());
        return result;
        // Write your solution here
    }
    private void helper(char[] array, int k, int index, List<String> result, StringBuilder sb) {
        if (k==0) {
            result.add(new String(sb));
            return;
        }
        Set<Character> used = new HashSet<>();
        int len = array.length;
        for (int i=index;i<=len-k;i++) {
            if (used.add(array[i])) {
                sb.append(array[i]);
                helper(array,k-1,i+1,result,sb);
                sb.setLength(sb.length()-1);
            }
        }
    }
    /*
    643. All Permutations of Subsets
    Given a string with no duplicate characters, return a list with all permutations of the string and all its subsets.
    Examples
    Set = “abc”, all permutations are [“”, “a”, “ab”, “abc”, “ac”, “acb”, “b”, “ba”, “bac”, “bc”, “bca”, “c”, “cb”, “cba”, “ca”, “cab”].
    Set = “”, all permutations are [“”].
    Set = null, all permutations are [].
     */
    public List<String> allPermutationsOfSubsets(String set) { // 643
        List<String> result = new ArrayList<>();
        if (set==null) {return result;}
        helper(set.toCharArray(),0,result);
        return result;
    }
    private void helper(char[] array, int index, List<String> result) {
        int len=array.length;
        result.add(new String(array,0,index));
        for (int i=index;i<len;i++) {
            swap(array,i,index);
            helper(array,index+1,result);
            swap(array,i,index);
        }
    }
    private void swap(char[] array, int left, int right) {
        char tmp=array[left];
        array[left]=array[right];
        array[right]=tmp;
    }

    public String smallest(String s1, String s2) {
        if (s1==null || s2==null) {return null;}
        int l1=s1.length(),l2=s2.length();
        if (l1<l2) {return new String();}
        Map<Character,Integer> cs2 = new HashMap<>();
        Map<Character,Integer> cs1 = new HashMap<>();
        Map<Character,Integer> cs1f= new HashMap<>();
        for (int i=0;i<l2;i++) {
            char cur = s2.charAt(i);
            if (cs2.get(cur)==null) {
                cs2.put(cur,1);
                cs1.put(cur,0);
                cs1f.put(cur,0);
            } else {
                cs2.put(cur,cs2.get(cur)+1);
            }
        }
        char[] array = s1.toCharArray();
        for (int len=l2;len<=l1;len++) {
            if (len==l2) {
                for (int j=0;j<len;j++) {
                    if (cs1f.get(array[j])!=null) {
                        cs1f.put(array[j],cs1f.get(array[j])+1);
                    }
                }
            } else {
                if (cs1f.get(array[len-1])!=null) {
                    cs1f.put(array[len-1],cs1f.get(array[len-1])+1);
                }
            }
            copy(cs1f,cs1);
            if (find(cs1,cs2)) {return new String(array,0,len);}
            for (int i=1;i+len<=l1;i++) {
                if (cs1.get(array[i-1])!=null) {
                    cs1.put(array[i-1],cs1.get(array[i-1])-1);
                }
                if (cs1.get(array[i+len-1])!=null) {
                    cs1.put(array[i+len-1],cs1.get(array[i+len-1])+1);
                }
                if (find(cs1,cs2)) {return new String(array,i,len);}
            } // end for i
        } // end for len
        return new String();
        // Write your solution here
    }
    private void copy(Map<Character,Integer> cs1f, Map<Character,Integer> cs1) {
        for (Map.Entry<Character,Integer> e : cs1f.entrySet()) {
            cs1.put(e.getKey(),e.getValue());
        }
    }
    private boolean find(Map<Character,Integer> cs1, Map<Character,Integer> cs2) {
        for (Map.Entry<Character,Integer> e : cs2.entrySet()) {
            if (cs1.get(e.getKey())<e.getValue()) {return false;}
        }
        return true;
    }
    public static void main(String[] args) {
        Subsets solution = new Subsets();
//        String set = "dabc";
//        System.out.println(solution.subSets(set));
//        String set = "tebh";
//        System.out.println(solution.subSetsOfSizeK(set,3));
//        String set = "ofozjot";
//        System.out.println(solution.subSetsIIOfSizeK(set,6));
//        String input = "abab";
//        System.out.println(solution.subSets(input));
        String s1 = "movefastermoveslower";
        String s2 = "term";
        System.out.println(solution.smallest(s1,s2));
    }
}
