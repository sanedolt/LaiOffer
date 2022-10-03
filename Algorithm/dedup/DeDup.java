package com.laioffer.Algorithm.dedup;
import java.util.Arrays;

/*
    For all logic below, everything before "keep" pointer is what we "keep", not include "keep" pointer itself.
    And "read" is the pointer to traverse (i.e. read) the whole array
    If you prefer "slow" vs "fast" pointer, "keep" is the "slow" pointer and "read" is the "fast" pointer
    79. String DeDup 1 and,
    115. Array Deduplication I
    are the same problem (sorted, for all dup values, keep only 1),
    so the core logic is to keep the value when a[read] != a[keep - 1]
    80. Remove Adjacent Repeated Characters II and,
    116. Array Deduplication II
    are the same problem
    Their only differences to 79 & 115 is that keep max 2 for all dup values, so all 4 of them have very similar code.
    so the core logic is to keep the value when a[read] != a[keep - 2]
    315. Array Deduplication V is very close to above four problem too
    only variation is that array is not sorted (and the ask is to keep max 2 for all consecutive dup values)
    So we check both of the last two values before we decide to keep a value or not:
    a[read] != a[keep - 2] || a[read] != a[keep - 1]
    81. Remove Adjacent Repeated Characters III and,
    117. Array Deduplication III
    are the same problem for String and Array respectively
    the core logic I use here is to use a start pointer to record the starting index of each unique value (char/int)
    and let the read pointer move to the 1st of next different value,
    then use read - start as the count of each unique char/int, this way we know we only those with count of 1
    82. Remove Adjacent Repeated Characters IV and,
    118. Array Deduplication IV
    are the same problem for String and Array respectively
    the difference of 82 & 118 to 81 & 117 is that, not only you have to remove all consecutive values,
    if deleting something cause none-dup value to become dup, you'll need to delete that too.
    e.g.: int 81 & 117 "abbbaaccz" will be de-duped as "az"
    but 72 & 118 as us to de-dup it as "z"
    The key is to use the keep pointer (named as top in code below) as top of an "imaginary stack"
    as delete from stack is needed, we'll need to move top(keep) pointer back when dup is found, detail below
    And these two are the only code we use the keep(top) pointer to include value of the pointer index itself
    As this reconcile better with the concept of "top of stack"
    All problem have time complexity of O(n) as we traverse the array/string exactly once.
    And space complexity is O(1) if we don't consider the convert from String to char[] which is a java specific problem
    Of course, if we consider that, space will be O(n)
 */

