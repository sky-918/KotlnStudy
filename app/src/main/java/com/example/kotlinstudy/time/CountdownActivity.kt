package com.example.kotlinstudy.time

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_countdown.*
import timber.log.Timber
import java.util.*
import kotlin.text.StringBuilder

class CountdownActivity : AppCompatActivity() {
    private lateinit var m1Handler: Handler

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, CountdownActivity::class.java)
            context.startActivity(intent)
        }

    }

    init {
        @SuppressLint("HandlerLeak")
        m1Handler = object : Handler() {
            override fun handleMessage(msg: Message?) {
                when (msg!!.what) {
                    1 -> {
                        val startTime = time - Calendar.getInstance().timeInMillis
                        Timber.d(
                            "时间时间：${Calendar.getInstance().timeInMillis}   ¥${
                                formatTime(
                                    startTime, stringBuilder
                                )
                            }    ¥"
                        )
                        tv_title.text = formatTime(startTime, stringBuilder)
                        m1Handler.sendEmptyMessageDelayed(1, 10)
                    }
                    else -> throw IllegalStateException("Unexpected value: " + msg.what)
                }
            }
        }
    }

    val time = 1629719659758
    private lateinit var stringBuilder: StringBuilder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown)
        val startTime = time - Calendar.getInstance().timeInMillis
        val data = DateUtils.getDateToString(startTime)
        val data1 = DateUtils.getDateToString(time)
        val date = Date(startTime)
        stringBuilder = StringBuilder()
        Timber.d(
            "时间时间：${Calendar.getInstance().timeInMillis}   ¥${
                formatTime(
                    startTime,
                    stringBuilder
                )
            }    ¥$data1"
        )

//        m1Handler?.sendEmptyMessageDelayed(1, 10)

        cdv.setEndTimeMilliSecend(time)
        cdv.startCountDownTime()


    }

    /* * 毫秒转化 */
    fun formatTime(ms: Long, stringBuilder: StringBuilder): String? {
        val ss = 1000
        val mi = ss * 60
        val hh = mi * 60
        val dd = hh * 24
        val day = ms / dd
        val hour = (ms - day * dd) / hh
        val minute = (ms - day * dd - hour * hh) / mi
        val second = (ms - day * dd - hour * hh - minute * mi) / ss
        val milliSecond = (ms - day * dd - hour * hh - minute * mi - second * ss) / 10
        val strDay = if (day < 10) {
            stringBuilder.append(0).append(day).toString()
        } else {
            stringBuilder.append(day).toString()
        }//天
        stringBuilder.clear()
        val strHour = if (hour < 10) {
            stringBuilder.append(0).append(hour).toString()
        } else {
            stringBuilder.append(hour).toString()
        }//小时
        stringBuilder.clear()
        val strMinute = if (minute < 10) {
            stringBuilder.append(0).append(minute).toString()
        } else {
            stringBuilder.append(minute).toString()
        }//分
        stringBuilder.clear()
        val strSecond = if (second < 10) {
            stringBuilder.append(0).append(second).toString()
        } else {
            stringBuilder.append(second).toString()
        }//秒
        stringBuilder.clear()
        val strMilliSecond = if (milliSecond < 10) {
            stringBuilder.append(0).append(milliSecond).toString()
        } else {
            stringBuilder.append(milliSecond).toString()
        }//毫秒
        stringBuilder.clear()
        val result =
            stringBuilder.append(strDay).append("天").append(strHour).append("小时").append(strMinute)
                .append("分钟").append(strSecond).append("秒").append(strMilliSecond).toString()
        stringBuilder.clear()
        return result
    }

    fun onClickButton(view: View) {
        cdv
    }
}