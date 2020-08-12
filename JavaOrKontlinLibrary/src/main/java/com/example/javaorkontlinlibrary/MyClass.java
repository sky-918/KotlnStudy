package com.example.javaorkontlinlibrary;

import java.util.Date;
import java.util.TimeZone;

public class MyClass {
    public static void main(String[] args) {
    String tim= String.valueOf(TimeZone.getTimeZone("UTC"));
        System.out.println(tim);
    }
}
