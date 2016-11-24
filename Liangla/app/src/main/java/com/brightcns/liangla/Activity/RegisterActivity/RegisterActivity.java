package com.brightcns.liangla.Activity.RegisterActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.brightcns.liangla.Activity.LoginActivity.LoginActivity;
import com.brightcns.liangla.R;

public class RegisterActivity extends Activity implements View.OnClickListener{
    private TextView mReturn,mAgreement,mQaq;
    private EditText mNum,mCaptcha,mPwd;
    private Button mNext;

    private String phoneNum,captcha,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
    private void init(){
        mAgreement = (TextView) findViewById(R.id.agreement);
        mAgreement.setOnClickListener(this);
        mAgreement.setText("《亮啦用户协议》");
        mQaq       = (TextView) findViewById(R.id.qaq);
        mQaq.setOnClickListener(this);
        mReturn    = (TextView) findViewById(R.id.return_view);
        mReturn.setOnClickListener(this);
        mReturn.setText("<<");

        mNum     = (EditText) findViewById(R.id.phonenum);
        phoneNum =mNum.getText().toString();
        mCaptcha = (EditText) findViewById(R.id.captcha);
        captcha  =mCaptcha.getText().toString();
        mPwd     = (EditText) findViewById(R.id.pwd);
        pwd      =mPwd.getText().toString();
        mNext    = (Button) findViewById(R.id.next_register);
        mNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.return_view:
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                RegisterActivity.this.finish();
                break;
            case R.id.agreement:
                Toast.makeText(this,"亮啦协议",Toast.LENGTH_SHORT).show();
                break;
            case R.id.qaq:
                Toast.makeText(this,"遇到问题",Toast.LENGTH_SHORT).show();
                break;
            case R.id.next_register:
                if (phoneNum.length()==11||captcha.length()==6||pwd.length()>=6){
                    mNext.setBackgroundColor(getResources().getColor(R.color.mediumaquamarine));
                }
        }
    }
}
