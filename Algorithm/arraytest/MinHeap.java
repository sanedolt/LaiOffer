package com.laioffer.Algorithm.arraytest;
import java.util.*;

public class MinHeap {
    private int[] array;
    private int size;
    public MinHeap(int[] array) {
        if (array==null || array.length==0) {
            //throw new IllegalAccessException("input array cannot be null or empty");
        }
        this.array = Arrays.copyOf(array,array.length);
        size=array.length;
        heapify();
    }
    private void heapify() {
        for (int i=size/2-1;i>=0;i--) {
            percolateDown(i);
        }
    }
    public MinHeap(int cap) {
        if (cap<=0) {
            //throw new IllegalArgumentException("capacity must be positive");
        }
        array = new int[cap];
        size=0;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size==0;
    }
    public boolean isFull() {
        return size==array.length;
    }
    private void percolateUp(int index) {
        while (index>0) {
            int parentIndex=(index-1)/2;
            if (array[parentIndex]>array[index]) {
                swap(parentIndex,index);
            } else {
                break;
            }
            index=parentIndex;
        }
    }
    private void percolateDown(int index) {
        while (index<=size/2-1) {
            int leftChildIndex = index*2+1;
            int rightChildIndex = index*2+2;
            int swapCandidate = leftChildIndex;
            if (rightChildIndex<size && array[rightChildIndex]<=array[leftChildIndex]) {
                swapCandidate = rightChildIndex;
            }
            if (array[index]>array[swapCandidate]) {
                swap(index,swapCandidate);
            } else {
                break;
            }
            index=swapCandidate;
        }
    }
    private void swap (int i, int j) {
        int tmp = array[i];
        array[i]=array[j];
        array[j]=tmp;
    }
    public int peek() {
        if (size==0) {
            //throw new NoSuchElementException("heap is empty");
        }
        return array[0];
    }
    public int poll() {
        if (size==0) {
            //throw new NoSuchElementException("heap is empty");
        }
        int result = array[0];
        array[0]=array[size-1];
        size--;
        percolateDown(0);
        return result;
    }
    public void offer (int ele) {
        if (size==array.length) {
            array=Arrays.copyOf(array,(int)(array.length*1.5));
        }
        array[size++]=ele;
        percolateUp(size-1);
    }
    public int update (int index, int ele) {
        if (index<0 || index>size-1) {
            //throw new ArrayIndexOutOfBoundsException("invalid index range");
        }
        int result = array[index];
        array[index]=ele;
        if (result>ele) {
            percolateUp(index);
        } else {
            percolateDown(index);
        }
        return result;
    }
    public int[] heapify(int[] array) { // 326
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        for (int i=len/2-1;i>=0;i--) {
            int cur=i;
            while (cur<=len/2-1) {
                int lc = cur*2+1;
                int rc = cur*2+2;
                if (rc==len) {
                    if (array[cur]>=array[lc]) {
                        swap(array,cur,lc);
                        cur=lc;
                    } else {
                        break;
                    }
                } else {
                    if (array[rc]<=array[lc] && array[rc]<=array[cur]) {
                        swap(array,rc,cur);
                        cur=rc;
                    } else if (array[lc]<=array[rc] && array[lc]<=array[cur]) {
                        swap(array,lc,cur);
                        cur=lc;
                    } else {
                        break;
                    }
                }
            }
        } // end for
        return array;
        // Write your solution here
    }
    public int[] offerHeap(int[] array, int ele) { // 363
        if (array==null || array.length==0) {return null;}
        int len=array.length;
        int index=len-1;
        array[index]=ele;
        int parent=(index-1)/2;
        while (ele<array[parent]) {
            array[index]=array[parent];
            array[parent]=ele;
            index=parent;
            parent=(index-1)/2;
        }
        return array;
        // Write your solution here
    }
    public int[] updateHeap(int[] array, int index, int ele) { // 367
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        if (index<0 || index>=len || array[index]==ele) {return array;}
        boolean dn = array[index]<ele;
        array[index]=ele;
        if (dn) { // percolate down
            while (index<=len/2-1) {
                int lc=index*2+1;
                int rc=index*2+2;
                if (rc==len) {
                    if (array[lc]<array[index]) {
                        swap(array,lc,index);
                        index=lc;
                    } else {
                        break;
                    }
                } else {
                    if (array[rc]<=array[lc] && array[rc]<=array[index]) {
                        swap(array,rc,index);
                        index=rc;
                    } else if (array[lc]<=array[rc] && array[lc]<=array[index]) {
                        swap(array,lc,index);
                        index=lc;
                    } else {
                        break;
                    }
                }
            }
        } else { // percolate up
            while (index>0) {
                int parent=(index-1)/2;
                if (array[index]<array[parent]) {
                    swap(array,index,parent);
                    index=parent;
                } else {
                    break;
                }
            }
        }
        return array;
        // Write your solution here
    }
    public boolean isMinHeap(int[] array) { // 391
        if (array==null) {return false;}
        int len=array.length;
        if (len<=1) {return true;}
        int cur=0;
        while (cur<len) {
            if (cur*2+1<len && array[cur]>array[cur*2+1]) {return false;}
            if (cur*2+2<len && array[cur]>array[cur*2+2]) {return false;}
            cur++;
        }
        return true;
        // Write your solution here
    }
    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left]=array[right];
        array[right]=tmp;
    }
}
