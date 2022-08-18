package com.laioffer.optimized;

public class Replace {
    /*
    350. Replacements Of A And B
    Given a string with only character 'a' and 'b', replace some of the characters such that the string becomes in the forms of all the 'b's are on the right side of all the 'a's.
    Determine what is the minimum number of replacements needed.
     */
    public int minReplacements(String input) { // 350
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
        if (len==1) {return 0;}
        int count1=0,count2=0,count3=0,count4=0,subcount=0;
        // consider 4 cases, but only 1&2 or 3&4 are necessary for this question
        // 1. the former subarray looks like aaaa, thus ending at current in a
        // 2. the former subarray looks like aabb, thus ending at current in b
        // 3. the trailling subarray looks like aabb, thus beginning at current in a
        // 4. the trailling subarray looks like bbbb, thus beginning at current in b
        for (int i=0;i<len;i++) {
            if (input.charAt(i)=='a') {
                //count1;
                count2=Math.min(count1,count2+1);
            } else { // 'b'
                count2=Math.min(count1,count2);
                count1++;
            }
            if (input.charAt(len-1-i)=='b') {
                count3=Math.min(count3,count4-subcount)+1;
                subcount=0; // number of as before tailling b
                //count4;
            } else { // 'a'
                count4++;
                subcount++;
                count3=Math.min(count3,count4-subcount);
            }
        }
        return Math.min(count1,count2);
        //return Math.min(count3,count4);
        // Write your solution here
    }
    public static void main(String[] args) {
        Replace solution = new Replace();
    }
}
