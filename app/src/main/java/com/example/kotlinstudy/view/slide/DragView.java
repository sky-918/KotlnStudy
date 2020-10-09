package com.example.kotlinstudy.view.slide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by TYY on 2020/10/7
 * Explain:
 */
public class DragView extends View {


    private int lastX;
    private int lastY;


    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context) {
        super(context);
    }

    OnMoveXYListener onMoveXYListener;

    interface OnMoveXYListener {
        void onXYListener(int x, int y);
    }

    void setOnMoveXYListener(OnMoveXYListener onMoveXYListener) {
        this.onMoveXYListener = onMoveXYListener;
    }


    public boolean onTouchEvent(MotionEvent event) {


        //获取到手指处的横坐标和纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                lastX = x;
                lastY = y;

                break;

            case MotionEvent.ACTION_MOVE:

                //计算移动的距离
                int offX = x - lastX;
                int offY = 0;
                //调用layout方法来重新放置它的位置
                layout(getLeft()+offX, getTop()+offY,
                        getRight()+offX    , getBottom()+offY);


//                ((View) getParent()).scrollBy(-offX,- offY);
//                offsetLeftAndRight(offX);
//                offsetTopAndBottom(offY);
                break;
        }

        return true;
    }
}