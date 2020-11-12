package com.example.kotlinstudy

/**
 *
 * 可以随便运行kotlin语言的一个文件
 */
fun main(args: Array<String>) {
    print("dasdasdasd\n")
    // list add和set的区别
    val list = mutableListOf<String>()
    list.add("1")
    list.set(0, "2323")
    list.add(1, "2323")
    list.add(2, "2323")
    println("list.size=${list.size}")
}