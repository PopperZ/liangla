package com.brightcns.liangla.Activity.welcomeActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;

import com.brightcns.liangla.Activity.guideActivity.SplashActivity;
import com.brightcns.liangla.Activity.homeActivity.HomeActivity;
import com.brightcns.liangla.R;

public class WelcomeActivity extends Activity {
    // 定义一个布尔值
    private Boolean mIsfirst = null;
    SharedPreferences.Editor mEdited=null;
    SharedPreferences mShare =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    /***
     * 创建文件，实例化SharedPreferences
     * */
        mShare=getSharedPreferences("isFist", Context.MODE_PRIVATE);
        mEdited=mShare.edit();
        /**
         *取数据，有为true，没有直接赋值false
         *
         * */
        mIsfirst =mShare.getBoolean("isFist",true);
        new customAsync().execute();
    }
    /**
     *
     *定义异步任务类
     * */
    class customAsync extends AsyncTask<String, Integer,String>{
        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {//如果第一次下载打开则赋值，并跳转到引导页
            super.onPostExecute(result);
            if (mIsfirst){
                mEdited.putBoolean("isFist",false);
                mEdited.commit();
                Intent intent=new Intent(WelcomeActivity.this,SplashActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }else{//非第一次打开则跳转到主页
                Intent intent=new Intent(WelcomeActivity.this,HomeActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }
    }
}
