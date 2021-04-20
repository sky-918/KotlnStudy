package com.example.kotlinstudy.round

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinstudy.R

class RoundCornerActivity : AppCompatActivity() {
    companion object{
        @SuppressLint("LogNotTimber")
        fun newInstance(context:Context){
            val intent=Intent(context,RoundCornerActivity::class.java)
            context.startActivity(intent)
            Log.d( "newInstance: ","测试")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roun_corner)
    }
}