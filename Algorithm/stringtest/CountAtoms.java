package com.laioffer.Algorithm.stringtest;
import java.util.*;

public class CountAtoms {
    /*
    602. Count atoms
    Given a chemical formula (given as a string), return the count of each atom.
    An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
    1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
    Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.
    A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.
    Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
    Example 1:
    Input:
    formula = "H2O"
    Output: "H2O"
    Explanation:
    The count of elements are {'H': 2, 'O': 1}.
    Example 2:
    Input:
    formula = "Mg(OH)2"
    Output: "H2MgO2"
    Explanation:
    The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
    Example 3:
    Input:
    formula = "K4(ON(SO3)2)2"
    Output: "K4N2O14S4"
    Explanation:
    The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
    Note:
    All atom names consist of lowercase letters, except for the first character which is uppercase.
    The length of formula will be in the range [1, 1000].
    formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.
     */
    public String countOfAtoms(String formula) { // 602
        if (formula == null) {return null;}
        int n = formula.length();
        Deque<Map<String,Integer>> stack = new ArrayDeque<>();
        stack.push(new TreeMap());

        for (int i=0;i<n;) {
            char ch = formula.charAt(i);
            if (ch=='(') {
                stack.push(new TreeMap());
                i++;
            } else if (ch==')') {
                Map<String,Integer> top = stack.pop();
                int j=++i,num=0;
                while (i<n && Character.isDigit(formula.charAt(i))) {num=num*10+formula.charAt(i)-'0';i++;}
                if (i==j) {num=1;}
                for (String c : top.keySet()) {
                    int v = top.get(c);
                    stack.peek().put(c,stack.peek().getOrDefault(c,0)+v*num);
                }
            } else {
                int j=i++,num=0;
                while (i<n && Character.isLowerCase(formula.charAt(i))) {i++;}
                String name = formula.substring(j,i);
                j=i;
                while (i<n && Character.isDigit(formula.charAt(i))) {num=num*10+formula.charAt(i)-'0';i++;}
                if (i==j) {num=1;}
                stack.peek().put(name,stack.peek().getOrDefault(name,0)+num);
            }
        }
        StringBuilder result = new StringBuilder();
        // for (String name : stack.peek().keySet()) {
        //     result.append(name);
        //     int num=stack.peek().get(name);
        //     if (num>1) {result.append(num);}
        // }
        for (Map.Entry<String,Integer> e : stack.peek().entrySet()) {
            result.append(e.getKey());
            int num=e.getValue();
            if (num>1) {result.append(num);}
        }
        return result.toString();
        // Write your solution here
    }
    public static void main(String[] args) {
        CountAtoms solution = new CountAtoms();
        //System.out.println(solution.countOfAtoms("K4(ON(SO3)2)2"));
        System.out.println(solution.countOfAtoms("((Os21)2(No13Cl31)36(Pb7Co40)42)28(Rb30Te38Co22)19(Sb18Pr5He21Rn41)28(Lu29Zr29Pr23)22(Re2No25Tm17)41(Ba50Mn20)32(Db42Sg26Al49Lu)32(Cf23Tl37Ho13)45(B43)9(Cd12)18((Lr11)36(Co19Y21U36)18)16((Os37Se19)7(Nb33Ne19Os41)33(Sm9Md32Hs40Tm8)13(Sb6)3Fr16Bh)8"));
    }
}
