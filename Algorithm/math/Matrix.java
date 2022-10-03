package com.laioffer.Algorithm.math;
import java.util.*;

public class Matrix {
    /*
    423. Sparse Matrix Multiplication
    Given two sparse matrices A and B, return the result of AB.
    You may assume that A's column number is equal to B's row number.
     */
    public int[][] multiply(int[][] A, int[][] B) { // 423
        if (A==null || A.length==0 || A[0].length==0) {return null;}
        if (B==null || B.length==0 || B[0].length==0) {return null;}
        int rowa=A.length,cola=A[0].length;
        int rowb=B.length,colb=B[0].length;
        if (rowb!=cola) {return null;}
        // make it O(n^3) instead of O(n^4)
        Map<Integer,List<Integer>> sparseA = new HashMap<>();
        Map<Integer,List<Integer>> sparseB = new HashMap<>();
        for (int i=0;i<rowa;i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j=0;j<cola;j++) {
                if (A[i][j]!=0) {temp.add(j);}
            }
            sparseA.put(i,temp);
        }
        for (int i=0;i<colb;i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j=0;j<rowb;j++) {
                if (B[j][i]!=0) {temp.add(j);}
            }
            sparseB.put(i,temp);
        }
        int[][] result = new int[rowa][colb];
        for (int i=0;i<rowa;i++) {
            List<Integer> pa = sparseA.get(i);
            if (pa==null) {continue;}
            for (int j=0;j<colb;j++) {
                List<Integer> pb = sparseB.get(j);
                if (pb==null) {continue;}
                int ia=0,ib=0;
                while (ia<pa.size() && ib<pb.size()) {
                    if (pa.get(ia)==pb.get(ib)) {
                        result[i][j]+=A[i][pa.get(ia)]*B[pb.get(ib)][j];
                        ia++;ib++;
                    } else if (pa.get(ia)<pb.get(ib)) {
                        ia++;
                    } else {
                        ib++;
                    }
                }
            }
        }
        return result;
        // Write your solution here
    }
    /*
    573. Matrix Reshaping
    Given a matrix represented by a 2D array and two postive integers representing height and width of the matrix after reshaping. Read the numbers in the given matrix and fill them into the reshaped matrix by row-traversing order.
    Row-traversing order of a matrix is defined as: read the element in the matrix line by line from top to bottom, and from left to right in each line.
    For example,  for the following matrix:
    [[1,2,3],
     [4,5,6],
     [7,8,9],
     [10,11,12]]
    The row-traversing order of this matrix is: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12.
    If the origin matrix can not be reshaped with given height and width, return the origin matrix.
    Example 1:
    Input: matrix = [[1,2], [4,5]], height = 1, width = 4
    Output:[[1,2,4,5]]
    Example 2:
    Input: matrix = [[1,2], [4,5]], height = 4, width = 2
    Output: reshape =[[1,2], [4,5]]
    Explanation: There is no way to reshape a 2 * 2 matrix into 4 * 2, so return the original matrix.
    Assumptions:
    The height and width of the given matrix is in range [1, 100].
    The given number of height and width are positive.
     */
    public int[][] matrixReshape(int[][] matrix, int height, int width) { // 573
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return matrix;}
        int rows=matrix.length,cols=matrix[0].length;
        if (height*width!=rows*cols) {return matrix;}
        int[][] result = new int[height][width];
        for (int i=0;i<rows*cols;i++) {
            int row=i/cols;
            int col=i%cols;
            int newRow=i/width;
            int newCol=i%width;
            result[newRow][newCol]=matrix[row][col];
        }
        return result;
        // Write your solution here
    }
    /*
    578. Matrix Range Incrementation
    Suppose there is an m * n matrix in which all elements equals to 0 originally. Given a list operations, each operation is a pair of two numbers [a, b]. In each operation, increment matrix[i][j] by 1 where 0 <= i < a and 0 <= j < b. Return the number of maximum elements in the matrix after all operations are completed.
    Example:
    Input: m = 3, n = 3, ops = [[2,2],[2,3],[3,2]]
    Output: 4
    Explanation:
    Original matrix:
    [[0,0,0],
     [0,0,0],
     [0,0,0]]
    After applying first operation [2,2]:
    [[1,1,0],
     [1,1,0],
     [0,0,0]]
    After applying first operation [2,3]:
    [[2,2,1],
     [2,2,1],
     [0,0,0]]
    After applying first operation [3,2]:
    [[3,3,1],
     [3,3,1],
     [1,1,0]]
     The maximum number in the matrix is 3 and there are 4 of them. Hence the ouput is 4.
    Assumptions:
    For any operation [a, b], 1 <= a <= m and 1 <= b <= n.
     */
    public int numOfMaxValue(int m, int n, int[][] ops) { // 578
        if (ops==null) {return 0;}
        if (ops.length==0 || ops[0].length==0) {return m*n;}
        int minr=m+1,minc=n+1;
        for (int[] op : ops) {
            minr=Math.min(minr,op[0]);
            minc=Math.min(minc,op[1]);
        }
        return minr*minc;
        // Write your solution here
    }
    public static void main(String[] args) {
        Matrix solution = new Matrix();
    }
}
