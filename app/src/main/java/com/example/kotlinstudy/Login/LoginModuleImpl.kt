package com.example.kotlinstudy.Login

import android.content.Context
import android.util.Log
import com.example.kotlinstudy.api.WanAndroidApi
import com.example.kotlinstudy.bean.LoginBean
import com.example.kotlinstudy.network.NetworkScheduler
import com.example.kotlinstudy.network.RequstClient
import com.example.kotlinstudy.network.ResponsClient
import com.example.kotlinstudy.network.ResponseError

class LoginModuleImpl : ILoginContract.ILoginModule {
    override fun onCancleRequest() {
    }

    override fun onLogin(
        context: Context,
        name: String,
        password: String,
        loginPresenter: ILoginContract.ILoginPresenter.ILoginResponsListener
    ) {
        RequstClient.instance.getService(WanAndroidApi::class.java).LoginApi(name, password)
            .compose(NetworkScheduler.compose())
            .subscribe(object : ResponsClient<LoginBean>(context) {
                override fun onSuccess(T: LoginBean?) {
                    Log.e("Login", "${T.toString()}")
                    loginPresenter.onLoginSuccess(T)
                }

                override fun onFail(statusCode: Int, responseMsg: ResponseError) {
                    loginPresenter.onLoginFailed(responseMsg.msg)
                }
            })


    }

    override fun onRegister(
        context: Context,
        name: String,
        password: String,
        repassword: String,
        registerPresenter: ILoginContract.ILoginPresenter.IRegisterResponsListener
    ) {
        RequstClient.instance.getService(WanAndroidApi::class.java)
            .RegisterApi(name, password, repassword).compose(NetworkScheduler.compose())
            .subscribe(object : ResponsClient<LoginBean>(context) {
                override fun onSuccess(T: LoginBean?) {
                    registerPresenter.onRegisterSuccess(T)
                }

                override fun onFail(statusCode: Int, responseMsg: ResponseError) {
                    registerPresenter.onRegisterFailed(responseMsg.msg)
                }
            })
    }
}