package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name","Javarush");
        map.put("country","Russia");
        map.put("city","Moscow");
        map.put("age",null);
        System.out.println(getQuery(map));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String,String> entry: params.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) ;
            else {
                if (builder.length()>1)
                    builder.append(" and ");
                builder.append(entry.getKey());
                builder.append(" = \'");
                builder.append(entry.getValue());
                builder.append("\'");
            }
        }
        return builder.toString();
    }
}
