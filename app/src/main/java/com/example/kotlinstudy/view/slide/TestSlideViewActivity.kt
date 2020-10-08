package com.example.kotlinstudy.view.slide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_test_slide_view.*

class TestSlideViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_slide_view)
//        slideView.setOnMoveXYListener(object : SlideLayoutView.OnMoveXYListener {
//            override fun onXYListener(x: Int, y: Int) {
//                Log.d("TestSlideViewActivity", "x=$x  y=$y")
//            }
//        })
        slideLine.setOnMoveXYListener(object : SlideLineView.OnMoveXYListener {
            override fun onXYListener(x: Float, y: Int) {
                Log.d("TestSlideViewActivity", "x=$x  y=$y")
            }
        })
    }
}