package com.zouzhe.walkingapp.register;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.zouzhe.walkingapp.R;
import com.zouzhe.walkingapp.javabean.Cartyperesponse;
import com.zouzhe.walkingapp.javabean.Driverbean;
import com.zouzhe.walkingapp.javabean.Register23response;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.utils.IntentUtils;
import com.zouzhe.walkingapp.utils.SPUtils;
import com.zouzhe.walkingapp.utils.StringUtils;
import com.zouzhe.walkingapp.utils.UIUtils;

public class Register2 extends Activity {

	/*
	 * private static final String[] mCountries = { "点击请选择您的车型", "大巴", "中巴",
	 * "越野", "商务", "小车" }; private static final String[] mCountries2 = {
	 * "54b4f6296909ad52466667dd","54781c55f083f93ecdd056d1",
	 * "54781c45f083f93ecdd056d0", "54781c33f083f93ecdd056cf",
	 * "54781c12f083f93ecdd056ce", "54781becf083f93ecdd056cd" };
	 */
	private EditText name;
	private EditText nation;
	private EditText position;
	private EditText vehicle_age;
	private EditText passenger_number;
	private EditText price;
	private EditText license_plate_number;
	private EditText description;
	private Spinner vehicle;
	private Button save;
	private Driverbean driverbean;
	private EditText vehicle_model;
	private String SIJIZILIAOURL = Myconstants.SIJIZILIAOURL;
	private Activity mactivity;

