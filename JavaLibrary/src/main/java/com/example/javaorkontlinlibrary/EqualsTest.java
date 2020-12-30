package com.example.javaorkontlinlibrary;

import java.util.Objects;

/**
 * 比较equals和==区别
 */
 class Value
{
    int i;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value = (Value) o;
        return i == value.i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i);
    }
}
public class EqualsTest {

    public static void main(String[] args) {
        /*
         * ==比较的对象在内存中的首地址是否相同
         * equals对于字符串和基本数据类型的包装类进行比较是比较的内容，对非字符串比较时和==相同
         * 基本数据类型，type，short，int，long，char，float，double，boolean只能使用==，不能使用equals
         *
         */
        //基本数据类型比较,使用==，无法使用equals
        int a_int = 1;
        double b_double = 1.0;
        if (a_int == b_double) {
            System.out.println("a_int==b_double:");
        } else {
            System.out.println("a_int!=b_double:");

        }
        //字符串等基本数据类型包装类比较,不同等类型不能使用==，只能使用equals
        Integer c_Integer = 1;
        Double d_Double = 1.0;
        Double e_Double = 1.0;
        double f_Double = 1.0;
        if (c_Integer.equals(d_Double)) {
            System.out.println("c_Integer==d_Double:");
        } else {
            System.out.println("c_Integer!=d_Double:");

        }
        System.out.println("d_Double==e_Double:" + (d_Double == e_Double));
        System.out.println("d_Double .equals(e_Double):" + (d_Double.equals(e_Double)));
        //f_Double和d,e的比较是由于他们都是存在栈中
        System.out.println("d_Double==f_Double:" + (d_Double == f_Double));
        System.out.println("e_Double==f_Double:" + (f_Double == e_Double));
        System.out.println("d_Double .equals(f_Double):" + (d_Double.equals(f_Double)));

        //这是由于Value类中没有重写equals方法，此时是调用的object里的equals方法，比较的是地址
        Value v1 = new Value();
        Value v2 = new Value();
        v1.i = 100;
        v2.i = 100;
        //重写equals方法之后会正常
        System.out.println(v1.equals(v2));//（1）flase
        System.out.println(v1 == v2);//（2）false


    }
}
