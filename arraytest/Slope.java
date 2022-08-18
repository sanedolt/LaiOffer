package com.laioffer.arraytest;
import java.util.*;

class Point {
  public int x;
  public int y;
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class Slope {
    private void print(Point[] points) {
        for (int i=0;i<points.length;i++) {
            System.out.println(points[i].x+" "+points[i].y);
        }
    }
    static class MyComparator implements Comparator<Point> {
        @Override
        public int compare (Point p1, Point p2) {
            return p1.x!=p2.x?p1.x-p2.x:p2.y-p1.y;
        }
    }
    /*
    217. Largest Set Of Points With Positive Slope
    Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can form a set such that any pair of points in the set can form a line with positive slope. Return the size of such a maximal set.
    Assumptions
    The given array is not null
    Note: if there does not even exist 2 points can form a line with positive slope, should return 0.
    Examples
    <0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are {<0, 0>, <1, 1>, <2, 3>}, the size is 3.
     */
    public int largest(Point[] points) { // 217
        if (points==null || points.length==0) {return 0;}
        int len=points.length;
        Arrays.sort(points,new MyComparator());
        int[] dp = new int[len];
        int max=0;
        for (int i=0;i<len;i++) {
            for (int j=0;j<i;j++) {
                if (points[j].y<points[i].y) {
                    dp[i]=Math.max(dp[i],dp[j]);
                }
            }
            max=Math.max(max,++dp[i]); // maximum from previous subset that can form positive slope plus the current point
        }
        return max==1?0:max;
        // Write your solution here.
    }
    /*
    216. Most Points On A Line
    Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can be crossed by a single line in 2D space.
    Assumptions
    The given array is not null and it has at least 2 points
    Examples
    <0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is 3(<0, 0>, <1, 1>, <3, 3> are on the same line)
     */
    public int most(Point[] points) { // 216
        if (points==null || points.length==0) {return 0;}
        int len=points.length,max=0;
        for (int i=0;i<len;i++) {
            double ix = points[i].x;
            double iy = points[i].y;
            int same=1,sameX=0,most=0;
            Map<Double,Integer> counter = new HashMap<>();
            for (int j=0;j<len;j++) {
                if (i==j) {continue;}
                double jx = points[j].x;
                double jy = points[j].y;
                if (jx==ix && jy==iy) {
                    same++;
                } else if (jx==ix) {
                    sameX++;
                } else {
                    double slope = (jy-iy)/(jx-ix);
                    Integer cn = counter.get(slope);
                    counter.put(slope,cn==null?1:cn+1);
                    most=Math.max(most,counter.get(slope));
                } // end if
            } // end for j
            most=Math.max(most,sameX)+same;
            max=Math.max(max,most);
        } // end for i
        return max;
        // Write your solution here.
    }
    public static void main(String[] args) {
        Slope solution = new Slope();
        Point[] input = new Point[9];
        input[0]=new Point(0,1);
        input[1]=new Point(1,3);
        input[2]=new Point(2,3);
        input[3]=new Point(3,3);
        input[4]=new Point(3,1);
        input[5]=new Point(4,2);
        input[6]=new Point(5,2);
        input[7]=new Point(5,5);
        input[8]=new Point(6,1);
        System.out.println(solution.largest(input));
    }
}
