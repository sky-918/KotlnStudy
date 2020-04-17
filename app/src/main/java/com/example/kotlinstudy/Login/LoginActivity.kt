package com.example.kotlinstudy.Login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.kotlinstudy.R
import com.example.kotlinstudy.api.WanAndroidApi
import com.example.kotlinstudy.bean.LoginBean
import com.example.kotlinstudy.network.RequstClient
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : RxAppCompatActivity(), ILoginContract.ILoginView {
    lateinit var operationString: String
    val loginPreset: ILoginContract.ILoginPresenter = LoginPresenterImpl(this)
    override fun loginSucces(loginBean: LoginBean?) {
        Toast.makeText(this, "${operationString}成功", Toast.LENGTH_SHORT).show()
    }

    override fun loginFailure(errorMsg: String) {
        Log.e("loginFailure", errorMsg)
        Toast.makeText(this, "${operationString}失败   $errorMsg", Toast.LENGTH_SHORT).show()
    }

    //    RxAppCompatActivity 防止内存泄露有关
    companion object {
        val Tag = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener(onClickListener)
        btn_register.setOnClickListener(onClickListener)
        //tyy 绑定view
        loginPreset.attachView()

    }

    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.btn_login -> {
                val userName: String = et_account.text.toString()
                val password: String = et_password.text.toString()
                Log.i(Tag, "这是登录：userName=$userName     password=$password")

                operationString = "登录"

                loginPreset.onLogin(this, userName, password)
            }
            R.id.btn_register -> {
                val userName: String = et_account.text.toString()
                val password: String = et_password.text.toString()
                Log.i(Tag, "这是注册：userName=$userName     password=$password")
                operationString = "注册"
                loginPreset.onRegister(this, userName, password, password)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //tyy 解除绑定
        loginPreset.dettachView()
    }
}


