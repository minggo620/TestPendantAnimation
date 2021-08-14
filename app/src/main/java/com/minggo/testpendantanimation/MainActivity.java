package com.minggo.testpendantanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private Button startBt;
    private Button cancelBt;

    private List<ImageView> imageViewList;
    private float oY;

    private List<AnimatorSet> animatorSetList;

    private boolean canAnimate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        imageViewList = new ArrayList<>();
        animatorSetList = new ArrayList<>();

        imageView1 = findViewById(R.id.iv_1);
        imageView2 = findViewById(R.id.iv_2);
        imageView3 = findViewById(R.id.iv_3);
        imageView4 = findViewById(R.id.iv_4);
        imageView5 = findViewById(R.id.iv_5);
        imageView6 = findViewById(R.id.iv_6);

        imageViewList.add(imageView1);
        imageViewList.add(imageView2);
        imageViewList.add(imageView3);
        imageViewList.add(imageView4);
        imageViewList.add(imageView5);
        imageViewList.add(imageView6);

        startBt = findViewById(R.id.bt_start);
        cancelBt = findViewById(R.id.bt_cancel);

        for (ImageView imageView : imageViewList){
            imageView.setVisibility(View.GONE);
        }

        startBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0;i<imageViewList.size();i++){
                    final int finalI = i;
                    imageViewList.get(finalI).setVisibility(View.GONE);
                    startBt.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (canAnimate) {
                                startAnimation(imageViewList.get(finalI));
                            }
                        }
                    },i*100);

                }
            }
        });

        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAnimator();
            }
        });

        oY = imageView1.getY();
    }

    private void startAnimation(ImageView imageView){
        imageView.setVisibility(View.VISIBLE);
        ValueAnimator animatorUP = ObjectAnimator.ofFloat(imageView,"translationY",40,-20);
        animatorUP.setDuration(100);

        ValueAnimator animatorDown = ObjectAnimator.ofFloat(imageView,"translationY",-20,0);
        animatorDown.setDuration(168);

        ValueAnimator animatorRotate1= ObjectAnimator.ofFloat(imageView,"rotation",0,20);
        animatorRotate1.setDuration(100);

        ValueAnimator animatorRotate2= ObjectAnimator.ofFloat(imageView,"rotation",20,-5);
        animatorRotate2.setDuration(200);

        ValueAnimator animatorRotate3= ObjectAnimator.ofFloat(imageView,"rotation",-5,0);
        animatorRotate3.setDuration(100);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorDown).after(animatorUP);
        animatorSet.play(animatorRotate1).after(animatorDown);
        animatorSet.play(animatorRotate2).after(animatorRotate1);
        animatorSet.play(animatorRotate3).after(animatorRotate2);

        animatorSetList.add(animatorSet);
        if (canAnimate) {
            animatorSet.start();
        }

    }

    private synchronized void cancelAnimator(){
        canAnimate = false;

        for (AnimatorSet animatorSet :animatorSetList) {
            animatorSet.end();
        }

        for (ImageView imageView : imageViewList){
            imageView.clearAnimation();
            imageView.setVisibility(View.VISIBLE);
        }
    }

}