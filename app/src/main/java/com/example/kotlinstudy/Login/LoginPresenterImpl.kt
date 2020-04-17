package com.example.kotlinstudy.Login

import android.content.Context
import android.view.View
import com.example.kotlinstudy.bean.LoginBean

class LoginPresenterImpl(private var loginView: ILoginContract.ILoginView?) :
    ILoginContract.ILoginPresenter,
    ILoginContract.ILoginPresenter.ILoginResponsListener,
    ILoginContract.ILoginPresenter.IRegisterResponsListener {
    override fun onLoginSuccess(data: LoginBean?) {
        loginView?.loginSucces(data)
    }

    override fun onLoginFailed(errorMsg: String) {
        loginView?.loginFailure(errorMsg)
    }

    override fun onRegisterSuccess(data: LoginBean?) {
        loginView?.loginSucces(data)
    }

    override fun onRegisterFailed(errorMsg: String) {
        loginView?.loginFailure(errorMsg)
    }


    var loginModule: ILoginContract.ILoginModule = LoginModuleImpl()


    override fun onLogin(context: Context, name: String, password: String) {
        loginModule.onLogin(context, name, password, this)
    }

    override fun onRegister(context: Context, name: String, password: String, repassword: String) {
        loginModule.onRegister(context, name, password, repassword, this)
    }

    override fun attachView() {
    }

    override fun dettachView() {
        loginView = null
        //tyy 取消访问请求
        loginModule.onCancleRequest()
    }
}