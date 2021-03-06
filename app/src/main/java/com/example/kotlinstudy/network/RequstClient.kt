package com.example.kotlinstudy.network

import android.content.Context
import com.example.kotlinstudy.BuildConfig
import com.example.kotlinstudy.api.WanAndroidApi
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Created by TYY on 2020/4/9
 *Explain:
 */
class RequstClient private constructor() {
    //private constructor 表示这个类是私有的
    lateinit var serviceApi: WanAndroidApi
    lateinit var retrofit: Retrofit

    /**
     * tyy 这是单例模式
     */
    private object Holder {
        val INSTANCE = RequstClient()
    }

    companion object {
        //lazy 高阶函数 会进行判断是否初始化，已经初始化会返回已经初始化的对象
        val instance by lazy { Holder.INSTANCE }
    }

    fun createRetrifitRequest(context: Context) {
        // apply 高阶函数 返回调用者本身
        val okHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )

            ).addInterceptor(ChuckInterceptor(context))
        }.build()
        retrofit = Retrofit.Builder().apply {
            baseUrl("https://www.wanandroid.com/")
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
        }.build()
        serviceApi = retrofit.create(WanAndroidApi::class.java)
    }

    fun <T> getService(service: Class<T>): T = retrofit.create(service)
}