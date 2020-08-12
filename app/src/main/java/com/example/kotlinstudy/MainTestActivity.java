package com.example.kotlinstudy;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlinstudy.view.CircleButtonView;
import com.example.kotlinstudy.view.TakePhotoButton;
import com.example.kotlinstudy.view.PhotoButton;


public class MainTestActivity extends AppCompatActivity {


    private PhotoButton buttontake;
    private TakePhotoButton button;
    CircleButtonView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        this.buttontake = (PhotoButton) findViewById(R.id.button_take);
        button = (TakePhotoButton) findViewById(R.id.normal_btn);
        circleView = (CircleButtonView) findViewById(R.id.circleView);
        circleView.setMaxTime(20);

        buttontake.setOnProgressTouchListener(new PhotoButton.OnProgressTouchListener() {
            @Override
            public void onClick(PhotoButton photoButton) {
                Toast.makeText(MainTestActivity.this, "单机", Toast.LENGTH_SHORT).show();
//                buttontake.onStop();
            }

            @Override
            public void onLongClick(PhotoButton photoButton) {
                Toast.makeText(MainTestActivity.this, "长按", Toast.LENGTH_SHORT).show();
                buttontake.start();

            }

            @Override
            public void onLongClickUp(PhotoButton photoButton) {

            }


            @Override
            public void onTimeLong(float time) {
                TextView tv = (TextView) findViewById(R.id.tv_five);
                String ss = String.format("%.1f", time);
                if (tv != null) {
                    tv.setText(ss + " 丨60.0");
                }
            }


            @Override
            public void onFinish() {
                Toast.makeText(MainTestActivity.this, "录制结束", Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnProgressTouchListener(new TakePhotoButton.OnProgressTouchListener() {
            @Override
            public void onClick(TakePhotoButton photoButton) {
                Toast.makeText(MainTestActivity.this, "单机", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(TakePhotoButton photoButton) {
                Toast.makeText(MainTestActivity.this, "长按", Toast.LENGTH_SHORT).show();
                button.start();

            }

            @Override
            public void onLongClickUp(TakePhotoButton photoButton) {
                onFinish();
            }


            @Override
            public void onFinish() {
                Toast.makeText(MainTestActivity.this, "录制结束", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
