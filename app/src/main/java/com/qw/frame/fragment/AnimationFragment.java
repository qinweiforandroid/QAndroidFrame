package com.qw.frame.fragment;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.widget.ImageView;

import com.qw.frame.R;
import com.qw.frame.core.BaseFragment;

/**
 * Created by qinwei on 2016/9/23 21:54
 * email:qinwei_it@163.com
 */

public class AnimationFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_animation;
    }

    private ImageView mAnimationPictureImg;


    @Override
    protected void initializeView(View v) {
        mAnimationPictureImg = (ImageView) v.findViewById(R.id.mAnimationPictureImg);
        mAnimationPictureImg.setOnClickListener(this);
        onClick(mAnimationPictureImg);
    }

    @Override
    public void onClick(View v) {
//        ObjectAnimator.ofFloat(v, "rotationX", 0.0F, 360.0F)//
//                .setDuration(500)//
//                .start();

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(v, pvhX, pvhY,pvhZ).setDuration(1000).start();
    }
}
