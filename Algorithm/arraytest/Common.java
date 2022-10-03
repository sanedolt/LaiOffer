package com.laioffer.Algorithm.arraytest;
import java.util.*;

public class Common {
    public List<Integer> common70(List<Integer> a, List<Integer> b) { // 70
        if (a==null || b==null) {return null;}
        int la=a.size(),lb=b.size();
        Set<Integer> setA = new HashSet<>();
        for (int i=0;i<la;i++) {
            setA.add(a.get(i));
        }
        List<Integer> result = new ArrayList<>();
        for (int i=0;i<lb;i++) {
            if (setA.contains(b.get(i))) {
                result.add(b.get(i));
            }
        }
        int lr= result.size();
        if (lr==0) {return result;}
        int[] helper = new int[lr];
        mergeSort(result,0,lr-1,helper);
        return result;
        // Write your solution here
    }
    public List<Integer> common71(List<Integer> A, List<Integer> B) { // 71
        if (A==null || B==null) {return null;}
        int la=A.size(),lb=B.size();
        List<Integer> result = new ArrayList<>();
        if (la==0 || lb==0) {return result;}
        int ll = la>=lb?la:lb;
        int[] helper = new int[ll];
        mergeSort(A,0,la-1,helper);
        mergeSort(B,0,lb-1,helper);
        int ia=0,ib=0;
        while (ia<la && ib<lb) {
            if (A.get(ia).equals(B.get(ib))) {
                result.add(A.get(ia));
                ia++;ib++;
            } else if (A.get(ia)<B.get(ib)) {
                ia++;
            } else { // A.get(ia)>B.get(ib)
                ib++;
            }
        }
        return result;
        // Write your solution here
    }
    private void mergeSort(List<Integer> array, int left, int right, int[] helper) {
        if (left==right) {return;}
        int mid = left+(right-left)/2;
        mergeSort(array,left,mid,helper);
        mergeSort(array,mid+1,right,helper);
        merge(array,left,mid,right,helper);
    }
    private void merge(List<Integer> array, int left, int mid, int right, int[] helper) {
        for (int i=left;i<=right;i++) {
            helper[i]=array.get(i);
        }
        int arrIndex=left,leftIndex=left,rightIndex=mid+1;
        while (leftIndex<=mid && rightIndex<=right) {
            if (helper[leftIndex]<=helper[rightIndex]) {
                array.set(arrIndex++,helper[leftIndex++]);
            } else {
                array.set(arrIndex++,helper[rightIndex++]);
            }
        }
        while (leftIndex<=mid) {
            array.set(arrIndex++,helper[leftIndex++]);
        }
    }
    public List<Integer> common72(List<Integer> A, List<Integer> B) { // 72
        if (A==null || B==null) {return null;}
        List<Integer> result = new ArrayList<>();
        int la=A.size(),lb=B.size();
        if (la==0 || lb==0) {return result;}
        int ia=0,ib=0;
        while (ia<la && ib<lb) {
            if (A.get(ia).equals(B.get(ib))) {
                result.add(A.get(ia));
                ia++;ib++;
            } else if (A.get(ia)<B.get(ib)) {
                ia++;
            } else {
                ib++;
            }
        }
        return result;
        // Write your solution here
    }
    /*
    171. Common Elements In Three Sorted Array
    Find all common elements in 3 sorted arrays.
    Assumptions
    The 3 given sorted arrays are not null
    There could be duplicate elements in each of the arrays
    Examples
    A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]
     */
    public List<Integer> common(int[] a, int[] b, int[] c) { // 171
        if (a==null || b==null || c==null) {return null;}
        int la=a.length,lb=b.length,lc=c.length;
        List<Integer> result = new ArrayList<Integer>();
        int ia=0,ib=0,ic=0;
        while (ia<la && ib<lb && ic<lc) {
            int va=a[ia],vb=b[ib],vc=c[ic];
            if (va==vb && va==vc) {
                result.add(va);
                ia++;ib++;ic++;
            } else {
                if (va<vb || va<vc) {ia++;}
                if (vb<va || vb<vc) {ib++;}
                if (vc<va || vc<vb) {ic++;}
            }
        } // end while
        return result;
        // Write your solution here
    }
    /*
    245. Longest Common Prefix
    Write a function to find the longest common prefix string amongst an array of strings.
     */
    public String longestCommonPrefix(String[] strs) { // 245
        if (strs==null || strs.length==0) {return "";}
        int num=strs.length;
        int l1 = strs[0].length();
        for (int i=0;i<l1;i++) {
            char c1 = strs[0].charAt(i);
            for (int j=1;j<num;j++) {
                int lj = strs[j].length();
                if (i>=lj || strs[j].charAt(i)!=c1) {return strs[0].substring(0,i);}
            }
        }
        return strs[0];
        // Write your solution here
    }
    /*
    324. Different Elements In Two Sorted Arrays
    Given two sorted arrays a and b containing only integers, return two list of elements: the elements only in a but not in b, and the elements only in b but not in a.
    Do it in one pass.
    Assumptions:
    The two given arrays are not null.
    Examples:
    a = {1, 2, 2, 3, 4, 5}
    b = {2, 2, 2, 4, 4, 6}
    The returned two lists are:
    [
      [1, 3, 5],
      [2, 4, 6]  // there are two 2s in a, so there is one 2 in b not in a
    ]
     */
    public int[][] diff(int[] a, int[] b) { // 324
        if (a==null || b==null) {return null;}
        int la=a.length,lb=b.length;
        int slowa=0,slowb=0;
        int fasta=0,fastb=0;
        while (fasta<la || fastb<lb) {
            if (fasta<la && fastb<lb && a[fasta]==b[fastb]) {
                fasta++;
                fastb++;
            } else if (fasta<la && (fastb==lb || (fastb<lb && a[fasta]<b[fastb]))) {
                a[slowa++]=a[fasta++];
            } else { // fastb<lb && (fasta==la || (fasta<la && a[fasta]>b[fastb])))
                b[slowb++]=b[fastb++];
            }
        }
        int[][] result=new int[2][];
        result[0]=Arrays.copyOfRange(a,0,slowa);
        result[1]=Arrays.copyOfRange(b,0,slowb);
        return result;
        // Write your solution here
    }
    /*
    403. Search Common Element in Sorted Matrix
    Given a 2D integer matrix, where every row is sorted in ascending order. How to find a common element in all rows. If there is no common element, then returns -1.
    Example
    matrix = { { 1, 2, 3, 4 }, { 4, 5, 6, 7 }, { 2, 3, 4, 8 } }
    the common element is 4.
     */
    public int search403(int[][] matrix) { // 403
        if (matrix==null || matrix.length<=1) {return -1;}
        int rows=matrix.length;
        int r0=matrix[0].length-1; // last valid index in row 0
        for (int i=1;i<rows;i++) {
            int s0=0,f0=0; // slow and fast pointers of row 0
            int fi=0; // fast point of current/i row
            while (f0<=r0 && fi<matrix[i].length) {
                if (matrix[0][f0]==matrix[i][fi]) {
                    matrix[0][s0++]=matrix[0][f0++];
                } else if (matrix[0][f0]<matrix[i][fi]) {
                    f0++;
                } else { // matrix[0][f0]>matrix[i][fi]
                    fi++;
                }
            }
            r0=s0-1;
        }
        return r0>=0?matrix[0][0]:-1;
        // Write your solution here
    }
    /*
    520. Intersection of Two Arrays
    Given two arrays, write a function to compute their intersection.
    Example:
    Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
    Note:
    Each element in the result should appear as many times as it shows in both arrays.
    The result can be in any order.
     */
    public int[] intersect(int[] nums1, int[] nums2) { // 520
        if (nums1==null || nums2==null) {return null;}
        int l1=nums1.length,l2=nums2.length;
        if (l1==0 || l2==0) {return new int[0];}
        quickSort(nums1,0,nums1.length-1);
        quickSort(nums2,0,nums2.length-1);
        List<Integer> common = new ArrayList<>();
        int i1=0,i2=0;
        while (i1<l1 && i2<l2) {
            if (nums1[i1]==nums2[i2]) {
                common.add(nums1[i1]);
                i1++;
                i2++;
            } else if (nums1[i1]>nums2[i2]) {
                i2++;
            } else {
                i1++;
            }
        }
        int[] result = new int[common.size()];
        int index=0;
        for (int cm : common) {
            result[index++]=cm;
        }
        return result;
        // Write your solution here
    }
    private void quickSort(int[] array, int left, int right) {
        if (left>=right) {return;}
        int pivotPos = partition (array,left,right);
        quickSort(array,left,pivotPos-1);
        quickSort(array,pivotPos+1,right);
    }
    private int partition (int[] array, int left, int right) {
        Random random = new Random();
        int pivotIndex = left+(int) random.nextInt(right-left+1);
        int pivot=array[pivotIndex];
        swap(array,pivotIndex,right);
        int leftBound=left,rightBound=right-1;
        while (leftBound<=rightBound) {
            if (array[leftBound]<pivot) {
                leftBound++;
            } else if (array[rightBound]>pivot) {
                rightBound--;
            } else {
                swap(array,leftBound++,rightBound--);
            }
        }
        swap(array,leftBound,right);
        return leftBound;
    }
    private void swap (int[] array, int left, int right) {
        int temp=array[left];
        array[left]=array[right];
        array[right]=temp;
    }
    /*
    579. Find Common Elements In Two Lists with Minimum Index Sum
    Mary and Max are looking for job opportunities and practicing on Laicode together. Both of them have a list of dream companies. The lists are represented by two String array. Please help them to find their common interest companies so that the sum of the index is as small as possible. The output is an array of String and should be sorted in alphabetical order.
    Example 1:
    Input: list1 = ["amazon", "microsoft", "linkedin", "google"]; list2 = ["stark", "intel", "amd", "amazon"]
    Output: ["amazon"]
    Explanation: There is only one common company ["amazon"] in the two lists.
    Example 2:
    Input: list1 = ["amazon", "microsoft", "linkedin", "google"]; list2 = ["stark", "intel", "amd", "amazon","microsoft"]
    Output: ["amazon"]
    Explanation: There are two common companies in the two lists, "amazon" and "microsoft". Sum of index is 3 for "amazon" and 5 for "microsoft", so that the output is  ["amazon"].
    Example 3:
    Input: list1 = ["amazon", "microsoft", "linkedin", "google"]; list2 = ["stark", "intel", "microsoft", "amazon"]
    Output: ["amazon","microsoft"]
    Explanation:There are two common companies in the two lists, "amazon" and "microsoft". Sum of index of both of them is 3, so return both of them in alphabetical order.
    Assumptions:
    There is no duplicate in either list.
    The given lists are unordered.
    The index is in range between 0 and length of list - 1.
     */
    public List<String> getCommonInterest(String[] array1, String[] array2) { // 579
        if (array1==null || array2==null) {return null;}
        int l1=array1.length,l2=array2.length;
        if (l1==0 || l2==0) {return new ArrayList<>();}
        Map<String,Integer> w1 = new HashMap<>();
        for (int i=0;i<l1;i++) {
            w1.put(array1[i],i);
        }
        Map<String,Integer> w2 = new HashMap<>();
        for (int i=0;i<l2;i++) {
            w2.put(array2[i],i);
        }
        int min=l1+l2;
        for (int i=0;i<l1;i++) {
            if (!w2.containsKey(array1[i])) {
                w1.remove(array1[i]);
            } else {
                w1.put(array1[i],w1.get(array1[i])+w2.get(array1[i]));
                if (w1.get(array1[i])<min) {
                    min=w1.get(array1[i]);
                }
            }
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String,Integer> me : w1.entrySet()) {
            if (me.getValue()==min) {
                result.add(me.getKey());
            }
        }
        Collections.sort(result);
        return result;
        // Write your solution here
    }
    /*
    644. Common Elements In K Sorted Lists
    Find all common elements in K sorted lists.
    Assumptions
    The input and its elements are not null, and support fast random access.
    There could be duplicate elements in each of the lists.
    Examples
    Input = {{1, 2, 2, 3}, {2, 2, 3, 5}, {2, 2, 4}}, the common elements are {2, 2}.
     */
    public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) { // 644
        // use iterative reduction
        List<Integer> result = new ArrayList<>();
        if (input==null || input.size()==0) {return result;}
        int si = input.size();
        result=input.get(0);
        for (int i=1;i<si;i++) {
            result=helper(result,input.get(i));
        }
        return result;
        // Write your solution here
    }
    private List<Integer> helper(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>();
        int la=a.size(),lb=b.size();
        int ia=0,ib=0;
        while (ia<la && ib<lb) {
            int compare=a.get(ia).compareTo(b.get(ib));
            if (compare==0) {
                result.add(a.get(ia));
                ia++;
                ib++;
            } else if (compare<0) {
                ia++;
            } else {
                ib++;
            }
        }
        return result;
    }
    /*
    650. Common Numbers Of Two Arrays I(Array version)
    Find all numbers that appear in both of the two unsorted arrays, return the common numbers in increasing order.
    Assumptions
    Both arrays are not null.
    There are no duplicate numbers in each of the two arrays respectively.
    Exmaples
    A = {1, 2, 3}, B = {3, 1, 4}, return [1, 3]
    A = {}, B = {3, 1, 4}, return []
     */
    public List<Integer> common650(int[] a, int[] b) { // 650
        if (a==null || b==null) {return null;}
        int la = a.length, lb=b.length;
        List<Integer> result = new ArrayList<>();
        if (la==0 || lb==0) {return result;}
        Set<Integer> setA = new HashSet<>();
        for (int i=0;i<la;i++) {
            setA.add(a[i]);
        }
        for (int i=0;i<lb;i++) {
            if (setA.contains(b[i])) {
                result.add(b[i]);
            }
        }
        if (result.size()==0) {return result;}
        int[] helper = new int[result.size()];
        mergeSort650(result,0,result.size()-1,helper);
        return result;
        // Write your solution here
    }
    private void mergeSort650(List<Integer> array, int left, int right, int[] helper) {
        if (left==right) {return;}
        int mid = left+(right-left)/2;
        mergeSort650(array,left,mid,helper);
        mergeSort650(array,mid+1,right,helper);
        merge650(array,left,mid,right,helper);
    }
    private void merge650(List<Integer> array, int left, int mid, int right, int[] helper) {
        for (int i=left;i<=right;i++) {
            helper[i]=(array.get(i));
        }
        int arrIndex=left,leftIndex=left,rightIndex=mid+1;
        while (leftIndex<=mid && rightIndex<=right) {
            if (helper[leftIndex]<=helper[rightIndex]) {
                array.set(arrIndex++,helper[leftIndex++]);
            } else {
                array.set(arrIndex++,helper[rightIndex++]);
            }
        }
        while (leftIndex<=mid) {
            array.set(arrIndex++,helper[leftIndex++]);
        }
    }
    /*
    651. Common Numbers Of Two Arrays II(Array version)
    Find all numbers that appear in both of two unsorted arrays.
    Assumptions
    Both of the two arrays are not null.
    In any of the two arrays, there could be duplicate numbers.
    Examples
    A = {1, 2, 3, 2}, B = {3, 4, 2, 2, 2}, return [2, 2, 3] (there are both two 2s in A and B)
     */
    public List<Integer> common651(int[] A, int[] B) { // 651
        if (A==null || B==null) {return null;}
        int la = A.length, lb=B.length;
        List<Integer> result = new ArrayList<>();
        if (la==0 || lb==0) {return result;}
        int ll = Math.max(la,lb);
        int[] helper = new int[ll];
        mergeSort(A,0,la-1,helper);
        mergeSort(B,0,lb-1,helper);
        int ia=0,ib=0;
        while (ia<la && ib<lb) {
            if (A[ia]==B[ib]) {
                result.add(A[ia]);
                ia++;
                ib++;
            } else if (A[ia]>B[ib]) {
                ib++;
            } else { // A[ia]<B[ib]
                ia++;
            }
        }
        return result;
        // Write your solution here
    }
    private void mergeSort(int[] array, int left, int right, int[] helper) {
        if (left==right) {return;}
        int mid = left+(right-left)/2;
        mergeSort(array,left,mid,helper);
        mergeSort(array,mid+1,right,helper);
        merge(array,left,mid,right,helper);
    }
    private void merge(int[] array, int left, int mid, int right, int[] helper) {
        for (int i=left;i<=right;i++) {
            helper[i]=array[i];
        }
        int arrIndex=left,leftIndex=left,rightIndex=mid+1;
        while (leftIndex<=mid && rightIndex<=right) {
            if (helper[leftIndex]<=helper[rightIndex]) {
                array[arrIndex++]=helper[leftIndex++];
            } else {
                array[arrIndex++]=helper[rightIndex++];
            }
        }
        while (leftIndex<=mid) {
            array[arrIndex++]=helper[leftIndex++];
        }
    }
    /*
    652. Common Numbers Of Two Sorted Arrays(Array version)
    Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).
    Assumptions
    In each of the two sorted arrays, there could be duplicate numbers.
    Both two arrays are not null.
    Examples
    A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]
     */
    public List<Integer> common652(int[] A, int[] B) { // 652
        List<Integer> result = new ArrayList<>();
        if (A==null || B==null || A.length==0 || B.length==0) {return result;}
        int la = A.length, lb = B.length;
        int ia=0,ib=0;
        while (ia<la && ib<lb) {
            if (A[ia]==B[ib]) {
                result.add(A[ia]);
                ia++;
                ib++;
            } else if (A[ia]<B[ib]) {
                ia++;
            } else { // A[ia]>B[ib]
                ib++;
            }
        }
        return result;
        // Write your solution here
    }
    public static void main(String[] args) {
        Common solution = new Common();
//        List<Integer> input1 = Arrays.asList(0,2,2,2,4,6,7,7,9,9,10,12);
//        List<Integer> input2 = Arrays.asList(0,0,0,1);
//        List<Integer> input3 = Arrays.asList(1,3,5,5,6,7,9,11,11);
//        List<Integer> input4 = Arrays.asList(0,0,2);
//        List<List<Integer>> input = new ArrayList<>();
//        input.add(input1);
//        input.add(input2);
//        input.add(input3);
//        input.add(input4);
//        System.out.println(solution.commonElementsInKSortedArrays(input));
    }
}
