package com.laioffer.Algorithm.arraytest;
import java.util.*;

public class Pairs {
    private void printArray (int[] array) {
        System.out.println(Arrays.toString(array));
    }
    /*
    180. 2 Sum
    Determine if there exist two elements in a given array, the sum of which is the given target number.
    Assumptions
    The given array is not null and has length of at least 2
    Examples
    A = {1, 2, 3, 4}, target = 5, return true (1 + 4 = 5)
    A = {2, 4, 2, 1}, target = 4, return true (2 + 2 = 4)
    A = {2, 4, 1}, target = 4, return false
     */
    public boolean existSum1(int[] array, int target) { // 180
        if (array==null || array.length<2) {return false;}
        Set<Integer> appeared = new HashSet<>();
        for (int i=0;i<array.length;i++) {
            if (appeared.contains(target-array[i])) {return true;}
            if (!appeared.contains(array[i])) {appeared.add(array[i]);}
        }
        return false;
    }
    public boolean existSum(int[] array, int target) { // 180
        if (array==null || array.length<2) {return false;}
        Arrays.sort(array);
        int left=0,right=array.length-1;
        while (left<right) {
            int sum=array[left]+array[right];
            if (sum==target) {
                return true;
            } else if (sum<target) {
                left++;
            } else { //sum>target
                right--;
            }
        }
        return false;
        // Write your solution here
    }
    /*
    181. 2 Sum All Pair I
    Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.
    Assumptions
    The given array is not null and has length of at least 2.
    Examples
    A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
    A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
     */
    public List<List<Integer>> allPairs(int[] array, int target) { // 181
        List<List<Integer>> result = new ArrayList<>();
        if (array==null || array.length<2) {return result;}
        Map<Integer,List<Integer>> appeared = new HashMap<>(); // list of index of key
        for (int i=0;i<array.length;i++) {
            List<Integer> prev = appeared.get(target-array[i]);
            if (prev!=null) {
                for (int pre : prev) {
                    result.add(Arrays.asList(pre,i));
                }
            }
            if (!appeared.containsKey(array[i])) {
                appeared.put(array[i],new ArrayList<>());
            }
            appeared.get(array[i]).add(i);
        }
        return result;
        // Write your solution here
    }
    /*
    182. 2 Sum All Pair II
    Find all pairs of elements in a given array that sum to the pair the given target number. Return all the distinct pairs of values.
    Assumptions
    The given array is not null and has length of at least 2
    The order of the values in the pair does not matter
    Examples
    A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
     */
    public List<List<Integer>> allPairs1(int[] array, int target) { // 182
        List<List<Integer>> result = new ArrayList<>();
        if (array==null || array.length<2) {return result;}
        Set<Integer> appeared = new HashSet<>();
        Set<Integer> used = new HashSet<>();
        for (int i=0;i<array.length;i++) {
          int need=target-array[i];
          if (appeared.contains(need) && !used.contains(need) && !used.contains(array[i])) {
            result.add(Arrays.asList(Math.min(array[i],need),Math.max(array[i],need)));
            used.add(array[i]);
            used.add(need);
          }
          appeared.add(array[i]);
        }
        return result;
        // Write your solution here
    }
    public List<List<Integer>> allPairs2(int[] array, int target) { // 182
        List<List<Integer>> result = new ArrayList<>();
        if (array==null || array.length<2) {return result;}
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : array) {
            Integer count = map.get(num);
            if (num+num==target && count!=null && count==1) {
                result.add(Arrays.asList(num,num));
            } else if (map.containsKey(target-num) && count==null) {
                result.add(Arrays.asList(target-num,num));
            }
            map.put(num,count==null?1:count+1);
        }
        return result;
        // Write your solution here
    }
    public List<List<Integer>> allPairs3(int[] array, int target) { // 182
        List<List<Integer>> result = new ArrayList<>();
        if (array==null || array.length<2) {return result;}
        Arrays.sort(array);
        int left=0,right=array.length-1;
        while (left<right) {
          if (left>0 && array[left]==array[left-1]) {
            left++;
            continue;
          }
          int cur = array[left]+array[right];
          if (cur==target) {
            result.add(Arrays.asList(array[left++],array[right--]));
          } else if (cur<target) {
            left++;
          } else {
            right--;
          }
        }
        return result;
        // Write your solution here
    }
    /*
    183. 2 Sum Closest
    Find the pair of elements in a given array that sum to a value that is closest to the given target number. Return the values of the two numbers.
    Assumptions
    The given array is not null and has length of at least 2
    Examples
    A = {1, 4, 7, 13}, target = 7, closest pair is 1 + 7 = 8, return [1, 7].
     */
    public List<Integer> closest(int[] array, int target) { // 183
        List<Integer> result = new ArrayList<>();
        if (array==null || array.length<2) {return result;}
        result.add(-1);result.add(-1);
        Arrays.sort(array);
        int left=0,right=array.length-1,sum=0;
        int min=Integer.MAX_VALUE;
        while (left<right) {
            sum=array[left]+array[right];
            if (Math.abs(sum-target)<min) {
                min=Math.abs(sum-target);
                result.set(0,array[left]);
                result.set(1,array[right]);
                if (sum==target) {return result;}
            }
            if (sum<target) {
                left++;
            } else if (sum>target) {
                right--;
            }
        } // end while
        return result;
        // Write your solution here
    }
    /*
    184. 2 Sum Smaller
    Determine the number of pairs of elements in a given array that sum to a value smaller than the given target number.
    Assumptions
    The given array is not null and has length of at least 2
    Examples
    A = {1, 2, 2, 4, 7}, target = 7, number of pairs is 6({1,2}, {1, 2}, {1, 4}, {2, 2}, {2, 4}, {2, 4})
     */
    public int smallerPairs(int[] array, int target) { // 184
        if (array==null || array.length<2) {return 0;}
        Arrays.sort(array);
        // int result=0,left=0,right=array.length;
        // while (left<--right) {
        //   while (left<right && array[left]+array[right]<target) {
        //     left++;
        //   }
        //   result+=left; //0~left-1
        // }
        // return result+right*(right+1)/2;
        int result=0,left=0,right=array.length-1;
        while (left<right) {
            int sum=array[left]+array[right];
            if (sum<target) {
                result+=right-left;
                left++;
            } else {
                right--;
            }
        }
        return result;
        // Write your solution here
    }
    /*
    185. 2 Sum 2 Arrays
    Given two arrays A and B, determine whether or not there exists a pair of elements, one drawn from each array, that sums to the given target number.
    Assumptions
    The two given arrays are not null and have length of at least 1
    Examples
    A = {3, 1, 5}, B = {2, 8}, target = 7, return true(pick 5 from A and pick 2 from B)
    A = {1, 3, 5}, B = {2, 8}, target = 6, return false
     */
    public boolean existSum185(int[] a, int[] b, int target) { // 185
        if (a==null || b==null || a.length<=1 || b.length<=1) {return false;}
        Arrays.sort(a);
        Arrays.sort(b);
        int ia=0,ib=b.length-1;
        while (ia<a.length && ib>=0) {
            if (a[ia]+b[ib]==target) {
                return true;
            } else if (a[ia]+b[ib]<target) {
                ia++;
            } else { // a[ia]+b[ib]>target
                ib--;
            }
        }
        return false;
        // Write your solution here
    }
    /*
    186. 3 Sum
    Determine if there exists three elements in a given array that sum to the given target number. Return all the triple of values that sums to target.
    Assumptions
    The given array is not null and has length of at least 3
    No duplicate triples should be returned, order of the values in the tuple does not matter
    Examples
    A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]
     */
    public List<List<Integer>> allTriples(int[] array, int target) { // 186
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (array==null || array.length<3) {return result;}
        Arrays.sort(array);
        int i=0,len=array.length,temp=0;
        while (i<len-2) {
            int need=target-array[i];
            int left=i+1,right=len-1;
            while (left<right) {
                temp = array[left]+array[right];
                if (temp==need) {
                    result.add(Arrays.asList(array[i],array[left],array[right--]));
                    temp=array[left++];
                    while (left<len-1 && array[left]==temp) {left++;}
                } else if (temp>need) {
                    right--;
                } else { // temp<need
                    left++;
                } // end if
            } // end while
            temp=array[i++];
            while (i<len-2 && array[i]==temp) {i++;}
        } // end for
        return result;
        // Write your solution here
    }
    /*
    187. 3 Sum 3 Arrays
    Given three arrays, determine if a set can be made by picking one element from each array that sums to the given target number.
    Assumptions
    The three given arrays are not null and have length of at least 1
    Examples
    A = {1, 3, 5}, B = {8, 2}, C = {3}, target = 14, return true(pick 3 from A, pick 8 from B and pick 3 from C)
     */
    public boolean exist187(int[] a, int[] b, int[] c, int target) { // 187
        if (a==null || b==null || c==null || a.length<=1 || b.length<=1 || c.length<=1) {return false;}
        Arrays.sort(a);
        Arrays.sort(b);
        for (int vc:c) {
            int ia=0,ib=b.length-1;
            while (ia<a.length && ib>=0) {
                if (a[ia]+b[ib]==target-vc) {
                    return true;
                } else if (a[ia]+b[ib]<target-vc) {
                    ia++;
                } else { // a[ia]+b[ib]>target-vc
                    ib--;
                } // end if
            } // end while
        } // end for
        return false;
        // Write your solution here
    }
    /*
    188. 4 Sum
    Determine if there exists a set of four elements in a given array that sum to the given target number.
    Assumptions
    The given array is not null and has length of at least 4
    Examples
    A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 9)
    A = {1, 2, 2, 3, 4}, target = 12, return false
     */
    public boolean exist1(int[] array, int target) { // 188
        if (array == null || array.length < 4) {return false;}
        Arrays.sort(array);
        int len = array.length;
        for (int i=0;i<len-3;i++) {
          if (array[i]>=target) {break;}
          for (int j=i+1;j<len-2;j++) {
            int sumTwo = array[i]+array[j];
            if (sumTwo>=target) {break;}
            int left=j+1,right=len-1;
            while (left<right) {
              int sumFour=sumTwo+array[left]+array[right];
              if (sumFour==target) {
                return true;
              } else if (sumFour>target) {
                right--;
              } else {
                left++;
              }
            } // end while
          }
        }
        return false;
        // Write your solution here
    }
    static class Element implements Comparable<Element> {
      int left,right,sum;
      Element(int left, int right, int sum) {
        this.left=left;
        this.right=right;
        this.sum=sum;
      }
      @Override
      public int compareTo(Element another) {
        if (this.sum!=another.sum) {
          return this.sum<another.sum?-1:1;
        } else if (this.right!=another.right) {
          return this.right<another.right?-1:1;
        } else if (this.left!=another.left) {
          return this.left<another.left?-1:1;
        }
        return 0;
      }
    }
    private Element[] getPairSum(int[] array) {
      int len=array.length;
      Element[] pairSum = new Element[len*(len-1)/2];
      int curIndex=0;
      for (int i=1;i<len;i++) {
        for (int j=0;j<i;j++) {
          pairSum[curIndex++]=new Element(j,i,array[i]+array[j]);
        }
      }
      return pairSum;
    }
    public boolean exist2(int[] array, int target) { // 188
        if (array == null || array.length < 4) {return false;}
        Arrays.sort(array);
        int len = array.length;
        Element[] pairSum = getPairSum(array);
        Arrays.sort(pairSum);
        int left=0,right=pairSum.length-1;
        while (left<right) {
          if (pairSum[left].sum+pairSum[right].sum==target && pairSum[left].right<pairSum[right].left) {
            return true;
          } else if (pairSum[left].sum+pairSum[right].sum<target) {
            left++;
          } else {
            right--;
          }
        }
        return false;
        // Write your solution here
    }
    static class Pair{
        int left,right;
        Pair(int left,int right) {
            this.left=left;
            this.right=right;
        }
    }
    public boolean exist3(int[] array, int target) { // 188
        if (array == null || array.length < 4) {return false;}
        Arrays.sort(array);
        int len = array.length;
        Map<Integer,Pair> map = new HashMap<>();
        for (int i=1;i<len;i++) {
            for (int j=0;j<i;j++) {
                int pairSum=array[j]+array[i];
                if (map.containsKey(target-pairSum) && map.get(target-pairSum).right<j) {
                    return true;
                }
                if (!map.containsKey(pairSum)) {
                    map.put(pairSum, new Pair(j,i));
                }
            }
        }
        return false;
        // Write your solution here
    }
    /*
    496. 3Sum Smaller
    Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
    For example, given nums = [-2, 0, 1, 3], and target = 2.
    Return 2. Because there are two triplets which sums are less than 2:
    [-2, 0, 1]
    [-2, 0, 3]
     */
    public int threeSumSmaller(int[] num, int target) { // 496
        if (num==null) {return -1;}
        if (num.length<3) {return 0;}
        Arrays.sort(num);
        int result=0;
        int len=num.length;
        for (int i=0;i<len-2;i++) {
            int left=i+1,right=len-1;
            while (left<right) {
                if (num[left]+num[right]>=target-num[i]) {
                    right--;
                } else {
                    result+=right-left;
                    left++;
                }
            } // end while
        } // end for
        return result;
        // Write your solution here
    }
    /*
    244. 3Sum Closest
    Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the difference  between the sum of the three integers and the given number. You may assume that each input would have exactly one solution.
    Example
       For example, given array S = {-1 2 1 -4}, and target = 1.
      The sum that is closest to the target is 2. (-1 + 2 + 1 = 2) and the difference is 1.
     */
    public int threeSumClosest(int[] num, int target) { // 244
        if (num==null || num.length<3) {return -1;}
        int min=Math.abs(num[0]+num[1]+num[2]-target);
        int len=num.length;
        Arrays.sort(num);
        for (int i=0;i<len-2;i++) {
            int left=i+1,right=len-1;
            while (left<right) {
                int temp=num[i]+num[left]+num[right];
                min=Math.min(min,Math.abs(temp-target));
                if (min==0) {return min;}
                if (temp>target) {right--;}
                if (temp<target) {left++;}
            } // end while
        } // end for
        return min;
        // Write your solution here
    }
    /*
    273. Number Of Valid Triangles
    Given an unsorted array of positive integers. Find the number of triangles that can be formed with three different array elements as three sides of triangles.
     */
    public int numOfTriangles(int[] array) { // 273
        if (array==null || array.length<3) {return 0;}
        Arrays.sort(array);
        int len=array.length;
        int count=0;
        for (int right=len-1;right>=2;right--) {
            int left=0,mid=right-1;
            while (left<mid) {
                if (array[left]+array[mid]>array[right]) {
                    count+=mid-left;
                    mid--;
                } else {
                    left++;
                }
            }
        }
        return count;
        // Write your solution here
    }
    /*
    581. Count Triangles
    Given an array of positive integers representing side length of triangle. Take three numbers each time to construct a triangle. Count how many triangles we could construct using the given numbers.
    Example 1:
    Input: array = [3,4,5,5]
    Output: 4
    Explanation: We could construct 4 triangles using numbers in given array.
    [3,4,5] the first 5
    [3,4,5] the second 5
    [3,5,5]
    [4,5,5]
    Assumptions:
    The given array is unsorted.
    The given array only contains positive numbers.
     */
    public int countTriangles(int[] nums) { // 581
        if (nums==null || nums.length==0) {return 0;}
        Arrays.sort(nums);
        int len=nums.length;
        int count=0;
        for (int j=2;j<len;j++) {
            for (int i=0;i<j-1;i++) {
                int tmp = find(nums,i,j);
                count+=tmp==-1?0:j-tmp;
            }
        }
        return count;
        // Write your solution here
    }
    private int find (int[] nums, int left, int right) {
        int target=nums[right--]-nums[left++]; // find the number bigger than target
        while (left<right) {
            int mid=left+(right-left)/2;
            if (nums[mid]<=target) {
                left=mid+1;
            } else {
                right=mid;
            }
        }
        if (nums[left]>target) {
            return left;
        } else {
            return -1;
        }
    }
    /*
    314. A + B + C = D
    Determine if there exist 4 elements in an given positive integer array, such that A + B + C = D.
    Assumptions:
    The given array is not null and has length of >= 4.
    All the elements in the given array are positive integers.
    Examples:
    array = {1, 4, 3, 2}, there does not exist such elements, return false.
    array = {5, 1, 4, 0, 2, 1}, since 1 + 4 + 0 = 5, return true.
     */
    public boolean exist(int[] array) { // 314
        if (array==null || array.length<4) {return false;}
        Arrays.sort(array);
        int len=array.length;
        for (int i=0;i<len-3;i++) {
            for (int j=i+3;j<len;j++) {
                int dif=array[j]-array[i];
                int left=i+1,right=j-1;
                while (left<right) {
                    int sum=array[left]+array[right];
                    if (sum==dif) {return true;}
                    if (sum>dif) {right--;} else {left++;}
                }
            }
        }
        return false;
        // Write your solution here
    }
    /*
    343. Number Of Pairs Diff To Target
    Given an integer array, determine how many pairs of elements, the absolute value of the difference between the two elements is the given target value.
    Assumptions:
    There could be elements with duplicate value in the array, and each of the elements is considered different.
    The given array is not null and has length >= 2.
    Examples:
    array = {3, 1, 2, 1}, target = 2, there are 2 pairs { (3, 1), (3, 1) }
     */
    public int pairs(int[] array, int target) { // 343
        if (array==null || array.length<2) {return 0;}
        Map<Integer,Integer> visited = new HashMap<>();
        int result=0;
        for (int i=0;i<array.length;i++) {
            result+=visited.getOrDefault(array[i]+target,0)+visited.getOrDefault(array[i]-target,0);
            visited.put(array[i],visited.getOrDefault(array[i],0)+1);
        }
        return result;
        // Write your solution here
    }
    public int[] twoDiff(int[] array, int target) {
        if (array==null || array.length<2) {return new int[0];}
        int[] result=new int[2];
        int len=array.length;
        for (int i=0;i<array.length-1;i++) {
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
    }
    public int pairs2(int[] array, int target) {
        if (array==null || array.length<2) {return 0;}
        Arrays.sort(array);
        int result=0;
        for (int i=0;i<array.length;i++) {
            int need=array[i]+Math.abs(target);
            int left=i+1,right=array.length-1;
            while (left<right) {
                if (array[left]==need && array[right]==need) {break;}
                int mid=left+(right-left)/2;
                if (array[mid]>need) {
                    right=mid-1;
                } else if (array[mid]<need) {
                    left=mid+1;
                } else {
                    if (array[left]<need) {
                        left++;
                    }
                    if (array[right]>need) {
                        right--;
                    }
                }
            } // end while
            if (left==right && array[left]==need || left<right) {
                result+=right-left+1;
            }
        }
        return result;
    }
    /*
    375. Minimum Three Array Distance
    Given three sorted integer arrays, pick one element from each of them, what is the min value of |x - y| + |y - z| + |z - x|.
    Assumptions:
    The given three arrays are not null or empty.
    Examples:
    a = {1, 2, 3}
    b = {4, 5}
    c = {3, 4}
    The minimum value is |3 - 4| + |4 - 4| + |4 - 3| = 2
     */
    public int minDistance(int[] a, int[] b, int[] c) { // 375
        if (a==null || b==null || c==null) {return -1;}
        int la=a.length, lb=b.length, lc=c.length;
        if (la==0 || lb==0 || lc==0) {return -1;}
        int mindis=Math.max(Math.max(a[la-1],b[lb-1]),c[lc-1])-Math.min(Math.min(a[0],b[0]),c[0]);
        int j=0,k=0;
        for (int i=0;i<la;i++) {
            j = binarySearch(b,a[i],j,lb-1);
            k = binarySearch(c,a[i],k,lc-1);
            for (int l=0;l<4;l++) {
                int stepj=l/2; // 0 or 1
                int stepk=l%2; // 0 or 1
                if (j+stepj==lb || k+stepk==lc) {continue;}
                int abcmax=Math.max(a[i],Math.max(b[j+stepj],c[k+stepk]));
                int abcmin=Math.min(a[i],Math.min(b[j+stepj],c[k+stepk]));
                mindis=Math.min(mindis,abcmax-abcmin);
            }
        } // end for
        return mindis*2;
        // Write your solution here
    }
    private int binarySearch(int[] array, int target, int left, int right) {
        if (left>right) {return -1;}
        while (left<right-1) {
            int mid=left+(right-left)/2;
            if (array[mid]==target) {
                return mid;
            } else if (array[mid]>target) {
                right=mid-1;
            } else { // array[mid]<target
                left=mid;
            }
        }
        return array[right]<=target?right:left; // not standard binary search
    }
    /*
    376. Ascending Triple I
    Determine if the given integer array has three indices such that i < j < k and a[i] < a[j] < a[k].
    Assumptions:
    The given array is not null.
    Examples:
    {1, 5, 2, 4}, return true since i = 0, j = 2, k = 3
    {4, 3, 2, 1}, return false
     */
    public boolean existIJK(int[] array) { // 376
        if (array==null || array.length<2) {return false;}
        // int len=array.length;
        // int left=0,right=len-1;
        // while (left<len-2 && array[left]>array[left+1]) {
        //   left++;
        // }
        // if (array[left]>array[left+1]) {return false;}
        // while (right>1 && array[right]<array[right-1]) {
        //   right--;
        // }
        // if (array[right]<array[right-1]) {return false;}
        // if (array[left+1]<array[right-1]) {return true;}
        // for (int i=left+1;i<=right-1;i++) {
        //   if (array[i]>array[left] && array[i]<array[right]) {return true;}
        // }
        // return false;
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : array) {
            if (n <= small) { small = n; } // update small if n is smaller than both
            else if (n <= big) { big = n; } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
        // Write your solution here
    }
    /*
    468. Increasing Triplet Subsequence
    Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
    Formally the function should:
    Return true if there exists i, j, k
    such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
    Your algorithm should run in O(n) time complexity and O(1) space complexity.
    Examples:
    Given [1, 2, 3, 4, 5],
    return true.
    Given [5, 4, 3, 2, 1],
    return false.
     */
    public boolean increasingTriplet(int[] nums) { // 468
        if (nums==null || nums.length<3) {return false;}
        int first_num = Integer.MAX_VALUE;
        int second_num = Integer.MAX_VALUE;
        for (int n: nums) {
            if (n <= first_num) {
                first_num = n;
            } else if (n <= second_num) {
                second_num = n;
            } else {
                return true;
            }
        }
        return false;
//        int[] result = new int[4];
//        result[0]=Integer.MIN_VALUE;
//        return helper(nums,0,1,result);
        // Write your solution here
    }
    private boolean helper(int[] nums, int index, int add, int[] result) {
        if (add==result.length) {return true;}
        if (nums.length-index<result.length-add) {return false;}
        if (nums[index]<=result[add-1]) {
            return helper(nums,index+1,add,result);
        } else { // might or might not add
            result[add]=nums[index];
            return helper(nums,index+1,add+1,result) || helper(nums,index+1,add,result);
        }
    }
    /*
    572. Maximum Sum Partition
    Suppose there are 2*N integers. Try to group these integers into N pairs (x1, y1), ..., (xN, yN). so that the sum of the minimum value of each pair(xi, yi) (1 <= i <= N) could be as large as possible. Return the maximum sum.
    Example:
    Input: [5,7,3,1]
    Output: 6
    Explanation: Pairs (5,7), (3,1) give the result 6. min(5,7) + min(3,1) = 6.
    Assumptions:
    N is a positive integer within the range of [1, 10000].
    Integers in the given array are in the range of [-10000, 10000].
    Follow up:
    Can you approve that the result returned by your solution is the maximum?
     */
    public int maxPairSum(int[] nums) { // 572
        if (nums==null || nums.length==0) {return 0;}
        int len=nums.length;
        if (len%2==1) {return 0;}
        Arrays.sort(nums);
        int sum=0;
        for (int i=0;i<len;i+=2) {
            sum+=nums[i];
        }
        return sum;
        // Write your solution here
    }
    public static void main(String[] args) {
        Pairs solution = new Pairs();
//        int[] input = new int[] {3,2};
//        System.out.println(solution.pairs(input,1));
//        int[] input = new int[]{3,9,1,2,3,2,1};
//        System.out.println(solution.allPairs(input,4));
//        int[] input = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//        System.out.println(solution.allTriples(input, 3));
//        int[] input = new int[] {19,52,52,75,37,99,52,195,89,127,71,142,94};
//        System.out.println(solution.numOfTriangles(input));
//        int[] input = new int[] {1,4,8,14};
//        solution.printArray(solution.twoDiff(input,7));
        //int[] a = new int[] {80,81,82,83,84,85,86,87,88,90,91,92,93,95};
        //int[] b = new int[] {77,78};
        //int[] c = new int[] {87,88,89};
        //int[] a = new int[]{419,420,421,426,428,429,434,437,441,442,443,449,450,452,455,459,460,462,464,465,467,472,473,477,479,482,484,490,491,492,495,497,499,501,502,503,505,507,508,510,511,512,513,514,515,519,520,522,523,532,533,534,537,538,539,541,543,544,545,546,549,550,551,552,553,555,556,562,564,565,566,568,569,571,572,573,574,579,580,581,582,585,587,589,592,596,598,604};
        //int[] b = new int[]{617,631,632,633,637,644,647,658,673,675,677,688,691,694,695,696,697,702,710,719,726,728,734,736,750,753,757,777,779,781,783,785,786,788,789,791,798,801,803,810,814,815,820,823,824,830,832,840,842,844,847,848,849,851,853,854,855,857,869,870,881,888,891,892,900,903,905,908,909,917,921,924,927,929,931,939};
        //int[] c = new int[]{412,413,414,415,418,419,432,435,443,445,447,450,453,463,471,472,477,482,487,493,496,497,499,507,516,517,519,520,525,531,535,537,542,544,548,549,550,558,560,563,564,570,586,601,605,607,609,611,614,619,623,625,626,633,640,646,648,653,657,663,664,665,668,669,670,679,680,685,692,696,700,703,707,708,710,713,716,717,722,723,733,735,736,745,748,750,751,754,756,758,762,771,775,791,792,796,797,801,807,811,812,822,824,825,833,836,837,838,840,848,852,859,860,867,869,871,876,877,879,882,895,901,906,914,918,919,920,923,931,932,933};
        int[] a = new int[]{1,2,3};
        int[] b = new int[]{4,5};
        int[] c = new int[]{3,4};
        System.out.println(solution.minDistance(a,b,c));
    }
}
