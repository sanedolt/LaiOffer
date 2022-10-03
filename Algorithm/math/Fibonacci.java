package com.laioffer.Algorithm.math;

public class Fibonacci {
    /*
    160. Climbing Stairs
    There are in total n steps to climb until you can reach the top. You can climb 1 or 2 steps a time. Determine the number of ways you can do that.
     */
    public int stairs(int n) { // 160
        if (n<4) {return n;}
        int a=1,b=2,c=0;
        for (int i=3;i<=n;i++) {
            c=a+b;
            a=b;
            b=c;
        }
        return c;
        // Write your solution here
    }
    public long fibonacci2(int K) { //12
        long e1 = 0L, e2 = 1L, e3 = 1L;
        if (K <= 0) {
            return e1;
        }
        if (K == 1) {
            return e2;
        }
        for (int i=2;i<=K;i++) {
          e3=e1+e2;
          e1=e2;
          e2=e3;
        }
        return e3;
    }
    public int fibonacci1(int K) { // 624
        if (K<0) {return 0;}
        if (K<2) {return K;}
        return fibonacci1(K-1)+fibonacci1(K-2);
        // Write your solution here
    }
    static final long[][] SEED = new long[][]{{1,1},{1,0}};
    public long fibonacci(int K) {
        long e1=0L,e2=1L,e3=(long) K-1;
        if (K<=0) {return e1;}
        if (K==1) {return e2;}
        if (K<=4) {return e3;}
        long[][] matrix = new long[][]{{1,1},{1,0}};
        power(matrix,K-1);
        return matrix[0][0];
        // Write your solution here
    }
    private void power(long[][] matrix, int pow) {
        if (pow==1) {return;}
        power(matrix,pow/2);
        multiply(matrix,matrix);
        if (pow%2==1) {
            multiply(matrix,SEED);
        }
    }
    private void multiply(long[][] matrix1, long[][] matrix2) {
        long topleft=matrix1[0][0]*matrix2[0][0]+matrix1[0][1]*matrix2[1][0];
        long topright=matrix1[0][0]*matrix2[0][1]+matrix1[0][1]*matrix2[1][1];
        long bottomleft=matrix1[1][0]*matrix2[0][0]+matrix1[1][1]*matrix2[1][0];
        long bottomright=matrix1[1][0]*matrix2[0][1]+matrix1[1][1]*matrix2[1][1];
        matrix1[0][0]=topleft;
        matrix1[0][1]=topright;
        matrix1[1][0]=bottomleft;
        matrix1[1][1]=bottomright;
    }
    public static void main(String[] args) {
        Fibonacci solution = new Fibonacci();
        System.out.println(solution.fibonacci(100));
    }
}
