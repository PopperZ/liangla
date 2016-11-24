package com.brightcns.liangla.Activity.ScanCardActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.brightcns.liangla.R;

public class CardHistoryActivity extends Activity {
    private TextView mHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_history);
        mHistory= (TextView) findViewById(R.id.history);
        Intent intent=getIntent();
        String history=intent.getStringExtra("history_money")+intent.getStringExtra("history_time")+intent.getStringExtra("history_time2");
//        String history=intent.getStringExtra("history_money");
//        mHistory.setText(Html.fromHtml(history));
        mHistory.setText(history);
    }
}
