package com.laioffer.Algorithm.stringtest;
import java.util.*;

public class Calculator {
    /*
    448. Basic Calculator
    Implement a basic calculator to evaluate a simple expression string.
    The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
    You may assume that the given expression is always valid.
    Some examples:
    "1 + 1" = 2
    " 2-1 + 2 " = 3
    "(1+(4+5+2)-3)+(6+8)" = 23
    Note: Do not use the eval built-in library function.
     */
    public int calculate(String input) { // 448
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
        Deque<Integer> stack = new ArrayDeque<>();
        int operand=0,result=0,sign=1;
        for (int i=0;i<=len;i++) {
            char ch = i<len?input.charAt(i):'+';
            if (Character.isDigit(ch)) {
                operand=operand*10+(ch-'0');
            } else if (ch=='+') {
                result+=sign*operand;
                sign=1;
                operand=0;
            } else if (ch=='-') {
                result+=sign*operand;
                sign=-1;
                operand=0;
            } else if (ch=='(') {
                stack.push(result);
                stack.push(sign);
                sign=1;
                result=0;
            } else if (ch==')') {
                result+=sign*operand;
                result*=stack.pop();
                result+=stack.pop();
                operand=0;
            }
        }
        return result;
        // Write your solution here
    }
    /*
    450. Basic Calculator II
    Implement a basic calculator to evaluate a simple expression string.
    The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
    You may assume that the given expression is always valid.
    Some examples:
    "3+2*2" = 7
    " 3/2 " = 1
    " 3+5 / 2 " = 5
    Note: Do not use the eval built-in library function.
     */
    public int calculate450(String input) { // 450
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
        int res=0,last=0,num=0;
        char prev='+';
        for (int i=0;i<=len;i++) {
            char ch = i<len?input.charAt(i):'+';
            if (ch==' ') {continue;}
            if (Character.isDigit(ch)) {
                num=num*10+(ch-'0');
            } else {
                if (prev=='+' || prev=='-') {
                    res+=last;
                    last=prev=='-'?-num:num;
                } else if (prev=='*') {
                    last=last*num;
                } else {
                    last=last/num;
                }
                prev=ch;
                num=0;
            }
        }
        return res+last;
        // Write your solution here
    }
    /*
    Leetcode
    772. Basic Calculator III
    Implement a basic calculator to evaluate a simple expression string.
    The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
    You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
    Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
     */
    int len,index;
    public int calculate772(String s) {
        if (s==null || s.length()==0) {return 0;}
        index=0;len=s.length();
        return helper(s);
    }
    private int helper(String s) {
        int preNum=0,curNum=0,result=0;
        char prev='+';
        while (index<len) {
            char ch = s.charAt(index++);
            if (ch==' ') {continue;}
            if (Character.isDigit(ch)) {
                curNum=curNum*10+(ch-'0');
            }
            if (ch=='(') {
                curNum=helper(s);
            }
            if (ch=='+' || ch=='-' || ch=='*' || ch=='/' || ch==')' || index==len) {
                switch(prev) {
                    case '+' : result+=preNum;preNum=curNum;break;
                    case '-' : result+=preNum;preNum=-curNum;break;
                    case '*' : preNum*=curNum;break;
                    case '/' : preNum/=curNum;break;
                }
                if (ch==')') {break;}
                prev=ch;
                curNum=0;
            }
        }

        return result+preNum;
    }
    public static void main(String[] args) {
        Calculator solution = new Calculator();
//        String input = "(1+(4+5)+(2)-3)+(6+8)";
//        System.out.println(solution.calculate1(input));
        String input = " 3 *5 * 2 /4/7*9-3*5/4+5 / 2 /2 * 3/1-6*2+2/3+2*2*8*3";
        System.out.println(solution.calculate450(input));
    }
}
