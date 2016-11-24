package com.brightcns.liangla.fragment;

import android.app.MediaRouteActionProvider;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.brightcns.liangla.Activity.ScanCardActivity.ScanCardActivity;
import com.brightcns.liangla.Adapter.DetailsAdapter;
import com.brightcns.liangla.R;
import com.brightcns.liangla.engine.Details;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by wugang on 17/11/16.
 */

public class TraficFragment extends Fragment implements View.OnClickListener{
    private View view;// 需要返回的布局
    private GridView mDetails;
    private Details mDetail;
    private DetailsAdapter mDetailsAdapter;
    private LinearLayout mScanCard;
    private int [] images={R.mipmap.help,R.mipmap.bar,R.mipmap.subway,R.mipmap.card,R.mipmap.rimtravel,
            R.mipmap.money,R.mipmap.shake,R.mipmap.total};
    private String [] names={"帮一帮","公交查询","地铁查询","卡卷","周边游","亮啦理财","摇一摇","全部"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {// 优化View减少View的创建次数
            view = inflater.inflate(R.layout.traffic_layout, null);
            init(view);
            ArrayList<Details> list=new ArrayList<Details>();
            for (int i=0;i<images.length;i++){
                mDetail=new Details(images[i],names[i]);
                list.add(mDetail);
                Log.d("TGA",list.toString());
            }
            mDetailsAdapter=new DetailsAdapter(list,getContext());
            mDetails.setAdapter(mDetailsAdapter);
            return view;
        }
        return view;
    }
    private void init(View view){
        mDetails= (GridView) view.findViewById(R.id.details);
        mScanCard= (LinearLayout) view.findViewById(R.id.scancard);
        mScanCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.scancard:
                Toast.makeText(getActivity(),"确认在手机Nfc开启状态下扫卡",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), ScanCardActivity.class);
                startActivity(intent);
                break;
        }
    }
}
