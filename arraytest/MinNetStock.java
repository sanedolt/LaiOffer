package com.laioffer.arraytest;

public class MinNetStock {
    public int findEarliestMonth(int[] array){
        int len = array.length;
        int sum = 0;
        for (int i=0;i<len;i++) {
            sum+=array[i];
        }
        int index=0;
        int m=Integer.MAX_VALUE;
        int leftSum=0;
        int min=0;
        for(int i=0;i<len-1;i++){
            leftSum+=array[i];
            min=Math.abs(leftSum/(i+1)-(sum-leftSum)/(len-1-i));

            if(m>=min){
                index=i;
                m=min;
            }
        }
        return index;
    }
//    public int findEarliestMonth(int[] array){
//        int[] min=new int[array.length-1];
//        int index=0;
//        int m=Integer.MAX_VALUE;
//        for(int i=0;i<array.length-1;i++){
//
//            min[i]=Math.abs(avg(array,0,i)-avg(array,i+1, array.length-1));
//
//            if(m>=min[i]){
//                index=i;
//                m=min[i];
//            }
//
//        }
//
//        return index;
//    }
    private int avg(int[] array, int left, int right){

        int sum=0;
        int month=right-left+1;
        while(right-left>=0){
            sum+=array[left++];
        }
        return sum/month;
    }

    public static void main(String[] args) {
        MinNetStock m=new MinNetStock();
        int[] array={5,1,3,2,4};
        System.out.println(m.findEarliestMonth(array));
    }
}
