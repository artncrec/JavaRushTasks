package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/

public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        BufferedReader reader0 = new BufferedReader(new InputStreamReader(System.in));
        String file = reader0.readLine();
        reader0.close();
        InputStream inputStream = new FileInputStream(file);
        load(inputStream);
        inputStream.close();
        OutputStream stream = new FileOutputStream(file);
        save(stream);
        stream.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        Properties property = new Properties();
        for (Map.Entry<String, String> entry : properties.entrySet())
        {
            property.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
        property.store(outputStream,"");
    }

    public void load(InputStream inputStream) throws Exception {
        Properties property = new Properties();
        property.load(inputStream);
        for (Map.Entry<Object, Object> entry : property.entrySet())
        {
            properties.put(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    public static void main(String[] args) throws Exception {
        new Solution().fillInPropertiesMap();
    }
}
