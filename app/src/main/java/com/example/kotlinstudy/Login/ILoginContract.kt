package com.example.kotlinstudy.Login

import com.example.kotlinstudy.base.IBaseModule
import com.example.kotlinstudy.base.IBasePresenter
import com.example.kotlinstudy.bean.LoginBean

interface ILoginContract {


    interface ILoginPresenter : IBasePresenter {
        fun onLogin(name: String, password: String)
        fun onRegister(name: String, password: String, repassword: String)

    }

    interface ILoginModule:IBaseModule {
        fun onLogin(name: String, password: String)
        fun onRegister(name: String, password: String, repassword: String)

    }


    interface ILoginView {
        fun loginSucces(loginBean: LoginBean)
        fun loginFailure(errorMsg: String)
    }


}