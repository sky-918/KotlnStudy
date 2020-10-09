package com.example.kotlinstudy.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;

import com.example.kotlinstudy.R;

/**
 * Created by CKZ on 2017/8/9.
 */

public class PhotoButton extends View {
    private float circleWidth;//外圆环宽度
    private int outCircleColor;//外圆颜色
    private int innerCircleColor;//内圆颜色
    private int progressColor;//进度条颜色

    private Paint outRoundPaint = new Paint(); //外圆画笔
    private Paint mCPaint = new Paint();//进度画笔
    private Paint innerRoundPaint = new Paint();
    private Paint mBigCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float width; //自定义view的宽度
    private float height; //自定义view的高度
    private float outRaduis; //外圆半径
    private float innerRaduis;//内圆半径
    private GestureDetectorCompat mDetector;//手势识别
    private boolean isLongClick;//是否长按
    private boolean isNeedLongClick = false;//是否需要长按
    private boolean isShowCenterRectangle = false;//是否需要展示中心矩形
    private int maxCircleIndex = 4;//默认循环三次
    private float startAngle = -90;//开始角度
    private float mmSweepAngleStart = 0f;//起点
    private float mmSweepAngleEnd = 360f;//终点
    private float mSweepAngle;//扫过的角度
    private int mLoadingTime;//循环一次的时间


    private int count = 0;

    public PhotoButton(Context context) {
        this(context, null);
    }

    public PhotoButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public PhotoButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TakePhotoButton);
        outCircleColor = array.getColor(R.styleable.TakePhotoButton_outCircleColor, Color.parseColor("#000000"));
        innerCircleColor = array.getColor(R.styleable.TakePhotoButton_innerCircleColor, Color.WHITE);
        progressColor = array.getColor(R.styleable.TakePhotoButton_readColor, Color.GREEN);
        mLoadingTime = array.getInteger(R.styleable.TakePhotoButton_maxSeconds, 10);
        maxCircleIndex = array.getInteger(R.styleable.TakePhotoButton_maxSeconds, 10);
        isNeedLongClick = array.getBoolean(R.styleable.TakePhotoButton_maxSeconds, false);
        mDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                //单击
                isLongClick = false;
                if (listener != null) {
                    listener.onClick(PhotoButton.this);
                }
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                //长按
                isLongClick = true;
                postInvalidate();
                if (listener != null) {
                    listener.onLongClick(PhotoButton.this);
                }
            }
        });
        mDetector.setIsLongpressEnabled(true);


    }

    private void resetParams() {
        width = getWidth();
        height = getHeight();
        //tyy 圆环的宽度
        circleWidth = 15f;
        outRaduis = (float) (Math.min(width, height) / 2);
        innerRaduis = outRaduis;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("PhotoButton", "onMeasure");
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (width > height) {
            setMeasuredDimension(height, height);
        } else {
            setMeasuredDimension(width, width);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("PhotoButton", "onDraw");
        resetParams();
        //整体背景圆
        outRoundPaint.setAntiAlias(true);
        outRoundPaint.setColor(outCircleColor);

        setMinimumWidth((int) width);
        canvas.drawCircle(width / 2, height / 2, outRaduis - circleWidth, outRoundPaint);

        mBigCirclePaint.setStrokeWidth(circleWidth);
        mBigCirclePaint.setStyle(Paint.Style.STROKE);
        mBigCirclePaint.setColor(getResources().getColor(R.color.colorAccent));

        //用于绘画圆环
//        RectF oval = new RectF(0 + circleWidth, 0 + circleWidth, width - circleWidth, height - circleWidth);
        RectF oval = new RectF(0 + circleWidth, 0 + circleWidth, width - circleWidth, height - circleWidth);
        canvas.drawArc(oval, 0, 360, false, mBigCirclePaint);

        //画内圆
        innerRoundPaint.setAntiAlias(true);
        innerRoundPaint.setColor(innerCircleColor);
        if (isLongClick || !isNeedLongClick) {

            onDrawCircle(canvas);

        }
        if (isShowCenterRectangle)
            canvas.drawRoundRect(width / 3, height / 3, width - width / 3, height - height / 3, 10f, 10f, innerRoundPaint);

    }

    //画圆环
    private void onDrawCircle(Canvas canvas) {
        RectF rectF = new RectF(0 + circleWidth, 0 + circleWidth, width - circleWidth, height - circleWidth);
        //画外原环
        mCPaint.setAntiAlias(true);
        mCPaint.setColor(progressColor);
        mCPaint.setStyle(Paint.Style.STROKE);
        mCPaint.setStrokeCap(Paint.Cap.ROUND);
        mCPaint.setStrokeWidth(circleWidth);
        canvas.drawArc(rectF, startAngle, mSweepAngle, false, mCPaint);
    }

    public void start() {


        isShowCenterRectangle = true;
        ValueAnimator animator = ValueAnimator.ofFloat(mmSweepAngleStart, mmSweepAngleEnd);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSweepAngle = (float) valueAnimator.getAnimatedValue();
                //获取到需要绘制的角度，重新绘制
                float time = mSweepAngle / 360 * mLoadingTime + count * mLoadingTime;
                Log.e("时间11111111111111    ", time + "");
                listener.onTimeLong(time);
                invalidate();
            }
        });
        //这里是时间获取和赋值
        ValueAnimator animator1 = ValueAnimator.ofInt(mLoadingTime, 0);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int time = (int) valueAnimator.getAnimatedValue();
                Log.e("时间2  ", time + "");
            }
        });
        AnimatorSet set = new AnimatorSet();

        set.playTogether(animator, animator1);
        set.setDuration(mLoadingTime * 1000);
        set.setInterpolator(new LinearInterpolator());
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.e("次数  ", count + "");
                if (count < maxCircleIndex - 1) {
                    count++;
                    start();
                } else {
                    clearAnimation();
                    isLongClick = false;
                    postInvalidate();
                    if (listener != null) {
                        listener.onFinish();
                    }
                }

            }
        });

    }
//
//    public void onStop() {
//        if (set.isRunning()) {
//            set.pause();
////            clearAnimation();
//            postInvalidate();
//            if (listener != null) {
//                listener.onFinish();
//            }
//        }
//    }
//
//    public void onClear() {
//        set.cancel();
//        clearAnimation();
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        switch (MotionEventCompat.getActionMasked(event)) {
            case MotionEvent.ACTION_DOWN:
                isLongClick = false;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isLongClick) {
                    isLongClick = false;
                    postInvalidate();
                    if (this.listener != null) {
                        this.listener.onLongClickUp(this);
                    }
                }
                break;
        }
        return true;
    }

    private OnProgressTouchListener listener;

    public void setOnProgressTouchListener(OnProgressTouchListener listener) {
        this.listener = listener;
    }

    /**
     * 进度触摸监听
     */
    public interface OnProgressTouchListener {
        /**
         * 单击
         *
         * @param photoButton
         */
        void onClick(PhotoButton photoButton);

        /**
         * 长按
         *
         * @param photoButton
         */
        void onLongClick(PhotoButton photoButton);

        /**
         * 长按抬起
         *
         * @param photoButton
         */
        void onLongClickUp(PhotoButton photoButton);

        /**
         * 计算时间
         *
         * @param time
         */

        void onTimeLong(float time);

        void onFinish();
    }

}
