package com.example.kotlinstudy.network

import android.content.Context
import com.example.kotlinstudy.bean.ResponseWrapper
import com.example.kotlinstudy.view.LoadingDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 *Created by TYY on 2020/4/10
 *Explain:
 */
abstract class ResponsClient<T>(val context: Context) : Observer<ResponseWrapper<T>> {
    //默认显示加载框
    private var isShowLoading = true;

    abstract fun onSuccess(data: ResponseWrapper<T>)
    abstract fun onFail(statusCode: Int, responseMsg: ResponseError)
    override fun onComplete() {
        //取消加载框
        LoadingDialog.cancle()
    }

    override fun onSubscribe(d: Disposable) {
//        加载中
        if (isShowLoading)
            LoadingDialog.show(context)
    }

    override fun onNext(t: ResponseWrapper<T>) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        //取消加载框
        LoadingDialog.cancle()
        //失败处理
        if (e is HttpException) {
            val error: ResponseError = when (e.code()) {
                ResponseErrorType.INTErNAL_SERVER_ERROR.code -> ResponseErrorType.INTErNAL_SERVER_ERROR.getResponseErrorMsg(
                    context
                )
                ResponseErrorType.BAD_GATEWAY.code -> ResponseErrorType.BAD_GATEWAY.getResponseErrorMsg(
                    context
                )
                ResponseErrorType.NOT_FOUND.code -> ResponseErrorType.NOT_FOUND.getResponseErrorMsg(
                    context
                )
                else -> otherError(e)
            } as ResponseError
            onFail(e.code(), error)
            return
        }
        //没有网络返回code时
        val error: ResponseErrorType = when (e) {
            is UnknownHostException -> ResponseErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> ResponseErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> ResponseErrorType.CONNECT_TIMEOUT
            else -> ResponseErrorType.UNKNOWN_ERROR
        }
        onFail(error.code, error.getResponseErrorMsg(context))
    }

    private fun otherError(e: HttpException) {
        Gson().fromJson(e.response().errorBody()?.charStream(), ResponseError::class.java)
    }
}