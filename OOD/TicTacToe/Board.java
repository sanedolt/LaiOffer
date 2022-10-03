package com.laioffer.OOD.TicTacToe;

public class Board {
    private static final int DEFAULT_BOARD_SIZE = 3;
    private final int size;
    private int empty;
    private int[][] board;

    public Board(int n) {
        size = n;
        board = new int[n][n];
        empty = n*n;
    }

    public boolean isFull(){
        return empty==0;
    }

    public boolean isEmpty(int r, int c) {
        if (!isValid(r,c)) {return false;}
        return board[r][c]==0;
    }

    public boolean setPiece(int r, int c, int sign) {
        if (!isValid(r,c)) {return false;}
        board[r][c]=sign;
        empty--;
        return true;
    }
    private boolean isValid(int r, int c) {
        return r>=0 && r<size && c>=0 && c<size;
    }
    public boolean win(int r, int c) {
        int sign = board[r][c];
        boolean row=true, col=true, diag1=(r==c), diag2=(r+c==size-1);
        for (int i=0;i<size;i++) {
            if (board[r][i]!=sign) {row=false;break;}
        }
        for (int i=0;i<size;i++) {
            if (board[i][c]!=sign) {col=false;break;}
        }
        for (int i=0;i<size;i++) {
            if (board[i][i]!=sign) {diag1=false;break;}
        }
        for (int i=0;i<size;i++) {
            if (board[i][r+c-i]!=sign) {diag2=false;break;}
        }
        return row || col || diag1 || diag2;
    }
}
