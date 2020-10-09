package com.example.kotlinstudy.view.slide

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.kotlinstudy.R

class SlideLineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mWidth = 0
    private var mHeight = 0
    private var mPaint: Paint? = null
    private var rectF: RectF? = null
    private var rectWidth = 50
    var mDp2 = 0
    private var downX = 0f
    private var lineColor =0


    private var onMoveXYListener: OnMoveXYListener? = null

    interface OnMoveXYListener {
        fun onXYListener(x: Float, y: Int)
    }

    fun setOnMoveXYListener(onMoveXYListener: OnMoveXYListener) {
        this.onMoveXYListener = onMoveXYListener
    }
    private fun init(
        context: Context,
        attrs: AttributeSet?
    ) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.SlideLayoutView)
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.style = Paint.Style.STROKE
        mDp2 = 10
        rectWidth = array.getInteger(R.styleable.SlideLayoutView_lineWidth, 2)
        lineColor = array.getColor(
            R.styleable.SlideLayoutView_lineColor,
            Color.parseColor("#FF4A83")
        )
        mPaint!!.strokeWidth = mDp2.toFloat()
    }

    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        if (mWidth == 0) {
            mWidth = width
            mHeight = height
            rectF = RectF()
            rectF!!.left = mWidth / 2 - rectWidth / 2.toFloat()
            rectF!!.top = 0f
            rectF!!.right = rectF!!.left + rectWidth
            rectF!!.bottom = mHeight.toFloat()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        move(event)
        return true
    }

    var isMove = false
    private fun move(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.rawX
                Log.d("点击的坐标", "downX= " + downX + "RECTL=" + (rectF!!.left - 100))
                if (downX < paddingLeft + left + rectF!!.left + rectWidth + 100 && downX > paddingLeft + left + rectF!!.left - 100) {
                    isMove = true
                }
            }
            MotionEvent.ACTION_MOVE -> if (isMove) {



                val moveX = event.rawX
                val scrollX = moveX - downX
                rectF!!.left = rectF!!.left + scrollX
                rectF!!.right = rectF!!.left + rectWidth
                //防止滑出控件
                if (rectF!!.left < 0) {
                    rectF!!.left = 0f
                    rectF!!.right = rectF!!.left + rectWidth
                }
                if (rectF!!.right > mWidth) {
                    rectF!!.right = mWidth.toFloat()
                    rectF!!.left = mWidth - rectWidth.toFloat()
                }
                invalidate()
                onMoveXYListener?.onXYListener(rectF!!.left+left, 0)
                downX = moveX
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> isMove = false
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        mPaint!!.color = lineColor
        mPaint!!.style = Paint.Style.FILL
        canvas.drawRect(rectF!!.left, rectF!!.top, rectF!!.left + rectWidth, rectF!!.bottom, mPaint)
    }

    init {
        init(context, attrs)
    }
}