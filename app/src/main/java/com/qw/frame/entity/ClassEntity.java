package com.qw.frame.entity;

import android.os.Bundle;

import com.qw.frame.activity.CheeseDetailActivity;
import com.qw.frame.activity.HomeBottomActivity;
import com.qw.frame.activity.HomeViewPagerActivity;
import com.qw.frame.activity.LoadingViewActivity;
import com.qw.frame.activity.LoopViewPagerActivity;
import com.qw.frame.activity.PullRecyclerActivity;
import com.qw.frame.activity.FragmentContentActivity;
import com.qw.frame.fragment.TitleFragment;
import com.qw.frame.utils.Constants;

import java.util.ArrayList;

public class ClassEntity {
    private String title;
    private String icon;
    private Class<?> clazz;
    public Bundle args;
    public ClassEntity(String title, String icon, Class<?> clazz) {
        super();
        this.title = title;
        this.icon = icon;
        this.clazz = clazz;
    }
    public ClassEntity(String title, String icon, Class<?> clazz,Bundle args) {
        super();
        this.args=args;
        this.title = title;
        this.icon = icon;
        this.clazz = clazz;
    }

    public static ArrayList<ClassEntity> getDatas() {
        ArrayList<ClassEntity> data = new ArrayList<>();
        data.add(new ClassEntity("ListView", "http://h.hiphotos.baidu.com/image/w%3D310/sign=71e108d6cc1b9d168ac79c60c3deb4eb/960a304e251f95cad6ef08bfcb177f3e670952a4.jpg", null));
        Bundle args=new Bundle();
        args.putSerializable(Constants.KEY_FRAGMENT_CLASS, TitleFragment.class);
        data.add(new ClassEntity("FragmentContentActivity", "http://e.hiphotos.baidu.com/image/w%3D310/sign=64daf20badc379317d688028dbc4b784/1c950a7b02087bf416fb67e4f0d3572c11dfcfa3.jpg", FragmentContentActivity.class,args));
        data.add(new ClassEntity("HomeBottom", "http://img1.imgtn.bdimg.com/it/u=78892727,3063927320&fm=23&gp=0.jpg", HomeBottomActivity.class));
        data.add(new ClassEntity("PullRecyclerView", "http://img.woyaogexing.com/2015/07/09/d7964c8b84125bb7!600x600.jpg", PullRecyclerActivity.class));
        data.add(new ClassEntity("LoadingView", "http://img2.imgtn.bdimg.com/it/u=1485774980,1042819350&fm=21&gp=0.jpg", LoadingViewActivity.class));
        data.add(new ClassEntity("HomeViewPager", "http://tupian.enterdesk.com/uploadfile/2014/0314/20140314023019977.jpeg.210.1000.jpg", HomeViewPagerActivity.class));
        data.add(new ClassEntity("CheeseDetail", "http://wenwen.soso.com/p/20111023/20111023192748-1009185806.jpg", CheeseDetailActivity.class));
        data.add(new ClassEntity("LoopViewPager", "http://wenwen.soso.com/p/20111023/20111023192748-1009185806.jpg", LoopViewPagerActivity.class));
        return data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

}
