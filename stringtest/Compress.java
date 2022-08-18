package com.laioffer.stringtest;
import java.util.*;

public class Compress {
    /*
    83. Encode Space
    In URL encoding, whenever we see an space " ", we need to replace it with "20%". Provide a method that performs this encoding for a given string.
    Examples
    "google/q?flo wer market" → "google/q?flo20%wer20%market"
     */
    public String encode(String input) { // 83
        if (input==null || input.length()==0) {return input;}
        int len=input.length();
        int spaceCount=0;
        for (int i=0;i<len;i++) {
            if (input.charAt(i)==' ') {spaceCount++;}
        }
        char[] result = new char[len+spaceCount*2];
        int slow = result.length-1;
        for (int fast=len-1;fast>=0;fast--) {
            if (input.charAt(fast)==' ') {
                result[slow--]='%';
                result[slow--]='0';
                result[slow--]='2';
            } else {
                result[slow--]=input.charAt(fast);
            }
        }
        return new String(result);
        // Write your solution here
    }
    /*
    173. Compress String
    Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences. If the character does not has any adjacent, repeated occurrences, it is not changed.
    Assumptions
    The string is not null
    The characters used in the original string are guaranteed to be ‘a’ - ‘z’
    Examples
    “abbcccdeee” → “ab2c3de3”
     */
    public String compress173(String input) { // 173
        if (input==null || input.length()==0) {return input;}
        int len=input.length();
        char[] array = input.toCharArray();
        int count=1,slow=1,fast=0;
        while (++fast<=len) {
            if (fast<len && array[fast]==array[fast-1]) {
                count++;
            } else {
                if (count>1) {
                    String cn = String.valueOf(count);
                    for (int i=0;i<cn.length();i++) {
                        array[slow++]=cn.charAt(i);
                    }
                }
                if (fast<len) {
                    array[slow++]=array[fast];
                }
                count=1;
            }
        }
        return new String(array,0,slow);
        // Write your solution here
    }
    /*
    174. Decompress String I
    Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences. If the character does not have any adjacent repeated occurrences, it is not compressed.
    Assumptions
    The string is not null
    The characters used in the original string are guaranteed to be ‘a’ - ‘z’
    There are no adjacent repeated characters with length > 9
    Examples
    “acb2c4” → “acbbcccc”
     */
    public String decompress174(String input) { // 174
        if (input==null || input.length()==0) {return input;}
        int len=input.length();
        List<Character> output = new ArrayList<>();
        char cur=' ';
        int count=0;
        for (int i=0;i<=len;i++) {
            if (i==len || input.charAt(i)>'9') {
                while (--count>0) {
                    output.add(cur);
                }
                count=0;
                if (i<len) {
                    cur=input.charAt(i);
                    output.add(cur);
                }
            } else {
                count=count*10+input.charAt(i)-'0';
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i=0;i<output.size();i++) {
            result.append(output.get(i));
        }
        return new String(result);
        // Write your solution here
    }
    /*
    175. Decompress String II
    Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences.
    Assumptions
    The string is not null
    The characters used in the original string are guaranteed to be ‘a’ - ‘z’
    There are no adjacent repeated characters with length > 9
    Examples
    “a1c0b2c4” → “abbcccc”
     */
    public String decompress175(String input) { // 175
        if (input==null || input.length()==0) {return input;}
        char[] array = input.toCharArray();
        int len=array.length;
        int slow=0,newLength=0;
        for (int i=0;i<len;i+=2) {
            int digit = array[i+1]-'0';
            if (digit>=0 && digit<=2) {
                for (int j=0;j<digit;j++) {
                    array[slow++]=array[i];
                }
            } else {
                array[slow++]=array[i];
                array[slow++]=array[i+1];
            }
            newLength+=digit;
        }
        char[] old = array;
        array = new char[newLength];
        int fast=newLength-1;
        while (--slow>=0) {
            int digit=old[slow]-'0';
            if (digit>2 && digit<=9) {
                slow--;
                for (int j=0;j<digit;j++) {
                    array[fast--]=old[slow];
                }
            } else { // already in character
                array[fast--]=old[slow];
            }
        }
        return new String(array);
        // Write your solution here
    }
    /*
    292. String Abbreviation Matching
    Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, return if the string matches the abbreviation.
    Assumptions:
    The original string only contains alphabetic characters.
    Both input and pattern are not null.
    Pattern would not contain invalid information like "a0a","0".
    Examples:
    pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.
     */
    public boolean match(String input, String pattern) { // 292
        if (input==null || pattern==null) {return false;}
        int li=input.length();
        int lp=pattern.length();
        if (lp==0) {return li==0;}
        if (lp>li) {return false;}
        int ii=0,ip=0;
        while (ii<li & ip<lp) {
            char cp = pattern.charAt(ip++);
            if (cp<'0' || cp>'9' && cp<'A' || cp>'Z' && cp<'a' || cp>'z') {return false;}
            if (cp=='0' && (ii==0 || pattern.charAt(ii-1)>'9')) {return false;}
            if (cp>='0' && cp<='9') { // number
                int num=cp-'0';
                while (ip<lp && pattern.charAt(ip)>='0' && pattern.charAt(ip)<='9') {
                    num=num*10+pattern.charAt(ip++)-'0';
                }
                ii+=num;
            } else { // letter
                if (input.charAt(ii++)!=cp) {return false;}
            } // end if
        } // end i
        return ii==li && ip==lp;
        // Write your solution here
    }
    /*
    611. Compress String II
    Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences.
    Assumptions
    The string is not null
    The characters used in the original string are guaranteed to be ‘a’ - ‘z’
    Examples
    “abbcccdeee” → “a1b2c3d1e3”
     */
    public String compress611(String input) { // 611
        if (input==null || input.length()==0) {return input;}
        char[] array = input.toCharArray();
        int slow=0,fast=0,len=array.length,newLength=0;
        while (fast<len) {
            int begin=fast;
            while (fast<len && array[fast]==array[begin]) {
                fast++;
            }
            array[slow++]=array[begin];
            if (fast-begin==1) { // won't write 1 at this time
                newLength+=2;
            } else { // add the count
                String temp = String.valueOf(fast-begin);
                for (int i=0;i<temp.length();i++) {
                    array[slow++]=temp.charAt(i);
                }
                newLength+=temp.length()+1;
            }
        }
        char[] old = array;
        array = new char[newLength];
        fast=slow-1;
        slow=newLength-1;
        while (fast>=0) {
            if ((old[fast]<'0' || old[fast]>'9') && (slow==newLength-1 || old[fast+1]<'0' || old[fast+1]>'9')) {
                array[slow--]='1';
            }
            array[slow--]=old[fast--];
        }
        return String.valueOf(array);
        // Write your solution here
    }
    /*
    616. Decode String
    Given an encoded string, return it's decoded string.
    The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
    You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
    Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
    Examples:
    s = "3[a]2[bc]", return "aaabcbc".
    s = "3[a2[c]]", return "accaccacc".
    s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     */
    public String decodeString(String input) { // 616
        if (input==null || input.length()==0) {return input;}
        Deque<List<Character>> trav = new ArrayDeque<>();
        Deque<Integer> weight = new ArrayDeque<>();
        List<Character> first = new ArrayList<>();
        trav.offerFirst(first);
        weight.offerFirst(1);
        int len=input.length(),num=0;
        for (int i=0;i<len;i++) {
            char cur = input.charAt(i);
            if (cur=='[') {
                trav.offerFirst(new ArrayList<>());
                weight.offerFirst(Math.max(1,num));
                num=0;
            } else if (cur==']') {
                List<Character> last = trav.pollFirst();
                List<Character> layer = trav.peekFirst();
                int wei = weight.pollFirst();
                while (--wei>=0) {
                    layer.addAll(last);
                }
            } else if (cur>='0' && cur<='9') {
                num=num*10+cur-'0';
            } else { // letter
                trav.peekFirst().add(cur);
            }
        }
        char[] result = new char[first.size()];
        len=0;
        for (char ch : first) {result[len++]=ch;}
        return new String(result);
        // Write your solution here
    }
    /*
    621. Decompress String III
    Given a encoded string that compressed according to the following rules:
    N[inner_pattern] -> N is a positive integer and the inner pattern will be repeated N times.
    Decompress the encoded string and return the original string.
    Assumptions:
    N is alway positive integer.
    The input is always valid, i.e. the brackets are always in pair.
    The decompressed string (that is the return value) doesn't contain digit or brackets, which means digit, '[' and ']' are only used in encoded (compressed) string.
    Example 1:
    Input: "abc"
    Output: "abc"
    Example 2:
    Input: "ab3[cd[2e]]f"
    Output: "abcdeecdeecdeef"
     */
    public String decodeStringIII(String input) { // 621
        if (input==null || input.length()==0) {return input;}
        Deque<List<Character>> trav = new ArrayDeque<>();
        Deque<Integer> weight = new ArrayDeque<>();
        int len=input.length(),num=0;
        List<Character> first = new ArrayList<>();
        trav.offerFirst(first);
        weight.offerFirst(1);
        for (int i=0;i<len;i++) {
            char cur = input.charAt(i);
            if (cur=='[') {
                trav.offerFirst(new ArrayList<>());
                weight.offerFirst(Math.max(1,num));
                num=0;
            } else if (cur==']') {
                List<Character> last = trav.pollFirst();
                List<Character> layer = trav.peekFirst();
                int wei = weight.pollFirst();
                while (--wei>=0) {
                    layer.addAll(last);
                }
            } else if (cur>='0' && cur<='9') {
                num=num*10+(cur-'0');
            } else { // letter
                List<Character> layer = trav.peekFirst();
                for (int j=0;j<Math.max(1,num);j++) {
                    layer.add(cur);
                }
                num=0;
            }
        }
        char[] result = new char[first.size()];
        len=0;
        for (char ch:first) {result[len++]=ch;}
        return String.valueOf(result);
        // Write your solution here
    }
    public static void main(String[] args) {
        Compress solution = new Compress();
        //String input = "hhhhhhhhhhhhhhhhhhhhhxxxxxxxxxxxxxxaaaaaaaaaddddffffgooooooooooooll";
        String input = "njtsgqlxizxynbnrxyvylkeelvvgbejnjtohzbxecnbkqfblhwnfeattv";
        System.out.println(input.length());
    }
}
