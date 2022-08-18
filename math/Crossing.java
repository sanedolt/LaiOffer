package com.laioffer.math;

public class Crossing {
    /*
    469. Self Crossing
    You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.
    Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.
     */
    public boolean isSelfCrossing(int[] x) { // 469
        if (x==null || x.length==0) {return false;}
        int len=x.length;
        int px1=0,py1=0,px2=0,py2=0,px3=0,py3=0,px4=0,py4=0;
        for (int i=0;i<len;i+=4) {
            int cx=px4,cy=py4;
            cy=cy+x[i];
            if (i>0 && cx<=px1 && cy>=py1) {return true;}
            px1=cx;py1=cy;
            if (i+1<len) {
                cx=cx-x[i+1];
                if (i>0 && cx<=px2 && cy<=py2) {return true;}
                px2=cx;py2=cy;
            }
            if (i+2<len) {
                cy=cy-x[i+2];
                if (i>0 && cx>=px3 && cy<=py3) {return true;}
                px3=cx;py3=cy;
            }
            if (i+3<len) {
                cx=cx+x[i+3];
                if (cx>=px4 && cy>=py4) {return true;}
                px4=cx;py4=cy;
            }
        }
        return false;
        // Write your solution here
    }
    /*
    608. Check Square
    Given the coordinates of four points in 2D space, return whether the four points form a square.
    The coordinate (x,y) of a point is represented by an integer array with two integers.
    Example:
    Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
    Output: True
    Note:
    All the input integers are in the range [-10000, 10000].
    A valid square has four equal sides with positive length and four equal angles (90-degree angles).
    Input points have no order.
     */
    public boolean checkSquare(int[] p1, int[] p2, int[] p3, int[] p4) { // 608
        double s12=Math.pow(p1[1]-p2[1],2)+Math.pow(p1[0]-p2[0],2);
        double s34=Math.pow(p3[1]-p4[1],2)+Math.pow(p3[0]-p4[0],2);
        if (s12!=s34) {return false;}
        double s13=Math.pow(p1[1]-p3[1],2)+Math.pow(p1[0]-p3[0],2);
        double s24=Math.pow(p2[1]-p4[1],2)+Math.pow(p2[0]-p4[0],2);
        if (s13!=s24) {return false;}
        double s14=Math.pow(p1[1]-p4[1],2)+Math.pow(p1[0]-p4[0],2);
        double s23=Math.pow(p2[1]-p3[1],2)+Math.pow(p2[0]-p3[0],2);
        if (s14!=s23) {return false;}
        return s12>0 && s13>0 && s23>0 && (s12==s13 || s12==s23 || s13==s23);
        // Write your solution here
    }
    public static void main(String[] args) {
        Crossing solution = new Crossing();
    }
}
