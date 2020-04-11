package com.example.kotlinstudy.view

import android.app.Dialog
import android.content.Context
import com.example.kotlinstudy.R

/**
 *Created by TYY on 2020/4/10
 *Explain:
 */
object LoadingDialog {
    private var dialog: Dialog? = null
    fun show(context: Context) {
        cancle()
        dialog = Dialog(context)
        // let 返回执行语句的最后一句
        dialog?.let {
            it.setContentView(R.layout.dialog_loading)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            it.show()
        }
    }

    fun cancle() {
        dialog?.cancel()
    }
}