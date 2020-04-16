package com.example.kotlinstudy.Login

import android.content.Context
import com.example.kotlinstudy.api.WanAndroidApi
import com.example.kotlinstudy.bean.LoginBean
import com.example.kotlinstudy.network.NetworkScheduler
import com.example.kotlinstudy.network.RequstClient
import com.example.kotlinstudy.network.ResponsClient

class LoginModuleImpl : ILoginContract.ILoginModule {
    override fun onCancleRequest() {
    }

    override fun onLogin(
        context: Context,
        name: String,
        password: String,
        loginPresenter: ILoginContract.ILoginPresenter
    ) {
        RequstClient.instance.getService(WanAndroidApi::class.java).LoginApi(name, password)
            .compose(NetworkScheduler.compose()).subscribe(object: ResponsClient<LoginBean>(context){
                override fun success(data: LoginBean) {
                    loginPresenter.(loginBean = valide(data))
                }

                override fun failure(statusCode: Int, apiError: ApiError) {
                    loginPresenter.loginFailed(apiError.message)
                }

            })


    }

    override fun onRegister(name: String, password: String, repassword: String) {
        RequstClient.instance.serviceApi.RegisterApi(name, password, repassword)
    }
}