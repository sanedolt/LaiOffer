package com.laioffer.BFS;
import java.util.*;

public class Room {
    static final int[][] DIRS = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    /*
    195. Place To Put The Chair I
    Given a gym with k pieces of equipment and some obstacles.  We bought a chair and wanted to put this chair into the gym such that  the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. The gym is represented by a char matrix, ‘E’ denotes a cell with equipment, ‘O’ denotes a cell with an obstacle, 'C' denotes a cell without any equipment or obstacle. You can only move to neighboring cells (left, right, up, down) if the neighboring cell is not an obstacle. The cost of moving from one cell to its neighbor is 1. You can not put the chair on a cell with equipment or obstacle.
    Assumptions
    There is at least one equipment in the gym
    The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
    It is guaranteed that each 'C' cell is reachable from all 'E' cells.
    If there does not exist such place to put the chair, just return {-1, -1}
    */
    public List<Integer> putChair195(char[][] gym) { // 195
        List<Integer> result = new ArrayList<>();
        if (gym==null || gym.length==0 || gym[0].length==0) {return result;}
        int rows=gym.length,cols=gym[0].length;
        int[][] sum = new int[rows][cols];
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (gym[i][j]=='E') {
                    bfs(sum,gym,i,j);
                }
            }
        }
        int min=Integer.MAX_VALUE,mini=-1,minj=-1;
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (gym[i][j]=='C' && sum[i][j]<min) {
                    min=sum[i][j];
                    mini=i;
                    minj=j;
                }
            }
        }
        return Arrays.asList(mini,minj);
        // Write your solution here
    }
    private void bfs(int[][] sum, char[][] gym, int ei, int ej) {
        int rows=gym.length,cols=gym[0].length;
        int[][] direction = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];
        queue.add(new int[]{ei,ej});
        visited[ei][ej]=true;
        int step=0;
        while (!queue.isEmpty()) {
            step++;
            int size=queue.size();
            for (int i=0;i<size;i++) {
                int[] cur = queue.poll();
                for (int[] dir : direction) {
                    int neir=cur[0]+dir[0];
                    int neic=cur[1]+dir[1];
                    if (neir<0 || neir>=rows || neic<0 || neic>=cols || visited[neir][neic] || gym[neir][neic]=='O') {continue;}
                    sum[neir][neic]+=step;
                    visited[neir][neic]=true;
                    queue.offer(new int[]{neir,neic});
                }
            }
        }
    }
    /*
    196. Place To Put The Chair II
    Given a gym with k pieces of equipment without any obstacles.  Let’s say we bought a chair and wanted to put this chair into the gym such that the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. The gym is represented by a char matrix, ‘E’ denotes a cell with equipment, ' ' denotes a cell without equipment. The cost of moving from one cell to its neighbor(left, right, up, down) is 1. You can put chair on any cell in the gym.
    Assumptions
    There is at least one equipment in the gym
    The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
     */
    public List<Integer> putChair196(char[][] gym) { // 196
        if (gym==null || gym.length==0) {return new ArrayList<>();}
        int rows=gym.length, cols=gym[0].length;
        int[][] sum = new int[rows][cols];
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (gym[i][j]=='E') {
                    bfs(i,j,gym,sum);
                }
            }
        }
        int min=Integer.MAX_VALUE;
        int mini=-1,minj=-1;
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (sum[i][j]<min) {
                    min=sum[i][j];
                    mini=i;
                    minj=j;
                }
            }
        }
        return Arrays.asList(mini,minj);
        // Write your solution here
    }
    private void bfs(int ei, int ej, char[][] gym, int[][] sum) {
        int rows=gym.length,cols=gym[0].length;
        int[][] direction = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];
        queue.offer(new int[]{ei,ej});
        visited[ei][ej]=true;
        int step=0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int j=0;j<size;j++) {
                int[] cur = queue.poll();
                for (int[] dir : direction) {
                    int neir=cur[0]+dir[0];
                    int neic=cur[1]+dir[1];
                    if (neir<0 || neir>=rows || neic<0 || neic>=cols || visited[neir][neic]) {continue;}
                    queue.offer(new int[]{neir,neic});
                    visited[neir][neic]=true;
                    sum[neir][neic]+=step;
                } // end for i
            } // end for j
        } // end while
    }
    /*
    503. Walls and gates
    You are given a m x n 2D grid initialized with these three possible values.
    -1 - A wall or an obstacle.
    0 - A gate.
    INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
    Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
     */
    public int[][] wallsAndGates(int[][] rooms) { // 503
        if (rooms==null || rooms.length==0) {return rooms;}
        int rows=rooms.length;
        int cols=rooms[0].length;
        Queue<Integer> visited = new ArrayDeque<>();
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (rooms[i][j]==0) {
                    visited.offer(i*cols+j);
                }
            }
        }
        int countinf=0;
        while (countinf>0 || !visited.isEmpty()) {
            int size=visited.size();
            countinf=0;
            for (int i=0;i<size;i++) {
                int cur = visited.poll();
                int cx = cur%cols;
                int cy = cur/cols;
                int dis = rooms[cy][cx];
                for (int[] dir : DIRS) {
                    int tx = cx+dir[0];
                    int ty = cy+dir[1];
                    if (tx<0 || tx>=cols || ty<0 || ty>=rows) {continue;}
                    if (rooms[ty][tx]==Integer.MAX_VALUE) {
                        countinf++;
                        rooms[ty][tx]=dis+1;
                        visited.offer(ty*cols+tx);
                    }
                } // end for
            } // end for
        } // end while
        return rooms;
        // Write your solution here
    }
    /*
    511. Best Meeting Point
    A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
    For example, given three people living at (0,0), (0,4), and (2,2):
    1 - 0 - 0 - 0 - 1
    |   |   |   |   |
    0 - 0 - 0 - 0 - 0
    |   |   |   |   |
    0 - 0 - 1 - 0 - 0
    The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
     */
    public int minTotalDistance(int[][] grid) {
        if (grid==null || grid.length==0 || grid[0].length==0) {return 0;}
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        return minDistance1D(rows) + minDistance1D(cols);
    }
    private int minDistance1D(List<Integer> points) {
        int distance = 0;
        int left = 0, right = points.size() - 1;
        while (left < right) {
            distance += points.get(right--) - points.get(left++);
        }
        return distance;
    }
    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }
    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }
    public int minTotalDistance2(int[][] grid) { // 511
        if (grid==null || grid.length==0 || grid[0].length==0) {return 0;}
        List<Integer> someone = getOne(grid);
        return bfs(someone,grid.length,grid[0].length);
        // Write your solution here
    }
    private List<Integer> getOne(int[][] grid) {
        int rows=grid.length,cols=grid[0].length;
        List<Integer> someone = new ArrayList<>();
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (grid[i][j]==1) {
                    someone.add(i*cols+j);
                }
            }
        }
        return someone;
    }
    private int bfs(List<Integer> someone, int rows, int cols) {
        final int[][] step = new int[][] {{1,0},{0,1}};
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];
        queue.add(0);
        visited[0][0]=true;
        int limit=rows+cols-2,minsum=Integer.MAX_VALUE;
        for (int steps=0;steps<limit;steps++) {
            int size = queue.size();
            for (int i=0;i<size;i++) {
                int cur = queue.poll();
                int midx=cur%cols,midy=cur/cols;
                for (int[] st : step) {
                    int testy=midy+st[0];
                    int testx=midx+st[1];
                    if (testy<0 || testy==rows || testx<0 || testx==cols || visited[testy][testx]) {continue;}
                    visited[testy][testx]=true;
                    queue.add(testy*cols+testx);
                    minsum=Math.min(minsum,getSum(someone,cols,testx,testy));
                } // end for direction
            } // end for i
        } // end for step
        return minsum;
    }
    private int getSum(List<Integer> someone, int cols, int x, int y) {
        int sum=0;
        for (int one : someone) {
            sum+=Math.abs(one/cols-y)+Math.abs(one%cols-x);
        }
        return sum;
    }
    /*
    515. Shortest Distance from All Points
    You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
    Each 0 marks an empty land which you can pass by freely.
    Each 1 marks a building which you cannot pass through.
    Each 2 marks an obstacle which you cannot pass through.
    For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
    1 - 0 - 2 - 0 - 1
    |   |   |   |   |
    0 - 0 - 0 - 0 - 0
    |   |   |   |   |
    0 - 0 - 1 - 0 - 0
    The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
    Note:
    There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
     */
    static final int[][] DIRS = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
    public int shortestDistance(int[][] grid) { // 515
        if (grid==null || grid.length==0 || grid[0].length==0) {return -1;}
        int rows = grid.length;
        int cols = grid[0].length;

        // Total Matrix to store total distance sum for each empty cell.
        int[][] total = new int[rows][cols];

        int emptyLandValue = 0;
        int minDist = Integer.MAX_VALUE;

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {

                // Start a BFS from each house.
                if (grid[row][col] == 1) {
                    minDist = Integer.MAX_VALUE;

                    // Use a queue to perform a BFS, starting from the cell at (r, c).
                    Queue<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{ row, col });

                    int steps = 0;
                    while (!q.isEmpty()) {
                        steps++;

                        for (int level = q.size(); level > 0; --level) {
                            int[] cur = q.poll();

                            for (int[] dir : DIRS) {
                                int nextRow = cur[0] + dir[0];
                                int nextCol = cur[1] + dir[1];

                                // For each cell with the value equal to empty land value
                                // add distance and decrement the cell value by 1.
                                if (nextRow >= 0 && nextRow < rows &&
                                        nextCol >= 0 && nextCol < cols &&
                                        grid[nextRow][nextCol] == emptyLandValue) {
                                    grid[nextRow][nextCol]--;
                                    total[nextRow][nextCol] += steps;

                                    q.offer(new int[]{ nextRow, nextCol });
                                    minDist = Math.min(minDist, total[nextRow][nextCol]);
                                }
                            }
                        }
                    }

                    // Decrement empty land value to be searched in next iteration.
                    emptyLandValue--;
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
        // Write your solution here
    }
    /*
    569. Distance to Zero
    Suppose there are 0s and 1s in a matrix. Cell A and Cell B are adjacent if Cell B is above, below, on the left, or on the right of Cell A. The distance between two adjacent cell is 1. Calculate the distance to the closest 0 of each cell.
     */
    public int[][] updateMatrix(int[][] matrix) {// 569
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return matrix;}
        int rows=matrix.length,cols=matrix[0].length;
        int[][] result = new int[rows][cols];
        for (int i=0;i<rows*cols;i++) {
            helper(i,matrix,result);
        } // end for i
        return result;
        // Write your solution here
    }
    private void helper(int index, int[][] matrix, int[][] result) {
        int rows=matrix.length,cols=matrix[0].length;
        Map<Integer,Integer> distance = new HashMap<>();
        Queue<Integer> passed = new ArrayDeque<>();
        distance.put(index,0);
        passed.offer(index);
        int[] origLoc = convert1Dto2D(index,cols);
        boolean found = false;
        if (matrix[origLoc[0]][origLoc[1]]==0) {found=true;}
        while (!passed.isEmpty() && !found) {
            int cur = passed.poll();
            int[] currLoc = convert1Dto2D(cur,cols);
            int dc = distance.get(cur);
            for (int[] dir : DIRS) {
                int testRow = currLoc[0]+dir[0];
                int testCol = currLoc[1]+dir[1];
                if (!isValid(testRow,testCol,rows,cols)) {continue;}
                int testIndex = convert2Dto1D(testRow,testCol,cols);
                if (distance.get(testIndex)==null) {
                    distance.put(testIndex,dc+1);
                    passed.offer(testIndex);
                    if (!found && matrix[testRow][testCol]==0) {
                        result[origLoc[0]][origLoc[1]]=dc+1;
                        found=true;
                        break;
                    }
                }
            } // end for dir
        } // end while
    }
