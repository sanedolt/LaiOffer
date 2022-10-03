package com.laioffer.Algorithm.queuestack;
import java.util.*;

public class Polish {
    public int evalRPN(String[] tokens) { // 8
        if (tokens==null || tokens.length==0) {return 0;}
        Deque<String> stack = new ArrayDeque<>();
        int len=tokens.length;
        int index=0;
        while (index<len) {
            String cur = tokens[index++];
            if (cur.equals("+") || cur.equals("-") || cur.equals("*") || cur.equals("/")) {
                int num2 = Integer.parseInt(stack.pollFirst());
                int num1 = Integer.parseInt(stack.pollFirst());
                if (cur.equals("+")) {
                    stack.offerFirst(String.valueOf(num1+num2));
                } else if (cur.equals("-")) {
                    stack.offerFirst(String.valueOf(num1-num2));
                } else if (cur.equals("*")) {
                    stack.offerFirst(String.valueOf(num1*num2));
                } else { // cur.equals("/")
                    stack.offerFirst(String.valueOf(num1/num2));
                }
            } else {
                stack.offerFirst(cur);
            }
        }
        return Integer.parseInt(stack.pollFirst());
        // Write your solution here
    }
    public static void main(String[] args) {
        Polish solution = new Polish();
        //String[] input = new String[]{"2", "1", "+", "3", "*"};
        String[] input = new String[]{"4", "13", "5", "/", "+"};
        System.out.println(solution.evalRPN(input));
    }
}
