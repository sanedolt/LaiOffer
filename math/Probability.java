package com.laioffer.math;
import java.util.*;

class RandomFive {
    static int random5() {
        Random random = new Random();
        return (int) random.nextInt(5);
    }
}
public class Probability {
    public void shuffle(int[] array) { // 108
        if (array==null || array.length<=1) {return;}
        int len=array.length;
        for (int i=len-1;i>=0;i--) {
            int temp = (int) (Math.random() * (i+1));
            swap(array,i,temp);
        }
        // Write your solution here.
    }
    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left]=array[right];
        array[right]=temp;
    }
    public int random7() { // 111
        // write your solution here
        // you can use RandomFive.random5() for generating
        // 0 - 4 with equal probability.
        // int sum=0;
        // for (int i=0;i<7;i++) {
        //   sum+=RandomFive.random5();
        // }
        // return sum%7;
        while (true) {
            int random=5*RandomFive.random5()+RandomFive.random5(); // equal chance of 0~24?
            if (random<21) {
                return random%7;
            }
        }
    }
    public int random1000() { // 112
        // Write your solution here.
        // you can use RandomFive.random5() for generating
        // 0 - 4 with equal probability.
        // int sum=0;
        // for (int i=0;i<8;i++) {
        //   sum+=RandomFive.random5();
        // }
        // int random8=sum%8;
        // int per200 = RandomFive.random5();
        // int per40 = RandomFive.random5();
        // int per8 = RandomFive.random5();
        // return per200*200+per40*40+per8*8+random8;
        while (true) {
            int num=0;
            for (int i=0;i<5;i++) {
                num=num*5+RandomFive.random5();
            } // equal chance of 0~3124
            if (num<3000) {
                return num%1000;
            }
        }
    }
    public int percentile95(List<Integer> lengths) { // 114
        if (lengths==null || lengths.size()==0) {return 0;}
        int len=lengths.size();
        int[] count = new int[4097];
        for (int l:lengths) {
            count[l]++;
        }
        int sum=0,ll=4097;
        while (sum<=0.05*len) {
            sum+=count[--ll];
        }
        return ll;
        // Write your solution here.
    }
    public class Solution109 { // 109
        private List<Integer> flow;
        private int length;
        private Random random;
        private Integer sample;
        public Solution109() {
            this.flow = new ArrayList<>();
            this.random = new Random();
            this.length=0;
            this.sample=null;
            // Write your constructor code here if necessary.
        }

        public void read(int value) {
            flow.add(value);
            length++;
            int index = (int) random.nextInt(length);
            sample=flow.get(index);
            // Write your implementation here.
        }

        public Integer sample() {
            return sample;
            // Write your implementation here.
        }
    }
    public class Solution110 { // 110
        private final int k;
        private List<Integer> flow;
        private int length;
        private Random random;
        private List<Integer> sample;
        public Solution110(int k) {
            if (k<=0) {
                throw new IllegalArgumentException("k must be positive");
            }
            this.flow = new ArrayList<>();
            this.random = new Random();
            this.length=0;
            this.sample = new ArrayList<>();
            // Complete the constructor if necessary.
            this.k = k;
        }

        public void read(int value) {
            length++;
            flow.add(value);
            if (length<=k) {
                sample.add(value);
            } else {
                int index=(int) random.nextInt(length);
                if (index<k) {
                    sample.set(index,value);
                }
            }
            // Write your implementation here.
        }

        public List<Integer> sample() {
            return sample;
            // Write your implementation here.
        }
    }

    public static void main(String[] args) {
        Probability solution = new Probability();
    }
}
