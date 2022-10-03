package com.laioffer.Algorithm.DFS;
import java.util.*;
public class Abbreviation {
    /*
    433. Generalized Abbreviation
    Write a function to generate the generalized abbreviations of a word.
    Example:
    Given word = "word", return the following list (order does not matter):
    ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd",
    "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2",
    "2r1", "3d", "w3", "4"]
     */
    public List<String> generateAbbreviations(String word) { // 433
        List<String> result = new ArrayList<>();
        if (word==null || word.length()==0) {return result;}
        helper(word.toCharArray(),0,result,new StringBuilder());
        return result;
    }
    private void helper(char[] array, int index, List<String> result, StringBuilder sb) {
        int len=array.length;
        if (index==len) {
            result.add(sb.toString());
            return;
        }
        char cur=array[index];
        int size=sb.length();
        char pre=size>0?sb.charAt(size-1):' ';
        char prv=size>1?sb.charAt(size-2):' ';
        // add the letter
        helper(array,index+1,result,sb.append(cur));
        sb.setLength(size);
        // add the number
        if (size==0 || pre>'9') { // not a number
            helper(array,index+1,result,sb.append('1'));
            sb.setLength(size);
        } else {
            int num=0;
            if (prv==' ' || prv>'9') {
                sb.setLength(size-1);
                num=pre-'0';
            } else {
                sb.setLength(size-2);
                num=(prv-'0')*10+(pre-'0');
            }
            helper(array,index+1,result,sb.append(String.valueOf(num+1)));
        }
    }
    public static void main(String[] args) {
        Abbreviation solution = new Abbreviation();
    }
}
