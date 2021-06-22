package com.example.kotlinstudy.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_countdown.*
import kotlinx.android.synthetic.main.view_countdown_time.view.*
import java.util.*


/**
 * @author Created by tyy on 2021/6/4
 */
class CountDownView : LinearLayout {
    private var endTimeMilliSecend: Long = 0
    private var stringBuilder: StringBuilder? = null
    private var mHandler: Handler? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    @SuppressLint("Recycle", "ResourceAsColor")
    private fun initView(context: Context?, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_countdown_time, this, true)
        val attributes: TypedArray? =
            context?.obtainStyledAttributes(attrs, R.styleable.CountDownView)

        val textColor =
            attributes?.getColor(R.styleable.CountDownView_android_textColor, R.color.buttonLabel)
        val textSize = attributes?.getDimension(R.styleable.CountDownView_android_textSize, 20f)
        tv_day.setTextColor(textColor!!)
        tv_hour.setTextColor(textColor)
        tv_minute.setTextColor(textColor)
        tv_second.setTextColor(textColor)
        tv_milli_second.setTextColor(textColor)
        tv_day.textSize = textSize!!
        tv_hour.textSize = textSize
        tv_minute.textSize = textSize
        tv_second.textSize = textSize
        tv_milli_second.textSize = textSize
        if (stringBuilder == null) {
            stringBuilder = StringBuilder()
        }
        if (mHandler == null) {
            mHandler = @SuppressLint("HandlerLeak")
            object : Handler() {
                override fun handleMessage(msg: Message?) {
                    when (msg!!.what) {
                        Companion.SHOW_TIME_FLAG -> {
                            val showTime = endTimeMilliSecend - Calendar.getInstance().timeInMillis
                            setTextTime(showTime, stringBuilder!!)
                            mHandler!!.sendEmptyMessageDelayed(
                                SHOW_TIME_FLAG,
                                SHOW_TIME_INTERVAL_FLAG
                            )

                        }
                        else -> throw IllegalStateException("Unexpected value: " + msg.what)
                    }
                }
            }
        }
    }

    fun setEndTimeMilliSecend(endTimeMilliSecend: Long) {
        this.endTimeMilliSecend = endTimeMilliSecend
    }

    fun startCountDownTime() {
        if (mHandler != null) {
            mHandler!!.sendEmptyMessageDelayed(SHOW_TIME_FLAG, SHOW_TIME_INTERVAL_FLAG)
        }
    }

    private fun setTextTime(timeMilliSecend: Long, stringBuilder: StringBuilder) {
        val ss = 1000
        val mi = ss * 60
        val hh = mi * 60
        val dd = hh * 24
        val day = timeMilliSecend / dd
        val hour = (timeMilliSecend - day * dd) / hh
        val minute = (timeMilliSecend - day * dd - hour * hh) / mi
        val second = (timeMilliSecend - day * dd - hour * hh - minute * mi) / ss
        val milliSecond = (timeMilliSecend - day * dd - hour * hh - minute * mi - second * ss) / 10
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
        tv_day.text = strDay
        tv_hour.text = strHour
        tv_minute.text = strMinute
        tv_second.text = strSecond
        tv_milli_second.text = strMilliSecond
    }

    fun clear() {
        if (mHandler != null) {
            mHandler!!.removeCallbacksAndMessages(null)
            mHandler = null
        }
        if (stringBuilder != null) {
            stringBuilder = null
        }
    }

    companion object {
        private const val SHOW_TIME_FLAG = 11
        private const val SHOW_TIME_INTERVAL_FLAG = 10L
    }
}