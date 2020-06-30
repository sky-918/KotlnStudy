package com.example.kotlinstudy.basic_study

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_color_view.*

class ColorViewActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_view)
        tv_one.setOnClickListener(this)
        tv_two.setOnClickListener(this)
        tv_three.setOnClickListener(this)
        tv_four.setOnClickListener(this)
        tv_five.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var idColor: Int? = null
        when (v?.id) {
            R.id.tv_one -> {
                idColor = R.color.colorAccent
            }
            R.id.tv_two -> {
                idColor = Color.GREEN
            }
            R.id.tv_three -> {
                idColor = Color.BLUE
            }
            R.id.tv_four -> {
                idColor = Color.MAGENTA
            }
            R.id.tv_five -> {
                idColor = Color.RED
            }
        }
        onChangedViewColor(v, idColor)
    }

    private fun onChangedViewColor(view: View?, id: Int?) {
        view?.setBackgroundColor(id!!)
    }
}
