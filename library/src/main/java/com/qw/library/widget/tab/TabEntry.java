package com.qw.library.widget.tab;


import android.support.v4.app.Fragment;

import java.io.Serializable;

public class TabEntry implements Serializable {
    private String label;
    private int iconRes;
    private Class<? extends Fragment> fragmentClass;

    public TabEntry(String label, int iconRes, Class<? extends Fragment> fragmentClass) {
        super();
        this.label = label;
        this.iconRes = iconRes;
        this.fragmentClass = fragmentClass;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }


}
