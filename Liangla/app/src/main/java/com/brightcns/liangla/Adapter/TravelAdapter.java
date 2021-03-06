package com.brightcns.liangla.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brightcns.liangla.R;
import com.brightcns.liangla.engine.Details;
import com.brightcns.liangla.engine.Travels;

import java.util.ArrayList;


/**
 * Created by wugang on 18/11/16.
 */

public class TravelAdapter extends BaseAdapter {
    private ArrayList<Travels> lstImageItem;
    private Context mContext;
    private Details mDetails;
    public TravelAdapter(ArrayList<Travels> lstImageItem, Context mContext){
        this.lstImageItem=lstImageItem;
        this.mContext=mContext;
    }
    @Override
    public int getCount() {
        return lstImageItem.size();
    }

    @Override
    public Object getItem(int i) {
        return lstImageItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.travel_item,null);
            viewHolder=new ViewHolder();
            viewHolder.icon= (ImageButton) view.findViewById(R.id.travel_image);
            viewHolder.city= (TextView) view.findViewById(R.id.city_name);
            viewHolder.introduce= (TextView) view.findViewById(R.id.city_introduce);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Log.e("TGA",lstImageItem.toString());
        viewHolder.icon.setBackgroundResource(lstImageItem.get(i).getmImage());
        viewHolder.city.setText(lstImageItem.get(i).getmCity());
        viewHolder.introduce.setText(lstImageItem.get(i).getmIntroduce());
        return view;
    }
    class ViewHolder{
        ImageButton icon;
        TextView city;
        TextView introduce;
    }
}
