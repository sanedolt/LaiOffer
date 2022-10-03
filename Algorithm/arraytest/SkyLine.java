package com.laioffer.Algorithm.arraytest;
import java.util.*;

 class Building {
   public int start;
   public int end;
   public int height;
   public Building(int start, int end, int height) {
     this.start = start;
     this.end = end;
     this.height = height;
   }
 }
public class SkyLine {
     /*
     269. Total Area Of Skyline
     Given n houses on the ground with each house represented by a rectangle. The i-th rectangle is represented as [start_i, end_i, height_i], where  0 <= i < n. The rectangles may overlap with each other.  How can we calculate the total area that these rectangles cover.
      */
     public int totalArea(List<Building> buildings) { // 269
         if (buildings==null || buildings.size()==0) {return 0;}
         PriorityQueue<Building> pq = new PriorityQueue<>(new Comparator<Building>(){
             public int compare (Building a, Building b) {
                 return ((Integer) b.height).compareTo((Integer) a.height);
             }
         });
         int size = buildings.size();
         int[][] enter = new int[size*2][];
         for (int i=0;i<size;i++) {
             Building cur = buildings.get(i);
             enter[i*2+0]=new int[]{cur.start,cur.height,i};
             enter[i*2+1]=new int[]{cur.end,-cur.height,i};
         }
         quickSort(enter,0,size*2-1);
         int area=0,prevHeight=0;
         for (int i=0;i<size*2;i++) {
             prevHeight=pq.isEmpty()?0:pq.peek().height;
             if (enter[i][1]>=0) { // new building
                 pq.offer(buildings.get(enter[i][2]));
             } else { // leaving building
                 pq.remove(buildings.get(enter[i][2]));
             }
             if (i>0) {area+=(enter[i][0]-enter[i-1][0])*prevHeight;}
         }
         return area;
         // Write your solution here.
     }
    private void quickSort(int[][] array, int left, int right) {
        if (left>=right) {return;}
        int pivotIndex = partition(array,left,right);
        quickSort(array,left,pivotIndex-1);
        quickSort(array,pivotIndex+1,right);
    }
    private int partition(int[][] array, int left, int right) {
        int pivotIndex = (int) (Math.random()*(right-left+1))+left;
        swap(array,pivotIndex,right);
        int leftBound=left,rightBound=right-1;
        while (leftBound<=rightBound) {
            if (array[leftBound][0]<array[right][0]) {
                leftBound++;
            } else if (array[rightBound][0]>=array[right][0]) {
                rightBound--;
            } else {
                swap(array,leftBound++,rightBound--);
            }
        }
        swap(array,leftBound,right);
        return leftBound;
    }
    private void swap(int[][] array, int left, int right) {
        int[] tmp=array[left];
        array[left]=array[right];
        array[right]=tmp;
    }
    /*
    441. The Skyline Problem
    A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skylineformed by these buildings collectively (Figure B).
    BuildingsSkyline Contour
    The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
    For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
    The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
    For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
    Notes:
    The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    The input list is already sorted in ascending order by the left x position Li.
    The output list must be sorted by the x position.
    There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
     */
    public int[][] getSkyline(int[][] buildings) { // 441
        if (buildings==null || buildings.length==0 || buildings[0].length==0) {return buildings;}
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare (int[] a, int[] b) {
                return Integer.compare(b[2],a[2]);
            }
        });
        int len=buildings.length;
        int[][] enter = new int[len*2][];
        for (int i=0;i<len;i++) {
            enter[i*2]=new int[]{buildings[i][0],i+1};
            enter[i*2+1]=new int[]{buildings[i][1],-i-1};
        }
        Arrays.sort(enter,new Comparator<int[]>(){
            public int compare (int[] a, int[] b) {
                return Integer.compare(a[0],b[0]);
            }
        });
        int index=0,currHeight=Integer.MIN_VALUE,prevHeight=0,sign=0;
        for (int i=0;i<len*2;i++) {
            if (enter[i][1]>0) { // add building
                pq.offer(buildings[enter[i][1]-1]);
                sign=1;
            } else { // remove building
                pq.remove(buildings[-enter[i][1]-1]);
                sign=-1;
            }
            currHeight = Math.max(currHeight,pq.isEmpty()?0:pq.peek()[2]*sign);
            if (i<len*2-1 && enter[i][0]==enter[i+1][0]) { // still need to check current time
                if (currHeight==prevHeight) {currHeight=Integer.MIN_VALUE;} // no additional information, exclude for either increase or decrease height
            } else { // can check to print
                currHeight=Math.abs(currHeight);
                if (currHeight!=prevHeight) { // if there is something to print
                    enter[index][0]=enter[i][0];
                    enter[index++][1]=currHeight;
                }
                prevHeight=currHeight;
                currHeight=Integer.MIN_VALUE;
            }
        }
        return Arrays.copyOfRange(enter,0,index);
        // Write your solution here
    }
    public static void main(String[] args) {
        SkyLine solution = new SkyLine();
//        Building b1 = new Building(1,5,1);
//        Building b2 = new Building(2,4,2);
//        Building b3 = new Building(3,6,3);
//        List<Building> buildings = new ArrayList<>();
//        buildings.add(b1);
//        buildings.add(b2);
//        buildings.add(b3);
//        System.out.println(solution.totalArea(buildings));
        int[][] buildings = new int[][]{{5,158,9594},{11,187,8406},{12,203,9537},{14,91,5137},{20,280,1927},{21,143,4972},{24,134,893},{27,286,8242},{28,232,702},{29,260,5729},{32,263,1940},{36,43,6749},{37,106,6025},{41,45,4623},{52,100,9959},{53,240,6894},{57,245,3088},{60,90,3541},{62,147,9040},{62,191,2387},{62,220,3325},{64,273,1882},{68,113,8918},{77,182,1023},{79,220,55},{88,247,4573},{89,247,3798},{97,219,5345},{98,126,4262},
                {98,179,3172},{101,110,3843},{106,171,1164},{107,171,3747},{123,261,7309},{127,266,4473},{129,166,3940},{139,158,1436},{141,240,5569},{147,242,3634},{148,165,8072},{148,231,6076},{150,180,883},{150,260,1711},{157,258,571},{160,167,9123},{168,236,2452},{173,264,5910},{178,245,5794},{186,255,6863},{192,246,1077},{211,245,2428},{211,281,7123},{224,281,2172},{236,240,6561},{255,299,8622},{274,299,1220},{286,294,9151}};
        System.out.println(Arrays.deepToString(solution.getSkyline(buildings)));
    }
}
