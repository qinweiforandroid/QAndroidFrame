package com.qw.frame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qw.frame.R;
import com.qw.frame.core.BaseListActivity;
import com.qw.frame.entity.ClassEntity;
import com.qw.frame.utils.Constants;
import com.qw.library.utils.ImageDisplay;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;
import com.qw.library.widget.pulltorefresh.layout.MGridLayoutManager;

import java.util.ArrayList;

/**
 * Created by qinwei on 2016/3/28 14:59
 * email:qinwei_it@163.com
 */
public class HomeActivity extends BaseListActivity {
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initializeData(Bundle saveInstance) {
        setTitle("主页");
        mPullRecycler.setPullToRefreshEnabled(false);
        ArrayList<ClassEntity> datas = ClassEntity.getDatas();
        modules.addAll(datas);
        adapter.notifyDataSetChanged();
        setLayoutManager(new MGridLayoutManager(this, 2));
    }

    @Override
    protected QBaseViewHolder onCreateAdapterView(ViewGroup parent, int viewType) {
        QBaseViewHolder holder = new ViewHolder(LayoutInflater.from(this).inflate(R.layout.activity_home_item, null));
        return holder;
    }

    class ViewHolder extends QBaseViewHolder implements View.OnClickListener {
        private ImageView mHomeItemIconImg;
        private TextView mHomeItemTitleLabel;
        private ClassEntity clazz;

        public ViewHolder(View view) {
            super(view);
        }

        @Override
        public void initializeView(View v) {
            mHomeItemIconImg = (ImageView) v.findViewById(R.id.mHomeItemIconImg);
            mHomeItemTitleLabel = (TextView) v.findViewById(R.id.mHomeItemTitleLabel);
            v.setOnClickListener(this);
        }

        @Override
        public void initializeData(int position) {
            clazz = (ClassEntity) modules.get(position);
            ImageDisplay.getInstance().displayImage(clazz.getIcon(), mHomeItemIconImg);
            mHomeItemTitleLabel.setText(clazz.getTitle());
        }

        @Override
        public void onClick(View v) {
            if (clazz.getClazz() != null) {
                Intent intent = new Intent(HomeActivity.this, clazz.getClazz());
                intent.putExtra(Constants.KEY_TITLE, clazz.getTitle());
                startActivity(intent);
            }
        }
    }

    @Override
    protected boolean hasBackIcon() {
        return false;
    }

    @Override
    public void protectApp() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }
}
