package com.laioffer.arraytest;
import java.util.*;

public class Search {
    private void printArray (int[] array) {
        System.out.println(Arrays.toString(array));
    }
    public int binarySearch1(int[] array, int target) { // 14
        if (array==null || array.length==0) {return -1;}
        int left=0,right=array.length-1;
        while (left<=right) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                return mid;
            } else if (array[mid]>target) {
                right=mid-1;
            } else { // array[mid]<target
                left=mid+1;
            }
        }
        return -1;
        // Write your solution here
    }
    public int firstOccur(int[] array, int target) { // 15
        if (array==null || array.length==0) {return -1;}
        int left=0,right=array.length-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                right=mid;
            } else if (array[mid]>target) {
                right=mid-1;
            } else { // array[mid]<target
                left=mid+1;
            }
        }
        return array[left]==target?left:-1;
        // Write your solution here
    }
    public int lastOccur(int[] array, int target) { // 16
        if (array==null || array.length==0) {return -1;}
        int left=0,right=array.length-1;
        while (left<right-1) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                left=mid;
            } else if (array[mid]<target) {
                left=mid+1;
            } else { // array[mid]>target
                right=mid-1;
            }
        }
        if (array[right]==target) {
            return right;
        } else if (array[left]==target) {
            return left;
        } else {
            return -1;
        }
        // Write your solution here
    }
    public int totalOccurrence(int[] array, int target) { // 24
        if (array==null || array.length==0) {return 0;}
        int len=array.length;
        int left=0,right=len-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                right=mid;
            } else if (array[mid]>target) {
                right=mid-1;
            } else { // array[mid]<target
                left=mid+1;
            }
        }
        int fo=array[left]==target?left:-1;
        left=0;right=len-1;
        while (left<right-1) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                left=mid;
            } else if (array[mid]<target) {
                left=mid+1;
            } else { // array[mid]>target
                right=mid-1;
            }
        }
        int lo=array[right]==target?right:(array[left]==target?left:-1);
        return fo+lo==-2?0:lo-fo+1;
        // Write your solution here
    }
    /*
    412. Search For a Range
    Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
    Your algorithm's runtime complexity must be in the order of O(log n).
    If the target is not found in the array, return [-1, -1].
    Example
    Given [1, 3, 3, 3, 5, 5, 7], and target value 3,
    return [1, 3].
    */
    public int[] range(int[] array, int target) { // 412
        // Write your solution here
        int[] result = new int[]{-1,-1};
        if (array==null || array.length==0) {return result;}
        int tmp = bs1(array,target);
        if (tmp==-1) {return result;}
        result[0]=tmp;
        result[1]=bs2(array,target);
        return result;
    }
    private int bs1(int[] array, int target) {
        int len=array.length;
        int left=0,right=len-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                right=mid;
            } else if (array[mid]>target) {
                right=mid-1;
            } else { // array[mid]<target
                left=mid+1;
            }
        }
        return array[left]==target?left:-1;
    }
    private int bs2(int[] array, int target) {
        int len=array.length;
        int left=0,right=len-1;
        while (left<right) {
            int mid=right-(right-left)/2;
            if (array[mid]==target) {
                left=mid;
            } else if (array[mid]<target) {
                left=mid+1;
            } else { // array[mid]>target
                right=mid-1;
            }
        }
        return right;
    }
    public int closest(int[] array, int target) { // 17
        if (array==null || array.length==0) {return -1;}
        int left=0,right=array.length-1;
        while (left<right-1) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                return mid;
            } else if (array[mid]<target) {
                left=mid;
            } else { // array[mid]>target
                right=mid;
            }
        }
        if (Math.abs(array[left]-target)<=Math.abs(array[right]-target)) {
            return left;
        } else {
            return right;
        }
        // Write your solution here
    }
    public int[] kClosest(int[] array, int target, int k) { // 19
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        if (k<=0 || k>len) {return new int[0];}
        int closestIndex = closest(array,target);
        int[] result = new int[k];
        result[0]=array[closestIndex];
        findClosest(array,result,target,closestIndex);
        return result;
        // Write your solution here
    }
    private void findClosest(int[] array, int[] result, int target, int sep) {
        int k=result.length;
        int leftIndex=sep-1,rightIndex=sep+1,resIndex=1;
        while (--k>0) {
            int leftDist=leftIndex>=0?Math.abs(target-array[leftIndex]):Integer.MAX_VALUE;
            int rightDist=rightIndex<array.length?Math.abs(target-array[rightIndex]):Integer.MAX_VALUE;
            if (leftDist<=rightDist) {
                result[resIndex++]=array[leftIndex--];
            } else {
                result[resIndex++]=array[rightIndex++];
            }
        }
    }
    /*
    161. Square Root I
    Given an integer number n, find its integer square root.
    */
    public int sqrt(int x) { // 161
//        long r = x;
//        while (r*r>x) {
//            r=(r+x/r)/2;
//        }
//        return (int) r;
        if (x<0) {return -1;}
        if (x<2) {return x;}
        int left=0,right=x/2+1;
        while (left<right-1) {
            int mid=left+(right-left)/2;
            if (x/mid==mid) {
                return mid;
            } else if (x/mid<mid) {
                right=mid-1;
            } else { // x/mid>mid
                left=mid;
            }
        }
        return x/right<right?left:right;
        // Write your solution here
    }
    public int kth1(int[] a, int[] b, int k) { // 202
        if (a==null || b==null || a.length+b.length==0) {return -1;}
        int la=a.length,lb=b.length;
        if (la+lb==0 || k<1 || k>la+lb) {return -1;}
        int leftA=0,leftB=0,valA=0,valB=0,max=Integer.MAX_VALUE;
        while (k>1) {
          valA=leftA+k/2-1>=la?max:a[leftA+k/2-1];
          valB=leftB+k/2-1>=lb?max:b[leftB+k/2-1];
          leftA+=valA<=valB?k/2:0;
          leftB+=valA>valB?k/2:0;
          k-=k/2;
        }
        valA=leftA>=la?max:a[leftA];
        valB=leftB>=lb?max:b[leftB];
        return Math.min(valA,valB);
        // Write your solution here
    }
    public int kth2(int[] a, int[] b, int k) { // 202
        if (a==null || b==null || a.length+b.length==0) {return -1;}
        int la=a.length,lb=b.length;
        if (la+lb==0 || k<1 || k>la+lb) {return -1;}
        return helper(a,0,b,0,k);
        // Write your solution here
    }
    private int helper(int[] a, int aleft, int[] b, int bleft, int k) {
        int la=a.length,lb=b.length;
        if (aleft>=la) {return b[bleft+k-1];}
        if (bleft>=lb) {return a[aleft+k-1];}
        if (k==1) {return a[aleft]<=b[bleft]?a[aleft]:b[bleft];}
        int amid=aleft+k/2-1;
        int bmid=bleft+k/2-1;
        int aval=amid>=la?Integer.MAX_VALUE:a[amid];
        int bval=bmid>=lb?Integer.MAX_VALUE:b[bmid];
        if (aval<=bval) {
            return helper(a,amid+1,b,bleft,k-k/2);
        } else {
            return helper(a,aleft,b,bmid+1,k-k/2);
        }
    }
    public int kth3(int[] a, int[] b, int k) { // 202
        if (a==null || b==null || a.length+b.length==0) {return -1;}
        int la=a.length,lb=b.length;
        if (la+lb==0 || k<1 || k>la+lb) {return -1;}
        int ans_from_a = binarySearch(a,b,k);
        return ans_from_a==-1?b[binarySearch(b,a,k)]:a[ans_from_a];
        // Write your solution here
    }
    private int readFromIndex(int[] nums, int index) {
        if (index<0) {
            return Integer.MIN_VALUE;
        } else if (index>=nums.length) {
            return Integer.MAX_VALUE;
        } else {
            return nums[index];
        }
    }
    private int binarySearch(int[] a, int[] b, int k) {
        int l=0,r=Math.min(k,a.length)-1;
        while (l<=r) {
            int m=l+(r-l)/2;
            if (a[m]<readFromIndex(b,k-m-2)) {
                l=m+1;
            } else if (a[m]>readFromIndex(b,k-m-1)) {
                r=m-1;
            } else {
                return m;
            }
        }
        return -1;
    }
    /*
    203. Median Of Two Arrays
    Given two arrays of integers, find the median value.
    Assumptions
    The two given array are not null and at least one of them is not empty
    The two given array are not guaranteed to be sorted
    Examples
    A = {4, 1, 6}, B = {2, 3}, median is 3
    A = {1, 4}, B = {3, 2}, median is 2.5
     */
    public double median(int[] a, int[] b) { // 203
        // TC: (n+m) worst O((n+m)^2), SC:O(log(n+m))
        if (a==null && b==null) {return 0;}
        int la=a.length,lb=b.length;
        int len=la+lb;
        if (len==0) {return 0;}
        int med=len/2;
        quickSelect(a,b,0,len-1,med);
        double m1 = getVal(a,b,med);
        if (len%2==1) {return m1;}
        quickSelect(a,b,0,med,med-1);
        double m2 = getVal(a,b,med-1);
        return (m1+m2)/2;
        // Write your solution here
    }
    private void quickSelect(int[] a, int[] b, int left, int right, int k) {
        int pivot = partition(a,b,left,right);
        if (pivot==k) {
            return;
        } else if (pivot>k) {
            quickSelect(a,b,left,pivot-1,k);
        } else { // pivot<k
            quickSelect(a,b,pivot+1,right,k);
        }
    }
    private int partition(int[] a, int[] b, int left, int right) {
        int pivotVal=getVal(a,b,right);
        int start=left,end=right-1;
        while (start<=end) {
            if (getVal(a,b,start)<pivotVal) {
                start++;
            } else if (getVal(a,b,end)>=pivotVal) {
                end--;
            } else {
                swap(a,b,start++,end--);
            }
        }
        swap(a,b,start,right);
        return start;
    }
    private void swap(int[] a, int[] b, int i, int j) {
        int tmp = getVal(a,b,i);
        setVal(a,b,i,getVal(a,b,j));
        setVal(a,b,j,tmp);
    }
    private int getVal(int[] a, int[] b, int index) {
        return index<a.length?a[index]:b[index-a.length];
    }
    private void setVal(int[] a, int[] b, int index, int val) {
        if (index<a.length) {a[index]=val;} else {b[index-a.length]=val;}
    }
    /*
    410. Median Of Two Sorted Arrays
    Given two sorted arrays of integers in ascending order, find the median value.
    Assumptions
    The two given array are not null and at least one of them is not empty
    The two given array are guaranteed to be sorted
    Examples
    A = {1, 4, 6}, B = {2, 3}, median is 3
    A = {1, 4}, B = {2, 3}, median is 2.5
     */
    public double median410a(int[] a, int[] b) { // 410
        if (a==null || b==null) {return -1;}
        int la=a.length,lb=b.length;
        if (la>lb) {return median(b,a);}
        if (la==0) {
            return lb%2==0?(b[lb/2]+b[lb/2-1])/2.0:b[lb/2]/1.0;
        }
        int len=(la+lb+1)/2;
        int left=0,right=la; // used numbers from a
        int ib=-1,na=Integer.MAX_VALUE,vb=Integer.MAX_VALUE;
        while (left<right) {
            int mid=left+(right-left)/2;
            ib=len-mid-1;
            na=mid==la?Integer.MAX_VALUE:a[mid];
            vb=b[ib];
            if (na<vb) {
                left=mid+1;
            } else { // va>=vb
                right=mid;
            }
        }
        int ia=left-1;
        ib=len-(ia+1)-1;
        int va=ia==-1?Integer.MIN_VALUE:a[ia];
        vb=ib==-1?Integer.MIN_VALUE:b[ib];
        na=ia+1==la?Integer.MAX_VALUE:a[ia+1];
        int nb=ib+1==lb?Integer.MAX_VALUE:b[ib+1];
        if ((la+lb)%2==1) {
            return Math.max(va,vb)/1.0;
        } else {
            return (Math.max(va,vb)+Math.min(na,nb))/2.0;
        }
    }
    public double median410b(int[] a, int[] b) { // 410
        if (a==null || b==null) {return -1;}
        int la=a.length,lb=b.length;
        int len=la+lb,mid=len/2;
        int[] result = kth(a,b,0,0,mid+1);
        double ans = result[0]==0?(double) a[result[1]]:(double) b[result[2]];
        if (len%2==1) {
            return ans;
        } else { // need to find the biggest smaller one
            int temp1=result[1]>0?a[result[1]-1]:Integer.MIN_VALUE;
            int temp2=result[2]>0?b[result[2]-1]:Integer.MIN_VALUE;
            return (ans+Math.max(temp1,temp2))/2;
        }
        // Write your solution here
    }
    private int[] kth(int[] a, int[] b, int lefta, int leftb, int k) {
        // zero element is which array contains the value, first element is the index for a, second element is the index for b, third element is the value
        if (lefta>=a.length || k==1 && leftb<b.length && b[leftb]<a[lefta]) {
            return new int[]{1,lefta,leftb+k-1};
        }
        if (leftb>=b.length || k==1 && lefta<a.length && a[lefta]<=b[leftb]) {
            return new int[]{0,lefta+k-1,leftb};
        }
        int mida=lefta+k/2-1;
        int midb=leftb+k/2-1;
        int vala = mida>=a.length?Integer.MAX_VALUE:a[mida];
        int valb = midb>=b.length?Integer.MAX_VALUE:b[midb];
        return vala<=valb?kth(a,b,mida+1,leftb,k-k/2):kth(a,b,lefta,midb+1,k-k/2);
    }
    /*
    267. Search In Sorted Matrix I
    Given a 2D matrix that contains integers only, which each row is sorted in an ascending order. The first element of next row is larger than (or equal to) the last element of previous row.
    Given a target number, returning the position that the target locates within the matrix. If the target number does not exist in the matrix, return {-1, -1}.
     */
    public int[] search267(int[][] matrix, int target) { // 267
        if (matrix==null) {return null;}
        int rows=matrix.length,cols=matrix[0].length;
        int left=0,right=rows*cols-1;
        while (left<=right) {
            int mid=left+(right-left)/2;
            int midrow=mid/cols;
            int midcol=mid%cols;
            if (matrix[midrow][midcol]==target) {
                return new int[]{midrow,midcol};
            } else if (matrix[midrow][midcol]>target) {
                right=mid-1;
            } else { // matrix[midrow][midcol]<target
                left=mid+1;
            }
        }
        return new int[]{-1,-1};
        // Write your solution here
    }
    /*
    268. Search In Sorted Matrix II
    Given a 2D matrix that contains integers only, which each row is sorted in ascending order and each column is also sorted in ascending order.
    Given a target number, returning the position that the target locates within the matrix. If the target number does not exist in the matrix, return {-1, -1}.
     */
    public int[] search(int[][] matrix, int target) {
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {
            return new int[]{-1,-1};
        }
        int row = matrix.length-1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return new int[]{row,col};
            }
        }
        return new int[]{-1,-1};
        // Write your solution here
    }
    /*
    270. Max Sum Of Two Indices
    Given an array with integers, find two indices i and j  (j>=i),  such that the value of A[i]+A[j]+ (j - i) is maximized.
    Return (i, j).
     */
    public int[] maxSum(int[] array) { // 270
        if (array==null || array.length==0) {return new int[0];}
        int len=array.length;
        // suppose we have a sum from A[i]-i, then we definitely need to the maximum of a[i]+i to get the max sum
        // so we can find the maximum a[i]+i first
        // now we find the index to get maximum a[i]+i, any element on the right of i would have smaller a[j]-j, because a[j]<a[i] & j>i
        // so we only need to search element up to i for maximum a[i]-i
        int indSub=0,indSum=0;
        for (int i=1;i<len;i++) {
            if (array[i]+i>array[indSum]+indSum) {
                indSum=i;
            }
        }
        for (int i=indSum;i>=0;i--) {
            if (array[i]-i>array[indSub]-indSub) {
                indSub=i;
            }
        }
        return new int[]{indSub,indSum};
        // Write your solution here
    }
    /*
    278. 2 Difference In Sorted Array
    Given a sorted array A, find a pair (i, j) such that A[j] - A[i] is identical to a target number(i != j).
    If there does not exist such pair, return a zero length array.
     */
    public int[] twoDiff(int[] array, int target) { // 278
        if (array==null || array.length<2) {return new int[0];}
        int[] result=new int[2];
        int len=array.length;
        for (int i=0;i<len-1;i++) {
            int left=i+1,right=len-1;
            int need=array[i]+Math.abs(target);
            while (left<=right) {
                int mid=left+(right-left)/2;
                if (array[mid]==need) {
                    if (target>=0) {
                        return new int[]{i,mid};
                    } else {
                        return new int[]{mid,i};
                    }
                } else if (array[mid]>need) {
                    right=mid-1;
                } else {
                    left=mid+1;
                }
            }
        } // end for
        return new int[0];
        // Write your solution here
    }
    /*
    321. Divide Two Integers With Restrictions
    Given two integers a and b, calculate a / b without using divide/mod operations.
    Assuming any number divided by 0 is Integer.MAX_VALUE.
    Examples:
    0 / 1 = 0
    1 / 0 = Integer.MAX_VALUE
    -1 / 0 = Integer.MAX_VALUE
    11 / 2 = 5
    -11 / 2 = -5
    11 / -2 = -5
    -11 / -2 = 5
    */
    public int divide(int a, int b) {
        if (b==0) {return Integer.MAX_VALUE;}
        if (b==1 || b==-1) {return a*b;}
        if (a==0) {return 0;}
        int aa=Math.abs(a), bb=Math.abs(b);
        int signa=aa>a?-1:1;
        int signb=bb>b?-1:1;
        int signc=signa*signb;
        int left=0,right=aa>>1;
        while (left<right) {
            int mid=right-(right-left)/2;
            if (mid*bb==aa) {
                return mid*signc;
            } else if (mid*bb>aa) {
                right=mid-1;
            } else { // (mid*bb<aa)
                left=mid;
            }
        }
        return signc*right;
        // Write your solution here
    }
    /*
    327. Find Local Minimum
    Given an unsorted integer array, return the local minimum's index.
    An element at index i is defined as local minimum when it is smaller than all its possible two neighbors a[i - 1] and a[i + 1]
    (you can think a[-1] = +infinite, and a[a.length] = +infinite)
    Assumptions:
    The given array is not null or empty.
    There are no duplicate elements in the array.
    There is always one and only one result for each case.
     */
    public static int localMinimum(int[] array) { // 327
        if (array==null || array.length==0) {return -1;}
        int left=0,right=array.length-1;
        while (left<right-1) {
            int mid=left+(right-left)/2;
            boolean midSmallerThanLeft = array[mid]<array[mid-1];
            boolean midSmallerThanRight = array[mid]<array[mid+1];
            if (midSmallerThanLeft && midSmallerThanRight) {return mid;}
            if (array[left]>array[right]) { // prefer the right part
                if (array[mid]>array[right] || midSmallerThanLeft && !midSmallerThanRight) {
                    left=mid;
                } else {
                    right=mid;
                }
            } else { // array[left]<array[right], prefer the left part
                if (array[mid]>array[left] || !midSmallerThanLeft && midSmallerThanRight) {
                    right=mid;
                } else {
                    left=mid;
                }
            }
        } // end while
        return array[left]<array[right]?left:right;
    }
    /*
    329. Number Of Negative Numbers In Sorted Matrix
    Given an matrix of integers, each row is sorted in ascending order from left to right, each column is also sorted in ascending order from top to bottom.
    Determine how many negative numbers in the matrix.
     */
    public int negNumber(int[][] matrix) { // 329
        if (matrix==null) {return -1;}
        int rows=matrix.length,cols=matrix[0].length;
        int num=0,index=0;
        for (int i=0;i<rows;i++) {
            if (i==0) {
                index=binarySearch(matrix[0],0,0,cols-1);
            } else {
                index=binarySearch(matrix[i],0,0,index);
            }
            num+=index+1;
        }
        return num;
        // Write your solution here
    }
    private int binarySearch(int[] array, int target, int left, int right) {
        if (left>right) {return -1;}
        while (left<right-1) {
            int mid=left+(right-left)/2;
            if (array[mid]>=target) {
                right=mid-1;
            } else {
                left=mid;
            }
        }
        if (array[right]<target) {
            return right;
        } else if (array[left]<target) {
            return left;
        } else {
            return -1;
        }
    }
    /*
    344. Celebrity Problem
    Given an binary matrix of N * N (the only elements in the matrix are 0s and 1s), each of the indices represents one person.
    matrix[i][j] = 1 if and only if person i knows person j (this is single direction, only if matrix[j][i] = 1 such that person j knows person i).
    Determine if there is one celebrity among all N persons, a celebrity is defined as
    He does not know any other person.
    All the other persons know him.
    Return the celebrity's index if there exist one (there could be at most one celebrity, why?), return -1 otherwise.
     */
    public int celebrity(int[][] matrix) { // 344
        if (matrix==null || matrix.length<1) {return -1;}
        int len=matrix.length;
        if (len==1) {return 0;}
        int[] sumRow = new int[len];
        int[] sumCol = new int[len];
        for (int i=0;i<len;i++) {
            for (int j=0;j<len;j++) {
                if (i==j) {continue;}
                sumRow[i]+=matrix[i][j];
                sumCol[j]+=matrix[i][j];
            }
        }
        for (int i=0;i<len;i++) {
            if (sumRow[i]==0 && sumCol[i]==len-1) {return i;}
        }
        return -1;
        // Write your solution here
    }
    /*
    401. Search In Bitonic Array
    Search for a target number in a bitonic array, return the index of the target number if found in the array, or return -1.
    A bitonic array is a combination of two sequence: the first sequence is a monotonically increasing one and the second sequence is a monotonically decreasing one.
    Assumptions:
    The array is not null.
    Examples:
    array = {1, 4, 7, 11, 6, 2, -3, -8}, target = 2, return 5.
     */
    public int search401(int[] array, int target) { // 401
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (len==1) {
            return array[0]==target?0:-1;
        }
        // find maximum first
        int left=0, right=len-2;
        while (left<right) {
            int mid=left+(right-left)/2;
            if ((array[mid+1]>array[mid])) { // mid in increasing half
                left=mid+1;
            } else { // mid in decreasing half
                right=mid;
            }
        }
        // search for increasing part
        int max=left;
        left=0; right=max;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                return mid;
            } else if (array[mid]>target) {
                right=mid-1;
            } else { // array[mid]<target
                left=mid+1;
            }
        }
        if (array[left]==target) {return left;}
        left=max; right=len-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                return mid;
            } else if (array[mid]>target) {
                left=mid+1;
            } else { // array[mid]<target
                right=mid-1;
            }
        }
        if (array[right]==target) {return right;}
        return -1;
        // Write your solution here
    }
    private boolean checkrow (int[][] array, int row) {
        if (array==null || array.length==0 || array[row].length==0) {return false;}
        for (int i=0;i<array[row].length;i++) {
            if (array[row][i]==1) {return true;}
        }
        return false;
    }
    private boolean checkcol (int[][] array, int col) {
        if (array==null || array.length==0) {return false;}
        for (int i=0;i<array.length;i++) {
            if (array[i][col]==1) {return true;}
        }
        return false;
    }
    /*
    413. Smallest Rectangle Enclosing Black Pixels
    An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
     */
    public int minArea(int[][] image, int x, int y) { // 413
        if (image[x][y]==0) {return -1;}
        int rows=image.length,cols=image[0].length;
        int top=0,bottom=x,mid=0;
        while (top<bottom) {
            mid=top+(bottom-top)/2;
            if (checkrow(image,mid)) {
                bottom=mid;
            } else {
                top=mid+1;
            }
        }
        int areatop=top;
        top=x;bottom=rows-1;
        while (top<bottom-1) {
            mid=top+(bottom-top)/2;
            if (checkrow(image,mid)) {
                top=mid;
            } else {
                bottom=mid-1;
            }
        }
        int areabottom=checkrow(image,bottom)?bottom:top;
        int left=0,right=y;
        while (left<right) {
            mid=left+(right-left)/2;
            if (checkcol(image,mid)) {
                right=mid;
            } else {
                left=mid+1;
            }
        }
        int arealeft=left;
        left=y;right=cols-1;
        while (left<right-1) {
            mid=left+(right-left)/2;
            if (checkcol(image,mid)) {
                left=mid;
            } else {
                right=mid-1;
            }
        }
        int arearight=checkcol(image,right)?right:left;
        return (areabottom-areatop+1)*(arearight-arealeft+1);
        // Write your solution here
    }
    public int search21(int[] array, int target) { // 21
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (array[0]>target && array[len-1]<target) {return -1;}
        int left=0,right=len-1;
        while (left<=right) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                return mid;
            }
            if (array[mid]>=array[left]) {
                if (array[mid]>target && target>=array[left]) {
                    right=mid-1;
                } else {
                    left=mid+1;
                }
            } else { // array[mid]<array[left]
                if (array[mid]<target && target<=array[right]) {
                    left=mid+1;
                } else {
                    right=mid-1;
                }
            }
        }
        return -1;
        // Write your solution here
    }
    public int search1(int[] array, int target) { // 21
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (array[0]>target && array[len-1]<target) {return -1;}
        if (array[0]<array[len-1]) {
            return binarySearch1(array,target,0,len-1);
        } else {
            return helper1(array,target,0,len-1);
        }
        // Write your solution here
    }
    private int helper1(int[] array, int target, int left, int right) {
        if (left>right || left==right && array[left]!=target) {return -1;}
        if (array[left]==target) {return left;}
        int mid=left+(right-left)/2;
        if (array[left]<=target) {
            if (array[mid]>=target) {
                return binarySearch1(array,target,left,mid);
            } else { // array[mid]<target
                return Math.max(helper1(array,target,left,mid),helper1(array,target,mid+1,right));
            }
        } else { // array[left]>target, target in smaller subarray
            if (array[mid]<=target) {
                return binarySearch1(array,target,mid,right);
            } else { // array[mid]>target
                return Math.max(helper1(array,target,left,mid),helper1(array,target,mid+1,right));
            }
        }
    }
    private int binarySearch1(int[] array, int target, int left, int right) {
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                return mid;
            } else if (array[mid]<target) {
                left=mid+1;
            } else { // array[mid]>target
                right=mid-1;
            }
        }
        return array[left]==target?left:-1;
    }
    public int search22(int[] array, int target) { // 22
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (array[0]>target && array[len-1]<target) {return -1;}
        int left=0,right=len-1;
        while (left<right-1) {
            int mid = left+(right-left)/2;
            if (array[left]==target) {
                return left;
            } else if (array[mid]==target) {
                right=mid;
            } else if (array[mid]==array[left] && array[mid]==array[right]) {
                left++;right--;
            } else if (array[mid]<target) {
                if (array[left]<array[right] || array[left]>target || array[mid]>=array[left] && array[mid]>array[right]) { // sorted or mid and target are in smaller subarray or array[right]<=array[left]<=array[mid]<target
                    left=mid+1;
                } else if (array[mid]<array[left]) { // target is in larger subarray or whole array and mid is in smaller subarray
                    right=mid-1;
                }
            } else if (array[mid]>target) {
                if (array[left]<array[right] || array[left]<target || array[mid]<=array[right] && array[mid]<array[left]) { // sorted or target and mid are in bigger subarray or target<array[mid]<=array[right]<=array[left]
                    right=mid-1;
                } else if (array[mid]>=array[left]) { // target is in smaller subarray or whole array and mid is in larger subarray
                    left=mid+1;
                }
            }
            //If we know for sure right side is sorted or left side is unsorted
            // if (array[mid] < array[right] || array[mid] < array[left]) { // mid in smaller subarray
            //   if (target > array[mid] && target <= array[right]) { // in right of mid
            //     left = mid + 1;
            //   } else {
            //     right = mid - 1;
            //   }
            //If we know for sure left side is sorted or right side is unsorted
            // } else if (array[mid] > array[left] || array[mid] > array[right]) { // mid in larger subarray
            //   if (target < array[mid] && target >= array[left]) { // in left of mid
            //     right = mid - 1;
            //   } else {
            //     left = mid + 1;
            //   }
            //If we get here, that means array[left] == array[mid] == array[right], then shifting out
            //any of the two sides won't change the result but can help remove duplicate from
            //consideration, here we just use right-- but left++ works too
            // } else {
            //   right--;
            // }
        }
        return array[left]==target?left:(array[right]==target?right:-1);
        // Write your solution here
    }
    public int shiftPosition(int[] array) { // 23
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        int left=0,right=len-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]<array[right]) {
                right=mid;
            } else { // array[mid]>array[right]
                left=mid+1;
            }
        }
        return left;
        // Write your solution here
    }
    public int[] kSmallest(int[] array, int k) { // 25
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        if (k<=0 || k>len) {return new int[0];}
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k,new Comparator<Integer>(){
            public int compare (Integer a, Integer b) {
                return b.compareTo(a);
            }
        });
        for (int i=0;i<len;i++) {
            if (i<k) {
                pq.offer(array[i]);
            } else if (array[i]<pq.peek()) {
                pq.poll();
                pq.offer(array[i]);
            }
        }
        int[] result = new int[k];
        for (int i=k-1;i>=0;i--) {
            result[i]=pq.poll();
        }
        return result;
        // Write your solution here
    }
    public int[] kSmallest2(int[] array, int k) { // 25
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        if (k<=0 || k>len) {return new int[0];}
        quickSelect(array,0,len-1,k-1);
        int[] result = Arrays.copyOf(array,k);
        Arrays.sort(result);
        return result;
        // Write your solution here
    }
    private void quickSelect(int[] array, int left, int right, int target) {
        int mid=partition(array,left,right);
        if (mid==target) {
            return;
        } else if (target<mid) {
            quickSelect(array,left,mid-1,target);
        } else { // target>mid
            quickSelect(array,mid+1,right,target);
        }
    }
    private int partition(int[] array, int left, int right) {
        int pivot=array[right];
        int start=left,end=right-1;
        while (start<=end) {
            if (array[start]<pivot) {
                start++;
            } else if (array[end]>=pivot) {
                end--;
            } else {
                swap(array,start++,end--);
            }
        }
        swap(array,start,right);
        return start;
    }
    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a]=array[b];
        array[b]=tmp;
    }
    /*
    436. Kth Largest Element in an Array
    Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
    For example,
    Given [3,2,1,5,6,4] and k = 2, return 5.
    Note:
    You may assume k is always valid, 1 ≤ k ≤ array's length.
     */
    public int findKthLargest(int[] nums, int k) { // 436
        if (nums==null || nums.length==0) {return -1;}
        int len=nums.length;
        if (k<=0 || k>len) {return -1;}
        if (len==1) {return nums[0];}
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i=0;i<len;i++) {
            if (i<k) {
                pq.offer(nums[i]);
            } else {
                if (nums[i]>pq.peek()) {
                    pq.poll();
                    pq.offer(nums[i]);
                }
            }
        }
        return pq.peek();
        // Write your solution here
    }
    public int findKthLargest2(int[] nums, int k) { // 436
        if (nums==null || nums.length==0) {return -1;}
        int len=nums.length;
        if (k<=0 || k>len) {return -1;}
        if (len==1) {return nums[0];}
        return kthLargest(nums,0,len-1,k);
        // Write your solution here
    }
    private int kthLargest(int[] arr, int low, int high, int k) {
        int partition = partition436(arr, low, high);
        int larger = high-partition+1;
        if (larger==k) {
            return arr[partition];
        } else if (larger>k) {
            return kthLargest(arr,partition+1,high,k);
        } else { // larger<k
            return kthLargest(arr,low,partition-1,k-larger);
        }
    }
    private int partition436(int[] arr, int low, int high){
        int pivot = arr[high], pivotloc = low;
        for (int i = low; i <= high; i++) {
            if (arr[i] < pivot) {
                swap(arr,i,pivotloc++);
            }
        }
        swap(arr,high,pivotloc);
        return pivotloc;
    }
