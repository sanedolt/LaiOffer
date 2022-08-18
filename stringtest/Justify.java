package com.laioffer.stringtest;
import java.util.*;

public class Justify {
    /*
    189. Test Justification
    Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified. For the last line, it should be left justified and no extra space is inserted between words.
     */
    public ArrayList<String> fullJustify(String[] words, int L) { // 189
        ArrayList<String> result = new ArrayList<>();
        if (words==null || words.length==0 || L<=0) {return result;}
        int len=words.length,left=0,count=0;
        char[] output = new char[L];
        for (int i=0;i<len;i++) {
            int lw = words[i].length();
            if (lw>L) {result.clear();return result;}
            int trying = count+lw+(i==left?0:1);
            if (trying<=L) {
                count=trying;
            } else {
                printLine(result,words,left,i-1,L-count,0);
                count=lw;
                left=i;
            }
        }
        printLine(result,words,left,len-1,0,L-count);
        return result;
        //Input your code here
    }
    private void printLine(ArrayList<String> result, String[] words, int left, int right, int leftSpace, int rightSpace) {
        int extraSpace=leftSpace/(int)Math.max(1,right-left);
        int residSpace=leftSpace%(int)Math.max(1,right-left);
        StringBuilder line = new StringBuilder();
        for (int j=left;j<=right;j++) {
            line.append(words[j]);
            if (j==right && j>left) {break;}
            line.append(spaces(extraSpace));
            if (j==right) {break;} // j==right==left
            line.append(' ');
            if (--residSpace>=0) {
                line.append(' ');
            }
        }
        line.append(spaces(rightSpace));
        result.add(new String(line));
    }
    private char[] spaces(int num) {
        char[] array = new char[num];
        Arrays.fill(array,' ');
        return array;
    }
    public static void main(String[] args) {
        Justify solution = new Justify();
        String[] input = new String[] {"I","love","the","world"};
//        String[] input = new String[] {"This", "is", "an", "example",  "of", "text","justification."};
        System.out.println(solution.fullJustify(input,14));
    }
}
