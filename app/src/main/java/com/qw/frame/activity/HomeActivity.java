package com.qw.frame.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qw.frame.R;
import com.qw.frame.entity.ClassEntity;
import com.qw.frame.support.BasePullRecyclerViewActivity;
import com.qw.frame.utils.Constants;
import com.qw.library.adapter.QBaseRecyclerViewHolder;
import com.qw.library.utils.ImageDisplay;

import java.util.ArrayList;

/**
 * Created by qinwei on 2016/3/28 14:59
 * email:qinwei_it@163.com
 */
public class HomeActivity extends BasePullRecyclerViewActivity {
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initializeData() {
        setTitle("主页");
        mPullRecyclerView.setPullToRefreshEnabled(false);
        ArrayList<ClassEntity> datas = ClassEntity.getDatas();
        modules.addAll(datas);
        adapter.notifyDataSetChanged();
        setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public QBaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        QBaseRecyclerViewHolder holder = new ViewHolder(LayoutInflater.from(this).inflate(R.layout.activity_home_item, null));
        return holder;
    }

    class ViewHolder extends QBaseRecyclerViewHolder implements View.OnClickListener {
        private ImageView mHomeItemIconImg;
        private TextView mHomeItemTitleLabel;
        private ClassEntity clazz;

        public ViewHolder(View view) {
            super(view);
            mHomeItemIconImg = (ImageView) view.findViewById(R.id.mHomeItemIconImg);
            mHomeItemTitleLabel = (TextView) view.findViewById(R.id.mHomeItemTitleLabel);
            view.setOnClickListener(this);
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
    protected boolean isCanBack() {
        return false;
    }

    @Override
    public void protectApp() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }
}
