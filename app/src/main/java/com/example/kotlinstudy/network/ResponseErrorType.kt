package com.example.kotlinstudy.network

import android.content.Context
import com.example.kotlinstudy.R


/**
 *Created by TYY on 2020/4/10
 *Explain:
 */
enum class ResponseErrorType(val code: Int, private val msgID: Int) {
    //小写字母切换成大写 ctrl+shift+u
    INTErNAL_SERVER_ERROR ( 500, R.string.service_error),
    BAD_GATEWAY (502, R.string.service_error),
    NOT_FOUND(404, R.string.not_found_error),
    CONNECT_TIMEOUT(408, R.string.timeout_error),
    NETWORK_NOT_CONNECT(499, R.string.network_error),
    UNKNOWN_ERROR(700, R.string.unknown_error);

    private val DEFAULT_CODE = 1
    fun getResponseErrorMsg(context: Context): ResponseError {
        return ResponseError(DEFAULT_CODE, context.getString(msgID))
    }
}