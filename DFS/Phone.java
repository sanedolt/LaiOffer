package com.laioffer.DFS;
import java.util.*;

public class Phone {
    /*
    272. Combinations For Telephone Pad I
    Given a telephone keypad, and an int number, print all words which are possible by pressing these numbers, the output strings should be sorted.
    {0 : "", 1 : "", 2 : "abc", 3 : "def", 4 : "ghi", 5 : "jkl", 6 : "mno", 7 : "pqrs", 8 : "tuv", 9 : "wxyz"}
     */
    public String[] combinations(int number) { // 272
        List<String> result = new ArrayList<>();
        String[] numToChar={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        StringBuilder sb = new StringBuilder();
        helper(Integer.toString(number).toCharArray(),numToChar,0,sb,result);
        return result.toArray(new String[0]);
    }
    private void helper (char[] number, String[] numToChar, int level, StringBuilder sb, List<String> result) {
        if (level==number.length) {
            result.add(sb.toString());
            return;
        }
        char[] chars = numToChar[number[level]-'0'].toCharArray();
        if (chars.length==0) {
            helper(number,numToChar,level+1,sb,result);
        } else {
            for (char ch : chars) {
                helper(number,numToChar,level+1,sb.append(ch),result);
                sb.setLength(sb.length()-1);
            }
        }
    }
    public static void main(String[] args) {
        Phone solution = new Phone();
    }
}
