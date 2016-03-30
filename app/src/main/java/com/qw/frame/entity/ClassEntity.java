package com.qw.frame.entity;

import com.qw.frame.activity.HomeBottomActivity;
import com.qw.frame.activity.PullRecyclerViewActivity;

import java.util.ArrayList;

public class ClassEntity {
    private String title;
    private String icon;
    private Class<?> clazz;

    public ClassEntity(String title, String icon, Class<?> clazz) {
        super();
        this.title = title;
        this.icon = icon;
        this.clazz = clazz;
    }

    public static ArrayList<ClassEntity> getDatas() {
        ArrayList<ClassEntity> data = new ArrayList<>();
        data.add(new ClassEntity("ListView", "http://h.hiphotos.baidu.com/image/w%3D310/sign=71e108d6cc1b9d168ac79c60c3deb4eb/960a304e251f95cad6ef08bfcb177f3e670952a4.jpg", null));
        data.add(new ClassEntity("swipeMenuList", "http://e.hiphotos.baidu.com/image/w%3D310/sign=64daf20badc379317d688028dbc4b784/1c950a7b02087bf416fb67e4f0d3572c11dfcfa3.jpg", null));
        data.add(new ClassEntity("HomeBottom", "http://img1.imgtn.bdimg.com/it/u=78892727,3063927320&fm=23&gp=0.jpg", HomeBottomActivity.class));
        data.add(new ClassEntity("PullRecyclerView", "http://img.woyaogexing.com/2015/07/09/d7964c8b84125bb7!600x600.jpg", PullRecyclerViewActivity.class));
        data.add(new ClassEntity("LoadingView", "http://img2.imgtn.bdimg.com/it/u=1485774980,1042819350&fm=21&gp=0.jpg", null));
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
