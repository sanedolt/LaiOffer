package com.laioffer.math;

import java.util.List;

public class Area {
    /*
    445. Rectangle Area
    Find the total area covered by two rectilinear rectangles in a 2D plane.
    Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) { // 445
        int square1=(C-A)*(D-B);
        int square2=(G-E)*(H-F);
        if (H<=B || F>=D || G<=A || E>=C) {return square1+square2;}
        int over=0,count=0;
        if (A<=E && G<=C) {count+=2;}
        if (E<=A && C<=G) {count+=3;}
        if (count==5) {count-=2;}
        if (F<=B && D<=H) {count+=4;}
        if (B<=F && H<=D) {count+=5;}
        if (count>8) {count-=4;}
        switch (count) {
            case 7: // enclosed
                over=Math.min(square1,square2);
                break;
            case 8: // cross
                over=(H-F)*(C-A);
                break;
            case 6: // cross
                over=(D-B)*(G-E);
                break;
            case 2:
                over=(G-E)*Math.min(Math.abs(D-F),Math.abs(B-H));
                break;
            case 3:
                over=(C-A)*Math.min(Math.abs(D-F),Math.abs(B-H));
                break;
            case 4:
                over=(D-B)*Math.min(Math.abs(G-A),Math.abs(C-E));
                break;
            case 5:
                over=(H-F)*Math.min(Math.abs(G-A),Math.abs(C-E));
                break;
            case 0:
                over = Math.min(Math.abs(G-A),Math.abs(C-E))*Math.min(Math.abs(B-H),Math.abs(D-F));
                break;
            default:
        }
        return square1+square2-over;
        // Write your solution here
    }
    /*
    635. Sum of Squares
    Problem: Give an array list of integer, calculate the sum of squares of all its elements.
    Note: return 0 if the list is null or empty.
    Example:
    list = {1,2,3} → returns 14 (14=1*1+2*2+3*3)
     */
    public int sumOfSquare(List<Integer> list) { // 635
        int sum=0;
        if (list==null) {return sum;}
        for (int i : list) {
            sum+=i*i;
        }
        return sum;
        // Write your solution here
    }
    public static void main(String[] args) {
        Area solution = new Area();
    }
}
