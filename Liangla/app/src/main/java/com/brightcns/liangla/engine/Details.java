package com.brightcns.liangla.engine;

/**
 * Created by wugang on 18/11/16.
 */

public class Details {
    private int icon;
    private String name;

    public Details(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
