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
import com.brightcns.liangla.engine.PerCenter;

import java.util.ArrayList;


/**
 * Created by wugang on 18/11/16.
 */

public class PercenterAdapter extends BaseAdapter {
    private ArrayList<PerCenter> lstImageItem;
    private Context mContext;
    private Details mDetails;
    public PercenterAdapter(ArrayList<PerCenter> lstImageItem, Context mContext){
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
            view= LayoutInflater.from(mContext).inflate(R.layout.per_msg_layout,null);
            viewHolder=new ViewHolder();
            viewHolder.icon= (ImageButton) view.findViewById(R.id.per_img);
            viewHolder.name= (TextView) view.findViewById(R.id.per_name);
            viewHolder.perCenter= (TextView) view.findViewById(R.id.per_details);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Log.e("TGA",lstImageItem.toString());
        viewHolder.icon.setBackgroundResource(lstImageItem.get(i).getmImage());
        viewHolder.name.setText(lstImageItem.get(i).getmName());
        viewHolder.perCenter.setText(lstImageItem.get(i).getmPerCenter());
        return view;
    }
    class ViewHolder{
        ImageButton icon;
        TextView name;
        TextView perCenter;
    }
}
