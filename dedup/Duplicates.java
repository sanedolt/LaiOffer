package com.laioffer.dedup;

import java.util.*;

public class Duplicates {
    /*
    437. Contains Duplicate
    Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
     */
    public boolean containsDuplicate(int[] nums) { // 437
        if (nums==null || nums.length==0) {return false;}
        Set<Integer> appeared = new HashSet<>();
        for (int i : nums) {
            if (appeared.contains(i)) {
                return true;
            } else {
                appeared.add(i);
            }
        }
        return false;
        // Write your solution here
    }
    /*
    438. Contains Duplicate II
    Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) { // 438
        if (nums==null || nums.length==0) {return false;}
        if (k<=0) {return false;}
        Set<Integer> appeared = new HashSet<>();
        int len=nums.length;
        for (int i=0;i<len;i++) {
            int num=nums[i];
            if (appeared.contains(num)) {return true;}
            if (i>=k) {
                appeared.remove(nums[i-k]);
            }
            appeared.add(num);
        }
        return false;
        // Write your solution here
    }
    /*
    442. Contains Duplicate III
    Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) { // 442
        if (nums==null || nums.length==0) {return false;}
        if (k<=0) {return false;}
        Set<Integer> appeared = new HashSet<>();
        int len=nums.length;
        // TC: O(n*t)
        for (int i=0;i<len;i++) {
            int num=nums[i];
            int min=num-t,max=num+t;
            for (int j=min;j<=max;j++) {
                if (appeared.contains(j)) {return true;}
            }
            if (i>=k) {
                appeared.remove(nums[i-k]);
            }
            appeared.add(num);
        }
        return false;
        // Write your solution here
    }
    public String removeDuplicateLetters(String input) {
        if (input==null || input.length()==0) {return input;}
        Map<Character, List<Integer>> appe = new HashMap<>();
        Map<Character,Integer> firstAppe = new HashMap<>();
        Map<Character,Integer> lastAppe = new HashMap<>();
        Set<Character> appeared = new HashSet<>();
        int len=input.length();
        boolean[] keeping = new boolean[len];
        Arrays.fill(keeping,true);
        for (int i=0;i<len;i++) {
            char cur = input.charAt(i);
            List<Integer> temp = new ArrayList<>();
            if (appe.get(cur)==null) {
                temp.add(i);
                appe.put(cur,temp);
                firstAppe.put(cur,i);
                lastAppe.put(cur,i);
                appeared.add(cur);
            } else {
                temp=appe.get(cur);
                temp.add(i);
                appe.replace(cur,temp);
                lastAppe.replace(cur,i);
            }
        } // end for
        String result = new String("");
        Set<Character> inserted = new HashSet<>();
        int lv = appe.size();
        for (int n=0;n<lv;n++) {
            System.out.println("n="+n);
            for (char i=97;i<123;i++) {
                if (appe.get(i)==null || inserted.contains(i)) {continue;}
                System.out.println("i="+i);
                int currFirst = firstAppe.get(i);
                boolean find=true;
                for (char j=97;j<123;j++) {
                    if (appe.get(j)==null || inserted.contains(j) || j==i) {continue;}
                    System.out.println("j="+j);
                    if (j=='q') {System.out.println(lastAppe.get(j)+" "+currFirst+" "+firstAppe.get(i));}
                    if (lastAppe.get(j)<currFirst) {
                        find=false;
                        break;
                    }
                } // end for j
                System.out.println("I am here1"+find);
                if (!find) {continue;} // next i
                result+=String.valueOf(i);
                System.out.println(result);
                inserted.add(i);
                System.out.println("I am here2");
                for (Character j: appeared) {
                    List<Integer> temp = appe.get(j);
                    if (inserted.contains(j)) {continue;}
                    System.out.println("I am here3"+j);
                    while (temp.get(0)<currFirst) {
                        System.out.println("I am here4");
                        temp.remove(0);
                        if (temp.size()>0) {
                            firstAppe.replace(j, temp.get(0));
                        } else {
                            appe.remove(j);
                        } // end if
                    } // end while
                } // end for j
                break;
            } // end for i
        } // end for n
        return result;
        // Write your solution here
    }
    public static void main(String[] args) {
        Duplicates solution = new Duplicates();
        //        String input = "jeaaeqenbgpjpztzuwwzukc";
        String input = "vmfvg";
        System.out.println(solution.removeDuplicateLetters(input));
    }
}
