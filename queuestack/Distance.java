package com.laioffer.queuestack;

public class Distance {
    /*
    283. String Distance
    Given an array of strings, and two different string s and t. We need to return the smallest indices difference between the two given strings.
    Return -1 if we can not find s or t in the array.
    Assumptions:
    The given array is not null, none of the strings in the array is null.
    s and t are different and they are not null.
    Examples:
    array =  {"this", "is", "a", "is", "fox", "happy"}, the distance of "fox" and "is" is 1.
     */
    public int distance(String[] array, String source, String target) { // 283
        if (array==null) {return -1;}
        if (source==null || target==null) {return -1;}
        int len=array.length;
        int indexs=-1,indext=-1,max=len;
        for (int i=0;i<len;i++) {
            if (array[i].equals(source)) {indexs=i;}
            if (array[i].equals(target)) {indext=i;}
            if (indexs>-1 && indext>-1 && Math.abs(indext-indexs)<max) {
                max=Math.abs(indext-indexs);
            }
        }
        return max<len?max:-1;
        // Write your solution here
    }
    public static void main(String[] args) {
        Distance solution = new Distance();
    }
}
