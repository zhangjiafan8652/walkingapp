package com.zouzhe.walkingapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zouzhe.walkingapp.javabean.Cartyperesponse;
import com.zouzhe.walkingapp.javabean.Register1response;
import com.zouzhe.walkingapp.javabean.Register1response1;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.utils.IntentUtils;
import com.zouzhe.walkingapp.utils.Logger;
import com.zouzhe.walkingapp.utils.SPUtils;
import com.zouzhe.walkingapp.utils.StringUtils;
import com.zouzhe.walkingapp.utils.UIUtils;


public class Enterin_Activity extends Activity implements View.OnClickListener {

    private EditText ed_phonenumber;
    private Button bt_sendverify_code;
    private EditText ed_inputverify_code;
    private Button bt_submit;
    private String phonenumber;
    private HttpUtils httpUtils;
    private RequestParams params;
    private int SENDMESSGESTATE = 0;
    private int HADSENDMESSAGE = 1;
    private String inputverify_code;
    private Activity mactivity;
    private String SENDverify_codeURL = Myconstants.SENDverify_codeURL;
    private String SUBMITVERIFYURLLONGIN=Myconstants.SUBMITVERIFYURLLONGIN;
    private String CARTYPEURL=Myconstants.CARTYPEURL;
    //private String SENDverify_codeURL = Constants.SENDverify_codeURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_enterin_);
        getcarType();
        initview();
        mactivity = this;
		/*IntentUtils.startActivityAndFinish(
				mactivity, MainUIActivity.class);*/
    }

    private void initview() {
        ed_phonenumber = (EditText) findViewById(R.id.ed_phonenumber);
        bt_sendverify_code = (Button) findViewById(R.id.bt_sendverify_code);
        ed_inputverify_code = (EditText) findViewById(R.id.et_inputverify_code);
        bt_submit = (Button) findViewById(R.id.bt_submitverify);
        bt_sendverify_code.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        httpUtils = new HttpUtils();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_sendverify_code:
                params = new RequestParams();
                bt_sendverify_code.setClickable(false);
                phonenumber = ed_phonenumber.getText().toString().trim();
                if (StringUtils.isMobileNumber(phonenumber)) {
                    params = new RequestParams();
                    params.addBodyParameter("mobile", phonenumber);
                    params.addBodyParameter("is_register",1+"");
                    httpUtils.send(HttpRequest.HttpMethod.POST, SENDverify_codeURL, params,
                            new RequestCallBack<String>() {

                                @Override
                                public void onStart() {
                                    UIUtils.showProgresssafe("正在发送验证码~！", mactivity);
                                    super.onStart();
                                }
                                @Override
                                public void onSuccess(
                                        ResponseInfo<String> responseInfo) {

                                    UIUtils.stopProgresssafe();
                                    //System.out.println("+++"+responseInfo.toString());
                                    bt_sendverify_code.setClickable(true);
                                    SENDMESSGESTATE = HADSENDMESSAGE;
                                    String results = responseInfo.result
                                            .toString().trim();
                                    Logger.e("我这是得到的数据",results);
                                    Gson gson = new Gson();
                                  /*  Register1response fromJson = JSON.parseObject(
                                            results, Register1response.class);*/
                                   // Register1response fromJson = JSON.parseObject(results.toString(), Register1response.class);
                                    Register1response fromJson = gson.fromJson(results, Register1response.class);
                                    if(fromJson.getStatus().equals("1")){
                                        UIUtils.showToastSafe(fromJson.getResult().getcode_msg());
                                    }else {
                                        //TODO 这里需要弹出服务器返回的错误信息
                                        UIUtils.showToastSafe(fromJson.getResult().getcode_msg());
                                    }

                                }

                                @Override
                                public void onFailure(HttpException error,
                                                      String msg) {
                                    UIUtils.stopProgresssafe();
                                    bt_sendverify_code.setClickable(true);
                                    UIUtils.showToastSafe("发送失败，请检查网络状态，重新发送");

                                }

                            });
                } else {
                    bt_sendverify_code.setClickable(true);
                    UIUtils.showToastSafe("请输入正确的电话号码");
                }
                bt_sendverify_code.setClickable(true);
                break;

            case R.id.bt_submitverify:
                bt_submit.setClickable(false);
                if (SENDMESSGESTATE == HADSENDMESSAGE) {
                    params = new RequestParams();
                    phonenumber = ed_phonenumber.getText().toString().trim();
                    inputverify_code = ed_inputverify_code.getText().toString()
                            .trim();
                    Logger.e("手机号码和验证码", phonenumber + inputverify_code);
                    if (StringUtils.isMobileNumber(phonenumber)) {
                        params.addBodyParameter("mobile_phone_number", phonenumber);
                        if (TextUtils.isEmpty(inputverify_code)) {
                            UIUtils.showToastSafe("验证码不能为空");
                            bt_submit.setClickable(true);
                            return;
                        }
                        params.addBodyParameter("code", inputverify_code);
                        httpUtils.send(HttpRequest.HttpMethod.POST, SUBMITVERIFYURLLONGIN, params,
                                new RequestCallBack<String>() {

                                    @Override
                                    public void onStart() {
                                        UIUtils.showProgresssafe("正在验证验证码。。", mactivity);
                                        super.onStart();
                                    }
                                    @Override
                                    public void onSuccess(
                                            ResponseInfo<String> responseInfo) {
                                        UIUtils.stopProgresssafe();

                                        String results = responseInfo.result
                                                .toString().trim();
                                        Gson gson = new Gson();

                                     /*   Register1response1 fromJson = JSON.parseObject(
                                                results, Register1response1.class);*/
                                        Register1response1 fromJson = gson.fromJson(
                                                results, Register1response1.class);
                                        if(fromJson.getStatus().equals("1")){
                                            Logger.e("测试为什么是空",fromJson.getResult().getcode_msg());
                                            UIUtils.showToastSafe(fromJson.getResult().getcode_msg());
                                            Myconstants.driver_id=fromJson.getResult().getDriver_id();
                                            SPUtils.putSPint("LOGINSTATE", Myconstants.HADLOGIN);
                                            SPUtils.putSPstring("driver_id", Myconstants.driver_id);
                                            IntentUtils.startActivityForDelayAndFinish(mactivity, Mainui_Activity.class, 1);

                                        }else{
                                            Logger.e("测试为什么是空",fromJson.getResult().getcode_msg());
                                            UIUtils.showToastSafe(fromJson.getResult().getcode_msg());
                                            bt_submit.setClickable(true);
                                        }

                                    }

                                    @Override
                                    public void onFailure(HttpException error,
                                                          String msg) {
                                        UIUtils.stopProgresssafe();
                                        UIUtils.showToastSafe("请检查网络状态，重新发送.错误代码201");
                                        bt_submit.setClickable(true);
                                    }

                                });
                    } else {
                        UIUtils.showToastSafe("请输入正确的电话号码");
                        bt_submit.setClickable(true);
                    }

                } else {
                    UIUtils.showToastSafe("别闹好不好~！你都没发送验证码~！");
                    bt_submit.setClickable(true);
                }
                break;
            default:
                break;
        }
    }



    private void getcarType(){
        HttpUtils httpUtils2 = new HttpUtils();

        httpUtils2.send(HttpRequest.HttpMethod.GET, CARTYPEURL, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //System.out.println(responseInfo.result);
              // Gson gson = new Gson();

                Cartyperesponse fromJson = JSON.parseObject(responseInfo.result, Cartyperesponse.class);
                //System.out.println(fromJson);
                //System.out.println(fromJson.getResult().get(1).getName());
                SPUtils.putObject("cartyperesponse", fromJson);
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
    }



}
