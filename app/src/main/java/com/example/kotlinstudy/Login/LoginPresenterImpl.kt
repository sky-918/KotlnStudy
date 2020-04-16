package com.example.kotlinstudy.Login

import android.view.View
import com.example.kotlinstudy.bean.LoginBean

class LoginPresenterImpl(private var loginView: ILoginContract.ILoginView?) :
    ILoginContract.ILoginPresenter ,ILoginContract.ILoginView{
    override fun loginSucces(loginBean: LoginBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginFailure(errorMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var loginModule: ILoginContract.ILoginModule = LoginModuleImpl()


    override fun onLogin(name: String, password: String) {
        loginModule.onLogin(name, password)
    }

    override fun onRegister(name: String, password: String, repassword: String) {
        loginModule.onRegister(name, password, repassword)
    }

    override fun attachView() {
    }

    override fun dettachView() {
        loginView = null
        //tyy 取消访问请求
        loginModule.onCancleRequest()
    }
}