//    private void swap(int[] array, int a, int b) {
//        int temp = array[a];
//        array[a]=array[b];
//        array[b]=temp;
//    }

    static class Cell1{
        int row,col,val;
        Cell1(int row, int col, int val) {
            this.row=row;
            this.col=col;
            this.val=val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) { // 26
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {return -1;}
        int rows=matrix.length,cols=matrix[0].length;
        PriorityQueue<Cell1> pq = new PriorityQueue<Cell1>(k,new Comparator<Cell1>(){
            public int compare(Cell1 a, Cell1 b) {
                return Integer.valueOf(a.val).compareTo(Integer.valueOf(b.val));
            }
        });
        pq.offer(new Cell1(0,0,matrix[0][0]));
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        while (--k>0) {
            Cell1 cur = pq.poll();
            String temp = String.valueOf(cur.row)+","+String.valueOf(cur.col+1);
            if (cur.col<cols-1 && !visited.contains(temp)) {
                pq.offer(new Cell1(cur.row,cur.col+1,matrix[cur.row][cur.col+1]));
                visited.add(temp);
            }
            temp = String.valueOf(cur.row+1)+","+String.valueOf(cur.col);
            if (cur.row<rows-1 && !visited.contains(temp)) {
                pq.offer(new Cell1(cur.row+1,cur.col,matrix[cur.row+1][cur.col]));
                visited.add(temp);
            }
        }
        return pq.poll().val;
        // Write your solution here
    }
    static class Cell2 {
        int indexa,indexb,value;
        Cell2(int indexa, int indexb, int value) {
            this.indexa=indexa;
            this.indexb=indexb;
            this.value=value;
        }
    }
    public int kthSum(int[] A, int[] B, int k) { // 27
        if (A==null || B==null || A.length==0 || B.length==0) {
            return Integer.MIN_VALUE;
        }
        PriorityQueue<Cell2> pq = new PriorityQueue<>(k,new Comparator<Cell2>(){
            @Override
            public int compare (Cell2 c, Cell2 d) {
                return Integer.valueOf(c.value).compareTo(Integer.valueOf(d.value));
            }
        });
        int la=A.length,lb=B.length;
        if (k<=0 || k>la*lb) {return Integer.MIN_VALUE;}
        int ia=0,ib=0;
        pq.offer(new Cell2(ia,ib,A[ia]+B[ib]));
        Set<String> visited = new HashSet<>();
        String temp="0,0";
        visited.add(temp);
        while (--k>0) {
            Cell2 cur = pq.poll();
            ia=cur.indexa;
            ib=cur.indexb;
            temp = String.valueOf(ia+1)+","+String.valueOf(ib);
            if (ia<la-1 && !visited.contains(temp)) {
                pq.offer(new Cell2(ia+1,ib,A[ia+1]+B[ib]));
                visited.add(temp);
            }
            temp = String.valueOf(ia)+","+String.valueOf(ib+1);
            if (ib<lb-1 && !visited.contains(temp)) {
                pq.offer(new Cell2(ia,ib+1,A[ia]+B[ib+1]));
                visited.add(temp);
            }
        }
        return pq.poll().value;
        // Write your solution here
    }
    public int[] largestAndSmallest(int[] array) { // 119
        if (array==null || array.length==0) {return new int[2];}
        int len=array.length;
        for (int i=0;i<len/2;i++) {
            if (array[i]>array[len-1-i]) {
                swap(array,i,len-1-i);
            }
        }
        int max=array[len-1],min=array[0];
        for (int i=0;i<=len/2;i++) {
            if (array[i]<min) {min=array[i];}
            if (array[len-1-i]>max) {max=array[len-1-i];}
        }
        return new int[]{max,min};
        // Write your solution here
    }
//    private void swap(int[] array, int left, int right) {
//        int tmp = array[left];
//        array[left]=array[right];
//        array[right]=tmp;
//    }
    static class Element {
        int value;
        List<Integer> comparedValues;
        Element(int value) {
            this.value=value;
            this.comparedValues = new ArrayList<>();
        }
    }
    public int[] largestAndSecond(int[] array) { // 120
        if (array==null || array.length<2) {return null;}
        Element[] record = convert(array);
        int len = array.length;
        while (len>1) {
            compareAndSwap(record,len);
            len=(len+1)/2;
        }
        return new int[]{record[0].value,largest(record[0].comparedValues)};
    }
    private Element[] convert(int[] array) {
        int len=array.length;
        Element[] record = new Element[len];
        for (int i=0;i<len;i++) {
            record[i] = new Element(array[i]);
        }
        return record;
    }
    private void compareAndSwap(Element[] record, int len) {
        for (int i=0;i<len/2;i++) {
            if (record[i].value<record[len-1-i].value) {
                swap(record,i,len-1-i);
            }
            record[i].comparedValues.add(record[len-1-i].value);
        }
    }
    private void swap(Element[] helper, int left, int right) {
        Element tmp=helper[left];
        helper[left]=helper[right];
        helper[right]=tmp;
    }
    private int largest(List<Integer> list) {
        int max=list.get(0);
        for (int num:list) {
            max=Math.max(max,num);
        }
        return max;
    }
    /*
    236. Search Insert Position
    Given a sorted array and a target value, return the index where it would be if it were inserted in order.
    Assumptions
    If there are multiple elements with value same as target, we should insert the target before the first existing element.
     */
    public int searchInsert(int[] input, int target) { // 236
        if (input==null) {return -1;}
        if (input.length==0) {return 0;}
        int left=0, right=input.length-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (input[mid]<target) {
                left=mid+1;
            } else { // input[mid]>=target
                right=mid;
            }
        }
        if (target<=input[left]) {
            return left;
        } else {
            return left+1;
        }
        // Write your solution here
    }
    /*
    483. Nth Largest Value
    For this problem, you will write a program that prints the Nth largest value in a fixed sized array of integers. To make things simple, N will be 3 and the array will always be have 10 decimal integer values.
    Input: 10 decimal integers whose values are between 1 and 1000 inclusive
    Output: the 3rd largest value of the corresponding 10 integers.
     */
    public int getThirdLargest(int[] array) { // 483
        if (array==null || array.length<3) {return -1;}
        int[] result = new int[3];
        for (int i=0;i<3;i++) {
            result[i]=array[i]*(-1);
        }
        Arrays.sort(result);
        for (int i=0;i<3;i++) {
            result[i]=result[i]*(-1);
        }
        for (int i=3;i<array.length;i++) {
            int cur=array[i];
            int ind=binarySearch(result,cur);
            if (ind>2) {continue;}
            for (int j=2;j>ind;j--) {
                result[j]=result[j-1];
            }
            result[ind]=cur;
        }
        return result[2];
        // Write your solution here
    }
    private int binarySearch(int[] array, int target) {
        int left=0,right=array.length-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]<=target) {
                right=mid;
            } else {
                left=mid+1;
            }
        }
        return array[left]<=target?left:array.length;
    }
    /*
    568. Find Single Element In Sorted Array
    Given a sorted integer array. Each integer appears twice except one single element appearing only once. Find this single integer.
    Example 1:
    Input: [1,1,2,2,3,3,4,5,5]
    Output: 4
    Example 2:
    Input: [5,5,6,7,7,8,8]
    Output: 6
    Assumption:The input array is not null and not empty.
    Note:Try to do it in Olog(n) time.
     */
    public int getSingleElement(int[] nums) { // 568
        if (nums==null || nums.length==0) {return -1;}
        int len=nums.length;
        if (len%2==0) {return -1;}
        if (len==1) {return nums[0];}
        if (nums[1]!=nums[0]) {return nums[0];}
        int left=1,right=len/2-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            int trueMid=mid*2;
            if (nums[trueMid]==nums[trueMid+1]) {
                left=mid+1;
            } else { // ==mid
                right=mid;
            }
        }
        return nums[right*2+(nums[right*2+1]==nums[right*2]?2:0)];
        // Write your solution here
    }
    /*
    636. Smallest Element Larger than Target
    Given a target integer T and an integer array A sorted in ascending order, find the index of the smallest element in A that is larger than T or return -1 if there is no such index.
    Assumptions
    There can be duplicate elements in the array.
    Examples
    A = {1, 2, 3}, T = 1, return 1
    A = {1, 2, 3}, T = 3, return -1
    A = {1, 2, 2, 2, 3}, T = 1, return 1
    Corner Cases
    What if A is null or A of zero length? We should return -1 in this case.
     */
    public int smallestElementLargerThanTarget(int[] array, int target) { // 636
        if (array==null || array.length==0) {return -1;}
        int left=0,right=array.length-1;
        while (left<right) {
            int mid=left+(right-left)/2;
            if (array[mid]<=target) {
                left=mid+1;
            } else { // array[mid]>target
                right=mid;
            }
        } // end while
        return array[left]>target?left:-1;
        // Write your solution here
    }
    /*
    659. First Bad Version
    Suppose there is a version control interface contains n versions of product [1,2,3,....n] (n >= 1).
    Also there is an API called isBadVersion(int n) in which input is version number and output is boolean representing that whether the version is bad or not. Versions after the first bad version are all bad. Versions before the first bad version are all good.
    Write a new API called findFirstBadVersion(int n) where n is the total number of versions that returns the version number of the first bad one. You can call isBadVersion(int n) in your code.
    Please try to minimize the total number of isBadVersion() calls.
    Example:
    Suppose n = 5 and version 3 is the first bad version.
    isBadVersion(1) = false;
    isBadVersion(2) = false;
    isBadVersion(3) = true;
    So your api findFirstBadVersion(5) should return 3.
     */
