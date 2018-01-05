package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if (this==n)
            return true;
        if (n==null || this.getClass()!=n.getClass())
            return false;
        if (!(n instanceof Solution))
            return false;
        Solution nn = (Solution) n;
        return ((first==nn.first || first!=null && first.equals(nn.first))
                && (last==nn.last || last!=null && last.equals(nn.last)));
    }

    public int hashCode() {
        return 31 * (first==null? 0 : first.hashCode()) + (last==null? 0 : last.hashCode());
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
