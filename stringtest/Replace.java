package com.laioffer.stringtest;
import java.util.*;

public class Replace {
    /*
    649. String Replace (basic)
    Given an original string input, and two strings S and T, from left to right replace all occurrences of S in input with T.
    Assumptions
    input, S and T are not null, S is not empty string
    Examples
    input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
    input = "laicode", S = "code", T = "offer", input becomes "laioffer"
     */
    public String replace0(String input, String source, String target) { // 649
        if (input==null || source==null || target==null) {return null;}
        int li=input.length(),ls=source.length(),lt=target.length();
        if (ls==0) {return input;}
        int count=0;
        Deque<Integer> found = new ArrayDeque<>();
        for (int i=0;i<=li-ls;i++) {
            for (int j=0;j<ls;j++) {
                if (input.charAt(i+j)!=source.charAt(j)) {break;}
                if (j==ls-1) {count++;found.offerFirst(i+ls-1);}
            }
        }
        if (count==0) {return input;}
        int len=li+(lt-ls)*count;
        char[] result = new char[len];
        int slow=len-1;
        int check = found.pollFirst();
        for (int fast=li-1;fast>=0;fast--) {
            if (fast!=check) {
                result[slow--]=input.charAt(fast);
            } else {
                for (int j=lt-1;j>=0;j--) {
                    result[slow--]=target.charAt(j);
                }
                fast-=ls-1;
                check=found.isEmpty()?-1:found.pollFirst();
            }
        }
        return new String(result);
        // Write your solution here
    }
    /*
    172. String Replace
    Given an original string input, and two strings S and T, replace all occurrences of S in input with T.
    Assumptions
    input, S and T are not null, S is not empty string
    Examples
    input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
    input = "dodododo", S = "dod", T = "a", input becomes "aoao"
     */
    public String replace1(String input, String source, String target) { // 172
        if (input == null || input.length() == 0) {
            return input;
        }
        if (source == null || target == null || source.length() == 0) {
            return input;
        }
        int li = input.length(), ls = source.length(), lt = target.length();
        if (li < ls) {
            return input;
        }
        return input.replace(source, target);
    }
    public String replace2(String input, String source, String target) { // 172
        if (input == null || input.length() == 0) {
            return input;
        }
        if (source == null || target == null || source.length() == 0) {
            return input;
        }
        int li = input.length(), ls = source.length(), lt = target.length();
        if (li < ls) {
            return input;
        }
        Deque<Integer> found = new ArrayDeque<>();
        for (int i = 0; i <= li - ls; i++) {
            if (input.charAt(i) != source.charAt(0)) {
                continue;
            }
            int j = 1;
            for (; j < ls; j++) {
                if (input.charAt(i + j) != source.charAt(j)) {
                    break;
                }
            }
            if (j == ls) {
                found.offerFirst(i + ls - 1);
                i += ls - 1;
            }
        }
        int len = li + (lt - ls) * found.size();
        char[] result = new char[len];
        int check = found.pollFirst();
        int slow = len - 1;
        for (int fast = li - 1; fast >= 0; fast--) {
            if (fast != check) {
                result[slow--] = input.charAt(fast);
            } else {
                for (int j = lt - 1; j >= 0; j--) {
                    result[slow--] = target.charAt(j);
                }
                check = found.isEmpty() ? -1 : found.pollFirst();
                fast -= ls - 1;
            }
        }
        return new String(result);
    }
    public String replace3(String input, String source, String target) { // 172
        if (input==null || input.length()==0) {return input;}
        if (source==null || target==null || source.length()==0) {return input;}
        int li=input.length(),ls=source.length(),lt=target.length();
        if (li<ls) {return input;}
        StringBuilder sb = new StringBuilder();
        int fromIndex=0;
        int matchIndex=input.indexOf(source,fromIndex);
        while (matchIndex!=-1) {
          sb.append(input,fromIndex,matchIndex).append(target);
          fromIndex = matchIndex + source.length();
          matchIndex = input.indexOf(source,fromIndex);
        }
        sb.append(input,fromIndex,input.length());
        return sb.toString();
        // Write your solution here
    }
    public String replace4(String input, String source, String target) { // 172
        if (input==null || input.length()==0) {return input;}
        if (source==null || target==null || source.length()==0) {return input;}
        int li=input.length(),ls=source.length(),lt=target.length();
        if (li<ls) {return input;}
        char[] array = input.toCharArray();
        if (ls>=lt) {
            return replaceShorter(array,source,target);
        } else {
            return replaceLonger(array,source,target);
        }
        // Write your solution here
    }
    private String replaceShorter(char[] array, String s, String t) {
        int len=array.length,ls=s.length(),lt=t.length();
        int slow=0,fast=0;
        while (fast<len) {
            if (fast<=len-ls && equalSubstring(array,fast,s)) {
                copySubstring(array,slow,t);
                slow+=lt;
                fast+=ls;
            } else {
                array[slow++]=array[fast++];
            }
        }
        return new String(array,0,slow);
    }
    private String replaceLonger(char[] array, String s, String t) {
        ArrayDeque<Integer> matches = getAllMatches(array,s);
        int lt=t.length(),ls=s.length();
        char[] result = new char[array.length+matches.size()*(lt-ls)];
        int fast = array.length-1;
        int slow = result.length-1;
        while (fast>=0) {
            if (!matches.isEmpty() && fast==matches.peekFirst()) {
                copySubstring(result,slow-lt+1,t);
                slow-=lt;
                fast-=ls;
                matches.pollFirst();
            } else {
                result[slow--]=array[fast--];
            }
        }
        return new String(result);
    }
    private boolean equalSubstring(char[] array, int fromIndex, String s) {
        for (int i=0;i<s.length();i++) {
            if (array[fromIndex+i]!=s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    private void copySubstring(char[] result, int fromIndex, String t) {
        for (int i=0;i<t.length();i++) {
            result[fromIndex+i]=t.charAt(i);
        }
    }
    private ArrayDeque<Integer> getAllMatches(char[] array, String s) {
        ArrayDeque<Integer> matches = new ArrayDeque<Integer>();
        int ls=s.length(),i=0;
        while (i<=array.length-ls) {
            if (equalSubstring(array,i,s)) {
                matches.offerFirst(i+ls-1); // mark the end
                i+=ls;
            } else {
                i++;
            }
        }
        return matches;
    }
    /*
    282. Number Of Words
    Given a string, count number of words in it. The delimiters can be the following characters: space (‘ ‘) or new line (‘\n’) or tab (‘\t’) or a combination/duplication of these.
     */
    public int numOfWords(String input) { // 282
        if (input==null || input.length()==0) {return 0;}
        int len=input.length(),count=0;
        for (int i=0;i<len;i++) {
            if ((input.charAt(i)!=' ' && input.charAt(i)!='\n' && input.charAt(i)!='\t') &&
                    (i==0 || input.charAt(i-1)==' ' || input.charAt(i-1)=='\n' || input.charAt(i-1)=='\t')) {count++;}
        }
        return count;
        // Write your solution here
    }
    /*
    586. Replace Word With Shortest Prefix
    Given a list of word prefix and a sentence, replace the words in the sentence with its prefix if the prefix exists in the given list. If a word has multiple given prefix, replace with the shortest one. Return the sentence after replacement.
    Example:
    Input:
    prefix = [pro, program, de, re]
    sentence = "if debugging is the process of removing bugs programming must be the process of putting them in"
    Output: "if de is the pro of re bugs pro must be the pro of putting them in"
    Assumptions:
    The given sentence only contains space and lower case characters.
    The given list of prefix only contains lower case characters.
    There is no duplicates in the list of prefix.
     */
    public String replaceWords(List<String> prefixes, String sentence) { // 586
        if (sentence==null || sentence.length()==0) {return sentence;}
        int size=prefixes.size();
        char[] array = sentence.toCharArray();
        boolean[] matching = new boolean[size];
        Arrays.fill(matching,true);
        boolean replacing = false;
        int len=array.length,slow=0,left=0,right=0; // slow is places have selected
        while (right<len) {
            char cur = array[right];
            if (!replacing || replacing && cur==' ') {
                array[slow++]=cur;
            }
            if (cur!=' ') {
                if (!replacing) {
                    for (int i=0;i<size;i++) {
                        if (!matching[i]) {continue;} // already not matching
                        int index=right-left;
                        if (cur!=prefixes.get(i).charAt(index)) {
                            matching[i]=false;
                        } else if (index==prefixes.get(i).length()-1) {
                            replacing=true;
                            break;
                        }
                    }
                }
                right++;
            } else { // is a space
                replacing=false; // reset
                Arrays.fill(matching,true);
                left=++right;
            }
        }
        while (array[slow-1]==' ') {slow--;}
        return new String(array,0,slow);
        // Write your solution here
    }
    /*
    600. To Lower Case
    Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
     */
    public String toLowerCase(String str) { // 600
        if (str==null || str.length()==0) {return str;}
        char[] array = str.toCharArray();
        int len=array.length;
        for (int i=0;i<len;i++) {
            if (array[i]>='A' && array[i]<='Z') {
                array[i]=(char) (array[i]-'A'+'a');
            }
        }
        return new String(array);
        // Write your solution here
    }
    public static void main(String[] args) {
        Replace solution = new Replace();
        System.out.println(solution.replace4("tywjtltyvtyegcboycmqtyq","ty","c"));
    }
}
