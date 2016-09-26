package com.qw.frame.holder;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.qw.frame.R;
import com.qw.frame.entity.Meizhi;
import com.qw.library.utils.ImageDisplay;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;

/**
 * Created by qinwei on 2016/4/25 15:02
 * email:qinwei_it@163.com
 */
public class PullRecyclerViewHolder extends QBaseViewHolder<Meizhi> implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    private ImageView mHomeItemIconImg;
    private TextView mHomeItemTitleLabel;
    private ImageView mHomeItemMenuImg;

    public PullRecyclerViewHolder(View view) {
        super(view);
    }
    public PullRecyclerViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        initializeView(itemView);
    }
    @Override
    public void initializeView(View v) {
        mHomeItemIconImg = (ImageView) v.findViewById(R.id.mHomeItemIconImg);
        mHomeItemMenuImg = (ImageView) v.findViewById(R.id.mHomeItemMenuImg);
        mHomeItemTitleLabel = (TextView) v.findViewById(R.id.mHomeItemTitleLabel);
        mHomeItemMenuImg.setOnClickListener(this);
    }

    @Override
    public void initializeData(Meizhi meizhi) {
        ImageDisplay.getInstance().displayImage(meizhi.getUrl(), mHomeItemIconImg);
        mHomeItemTitleLabel.setText(meizhi.getDesc());
    }

    @Override
    public void onClick(View v) {
        PopupMenu menu=new PopupMenu(context,v);
        MenuInflater inflater=menu.getMenuInflater();
        menu.setOnMenuItemClickListener(this);
        inflater.inflate(R.menu.menu_loading_view,menu.getMenu());
        menu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_one:
                Toast.makeText(context, ""+item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_two:
                break;
            case R.id.action_nodata:
                break;
            case R.id.action_loading:
                break;
            case R.id.action_err:
                break;
            default:
                break;
        }
        return false;
    }
}
