package com.laioffer.Algorithm.queuestack;

public class NestedValue {
    /*
    284. Nested Value
    Given a string, calcualte the nested integer weighted sum.
    Weight is +1 for each deeper level of "{}".
    Assumptions:
    The given string is not null
    The only characters in the string is either '0' - '9' or '{', '}'
    Each digit character is calculated separately.
    Examples:
    input = “{2{4{6}}}”. output = 2×1 + 4×2 + 6×3 = 28.
    input = "{34{5}6}". output = 3 + 4 + 5x2 + 6 = 23.
     */
    public int value(String input) { // 284
        if (input==null || input.length()==0) {return 0;}
        int len=input.length(),layer=0,sum=0;
        for (int i=0;i<len;i++) {
            char cur = input.charAt(i);
            if (cur=='{') {
                layer++;
            } else if (cur=='}') {
                layer--;
            } else {
                sum+=layer*(cur-'0');
            }
        } // end for
        return sum;
        // Write your solution here
    }
    /*
    516. Nested List Weight Sum
    Given a nested list of integers represented by a string without blank, parse the string and  return the sum of all integers in the list weighted by their depth.
    Each element is either an integer, or a list -- whose elements may also be integers or other lists.
    Example 1:
    Given the list "[[1,1],2,[1,1]]", return 10. (four 1's at depth 2, one 2 at depth 1)
    Example 2:
    Given the list "[1,[4,[6]]]", return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
     */
    public int depthSum(String nestlists) { // 516
        int sum=0,layer=0,num=0,sign=1;
        for (int i=0;i<nestlists.length();i++) {
            char cur=nestlists.charAt(i);
            if (cur=='[') {
                layer++;
                num=0;
            } else if (cur==']') {
                sum+=sign*num*layer;
                layer--;
                num=0;
            } else if (cur==',') { // get number
                sum+=sign*num*layer;
                num=0;
            } else if (cur=='-') {
                sign=-1;
                num=0;
            } else {
                num=num*10+cur-48;
            }
        }
        return sum;
        // Write your solution here
    }
    public static void main(String[] args) {
        NestedValue solution = new NestedValue();
    }

}
