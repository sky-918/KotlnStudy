package com.example.kotlinstudy.bean

/**
 *Created by TYY on 2020/4/9
 *Explain:
 */
//{
//    "admin": false,
//    "chapterTops": [ ],
//    "collectIds": [ ],
//    "email": "",
//    "icon": "",
//    "id": 57438,
//    "nickname": "12ewq3",
//    "password": "",
//    "publicName": "12ewq3",
//    "token": "",
//    "type": 0,
//    "username": "12ewq3"
//}

data class LoginBean(
    val admin: Boolean,
    val chapterTops: List<Any?>,
    val collectIds: List<Any?>,
    val email: String?,
    val icon: String?,
    val id: Int,
    val nickname: String?,
    val password: String?,
    val publicName: String?,
    val token: String?,
    val type: Int,
    val username: String?
)