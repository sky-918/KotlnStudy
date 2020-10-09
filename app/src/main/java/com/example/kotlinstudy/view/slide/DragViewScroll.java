package com.example.kotlinstudy.view.slide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by TYY on 2020/10/7
 * Explain:
 */

public class DragViewScroll extends View{


    private int lastX;
    private int lastY;
    private Scroller mScroller;

    public DragViewScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mScroller = new Scroller(context);
    }

    public DragViewScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public DragViewScroll(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }



    public boolean onTouchEvent(MotionEvent event) {

        //获取到手指处的横坐标和纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();


        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_MOVE:

                //计算移动的距离
                int offX = x - lastX;
                int offY = y - lastY;

                View viewGroup = (View) getParent();

                //开启滑动
                mScroller.startScroll(viewGroup.getScrollX(),
                        viewGroup.getScrollY(), -offX, -offY);

                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }



    public void computeScroll() {
        super.computeScroll();

        if(mScroller.computeScrollOffset())
        {
            ((View)getParent()).scrollTo(mScroller.getCurrX(),
                    mScroller.getCurrY());
        }

        invalidate();//必须要调用
    }
}