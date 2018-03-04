package com.javarush.task.task19.task1903;

/* 
Адаптация нескольких интерфейсов
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();
    static
    {
        countries.put("UA","Ukraine");
        countries.put("RU","Russia");
        countries.put("CA","Canada");
    }

    public static void main(String[] args) {

    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData incomeData;

        public IncomeDataAdapter(IncomeData incomeData) {
            this.incomeData = incomeData;
        }

        @Override
        public String getCompanyName() {
            return incomeData.getCompany();
        }

        @Override
        public String getCountryName() {
            for (Map.Entry entry : countries.entrySet())
                if (entry.getKey().equals(incomeData.getCountryCode()))
                    return (String) entry.getValue();
            return null;
        }

        @Override
        public String getName() {
            return incomeData.getContactLastName() + ", " + incomeData.getContactFirstName();
        }

        @Override
        public String getPhoneNumber() {
            char[] number = (incomeData.getPhoneNumber() + "").toCharArray();
            StringBuilder sb = new StringBuilder();
            int k = 0;
            for (int j = 0; j < number.length; j++) {
                if (sb.length() == 0)
                    sb.append('(');
                if (number.length + k < 10)
                    for (int i = 0; i < 10 - number.length; i++) {
                        if (sb.length() == 4)
                            sb.append(')');
                        sb.append('0');
                        k++;
                    }
                if (sb.length() == 4)
                    sb.append(')');
                if (sb.length() == 8)
                    sb.append('-');
                if (sb.length() == 11)
                    sb.append('-');
                sb.append(number[j]);
            }
            return "+" + incomeData.getCountryPhoneCode() + sb.toString();
        }
    }


    public static interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67
    }
}