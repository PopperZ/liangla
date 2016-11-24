package com.brightcns.liangla.engine;

/**
 * Created by wugang on 21/11/16.
 */

public class PerCenter {
    private  int mImage;
    private String mName;
    private String mPerCenter;

    public PerCenter(String mName, int mImage, String mPerCenter) {
        this.mName = mName;
        this.mImage = mImage;
        this.mPerCenter = mPerCenter;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPerCenter() {
        return mPerCenter;
    }

    public void setmPerCenter(String mPerCenter) {
        this.mPerCenter = mPerCenter;
    }
}
