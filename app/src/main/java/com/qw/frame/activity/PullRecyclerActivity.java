package com.qw.frame.activity;

import android.os.Handler;
import android.os.Message;
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
import com.qw.frame.core.BaseListActivity;
import com.qw.library.utils.ImageDisplay;
import com.qw.library.widget.IFooterView;
import com.qw.library.widget.pulltorefresh.PullRecyclerView;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;
import com.qw.library.widget.pulltorefresh.layout.MGridLayoutManager;
import com.qw.library.widget.pulltorefresh.layout.MLinearLayoutManager;
import com.qw.library.widget.pulltorefresh.layout.MStaggeredGridLayoutManager;


/**
 * Created by qinwei on 2015/10/26 14:57
 * email:qinwei_it@163.com
 */
public class PullRecyclerActivity extends BaseListActivity {

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
    public void onRefresh(PullRecyclerView.State state) {
        if (state == PullRecyclerView.State.PULL_TO_START) {
            handler.sendEmptyMessageDelayed(0, 3000);
        }else{
            handler.sendEmptyMessageDelayed(1, 3000);
        }
    }
    @Override
    protected QBaseViewHolder onCreateAdapterView(ViewGroup parent, int viewType) {
        QBaseViewHolder holder = new ViewHolder(LayoutInflater.from(this).inflate(R.layout.activity_pullrecyclerview_item, null));
        return holder;
    }
    class ViewHolder extends QBaseViewHolder {
        private ImageView mHomeItemIconImg;
        private TextView mHomeItemTitleLabel;

        public ViewHolder(View view) {
            super(view);
        }

        @Override
        public void initializeView(View v) {
            mHomeItemIconImg = (ImageView) v.findViewById(R.id.mHomeItemIconImg);
            mHomeItemTitleLabel = (TextView) v.findViewById(R.id.mHomeItemTitleLabel);
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
                setLayoutManager(new MLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
        MStaggeredGridLayoutManager lm = new MStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        setLayoutManager(lm);
    }

    private void setGridLayoutManager() {
         MGridLayoutManager lm = new MGridLayoutManager(this, 2);
        setLayoutManager(lm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview_swiperefresh_layout_mode, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected boolean isLoadMoreEnabled() {
        return true;
    }

    @Override
    public boolean isPullToRefreshEnabled() {
        return true;
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mPullRecycler.onRefreshCompleted();
            if (msg.what == 0) {
                modules.clear();
            } else {
                if (modules.size() > 20) {
                    adapter.notifyLoadMoreStateChanged(IFooterView.State.error);
                } else {
                    adapter.notifyLoadMoreStateChanged(IFooterView.State.done);
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
