package com.laioffer.OOD.TicTacToe;

import java.util.Random;

public class Game {
    private Board board;
    private int size;
    private Player player1;
    private Player player2;
    private Random random;
    private GameStatus status;

    public Game(int size, String p1, String p2) {
        this.board = new Board(size);
        this.player1 = new Player(p1,1);
        this.player2 = new Player(p2,2);
        this.status = GameStatus.Draw;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void play() {
        int step = 0;
        while (!board.isFull()) {
            step++;
            boolean proceed = false;
            int r = -1, c = -1;
            if (step % 2 == 1) {
                while (!proceed) {
                    r = random.nextInt(size);
                    c = random.nextInt(size);
                    proceed = player1.turn(board, r, c);
                }
                if (board.win(r,c)) {
                    setStatus(GameStatus.Player1_win);
                    break;
                }
            } else {
                while (!proceed) {
                    r = random.nextInt(size);
                    c = random.nextInt(size);
                    proceed = player2.turn(board, r, c);
                }
                if (board.win(r,c)) {
                    setStatus(GameStatus.Player2_win);
                    break;
                }
            }
        }
        System.out.println(getStatus().toString());
    }
}
