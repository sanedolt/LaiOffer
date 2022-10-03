package com.laioffer.Algorithm.DFS;
import java.util.*;

public class Maze { // 218
    /*
    218. Generate Random Maze
    Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell. For each pair of cells on the corridor, there must exist one and only one path between them. (Randomly means that the solution is generated randomly, and whenever the program is executed, the solution can be different.). The wall is denoted by 1 in the matrix and corridor is denoted by 0.
    Assumptions
    N = 2K + 1 and K >= 0
    the top left corner must be corridor
    there should be as many corridor cells as possible
    for each pair of cells on the corridor, there must exist one and only one path between them
     */
    public int[][] maze(int n) {
        if (n<1 || n%2==0) {return null;}
        int[][] maze = new int[n][n];
        for (int i=0;i<n;i++) {
            Arrays.fill(maze[i],1);
        }
        maze[0][0]=0;
        generate(maze,0,0);
        return maze;
        // Write your solution here.
    }
    private void generate(int[][] maze, int x, int y) {
        Dir[] dirs = Dir.values();
        shuffle(dirs);
        for (Dir dir : dirs) {
            int nextX = dir.moveX(x,2);
            int nextY = dir.moveY(y,2);
            if (isValidWall(maze,nextX,nextY)) {
                maze[nextX][nextY]=maze[(x+nextX)/2][(y+nextY)/2]=0;
                generate(maze,nextX,nextY);
            }
        }
    }
    private void shuffle(Dir[] dirs) {
        for (int i=0;i<dirs.length;i++) {
            int index = (int)(Math.random()*(dirs.length-i));
            Dir temp = dirs[i];
            dirs[i]=dirs[i+index];
            dirs[i+index]=temp;
        }
    }
    private boolean isValidWall(int[][]maze, int x, int y) {
        return x>=0 && x<maze.length && y>=0 && y<maze[0].length && maze[x][y]==1;
    }
    enum Dir{
        NORTH(-1,0),SOUTH(1,0),WEST(0,-1),EAST(0,1);
        int dx;
        int dy;
        Dir (int dx, int dy) {
            this.dx=dx;
            this.dy=dy;
        }
        public int moveX(int x, int times) {
            return x+times*dx;
        }
        public int moveY(int y, int times) {
            return y+times*dy;
        }
    }
    /*
    LC 499
     */
    enum DIRS {
        u (-1,0),
        d (1,0),
        l (0,-1),
        r (0,1);
        private int di,dj;
        DIRS (int di, int dj) {
            this.di=di;
            this.dj=dj;
        }
    }
    int rows,cols;
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        String result = "impossible";
        if (maze==null || ball==null || hole==null) {return result;}
        if (maze[hole[0]][hole[1]]==1) {return result;}
        rows=maze.length;cols=maze[0].length;
        maze[ball[0]][ball[1]]=2;
        String[][] inst = new String[rows][cols];
        inst[ball[0]][ball[1]]="";
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{ball[0],ball[1],0});
        DIRS[] dirs = DIRS.values();
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[2]>maze[cur[0]][cur[1]]-2) {continue;}
            for (DIRS dir : dirs) {
                int neii=cur[0],neij=cur[1],count=cur[2];
                do {
                    neii+=dir.di;neij+=dir.dj;count++;
                } while (valid(neii,neij) && maze[neii][neij]!=1 && !enter(neii,neij,hole));
                if (!enter(neii,neij,hole)) {neii-=dir.di;neij-=dir.dj;count--;}
                if (maze[neii][neij]>1 && count>maze[neii][neij]-2) {continue;}
                String tmp = inst[cur[0]][cur[1]]+dir.name();
                if (maze[neii][neij]>1 && count==maze[neii][neij]-2 && lexpri(inst[neii][neij],tmp)) {continue;}
                maze[neii][neij]=count+2;
                inst[neii][neij]=tmp;
                if (!enter(neii,neij,hole)) {
                    q.offer(new int[]{neii,neij,count});
                }
            }
        }
        return maze[hole[0]][hole[1]]==0?"impossible":inst[hole[0]][hole[1]];
    }
    private boolean lexpri(String a, String b) {
        int la=a.length(),lb=b.length();
        int ia=0,ib=0;
        while (ia<la && ib<lb) {
            if (a.charAt(ia)<b.charAt(ib)) {return true;}
            if (a.charAt(ia)>b.charAt(ib)) {return false;}
            ia++;ib++;
        }
        return la<=lb;
    }
    private boolean enter(int i, int j, int[] hole) {
        return i==hole[0] && j==hole[1];
    }
    private boolean valid(int i, int j) {
        if (i<0 || i>=rows || j<0 || j>=cols) {return false;}
        return true;
    }
    public static void main(String[] args) {
        Maze solution = new Maze();
        //System.out.println(Arrays.deepToString(solution.maze(5)));
        int[][] maze = new int[][]{{0,0,0,0,0,0,0},{0,0,1,0,0,1,0},{0,0,0,0,1,0,0},{0,0,0,0,0,0,1}};
        int[] ball = new int[]{0,4};
        int[] hole = new int[]{3,5};
        System.out.println(solution.findShortestWay(maze,ball,hole));
    }
}
