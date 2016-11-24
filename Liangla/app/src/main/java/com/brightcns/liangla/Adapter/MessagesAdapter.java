package com.brightcns.liangla.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brightcns.liangla.CustomView.CircleImageView;
import com.brightcns.liangla.R;
import com.brightcns.liangla.engine.Details;
import com.brightcns.liangla.engine.Messages;

import java.util.ArrayList;


/**
 * Created by wugang on 18/11/16.
 */

public class MessagesAdapter extends BaseAdapter {
    private ArrayList<Messages> lstImageItem;
    private Context mContext;
    private Details mDetails;
    public MessagesAdapter(ArrayList<Messages> lstImageItem, Context mContext){
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
            view= LayoutInflater.from(mContext).inflate(R.layout.messages_item,null);
            viewHolder=new ViewHolder();
            viewHolder.icon= (CircleImageView) view.findViewById(R.id.message_icon);
            viewHolder.name= (TextView) view.findViewById(R.id.message_name);
            viewHolder.content= (TextView) view.findViewById(R.id.message_content);
            viewHolder.time= (TextView) view.findViewById(R.id.message_time);

            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Log.e("TGA",lstImageItem.toString());
        viewHolder.icon.setImageResource(lstImageItem.get(i).getmIcon());
        viewHolder.name.setText(lstImageItem.get(i).getmName());
        viewHolder.content.setText(lstImageItem.get(i).getmMessages());
        viewHolder.time.setText(lstImageItem.get(i).getmTime());
        return view;
    }
    class ViewHolder{
        CircleImageView icon;
        TextView name;
        TextView content;
        TextView time;

    }
}
