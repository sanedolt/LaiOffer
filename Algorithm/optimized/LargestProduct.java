package com.laioffer.Algorithm.optimized;
import java.util.*;

public class LargestProduct {
    /*
    98. Largest Subarray Product
    Given an unsorted array of doubles, find the subarray that has the greatest product. Return the product.
    Assumptions
    The given array is not null and has length of at least 1.
     */
    public double largestProduct(double[] array) { // 98
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        if (len==1) {return array[0];}
        double min, max;
        double res = min = max = array[0];
        for (int i = 1; i < len; i++) {
            double minPro = array[i] * min;
            double maxPro = array[i] * max;
            min = Math.min(Math.min(minPro, maxPro), array[i]);
            max = Math.max(Math.max(minPro, maxPro), array[i]);
            res = Math.max(res, max);
        }
        return res;
        // Write your solution here
    }
    /*
    191. Largest Product Of Length
    Given a dictionary containing many words, find the largest product of two words’ lengths, such that the two words do not share any common characters.
    Assumptions
    The words only contains characters of 'a' to 'z'
    The dictionary is not null and does not contains null string, and has at least two strings
    If there is no such pair of words, just return 0
    Examples
    dictionary = [“abcde”, “abcd”, “ade”, “xy”], the largest product is 5 * 2 = 10 (by choosing “abcde” and “xy”)
     */
    public int largestProduct(String[] dict) { // 191
        int len=dict.length;
        if (len<2) {return 0;}
        HashMap<String,Integer> bitMasks=getBitMasks(dict);
        Arrays.sort(dict, new Comparator<String>(){
            @Override
            public int compare (String s0, String s1) {
                return ((Integer)s1.length()).compareTo((Integer)s0.length());
            }
        });
        int max=0;
        for (int i=1;i<len;i++) {
            for (int j=0;j<i;j++) {
                int prod=dict[i].length()*dict[j].length();
                if (prod<=max) {break;}
                if ((bitMasks.get(dict[i])&bitMasks.get(dict[j]))==0) { // max>prod
                    max=prod;
                }
            }
        }
        return max;
        // Write your solution here
    }
    private HashMap<String,Integer> getBitMasks(String[] dict) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for (String str : dict) {
            int bitMask=0;
            for (int i=0;i<str.length();i++) {
                bitMask |= 1<<(str.charAt(i)-'a');
            }
            map.put(str,bitMask);
        }
        return map;
    }
    public static void main(String[] args) {
        LargestProduct solution = new LargestProduct();
        double[] input = new double[]{-0.4,0,-0.3,0.2,0,-0.1};
        System.out.println(solution.largestProduct(input));
    }
}