	private String[] mCountries;
	private String[] mCountries2;
	private String tempid;
	private int tempi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register2);
		mactivity = this;

		Cartyperesponse object = SPUtils.getObject("cartyperesponse");
		if (object == null) {
			UIUtils.showToastSafe("请重新登陆联网获取车型信息");
		} else {
			mCountries = new String[object.getResult().size()];
			mCountries2 = new String[object.getResult().size()];
			// mCountries[0] = "请选择车型";
			// mCountries2[0] = object.getResult().get(0).get_id();
			for (int i = 0; i < mCountries2.length; i++) {
				if (object.getResult().get(i).getName().equals("不限车型")) {
					tempid = object.getResult().get(i).get_id();
					tempi = i;
				}
				mCountries[i] = object.getResult().get(i).getName();
				mCountries2[i] = object.getResult().get(i).get_id();

				// System.out.println(mCountries);
			}
		}
		String temp0name = mCountries[0];
		String temp0id = mCountries2[0];
		mCountries[0] = "请选择车型";
		mCountries2[0] = tempid;
		mCountries[tempi] = temp0name;
		mCountries2[tempi] = temp0id;

		initview();
	}

	private void initview() {
		name = (EditText) findViewById(R.id.ed_register2_name);
		nation = (EditText) findViewById(R.id.ed_register2_nation);
		position = (EditText) findViewById(R.id.ed_register2_position);
		vehicle_age = (EditText) findViewById(R.id.ed_regist2_vehicle_age);
		passenger_number = (EditText) findViewById(R.id.ed_regist2_passenger_number);
		price = (EditText) findViewById(R.id.ed_regist2_price);
		license_plate_number = (EditText) findViewById(R.id.ed_regist2_license_plate_number);
		description = (EditText) findViewById(R.id.ed_regist2_description);
		vehicle_model = (EditText) findViewById(R.id.ed_regist2_vehicle_model);

		vehicle = (Spinner) findViewById(R.id.sn_register2_vehicle);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mCountries);
		ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vehicle.setAdapter(ad);

		driverbean = new Driverbean();
		save = (Button) findViewById(R.id.bt_regist2_save);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (vehicle.getSelectedItemPosition() == 0) {
					UIUtils.showToastSafe("请选择车型，在最后一栏");
					return;
				}
				if (TextUtils.isEmpty(license_plate_number.getText())
						|| TextUtils.isEmpty(name.getText())
						|| TextUtils.isEmpty(nation.getText())
						|| TextUtils.isEmpty(passenger_number.getText())
						|| TextUtils.isEmpty(position.getText())
						|| TextUtils.isEmpty(price.getText())
						|| TextUtils.isEmpty(vehicle_age.getText())
						|| TextUtils.isEmpty(vehicle_model.getText())) {
					UIUtils.showToastSafe("数据不能为空");
					return;
				}

				// 价格说明
				driverbean.setDescription(description.getText().toString()
						.trim());
				// 车牌号
				driverbean.setLicense_plate_number(license_plate_number
						.getText().toString().trim());
				// 姓名
				driverbean.setName(name.getText().toString().trim());
				// 民族
				driverbean.setNation(nation.getText().toString().trim());
				// 可坐人数
				driverbean.setPassenger_number(passenger_number.getText()
						.toString().trim());
				// 所在地
				driverbean.setPosition(position.getText().toString().trim());
				// 价格
				driverbean.setPrice(price.getText().toString().trim());
				// 车型
				driverbean.setVehicle(mCountries2[vehicle
						.getSelectedItemPosition()]);
				// 驾龄
				driverbean.setVehicle_age(vehicle_age.getText().toString()
						.trim());
				// 车型号
				driverbean.setVehicle_model(vehicle_model.getText().toString()
						.trim());
				// 司机id
				driverbean.setdriver_id(Myconstants.driver_id);
				System.out.println(driverbean.toString());
				// 发送json格式字段，发送成功
				HttpUtils http = new HttpUtils();
				RequestParams params = new RequestParams();
				params.addBodyParameter("driver_id", driverbean.getdriver_id());
				params.addBodyParameter("license_plate_number",
						driverbean.getLicense_plate_number());
				params.addBodyParameter("name", driverbean.getName());
				params.addBodyParameter("passenger_number",
						driverbean.getPassenger_number());
				params.addBodyParameter("price", driverbean.getPrice());
				params.addBodyParameter("nation", driverbean.getNation());
				params.addBodyParameter("main_cities", driverbean.getPosition());
				params.addBodyParameter("description",
						driverbean.getDescription());
				params.addBodyParameter("driving_years",
						driverbean.getVehicle_age());
				params.addBodyParameter("vehicle_model_id",
						driverbean.getVehicle());
				params.addBodyParameter("vehicle_model",
						driverbean.getVehicle_model());
				http.send(HttpMethod.POST, SIJIZILIAOURL, params,
						new RequestCallBack<String>() {

							@Override
							public void onStart() {
								UIUtils.showProgresssafe("正在提交~！", mactivity);
								super.onStart();
							}

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								// TODO Auto-generated method stub
								UIUtils.stopProgresssafe();
								String results = responseInfo.result.toString()
										.trim();
								Gson gson = new Gson();
								Register23response fromJson = gson.fromJson(
                                        results, Register23response.class);
								// UIUtils.showToastSafe(fromJson.getStatus());
								if (fromJson.getStatus().equals("1")) {
									UIUtils.showToastSafe(fromJson.getResult()
											.getcode_msg());
									IntentUtils.startActivity(mactivity,
                                            Register3.class);
									finish();
								} else {
									UIUtils.showToastSafe(fromJson.getResult()
											.getcode_msg());
								}

							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								UIUtils.stopProgresssafe();

								if (!StringUtils.isDigit(driverbean
                                        .getPassenger_number())) {
									UIUtils.showToastSafe("可坐人数只能填数字");
									return;
								}
								if (!StringUtils.isDigit(driverbean.getPrice())) {

									UIUtils.showToastSafe("价格只能填数字");
									return;
								}
								if (!StringUtils.isDigit(driverbean.getVehicle_age())) {

									UIUtils.showToastSafe("驾龄只能填数字");
									return;
								}
								UIUtils.showToastSafe("提交失败,请检查网络,错误代码201");

							}
						});

			}
		});
	}
}
