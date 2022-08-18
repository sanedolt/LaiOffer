package com.laioffer.queuestack;
import java.util.*;

public class Suffix {
    /*
    370. Evaluate Suffix Expression
    Evaluate suffix expression, the expression is represented by an String array. The elements in the array are either an integer or "+", "-", "*", "/"
    Assumptions:
    The expression array is not null and it is guaranteed to be a valid infix expression.
    Examples:
    {"2", "12", "4", "+", "*"} --> 2 * (12 + 4) = 32
     */
    public int evaluate(String[] suffix) { // 370
        if (suffix==null || suffix.length==0) {return 0;}
        Deque<Integer> stack = new ArrayDeque<>();
        for (String cur : suffix) {
            if (cur=="+" || cur=="-" || cur =="*" || cur=="/") {
                int b = stack.pollFirst();
                int a = stack.pollFirst();
                if (cur=="+") {
                    stack.offerFirst(a+b);
                } else if (cur=="-") {
                    stack.offerFirst(a-b);
                } else if (cur=="*") {
                    stack.offerFirst(a*b);
                } else {
                    stack.offerFirst(a/b);
                }
            } else {
                stack.offerFirst(Integer.parseInt(cur));
            }
        }
        return stack.pollFirst();
        // Write your solution here
    }
    /*
    380. Suffix To Infix
    Given a suffix expression, convert it to infix one with the minimum number parentheses.
    The expression is represented by a string and each token is separated by ' '.
    Examples:
    "1 2 3 - +" is converted to "1 + 2 - 3", ("1 + ( 2 - 3 )" is not the correct answer since it has an extra parenthese).
    "1 2 3 * +" is converted to "1 + 2 * 3"
    "1 2 3 + *" is converted to "1 * ( 2 + 3 )"
     */
    public String convert(String suffix) { // 380
        if (suffix==null || suffix.length()==0) {return suffix;}
        char[] array = suffix.toCharArray();
        int len=array.length;
        Map<Character,Integer> level = new HashMap<>();
        level.put('+',1);level.put('-',2);level.put('*',3);level.put('/',4);
        Deque<Boolean> par = new ArrayDeque<>();
        int left=0,right=len-1,count=0,prev=1;
        while (left<=right) {
            if (array[left]!=' ') {
                left++;
            } else if (array[right]==' ') {
                right--;
            } else {
                if (difPrio(level.get(array[right]),prev)) {
                    count++;
                    par.offerFirst(true);
                } else {
                    par.offerFirst(false);
                }
                prev=level.get(array[right]);
                swap(array,left++,right--);
            }
        }
        len=left+count*2;
        int fast=left-1,slow=len-1;
        while (count-->0) {
            array[slow--]=')';
        }
        while (array[fast]>='0' && array[fast]<='9') {
            array[slow--]=array[fast--];
        }
        while (fast>=0) {
            array[slow--]=array[fast--]; // operator
            while (fast>=0 && array[fast]>='0' && array[fast]<='9') {
                array[slow--]=array[fast--];
            }
            if (par.pollFirst()) {
                array[slow--]='(';
            }
        }
        return new String(array,0,len);
        // Write your solution here
    }
    private boolean difPrio(int op1, int op2) {
        if (op2==1) {return false;}
        //if (op2==4) {return true;}
        return op1<=2;
    }
    private void swap(char[] array, int a, int b) {
        char tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
    public static void main(String[] args) {
        Suffix solution = new Suffix();
    }
}
