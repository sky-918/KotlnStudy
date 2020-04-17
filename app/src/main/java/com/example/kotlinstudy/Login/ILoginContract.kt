package com.example.kotlinstudy.Login

import android.content.Context
import com.example.kotlinstudy.base.IBaseModule
import com.example.kotlinstudy.base.IBasePresenter
import com.example.kotlinstudy.bean.LoginBean

interface ILoginContract {


     interface ILoginPresenter : IBasePresenter {
        fun onLogin( context: Context,name: String, password: String)
        fun onRegister( context: Context,name: String, password: String, repassword: String)
         interface ILoginResponsListener {
             fun onLoginSuccess(data: LoginBean?)
             fun onLoginFailed(errorMsg: String)
         }

         interface IRegisterResponsListener {
             fun onRegisterSuccess(data: LoginBean?)
             fun onRegisterFailed(errorMsg: String)
         }
    }


    interface ILoginModule : IBaseModule {
        fun onLogin(
            context: Context,
            name: String,
            password: String,
            loginPresenter: ILoginPresenter.ILoginResponsListener
        )

        fun onRegister(
            context: Context,
            name: String,
            password: String,
            repassword: String,
            registerPresenter: ILoginPresenter.IRegisterResponsListener
        )

    }


    interface ILoginView {
        fun loginSucces(loginBean: LoginBean?)
        fun loginFailure(errorMsg: String)
    }


}