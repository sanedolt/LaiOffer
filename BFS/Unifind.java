package com.laioffer.BFS;
import java.util.*;

public class Unifind {
    /*
    219. Disjoint White Objects
    In a 2D black image there are some disjoint white objects with arbitrary shapes, find the number of disjoint white objects in an efficient way.
    By disjoint, it means there is no white pixels that can connect the two objects, there are four directions to move to a neighbor pixel (left, right, up, down).
    Black is represented by 1’s and white is represented by 0’s.
    Assumptions
    The given image is represented by a integer matrix and all the values in the matrix are 0 or 1
    The given matrix is not null
     */
    public int whiteObjects(int[][] matrix) { // 219
        if (matrix==null || matrix.length==0) {return 0;}
        int rows=matrix.length,cols=matrix[0].length;
        Map<Integer,Integer> roots = new HashMap<>();
        //int[][] dirs = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
        int[][] dirs = new int[][] {{-1,0},{0,-1}};
        int num=0;
        for (int i=0;i<rows*cols;i++) {
            int[] curLoc = convert1Dto2D(i,cols);
            if (matrix[curLoc[0]][curLoc[1]]==1) {continue;}
            num++;
            roots.put(i,i);
            for (int[] dir : dirs) {
                int neiRow=curLoc[0]+dir[0];
                int neiCol=curLoc[1]+dir[1];
                if (!isValid(neiRow,neiCol,rows,cols,matrix)) {continue;}
                int neiIndex = convert2Dto1D(neiRow,neiCol,cols);
                int neiRoot = findRoot(neiIndex,roots);
                if (neiRoot!=i) {
                    num--;
                    roots.put(neiRoot,i);
                }
            } // end for dir
        } // end for i
        return num;
        // Write your solution here
    }
    private int findRoot(int index, Map<Integer,Integer> roots) {
        while (roots.get(index)!=index) {
            index=roots.get(index);
        }
        return index;
    }
    private boolean isValid (int row, int col, int rows, int cols, int[][] matrix) {
        return row>=0 && row<rows && col>=0 && col<cols && matrix[row][col]==0;
    }
    private int[] convert1Dto2D (int index, int cols) {
        return new int[] {index/cols,index%cols};
    }
    private int convert2Dto1D (int row, int col, int cols) {
        return row*cols+col;
    }
    /*
    525. Number of Islands
    Given a 2d grid map of 1s (land) and 0s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
     */
    public int numIslands(char[][] grid) { // 525
        if (grid==null || grid.length==0) {return 0;}
        int rows=grid.length,cols=grid[0].length;
        int total=rows*cols;
        Map<Integer,Integer> roots = new HashMap<>(); // index and root
        //final int[][] dirs = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
        final int[][] dirs = new int[][] {{-1,0},{0,-1}};
        int num=0;
        for (int i=0;i<total;i++) {
            int[] origLoc = convert1Dto2D(i,cols);
            if (grid[origLoc[0]][origLoc[1]]=='0') {continue;}
            if (roots.containsKey(i)) {continue;}
            num++;
            roots.put(i,i);
            for (int[] dir : dirs) {
                int testRow=origLoc[0]+dir[0];
                int testCol=origLoc[1]+dir[1];
                if (!isValid525(testRow,testCol,rows,cols) || grid[testRow][testCol]=='0') {continue;}
                int testIndex = convert2Dto1D(testRow,testCol,cols);
                int testRoot = findRoot(testIndex,roots);
                if (testRoot!=i) {
                    num--;
                    roots.put(testRoot,i); // so that i becomes testroot's root
                }
            } // end for dir
        } // end for i
        return num;
        // Write your solution here
    }
//    private int findRoot(int index, Map<Integer,Integer> roots) {
//        while (roots.get(index)!=index) {
//            index=roots.get(index);
//        } // find the grid whose root is itself
//        return index;
//    }
    private boolean isValid525 (int row, int col, int rows, int cols) {
        if (row<0 || row>=rows || col<0 || col>=cols) {return false;}
        return true;
    }
//    private int[] convert1Dto2D (int index, int cols) {
//        return new int[] {index/cols,index%cols};
//    }
//    private int convert2Dto1D (int row, int col, int cols) {
//        return row*cols+col;
//    }
    /*
    419. Number of Islands II
    A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water and the given list of positions do not duplicate.
     */
    static class UnionFind{
        int[] roots;
        int[] sizes;
        int count;
        UnionFind(int n) {
            this.roots=new int[n];
            this.sizes=new int[n];
            for (int i=0;i<n;i++) {
                roots[i]=i;
                sizes[i]=1;
            }
        }
        int getRoot(int a) {
            if (a!=roots[a]) {
                roots[a]=getRoot(roots[a]);
            }
            return roots[a];
        }
        void union(int a, int b) {
            int roota = getRoot(a);
            int rootb = getRoot(b);
            if (roota==rootb) {
                return; // already same island
            } else if (sizes[roota]<=sizes[rootb]) {
                roots[roota]=rootb;
                sizes[rootb]+=sizes[roota];
            } else {
                roots[rootb]=roota;
                sizes[roota]+=sizes[rootb];
            }
            count--;
        }
    }
    final static int[][] DIRS = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
    public List<Integer> numIslands(int m, int n, int[][] positions) { // 419
        List<Integer> result = new ArrayList<>();
        if (m <= 0 || n <= 0 || positions == null || positions.length == 0 || positions[0].length == 0){
            return  result;
        }
        UnionFind uf = new UnionFind(m*n);
        int[][] island = new int[m][n];
        for(int[] p: positions){
            int row = p[0];
            int col = p[1];
            //this area is an existed island
            if (island[row][col] == 1){
                result.add(uf.count);
                continue;
            }
            //try to connect cur island with other existed island
            island[row][col] = 1;
            uf.count++;
            int cur = convert2Dto1D(row,col,n);
            for (int[] dir : DIRS) {
                int neirow = row+dir[0];
                int neicol = col+dir[1];
                if (!isValid(neirow,neicol,m,n)) {continue;}
                if (island[neirow][neicol]==0) {continue;}
                uf.union(cur,convert2Dto1D(neirow,neicol,n));
            }
            result.add(uf.count);
        }
        return result;
    }
    public List<Integer> numIslands419(int m, int n, int[][] positions) { // 419
        List<Integer> result = new ArrayList<>();
        if (positions==null || positions.length==0 || m<=0 || n<=0) {return result;}
        int len=positions.length;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        Map<Integer,Integer> roots = new HashMap<>();
        int num=0;
        for (int[] pos : positions) {
            int row = pos[0];
            int col = pos[1];
            int cur = convert2Dto1D(row,col,n);
            num++;
            roots.put(cur,cur);
            for (int[] dir : dirs) {
                int neirow = row+dir[0];
                int neicol = col+dir[1];
                if (!isValid(neirow,neicol,m,n)) {continue;}
                int neiRoot = findRoot(convert2Dto1D(neirow,neicol,n),roots);
                if (neiRoot==-1) {continue;}
                if (neiRoot!=cur) {
                    num--;
                    roots.put(neiRoot,cur);
                }
            }
            result.add(num);
        }
        return result;
        // Write your solution here
    }
//    private int findRoot(int index, Map<Integer,Integer> roots) {
//        if (roots.get(index)==null) {return -1;}
//        while (roots.get(index)!=index) {
//            index=roots.get(index);
//        }
//        return index;
//    }
//    private int convert2Dto1D (int row, int col, int cols) {
//        return row*cols+col;
//    }
    private boolean isValid (int row, int col, int rows, int cols) {
        if (row<0 || row>=rows || col<0 || col>=cols) {return false;}
        return true;
    }
    public static void main(String[] args) {
        Unifind solution = new Unifind();
        int[][] positions = new int[][]{{0,1},{0,0},{0,3},{0,5},{0,2},{0,4}};
        System.out.println(solution.numIslands(2,7,positions));
    }
}
