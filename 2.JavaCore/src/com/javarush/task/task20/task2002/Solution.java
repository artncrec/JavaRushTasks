package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            //File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream("D:\\0.txt");
            InputStream inputStream = new FileInputStream("D:\\0.txt");

            JavaRush javaRush = new JavaRush();
            User q = new User();
            q.setFirstName("one");
            q.setLastName("two");
            q.setBirthDate(new Date());
            q.setMale(true);
            q.setCountry(User.Country.RUSSIA);
            javaRush.users.add(q);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            for (int i = 0; i < users.size(); i++)
            {
                writer.println(users.get(i).getFirstName());
                writer.println(users.get(i).getLastName());
                writer.println(users.get(i).getBirthDate());
                writer.println(users.get(i).isMale());
                writer.println(users.get(i).getCountry());
            }
            writer.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s; User r;
            while ((s=reader.readLine()) != null)
            {
                r = new User();
                r.setFirstName(s);
                r.setLastName(reader.readLine());
                r.setBirthDate(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(reader.readLine()));
                r.setMale(reader.readLine().equals("true"));
                r.setCountry(User.Country.valueOf(reader.readLine()));
                this.users.add(r);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
