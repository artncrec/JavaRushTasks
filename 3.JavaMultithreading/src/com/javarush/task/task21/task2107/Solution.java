package com.javarush.task.task21.task2107;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable{

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);
            System.out.println(solution.equals(clone));
            System.out.println(solution.users);
            System.out.println(clone.users);
            System.out.println(solution.users.equals(clone.users));
            clone.users.put("A", new User(11, "a"));
            System.out.println(solution.equals(clone));
            System.out.println(solution.users.equals(clone.users));

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Solution solution = new Solution();
        for (Map.Entry<String, User> entry : this.users.entrySet())
        {
            String s = entry.getKey();
            User u = entry.getValue().clone(); //new User(entry.getValue().age, entry.getValue().name);
            solution.users.put(s, u);
        }
        return solution;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (Map.Entry<String, User> entry : this.users.entrySet()) {
            String s = entry.getKey();
            result = result + (s != null ? s.hashCode() : 0) + entry.getValue().age +
                    (entry.getValue().name != null ? entry.getValue().name.hashCode() : 0);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;
        if (getClass() != o.getClass()) return false;

        Solution sol = (Solution) o;
        Iterator<Map.Entry<String, User>> iterator = users.entrySet().iterator();
        Iterator<Map.Entry<String, User>> iteratorO = sol.users.entrySet().iterator();
        while (true) {
            if (iterator.hasNext())
                if (iteratorO.hasNext()) {
                    Map.Entry<String, User> entry = iterator.next();
                    Map.Entry<String, User> entryO = iteratorO.next();
                    if (entry.getKey() == null ? entryO.getKey() != null : !entry.getKey().equals(entryO.getKey()))
                        return false;
                    if (entry.getValue().age!=entryO.getValue().age) return false;
                    if (entry.getValue().name == null ? entryO.getValue().name != null :
                            !entry.getValue().name.equals(entryO.getValue().name)) return false;
                } else return false;
            else if (iteratorO.hasNext()) return false;
                 else break;
        }
        return true;
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable{
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        protected User clone() throws CloneNotSupportedException {
            return new User(this.age, this.name);
        }

        @Override
        public int hashCode() {
            return 31 * age + (name != null ? name.hashCode() : 0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            if (getClass() != o.getClass()) return false;

            User user = (User) o;
            if (age != user.age) return false;
            if (name == null ? user.name != null : !name.equals(user.name)) return false;
            return true;
        }
    }
}
