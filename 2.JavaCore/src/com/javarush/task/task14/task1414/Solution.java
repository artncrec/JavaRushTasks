package com.javarush.task.task14.task1414;

/* 
MovieFactory
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        String key;
        while (true) {
            key = reader.readLine();
            if (key.equals("cartoon"))
                list.add(key);
            else if (key.equals("thriller"))
                list.add(key);
            else if (key.equals("soapOpera"))
                list.add(key);
            else break;
        }
        Movie movie = null;
        for (String s : list) {
            movie = MovieFactory.getMovie(s);
            System.out.println(movie.getClass().getSimpleName());
        }
    }

    static class MovieFactory {

        static Movie getMovie(String key) {
            Movie movie = null;
            //создание объекта SoapOpera (мыльная опера) для ключа "soapOpera"
            if ("soapOpera".equals(key)) {
                movie = new SoapOpera();
            }
            else if ("cartoon".equals(key)) {
                movie = new Cartoon();
            }
            else if ("thriller".equals(key)) {
                movie = new Thriller();
            }
            return movie;
        }
    }

    static abstract class Movie {
    }

    static class SoapOpera extends Movie {
    }

    static class Cartoon extends Movie {
    }

    static class Thriller extends Movie {
    }
}
