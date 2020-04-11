package com.example.kotlinstudy.bean

/**
 *Created by TYY on 2020/4/9
 *Explain:
 */

//    "errorCode": 0,
//    "errorMsg": ""
data class ResponseWrapper<out T>(val data: T, val errorCode: Int, val errorMsg: String)