package com.laioffer.OOD.TicTacToe;

public class Player {
    private final String name;
    Integer sign;
    public Player(String name, Integer sign) {
        this.name = name;
        this.sign = sign;
    }
    public boolean turn (Board board, int r, int c) {
        if (!board.isEmpty(r,c)) {return false;}
        board.setPiece(r,c,sign);
    }
}
