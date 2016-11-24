package com.brightcns.liangla.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.brightcns.liangla.Activity.LoginActivity.LoginActivity;
import com.brightcns.liangla.Activity.MessageActivity.MyMessageActivity;
import com.brightcns.liangla.Activity.UserGuidActivity.UserGuidActivity;
import com.brightcns.liangla.Adapter.PercenterAdapter;
import com.brightcns.liangla.R;
import com.brightcns.liangla.engine.Details;
import com.brightcns.liangla.engine.PerCenter;

import java.util.ArrayList;

/**
 * Created by wugang on 17/11/16.
 */

public class PerCenterFragment extends Fragment implements View.OnClickListener{
    private View view;// 需要返回的布局
    private LinearLayout mLogin;
    private SharedPreferences.Editor mEdited=null;
    private SharedPreferences mShare =null;
    private int [] images={R.mipmap.banlance,R.mipmap.bank,R.mipmap.recharge,R.mipmap.fund};
    private String [] names= {"余额","我的银行卡","充值","基金"};
    private String [] perCenter={"2.78", "1","立即转入","买入率1折起"};
    private GridView mGridView;
    private PerCenter mPerCenter;
    private PercenterAdapter mPercenterAdapter;
    private LinearLayout mUsermsg,mUserGuid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mShare=getActivity().getSharedPreferences("admin",getActivity().MODE_PRIVATE);
        mEdited=mShare.edit();
        boolean isLogin=mShare.getBoolean("isLogin",true);
        if (view == null) {// 优化View减少View的创建次数
            if (isLogin) {
                view = inflater.inflate(R.layout.percenter_layout, null);
                mLogin = (LinearLayout) view.findViewById(R.id.no_login);
                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }else{
                view=inflater.inflate(R.layout.per_login_layout,null);
                init(view);
                ArrayList<PerCenter> list=new ArrayList<PerCenter>();
                for (int i=0;i<images.length;i++){
                    mPerCenter=new PerCenter(names[i],images[i],perCenter[i]);
                    list.add(mPerCenter);
                }
                mPercenterAdapter=new PercenterAdapter(list,getContext()) ;
                mGridView.setAdapter(mPercenterAdapter);
            }
            return view;
        }
        return view;
    }
    private void init(View v){
        mGridView   = (GridView) v.findViewById(R.id.per_msg);

        mUsermsg    = (LinearLayout) v.findViewById(R.id.mymsg);
        mUsermsg.setOnClickListener(this);
        mUserGuid   = (LinearLayout) v.findViewById(R.id.user_guid);
        mUserGuid.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mymsg:
                Intent intent =new Intent(getActivity(), MyMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.user_guid:
                Intent intent_guid=new Intent(getActivity(), UserGuidActivity.class);
                startActivity(intent_guid);
                break;
            default:
                break;
        }
    }
}
