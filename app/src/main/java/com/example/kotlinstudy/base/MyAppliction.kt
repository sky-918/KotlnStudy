package com.example.kotlinstudy.base

import android.app.Application
import com.example.kotlinstudy.network.RequstClient

/**
 *Created by TYY on 2020/4/10
 *Explain:
 */
class MyAppliction : Application() {
    override fun onCreate() {
        super.onCreate()
        RequstClient.instance.createRetrifitRequest()
    }
}