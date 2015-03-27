package com.zouzhe.walkingapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.register.Register1;
import com.zouzhe.walkingapp.utils.IntentUtils;
import com.zouzhe.walkingapp.utils.Logger;
import com.zouzhe.walkingapp.utils.SPUtils;




public class Login_Activity extends Activity {
    private Button bt_login;
    private Button bt_register;
    private Activity mactivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_);
        Logger.e("傻瓜啊","都不知道你在干吗3");
        initview();
        mactivity=this;

    }

    private void initview() {
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_register = (Button) findViewById(R.id.bt_register);

        bt_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Logger.i("点击进去登录", "登录");
                int k=SPUtils.getSPint("LOGINSTATE", Myconstants.NEVERLOGIN);
                if(k==Myconstants.NEVERLOGIN){
                    IntentUtils.startActivityAndFinish(mactivity, Enterin_Activity.class);
                }else{
                    IntentUtils.startActivityAndFinish(mactivity, Mainui_Activity.class);
                }

            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(mactivity, Register1.class);

            }
        });
    }



}
