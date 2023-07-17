package com.example.mrta_app_parking_mobile_v1.activity;

import android.app.PendingIntent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.widget.Toast;


import com.example.mrta_app_parking_mobile_v1.R;
import com.example.mrta_app_parking_mobile_v1.util.ImportantMethod;

public class MainTestReadCardActivity extends ImportantMethod {

    String TAG = "MainTestReadCardActivity";

    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test_read_card);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null) {
            // อุปกรณ์ไม่สนับสนุน NFC
            Toast.makeText(this, "อุปกรณ์ของคุณไม่สนับสนุน NFC", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (!nfcAdapter.isEnabled()) {
            // ต้องเปิดใช้งาน NFC
            Toast.makeText(this, "กรุณาเปิดใช้งาน NFC ในอุปกรณ์ของคุณ", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        handleIntent(getIntent());

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            enableNfcForegroundDispatch();
        }
    }

    private void enableNfcForegroundDispatch() {
        Intent intent = new Intent(this, MainTestReadCardActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        final int flag =  Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT;

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, flag);
        IntentFilter[] intentFiltersArray = new IntentFilter[]{};

        String[][] techList = new String[][]{
                {MifareClassic.class.getName()},
                {Ndef.class.getName()}
        };

        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techList);
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }
    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag != null) {
                readTagData(tag);
                showToastLog(TAG,"sssss");
            }else{

                showToastLog(TAG,"xxxxx");
                Toast.makeText(this, "ไม่สามารถอ่านบัตร Mifare ได้", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void readTagData(Tag tag) {
        Toast.makeText(this, "สามารถอ่านบัตร Mifare ได้", Toast.LENGTH_SHORT).show();

    }
}