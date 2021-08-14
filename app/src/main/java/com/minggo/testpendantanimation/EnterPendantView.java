package com.minggo.testpendantanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author minggo(戴统民)
 * @date 2021/8/13
 */
public class EnterPendantView extends LinearLayout {

    private List<ImageView> imageViewList;
    private List<AnimatorSet> animatorSetList;
    public boolean canAnimate;

    public EnterPendantView(Context context) {
        super(context);
        initView(context);
    }

    public EnterPendantView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EnterPendantView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public EnterPendantView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        imageViewList = new ArrayList<>();
        animatorSetList = new ArrayList<>();
        LayoutInflater.from(context).inflate(R.layout.nn_enter_room_pendant_view, this);
    }

    public void init(boolean isTheaterMode) {

        imageViewList.clear();
        animatorSetList.clear();
        requestLayout();
//        if (isTheaterMode) {
//            this.removeAllViews();
//            LayoutInflater.from(context).inflate(R.layout.nn_enter_room_pendant_view_theater_mode, this);
//        }
        imageViewList.add((ImageView) this.findViewById(R.id.iv_pendant_1));
        imageViewList.add((ImageView) this.findViewById(R.id.iv_pendant_2));
        imageViewList.add((ImageView) this.findViewById(R.id.iv_pendant_3));
        imageViewList.add((ImageView) this.findViewById(R.id.iv_pendant_4));
        imageViewList.add((ImageView) this.findViewById(R.id.iv_pendant_5));
        imageViewList.add((ImageView) this.findViewById(R.id.iv_pendant_6));

        for (ImageView imageView : imageViewList) {
            imageView.setVisibility(View.GONE);
        }

    }


    public void startAnimations() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < imageViewList.size(); i++) {
                    final int finalI = i;

                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!imageViewList.isEmpty() && imageViewList.size() > finalI) {
                                startPendantAnimation(imageViewList.get(finalI));
                            }

                        }
                    }, (i + 1) * 80);

                }
            }
        },300);

    }


    private void startPendantAnimation(ImageView imageView) {
        imageView.setVisibility(View.VISIBLE);
        ValueAnimator animatorUP = ObjectAnimator.ofFloat(imageView, "translationY", 30, -20);
        animatorUP.setDuration(100);

        ValueAnimator animatorDown = ObjectAnimator.ofFloat(imageView, "translationY", -20, 0);
        animatorDown.setDuration(168);

        ValueAnimator animatorRotate1 = ObjectAnimator.ofFloat(imageView, "rotation", 0, 20);
        animatorRotate1.setDuration(100);

        ValueAnimator animatorRotate2 = ObjectAnimator.ofFloat(imageView, "rotation", 20, -5);
        animatorRotate2.setDuration(200);

        ValueAnimator animatorRotate3 = ObjectAnimator.ofFloat(imageView, "rotation", -5, 0);
        animatorRotate3.setDuration(100);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorDown).after(animatorUP);
        animatorSet.play(animatorRotate1).after(animatorDown);
        animatorSet.play(animatorRotate2).after(animatorRotate1);
        animatorSet.play(animatorRotate3).after(animatorRotate2);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSetList.add(animatorSet);
        animatorSet.start();

    }

    public void cancelAnimator() {

//        for (AnimatorSet animatorSet : animatorSetList) {
//            animatorSet.end();
//        }

        for (ImageView imageView : imageViewList) {
            imageView.clearAnimation();
//            imageView.requestLayout();
            imageView.setVisibility(View.VISIBLE);
        }
        imageViewList.clear();

//        requestLayout();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        startAnimations();
//        Logger.w("M_PENDANT","EnterRoomMsgPendantView onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        cancelAnimator();
//        Logger.w("M_PENDANT","EnterRoomMsgPendantView onDetachedFromWindow");
    }

    public void setPendants(List<Bitmap> pendantBitmapList) {

        // TODO 模拟数据

//        if (pendantBitmapList != null && !pendantBitmapList.isEmpty()) {
//            setVisibility(VISIBLE);
//            for (int i = 0; i < MAX_COUNT; i++) {
//                if (pendantBitmapList.size() > 0 && pendantBitmapList.size() - 1 >= i) {
//                    imageViewList.get(i).setVisibility(VISIBLE);
//                    imageViewList.get(i).setImageBitmap(pendantBitmapList.get(i));
//                } else {
//                    imageViewList.get(i).setVisibility(INVISIBLE);
//                }
//            }
//            startAnimations();
//        } else {
//            setVisibility(GONE);
//        }

    }
}
