package com.laioffer.DFS;
import java.util.*;

public class Decode {
    public int numDecodeWay(String input) { // 148
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
        int[] dp = new int[len+1];
        dp[len]=1;
        for (int i=len-1;i>=0;i--) {
            char ch=input.charAt(i);
            if (ch=='0') {
                dp[i]=0;
            } else if (ch>'2') {
                dp[i]=dp[i+1];
            } else { // 1 or 2
                if (i==len-1) {
                    dp[i]=1;
                } else if (input.charAt(i+1)=='0' || ch=='2' && input.charAt(i+1)>'6') {
                    dp[i]=dp[i+2];
                } else {
                    dp[i]=dp[i+1]+dp[i+2];
                }
            }
        }
        return dp[0];
        // Write your solution here
    }
    public List<String> numDecodeWay2(String input) { // Exam 1
        List<String> result = new ArrayList<>();
        if (input==null || input.length()==0) {return result;}
        helper(input,result,0,new StringBuilder());
        return result;
    }
    private void helper(String input, List<String> result, int index, StringBuilder sb) {
        int len=input.length();
        if (index==len) {
            result.add(sb.toString());
            return;
        }
        char c0 = input.charAt(index);
        if (c0=='0') {return;} // cannot start with 0 for decoding
        if (c0<'0' || c0>'9') {return;} // sanity check
        char c1 = (char) (c0-49+65); // 1->A, 9->I
        char c2 = index==len-1?'z':(char) ((c0-'0')*10+(input.charAt(index+1)-'0')+64); // assign any letter which has ASCII larger than Z (90) if index is the last letter
        // use 1 digit for a letter, c1 won't be 0
        helper(input,result,index+1,sb.append(c1));
        sb.setLength(sb.length()-1);
        if (c2<='Z') { // use 2 digits for a letter, the arbitrary assigned letter when index==len-1 should give false here
            helper(input,result,index+2,sb.append(c2));
            sb.setLength(sb.length()-1);
        }
    }
    public List<String> decode(String input) { // Exam 1
        List<String> result = new ArrayList<>();
        if (input==null || input.length()==0) {return result;}
        helper2(input,result,0,new StringBuilder());
        return result;
    }
    private void helper2(String input, List<String> result, int index, StringBuilder sb) {
        int len=input.length();
        if (index==len) {
            result.add(sb.toString());
            return;
        }
        int num = input.charAt(index)-'0';
        if (num>0 && num<=9) {
            char cur = (char) (num-1+'A');
            helper2(input,result,index+1,sb.append(cur));
            sb.setLength(sb.length()-1);
        }
        if (index<len-1) {
            num=num*10+(input.charAt(index+1)-'0');
            if (num>=10 && num<=26) {
                char cur = (char) (num - 1 + 'A');
                helper2(input, result, index + 2, sb.append(cur));
                sb.setLength(sb.length() - 1);
            }
        }
    }
    public static void main(String[] args) {
        Decode solution = new Decode();
        String input = "1121";
        List<String> result = solution.decode(input);
        for (String res : result) {
            System.out.println(res);
        }
        System.out.println(solution.numDecodeWay("624212641113981521649688221891834112776717328126106"));
    }
}
