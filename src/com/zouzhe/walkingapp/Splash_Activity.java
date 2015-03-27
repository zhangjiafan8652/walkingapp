package com.zouzhe.walkingapp;

import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.utils.IntentUtils;
import com.zouzhe.walkingapp.utils.Logger;
import com.zouzhe.walkingapp.utils.PackageUtils;
import com.zouzhe.walkingapp.utils.SPUtils;
import com.zouzhe.walkingapp.utils.UIUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;



public class Splash_Activity extends Activity {

    private String k;

    private Activity mAcitivity;

    private TextView tv_versionname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_);
        mAcitivity = this;
        // 友盟推送
        //   PushAgent.getInstance(this).onAppStart();

        initveiw();
        System.out.print("你好呀");
        Logger.e("傻瓜啊","都不知道你在干吗");
        UIUtils.showToastSafe("你好呀");
        // 友盟更新
        //  umengUpdate();
        // 跳转到主界面
        // IntentUtils.startActivityForDelayAndFinish(this, Login.class, 3000);
        int LOGINSTATE = SPUtils.getSPint("LOGINSTATE", Myconstants.NEVERLOGIN);
        System.out.println(System.currentTimeMillis()+"---------------------------");
        if (LOGINSTATE == Myconstants.NEVERLOGIN) {
        	Logger.e("傻瓜啊","都不知道你在干吗1");
        	IntentUtils.startActivityForDelayAndFinish(mAcitivity, Login_Activity.class,
                    3000);
        } else {
        	Logger.e("傻瓜啊","都不知道你在干吗2");
            IntentUtils.startActivityForDelayAndFinish(mAcitivity,
                    Mainui_Activity.class, 3000);
        }


    }

    private void initveiw() {
        tv_versionname = (TextView) findViewById(R.id.tv_splash_versionname);
        String versionName = PackageUtils.getVersionName();
        tv_versionname.setText("版本号:"+versionName);
    }
}
