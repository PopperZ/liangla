package com.brightcns.liangla.Activity.MessageActivity;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.brightcns.liangla.Adapter.MessagesAdapter;
import com.brightcns.liangla.R;
import com.brightcns.liangla.engine.Messages;
import com.brightcns.liangla.fragment.PerCenterFragment;

import java.util.ArrayList;

public class MyMessageActivity extends FragmentActivity implements View.OnClickListener{
    private ImageButton msg_return;
    private TextView tips,mClearMessage;
    private MessagesAdapter messagesAdapter;
    private ListView listView;
    private  ArrayList<Messages> mList;
    private FragmentManager manager;
    private LinearLayout message_list;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
        mList= new ArrayList<Messages>();
        for (int i = 0; i < 5; i++) {
            Messages messages = new Messages("亮啦团队", R.mipmap.logo, "消息" + i, "六个月前");
            mList.add(messages);
        }
        messagesAdapter=new MessagesAdapter(mList,getApplicationContext());
        listView.setAdapter(messagesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.putExtra("message",mList.get(i).getmMessages());
                intent.putExtra("icon",mList.get(i).getmIcon());
                intent.putExtra("name",mList.get(i).getmName());
                intent.setClass(MyMessageActivity.this,MessageDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        msg_return= (ImageButton) findViewById(R.id.msg_return);
        msg_return.setOnClickListener(this);
        tips      = (TextView) findViewById(R.id.tips);
        listView= (ListView) findViewById(R.id.user_msg);
        mClearMessage= (TextView) findViewById(R.id.clear_message);
        mClearMessage.setOnClickListener(this);
        message_list= (LinearLayout) findViewById(R.id.message_list);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.msg_return:
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.message_list,new PerCenterFragment());
                transaction.commit();
                MyMessageActivity.this.finish();
                break;
            case R.id.clear_message:
                mList.clear();
                messagesAdapter.notifyDataSetChanged();
                tips.setText("暂时没有消息！");

        }
    }
}
