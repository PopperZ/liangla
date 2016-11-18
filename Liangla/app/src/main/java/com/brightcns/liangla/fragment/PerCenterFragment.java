package com.brightcns.liangla.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.brightcns.liangla.Activity.LoginActivity.LoginActivity;
import com.brightcns.liangla.R;

/**
 * Created by wugang on 17/11/16.
 */

public class PerCenterFragment extends Fragment{
    private View view;// 需要返回的布局
    private LinearLayout mLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {// 优化View减少View的创建次数
            view = inflater.inflate(R.layout.percenter_layout, null);
            mLogin= (LinearLayout) view.findViewById(R.id.no_login);
            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            return view;
        }
        return view;
    }
}
