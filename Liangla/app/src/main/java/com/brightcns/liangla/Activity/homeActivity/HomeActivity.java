package com.brightcns.liangla.Activity.homeActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
           mTrafic_text.setTextColor(getResources().getColor(R.color.colorAccent));
       }else{
           mTraffic_btn.setImageResource(R.mipmap.traffic_normal);
           mTrafic_text.setTextColor(getResources().getColor(R.color.colorwhite));
       }
        if (vID==R.id.travel){
            mTravel_btn.setImageResource(R.mipmap.travel_click);
            mTravel_text.setTextColor(getResources().getColor(R.color.colorAccent));
        }else{
            mTravel_btn.setImageResource(R.mipmap.travel_normal);
            mTravel_text.setTextColor(getResources().getColor(R.color.colorwhite));
        }
        if (vID==R.id.find){
            mFind_btn.setImageResource(R.mipmap.find_click);
            mFind_text.setTextColor(getResources().getColor(R.color.colorAccent));
        }else{
            mFind_btn.setImageResource(R.mipmap.find_normal);
            mFind_text.setTextColor(getResources().getColor(R.color.colorwhite));
        }
        if (vID==R.id.perCenter){
            mPerCenter_btn.setImageResource(R.mipmap.mine_click);
            mPerCenter_text.setTextColor(getResources().getColor(R.color.colorAccent));
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

}
