package com.brightcns.liangla.engine;

/**
 * Created by wugang on 21/11/16.
 */

public class Messages {
    private String mName;
    private int mIcon;
    private String mMessages;
    private String mTime;

    public Messages(String mName, int mIcon, String mMessages, String mTime) {
        this.mName = mName;
        this.mIcon = mIcon;
        this.mMessages = mMessages;
        this.mTime = mTime;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public String getmMessages() {
        return mMessages;
    }

    public void setmMessages(String mMessages) {
        this.mMessages = mMessages;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
