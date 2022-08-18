package com.laioffer.stringtest;
import java.util.*;

public class Representation {
    public String hex(int number) { // 78
        if (number<0) {return "";}
        StringBuilder sb = new StringBuilder();
        if (number==0) {
            sb.append('0');
        } else {
            while (number>0) {
                int num=number%16;
                number>>>=4;
                if (num<10) {
                    sb.append((char)(num+'0'));
                } else {
                    sb.append((char)(num-10+'A'));
                }
            }
        }
        sb=sb.reverse();
        sb.insert(0,"0x");
        return new String(sb);
        // Write your solution here
    }
    public int romanToInt(String input) { // 246
        if (input.length()==0) {return 0;}
        Map<Character, Integer> dict = new HashMap<>();
        dict.put('I', 1);
        dict.put('V', 5);
        dict.put('X', 10);
        dict.put('L', 50);
        dict.put('C', 100);
        dict.put('D', 500);
        dict.put('M', 1000);
        int ans=0,cur=0,nxt=0;
        for (int i=input.length()-1;i>=0;i--) {
            cur=dict.get(input.charAt(i));
            ans+=(cur<nxt?-1:1)*cur;
            nxt=cur;
        }
        return ans;
        // Write your solution here
    }
    public String intToRoman(int num) { // 247
        if (num<0) {return "NAN";}
        Map<Integer, Character> dict = new HashMap<>();
        dict.put(1,'I');
        dict.put(5,'V');
        dict.put(10,'X');
        dict.put(50,'L');
        dict.put(100,'C');
        dict.put(500,'D');
        dict.put(1000,'M');
        StringBuilder ans= new StringBuilder();
        int n=1;
        while (n<num+1) {n*=10;}
        while (n>1) {
            n/=10;
            int q=num/n;
            num-=q*n;
            if (q%5==4) {ans.append(dict.get(n));}
            if ((q+1)/5==1) {ans.append(dict.get(n*5));}
            for (int i=0;i<q%5%4;i++) {ans.append(dict.get(n));}
            if (q==9) {ans.append(dict.get(n*10));}
        }
        return ans.toString();
        // Write your solution here
    }
    /*
    528. Integer to English Words
    Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
    For example,
    123 -> "One Hundred Twenty Three"
    12345 -> "Twelve Thousand Three Hundred Forty Five"
    1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
     */
    public String numberToWords(int num) { // 528
        if (num<0) {return "";}
        if (num==0) {return "Zero";}
        String[] single = {"dummyZero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
        String[] teens = {"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
        String[] tens = {"dummyZero","dummyTen","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        String[] units = {"","Thousand","Million","Billion","Trillion"};
        String[] prefix = {""," "};
        StringBuilder result = new StringBuilder();
        int index=0,block=0,unit=1000000000;
        for (int i=3;i>=0;i--) { // up to billion
            block = num / unit; // get the current 3 digits
            int h = block / 100, t = block / 10 - h * 10, o = block % 10;
            if (h > 0) {
                result.append(prefix[index]);
                result.append(single[h]);
                result.append(" Hundred");
                index=1;
            }
            if (t > 1) {
                result.append(prefix[index]);
                result.append(tens[t]);
                index=1;
            } else if (t == 1) {
                result.append(prefix[index]);
                result.append(teens[block%10]);
                o=0;
                index=1;
            } else {} // t==0, do nothing
            if (o > 0) {
                result.append(prefix[index]);
                result.append(single[o]);
                index=1;
            }
            if (block>0) {result.append(prefix[i>0?1:0]);result.append(units[i]);}
            num%=unit;
            unit/=1000;
        }
        return new String(result);
        // Write your solution here
    }
    /*
    595. Solve Linear Equation
    Solve the given equation and return the value of variable x in the form of string "x=some number". The equation only contains '+', '-' operation, the variable x and its coefficient.
    If there is no solution to the equation, output "No solution".
    If there are infinite number of solutions, output "Infinite solutions".
    If there is only one solution, output the result, for example "x=1".
    Example 1:
    Input = "x+1=2x"
    Output = "x=1"
    Example 2:
    Input = "x+1=x+1"
    Output = "Infinite solutions"
    Example 3:
    Input = "x+1=x+2"
    Output = "No solution"
    Assumption:
    If the given equation has a solution, the value of the solution is guaranteed integer.
     */
    public String solveEquation(String equation) { // 595
        if (equation==null) {return "No solution";}
        char[] array = equation.toCharArray();
        int len=array.length,num=0;
        int a=0,b=0,sign=1,mid=1;
        for (int i=0;i<=len;i++) {
            char cur = i<len?array[i]:0;
            if (cur>='0' && cur <='9') {
                if (i==len) {
                    b+=num*sign*mid;
                } else {
                    num=num*10+(cur-'0');
                }
            } else {
                if (cur=='-') {
                    if (i>0 && array[i-1]!='x') {
                        b+=num*sign*mid;
                    }
                } else if (cur=='+') {
                    if (array[i-1]!='x') {
                        b+=num*sign*mid;
                    }
                } else if (cur=='x') {
                    if (i==0 || array[i-1]<'0' || array[i-1]>'9') {num=1;}
                    a+=num*sign*mid;
                } else { // =
                    if (array[i-1]!='x') {
                        b+=num*sign*mid;
                    }
                    mid=-1;
                }
                num=0;
                sign=cur=='-'?-1:1;
            }
        }
        if (a==0 && b==0) {return "Infinite solutions";}
        if (a==0 && b!=0) {return "No solution";}
        int c = -(b/a);
        return "x="+(c<0?"-":"")+String.valueOf(Math.abs(c));
        // Write your solution here
    }
    public static void main(String[] args) {
        Representation solution = new Representation();
    }
}
