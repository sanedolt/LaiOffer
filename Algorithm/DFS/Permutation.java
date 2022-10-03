package com.laioffer.Algorithm.DFS;
import java.util.*;

public class Permutation {
    private void printArray (int[] array) {
        System.out.println(Arrays.toString(array));
    }
    private void swap(int[] array, int left, int right) {
        int temp=array[left];
        array[left]=array[right];
        array[right]=temp;
    }
    public int[] nextPermutation(int[] num) {
        if (num==null) {return num;}
        int l=num.length;
        if (l<=1) {return num;}
        int node=-1;
        for (int i=l-1;i>0;i--) {
            if (num[i]>num[i-1]) {node=i-1;break;}
        }
        System.out.println(l);
        System.out.println(node);
        if (node==-1) { // reverse order
            for (int i=0;i<l/2;i++) {
                swap(num,i,l-1-i);
            }
        } else if (node==l-2) {
            swap(num,l-1,l-2);
        } else {
            int tar=l;
            for (int i=l-1;i>node;i--) {
                if (num[i]>num[node]) {tar=i;break;}
            }
            swap(num, node, tar);
            for (int i=node+1;i<=(node+l)/2;i++) {
                swap(num,i,l+node-i);
            }
            printArray(num);
        }
        return num;
    }
    public String getPermutation(int n, int k) {
        char[] result = new char[n];
        int fac=1;
        for (int i=1;i<=n;i++) {
            fac*=i;
            result[i-1]=(char) (i+48);
        }
        System.out.println(result);
        helper(k,result,0,fac);
        return new String(result);
        // Write your solution here
    }
    private void helper(int k, char[] array, int index, int fac) {
        System.out.println(k+" "+index+" "+fac);
        System.out.println(array);
        int n = array.length;
        if (index==n) {return;}
        int unit = fac/(n-index);
        int num = (k-1)/unit;
        char temp = array[index+num];
        System.out.println(unit+" "+num+" "+temp);
        for (int i=index+num;i>index;i--) {
            array[i]=array[i-1];
        }
        array[index]=temp;
        helper(k-num*unit,array,index+1,unit);
    }
    public List<String> permutations(String input) { // 64
        if (input==null) {return null;}
        char[] array = input.toCharArray();
        List<String> result = new ArrayList<>();
        helper(array,result,0);
        return result;
        // Write your solution here
    }
    private void helper(char[] array, List<String> result, int index) {
        int len=array.length;
        if (index==len) {
            result.add(new String(array));
            return;
        }
        for (int i=index;i<len;i++) {
            swap(array,i,index);
            helper(array,result,index+1);
            swap(array,i,index);
        }
    }
    public List<String> permutations2(String input) { // 65
        if (input==null) {return null;}
        char[] array = input.toCharArray();
        List<String> result = new ArrayList<>();
        helper2(array,result,0);
        return result;
        // Write your solution here
    }
    private void helper2(char[] array, List<String> result, int index) {
        int len=array.length;
        if (index==len) {
            result.add(new String(array));
            return;
        }
        Set<Character> visited = new HashSet<>();
        for (int i=index;i<len;i++) {
            if (visited.add(array[i])) {
                swap(array,i,index);
                helper(array,result,index+1);
                swap(array,i,index);
            }
        }
    }
    private void swap(char[] array, int a, int b) {
        char tmp = array[a];
        array[a]=array[b];
        array[b]=tmp;
    }
    public static void main(String[] args) {
        Permutation solution = new Permutation();
//        input = new int[] {2,1,2,1};
//        solution.printArray(input);
//        solution.printArray(solution.nextPermutation(input));
        System.out.println(solution.getPermutation(6,100));
    }
}
