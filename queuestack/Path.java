package com.laioffer.queuestack;
import java.util.*;

public class Path {
    /*
    159. Simplify Path
    Given an absolute path for a file (Unix-style), simplify it.
    Input: path = “/home/”
    Output: “/home”
    Input: path = “/a/./b/../../c/”
    Output: “/c”
     */
    public String simplify(String path) { // 159
        if (path==null) {return path;}
        char[] array = path.toCharArray();
        int len=array.length;
        Deque<List<Character>> dq = new ArrayDeque<>();
        for (int i=0;i<len;i++) {
            char cur = array[i];
            if (cur=='/') {
                if (i>1 && array[i-1]=='.') {dq.pollFirst();} // assuming /./
            } else if (cur=='.' && array[i-1]=='.') { // ..
                dq.pollFirst(); // get rid of . in curernt layer
                if (dq.size()>0) {dq.pollFirst();} // go above
            } else { // . or character
                if (i==0 || array[i-1]=='/') {dq.offerFirst(new ArrayList<>());}
                dq.peekFirst().add(cur);
            }
        }
        StringBuilder result = new StringBuilder();
        if (dq.size()>1 && dq.peekFirst().size()==0) {dq.pollFirst();}
        if (dq.size()==0) {
            result.append('/');
        } else {
            while (!dq.isEmpty()) {
                List<Character> layer = dq.pollLast();
                result.append('/');
                for (char ch : layer) {
                    result.append(ch);
                }
            }
        }
        return result.toString();
        // Write your solution here
    }
    public static void main(String[] args) {
        Path solution = new Path();
        System.out.println(solution.simplify("/a/b/../"));
    }
}
