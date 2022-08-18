package com.laioffer.queuestack;

public class ShortestDistance {
    /*
    477. Shortest Word Distance
    Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
    For example,
    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
    Given word1 = “coding”, word2 = “practice”, return 3.
    Given word1 = "makes", word2 = "coding", return 1.
    Note:
    You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
     */
    public int shortestDistance(String[] words, String word1, String word2) { // 477
        if (words==null) {return -1;}
        int len=words.length;
        int ind1=-1,ind2=-1,mindist=len;
        for (int i=0;i<len;i++) {
            if (words[i].equals(word1)) {
                ind1=i;
                if (ind2>-1 && ind1-ind2<mindist) {
                    mindist=ind1-ind2;
                }
            } else if (words[i].equals(word2)) {
                ind2=i;
                if (ind1>-1 && ind2-ind1<mindist) {
                    mindist=ind2-ind1;
                }
            }
        }
        return mindist<len?mindist:-1;
        // Write your solution here
    }
    /*
    478. Shortest Word Distance III
    This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
    Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
    word1 and word2 may be the same and they represent two individual words in the list.
    For example,
    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
    Given word1 = “makes”, word2 = “coding”, return 1.
    Given word1 = "makes", word2 = "makes", return 3.
    Note:
    You may assume word1 and word2 are both in the list.
     */
    public int shortestWordDistance(String[] words, String word1, String word2) { // 478
        if (words==null) {return -1;}
        int len=words.length;
        int ind1=-1,ind2=-1,mindist=len;
        int unit = word1.equals(word2)?1:0;
        int shift=0;
        for (int i=0;i<len;i++) {
            if (words[i].equals(word1) && shift%2==0) {
                ind1=i;
                shift+=unit;
                if (ind2>-1 && ind1-ind2<mindist) {
                    mindist=ind1-ind2;
                }
            } else if (words[i].equals(word2)) {
                ind2=i;
                shift+=unit;
                if (ind1>-1 && ind2-ind1<mindist) {
                    mindist=ind2-ind1;
                }
            }
        }
        return mindist<len?mindist:-1;
        // Write your solution here
    }
    public static void main(String[] args) {
        ShortestDistance solution = new ShortestDistance();
        String[] words = new String[]{"practice","makes","perfect","coding","makes","coding"};
        System.out.println(solution.shortestDistance(words,"coding","practice"));
        System.out.println(solution.shortestWordDistance(words,"coding","practice"));
    }
}
