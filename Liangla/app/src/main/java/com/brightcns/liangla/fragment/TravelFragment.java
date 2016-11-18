package com.brightcns.liangla.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brightcns.liangla.R;

/**
 * Created by wugang on 17/11/16.
 */

public class TravelFragment extends Fragment{
    private View view;// 需要返回的布局
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {// 优化View减少View的创建次数
            view = inflater.inflate(R.layout.travel_layout, null);
            return view;
        }
        textView= (TextView) view.findViewById(R.id.textTravel);
        return view;
    }
}
