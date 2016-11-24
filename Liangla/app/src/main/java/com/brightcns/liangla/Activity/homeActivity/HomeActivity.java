package com.brightcns.liangla.Activity.homeActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brightcns.liangla.R;
import com.brightcns.liangla.fragment.FindFragment;
import com.brightcns.liangla.fragment.PerCenterFragment;
import com.brightcns.liangla.fragment.TraficFragment;
import com.brightcns.liangla.fragment.TravelFragment;

public class HomeActivity extends FragmentActivity implements View.OnClickListener{
    private FragmentManager mFragmentManager;

    private TraficFragment mTraficFragment;
    private TravelFragment mTravelFragment;
    private FindFragment mFindFragment;
    private PerCenterFragment mPerCenterFragment;
    private LinearLayout mTraffic,mTravel,mFind,mPerCenter;
    private TextView mTrafic_text,mTravel_text,mFind_text,mPerCenter_text;
    private ImageView mTraffic_btn,mTravel_btn,mFind_btn,mPerCenter_btn;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
        onClick(findViewById(R.id.traffic));//默认为"交通"
    }
    /*
     *初始化控件
     * */
    private  void init(){
        mTraffic        = (LinearLayout) findViewById(R.id.traffic);
        mTrafic_text    = (TextView) findViewById(R.id.traffic_text);
        mTraffic_btn    = (ImageView) findViewById(R.id.traffic_btn);

        mTravel         = (LinearLayout) findViewById(R.id.travel);
        mTravel_text    = (TextView) findViewById(R.id.travel_text);
        mTravel_btn     = (ImageView) findViewById(R.id.travel_btn);

        mFind           = (LinearLayout) findViewById(R.id.travel);
        mFind_text      = (TextView) findViewById(R.id.find_text);
        mFind_btn       = (ImageView) findViewById(R.id.find_btn);

        mPerCenter      = (LinearLayout) findViewById(R.id.find);
        mPerCenter_text = (TextView) findViewById(R.id.perCenter_text);
        mPerCenter_btn  = (ImageView) findViewById(R.id.perCenter_btn);

        mFragmentManager=getSupportFragmentManager();
    }

    //设置menu样式
    private  void setMenuStyle(int vID){
       if (vID==R.id.traffic){
           mTraffic_btn.setImageResource(R.mipmap.traffic_click);
           mTrafic_text.setTextColor(getResources().getColor(R.color.ivory));
       }else{
           mTraffic_btn.setImageResource(R.mipmap.traffic_normal);
           mTrafic_text.setTextColor(getResources().getColor(R.color.colorwhite));
       }
        if (vID==R.id.travel){
            mTravel_btn.setImageResource(R.mipmap.travel_click);
            mTravel_text.setTextColor(getResources().getColor(R.color.ivory));
        }else{
            mTravel_btn.setImageResource(R.mipmap.travel_normal);
            mTravel_text.setTextColor(getResources().getColor(R.color.colorwhite));
        }
        if (vID==R.id.find){
            mFind_btn.setImageResource(R.mipmap.find_click);
            mFind_text.setTextColor(getResources().getColor(R.color.ivory));
        }else{
            mFind_btn.setImageResource(R.mipmap.find_normal);
            mFind_text.setTextColor(getResources().getColor(R.color.colorwhite));
        }
        if (vID==R.id.perCenter){
            mPerCenter_btn.setImageResource(R.mipmap.mine_click);
            mPerCenter_text.setTextColor(getResources().getColor(R.color.ivory));
        }else{
            mPerCenter_btn.setImageResource(R.mipmap.mine_normal);
            mPerCenter_text.setTextColor(getResources().getColor(R.color.colorwhite));
        }

    }
    //隐藏所有fragment

    private void hideFragment(FragmentTransaction transaction){
        if (mTraficFragment!=null){
            transaction.hide(mTraficFragment);
        }
        if (mTravelFragment!=null){
            transaction.hide(mTravelFragment);
        }
        if (mFindFragment!=null){
            transaction.hide(mFindFragment);
        }
        if (mPerCenterFragment!=null){
            transaction.hide(mPerCenterFragment);
        }
    }
    //设置fragment
    private  void setFragment(int vID,FragmentTransaction transaction){
        switch (vID){
            case R.id.traffic:
                if (mTraficFragment==null){
                    mTraficFragment=new TraficFragment();
                    transaction.add(R.id.content,mTraficFragment);
                }else{
                    transaction.show(mTraficFragment);
                }
                break;
            case R.id.travel:
                if (mTravelFragment==null){
                    mTravelFragment=new TravelFragment();
                    transaction.add(R.id.content,mTravelFragment);
                }else{
                    transaction.show(mTravelFragment);
                }
                break;
            case R.id.find:
                if (mFindFragment==null){
                    mFindFragment=new FindFragment();
                    transaction.add(R.id.content,mFindFragment);
                }else{
                    transaction.show(mFindFragment);
                }
                break;
            case R.id.perCenter:
                if (mPerCenterFragment==null){
                    mPerCenterFragment=new PerCenterFragment();
                    transaction.add(R.id.content,mPerCenterFragment);
                }else{
                    transaction.show(mPerCenterFragment);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction=mFragmentManager.beginTransaction();
        int vID=view.getId();
        //设置menu样式
        setMenuStyle(vID);
        //隐藏所有fragment
        hideFragment(transaction);
        //设置Fragment
        setFragment(vID,transaction);
        transaction.commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {//防止UI重叠
        super.onAttachFragment(fragment);
        if (mTraficFragment==null&&fragment instanceof TraficFragment){
            mTraficFragment= (TraficFragment) fragment;
        }else if (mTravelFragment==null&&fragment instanceof TravelFragment){
            mTravelFragment= (TravelFragment) fragment;
        }else if (mFindFragment==null&&fragment instanceof  FindFragment){
            mFindFragment= (FindFragment) fragment;
        }else if (mPerCenterFragment==null&&fragment instanceof PerCenterFragment){
            mPerCenterFragment= (PerCenterFragment) fragment;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
