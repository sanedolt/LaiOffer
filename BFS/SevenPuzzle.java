package com.laioffer.BFS;
import java.util.*;

public class SevenPuzzle {
    static class Board{
        private int[] cards;
        public Board(int[] input) {
            cards = new int[8];
            for (int i=0;i<8;i++) {
                cards[i]=input[i];
            }
        }
        private void swap(int left, int right) {
            int temp = cards[left];
            cards[left]=cards[right];
            cards[right]=temp;
        }
        private int findZero() {
            for (int i=0;i<8;i++) {
                if (cards[i]==0) {return i;}
            }
            return -1;
        }
        @Override
        public int hashCode() {
            int code=0;
            for (int i=0;i<8;i++) {
                code=code*10+cards[i];
            }
            return code;
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Board)) {
                return false;
            } else {
                Board ref = (Board) o;
                for (int i=0;i<8;i++) {
                    if (cards[i]!=ref.cards[i]) {return false;}
                }
            }
            return true;
        }
        @Override
        public Board clone() {
            return new Board(cards);
        }
    }
    final static int[] DIRS = new int[]{-1,1,-4,4};
    public int numOfSteps(int[] values) {
        if (values==null || values.length==0) {return -1;}
        Map<Board,Integer> state = new HashMap<>();
        Queue<Board> moving = new ArrayDeque<>();
        Board source = new Board(values);
        Board target = new Board(new int[]{0,1,2,3,4,5,6,7});
        state.put(target,0);
        moving.offer(target);
        while (!moving.isEmpty()) {
            Board cur = moving.poll();
            System.out.println(Arrays.toString(cur.cards));
            int nc = state.get(cur);
            System.out.println(nc);
            if (cur.equals(source)) {return nc;}
            int c0 = cur.findZero();
            for (int i=0;i<4;i++) {
                int c0n = c0+DIRS[i];
                if (c0n<0 || c0n>7) {continue;}
                if ((c0==3 && c0n==4) || (c0==4 && c0n==3)) {continue;}
                cur.swap(c0,c0n);
                System.out.println(Arrays.toString(cur.cards));
                if (state.get(cur)==null) {
                    Board newBoard = cur.clone();
                    state.put(newBoard,nc+1);
                    moving.offer(newBoard);
                }
                cur.swap(c0,c0n);
            }
        }
        return -1;
        // Write your solution here
    }

    public static void main(String[] args) {
        SevenPuzzle solution = new SevenPuzzle();
        //System.out.println(solution.numOfSteps(new int[]{6,7,3,5,4,2,1,0}));
        System.out.println(solution.numOfSteps(new int[]{3,6,0,7,1,2,4,5}));
    }
}
