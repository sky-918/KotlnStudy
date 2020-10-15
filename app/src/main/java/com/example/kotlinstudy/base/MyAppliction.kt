package com.example.kotlinstudy.base

import android.app.Application
import com.example.kotlinstudy.network.RequstClient
import timber.log.Timber

/**
 *Created by TYY on 2020/4/10
 *Explain:
 */
class MyAppliction : Application() {
    override fun onCreate() {
        super.onCreate()
        RequstClient.instance.createRetrifitRequest(this)
        Timber.plant(Timber.DebugTree())
    }
}