//    class Solution extends VersionControl {
//        public int findFirstBadVersion(int n) {
//            int left=1,right=n;
//            while (left<right) {
//                int mid=left+(right-left)/2;
//                if (isBadVersion(mid)) {
//                    right=mid; // could be first bad
//                } else {
//                    left=mid+1;
//                }
//            }
//            return isBadVersion(left)?left:-1;
//            // write your solution here
//        }
//    }
    public static void main(String[] args) {
        Search solution = new Search();
//        int[] input=new int[] {3,5,1,2,4,8};
//        System.out.println(solution.binarySearch(input,1));
//        int[] a = new int[] {1,4,5,5,8,12,12,12};
//        int[] b = new int[] {2,2,3,7,9,9,9};
//        System.out.println(solution.kth(a,b,4));
//        int[][] matrix= { {1, 2, 3}, {4, 5, 7}, {8, 9, 10} };
//        int[] result=solution.matrixSearch(matrix,6);
//        System.out.println(result[0]+","+result[1]);
//        solution.printArray(solution.matrixSearch2(matrix,target));
//        int[] input=new int[] {1,2,3,4};
//        System.out.println(solution.localMinimum(input));
//        int[][] matrix=new int[][]{{1,2,3,4,10,11,100,200},{5,6,7,8,9,10,10,11,100,101},{2,3,5,8,9,12,99,100}};
//        System.out.println(solution.search(matrix));
//        int[] input = new int[]{4,1,1,1,1,1,3,3};
//        System.out.println(solution.search(input,1));
//        int[] array = new int[]{3,1,5,2,4};
//        solution.printArray(solution.kSmallest(array,5));
//        int[] input = new int[]{1,2};
//        System.out.println(Arrays.toString(solution.range(input,0)));
/*        int[][] matrix = new int[][] {
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};*/
//        solution.printArray(matrix);
//        System.out.println(solution.minArea(matrix,6,29));
    }
}
