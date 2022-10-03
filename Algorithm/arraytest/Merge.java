package com.laioffer.Algorithm.arraytest;
import java.util.*;

public class Merge {
    /*
    149. Merge Two Sorted Array
    Merge given amount of numbers from two sorted arrays.
    Note that given amount of numbers are not larger than the length of the respective arrays.
    Input: [1, 2, 3], 3, [2, 4, 6], 1
    Output: [1,2, 2, 3]
     */
    public int[] merge(int[] A, int m, int[] B, int n) { // 149
        if (A==null || B==null || m<0 || n<0) {return new int[0];}
        int la=A.length,lb=B.length;
        if (m>la || n>lb) {return new int[0];}
        if (n==0) {
            return Arrays.copyOfRange(A,0,m);
        }
        if (m==0) {
            return Arrays.copyOfRange(B,0,n);
        }
        int[] result = new int[m+n];
        int ia=0,ib=0,ir=0;
        while (ia<m || ib<n) {
            if (ib==n || (ia<m && A[ia]<=B[ib])) {
                result[ir++]=A[ia++];
            } else { //if (ia==m || (ib<n && A[ia]>B[ib])) {
                result[ir++]=B[ib++];
            }
        }
        return result;
        // Write your solution here
    }
    /*
    547. Merge Two Sorted Arrays
    Given two sorted arrays, merge them into one sorted array.
    Assumption: the two arrays are not null or empty.
    Example:
    array1 = {3,7}
    array2 = {1,6,8,9}
    answer = {1,3,6,7,8,9}
     */
    public int[] merge(int[] one, int[] two) { // 547
        int[] out = new int[one.length+two.length];
        int ind1=0, ind2=0;
        while (ind1<one.length || ind2<two.length) {
            if (ind1==one.length) {
                out[ind1+ind2]=two[ind2++];
            } else if (ind2==two.length) {
                out[ind1+ind2]=one[ind1++];
            } else if (one[ind1]<=two[ind2]) {
                out[ind1+ind2]=one[ind1++];
            } else {
                out[ind1+ind2]=two[ind2++];
            }
        }
        return out;
        // Write your solution here
    }
    /*
    133. Merge K Sorted Array
     */
    // TC: O(knlogk)
    // SC: O(3k)
    public int[] merge(int[][] arrayOfArrays) { // 133
        if (arrayOfArrays==null) {return new int[0];}
        PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(new MyComparator());
        int num=arrayOfArrays.length,tot=0;
        for (int i=0;i<num;i++) {
            int[] array = arrayOfArrays[i];
            if (array.length!=0) {
                tot+=array.length;
                minHeap.offer(new Entry(i,0,array[0]));
            }
        }
        int[] result = new int[tot];
        int ir=0;
        while (!minHeap.isEmpty()) {
            Entry cur = minHeap.poll();
            result[ir++]=cur.value;
            if (cur.dim2<arrayOfArrays[cur.dim1].length-1) {
                cur.value=arrayOfArrays[cur.dim1][++cur.dim2];
                minHeap.offer(cur);
            }
        }
        return result;
        // Write your solution here
    }
    static class MyComparator implements Comparator<Entry> {
        @Override
        public int compare(Entry e1, Entry e2) {
            return (Integer.valueOf(e1.value)).compareTo(Integer.valueOf(e2.value));
        }
    }
    static class Entry{
        int dim1;
        int dim2;
        int value;
        Entry(int dim1,int dim2,int value) {
            this.dim1=dim1;
            this.dim2=dim2;
            this.value=value;
        }
    }
    class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }
    // TC: O(n+2n+3n+...+kn)=O((k^2/2+k/2)*n)=O(k^2n)
    // SC: O(1)
    public int[] merge2(int[][] arrayOfArrays) {
        if (arrayOfArrays==null) {return new int[0];}
        int num=arrayOfArrays.length,tot=0;
        for (int i=0;i<num;i++) {
            tot+=arrayOfArrays[i].length;
        }
        int[] result = new int[tot];
        int[] ar0 = arrayOfArrays[0];
        int ir=0;
        for (int i=0;i<ar0.length;i++) {
            result[ir++]=ar0[i];
        }
        for (int i=1;i<num;i++) {
            helper(result,arrayOfArrays[i],ir);
            ir+=arrayOfArrays[i].length;
        }
        return result;
    }
    private void helper(int[] one, int[] two, int l1) {
        int l2=two.length;
        int i1=l1-1, i2=l2-1, ir=l1+l2-1;
        while (i1>=0 || i2>=0) {
            if (i1==-1) {
                one[ir--]=two[i2--];
            } else if (i2==-1) {
                one[ir--]=one[i1--];
            } else if (one[i1]<=two[i2]) {
                one[ir--]=two[i2--];
            } else { // one[i1]>two[i2]
                one[ir--]=one[i1--];
            }
        }
        // Write your solution here
    }
    /*
    134. Merge K Sorted Lists
     */
    public ListNode merge1(List<ListNode> listOfLists) { // 134
        if (listOfLists==null || listOfLists.size()==0) {return null;}
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(new MyComparator134());
        for (ListNode node : listOfLists) {
            if (node!=null) {
                minHeap.offer(node); // TC: O(klogk) SC: O(k)
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!minHeap.isEmpty()) {
            cur.next=minHeap.poll(); // TC: O(nk*logk)
            if (cur.next.next!=null) {
                minHeap.offer(cur.next.next); // TC: O(nk*logk)
            }
            cur=cur.next;
        }
        return dummy.next;
        // Write your solution here/.
    }
    static class MyComparator134 implements Comparator<ListNode> {
        @Override
        public int compare (ListNode o1, ListNode o2) {
            return (Integer.valueOf(o1.value)).compareTo(Integer.valueOf(o2.value));
        }
    }
    /*
    iterative reduction
    */
    public ListNode merge2(List<ListNode> listOfLists) { // 134
        if (listOfLists==null || listOfLists.size()==0) {return null;}
        int num = listOfLists.size();
        ListNode l1 = listOfLists.get(0);
        int i=1;
        while (l1==null && i<num) {l1=listOfLists.get(i++);}
        for (;i<num;i++) {
            ListNode l2 = listOfLists.get(i);
            if (l2==null) {continue;}
            l1=merge(l1,l2);
        }
        return l1;
    }
    // TC: O(2n+3n+4n+...+kn) = O((k^2/2+k)*n), SC: O(1)
    private ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (one!=null && two!=null) {
            if (one.value<=two.value) {
                cur.next=one;
                one=one.next;
            } else {
                cur.next=two;
                two=two.next;
            }
            cur=cur.next;
        }
        cur.next=one==null?two:one;
        return dummy.next;
    }

    public ListNode merge3(List<ListNode> listOfLists) { // 134
        if (listOfLists==null || listOfLists.size()==0) {return null;}
        int num = listOfLists.size();
        if (num==1) {return listOfLists.get(0);}
        ListNode l1=null,l2=null,output=null;
        int index=0;
        while (true) {
            int index1=index;
            do {
                l1=listOfLists.get(index++%num);
            } while (l1==null && index!=index1+num);
            if (l1==null) {break;} // if all lists are null
            index1=index-1;
            do {
                l2=listOfLists.get(index++%num);
            } while (l2==null && index!=index1+num);
            int index2=index-1;
            if (l2==null) {break;} // termination condition for return, only 1 list left
            output = merge(l1,l2); // output points to either head of l1 or head of l2
            if (output==l1) {
                listOfLists.set(index2%num,null);
            } else {
                listOfLists.set(index1%num,null);
            }
            //index%=num;
        }
        return output;
    }
    // O(k/2*2n+k/4*4n+k/8*8n+...)
    // TC: O(knlogk+klogk), SC: O(1)

    public ListNode merge(List<ListNode> listOfLists) { // 134
        if (listOfLists==null || listOfLists.size()==0) {return null;}
        int num = listOfLists.size();
        int index=0,step=1;
        while (step<num) {
            listOfLists.set(index,index+step<num?merge(listOfLists.get(index),listOfLists.get(index+step)):listOfLists.get(index));
            index+=step*2;
            if (index>=num) {
                step*=2;
                index=0;
            }
        }
        return listOfLists.get(0);
    }
    // TC: O(knlogk+??) SC: O(1)
    public static void main(String[] args) {
        Merge solution = new Merge();
        int[] a = new int[] {2,2,2,-1,-1,-1,-1};
        int[] b = new int[] {1,1,1,1};
        System.out.println(Arrays.toString(solution.merge(a,3,b,4)));
    }
}
