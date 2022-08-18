package com.laioffer.stringtest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Length {
    /*
    168. Length of Last Word
    Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
    Input: s = “Hello World   “
    Output: 5
     */
    public int lenOflast(String input) { // 168
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
//        int left=0,last=0;
//        for (int right=0;right<=len;right++) {
//            if (right<len && input.charAt(right)!=' ') {continue;}
//            int dist=right-left;
//            if (dist>0) {last=dist;}
//            left=right+1;
//        }
//        return last;
        boolean word=false;
        int last=len;
        for (int i=len-1;i>=0;i--) {
            if (input.charAt(i)==' ') {
                if (!word) {last=i;continue;}
                return last-i-1;
            } else if (!word) {
                word=true;
            }
        }
        return word?last:0;
        // Write your solution here
    }
    public int longest(String input) {
        if (input==null) {return -1;}
        if (input.length()==0) {return 0;}
        int len=input.length();
        int[] loc = new int[26];
        int[] dist= new int[len];
        int effbeg=1; // next of repeated character on left
        for (int i=0;i<len;i++) {
            int ch = ((int) input.charAt(i))-97;
            if (loc[ch]>0) {
                dist[i]=Math.min(i+1-loc[ch],i+1-effbeg+1);
                effbeg=Math.max(effbeg,loc[ch]+1);
            } else if (i==0) {
                dist[i]=1;
            } else {
                dist[i]=Math.max(dist[i-1]+1,i+1-effbeg+1);
            }
            loc[ch]=i+1;
            System.out.println("i="+i+"effbeg="+effbeg+"ch="+ch+"dist="+dist[i]+"loc="+loc[ch]);
        }
        int max=-1;
        for (int i=0;i<len;i++) {
            if (dist[i]>max) {
                max=dist[i];
            }
        }
        return max;
    }
    public int lengthOfLongestSubstringTwoDistinct(String input) {
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
        if (len<3) {return len;}
        char[] inpstring = input.toCharArray();
        Set<Character> visited = new HashSet<>();
        int longest=-1;
        for (int i=0;i<len;i++) {
            visited.clear();
            visited.add(inpstring[i]);
            System.out.println(i+" "+inpstring[i]);
            int count=1,current=0,j=0;
            for (j=i+1;j<len;j++) {
                System.out.println(j+""+inpstring[j]+" "+count+" "+count);
                if (!visited.contains(inpstring[j])) {
                    count++;
                    visited.add(inpstring[j]);
                    if (count==3) {
                        current=j-i;
                        break;
                    }
                }
            } // end for j
            if (j==len) {current=j-i;}
            System.out.println(i+" "+current);
            if (current>longest) {longest=current;}
        } // end for i
        return longest;
        // Write your solution here
    }
    public int lengthOfLongestSubstringKDistinct(String input, int k) {
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
        if (len<=k) {return len;}
        char[] array = input.toCharArray();
        Map<Character,Integer> visited = new HashMap<>();
        int longest=-1,left=0,right=0;
        while (true) {
            while (right<len) {
                Integer cr = visited.get(array[right]);
                visited.put(array[right],cr==null?1:cr+1);
                if (cr==null && visited.size()==k+1) {break;}
                right++;
            }
            if (right-left>longest) {longest=right-left;}
            if (right++==len) {break;}
            while (left<right && left<len) {
                Integer cl = visited.get(array[left]);
                if (cl==1) {
                    visited.remove(array[left++]);
                    break;
                } else {
                    visited.put(array[left++],cl-1);
                }
            }
        }
        return longest;
        // Write your solution here
    }
    public String longestCommon(String source, String target) {
        if (source==null || target == null) {return "";}
        int ls=source.length(),lt=target.length();
        if (ls==0 || lt==0) {return "";}
        int[] prev = new int[lt];
        int[] curr = new int[lt];
        int max=-1,maxi=-1;
        for (int i=0;i<ls;i++) {
            for (int j=0;j<lt;j++) {
                if (source.charAt(i)==target.charAt(j)) {
                    if (i==0 || j==0) {
                        curr[j]=1;
                    } else {
                        curr[j]=prev[j-1]+1;
                    }
                } else {
                    curr[j]=0;
                } // end if
                if (max<curr[j]) {
                    max=curr[j];
                    maxi=i;
                }
            }
            for (int j=0;j<lt;j++) {
                prev[j]=curr[j];
            }
        }
        System.out.println(maxi+" "+max);
        return source.substring(maxi-max+1,maxi+1);
        // Write your solution here
    }
    public static void main(String[] args) {
        Length solution = new Length();
        String input = " an  apple  ";
//        System.out.println(solution.longest(input));
//        System.out.println(solution.lengthOfLongestSubstringTwoDistinct(input));
        String input2 = "yysbmhstkxtjarvjiupouikwddhxrtnfsspdmoxzldkcsmfqbhjdlypvoztcgzztveffmkyabbuizcovohynwusmjxtzavvfatsujxkgoxplmnhktezuxcqytlyjfvfhvleqdppejgcvtxtcjgnshzyrhnhsaqvagqdbnk";
        System.out.println(solution.lengthOfLongestSubstringKDistinct(input2,7));
//        String source = "abccddefffghhh";
//        String target = "bdhghhf";
//        System.out.println(solution.longestCommon(source,target));
    }
}
