package com.example.liuhaifeng.numberlogin;

import android.app.Application;
import android.content.Intent;

import com.squareup.okhttp.OkHttpClient;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import static com.example.liuhaifeng.numberlogin.OkhttpUtils.getSafeFromServer;

/**
 * Created by liuhaifeng on 2017/4/13.
 */

public class myApp extends Application {
    public static OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            client=new OkHttpClient()
                    .setSslSocketFactory(
                            getSafeFromServer(
                                    new BufferedInputStream(getAssets().open("cer.cer"))
                            ).getSocketFactory())
                    .setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            // 未校验服务器端证书域名
                            return true;
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
       getApplicationContext().startService(new Intent(getApplicationContext(), MyService.class));

    }
}