public class DeDup {
    /*
    79. String DeDup 1
    Remove adjacent, repeated characters in a given string, leaving only one character for each group of such characters.
    Assumptions
    Try to do it in place.
    Examples
    “aaaabbbc” is transferred to “abc”
    Corner Cases
    If the given string is null, returning null or an empty string are both valid.
    */
    public String deDup79(String input) { // 79
        if (input==null || input.length()==0) {return input;}
        char[] array = input.toCharArray();
        int len=array.length;
        int slow=1,fast=1;
        while (fast<len) {
            if (array[fast]==array[slow-1]) {
                fast++;
            } else {
                array[slow++]=array[fast++];
            }
        }
        return new String(array,0,slow);
        // Write your solution here
    }
    /*
    115. Array Deduplication I
    Given a sorted integer array, remove duplicate elements.
    For each group of elements with the same value keep only one of them.
    Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array.
    Return the array after deduplication.
    Assumptions
    The array is not null
    Examples
    {1, 2, 2, 3, 3, 3} → {1, 2, 3}
     */
    public int[] dedup115(int[] array) { // 115
        if (array==null || array.length<=1) {return array;}
        int slow=1;
        for (int right=1;right<array.length;right++) {
            if (array[right]!=array[right-1]) {
                array[slow++]=array[right];
            }
        }
        return Arrays.copyOf(array,slow);
        // Write your solution here
    }
    /*
    80. Remove Adjacent Repeated Characters II
    Remove adjacent, repeated characters in a given string, leaving only two characters for each group of such characters.
    The characters in the string are sorted in ascending order.
    Assumptions
    Try to do it in place.
            Examples
    “aaaabbbc” is transferred to “aabbc”
    Corner Cases
    If the given string is null, we do not need to do anything.
    */
    public String deDup80(String input) { // 80
        if (input==null || input.length()<=2) {return input;}
        int len=input.length();
        char[] array = input.toCharArray();
        int slow=2,fast=2;
        while (fast<len) {
            if (array[fast]==array[slow-2]) {
                fast++;
            } else {
                array[slow++]=array[fast++];
            }
        }
        return new String(array,0,slow);
        // Write your solution here
    }
    /*
    116. Array Deduplication II
    Given a sorted integer array, remove duplicate elements.
    For each group of elements with the same value keep at most two of them.
    Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array.
    Return the array after deduplication.
    Assumptions
    The given array is not null
    Examples
    {1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
    */
    public int[] dedup116(int[] array) { // 116
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        if (len==1) {return array;}
        int slow=2,fast=2;
        while (fast<len) {
            if (array[fast]==array[slow-2]) {
                fast++;
            } else {
                array[slow++]=array[fast++];
            }
        }
        return Arrays.copyOf(array,slow);
        // Write your solution here
    }
    /*
    81. Remove Adjacent Repeated Characters III
    Remove adjacent, repeated characters in a given string, leaving no character for each group of such characters.
    The characters in the string are sorted in ascending order.
    Assumptions
    Try to do it in place.
    Examples
    “aaaabbbc” is transferred to “c”
    Corner Cases
    If the given string is null, we do not need to do anything.
    */
    public String deDup81(String input) { // 81
        if (input==null || input.length()<=1) {return input;}
        int len=input.length();
        char[] array = input.toCharArray();
        int slow=0,fast=0;
        while (fast<len) {
            int fast2=fast+1;
            while (fast2<len && array[fast2]==array[fast]) {
                fast2++;
            }
            if (fast2==fast+1) {
                array[slow++]=array[fast];
            }
            fast=fast2;
        }
        return new String(array,0,slow);
        // Write your solution here
    }
    /*
    117. Array Deduplication III
    Given a sorted integer array, remove duplicate elements.
    For each group of elements with the same value do not keep any of them.
    Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array.
    Return the array after deduplication.
    Assumptions
    The given array is not null
    Examples
    {1, 2, 2, 3, 3, 3} → {1}
    */
    public int[] dedup117(int[] array) { // 117
        if (array==null || array.length<=1) {return array;}
        int len=array.length;
        int slow=1,fast=1;
        while (fast<len) {
            if (slow>0 && array[fast]==array[slow-1]) {
                while (fast<len && array[fast]==array[slow-1]) {fast++;}
                slow--;
            } else {
                array[slow++]=array[fast++];
            }
        }
        return Arrays.copyOf(array,slow);
        // Write your solution here
    }
    /*
    82. Remove Adjacent Repeated Characters IV
    Repeatedly remove all adjacent, repeated characters in a given string from left to right.
    No adjacent characters should be identified in the final string.
        Examples
    "abbbaaccz" → "aaaccz" → "ccz" → "z"
            "aabccdc" → "bccdc" → "bdc"
    */
    public String deDup82(String input) { // 82
        if (input==null || input.length()<=1) {return input;}
        char[] array = input.toCharArray();
        int len=array.length;
        int slow=0,fast=0;
        while (fast<len) {
            if (slow==0 || array[fast]!=array[slow-1]) {
                array[slow++]=array[fast++];
            } else { // array[fast]==array[slow-1], find next element which does not equal to array[slow-1]
                while (fast<len && array[fast]==array[slow-1]) {
                    fast++;
                }
                slow--;
            }
        }
        return new String(array,0,slow);
        // Write your solution here
    }
    /*
    118. Array Deduplication IV
    Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right.
    For each group of elements with the same value do not keep any of them.
    Do this in-place, using the left side of the original array. Return the array after deduplication.
    Assumptions
    The given array is not null
    Examples
    {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
    */
    public int[] dedup118(int[] array) { // 118
        if (array==null || array.length<=1) {return array;}
        int len=array.length;
        int slow=1,fast=1;
        while (fast<len) {
            if (slow>0 && array[fast]==array[slow-1]) {
                while (fast<len && array[fast]==array[slow-1]) {fast++;}
                slow--;
            } else {
                array[slow++]=array[fast++];
            }
        }
        return Arrays.copyOf(array,slow);
        // Write your solution here
    }
    /*
    315. Array Deduplication V
    Given an integer array(not guaranteed to be sorted),
    remove adjacent repeated elements. For each group of elements with the same value keep at most two of them.
    Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array.
    Return the final array.
    Assumptions
    The given array is not null
    Examples
    {1, 2, 2, 3, 3, 3} --> {1, 2, 2, 3, 3}
    {2, 1, 2, 2, 2, 3} --> {2, 1, 2, 2, 3}
    */
    public int[] dedup315(int[] array) { // 315
        if (array==null || array.length<=2) {return array;}
        int len=array.length;
        int slow=2,fast=2;
        while (fast<len) {
            if (array[fast]==array[slow-1] && array[fast]==array[slow-2]) {
                int fast2=fast+1;
                while (fast2<len && array[fast2]==array[slow-2]) {fast2++;}
                if (fast2==len) {break;}
                fast=fast2;
            } else {
                array[slow++]=array[fast++];
            }
        }
        return Arrays.copyOf(array,slow);
        // Write your solution here
    }
    public static void main(String[] args) {
        DeDup solution = new DeDup();
//        int[] input = new int[] {1,2,2,3,2,2,2,3,3,3};
//        solution.printArray(solution.dedup5(input));
    }
}
