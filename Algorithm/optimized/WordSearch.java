package com.laioffer.Algorithm.optimized;

public class WordSearch {
    /*
    154. Word Search
    Given a 2D board and a word, find if the word exists in the grid.The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
     */
    final static int[][] DIRS = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public boolean isWord(char[][] board, String word) { // 154
        if (board==null || board.length==0 || board[0].length==0) {return false;}
        if (word==null || word.length()==0) {return false;}
        int rows=board.length,cols=board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        char[] array = word.toCharArray();
        char ch = word.charAt(0);
        boolean found = false;
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (board[i][j]!=ch) {continue;}
                visited[i][j]=true;
                found = found || helper(board,array,1,i,j,visited);
                visited[i][j]=false;
            }
        }
        return found;
        // Write your solution here
    }
    private boolean helper (char[][] board, char[] array, int index, int row, int col, boolean[][] visited) {
        int len = array.length;
        if (index==len) {return true;}
        int rows=board.length,cols=board[0].length;
        char cur = array[index];
        boolean found = false;
        for (int[] dir : DIRS) {
            int neiRow = row+dir[0];
            int neiCol = col+dir[1];
            if (neiRow<0 || neiRow>=rows || neiCol<0 || neiCol>=cols) {continue;}
            if (visited[neiRow][neiCol] || board[neiRow][neiCol]!=cur) {continue;}
            visited[neiRow][neiCol]=true;
            found = found || helper(board,array,index+1,neiRow,neiCol,visited);
            visited[neiRow][neiCol]=false;
        }
        return found;
    }
    public static void main(String[] args) {
        WordSearch solution = new WordSearch();
    }
}
