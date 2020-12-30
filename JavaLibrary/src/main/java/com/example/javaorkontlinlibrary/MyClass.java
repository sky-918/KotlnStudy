package com.example.javaorkontlinlibrary;

import java.util.Date;
import java.util.TimeZone;

public class MyClass {
    public static void main(String[] args) {
//    String tim= String.valueOf(TimeZone.getTimeZone("UTC"));
//        System.out.println(tim);
       int a=2;
       double b=1.0;
       char c='1';
       String aa="2";
       String bb=new String("2");
       if (a==c){
           System.out.println("两者相等");
       }else{
           System.out.println("a!=b");
       }
       Object aaa=new Object();

       if (aa.equals(bb)){
           System.out.println("aabb两者相等");
       }else{
           System.out.println("aa!=bb");
       }

    }
}
