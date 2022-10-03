package com.laioffer.Algorithm.stringtest;
import java.util.*;

public class Ladders {
    /*
    662. Word Ladder II
    Given a begin word, an end word and a dictionary, find the least number transformations from begin word to end word, and return the transformation sequences. Return empty list if there is no such transformations.
    In each transformation, you can only change one letter, and the word should still in the dictionary after each transformation.
    Assumptions
    1. All words have the same length.
    2. All words contain only lowercase alphabetic characters.
    3. There is no duplicates in the word list.
    4.The beginWord and endWord are non-empty and are not the same.
    Example: start = "git", end = "hot", dictionary = {"git","hit","hog","hot","got"}
    Output: [["git","hit","hot"], ["git","got","hot"]]
     */
    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) { // 662
        if (wordList==null || beginWord==null || endWord==null) {return null;}
        List<List<String>> result = new ArrayList<>();
        int lb=beginWord.length(),le=endWord.length(),ll=wordList.size();
        if (lb!=le) {return result;}
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        result.add(temp);
        if (beginWord.equals(endWord)) {
            return result;
        } else if (ll==0) {
            return null;
        }
        Map<String,List<List<String>>> steps = new HashMap<>();
        Queue<String> transverse = new LinkedList<>();
        steps.put(beginWord,result);
        transverse.add(beginWord);
        while (!transverse.isEmpty()) {
            String curr = transverse.poll();
            result = steps.get(curr);
            int rs = result.size();
            System.out.println(curr+" "+rs);
            for (int i=0;i<ll;i++) {
                String test = wordList.get(i);
                int difference=validTransform(curr,test);
                System.out.println(curr+" "+test+" "+difference);
                if (difference<0) {return null;}
                if (difference==0 || difference>1) {continue;}
                List<List<String>> adding = new ArrayList<>();
                int ts=-1;
                for (int j=0;j<rs;j++) {
                    List<String> temp2 = new ArrayList<>();
                    temp=result.get(j);
                    ts=temp.size();
                    for (int k=0;k<ts;k++) {
                        temp2.add(temp.get(k));
                    }
                    temp2.add(test);
                    System.out.println(i+" "+j+" "+test+" "+result.get(j)+" "+temp2.toString());
                    adding.add(temp2);
                } // end for
                ts++;
                if (steps.get(test)==null) {
                    steps.put(test,adding);
                    transverse.add(test);
                } else {
                    List<List<String>> getting = new ArrayList<>();
                    getting=steps.get(test);
                    int gs = getting.get(0).size();
                    if (ts<gs) { // should not happen
                        steps.replace(test,adding);
                    } else if (ts==gs) {
                        getting.addAll(adding);
                        steps.replace(test,getting);
                    }
                } // end if
            } // end for
        } // end while
        if (steps.get(endWord)!=null) {
            return steps.get(endWord);
        } else {
            result.clear();
            return result;
        }
    }
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) { // 662
        if (wordList==null || beginWord==null || endWord==null) {return null;}
        List<List<String>> result = new ArrayList<>();
        int lb=beginWord.length(),le=endWord.length(),ll=wordList.size();
        if (lb!=le) {return result;}
        List<String> temp = new ArrayList<String>(){{add(beginWord);}};
        result.add(temp);
        if (beginWord.equals(endWord)) {
            return result;
        } else if (ll==0) {
            return null;
        }
        Map<String,List<List<String>>> steps = new HashMap<>();
        Queue<String> trav = new ArrayDeque<>();
        steps.put(beginWord,result);
        trav.add(beginWord);
        while (!trav.isEmpty()) {
            String curr = trav.poll();
            result = steps.get(curr);
            for (String test : wordList) {
                int difference=validTransform(curr,test);
                if (difference<0) {return null;}
                if (difference==0 || difference>1) {continue;}
                List<List<String>> adding = null;
                if (steps.get(test)==null) {
                    adding=new ArrayList<>();
                    steps.put(test,adding);
                    trav.add(test);
                } else {
                    adding=steps.get(test);
                    if (adding.get(0).size()<=result.get(0).size()) {continue;}
                }
                for (List<String> temp1 : result) {
                    List<String> temp2 = new ArrayList<>();
                    temp2.addAll(temp1);
                    temp2.add(test);
                    adding.add(temp2);
                }
                if (test.equals(endWord)) {break;}
            } // end for
        } // end while
        return steps.get(endWord);
        // Write your solution here
    }
    /*
    661. Word Ladder
    Given a begin word, an end word and a dictionary, find the least number transformations from begin word to end word, and return the length of the transformation sequence. Return 0 if there is no such transformations.
    In each transformation, you can only change one letter, and the word should still in the dictionary after each transformation.
    Assumptions
    1. All words have the same length.
    2. All words contain only lowercase alphabetic characters.
    3. There is no duplicates in the word list.
    4.The beginWord and endWord are non-empty and are not the same.
    Example: start = "git", end = "hot", dictionary = {"git","hit","hog","hot"}
    Output: 3
    Explanation: git -> hit -> hot
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) { // 661
        if (wordList==null || wordList.size()==0) {return -1;}
        if (beginWord==null || endWord==null) {return -1;}
        int lb=beginWord.length(),le=endWord.length(),ll=wordList.size();
        if (lb!=le) {return -1;}
        if (beginWord.equals(endWord)) {return 1;}
        Map<String,Integer> steps = new HashMap<>();
        Queue<String> trav = new ArrayDeque<>();
        steps.put(beginWord,1);
        trav.add(beginWord);
        while (!trav.isEmpty()) {
            String curr = trav.poll();
            int scur = steps.get(curr);
            for (int i=0;i<ll;i++) {
                String test = wordList.get(i);
                if (steps.get(test)!=null) {continue;}
                int difference=validTransform(curr,test);
                if (difference==-1) {return -1;}
                if (difference==1) {
                    steps.put(test,scur+1);
                    trav.add(test);
                    if (test.equals(endWord)) {return scur+1;}
                } // end if
            } // end for
        } // end while
        return 0;
        // Write your solution here
    }
    private int validTransform(String a, String b) {
        int la=a.length(),lb=b.length();
        if (la!=lb) {return -1;}
        if (a.equals(b)) {return 0;}
        int count=0;
        for (int i=0;i<la;i++) {
            if (a.charAt(i)!=b.charAt(i)) {
                count++;
            }
        }
        return count;
    }
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) { // 661
        if (wordList==null || wordList.size()==0) {return -1;}
        if (beginWord==null || endWord==null) {return -1;}
        int lb=beginWord.length(),le=endWord.length(),ll=wordList.size();
        if (lb!=le) {return -1;}
        if (beginWord.equals(endWord)) {return 1;}
        int endIndex = wordList.indexOf(endWord);
        if (endIndex==-1) {return 0;}
        List<String> words = null;
        int beginIndex = wordList.indexOf(beginWord);
        if (beginIndex==-1) {
            words = new ArrayList<>(wordList);
            words.add(beginWord);
            beginIndex = words.size()-1;
        } else {
            words = wordList;
        }
        NeighborFinder finder = new NeighborFinder(words);
        Queue<Integer> queue = new ArrayDeque<>();
        int[] step = new int[words.size()];
        Arrays.fill(step,-1);
        queue.offer(beginIndex);
        step[beginIndex]=1;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (x == endIndex) {
                return step[x];
            }
            for (int y : finder.findNeighbors(x)) {
                if (step[y]==-1) {
                    queue.offer(y);
                    step[y]=step[x]+1;
                }
            }
        }
        return 0;
    }
    static class NeighborFinder {
        private Map<String,Integer> wordIndex = new HashMap<>();
        private List<String> words;
        public NeighborFinder(List<String> words) {
            for (int i=0;i<words.size();i++) {
                wordIndex.put(words.get(i),i);
            }
            this.words=words;
        }
        public List<Integer> findNeighbors(int i) {
            List<Integer> neighbors = new ArrayList<>(16);
            String word = words.get(i);
            StringBuilder wordModifier = new StringBuilder(word);
            for (int j=0;j<wordModifier.length();j++) {
                char orig = word.charAt(j);
                for (char c='a';c<='z';c++) {
                    if (c==orig) {continue;}
                    wordModifier.setCharAt(j,c);
                    int neighbor = wordIndex.getOrDefault(wordModifier.toString(),-1);
                    if (neighbor!=-1) {neighbors.add(neighbor);}
                }
                wordModifier.setCharAt(j,orig);
            }
            return neighbors;
        }
    }
    public static void main(String[] args) {
        Ladders solution = new Ladders();
        String[] input = new String[] {"hit","hot","dot","dog","lot","log","cog"};
        System.out.println(solution.findLadders1("hit","cog",Arrays.asList(input)));
    }
}
