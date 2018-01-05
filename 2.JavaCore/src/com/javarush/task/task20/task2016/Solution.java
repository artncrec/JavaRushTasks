package com.javarush.task.task20.task2016;

import java.io.Serializable;

/*
Минимум изменений
*/
public class Solution {
    public class A implements Serializable{
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public class B extends A {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }
    }

    public class C extends B {
        String name = "C";

        public C(String name) {
            super(name);
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        A a = sol.new A(" first");
        B b = sol.new B(" second");
        C c = sol.new C(" third");
        System.out.println(a);
        System.out.println(a.name);
        System.out.println(b);
        System.out.println(b.name);
        System.out.println(c);
        System.out.println(c.name);
    }
}
