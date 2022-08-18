package com.laioffer.optimized;
import java.util.*;

public class ArrayHopper {
    public boolean canJump1(int[] array) { // 88
        if (array==null || array.length==0) {return false;}
        int len=array.length;
        boolean[] canJump = new boolean[len];
        canJump[len-1]=true;
        for (int i=len-2;i>=0;i--) {
            if (i+array[i]>=len-1) {
                canJump[i]=true;
            } else {
                for (int j=array[i];j>=1;j--) {
                    if (canJump[j+i]) {
                        canJump[i]=true;
                        break;
                    }
                }
            }
        }
        return canJump[0];
        // Write your solution here
    }
    public boolean canJump2(int[] array) { // 88
        if (array==null || array.length==0) {return false;}
        int len=array.length;
        int reach=0;
        for (int i=0;i<len;i++) {
           if (i>reach) {return false;}
           reach=Math.max(reach,i+array[i]);
           if (reach>=len-1) {return true;}
        }
        return false;
        // Write your solution here
    }
    public boolean canJump3(int[] array) { // 88
        if (array==null || array.length==0) {return false;}
        int len=array.length;
        int cur=0,next=0;
        for (int i=0;i<len;i++) {
           if (i>cur) {
               if (cur==next) {
                   return false;
               }
               cur=next;
           }
           next=Math.max(next,i+array[i]);
        }
        return true;
        // Write your solution here
    }
    public boolean canJump4(int[] array) { // 88
        if (array==null || array.length==0) {return false;}
        int len=array.length;
        boolean[] canJump = new boolean[len];
        canJump[0]=true;
        for (int i=1;i<len;i++) {
           for (int j=0;j<i;j++) {
               if (canJump[j] && j+array[j]>=i) {
                   canJump[i]=true;
                   break;
               }
           }
        }
        return canJump[len-1];
        // Write your solution here
    }
    public int minJump891(int[] array) { // 89
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (len==1) {return 0;}
        if (array[0]==0) {return -1;}
        int[] minJump = new int[len];
        minJump[len-1]=0;
        for (int i=len-2;i>=0;i--) {
            minJump[i]=len;
            for (int j=array[i];j>0;j--) {
                if (i+j>=len-1) {
                    minJump[i]=1;
                } else {
                    minJump[i]=Math.min(minJump[i],minJump[i+j]+1);
                }
            }
        }
        return minJump[0]==len?-1:minJump[0];
        // Write your solution here
    }
    public int minJump892(int[] array) { // 89
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (len==1) {return 0;}
        if (array[0]==0) {return -1;}
        int[] minJump = new int[len];
        minJump[0]=0;
        for (int i=1;i<len;i++) {
           minJump[i]=len;
           for (int j=i-1;j>=0;j--) {
               if (j+array[j]>=i) {
                   minJump[i]=Math.min(minJump[i],minJump[j]+1);
               }
           }
        }
        return minJump[len-1]==len?-1:minJump[len-1];
        // Write your solution here
    }
    public int minJump893(int[] array) { // 89
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (len==1) {return 0;}
        if (array[0]==0) {return -1;}
        int[] minJump = new int[len];
        int reach=0,changed=0;
        for (int i=0;i<len;i++) {
           if (i>reach) {return -1;}
           reach=Math.max(reach,i+array[i]);
           if (reach>=len-1) {return minJump[i]+1;}
           for (int j=changed+1;j<=reach;j++) {
               minJump[j]=Math.min(minJump[j],minJump[i]+1);
           }
           changed=reach;
        }
        return -1;
        // Write your solution here
    }
    public int minJump894(int[] array) { // 89
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (len==1) {return 0;}
        if (array[0]==0) {return -1;}
        int jump=0,cur=0,next=0; // maximum using current jump
        for (int i=0;i<len;i++) {
           if (i>cur) {
               jump++;
               if (cur==next) {
                   return -1;
               }
               cur=next;
           }
           next=Math.max(next,i+array[i]);
        }
        return jump;
        // Write your solution here
    }
    public int minJump901(int[] array) { // 90
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (array[0]==0) {return -1;}
        int[] minJump = new int[len];
        for (int i=len-1;i>=0;i--) {
            if (i+array[i]>len-1) {
                minJump[i]=1;
            } else {
                minJump[i]=len+1;
                for (int j=array[i];j>0;j--) {
                    minJump[i]=Math.min(minJump[i],minJump[i+j]+1);
                }
            }
        }
        return minJump[0]<=len?minJump[0]:-1;
        // Write your solution here
    }
    public int minJump902(int[] array) { // 90
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (array[0]==0) {return -1;}
        int[] minJump = new int[len];
        int reach=0,changed=0;
        for (int i=0;i<len;i++) {
          if (i>reach) {return -1;}
          reach=Math.max(reach,i+array[i]);
          if (reach>=len) {return minJump[i]+1;}
          for (int j=Math.max(i,changed)+1;j<=reach;j++) {
            minJump[j]=Math.min(minJump[j],minJump[i]+1);
          }
          changed=reach;
        }
        return -1;
        // Write your solution here
    }
    public int minJump903(int[] array) { // 90
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        if (array[0]==0) {return -1;}
        int[] minJump = new int[len];
        int jump=0,cur=0,next=0;
        for (int i=0;i<len;i++) {
          if (i>cur) {
            jump++;
            if (cur==next) {
              return -1;
            }
            cur=next;
          }
          next=Math.max(next,i+array[i]);
        }
        if (cur>=len) {return jump;}
        if (next>=len) {return jump+1;}
        return -1;
        // Write your solution here
    }
    public int minJump(int[] array, int index) { // 91
        if (array==null || array.length==0) {return -1;}
        int len=array.length;
        Map<Integer,Integer> minJump = new HashMap<>();
        Queue<Integer> visited = new ArrayDeque<>();
        visited.add(index);
        minJump.put(index,0);
        while (!visited.isEmpty()) {
            int cur = visited.poll();
            int jump = minJump.get(cur);
            for (int i=cur-array[cur];i<=cur+array[cur];i++) {
                if (i>=0 && i<len) {
                    Integer jp = minJump.get(i);
                    if (jp==null || jump+1<jp) {
                        minJump.put(i,jump+1);
                        visited.add(i);
                    }
                }
            }
        }
        Integer jp = minJump.get(len-1);
        return jp==null?-1:jp;
        // Write your solution here
    }
    public static void main(String[] args) {
        ArrayHopper solution = new ArrayHopper();
        int[] array = new int[]{5,1,1,1,0,4};
        System.out.println(solution.minJump(array,0));
    }
}
