package com.laioffer.Algorithm.queuestack;
import java.util.*;

class Frequency {
    String str;
    int freq;
    Frequency(String str, int freq) {
        this.str = str;
        this.freq = freq;
    }
}
public class Frequent {
    /*
    289. String Frequency In Windows
    Given a string containing only 'A', 'B', 'C', 'D', return the number of occurrences of substring which has length 4 and includes all of the characters from 'A', 'B', 'C', 'D' with in descending sorted order.
    Assumptions:
    The given string is not null and has length of >= 4.
    In the output, if two substrings have the same frequency, then they should be sorted by the their natural order.
    Examples:
     “ABCDABCDD”, --> {"ABCD" : 2, "BCDA" : 1, "CDAB" : 1, "DABC" : 1}
     */
    public List<Frequency> frequency(String input) { // 289
        if (input==null || input.length()<4) {return null;}
        int len=input.length();
        Map<Integer,Integer> appeared = new HashMap<>();
        Map<Character,Integer> around = new HashMap<>();
        for (int i=0;i<len;i++) {
            char ch=input.charAt(i);
            Integer prev = around.get(ch);
            if (prev==null) {
                if (around.size()==3) {
                    addInte(appeared,strToInt(input,i));
                    delChar(around,input.charAt(i-3));
                }
            } else {
                for (int j=i-3;j<=prev;j++) {
                    if (j<0) {continue;}
                    delChar(around,input.charAt(j));
                } // delete characters until hit the previous ch
            }
            around.put(ch,i);
        }
        return printMap(appeared);
        // Write your solution here.
    }
    private List<Frequency> printMap(Map<Integer,Integer> appeared) {
        int k = appeared.size();
        PriorityQueue<Frequency> pq = new PriorityQueue<>(k,new Comparator<Frequency>(){
            public int compare (Frequency a, Frequency b) {
                if (a.freq==b.freq) {
                    return Integer.valueOf(strToInt(a.str,3)).compareTo(Integer.valueOf(strToInt(b.str,3)));
                } else {
                    return Integer.valueOf(b.freq).compareTo(Integer.valueOf(a.freq));
                }
            }
        });
        for (Map.Entry<Integer,Integer> e : appeared.entrySet()) {
            pq.offer(new Frequency(intToStr(e.getKey()),e.getValue()));
        }
        List<Frequency> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        return result;
    }
    private int strToInt (String input, int index) {
        return (input.charAt(index-3)-64)*1000+(input.charAt(index-2)-64)*100+(input.charAt(index-1)-64)*10+(input.charAt(index)-64);
    }
    private String intToStr (int index) {
        int a=1000;
        StringBuilder result = new StringBuilder();
        for (int i=0;i<4;i++,a/=10) {
            result.append((char)(index/a+64));
            index%=a;
        }
        return new String(result);
    }
    private void addInte (Map<Integer,Integer> map, int tt) {
        Integer cn = map.get(tt);
        map.put(tt,cn==null?1:cn+1);
    }
    private void delChar (Map<Character,Integer> map, char tt) {
        map.remove(tt);
    }
    public String[] topKFrequent(String[] combo, int k) { // 67
        if (combo==null || combo.length==0) {return combo;}
        Map<String,Integer> freq = getMap(combo);
        PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<>(k,new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare (Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        for (Map.Entry<String,Integer> fq : freq.entrySet()) {
            if (pq.size()<k || fq.getValue()>pq.peek().getValue()) {
                if (pq.size()==k) {pq.poll();}
                pq.offer(fq);
            }
        }
        return freqArray(pq);
        // Write your solution here
    }
    private Map<String,Integer> getMap(String[] combo) {
        Map<String,Integer> freq = new HashMap<>();
        for (String com : combo) {
            Integer cn = freq.get(com);
            freq.put(com,cn==null?1:cn+1);
        }
        return freq;
    }
    private String[] freqArray(PriorityQueue<Map.Entry<String,Integer>> pq) {
        int count=pq.size();
        String[] result = new String[count];
        for (int i=count-1;i>=0;i--) {
            result[i]=pq.poll().getKey();
        }
        return result;
    }
    public static void main(String[] args) {
        Frequent solution = new Frequent();
        String input = "CABDACBCCDADCB";;
        List<Frequency> result = solution.frequency(input);
        for (int i=0;i<result.size();i++) {
            System.out.println(result.get(i).str+result.get(i).freq);
        }
        String[] input2 = new String[] {"d","a","c","b","d","a","b","b","a","d","d","a","d"};
        System.out.println(solution.topKFrequent(input2,5));
    }
}
