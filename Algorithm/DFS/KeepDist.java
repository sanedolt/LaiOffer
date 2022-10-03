package com.laioffer.Algorithm.DFS;

public class KeepDist {
    /*
    264. Keep Distance For Identical Elements(C++ not ready)
    Given an integer k, arrange the sequence of integers [1, 1, 2, 2, 3, 3, ...., k - 1, k - 1, k, k], such that the output integer array satisfy this condition:
    Between each two i's, they are exactly i integers (for example: between the two 1s, there is one number, between the two 2's there are two numbers).
    If there does not exist such sequence, return null.
    Assumptions:
    k is guaranteed to be > 0
     */
    public int[] keepDistance1(int k) { // 264
        if (k<1) {return new int[2*k];}
        int[] result = new int[2*k];
        for (int i=0;i<k;i++) {
          result[i*2]=i+1;
          result[i*2+1]=i+1;
        }
        boolean[] used = new boolean[k+1];
        return helperI(result,0,used)?result:null;
        // Write your solution here.
    }
    private boolean helperI(int[] array, int index, boolean[] used) {
        int len=array.length;
        if (index==len) {return true;}
        for (int i=index;i<len;i++) {
            int cur=array[i];
            if (!used[cur] || (index>cur && array[index-cur-1]==cur)) {
                swap(array,index,i); // switch element from i to index which has value cur
                used[cur]=!used[cur];
                if (helperI(array,index+1,used)) {return true;}
                swap(array,index,i);
                used[cur]=!used[cur];
            }
        }
        return false;
    }
    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }
    public int[] keepDistance2(int k) {
        if (k<1) {return new int[2*k];}
        int[] result = new int[2*k];
        int[] used = new int[k+1];
        return helperII(result,0,used)?result:null;
        // Write your solution here.
    }
    private boolean helperII(int[] array, int index, int[] used) {
        if (index==array.length) {return true;}
        for (int i=1;i<used.length;i++) {
            if (used[i]==0 || used[i]==1 && index>i && array[index-1-i]==i) {
                array[index]=i;
                used[i]++;
                if (helperII(array,index+1,used)) {return true;}
                used[i]--;
            }
        }
        return false;
    }
    public int[] keepDistance3(int k) {
        if (k<1) {return new int[2*k];}
        int[] result = new int[2*k];
        return helperIII(result,k)?result:null;
        // Write your solution here.
    }
    private boolean helperIII(int[] array, int n) {
        if (n==0) {return true;}
        for (int i=0;i<array.length-n-1;i++) {
            if (array[i]==0 && array[i+n+1]==0) {
                array[i]=array[i+n+1]=n;
                if (helperIII(array,n-1)) {return true;}
                array[i]=array[i+n+1]=0;
            } // ned if
        } // end for
        return false;
    }
    public static void main(String[] args) {
        KeepDist solution = new KeepDist();
    }
}
