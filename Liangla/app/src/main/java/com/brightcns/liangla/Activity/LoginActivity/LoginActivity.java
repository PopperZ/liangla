package com.brightcns.liangla.Activity.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.brightcns.liangla.Activity.RegisterActivity.RegisterActivity;
import com.brightcns.liangla.R;

public class LoginActivity extends Activity implements View.OnClickListener{
    private Button mRegister,mResetPwd,mLogin;
    private EditText mPhoneNum,mPwd;
    private ImageButton mPwd_show;
    private SharedPreferences.Editor mEdited=null;
    private SharedPreferences mShare =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        mShare=getSharedPreferences("admin",MODE_PRIVATE);
        mEdited=mShare.edit();
    }
    private void init(){
        mPhoneNum   = (EditText) findViewById(R.id.phonenum);
        mPhoneNum.setOnClickListener(this);
        mPwd        = (EditText) findViewById(R.id.pwd);
        mPwd.setOnClickListener(this);
        mRegister   = (Button) findViewById(R.id.register);
        mRegister.setOnClickListener(this);
        mResetPwd   = (Button) findViewById(R.id.reset_pwd);
        mResetPwd.setOnClickListener(this);
        mLogin      = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mPwd_show   = (ImageButton) findViewById(R.id.pwd_show);
        mPwd_show.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                Intent register=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.reset_pwd:
                Intent resetpwd=new Intent(LoginActivity.this, ResetPwdActivity.class);
                startActivity(resetpwd);
                break;
            case R.id.login:
                Toast.makeText(this,"正在登陆。。。。",Toast.LENGTH_SHORT).show();
                mEdited.putBoolean("isLogin",false);
                mEdited.commit();
                break;
            case R.id.pwd_show:

                Toast.makeText(this,"显示密码",Toast.LENGTH_SHORT).show();

                break;

        }
    }
}
