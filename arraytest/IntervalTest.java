package com.laioffer.arraytest;
import java.util.*;

class Interval {
    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
public class IntervalTest {
    /*
    266. Merge Intervals
    Given a set of time intervals in any order, return the total length of the area covered by those intervals.
    Assumptions:
    The given array of intervals is not null.
     */
    public int length(List<Interval> intervals) { // 266
        if (intervals==null || intervals.size()==0) {return 0;}
        int si = intervals.size();
        int[] record = new int[si*2];
        for (int i=0;i<si;i++) {
            record[i*2+0]=intervals.get(i).start;
            record[i*2+1]=-intervals.get(i).end;
        }
        quickSort(record,0,si*2-1);
        int sum=0,cur=0,left=0;
        for (int i=0;i<si*2;i++) {
            if (record[i]>0) {
                if (cur++==0) {left=record[i];}
            } else {
                if (--cur==0) {sum+=-record[i]-left;}
            }
        }
        return sum;
        // Write your solution here.
    }
    private void quickSort(int[] array, int left, int right) {
        if (left>=right) {return;}
        int pivotIndex = partition(array,left,right);
        quickSort(array,left,pivotIndex-1);
        quickSort(array,pivotIndex+1,right);
    }
    private int partition(int[] array, int left, int right) {
        int pivotIndex = left+(int) Math.random()*(right-left+1);
        swap(array,pivotIndex,right);
        int leftBound=left,rightBound=right-1;
        while (leftBound<=rightBound) {
            if (Math.abs(array[leftBound])<Math.abs(array[right])) {
                leftBound++;
            } else if (Math.abs(array[rightBound])>Math.abs(array[right])) {
                rightBound--;
            } else {
                swap(array,leftBound++,rightBound--);
            }
        }
        swap(array,leftBound,right);
        return leftBound;
    }
    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
    /*
    393. Login Numbers Per Interval
    Given a list of login sessions with start and end timestamps.
    Get the list of intervals with number of users logged in. Intervals with 0 number of users logged in should not be included.
    Examples:
    login sessions: [[1, 2], [0, 4], [5, 6]]
    return [[0, 1, 1], [1, 2, 2], [2, 4, 1], [5, 6, 1]]
    at (0, 1) there is 1 user logged in.
    at (1, 2) there is 2 user logged in.
    at (2, 4) there is 1 user logged in (one user logged out at 2).
    at (5, 6) there is 1 user logged in.
     */
    public int[][] sessionNum(int[][] logins) { // 393
        if (logins==null || logins.length==0 || logins[0].length==0) {return logins;}
        int len=logins.length,index=0;
        int[] session = new int[len*2];
        for (int[] login : logins) {
            session[index++]=login[0];
            session[index++]=-login[1];
        }
        int[] helper = new int[len*2];
        mergeSort(session,helper,0,len*2-1);
        List<int[]> output = new ArrayList<>();
        int prev=0,curr=0,left=0;
        for (int i=0;i<len*2;i++) {
            int right=Math.abs(session[i]);
            curr+=session[i]>=0?1:-1;
            if (i==len*2-1 || right<Math.abs(session[i+1]) && curr!=prev) {
                if (prev>0) {
                    output.add(new int[]{left,right,prev});
                }
                left=right;
                prev=curr;
            }
        }
        return output.toArray(new int[output.size()][]);
        // Write your solution here
    }
    private void mergeSort(int[] session, int[] helper, int left, int right) {
        if (left>=right) {return;}
        int mid=left+(right-left)/2;
        mergeSort(session,helper,left,mid);
        mergeSort(session,helper,mid+1,right);
        merge(session,helper,left,mid,right);
    }
    private void merge(int[] session, int[] helper, int left, int mid, int right) {
        for (int i=left;i<=right;i++) {
            helper[i]=session[i];
        }
        int leftIndex=left,rightIndex=mid+1,curIndex=left;
        while (leftIndex<=mid && rightIndex<=right) {
            if (Math.abs(helper[leftIndex])<Math.abs(helper[rightIndex])) {
                session[curIndex++]=helper[leftIndex++];
            } else {
                session[curIndex++]=helper[rightIndex++];
            }
        }
        while (leftIndex<=mid) {
            session[curIndex++]=helper[leftIndex++];
        }
    }
//    private void swap(int[] session, int left, int right) {
//        int tmp = session[left];
//        session[left]=session[right];
//        session[right]=tmp;
//    }
    /*
    482. Meeting Rooms
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
    For example,
    Given [[0, 30],[5, 10],[15, 20]],
    return false.
     */
    public boolean canAttendMeetings(int[][] intervals) { // 482
        if (intervals==null || intervals.length==0) {return true;}
        int len=intervals.length;
        quickSort(intervals,0,len-1);
        for (int i=1;i<len;i++) {
            if (intervals[i][0]<intervals[i-1][1]) {return false;}
        }
        return true;
        // Write your solution here
    }
    private void swap(int[][] array, int left, int right) {
        int temp=array[left][0];
        array[left][0]=array[right][0];
        array[right][0]=temp;
        temp=array[left][1];
        array[left][1]=array[right][1];
        array[right][1]=temp;
    }
    private int pivotIndex(int left, int right) {
        return left+(int)(Math.random()*(right-left+1));
    }
    private int partition(int[][] array, int left, int right) {
        int pivotIndex=pivotIndex(left,right);
        int pivot=array[pivotIndex][0];
        swap(array,pivotIndex,right);
        int leftBound=left,rightBound=right-1;
        while (leftBound<=rightBound) {
            if (array[leftBound][0]<pivot) {
                leftBound++;
            } else if (array[rightBound][0]>pivot) {
                rightBound--;
            } else {
                swap(array,leftBound++,rightBound--);
            }
        }
        swap(array,leftBound,right);
        return leftBound;
    }
    private void quickSort(int[][] array, int left, int right) {
        if (left>=right) {return;}
        int pivotPos=partition(array,left,right);
        quickSort(array, left, pivotPos-1);
        quickSort(array,pivotPos+1,right);
    }
    /*
    494. Meeting rooms II
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
    For example,
    Given [[0, 30],[5, 10],[15, 20]],
    return 2.
     */
//    private void swap(int[][] array, int left, int right) {
//        int temp=array[left][0];
//        array[left][0]=array[right][0];
//        array[right][0]=temp;
//        temp=array[left][1];
//        array[left][1]=array[right][1];
//        array[right][1]=temp;
//    }
//    private int pivotIndex(int left, int right) {
//        return left+(int)(Math.random()*(right-left+1));
//    }
//    private int partition(int[][] array, int left, int right) {
//        int pivotIndex=pivotIndex(left,right);
//        int pivot=array[pivotIndex][0];
//        swap(array,pivotIndex,right);
//        int leftBound=left,rightBound=right-1;
//        while (leftBound<=rightBound) {
//            if (array[leftBound][0]<pivot) {
//                leftBound++;
//            } else if (array[rightBound][0]>pivot) {
//                rightBound--;
//            } else {
//                swap(array,leftBound++,rightBound--);
//            }
//        }
//        swap(array,leftBound,right);
//        return leftBound;
//    }
//    private void quickSort(int[][] array, int left, int right) {
//        if (left>=right) {return;}
//        int pivotPos=partition(array,left,right);
//        quickSort(array,left,pivotPos-1);
//        quickSort(array,pivotPos+1,right);
//    }
    public int minMeetingRooms(int[][] intervals) { // 494
        if (intervals==null || intervals.length==0) {return 0;}
        int len=intervals.length;
        if (len==1) {return 1;}
        quickSort(intervals,0,len-1);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int num=0;
        for (int[] itv : intervals) {
            if (pq.isEmpty() || itv[0]<pq.peek()) {
                num++;
            } else {
                pq.poll();
            }
            pq.offer(itv[1]);
        }
        return num;
        // Write your solution here
    }
    /*
    599. Meeting Schedule
    Duration of a meeting could be represented as a time interval using an array [s, e] (s < e) where s means start time and e mean end time.
    Given a list of meeting time intervals[[s0, e0],[s1, e1]......], return the maximum number of meetings a person could attend. A person could attend two meetings [si, ei] and [sj, ej] only when ei  < sj.
    Example:
    Input = [[1,2],[2,3],[3,4],[4,5]]
    Output = 2
    Explanation: The person could attend two meetings either [[1,2], [3,4]] or [[2,3], [4,5].
     */
    public int maximumMeetings(int[][] intervals) { // 599
        if (intervals==null || intervals.length==0) {return 0;}
        int len=intervals.length;
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return a[1]-b[1];
            }
        });
        int res=1, end = intervals[0][1];
        for (int[] i : intervals) {
            if (i[0]<=end) {continue;}
            res++;
            end=i[1];
        }
        return res;
        // Arrays.sort(intervals,new Comparator<int[]>(){
        //   @Override
        //   public int compare(int[] a, int[] b) {
        //     return a[0]-b[0];
        //   }
        // });
        // int[] dp = new int[len];
        // Arrays.fill(dp,1);
        // for (int i=1;i<len;i++) {
        //   for (int j=i-1;j>=0;j--) {
        //     if (intervals[i][0]>intervals[j][1]) {
        //       dp[i]=Math.max(dp[i],dp[j]+1);
        //     }
        //   }
        // }
        // return dp[len-1];
        // Write your solution here
    }

    /*
    629. Execution Time of Functions
    Suppose there is a single-threaded CPU that could run only one function each time. We store execution logs in time order that describe jobs starting or ending. Each log is formatted as {function_id}:{"start"}:{timestamp} or {function_id}:{"end"}:{timestamp}
    For example, "0:start:1" means function 0 starts at timestamp 1; "0:end:2" means function 0 ends at timestamp 2.
    The duration of a function is the total number of time units that the single-threaded CPU spends on running the function.
    Given an integer n and a list of execution logs, suppose we have n functions with id of 0 to n - 1, return the duration of each job in the order of job id.
    Example:
    Input: 3, ["0:start:0","1:start:2","1:end:5","2:start:6","2:end:6", "0:end:7"]
    Output: [3,4,1]
    Explanation: the function running in each time unit is:
    time unit     |    function
    0            |    function 0 (start)
    1            |    function 0
    2            |    function 1 (start)
    3            |    function 1
    4            |    function 1
    5            |    function 1 (end)
    6            |    function 2 (start and end)
    7            |    function 0 (end)
    There are three functions (0, 1, 2). Function 0 runs 3 time units (0, 1, 7). Function 1 runs 4 time units(2,3,4,5). Function 2 runs 1 time unit(6), so the return value is [3, 4, 1].
     */
    public int[] executeTime(int n, List<String> logs) { // 629
        if (logs==null || n<=0 || logs.size()!=n*2) {return new int[0];}
        int[] result = new int[n];
        int[] beg = new int[n];
        int[] end = new int[n];
        Deque<Integer> function = new ArrayDeque<>();
        Deque<Integer> interrupt = new ArrayDeque<>();
        for (int i=0;i<n*2;i++) {
            String cur = logs.get(i);
            int len=cur.length(),num=0,curId=-1;
            boolean start = false;
            for (int j=0;j<len;j++) {
                char ch = cur.charAt(j);
                if (ch>='0' && ch<='9') {
                    num=num*10+ch-'0';
                } else if (ch==':') {
                    curId=num;
                    num=0;
                    if (cur.charAt(j+1)=='s') {start=true;j+=6;} else {start=false;j+=4;} // jump over the letter
                }
            }
            if (start) {
                beg[curId]=num;
                function.offerFirst(curId);
                interrupt.offerFirst(0);
            } else { // end
                end[curId]=num;
                function.pollFirst();
                int interval=end[curId]-beg[curId]+1;
                result[curId]=interval-interrupt.pollFirst();
                if (interrupt.size()>0) {
                    int tmp = interrupt.pollFirst();
                    tmp+=interval;
                    interrupt.offerFirst(tmp);
                }
            }
        }
        return result;
        // Write your solution here
    }
    public int mostBooked(int n, int[][] meetings) {
        if (n==1) {return 0;}
        Arrays.sort(meetings,(a,b)->Integer.compare(a[0],b[0]));
        int[] count = new int[n];
        // int[] as room, next available time
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare (int[] a, int[] b) {
                if (a[1]==b[1]) {
                    return Integer.compare(a[0],b[0]);
                    // return a[0]-b[0];
                } else {
                    // return Integer.compare(a[1],b[1]);
                    return a[1]-b[1];
                }
            }
        });

        int len = meetings.length;
        for (int i=0;i<n;i++) {
            pq.offer(new int[]{i,0});
        }
        int result=0;
        for (int i=0;i<len;i++) {

            while (pq.peek()[1] < meetings[i][0]) {// order all available room by room index
                pq.offer(new int[] { pq.poll()[0],meetings[i][0] });
            }

            int[] current = pq.poll();
            int curRoom = current[0];
            int meetingEndTime = current[1] + (meetings[i][1] - meetings[i][0]); // current room endTime + this meeting time
            count[curRoom]++;

            if (count[curRoom] > count[result]) { // update result
                result = curRoom;
            } else if (count[curRoom] == count[result]) {
                result = Math.min(result, curRoom);
            }

            pq.offer(new int[] { curRoom, meetingEndTime});
            
        }
        return result;
    }
    public static void main(String[] args) {
        IntervalTest solution = new IntervalTest();
//        Interval interval1 = new Interval (7,10);
//        Interval interval2 = new Interval (6,8);
//        Interval interval3 = new Interval (1,4);
//        Interval interval4 = new Interval (4,7);
//        List<Interval> intervals = new ArrayList<>();
//        intervals.add(interval1);
//        intervals.add(interval2);
//        intervals.add(interval3);
//        intervals.add(interval4);
//        System.out.println(solution.length(intervals));
        int[][] intervals = new int[][]{{30,210},{55,251},{205,229},{110,218},{30,99},{0,249},{143,191}};
//        System.out.println(Arrays.deepToString(solution.sessionNum(intervals)));

    }

}
