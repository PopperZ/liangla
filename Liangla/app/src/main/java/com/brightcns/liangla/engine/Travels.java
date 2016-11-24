package com.brightcns.liangla.engine;

/**
 * Created by wugang on 21/11/16.
 */

public class Travels {
    private  int mImage;
    private String mCity;
    private String mIntroduce;

    public Travels(int mImage, String mCity, String mIntroduce) {
        this.mImage = mImage;
        this.mCity = mCity;
        this.mIntroduce = mIntroduce;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmIntroduce() {
        return mIntroduce;
    }

    public void setmIntroduce(String mIntroduce) {
        this.mIntroduce = mIntroduce;
    }
}
