package com.laioffer.Algorithm.dedup;
import java.util.*;

public class Remove {
    /*
    240. Remove Element
    Given an array and a value, remove all instances of that value in place and return a new array left elements. The order of elements can not be changed.
    Examples
    Input:     [1,2,3,1]
    value:      1
    Output:   [2,3]
     */
    public int[] removeElement(int[] input, int value) { // 240
        if (input==null) {return input;}
        int slow=0;
        for (int right=0;right<input.length;right++) {
            if (input[right]!=value) {
                input[slow++]=input[right];
            }
        }
        return Arrays.copyOfRange(input,0,slow);
        // Write your solution here
    }
    /*
    281. Remove Spaces
    Given a string, remove all leading/trailing/duplicated empty spaces.
     */
    public String removeSpaces(String input) { // 281
        if (input==null || input.length()==0) {return input;}
        char[] array = input.toCharArray();
        int len=array.length;
        int slow=0,fast=0;
        while (fast<len) {
            if (array[fast]==' ' && (fast==0 || array[fast-1]==' ')) {
                fast++;
            } else {
                array[slow++]=array[fast++];
            }
        }
        if (slow>0 && array[slow-1]==' ') {slow--;}
        return new String(array,0,slow);
        // Write your solution here
    }
    /*
    395. Remove Certain Characters
    Remove given characters in input string, the relative order of other characters should be remained. Return the new string after deletion.
    Assumptions
    The given input string is not null.
    The characters to be removed is given by another string, it is guaranteed to be not null.
    Examples
    input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
     */
    public String remove(String input, String t) { // 395
        if (input==null || input.length()==0) {return input;}
        if (t==null || t.length()==0) {return input;}
        Set<Character> tchar = new HashSet<>();
        for (int i=0;i<t.length();i++) {
            tchar.add(t.charAt(i));
        }
        char[] array = input.toCharArray();
        int slow=0;
        for (int fast=0;fast<array.length;fast++) {
            if (!tchar.contains(array[fast])) {
                array[slow++]=array[fast];
            }
        }
        return new String(array,0,slow);
        // Write your solution here
    }
    /*
    428. Remove Duplicate Letters
    Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
    Example:
    Given "bcabc"
    Return "abc"
    Given "cbacdcbc"
    Return "acdb"
     */
    public String removeDuplicateLetters(String input) { // 428
        if (input==null || input.length()==0) {return input;}
        // Map<Character,List<Integer>> appe = getMap(input);
        // StringBuilder result = new StringBuilder();
        // Set<Character> inserted = new HashSet<>();
        // helper(result,inserted,appe);
        // return new String(result);
        Stack<Character> stack = new Stack<>();
        HashSet<Character> seen = new HashSet<>();
        HashMap<Character, Integer> last_occurrence = new HashMap<>();
        for(int i = 0; i < input.length(); i++) {last_occurrence.put(input.charAt(i), i);}

        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            // we can only try to add c if it's not already in our solution
            // this is to maintain only one of each character
            if (!seen.contains(c)){
                // if the last letter in our solution:
                //     1. exists
                //     2. is greater than c so removing it will make the string smaller
                //     3. it's not the last occurrence
                // we remove it from the solution to keep the solution optimal
                while(!stack.isEmpty() && c < stack.peek() && last_occurrence.get(stack.peek()) > i){
                    seen.remove(stack.pop());
                }
                seen.add(c);
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder(stack.size());
        for (Character c : stack) {sb.append(c.charValue());}
        return sb.toString();
        // Write your solution here
    }
    private void helper(StringBuilder result, Set<Character> inserted, Map<Character,List<Integer>> appe) {
        int lv = appe.size();
        for (int n=0;n<lv;n++) {
            for (char i='a';i<='z';i++) { // find the lexicographical first available to use
                List<Integer> tmp = appe.get(i);
                if (tmp==null || inserted.contains(i)) {continue;}
                int currFirst = tmp.get(0);
                boolean find=false;
                for (char j: appe.keySet()) { // if any other character's any/last occurance before the first occurance of i
                    if (inserted.contains(j) || j==i) {continue;}
                    List<Integer> temp = appe.get(j);
                    if (temp.get(temp.size()-1)<currFirst) {
                        find=true;
                        break;
                    }
                } // end for j
                if (find) {continue;}
                result.append(i); // any character can appear after i
                inserted.add(i);
                for (char j: appe.keySet()) {
                    List<Integer> temp = appe.get(j);
                    if (inserted.contains(j)) {continue;}
                    while (temp.get(0)<currFirst) {temp.remove(0);}
                } // end for j
                break; // the current letter
            } // end for i
        } // end for n
    }
    private Map<Character,List<Integer>> getMap(String input) {
        Map<Character,List<Integer>> appe = new HashMap<>();
        int len=input.length();
        for (int i=0;i<len;i++) {
            char cur = input.charAt(i);
            List<Integer> temp = appe.get(cur);
            if (temp==null) {
                temp = new ArrayList<>();
                appe.put(cur,temp);
            }
            temp.add(i);
        }
        return appe;
    }
    public static void main(String[] args) {
        Remove solution = new Remove();
    }
}
