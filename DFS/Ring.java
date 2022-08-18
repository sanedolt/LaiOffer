package com.laioffer.DFS;

public class Ring {
    /*
    286. Form A Ring With Words
    Given an array of strings, find if the strings can be concatenated to form a ring. The two strings s1 and s2 can be concatenated iff the the last char of s1 is identical to the first char of s2. The first char of the first string in the array must be identical to the last char of the last string. The ring must contain every string in the input once and only once.
    Assumptions:
    The given array is not null or empty.
    None of the strings in the array is null or empty.
    Examples:
    input = {"aaa", "bbb", "baa", "aab"}, return true since it can be re-arranged to {"aaa", "aab", "bbb"  and "baa"}
    input = {"aaa", "bbb"}, return false
     */
    public boolean formRing(String[] input) { // 286
        if (input==null || input.length==0) {return false;} // corner case, assumed to be false
        return helper(input,1); // since it's a round table, can always let one student sit first, and then check whether other students can sit
        // Write your solution here
    }
    private boolean helper(String[] input, int index) {
        int len=input.length;
        if (index==len) {
            return canSit(input[len-1],input[0]); // since it's a round table, the last student also needs to be eligible to sit next to the first student
        }
        for (int i=index;i<len;i++) {
            if (canSit(input[index-1],input[i])) { // can sit with previous student
                swap(input,i,index);
                if (helper(input,index+1)) {return true;}
                swap(input,i,index);
            }
        }
        return false;
    }
    private boolean canSit(String s1, String s2) { // check whether s1's last character is the same as s2's first character
        return s1.charAt(s1.length()-1)==s2.charAt(0);
    }
    private void swap(String[] array, int left, int right) { // swap the reference of each string
        String tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }
    public static void main(String[] args) {
        Ring solution = new Ring();
    }
}
