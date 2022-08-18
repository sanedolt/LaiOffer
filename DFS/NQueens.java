package com.laioffer.DFS;
import java.util.*;

public class NQueens {
    /*
    233. N Queens
    Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.
    Assumptions
    N > 0
    Return
    A list of ways of putting the N Queens
    Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)
     */
    public List<List<Integer>> nqueens (int n) { // 233
        List<List<Integer>> result = new ArrayList<>();
        if (n<1) {return result;}
        List<Integer> loc = new ArrayList<>(); // to save queen locations
        boolean[] column = new boolean[n];
        boolean[] diag1 = new boolean[n*2-1];
        boolean[] diag2 = new boolean[n*2-1];
        helper(loc,result,column,diag1,diag2);
        return result;
    }
    private void helper(List<Integer> loc, List<List<Integer>> result, boolean[] column, boolean[] diag1, boolean[] diag2) {
        int n = column.length;
        int row = loc.size();
        if (n==row) {
            result.add(new ArrayList(loc));
            return;
        }
        for (int col=0;col<n;col++) {
            if (isValid(n,row,col,column,diag1,diag2)) {
                loc.add(col);
                mark(n,row,col,column,diag1,diag2); // mark new occupied positions
                helper(loc,result,column,diag1,diag2); // search for next row
                unmark(n,row,col,column,diag1,diag2); // unmark occupied positions from previous try
                loc.remove(loc.size()-1); // delete the previous try on current row
            } // end if
        } // end for
    }
    private boolean isValid(int n, int row, int col, boolean[] column, boolean[] diag1, boolean[] diag2) {
        return !column[col] && !diag1[row+col] && !diag2[row-col+n-1];
    }
    private void mark(int n, int row, int col, boolean[] column, boolean[] diag1, boolean[] diag2) {
        column[col]=diag1[row+col]=diag2[row-col+n-1]=true;
    }
    private void unmark(int n, int row, int col, boolean[] column, boolean[] diag1, boolean[] diag2) {
        column[col]=diag1[row+col]=diag2[row-col+n-1]=false;
    }
    /*
    234. N-Queen II
    Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.
    Follow up for N-Queens problem. Now, instead outputting board configurations, return the total number of distinct solutions.
    */
    public int totalNQueens(int n) { // 234
        if (n<=0) {return 0;}
        boolean[] usedColumn = new boolean[n];
        boolean[] usedDiagonal = new boolean[n*2-1];
        boolean[] usedRevDiagonal = new boolean[n*2-1] ;
        List<Integer> loc = new ArrayList<>();
        int[] result = new int[1];
        findNext (loc,usedColumn,usedDiagonal,usedRevDiagonal,result);
        return result[0];
        // Write your solution here
    }
    private void findNext (List<Integer> loc, boolean[] usedColumn, boolean[] usedDiagonal, boolean[] usedRevDiagonal, int[] result) {
        int n = usedColumn.length;
        int row = loc.size();
        if (row==n) {
            result[0]++;
            return;
        }
        for (int col=0;col<n;col++) {
            if (isValid(n,row,col,usedColumn,usedDiagonal,usedRevDiagonal)) {
                loc.add(col);
                mark(n,row,col,usedColumn,usedDiagonal,usedRevDiagonal);
                findNext(loc,usedColumn,usedDiagonal,usedRevDiagonal,result);
                unmark(n,row,col,usedColumn,usedDiagonal,usedRevDiagonal);
                loc.remove(loc.size()-1);
            }
        }
    }
    public static void main(String[] args) {
        NQueens solution = new NQueens();
    }
}
