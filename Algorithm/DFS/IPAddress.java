package com.laioffer.Algorithm.DFS;
import java.util.*;

public class IPAddress {
    public List<String> Restore(String ip) { // 147
        List<String> result = new ArrayList<>();
        if (ip==null || ip.length()==0) {return result;}
        StringBuilder sb = new StringBuilder();
        char[] array = ip.toCharArray();
        helper(array,result,sb,0,0);
        return result;
        // Write your solution here
    }
    private void helper (char[] array, List<String> result, StringBuilder sb, int level, int index) {
        int len = array.length;
        for (int i=1;i<=3;i++) {
            if (len-index-i>(3-level)*3 || len-index-i<3-level) {continue;} // each number could have 1 to 3 digits
            if (array[index]=='0' && i>1) {continue;}
            if (Integer.parseInt(String.valueOf(Arrays.copyOfRange(array,index,index+i)))>255) {continue;}
            for (int j=0;j<i;j++) {
                sb.append(array[index+j]);
            }
            if (level<3) {
                helper(array,result,sb.append('.'),level+1,index+i);
                sb.delete(sb.length()-i-1, sb.length());
            } else {
                result.add(sb.toString());
                sb.delete(sb.length()-i, sb.length());
            }
        }
    }
    public static void main(String[] args) {
        IPAddress solution = new IPAddress();
        System.out.println(solution.Restore("10809010"));
    }
}
