package com.brightcns.liangla.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brightcns.liangla.R;

/**
 * Created by wugang on 17/11/16.
 */

public class FindFragment extends Fragment{
    private View view;// 需要返回的布局

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {// 优化View减少View的创建次数
            view = inflater.inflate(R.layout.find_layout, null);
            return view;
        }
        return view;
    }
}
