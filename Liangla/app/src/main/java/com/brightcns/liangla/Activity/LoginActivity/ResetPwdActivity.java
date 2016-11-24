package com.brightcns.liangla.Activity.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.brightcns.liangla.Activity.RegisterActivity.RegisterActivity;
import com.brightcns.liangla.R;

public class ResetPwdActivity extends Activity implements View.OnClickListener{
    private Button mNext,mReturn;
    private EditText mNum,mCaptcha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        init();
    }
    private void init(){
        mNum     = (EditText) findViewById(R.id.phonenum);
        mCaptcha = (EditText) findViewById(R.id.captcha);
        mNext    = (Button) findViewById(R.id.next_reset);
        mNext.setOnClickListener(this);
        mReturn  = (Button) findViewById(R.id.return_btn);
        mReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next_reset:
                Toast.makeText(this,"下一步",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(ResetPwdActivity.this,ResetActivity.class);
                startActivity(intent);
                ResetPwdActivity.this.finish();
                break;
            case R.id.return_btn:
                Intent intent1=new Intent(ResetPwdActivity.this,LoginActivity.class);
                startActivity(intent1);
                ResetPwdActivity.this.finish();
        }
    }
}
