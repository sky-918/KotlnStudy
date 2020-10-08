package com.example.kotlinstudy.view.slide

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View


/**
 *Created by TYY on 2020/10/7
 *Explain:
 */
class SlideLayoutView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    private var lastX = 0
    private var lastY = 0

    private var onMoveXYListener: OnMoveXYListener? = null

    interface OnMoveXYListener {
        fun onXYListener(x: Int, y: Int)
    }

    fun setOnMoveXYListener(onMoveXYListener: OnMoveXYListener) {
        this.onMoveXYListener = onMoveXYListener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("PhotoButton", " slide  onTouchEvent")

        //获取到手指处的横坐标和纵坐标
        val x = event.rawX.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = x
                lastY = y
            }

            MotionEvent.ACTION_MOVE -> {
                onMoveXYListener?.onXYListener(x, y)
                //计算移动的距离
                val offX = x - lastX
                val offY = y - lastY
                //调用layout方法来重新放置它的位置
                layout(
                    left + offX, top + offY,
                    right + offX, bottom + offY
                )

                lastX = x
                lastY = y
                //调用layout方法来重新放置它的位置
//                layout(getLeft()+offX, getTop()+offY,
//                        getRight()+offX    , getBottom()+offY);
//                offsetLeftAndRight(offX)
//                offsetTopAndBottom(offY);
            }
        }
        return true
    }
}