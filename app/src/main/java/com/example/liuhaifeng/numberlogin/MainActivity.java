package com.example.liuhaifeng.numberlogin;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final int MSG_RECEIVER_CODE = 1;

    SmsManager smsManager = SmsManager.getDefault();
    @InjectView(R.id.phoneNumber)
    EditText phoneNumber;
    @InjectView(R.id.editText2)
    EditText editText2;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.getnumber)
    Button getnumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.login, R.id.getnumber})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:

                break;
            case R.id.getnumber:


                break;
        }
    }
}