//    private boolean isValid(int row, int col, int rows, int cols) {
//        if (row<0 || row>=rows || col<0 || col>=cols) {return false;}
//        return true;
//    }
//    private int convert2Dto1D (int row, int col, int cols) {
//        return row*cols+col;
//    }
//    private int[] convert1Dto2D (int index, int cols) {
//        return new int[] {index/cols,index%cols};
//    }
    public static void main(String[] args) {
        Room solution = new Room();
        char[][] gym = new char[][] {{'C','C','E','O','C'},{'C','C','O','C','E'},{'C','C','E','E','C'},{'C','O','C','E','E'},{'C','C','O','C','C'}};
        char[][] gym2 = new char[][] {{'E','E',' ','E','E'},{'E',' ','E','E',' '},{' ','E',' ',' ','E'},{'E',' ',' ','E',' '},{'E',' ',' ',' ','E'}};
        //System.out.println(solution.putChair(gym));
        //System.out.println(solution.putChair2(gym2));
        int[][] rooms = new int[][]{{-1,0,0,-1,-1,-1,2147483647,0,0,2147483647,2147483647,0,2147483647,-1,2147483647,2147483647,0,2147483647,0,0,-1,0,-1,2147483647,0,0,-1,0,-1,-1,0,2147483647,-1,0,-1,2147483647,2147483647,2147483647,0,0,-1,-1,-1,-1,-1,2147483647},
                {2147483647,-1,-1,0,2147483647,0,-1,-1,-1,2147483647,-1,2147483647,0,2147483647,0,2147483647,-1,0,2147483647,2147483647,0,-1,2147483647,2147483647,0,2147483647,0,2147483647,-1,-1,2147483647,-1,-1,2147483647,-1,0,-1,-1,0,0,-1,2147483647,0,2147483647,0,0},
                {2147483647,2147483647,2147483647,2147483647,0,-1,2147483647,0,-1,2147483647,2147483647,2147483647,0,-1,0,-1,-1,2147483647,-1,0,2147483647,-1,-1,0,-1,-1,-1,2147483647,2147483647,0,0,2147483647,0,2147483647,2147483647,0,0,0,2147483647,2147483647,2147483647,0,2147483647,2147483647,0,0},
                {0,-1,0,2147483647,-1,0,0,2147483647,-1,-1,-1,0,2147483647,2147483647,0,2147483647,-1,-1,0,-1,0,0,0,-1,2147483647,2147483647,0,-1,-1,2147483647,-1,-1,2147483647,0,-1,0,-1,-1,-1,-1,2147483647,0,2147483647,0,-1,-1},
                {2147483647,0,-1,-1,2147483647,-1,-1,-1,2147483647,0,-1,0,0,0,2147483647,0,2147483647,0,0,-1,-1,0,-1,-1,0,-1,-1,2147483647,-1,0,-1,0,2147483647,-1,0,2147483647,2147483647,-1,0,0,2147483647,0,2147483647,2147483647,-1,0}};
        System.out.println(Arrays.deepToString(solution.wallsAndGates(rooms)));
    }
}
