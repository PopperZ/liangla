package com.brightcns.liangla.Activity.ScanCardActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.brightcns.liangla.R;
import com.brightcns.liangla.utils.CardManager;

public class ScanCardActivity extends Activity {
    private PendingIntent mPendingIntent;
    private NfcAdapter mNfcAdapter;
    private String data=null;
    private Resources mResources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_card);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        mNfcAdapter=NfcAdapter.getDefaultAdapter(this);
        mPendingIntent=PendingIntent.getActivity(this,0,new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP),0);
        mResources=getResources();
        onNewIntent(getIntent());
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter!=null){
            mNfcAdapter.enableForegroundDispatch(this,mPendingIntent, CardManager.FILTERS,CardManager.TECHLISTS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter!=null){
            mNfcAdapter.disableReaderMode(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        final Parcelable p=intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (p!=null){
            data=CardManager.load(p,mResources);
        }else{
            data=null;
        }
        if (data==null||data.length()==0){
            return;
        }else{
            intent.setClass(ScanCardActivity.this,CardMessageActivity.class);
            Bundle bundle=new Bundle();
            Log.d("data",data);
            Log.d("data",CardManager.load(p,mResources));
            bundle.putString("data",CardManager.load(p,mResources));
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
