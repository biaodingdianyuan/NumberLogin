package com.example.liuhaifeng.numberlogin;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

import static com.example.liuhaifeng.numberlogin.R.id.editText2;

/**
 * Created by liuhaifeng on 2017/4/20.
 */

public class MyService extends Service {
    private MySmsObserver mySmsObserver;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 2:
                    Toast.makeText(getApplicationContext(), msg.obj+"", Toast.LENGTH_SHORT).show();

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mySmsObserver = new MySmsObserver(this,myhandler);
        Uri uri = Uri.parse("content://sms");
        getContentResolver().registerContentObserver(uri, true, mySmsObserver);
        Toast.makeText(getApplicationContext(), "发送完毕", Toast.LENGTH_SHORT).show();
    }


}
