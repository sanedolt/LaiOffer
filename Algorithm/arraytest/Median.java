package com.laioffer.Algorithm.arraytest;
import java.util.*;

public class Median {
    List<Integer> input;
    public Median() {
        this.input = new ArrayList<>();
        // add new fields and complete the constructor if necessary.
    }
    public void read(int value) {
        this.input.add(value);
        move(this.input);
        // write your implementation here.
    }
    public Double median() {
        int len = this.input.size();
        if (len == 0) {
            return null;
        } else if (len % 2 == 1) {
            return (double) this.input.get(len / 2);
        } else { // len%2==0
            return (double) (this.input.get(len / 2) + this.input.get(len / 2 - 1)) / 2.0;
        }
        // write your implementation here.
    }
    private void move(List<Integer> array) {
        int len = array.size();
        if (len<=1) {return;}
        int target=array.get(len-1);
        int cur = len - 2;
        while (array.get(cur) > target) {
            swap(array, cur, cur + 1);
            cur--;
        }
    }
    private void swap(List<Integer> array, int a, int b) {
        int temp = array.get(a);
        array.set(a, array.get(b));
        array.set(b, temp);
    }
    public static void main(String[] args) {
        Median solution = new Median();
        solution.read(1);
        System.out.println(solution.median());
        solution.read(2);
        System.out.println(solution.median());
        solution.read(3);
        System.out.println(solution.median());
        solution.read(4);
        System.out.println(solution.input);
        System.out.println(solution.median());
    }

}
