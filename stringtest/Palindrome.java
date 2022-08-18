package com.laioffer.stringtest;
import java.util.*;

public class Palindrome {
    /*
    446. Valid palindrome
    Given a string, determine if it is a palindrome, considering only alphanumeric characters('0'-'9','a'-'z','A'-'Z') and ignoring cases.
    For example,
    "an apple, :) elp pana#" is a palindrome.
    "dia monds dn dia" is not a palindrome.
     */
    public boolean valid(String input) { // 446
        if (input==null) {return false;}
        int len=input.length();
        if (len==1) {return true;}
        int left=0,right=len-1;
        while (left<right) {
            char cl = input.charAt(left);
            char cr = input.charAt(right);
            if (cl<48 || cl>57 && cl<65 || cl>90 && cl<97 || cl>122) {
                left++;
            } else if (cr<48 || cr>57 && cr<65 || cr>90 && cr<97 || cr>122) {
                right--;
            } else {
                if (cl!=cr && cl!=cr+32 && cl!=cr-32) {return false;}
                left++;
                right--;
            }
        }
        return true;
        // Write your solution here
    }
    /*
    521. Valid palindrome II
    Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
    Example 1:
    Input: "aba"
    Output: True
    Example 2:
    Input: "abca"
    Output: True
    Explanation: You could delete the character 'c'.
    Note:
    The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
     */
    public boolean validPalindrome(String input) { // 521
        if (input==null) {return false;}
        char[] array = input.toCharArray();
        int len=array.length;
        if (len<=2) {return true;}
        return isPalindrome(array,0,len-1,false);
        // Write your solution here
    }
    private boolean isPalindrome(char[] array, int left, int right, boolean changed) {
        if (left>=right) {return true;}
        if (array[left]==array[right]) {
            return isPalindrome(array,left+1,right-1,changed);
        } else if (changed) {
            return false;
        } else {
            return isPalindrome(array,left,right-1,true) || isPalindrome(array,left+1,right,true);
        }
    }
    /*
    500. Palindrome Permutation II
    Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
    For example:
    Given s = "aabb", return ["abba", "baab"].
    Given s = "abc", return [].
     */
    public List<String> generatePalindromes(String input) {
        List<String> result = new ArrayList<>();
        if (input==null) {return result;}
        int len=input.length();
        int[] count = new int[128];
        int odd=convert(input,count);
        if (odd>1) {return result;}
        char[] cur = new char[len];
        if (odd==1) {
            cur[len/2]=findOdd(count);
        }
        dfs(cur,count,0,result);
        return result;
        // Write your solution here
    }
    private void dfs (char[] cur, int[] count, int index, List<String> result) {
        if (index==cur.length/2) {
            result.add(new String(cur));
            return;
        }
        for (int i=0;i<128;i++) {
            if (count[i]>1) {
                count[i]-=2;
                cur[index]=cur[cur.length-1-index]=(char)i;
                dfs(cur,count,index+1,result);
                count[i]+=2;
            }
        }
    }
    private char findOdd(int[] count) {
        for (int i=0;i<128;i++) {
            if (count[i]%2==1) {return (char)i;}
        }
        return ' ';
    }
    private int convert(String input, int[] count) {
        int odd=0;
        for (char c : input.toCharArray()) {
            count[c]++;
            odd+=(count[c]%2==1?1:-1);
        }
        return odd;
    }
    /*
    435. Shortest Palindrome
    Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
    For example:
    Given "aacecaaa", return "aaacecaaa".
    Given "abcd", return "dcbabcd".
     */
    public String shortestPalindrome(String input) { // 435
        if (input==null) {return input;}
        int len=input.length();
        int n_new=len*2+1;
        char[] array = new char[n_new];
        for (int i=0;i<len;i++) {
            array[i]=array[n_new-1-i]=input.charAt(i);
        }
        array[len]='#';
        int[] f = new int[n_new];
        for (int i=1;i<n_new;i++) {
            int t=f[i-1];
            while (t>0 && array[i]!=array[t]) {
                t=f[t-1];
            }
            f[i]=(array[i]==array[t]?t+1:0);
        }
        return new String(array,len+1,len-f[n_new-1]) + input;
        // Write your solution here
    }
    /*
    470. Palindrome Pairs
    Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
    Example 1:
    Given words = ["bat", "tab", "cat"]
    Return [[0, 1], [1, 0]]
    The palindromes are ["battab", "tabbat"]
    Example 2:
    Given words = ["abcd", "dcba", "lls", "s", "sssll"]
    Return [[0, 1], [1, 0], [3, 2], [2, 4]]
    The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
     */
    public List<List<Integer>> palindromePairs470(String[] words) { // 470
        List<List<Integer>> result = new ArrayList<>();
        if (words==null || words.length==0) {return result;}
        int len=words.length;
        for (int i=0;i<len;i++) {
            for (int j=0;j<len;j++) {
                if (j==i) {continue;}
                if (isPalindrome(words[i],words[j])) {
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;
        // Write your solution here
    }
    private boolean isPalindrome (String input1, String input2) {
        if (input1==null || input2==null || input1.length()==0 || input2.length()==0) {return false;}
        int l1=input1.length(),l2=input2.length();
        int left=0,right=l1+l2-1;
        while (left<right) {
            char c1=left<l1?input1.charAt(left):input2.charAt(left-l1);
            char c2=right>=l1?input2.charAt(right-l1):input1.charAt(right);
            if (c1!=c2) {return false;}
            left++;right--;
        }
        return true;
    }
    public List<List<Integer>> palindromePairs(String[] words) { // 470
        TrieNode trie = new TrieNode();

        // Build the Trie
        for (int wordId = 0; wordId < words.length; wordId++) {
            String word = words[wordId];
            String reversedWord = new StringBuilder(word).reverse().toString();
            TrieNode currentTrieLevel = trie;
            for (int j = 0; j < word.length(); j++) {
                if (hasPalindromeRemaining(reversedWord, j)) {
                    currentTrieLevel.palindromePrefixRemaining.add(wordId);
                }
                Character c = reversedWord.charAt(j);
                if (!currentTrieLevel.next.containsKey(c)) {
                    currentTrieLevel.next.put(c, new TrieNode());
                }
                currentTrieLevel = currentTrieLevel.next.get(c);
            }
            currentTrieLevel.wordEnding = wordId;
        }

        // Find pairs
        List<List<Integer>> pairs = new ArrayList<>();
        for (int wordId = 0; wordId < words.length; wordId++) {
            String word = words[wordId];
            TrieNode currentTrieLevel = trie;
            for (int j = 0; j < word.length(); j++) {
                // Check for pairs of case 3.
                if (currentTrieLevel.wordEnding != -1
                        && hasPalindromeRemaining(word, j)) {
                    pairs.add(Arrays.asList(wordId, currentTrieLevel.wordEnding));
                }
                // Move down to the next trie level.
                Character c = word.charAt(j);
                currentTrieLevel = currentTrieLevel.next.get(c);
                if (currentTrieLevel == null) break;
            }
            if (currentTrieLevel == null) continue;
            // Check for pairs of case 1. Note the check to prevent non distinct pairs.
            if (currentTrieLevel.wordEnding != -1 && currentTrieLevel.wordEnding != wordId) {
                pairs.add(Arrays.asList(wordId, currentTrieLevel.wordEnding));
            }
            // Check for pairs of case 2.
            for (int other : currentTrieLevel.palindromePrefixRemaining) {
                pairs.add(Arrays.asList(wordId, other));
            }
        }

        return pairs;
        // Write your solution here
    }
    class TrieNode {
        public int wordEnding = -1; // We'll use -1 to mean there's no word ending here.
        public Map<Character, TrieNode> next = new HashMap<>();
        public List<Integer> palindromePrefixRemaining = new ArrayList<>();
    }
    private boolean hasPalindromeRemaining(String s, int left) {
        int right=s.length()-1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {return false;}
            left++; right--;
        }
        return true;
    }
    /*
    499. Palindrome Permutation
    Given a string, determine if a permutation of the string could form a palindrome.
    For example,
    "code" -> False, "aab" -> True, "carerac" -> True.
     */
    public boolean canPermutePalindrome(String input) { // 499
        if (input==null) {return false;}
        Map<Character, Integer> ht = new HashMap<>();
        int len=input.length();
        for (int i=0;i<len;i++) {
            char cur = input.charAt(i);
            ht.put(cur,ht.getOrDefault(cur,0)+1);
        }
        ArrayList<Integer> count = new ArrayList<>(ht.values());
        int odd=0;
        for (int i=0;i<count.size();i++) {
            if (count.get(i)%2==1) {odd++;}
        }
        return odd<=1;
        // Write your solution here
    }
    /*
    609. Count Palindrome Substrings
    Given a string, count how many substrings are palindrome. Two substrings are considered different if they are of difference indexes, even though they consist same content.
    Example 1:
    Input = "abc"
    Output = 3
    Explanation: "a", "b", "c
    Example 2:
    Input = "aaa"
    Output = 6
    Explanation: "a","a","a","aa","aa","aaa"
    Assumptions:
    The input string is not null.
     */
    public int countPalindromeSubs(String input) { // 609
        if (input==null || input.length()==0) {return 0;}
        int len=input.length();
        boolean[][] dp = new boolean[len][len];
        int count=0;
        for (int i=1;i<=len;i++) { // length
            for (int left=0;left<=len-i;left++) { // left
                int right=left+i-1;
                if (i==1) {
                    dp[left][right]=true;
                } else if (i==2) {
                    dp[left][right]=input.charAt(left)==input.charAt(right);
                } else {
                    dp[left][right]=input.charAt(left)==input.charAt(right) && dp[left+1][right-1];
                }
                count+=dp[left][right]?1:0;
            }
        }
        return count;
        // Write your solution here
    }
    public int longestPalindrome(String input) { // 252
        if (input==null || input.length()==0) {return 0;}
        int len = input.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) { // length of substring
            for (int k = i; k < len; k++) { // end index of substring
                int j= k-i; // begin index of substring
                if (i == 0) {
                    dp[j][k] = 1;
                } else {
                    if (input.charAt(j) == input.charAt(k)) {
                        dp[j][k] = dp[j+1][k-1] + 2;
                    } else {
                        dp[j][k] = Math.max(dp[j+1][k],dp[j][k-1]);
                    }
                }
            } // end for k
        } // end for i
        return dp[0][len-1];
    }
    public String longestPalindrome2(String input) { // 252
        if (input==null || input.length()==0) {return "";}
        char[] array = input.toCharArray();
        int len=array.length;
        if (len==1) {return input;}
        boolean[][] dp = new boolean[len][len];
        int left=-1,right=-1,max=-1;
        for (int j=0;j<len;j++) {
            for (int i=j;i>=0;i--) {
                if (i==j) {
                    dp[i][j]=true;
                } else {
                    dp[i][j]=array[i]==array[j] && (i+1==j || dp[i+1][j-1]);
                }
                if (dp[i][j]) {
                    int dist=j-i+1;
                    if (dist>max) {
                        max=dist;
                        left=i;
                        right=j;
                    }
                }
            }
        }
        return new String(array,left,max);
        // Write your solution here
    }
    public boolean isPalindrome(int x) { // 250
        if (x<0) {return false;}
        int y=x,c=0,m=1;
        while (y>=1) {
            c++;
            y/=10;
            if (c%2==0) {m*=10;}
        }
        int p1=c%2==0?x/m:x/m/10; // first part
        int p2=x%m; // second part
        for (int i=c/2;i>0;i--) {
            m/=10;
            if (p1/m!=p2%10) {return false;}
            p1%=m;
            p2/=10;
        }
        return true;
        // Write your solution here
    }
    public static void main(String[] args) {
        Palindrome solution = new Palindrome();
//        String input = "11100";
//        System.out.println(solution.generatePalindromes(input));
//        String input = "oklvojceguiuooqfsvlappalvsfqoouiuigecjovlko";
//        System.out.println(solution.validPalindrome(input));
//        String input = "aa";
//        System.out.println(solution.shortestPalindrome(input));
        String input = "abca";
        System.out.println(solution.longestPalindrome(input));
    }
}
