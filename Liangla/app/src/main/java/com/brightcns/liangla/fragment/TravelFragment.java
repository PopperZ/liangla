package com.brightcns.liangla.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.brightcns.liangla.Adapter.TravelAdapter;
import com.brightcns.liangla.R;
import com.brightcns.liangla.engine.Travels;

import java.util.ArrayList;

/**
 * Created by wugang on 17/11/16.
 */

public class TravelFragment extends Fragment{
    private View view;// 需要返回的布局
    private TextView textView;
    private int[] images={R.drawable.jinan,R.drawable.tengchong,R.drawable.siaban};
    private String []citys={"济南","腾冲","塞班"};
    private String []introduce={"一城山色半城湖","天下第一银杏王国","四季如夏七色海水"};
    private ArrayList<Travels>list;
    private Travels travels;
    private GridView city_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {// 优化View减少View的创建次数
            view = inflater.inflate(R.layout.travel_layout, null);
            list=new ArrayList<Travels>();
            for (int i=0;i<images.length;i++){
                travels=new Travels(images[i],citys[i],introduce[i]);
                list.add(travels);
            }
            city_view= (GridView) view.findViewById(R.id.trave_gridview);
            city_view.setAdapter(new TravelAdapter(list,getContext()));
            return view;
        }
        textView= (TextView) view.findViewById(R.id.textTravel);
        return view;
    }
}
