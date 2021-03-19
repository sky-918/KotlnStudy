package com.example.javaorkontlinlibrary;

public class BaseDataTypeTest {
    public static void main(String[] args) {
        char c = 'c';
        char b = 'a';
        System.out.println(c +b);


        Integer a_Integer=8;

        System.out.println(a_Integer.compareTo(9));

        String c_String="111";
        String d_String="111";
        System.out.println(d_String==c_String);
        String a_String = new String("111");
        String b_String = new String("111");
        System.out.println(c_String==a_String);
    }
}
