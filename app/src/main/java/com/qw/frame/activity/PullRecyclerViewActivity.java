package com.qw.frame.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qw.frame.R;
import com.qw.frame.support.BasePullRecyclerViewActivity;
import com.qw.library.adapter.QBaseRecyclerViewHolder;
import com.qw.library.utils.ImageDisplay;
import com.qw.library.widget.FooterView;


/**
 * Created by qinwei on 2015/10/26 14:57
 * email:qinwei_it@163.com
 */
public class PullRecyclerViewActivity extends BasePullRecyclerViewActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_pullrecyclerview);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
    }

    @Override
    protected void initializeData() {
        setTitle("RecyclerRefreshView");
        modules.add("http://f.hiphotos.baidu.com/image/h%3D360/sign=967a27edcf80653864eaa215a7dca115/8cb1cb1349540923d83dc1dd9758d109b3de4938.jpg");
        modules.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=b6b75d1360380cd7f91ea4eb9145ad14/ca1349540923dd54508705cbd409b3de9d824898.jpg");
        modules.add("http://img2.imgtn.bdimg.com/it/u=1539738387,1812146723&fm=23&gp=0.jpg");
        modules.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=b6b75d1360380cd7f91ea4eb9145ad14/ca1349540923dd54508705cbd409b3de9d824898.jpg");
        modules.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=8f437d29d5a20cf45990f8d946084b0c/9d82d158ccbf6c8156cbd646b93eb13532fa40a4.jpg");
        modules.add("http://c.hiphotos.baidu.com/image/h%3D360/sign=c1bb2ce88f1001e9513c1209880f7b06/a71ea8d3fd1f4134f57d607f271f95cad1c85e6c.jpg");
        modules.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=b6b75d1360380cd7f91ea4eb9145ad14/ca1349540923dd54508705cbd409b3de9d824898.jpg");
        modules.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=8f437d29d5a20cf45990f8d946084b0c/9d82d158ccbf6c8156cbd646b93eb13532fa40a4.jpg");
        modules.add("http://c.hiphotos.baidu.com/image/h%3D360/sign=c1bb2ce88f1001e9513c1209880f7b06/a71ea8d3fd1f4134f57d607f271f95cad1c85e6c.jpg");
        modules.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=b6b75d1360380cd7f91ea4eb9145ad14/ca1349540923dd54508705cbd409b3de9d824898.jpg");
        modules.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=8f437d29d5a20cf45990f8d946084b0c/9d82d158ccbf6c8156cbd646b93eb13532fa40a4.jpg");
        modules.add("http://c.hiphotos.baidu.com/image/h%3D360/sign=c1bb2ce88f1001e9513c1209880f7b06/a71ea8d3fd1f4134f57d607f271f95cad1c85e6c.jpg");
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getHeaderViewId() {
        return R.layout.layout_header_view;
    }

    @Override
    public void initializeHeaderView(View view) {
        super.initializeHeaderView(view);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("initializeHeaderView");
    }


    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    public void onLoadMore() {
        handler.sendEmptyMessageDelayed(1, 3000);
    }

    @Override
    public QBaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        QBaseRecyclerViewHolder holder = new ViewHolder(LayoutInflater.from(this).inflate(R.layout.activity_pullrecyclerview_item, null));
        return holder;
    }

    class ViewHolder extends QBaseRecyclerViewHolder {
        private ImageView mHomeItemIconImg;
        private TextView mHomeItemTitleLabel;

        public ViewHolder(View view) {
            super(view);
            mHomeItemIconImg = (ImageView) view.findViewById(R.id.mHomeItemIconImg);
            mHomeItemTitleLabel = (TextView) view.findViewById(R.id.mHomeItemTitleLabel);
        }

        @Override
        public void initializeData(int position) {
            String icon = (String) modules.get(position);
            ImageDisplay.getInstance().displayImage(icon, mHomeItemIconImg);
            mHomeItemTitleLabel.setText("position:" + position + icon);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                break;
            case R.id.action_gridview:
                setGridLayoutManager();
                break;
            case R.id.action_staggered:
                setStaggeredGridLayoutManager();
                break;
            case R.id.action_add:
                break;
            case R.id.action_remove:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setStaggeredGridLayoutManager() {
        StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        setLayoutManager(lm);
    }

    private void setGridLayoutManager() {
        //初始化布局管理器
        final GridLayoutManager lm = new GridLayoutManager(this, 2);
        /*
        *设置SpanSizeLookup，它将决定view会横跨多少列。这个方法是为RecyclerView添加Header和Footer的关键。
        *当判断position指向的View为Header或者Footer时候，返回总列数（ lm.getSpanCount()）,即可让其独占一行。
        */
        lm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //第0個位置和最后一个位置独占一行
                if (isCanLoadMore() && adapter.getItemCount() == position + 1) {
                    return lm.getSpanCount();
                }
                if (isContainerHeader() && position == 0) {
                    return lm.getSpanCount();
                }

                return 1;
            }
        });
        setLayoutManager(lm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview_swiperefresh_layout_mode, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected boolean isCanLoadMore() {
        return true;
    }

    @Override
    public boolean isContainerHeader() {
        return true;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                modules.clear();
                onRefreshCompleted();
                mPullRecyclerView.mSwipeRefreshLayout.setEnabled(true);
            } else {
                if (modules.size() > 20) {
                    notifyFooterViewDataChanged(FooterView.State.error);
                } else {
                    notifyFooterViewDataChanged(FooterView.State.done);
                }
            }
            modules.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=8f437d29d5a20cf45990f8d946084b0c/9d82d158ccbf6c8156cbd646b93eb13532fa40a4.jpg");
            modules.add("http://c.hiphotos.baidu.com/image/h%3D360/sign=c1bb2ce88f1001e9513c1209880f7b06/a71ea8d3fd1f4134f57d607f271f95cad1c85e6c.jpg");
            modules.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=b6b75d1360380cd7f91ea4eb9145ad14/ca1349540923dd54508705cbd409b3de9d824898.jpg");
            modules.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=8f437d29d5a20cf45990f8d946084b0c/9d82d158ccbf6c8156cbd646b93eb13532fa40a4.jpg");
            modules.add("http://c.hiphotos.baidu.com/image/h%3D360/sign=c1bb2ce88f1001e9513c1209880f7b06/a71ea8d3fd1f4134f57d607f271f95cad1c85e6c.jpg");
            adapter.notifyDataSetChanged();
        }
    };
}
