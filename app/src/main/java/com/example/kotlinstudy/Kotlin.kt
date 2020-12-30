package com.example.kotlinstudy

import kotlin.math.ceil
import kotlin.math.floor

/**
 *
 * 可以随便运行kotlin语言的一个文件
 */
fun main(args: Array<String>) {
       val interestingThins= listOf("kotlin","Hello")
    interestingThins[0]
    sayHello("hi","Kotlin","tyy")
    greetPerson("hi","java")
}
fun  sayHello(greeting:String,vararg item:String){
    item.forEach { item->
        println("$greeting $item")
    }


}
fun greetPerson(greeting: String,name:String)= println("$greeting $name")
