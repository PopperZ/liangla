package com.brightcns.liangla.Activity.MessageActivity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.camera2.params.InputConfiguration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brightcns.liangla.CustomView.CircleImageView;
import com.brightcns.liangla.R;

public class MessageDetailsActivity extends Activity implements View.OnClickListener{
    private CircleImageView mIcon;
    private TextView mContent,mName;
    private ImageButton mKill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            init();
    }
    private  void init(){
        Intent intent  =getIntent();
        String message =intent.getStringExtra("message");
        String name    =intent.getStringExtra("name");
        int icon       =intent.getIntExtra("icon",R.mipmap.logo);
        mIcon          = (CircleImageView) findViewById(R.id.content_icon);
        mIcon.setImageResource(icon);
        mContent       = (TextView) findViewById(R.id.message_content);
        mContent.setText(message);
        mName          = (TextView) findViewById(R.id.label_name);
        mName.setText(name);
        mKill          = (ImageButton) findViewById(R.id.kill_messgae);
        mKill.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.kill_messgae:
                MessageDetailsActivity.this.finish();
                break;
        }
    }
}
