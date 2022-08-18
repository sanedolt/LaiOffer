package com.laioffer.arraytest;
import java.util.*;

public class Sort {
    private void swap(int[] array, int left, int right) { // 541
        int temp=array[left];
        array[left]=array[right];
        array[right]=temp;
    }
    public void partition549(int[] array, int pivotIndex) { // 549
        if (array==null || array.length==0) {return;}
        int pivot=array[pivotIndex];
        int len=array.length;
        swap(array,pivotIndex,len-1);
        int left=0,right=len-2;
        while (left<=right) {
            if (array[left]<pivot) {
                left++;
            } else if (array[right]>=pivot) {
                right--;
            } else {
                swap(array,left++,right--);
            }
        }
        swap(array,left,len-1);
    }
    private int pivotIndex(int left, int right) {
        return left+(int)(Math.random()*(right-left+1));
    }
    private int partition(int[] array, int left, int right) {
        int pivotIndex=pivotIndex(left,right);
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
    private int partition(int[] array, int pivotIndex) {
        if (array==null || array.length==0) {return -1;}
        int pivot=array[pivotIndex];
        int length=array.length;
        swap(array,pivotIndex,length-1);
        int left=0;
        int right=length-2;
        while (left<=right) {
            if (array[left]<pivot) {
                left++;
            } else {
                swap(array,left,right);
                right--;
            }
        }
        swap(array,left,length-1);
        return left;
    }
    private int[] merge(int[] one, int[] two) {
        int[] out = new int[one.length+two.length];
        int ind1=0, ind2=0;
        while (ind1<one.length || ind2<two.length) {
            if (ind1==one.length) {
                out[ind1+ind2]=two[ind2];
                ind2++;
            } else if (ind2==two.length) {
                out[ind1+ind2]=one[ind1];
                ind1++;
            } else if (one[ind1]<=two[ind2]) {
                out[ind1+ind2]=one[ind1];
                ind1++;
            } else {
                out[ind1+ind2]=two[ind2];
                ind2++;
            }
        }
        return out;
    }
    public int[] mergeSort(int[] array, int a, int b) {
        if (array==null || array.length==0) {return null;}
        int[] out=new int[b-a+1];
        if (a==b) {
            out[0]=array[a];
        } else {
            int c = a + (b - a) / 2;
            out = merge(mergeSort(array, a, c), mergeSort(array, c + 1, b));
        }
        return out;
    }
    private void merge(int[] array, int left, int mid, int right, int[] helper) {
        for (int i=left;i<=right;i++) {
            helper[i]=array[i];
        }
        int leftIndex=left,rightIndex=mid+1,curIndex=left;
        while (leftIndex<=mid && rightIndex<=right) {
            if (helper[leftIndex]<=helper[rightIndex]) {
                array[curIndex++]=helper[leftIndex++];
            } else { // helper[rightIndex]<helper[leftIndex]
                array[curIndex++]=helper[rightIndex++];
            }
        }
        while (leftIndex<=mid) {
            array[curIndex++]=helper[leftIndex++];
        }
    }
    private void mergeSort(int[] array, int left, int right, int[] helper) {
        if (left==right) {return;}
        int mid=left+(right-left)/2;
        mergeSort(array,left,mid,helper);
        mergeSort(array,mid+1,right,helper);
        merge(array,left,mid,right,helper);
    }
    public int[] mergeSort(int[] array) { // 9
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        int[] helper = new int[len];
        mergeSort(array,0,len-1,helper);
        return array;
        // Write your solution here
    }
    private void quickSort(int[] array, int left, int right) {
        if (left>=right) {return;}
        int pivotPos=partition(array,left,right);
        quickSort(array, left, pivotPos-1);
        quickSort(array,pivotPos+1,right);
    }
    public int[] quickSort(int[] array) { // 10
        if (array==null || array.length<2) {return array;}
        quickSort(array,0,array.length-1);
        return array;
    }
    public int[] selectionSort(int[] array) { // 4
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        for (int i=0;i<len-1;i++) {
            int minind=i;
            for (int j=i+1;j<len;j++) {
                if (array[j]<array[minind]) {minind=j;}
            }
            swap(array,i,minind);
        }
        return array;
    }
    public int[] insertionSort(int[] array) { // 340
        if (array==null || array.length<=1) {return array;}
        int len=array.length;
        for (int i=1;i<len;i++) {
            for (int j=0;j<i;j++) {
                if (array[i]<array[j]) {
                    swap(array,i,j);
                }
            }
        }
        return array;
        // Write your solution here
    }
    public int[] rainbowSort(int[] array) { // 11
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        int i=0,j=0,k=len-1; // all red on left of i, all green on left of j (starting from i), all blud on right of k
        while (j<=k) {
            if (array[j]==-1) {
                swap(array,i++,j++);
            } else if (array[j]==0) {
                j++;
            } else { // array[j]=1;
                swap(array,j,k--);
            }
        }
        return array;
    }
    public int[] rainbowSortII(int[] array) { // 399
        if (array==null || array.length==0) {return array;}
        int len=array.length;
        int r=0,g=0,b=0,k=len-1;
        while (b<=k) {
            if (array[b]==0) {
                swap(array,g,b++);
                swap(array,r++,g++);
            } else if (array[b]==1) {
                swap(array,g++,b++);
            } else if (array[b]==2) {
                b++;
            } else { // array[b]==3
                swap(array,b,k--);
            }
        }
        return array;
        // Write your solution here
    }
    public int[] rainbowSortIII(int[] array, int k) { // 400
        // Write your solution here
        if (array==null || array.length<=1) {return array;}
        int len=array.length;
        int[] bar = new int[k];
        // left of bar[0] is all 1, left of bar[1] is 2, left of bar[k-2] is k-1, right of bar[k-1] is k, bar[k-2] to bar[k-1] is undetermined
        bar[k-1]=len-1;
        while (bar[k-2]<=bar[k-1]) {
            int val=array[bar[k-2]];
            if (val==k) {
                swap(array,bar[k-2],bar[k-1]--);
            } else if (val==k-1) {
                bar[k-2]++;
            } else {
                for (int i=k-3;i>=val-1;i--) {
                    swap(array,bar[i+1]++,bar[i]);
                }
                bar[val-1]++;
            }
        }
        return array;
    }
    public int[] rainbowSortIII2(int[] array, int k) { // 400
        // Write your solution here
        if (array==null || array.length<=1) {return array;}
        int len=array.length;
        int left=0,right=len-1;
        int min=1,max=k;
        while (left<right && min<=max) {
            int bl=left,br=right,bc=left;
            while (bc<=br) {
                if (array[bc]==min) {
                    swap(array,bl++,bc++);
                } else if (array[bc]==max) {
                    swap(array,br--,bc);
                } else {
                    bc++;
                }
            }
            left=bl;right=br;min++;max--;
        }
        return array;
    }
    public int[] heapsort(int[] array) { // 328
        if (array==null || array.length<=1) {return array;}
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare (Integer a, Integer b) {
                if (a.equals(b)) {
                    return 0;
                } else {
                    return a>b?-1:1;
                }
            }
        });
        int len=array.length;
        for (int i=0;i<len;i++) {
            pq.offer(array[i]);
        }
        for (int i=0;i<len;i++) {
            array[i]=pq.poll();
        }
        for (int i=len-1;i>0;i--) {
            swap(array,0,i);
            int cur=0;
            while (cur<=i/2-1) {
                int lc=cur*2+1;
                int rc=cur*2+2;
                int bc = lc;
                if (rc<i && array[lc]<array[rc]) {bc=rc;}
                if (array[cur]<array[bc]) {
                    swap(array,cur,bc);
                    cur=bc;
                } else {
                    break;
                }
            }
        }
        return array;
        // Write your solution here
    }
    /*
    458. Wiggle Sort II
    Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
    Example:
    (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
    (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
    Note:
    You may assume all input has valid answer.
    This question does not have unique answer, and the correctness check may be not working.
    Follow Up:
    Can you do it in O(n) time and/or in-place with O(1) extra space?
     */
    public int[] wiggleSort(int[] nums) { // 458
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0) == (nums[i] > nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
        return nums;
    }
