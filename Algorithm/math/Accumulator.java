package com.laioffer.Algorithm.math;

public class Accumulator { // 544
    int s;
    public void add(int x) {
        s+=x;
    }
    public int sum() {
        return s;
    }

    public static void main(String[] args) {
        Accumulator tryme = new Accumulator();
        tryme.add(2);
        tryme.add(3);
        System.out.println(tryme.sum());
    }
}
