package com.javarush.task.task23.task2309;

import com.javarush.task.task23.task2309.vo.*;

import java.util.List;

/* 
Анонимность иногда так приятна!
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.getUsers());
        print(solution.getLocations());
    }

    public static void print(List list) {
        String format = "Id=%d, name='%s', description=%s";
        for (Object obj : list) {
            NamedItem item = (NamedItem) obj;
            System.out.println(String.format(format, item.getId(), item.getName(), item.getDescription()));
        }
    }

    public List<User> getUsers() {
        AbstractDbSelectExecutor<User> AbstractDbSelectExecutor = new AbstractDbSelectExecutor<User>() {
            public String getQuery() {
                return "SELECT * FROM USER";
            }
        };
        return AbstractDbSelectExecutor.execute();
    }

    public List<Location> getLocations(){
        AbstractDbSelectExecutor<Location> AbstractDbSelectExecutor = new AbstractDbSelectExecutor<Location>() {
            public String getQuery() {
                return "SELECT * FROM LOCATION";
            }
        };
        return AbstractDbSelectExecutor.execute();
    }

    public List<Server> getServers(){
        AbstractDbSelectExecutor<Server> AbstractDbSelectExecutor = new AbstractDbSelectExecutor<Server>() {
            public String getQuery() {
                return "SELECT * FROM SERVER";
            }
        };
        return AbstractDbSelectExecutor.execute();
    }

    public List<Subscription> getSubscriptions(){
        AbstractDbSelectExecutor<Subscription> AbstractDbSelectExecutor = new AbstractDbSelectExecutor<Subscription>() {
            public String getQuery() {
                return "SELECT * FROM SUBSCRIPTION";
            }
        };
        return AbstractDbSelectExecutor.execute();
    }

    public List<Subject> getSubjects(){
        AbstractDbSelectExecutor<Subject> AbstractDbSelectExecutor = new AbstractDbSelectExecutor<Subject>() {
            public String getQuery() {
                return "SELECT * FROM SUBJECT";
            }
        };
        return AbstractDbSelectExecutor.execute();
    }
}
