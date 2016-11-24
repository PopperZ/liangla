package com.brightcns.liangla.Activity.ScanCardActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.brightcns.liangla.R;

public class CardMessageActivity extends Activity implements View.OnClickListener{
    private  String mData=null;
    private TextView mCardName,mCardId,mCardMoney,mCardRecord,mCardHistory,mCardHelp;
    private String  mName,mId,mMoney,
            mHistory,mHistory_time,mHistory_money,mHistory_time2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_message);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        init();
        Bundle bundle=this.getIntent().getExtras();
        mData=bundle.getString("data");
        formTheDataSource();
        fillData();
    }

    private void fillData() {
        mCardName.setText(mName);
        mCardId.setText(mId);
        mCardMoney.setText(mMoney);
        mCardHistory.setOnClickListener(this);
    }

    private void formTheDataSource() {
        try {
            mName=mData.substring(mData.indexOf("类型")+2,mData.indexOf("余"));
            mId=mData.substring(mData.indexOf("序号"), mData.indexOf("版") - 6);
            mMoney = mData.substring(mData.indexOf("余额"), mData.indexOf("元") + 1);
            mHistory_time=mData.substring(mData.indexOf(">")+1,mData.indexOf("-"));
            mHistory_time2=mData.substring(mData.indexOf(">")+53,mData.indexOf("-")+53);
            mHistory_money=mMoney;
            Log.d("data",mData+"mmm");
            Log.d("time",mHistory_time2+"kk");
            Log.d("money",mHistory_money);
        }catch (Exception e){
            Toast.makeText(this,"读卡失败，请重新扫卡！",Toast.LENGTH_SHORT).show();
            Log.e("TGA",e.toString());
        }
    }

    private void init() {
        mCardName= (TextView) findViewById(R.id.card_name);
        mCardId= (TextView) findViewById(R.id.card_id);
        mCardMoney= (TextView) findViewById(R.id.card_money);
        mCardRecord= (TextView) findViewById(R.id.card_record);
        mCardHistory= (TextView) findViewById(R.id.card_history);
        mCardHelp= (TextView) findViewById(R.id.card_help);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_history:
                Intent intent=new Intent(CardMessageActivity.this,CardHistoryActivity.class);
                intent.putExtra("history_time",mHistory_time);
                intent.putExtra("history_money",mHistory_money);
                intent.putExtra("history_time2",mHistory_time2);
                startActivity(intent);
        }
    }
}
