package com.qw.frame.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qw.frame.R;
import com.qw.frame.entity.ClassEntity;
import com.qw.frame.utils.Constants;
import com.qw.library.utils.ImageDisplay;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;

/**
 * Created by qinwei on 2016/4/25 15:08
 * email:qinwei_it@163.com
 */
public class ClassEntityViewHolder extends QBaseViewHolder<ClassEntity> implements View.OnClickListener {
    private Context context;
    private ImageView mHomeItemIconImg;
    private TextView mHomeItemTitleLabel;
    private ClassEntity clazz;

    public ClassEntityViewHolder(View view, Context context) {
        super(view);
        this.context = context;
    }

    @Override
    public void initializeView(View v) {
        mHomeItemIconImg = (ImageView) v.findViewById(R.id.mHomeItemIconImg);
        mHomeItemTitleLabel = (TextView) v.findViewById(R.id.mHomeItemTitleLabel);
        v.setOnClickListener(this);
    }

    @Override
    public void initializeData(ClassEntity classEntity, int position) {
        this.clazz = classEntity;
        ImageDisplay.getInstance().displayImage(clazz.getIcon(), mHomeItemIconImg);
        mHomeItemTitleLabel.setText(clazz.getTitle());
    }

    @Override
    public void onClick(View v) {
        if (clazz.getClazz() != null) {
            Intent intent = new Intent(context, clazz.getClazz());
            intent.putExtra(Constants.KEY_TITLE, clazz.getTitle());
            context.startActivity(intent);
        }
    }
}
