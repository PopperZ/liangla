package com.brightcns.liangla.Activity.guideActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.brightcns.liangla.Activity.homeActivity.HomeActivity;
import com.brightcns.liangla.Adapter.SplashViewAdapter;
import com.brightcns.liangla.R;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends Activity implements View.OnClickListener, ViewPager.OnPageChangeListener {
/*引导页
*
* 使用viewpage来实现首次安装app时的引导页面；
*/
    private ViewPager splashView;
    private TextView mGuideShow;
    private List<View>mViews;
    private SplashViewAdapter splashViewAdapter;
    //引导图片
    private static final int [] mPics={R.drawable.a,R.drawable.aa,R.drawable.aaa,R.drawable.aaaa,};
    //底部小点
    private ImageButton [] mPoints;
    // 记录当前选择位置
    private int mCurrentIndex;
    private View  mSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();//初始化控件
        ininPoints();//初始化底部小点
        splashViewAdapter=new SplashViewAdapter(mViews);
        splashView.setAdapter(splashViewAdapter);
        splashView.setOnPageChangeListener(this);
    }
    private void init(){
        mViews= new ArrayList<View>();
        mGuideShow= (TextView) findViewById(R.id.guideshow);
        splashView = (ViewPager) findViewById(R.id.splashView);
        LayoutInflater inflater=getLayoutInflater();
        mSplash=inflater.inflate(R.layout.splashview,null);
        for (int i=0;i<mPics.length;i++){
            mSplash=inflater.inflate(R.layout.splashview,null);
            mSplash.setBackgroundResource(mPics[i]);
            mViews.add(mSplash);
        }
    }
    /**
     * 初始化底部小点
     * */

    private void ininPoints(){
        LinearLayout ll= (LinearLayout) findViewById(R.id.pointView);
        mPoints=new ImageButton[mPics.length];
        for (int i=0;i<mPoints.length;i++){
            mPoints[i]= (ImageButton) ll.getChildAt(i);
            mPoints[i].setTag(i);
            mPoints[i].setEnabled(false);//选中状态
        }
        mCurrentIndex=0;
        mPoints[mCurrentIndex].setEnabled(true);
    }
    /*
    * 设置当前的引导页
    * */
    private  void setCurView(int position ){
        if (position<0||position>=mPics.length){
            return;
        }
        mSplash.setBackgroundResource(mPics[position]);
    }
    /*
    * 引导小点的选中
    * */
    private void setCurPoint(int position){
        if (position<0||position>mPics.length||mCurrentIndex==position){
            return;
        }
        mPoints[position].setEnabled(true);
        mPoints[mCurrentIndex].setEnabled(false);
        mCurrentIndex=position;
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        setCurView(position);
        setCurPoint(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
//在选择后的业务
    @Override
    public void onPageSelected(int position) {
        setCurPoint(position);
        setCurView(position);
        if (position==(mPics.length-1)){
            mGuideShow.setVisibility(View.VISIBLE);
            mGuideShow.setOnClickListener(new View.OnClickListener() {//跳转到主页面
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"跳转",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            });
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
