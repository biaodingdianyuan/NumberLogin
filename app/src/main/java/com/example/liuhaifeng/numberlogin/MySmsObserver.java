package com.example.liuhaifeng.numberlogin;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuhaifeng on 2017/4/19.
 */

public class MySmsObserver extends ContentObserver {
    private Context mContext;
    private Handler mHandler;


    public MySmsObserver(Context context, Handler handler) {
        super(handler);
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);

        if (uri.toString().equals("content://sms/raw")) {
            return;
        }

        Uri queryUri = Uri.parse("content://sms/inbox");
        String code = "";
        final Cursor cursor = mContext.getContentResolver().query(queryUri, null, null, null, "date desc");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String message = cursor.getString(cursor.getColumnIndex("body"));

                // TODO: 2015/9/28 这里可以根据address做一些自己的判断，比如只有特定的手机号才做判断

                Log.e("guxuewu", "address:==>" + address + " message:==>" + message);

                // TODO: 2015/9/28 这里可以根据自己的项目进行特定的正则表达式的编写
                Pattern pattern = Pattern.compile("(\\d{6})");
                Matcher matcher = pattern.matcher(message);

                if (matcher.find()) {
                    code = matcher.group(0);
                   // mHandler.obtainMessage(MainActivity.MSG_RECEIVER_CODE, code).sendToTarget();
                    final String url = "https://192.168.18.83:9443/testweb/test?action=3&smscode=" + code;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Request request = new Request.Builder().get().url(url).build();

                            Response response = null;
                            try {
                                response = myApp.client.newCall(request).execute();
                                if (response.isSuccessful()) {



                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();

                    String pid = cursor.getString(cursor.getColumnIndex("thread_id"));

                    int result= mContext.getContentResolver().delete(Uri.parse("content://sms/"),"thread_id="+pid, null);
                    Log.d("deleteSMS", "threadId:: " + pid + "  result::"
                            + result);
                    mHandler.obtainMessage(2, result).sendToTarget();
                    cursor.close();
                }
            }


        }


    }
}
