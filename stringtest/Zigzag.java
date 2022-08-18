package com.laioffer.stringtest;

public class Zigzag {
    public String convert(String input, int nRows) { // 251
        if (input==null || input.length()==0 || nRows==0) {return input;}
        if (nRows==1) {return input;}
        int len=input.length();
        int period = (nRows-1)*2;
        int quotient = len/period;
        if (len%period>0) {quotient++;}
        int index=0,count=0;
        char[] output = new char[len];
        for (int i=0;i<quotient*period;i++) {
            int qq=i/quotient,rr=i%quotient;
            if (qq<1) {
                index=rr*period;
            } else if (qq==period-1) {
                index=rr*period+(nRows-1);
            } else {
                int row = (i-quotient)/(quotient*2)+1;
                int tmp = (i-quotient)%(quotient*2);
                index=tmp/2*period+(tmp%2==0?row:period-row);
            }
            if (index>=len) {continue;}
            output[count++]=input.charAt(index);
        }
        return new String(output);
        // Write your solution here
    }
    public static void main(String[] args) {
        Zigzag solution = new Zigzag();
        System.out.println(solution.convert("PAHNAPLSIIGYIR",3));
    }
}
