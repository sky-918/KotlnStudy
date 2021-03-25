package com.example.kotlinstudy.round

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinstudy.R

class RoundCornerActivity : AppCompatActivity() {
    companion object{
        fun newInstance(context:Context){
            val intent=Intent(context,RoundCornerActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roun_corner)
    }
}