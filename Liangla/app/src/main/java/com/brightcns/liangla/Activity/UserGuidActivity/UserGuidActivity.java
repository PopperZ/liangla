package com.brightcns.liangla.Activity.UserGuidActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.brightcns.liangla.R;

public class UserGuidActivity extends Activity implements View.OnClickListener{
    private ImageButton mRutern;
    private LinearLayout mQAQ,mAbout,mVersion,mUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guid);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            init();
    }

    private void init() {
        mRutern= (ImageButton) findViewById(R.id.guid_return);
        mRutern.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.guid_return:
                UserGuidActivity.this.finish();
        }
    }
}
