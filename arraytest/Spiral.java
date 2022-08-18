package com.laioffer.arraytest;
import java.util.*;

public class Spiral {
    public List<Integer> spiral121(int[][] matrix) { // 121
        List<Integer> result = new ArrayList<Integer>();
        if (matrix==null) {return result;}
        int rows=matrix.length,cols=matrix[0].length;
        if (rows==0 || cols==0 || rows!=cols) {return result;}
        for (int k=0;k<rows/2;k++) {
            int l = rows-1-k;
            for (int j=k;j<l;j++) {
                result.add(matrix[k][j]);
            }
            for (int i=k;i<l;i++) {
                result.add(matrix[i][l]);
            }
            for (int j=l;j>k;j--) {
                result.add(matrix[l][j]);
            }
            for (int i=l;i>k;i--) {
                result.add(matrix[i][k]);
            }
        }
        if (rows%2==1) {result.add(matrix[rows/2][rows/2]);}
        return result;
        // Write your solution here
    }
    public List<Integer> spiral122(int[][] matrix) { // 122
        List<Integer> result = new ArrayList<Integer>();
        if (matrix==null) {return result;}
        int rows=matrix.length,cols=matrix[0].length;
        if (rows==0 || cols==0) {return result;}
        int left=0,right=cols-1,up=0,down=rows-1;
        while (left<right && up<down) {
            for (int i=left;i<right;i++) {
                result.add(matrix[up][i]);
            }
            for (int i=up;i<down;i++) {
                result.add(matrix[i][right]);
            }
            for (int i=right;i>left;i--) {
                result.add(matrix[down][i]);
            }
            for (int i=down;i>up;i--) {
                result.add(matrix[i][left]);
            }
            left++;right--;up++;down--;
        }
        if (left>right || up>down) {
            return result;
        }
        if (left==right) {
            for (int i=up;i<=down;i++) {
                result.add(matrix[i][left]);
            }
        } else {
            for (int i=left;i<=right;i++) {
                result.add(matrix[up][i]);
            }
        }
        // int l=Math.min(rows,cols)/2;
        // for (int k=0;k<l;k++) {
        //   int m=rows-1-k;
        //   int n=cols-1-k;
        //   for (int j=k;j<n;j++) {
        //     result.add(matrix[k][j]);
        //   }
        //   for (int i=k;i<m;i++) {
        //     result.add(matrix[i][n]);
        //   }
        //   for (int j=n;j>k;j--) {
        //     result.add(matrix[m][j]);
        //   }
        //   for (int i=m;i>k;i--) {
        //     result.add(matrix[i][k]);
        //   }
        // }
        // if (rows>cols && cols%2==1) {
        //   for (int i=l;i<rows-l;i++) {
        //     result.add(matrix[i][l]);
        //   }
        // }
        // if (rows<cols && rows%2==1) {
        //   for (int j=l;j<cols-l;j++) {
        //     result.add(matrix[l][j]);
        //   }
        // }
        // if (rows==cols && rows%2==1) {
        //   result.add(matrix[l][l]);
        // }
        return result;
        // Write your solution here
    }
    public int[][] spiralGenerate(int n) { // 123
        if (n<0) {return null;}
        int num=0;
        int[][] result = new int[n][n];
        for (int k=0;k<n/2;k++) {
            int l=n-1-k;
            for (int j=k;j<l;j++) {
                result[k][j]=++num;
            }
            for (int i=k;i<l;i++) {
                result[i][l]=++num;
            }
            for (int j=l;j>k;j--) {
                result[l][j]=++num;
            }
            for (int i=l;i>k;i--) {
                result[i][k]=++num;
            }
        }
        if (n%2==1) {result[n/2][n/2]=++num;}
        return result;
        // Write your solution here
    }
    public int[][] spiralGenerate(int m, int n) { // 124
        if (m<0 || n<0) {return null;}
        int[][] result = new int[m][n];
        int num=0;
        int l=Math.min(m,n)/2;
        for (int k=0;k<l;k++) {
            int p=m-1-k;
            int q=n-1-k;
            for (int j=k;j<q;j++) {
                result[k][j]=++num;
            }
            for (int i=k;i<p;i++) {
                result[i][q]=++num;
            }
            for (int j=q;j>k;j--) {
                result[p][j]=++num;
            }
            for (int i=p;i>k;i--) {
                result[i][k]=++num;
            }
        }
        if (m>n && n%2==1) {
            for (int i=l;i<m-l;i++) {
                result[i][l]=++num;
            }
        }
        if (m<n && m%2==1) {
            for (int j=l;j<n-l;j++) {
                result[l][j]=++num;
            }
        }
        if (m==n && m%2==1) {
            result[l][l]=++num;
        }
        return result;
        // Write your solution here
    }
    public void rotate(int[][] matrix) { // 125
        if (matrix==null) {return;}
        if (matrix.length==0) {return;}
        if (matrix.length!=matrix[0].length) {return;}
        if (matrix.length==1) {return;}
        int n=matrix.length;
        for (int k=0;k<n/2;k++) {
            int l=n-1-k;
            for (int i=k;i<l;i++) {
                int j=n-1-i;
                int tmp = matrix[k][i];
                matrix[k][i]=matrix[j][k];
                matrix[j][k]=matrix[l][j];
                matrix[l][j]=matrix[i][l];
                matrix[i][l]=tmp;
            }
        }
        return;
        // Write your solution here
    }
    public static void main(String[] args) {
        Spiral solution = new Spiral();
    }
}
