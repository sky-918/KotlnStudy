package com.example.kotlinstudy.api

import com.example.kotlinstudy.bean.ResponseWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*
import com.example.kotlinstudy.bean.LoginBean as LoginBean

/**
 *Created by TYY on 2020/4/9
 *Explain:
 */
interface WanAndroidApi {
    @POST("/user/login")
    @FormUrlEncoded
    fun LoginApi(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<ResponseWrapper<LoginBean>>

    @POST("/user/register")
    @FormUrlEncoded
    fun RegisterApi(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<ResponseWrapper<LoginBean>>
}