//    private void swap (int[] nums, int a, int b) {
//        int tmp = nums[a];
//        nums[a] = nums[b];
//        nums[b] = tmp;
//    }
    /*
    260. Interleave Positive And Negative Elements
    Given an array with both positive and negative numbers in random order. Shuffle the array so that positive and negative numbers are put in position with even and odd indices, respectively.
    If there are more positive/negative numbers, put them at the end of the array. The ordering of positive/negative numbers does not matter.
    Assumptions:
    The given array is not null.
    There is no 0 in the array.
    */
    public int[] interleave(int[] array) { // 260
        if (array == null || array.length <= 1) {
            return array;
        }
        int len=array.length;
        int[] sign = new int[]{1,-1};
        for (int i = 0; i < len; i++) {
            int res=i%2;
            if (res==0 && array[i]>0 || res==1 && array[i]<0) {continue;}
            int[] jbeg = new int[]{((len-1)/2)*2,(len/2)*2-1};
            // for odd number, last (should be positive), second from last (should be negative)
            // for even number, second from last (should be positive), last (should be negative)
            boolean found=false;
            for (int j=jbeg[1-res];j>i;j-=2) {
                if (array[j]*sign[res]>0) {
                    swap(array,i,j);
                    found=true;
                    break;
                }
            }
            if (found) {continue;}
            for (int j=jbeg[res];j>i;j-=2) {
                if (array[j]*sign[res]>0) {
                    swap(array,i,j);
                    found=true;
                    break;
                }
            }
        } // end for
        return array;
        // Write your solution here
    }
    /*
    261. Sort In Specified Order
    Given two integer arrays A1 and A2, sort A1 in such a way that the relative order among the elements will be same as those are in A2.
    For the elements that are not in A2, append them in the right end of the A1 in an ascending order.
     */
    static class MyComparator implements Comparator<Integer> {
        private Map<Integer,Integer> map;
        public MyComparator(int[] array) {
            map=new HashMap<>();
            for (int i=0;i<array.length;i++) {
                map.put(array[i],i);
            }
        }
        @Override
        public int compare (Integer i1, Integer i2) {
            Integer index1=map.get(i1);
            Integer index2=map.get(i2);
            if (index1!=null && index2!=null) {
                return index1.compareTo(index2);
            } else if (index1==null && index2==null) {
                return i1.compareTo(i2);
            }
            return index1!=null?-1:1;
        }
    }
    public int[] sortSpecial(int[] A1, int[] A2) { // 261
        Integer[] refArray = toIntegerArray(A1);
        Arrays.sort(refArray,new MyComparator(A2));
        toIntArray(refArray,A1);
        return A1;
        // Write your solution here
    }
    private void toIntArray(Integer[] array, int[] result) {
        for (int i=0;i<array.length;i++) {
            result[i]=array[i];
        }
    }
    private Integer[] toIntegerArray(int[] array) {
        Integer[] result = new Integer[array.length];
        for (int i=0;i<array.length;i++) {
            result[i]=array[i];
        }
        return result;
    }
    /*
    262. Sort In Pair
    Given an array A with integers, sort the array such that  A[0] < A[1]  > A[2] < A[3] >  A[4] < A[5] > … A[N-1].
    Assumptions:
    A is guaranteed to be not null.
    There are no duplicate elements in A.
     */
    public void sortInPair(int[] array) { // 262
        if (array==null || array.length==0) {return;}
        int len=array.length;
        for (int i=0;i<len;i+=2) {
            if (i<len-1 && array[i]>array[i+1]) {
                swap(array,i,i+1);
            }
            if (i>0 && array[i]>array[i-1]) {
                swap(array,i,i-1);
            }
        }
        // Write your solution here.
    }
    /*
    427. Count of Smaller Numbers After Self
    You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
    Example:
    Given nums = [5, 2, 6, 1]
    To the right of 5 there are 2 smaller elements (2 and 1).
    To the right of 2 there is only 1 smaller element (1).
    To the right of 6 there is 1 smaller element (1).
    To the right of 1 there is 0 smaller element.
     */
    public List<Integer> countSmaller(int[] nums) { // 427
        List<Integer> result = new ArrayList<>();
        if (nums==null) {return result;}
        int len=nums.length;
        // int[] shift = new int[len];
        // int[] position = new int[len];
        // for (int i=0;i<len;i++) {
        //   position[i]=i; // current index
        //   result.add(0);
        // }
        // countShift(nums,0,len,position,shift);  // merge sort
        // for (int i=0;i<len;i++) {
        //   result.set(position[i],shift[i]);
        // }
        int offset = 10000; // offset negative to non-negative
        int size=2*10000+2;
        int[] tree = new int[size];
        for (int i=len-1;i>=0;i--) {
            result.add(query(nums[i]+offset,tree));
            update(nums[i]+offset,1,tree,size);
        }
        Collections.reverse(result);
        return result;
        // Write your solution here
    }
    private void countShift(int[] nums, int left, int right, int[] position, int[] shift) {
        if (left+1==right) {return;}
        int mid=left+(right-left)/2;
        countShift(nums,left,mid,position,shift);
        countShift(nums,mid,right,position,shift);
        int[] caches = new int[right-left]; // shift
        int[] cachep = new int[right-left]; // position
        int leftBound=left,rightBound=mid;
        for (int i=left;i<right;i++) {
            if (rightBound>=right || leftBound<mid && nums[position[leftBound]]<=nums[position[rightBound]]) {
                caches[i-left]=shift[leftBound]+rightBound-mid; // how many move from right to left
                cachep[i-left]=position[leftBound++];
            } else {
                caches[i-left]=shift[rightBound];
                cachep[i-left]=position[rightBound++];
            }
        }
        for (int i=left;i<right;i++) {
            shift[i]=caches[i-left];
            position[i]=cachep[i-left];
        }
    }
    private void update(int index, int value, int[] tree, int size) {
        index++; // index in BIT is 1 more than the original index
        while (index < size) {
            tree[index] += value;
            index += index & -index;
        }
    }
    private int query(int index, int[] tree) {
        // return sum of [0, index)
        int result = 0;
        while (index >= 1) {
            result += tree[index];
            index -= index & -index;
        }
        return result;
    }
    public static void main(String[] args) {
        Sort solution = new Sort();
//        int[] input = new int[] {9,4,6,2,1,0,7};
//        solution.partition(input,2);
//        int[] input=new int[] {4,2,-3,6,1};
//        int[] output=solution.selectionSort(input);
//        int[] output=solution.quickSort(input);
//        int[] input=new int[] {1,1,0,-1,0,1,-1};
//        int[] output = solution.rainbowSort(input);
//        int[] input=new int[] {3,5,1,2,4,8};
//        int[] output = solution.mergeSort(input,0,input.length-1);
//        int[] input = new int[] {388,-304,304,-350,26,366,190,85,468,-321,159,-29,269,360,454,-214,496,-472,77,82,197,-104,495,-192,148,266,432,209,149,-314,61,-153,400,-38,372,144,267,-241,153,294,443,176,395,-498,170,-494,7,315,295,-110,1,224,11,-314,275,-199,126,-311,334,412,214,-216,128,-91,388,208,329,-188,324,375,111,147,22,131,23,-449,22,377,227,208,86,240,292,-65,498,345,311,14,315,299,435,414,476,196,440,-327,226,-212,108,21,335,19,191,308,13,-249,273,-107,370,-151,223,-33,97,290,435,-319,382,-438,268,65,333,458,65,-43,405,279,236,75,492,-409,231,-78,499,-65,293,413,251,323,97,21,163,-116,349,-270,72,-170,57,286,53,-289,56,78,467,56,405,483,311,-371,43,-368,248,-61,76,414,442,110,189,-49,38,-77,92,-66,280,-355,443,-29,372,253,5,-467,244,-219,237,191,426,-154,224,-447,177,-183,234,-245,457,-265,261,376,205,-177,234,395,214,376,40,169,173,-141,210,433,195,318,50,15,336,-456,46,306,231,25,237,-496,118,379,216,320,17,-147,163,136,363,452,212,-419,352,462,81,91,140,432,213,326,433,176,209,227,283,-8,56,100,211,71,86,363,467,57,241,483,428,-212,55,197,265,-481,302,337,75,374,13,427,226,312,2,-230,139,-359,202,-79,167,6,450,273,142,-180,448,4,482,287,342,-252,77,-451,370,208,397,-4,168,365,499,429,202,53,239,-392,358,483,135,-296,23,-299,379,288,432,61,500,-193,272,-436,184,-64,394,284,149,-194,91,192,377,-141,184,369,306,21,139,30,400,-53,170,-272,233,311,146,-126,418,-467,250,-19,344,-161,129,99,313,298,298,496,170,286,265,12,68,-119,265,436,432,-168,101,-110,115,92,431,-497,85,381,351,-455,319,-139,358,-154,320,-163,203,-500,415,324,296,-165,163,416,142,105,318,-305,345,-336,392,290,453,173,372,-190,364,-18,158,74,174,-28,211,489,190,11,294,-85,394,-347,50,118,403,-128,440,219,437,447,329,86,462,46,218,279,65,211,262,-23,169,210,337,315,483,157,490,-339,67,-113,109,-288,53,-154,128,-324,473,-387,13,464,224,-221,144,-450,478,-406,33,90,357,165,127,-47,396,46,146,-439,185,-301,126,-177,229,-314,464,-22,338,435,365,-129,378,65,133,54,447,84,265,91,422,-276,240,-362,473,314,331,186,446,353,101,-175,45,-107,89,51,486,433,424,404,200,-246,372,-304,326,58,450,-322,57,306,393,274,456,-116,345,-86,72,-414,158,85,190,439,186,-54,144,78,403,-274,500,220,46,-292,488,275,362,-247,291,305,302,-296,283,326,295,-471,467,434,400,-307,250,350,333,130,225,-244,447,106,452,234,323,462,188,-294,141,-35,184,18,484,-12,82,-273,130,323,12,187,293,-301,286,-225,68,497,88,446,170,233,237,-166,226,-395,31,-169,142,284,254,-122,153,192,257,217,284,-312,18,15,12,-52,311,-438,379,-154,30,-270,324,-203,319,475,343,362,11,479,3,-121,362,-69,114,52,343,54,162,181,153,226,290,-288,250,450,324,-145,64,486,459,298,66,172,265,475,125,334,284,393,46,214,376,-230,295,-468,12,138,321,135,23,-420,396,-319,217,-12,190,-31,36,83,234,322,215,-46,107,-91,64,-469,56,5,203,236,307,-28,160,49,129,170,301,358,177,113,114,230,324,-85,307,244,75,486,116,-423,252,-10,100,-162,429,-194,27,40,78,-415,239,189,98,35,112,185,308,-235,447,140,58,-175,224,-257,447,-386,287,5,150,452,70,-416,36,436,448,-108,212,173,300,361,240,-423,225,-439,62,231,280,364,256,-278,342,-42,419,-42,54,339,430,-268,450,-172,485,-320,106,299,123,360,222,39,8,387,175,-406,473,425,94,-470,301,374,478,-238,319,279,357,6,305,304,353,-74,198,-350,370,-209,404,409,423,-241,442,188,371,476,331,-220,45,290,9,300,394,260,128,-44,390,66,349,104,20,123,257,263,171,436,174,-414,232,234,52,-380,118,-9,193,293,328,230,378,-237,464,408,425,136,436,264,440,92,290,240,403,146,322,-26,338,-398,243,407,134,262,293,-251,75,-393,230,405,369,473,124,-42,369,207,347,-480,314,-85,69,21,214,-293,395,-448,68,-268,143,473,291,455,492,-187,188,-194,467,417,438,-209,399,440,393,-327,126,2,16,73,286,276,82,207,75,-163,133,-16,338,-162,418,-83,305,463,319,-418,165,151,391,-77,35,297,356,-210,152,463,379,-44,405,-314,113,296,494,105,367,-50,192,-40,335,56,272,28,84,80,250,-363,487,-129,83,-226,476,298,476,168,356,-266,321,-93,28,-270,226,-130,254,-93,373,175,85,493,257,-53,283,-57,91,-88,459,-224,346,-276,254,95,30,470,210,260,431,-484,464,-167,76,-323,133,221,166,-247,176,420,212,-396,99,-124,484,273,268,-250,281,165,25,-168,312,-95,127,-86,251,299,378,-303,285,-226,14,198,89,-217,228,196,351,-76,226,458,500,-84,18,370,357,200,29,107,398,-387,306,-302,273,-500,442,135,92,-325,389,210,376,-189,90,-99,322,248,343,-64,322,-340,455,-365,375,-254,113,262,153,-110,4,-137,163,-244,439,44,436,-112,267,211,250,-303,13,-469,334,-327,77,301,439,244,400,461,31,486,330,-316,188,-196,365,-108,90,-231,203,24,193,-460,382,-393,398,-317,431,-1,155,-266,235,177,31,-432,356,-396,345,492,225,2,450,-32,83,-164,203,78,134,-65,96,139,204,-48,344,374,238,295,69,169,183,-235,480,259,241,369,351};
//        int[] input = new int[] {2,1};
//        solution.printArray(solution.quickSort(input));
//        int[] input = new int[] {9,7,5,7,9};
//        solution.printArray(solution.rainbowSortIII(input,13));
        int[] input = new int[]{2,1,3,5,4};
        System.out.println(Arrays.toString(solution.heapsort(input)));
    }
}
