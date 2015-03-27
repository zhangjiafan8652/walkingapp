package com.zouzhe.walkingapp.register;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.zouzhe.walkingapp.R;
import com.zouzhe.walkingapp.Rules_Activity;
import com.zouzhe.walkingapp.javabean.Cartyperesponse;
import com.zouzhe.walkingapp.javabean.Register1response;
import com.zouzhe.walkingapp.javabean.Register1response1;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.utils.IntentUtils;
import com.zouzhe.walkingapp.utils.Logger;
import com.zouzhe.walkingapp.utils.SPUtils;
import com.zouzhe.walkingapp.utils.StringUtils;
import com.zouzhe.walkingapp.utils.UIUtils;

public class Register1 extends Activity implements OnClickListener {

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
	private String CARTYPEURL = Myconstants.CARTYPEURL;
	// private String SENDverify_codeURL = Constants.SENDverify_codeURL;
	// 请求验证码
	private String SENDverify_codeURL = Myconstants.SENDverify_codeURL;
	// 提交验证码
	private String SUBMITVERIFYURL = Myconstants.SUBMITVERIFYURLZHUCE;
	private CheckBox cb_agreesrules;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register1);
		mactivity = this;
		getcarType();
		initview();

	}

	private void initview() {
		ed_phonenumber = (EditText) findViewById(R.id.ed_phonenumber);
		bt_sendverify_code = (Button) findViewById(R.id.bt_sendverify_code);
		ed_inputverify_code = (EditText) findViewById(R.id.et_inputverify_code);
		bt_submit = (Button) findViewById(R.id.bt_submitverify);

		cb_agreesrules = (CheckBox) findViewById(R.id.cb_register_agreerules);

		bt_sendverify_code.setOnClickListener(this);
		bt_submit.setOnClickListener(this);
		httpUtils = new HttpUtils();

	}

	@Override
	public void onClick(View v) {

		if (!cb_agreesrules.isChecked()) {
			UIUtils.showToastSafe("请阅读并且同意平台规则");
			return;
		}

		switch (v.getId()) {

		case R.id.bt_sendverify_code:
			params = new RequestParams();
			bt_sendverify_code.setClickable(false);
			phonenumber = ed_phonenumber.getText().toString().trim();
			// TODO
			if (StringUtils.isMobileNumber(phonenumber)) {
				params = new RequestParams();
				params.addBodyParameter("mobile", phonenumber);
				params.addBodyParameter("is_register", 0 + "");
				httpUtils.send(HttpMethod.POST, SENDverify_codeURL, params,
						new RequestCallBack<String>() {

							@Override
							public void onStart() {
								UIUtils.showProgresssafe("正在验证手机。。", mactivity);
								super.onStart();

							}

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								UIUtils.stopProgresssafe();
								bt_sendverify_code.setClickable(true);
								SENDMESSGESTATE = HADSENDMESSAGE;
								String results = responseInfo.result.toString()
										.trim();
								Gson gson = new Gson();
								Register1response fromJson = gson.fromJson(
										results, Register1response.class);
								if (fromJson.getStatus().equals("1")) {
									UIUtils.showToastSafe(fromJson.getResult()
											.getcode_msg());
								} else {
									// TODO 这里需要弹出服务器返回的错误信息
									UIUtils.showToastSafe(fromJson.getResult()
											.getcode_msg());
								}

							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								UIUtils.stopProgresssafe();
								bt_sendverify_code.setClickable(true);
								UIUtils.showToastSafe("发送失败，请检查网络状态，重新发送.错误代码201");

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
				if (StringUtils.isMobileNumber(phonenumber)) {
					params.addBodyParameter("mobile_phone_number", phonenumber);
					if (TextUtils.isEmpty(inputverify_code)) {
						UIUtils.showToastSafe("验证码不能为空");
						bt_submit.setClickable(true);
						return;
					}
					params.addBodyParameter("verify_code", inputverify_code);
					httpUtils.send(HttpMethod.POST, SUBMITVERIFYURL, params,
							new RequestCallBack<String>() {

								@Override
								public void onStart() {
									UIUtils.showProgresssafe("正在验证手机。。",
											mactivity);
									super.onStart();
								}

								@Override
								public void onSuccess(
										ResponseInfo<String> responseInfo) {

									UIUtils.stopProgresssafe();
									String results = responseInfo.result
											.toString().trim();
									Gson gson = new Gson();
									Register1response1 fromJson = gson
											.fromJson(results,
													Register1response1.class);
									// UIUtils.showToastSafe(fromJson.getStatus());
									if (fromJson.getStatus().equals("1")) {
										UIUtils.showToastSafe(fromJson
												.getResult().getcode_msg());
										Logger.e("nihao", fromJson.getResult()
												.getDriver_id());
										Myconstants.driver_id = fromJson
												.getResult().getDriver_id();
										SPUtils.putSPstring("driver_id",
												Myconstants.driver_id);
										IntentUtils.startActivity(mactivity,
												Register2.class);
										mactivity.finish();
									} else {
										UIUtils.showToastSafe(fromJson
												.getResult().getcode_msg());
										bt_submit.setClickable(true);
									}

								}

								@Override
								public void onFailure(HttpException error,
										String msg) {
									UIUtils.stopProgresssafe();
									UIUtils.showToastSafe("发送失败，请检查网络状态，重新发送.错误代码201");
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

	private void getcarType() {
		HttpUtils httpUtils2 = new HttpUtils();

		httpUtils2.send(HttpMethod.GET, CARTYPEURL,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

						Gson gson = new Gson();
						Cartyperesponse fromJson = gson.fromJson(
								responseInfo.result, Cartyperesponse.class);
						System.out.println(fromJson.getResult().get(1)
								.getName());
						SPUtils.putObject("cartyperesponse", fromJson);
					}

					@Override
					public void onFailure(HttpException error, String msg) {

					}
				});
	}

	public void readrules(View view) {
		IntentUtils.startActivity(this, Rules_Activity.class);
	}
}
