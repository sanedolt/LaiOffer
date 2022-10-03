package com.laioffer.Algorithm.DFS;
import java.util.*;

public class Parentheses {
    public List<String> validParentheses66(int n) { // 66
        List<String> result = new ArrayList<>();
        if (n<=0) {return result;}
        StringBuilder sb = new StringBuilder();
        helper66(result,sb,n,n);
        return result;
        // Write your solution here
    }
    private void helper66(List<String> result, StringBuilder sb, int left, int right) {
        if (left+right==0) {
            result.add(new String(sb));
            return;
        }
        if (left>0) {
            sb.append('(');
            helper66(result,sb,left-1,right);
            sb.setLength(sb.length()-1);
        }
        if (right>left) {
            sb.append(')');
            helper66(result,sb,left,right-1);
            sb.setLength(sb.length()-1);
        }
    }
    /*
    237. Longest Valid Parentheses
    Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
    Example
    ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     */
    public int longestValidParentheses(String input) { // 237
        if (input==null || input.length()==0) {return 0;}
        int len=input.length(),ans=0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i=0;i<len;i++) {
            if (input.charAt(i)=='(') {
                stack.push(i);
            } else { // ')'
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans=Math.max(ans,i-stack.peek());
                }
            }
        }
        return ans;
        // Write your solution here
    }
    public List<String> validParentheses(int n) {
        List<String> result = new ArrayList<>();
        if (n<=0) {return result;}
        return helper(n);
        // Write your solution here
    }
    private List<String> helper(int n) {
        List<String> result = new ArrayList<>();
        if (n==0) {result.add("");return result;}
        List<String> temp = helper(n-1);
        Set<String> visited = new HashSet<>();
        for (String prev : temp) {
            String curr = "("+prev+")";
            if (!visited.contains(curr)) {
                result.add(curr);
                visited.add(curr);
            }
            curr="()"+prev;
            if (!visited.contains(curr)) {
                result.add(curr);
                visited.add(curr);
            }
            curr=prev+"()";
            if (!visited.contains(curr)) {
                result.add(curr);
                visited.add(curr);
            }
        } // end for
        System.out.println(n);
        System.out.println(result);
        return result;
    }
    /*
    411. Remove Invalid Parentheses
    Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
    Note: The input string may contain letters other than the parentheses ( and ).
    Examples:
    "()())()" -> ["()()()", "(())()"]
    "(a)())()" -> ["(a)()()", "(a())()"]
    ")(" -> [""]
     */
    public List<String> removeInvalidParentheses(String input) { // 411
        if (input==null || input.length()==0) {return new ArrayList<>();}
        int si = input.length();
        int left=0,right=0;
        for (int i=0;i<si;i++) {
            if (input.charAt(i)=='(') {
                left++; // unanswered left
            } else if (input.charAt(i)==')') {
                if (left>0) {
                    left--;
                } else {
                    right++; // unanswered right
                }
            } // end if
        } // end for
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        helper(input,0,left,right,0,0,sb,result);
        return result;
        // Write your solution here
    }
    private void helper (String input, int index, int left, int right, int curLeft, int curRight, StringBuilder sb, List<String> result) {
        if (index==input.length()) {
            if (left==0 && right==0) { // all extra are dropped
                result.add(sb.toString());
            }
            return;
        }
        char cur = input.charAt(index);
        if (cur=='(') {
            sb.append(cur);
            helper(input,index+1,left,right,curLeft+1,curRight,sb,result);
            sb.deleteCharAt(sb.length()-1);
            if (left>0) { // could drop a left bracket
                if (sb.length()==0 || sb.charAt(sb.length()-1)!=cur) { // if consecutive (, can drop the earlier one
                    helper(input,index+1,left-1,right,curLeft,curRight,sb,result);
                }
            }
        } else if (cur==')') {
            if (curLeft>curRight) { // can put a right bracket
                sb.append(cur);
                helper(input,index+1,left,right,curLeft,curRight+1,sb,result);
                sb.deleteCharAt(sb.length()-1);
            }
            if (right>0) { // could drop a right bracket
                if (sb.length()==0 || sb.charAt(sb.length()-1)!=cur) { // if consecutive ), can drop the earlier one
                    helper(input,index+1,left,right-1,curLeft,curRight,sb,result);
                }
            }
        } else { // just a letter
            sb.append(cur);
            helper(input,index+1,left,right,curLeft,curRight,sb,result);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private static final char[] PS = new char[]{'(',')','<','>','{','}'};
    public List<String> validParentheses(int l, int m, int n) { // 179
        List<String> result = new ArrayList<>();
        if (l<0 || m<0 || n<0 || l+m+n==0) {
            return result;
        }
        int[] remain = new int[] {l,l,m,m,n,n};
        char[] ans = new char[(l+m+n)*2];
        Deque<Character> stack = new ArrayDeque<>();
        helper(ans,result,remain,stack,0);
        return result;
        // Write your solution here
    }
    private void helper(char[] ans, List<String> result, int[] remain, Deque<Character> stack, int index) {
        if (index==ans.length) {
            result.add(new String(ans));
            return;
        }
        for (int i=0;i<remain.length;i++) {
            if (i%2==0) { // left brackets
                if (remain[i]>0) {
                    ans[index]=PS[i];
                    stack.offerFirst(PS[i]);
                    remain[i]--;
                    helper(ans,result,remain,stack,index+1);
                    stack.pollFirst();
                    remain[i]++;
                }
            } else { // right brackets
                if (!stack.isEmpty() && stack.peekFirst()==PS[i-1]) {
                    ans[index]=PS[i];
                    stack.pollFirst();
                    remain[i]--;
                    helper(ans,result,remain,stack,index+1);
                    stack.offerFirst(PS[i-1]);
                    remain[i]++;
                }
            }
        }
    }

    public List<String> validParenthesesIII(int l, int m, int n) { // 642
        List<String> result = new ArrayList<>();
        if (l<0 || m<0 || n<0 || l+m+n==0) {return result;}
        helper642(new char[(l+m+n)*2],result,new int[]{l,l,m,m,n,n},new ArrayDeque<>(),0);
        return result;
        // Write your solution here
    }
    private void helper642(char[] ans, List<String> result, int[] remain, Deque<Integer> stack, int index) {
        if (index==ans.length) {
            result.add(new String(ans));
            return;
        }
        for (int i=0;i<remain.length;i++) {
            if (i%2==0) { // left bracket
                if (remain[i]>0 && (stack.isEmpty() || stack.peekFirst()>i)) { // need to check if haing lower priority
                    ans[index]=PS[i];
                    stack.offerFirst(i);
                    remain[i]--;
                    helper642(ans,result,remain,stack,index+1);
                    stack.pollFirst();
                    remain[i]++;
                }
            } else { // right bracket
                if (!stack.isEmpty() && stack.peekFirst()==i-1) {
                    ans[index]=PS[i];
                    stack.pollFirst();
                    remain[i]--;
                    helper642(ans,result,remain,stack,index+1);
                    stack.offerFirst(i-1);
                    remain[i]++;
                }
            }
        }
    }
    /*
    242. Valid Parenthese
    Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. The brackets must close in the correct order.
    Examples
    "()" and "()[]{}", "[{()}()]" are all valid but "(]" and "([)]" are not.
     */
    public boolean isValid(String input) { // 242
        if (input==null || input.length()==0) {return true;}
        int len=input.length();
        Deque<Character> line = new ArrayDeque<>();
        Map<Character,Character> match = new HashMap<>();
        match.put('(',')');
        match.put('[',']');
        match.put('{','}');
        for (int i=0;i<len;i++) {
            char ci = input.charAt(i);
            if (ci=='(' || ci=='[' || ci=='{') {
                line.push(ci);
            } else if (ci==')' || ci==']' || ci=='}') {
                if (line.isEmpty() || match.get(line.poll())!=ci) {return false;}
            } else { // other character
                return false;
            }
        }
        return line.isEmpty();
        // Write your solution here
    }
    public List<List<String>> nBlocks(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n<1) {return result;}
        char[] ans = new char[n*2]; // a char array to make recording result easier
        helper(result,ans,0,n,n); // output, index/recursion layer, number of { left, number of } left
        return result;
    }
    private void helper(List<List<String>> result, char[] ans, int index, int left, int right) {
        if (left==0 && right==0) { // or index==ans.length==n*2
            result.add(getOutput(ans));
            return;
        }
        if (left>0) {
            ans[index]='{';
            helper(result,ans,index+1,left-1,right);
        } // no need to backtracking since it's controlled by index
        if (right>left) {
            ans[index]='}';
            helper(result,ans,index+1,left,right-1);
        }
    }
    private List<String> getOutput(char[] ans) {
        int indent = 0; // layer of nested bracket
        List<String> output = new ArrayList<>();
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == '{') {
                output.add(new String(spaces) + "if {");
                spaces.append("  ");
            } else { // ans[i]='}'
                spaces.setLength(spaces.length() - 2);
                output.add(new String(spaces) + "}");
            }
        }// end for i
        return output;
    }
    public static void main(String[] args) {
        Parentheses solution = new Parentheses();
//        String input = "))(()))(()()()(())(()";
//        System.out.println(solution.longestValidParentheses(input));
//        System.out.println(solution.validParentheses(2));
//        input = "(())f(())()))(t(((uk(((";
//        System.out.println(solution.removeInvalidParentheses(input));
//        System.out.println(solution.validParentheses(1,1,0));
//        System.out.println(solution.validParenthesesIII(3,1,0));
        System.out.println(solution.nBlocks(2));
    }
}
