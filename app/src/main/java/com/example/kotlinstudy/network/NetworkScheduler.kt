package com.example.kotlinstudy.network

import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 *Created by TYY on 2020/4/10
 *Explain:
 */
object NetworkScheduler {
    fun <T> compose(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}