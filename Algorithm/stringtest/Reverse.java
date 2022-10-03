package com.laioffer.Algorithm.stringtest;
import java.util.*;

public class Reverse {
    /*
    84. Reverse Words In A Sentence I
     */
    public String reverseWords84(String input) { // 84
        if (input==null) {return input;}
        char[] array = input.toCharArray();
        int len=array.length;
        int left=0;
        for (int i=0;i<len;i++) {
            if (array[i]==' ' || i==len-1) {
                int right=i==len-1?i:i-1;
                reverse(array,left,right);
                left=i+1;
            }
        }
        reverse(array,0,len-1);
        return new String(array);
        // Write your solution here
    }
    private void reverse(char[] array, int left, int right) {
        while (left<right) {
            swap(array,left++,right--);
        }
    }
    private void swap(char[] array, int left, int right) {
        char tmp = array[left];
        array[left]=array[right];
        array[right]=tmp;
    }
    /*
    197. ReOrder Array
    Given an array of elements, reorder it as follow:
    { N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }
    { N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }
    Try to do it in place.
     */
    public int[] reorder(int[] array) { // 197
        if (array==null) {return new int[0];}
        int len=array.length;
        convert(array,0,len/2*2-1);
        return array;
    }
    private void convert(int[] array, int left, int right) {
        if (left+1>=right) {return;}
        int size=right-left+1;
        int mid = left+size/2;
        int leftmid = left+size/4;
        int rightmid = mid+size/4;
        int midmid = leftmid+size/4;
        reverse(array,leftmid,mid-1);
        reverse(array,mid,rightmid-1);
        reverse(array,leftmid,rightmid-1);
        //reverse(array,leftmid,leftmid+size/4-1);
        //reverse(array,leftmid+size/4,rightmid-1);
        convert(array,left,midmid-1);
        convert(array,midmid,right);
    }
    private void reverse(int[] array, int left, int right) {
        while (left<right) {
            swap(array,left++,right--);
        }
    }
    private void swap(int[] array, int left, int right) {
        int tmp=array[left];
        array[left]=array[right];
        array[right]=tmp;
    }
    /*
    348. Reverse Only Vowels
    Only reverse the vowels('a', 'e', 'i', 'o', 'u') in a given string, the other characters should not be moved or changed.
     */
    public String reverse348(String input) { // 348
        if (input==null || input.length()<2) {return input;}
        char[] chin = input.toCharArray();
        Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u'));
        int left=0,right=input.length()-1;
        while (left<right) {
            if (!vowels.contains(chin[left])) {
                left++;
            } else if (!vowels.contains(chin[right])) {
                right--;
            } else {
                swap(chin,left++,right--);
            }
        }
        return String.valueOf(chin);
        // Write your solution here
    }
    /*
    383. Reverse Words In A Sentence II
    Reverse the words in a sentence and truncate all heading/trailing/duplicate space characters.
    Examples
    “ I  love  Google  ” → “Google love I”
    Corner Cases
    If the given string is null, we do not need to do anything.
     */
    public String reverseWords85(String input) { // 383
        if (input==null) {return input;}
        char[] array = input.toCharArray();
        int len=array.length;
        if (len==0) {return input;}
        int left=0,slow=0;
        while (left<len && array[left]==' ') {left++;}
        for (int i=left;i<=len;i++) {
            char cur = i<len?array[i]:' ';
            if (cur==' ') {
                if (array[i-1]==' ') {
                    left=i+1;
                } else {
                    reverse(array,left,i-1); // reverse a word
                    while (left<=i && left<len) {
                        array[slow++]=array[left++];
                    } // copy to left places
                }
            } // end if
        } // end for
        while (slow>0 & array[slow-1]==' ') {slow--;}
        reverse(array,0,slow-1);
        return new String(array,0,slow);
        // Write your solution here
    }
    /*
    396. Reverse String
     */
    public String reverse396(String input) { // 396
        if (input==null || input.length()<2) {return input;}
        int len=input.length();
        char[] array = input.toCharArray();
        reverse396(array,0,len-1);
        return String.valueOf(array);
        // Write your solution here
    }
    private void reverse396(char[] array, int left, int right) {
        while (left<right) {
            swap(array,left++,right--);
        }
    }
    /*
    397. Right Shift By N Characters
    Right shift a given string by n characters.
    Assumptions
    The given string is not null.
    n >= 0.
    Examples
    "abc", 4 -> "cab"
     */
    public String rightShift(String input, int n) { // 397
        if (input==null || input.length()==0) {return input;}
        int len=input.length();
        int shift=n%len;
        char[] array = input.toCharArray();
        reverse(array,0,len-1);
        reverse(array,0,shift-1);
        reverse(array,shift,len-1);
        return new String(array);
        // Write your solution here
    }
    public static void main(String[] args) {
        Reverse solution = new Reverse();
        String input = " an  apple";
        System.out.println(solution.reverseWords85(input));
    }
}
