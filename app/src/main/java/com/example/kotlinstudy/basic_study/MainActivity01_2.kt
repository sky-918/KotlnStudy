package com.example.kotlinstudy.basic_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_main_activity01_2.*

class MainActivity01_2 : AppCompatActivity() {
    /**
     * continually calling findViewById() could cause your app to lag.
     * Instead it is a best practice to just call findViewById() once
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity01_2)
        val randomInt = (1..6).random()
        textView2.text = randomInt.toString()
        button.setOnClickListener { rollDice1() }
    }

    private fun rollDice() {
        val randomInt = (1..6).random()
        textView2.text = randomInt.toString()
        Log.d("cehsi ", "$randomInt")
        when (randomInt) {
            1 -> imageView.setImageResource(R.drawable.dice_1)
            2 -> imageView.setImageResource(R.drawable.dice_2)
            3 -> imageView.setImageResource(R.drawable.dice_3)
            4 -> imageView.setImageResource(R.drawable.dice_4)
            5 -> imageView.setImageResource(R.drawable.dice_5)
            else -> imageView.setImageResource(R.drawable.dice_6)
        }
    }

    private fun rollDice1() {
        imageView.setImageResource(getImageID())
    }

    fun getImageID(): Int {
        val randomInt = (1..6).random()

        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        return drawableResource
    }
}
