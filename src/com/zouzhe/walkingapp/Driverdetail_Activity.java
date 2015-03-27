package com.zouzhe.walkingapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.zouzhe.walkingapp.javabean.Cartyperesponse;
import com.zouzhe.walkingapp.javabean.Driverbeanrespose;
import com.zouzhe.walkingapp.javabean.Driverdetialbean;
import com.zouzhe.walkingapp.javabean.Register23response;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.utils.Logger;
import com.zouzhe.walkingapp.utils.SPUtils;
import com.zouzhe.walkingapp.utils.UIUtils;

public class Driverdetail_Activity extends Activity implements
		OnClickListener {

	private RelativeLayout rl_driverdetial_name;
	private TextView tv_driverdetial_name;
	private RelativeLayout rl_driverdetial_introduction;
	private TextView tv_driverdetial_introduction;
	private RelativeLayout rl_driverdetial_driver_years;
	private TextView tv_driverdetial_driver_years;
	private RelativeLayout rl_driverdetial_nation;
	private TextView tv_driverdetial_nation;
	private RelativeLayout rl_driverdetial_vehicle_mode_id;
	private TextView tv_driverdetial_vehicle_mode_id;
	private RelativeLayout rl_driverdetial_vehicle_model;
	private TextView tv_driverdetial_vehicle_model;
	private RelativeLayout rl_driverdetial_price;
	private TextView tv_driverdetial_price;
	private RelativeLayout rl_driverdetial_vehicle_years;
	private TextView tv_driverdetial_vehicle_years;
	private RelativeLayout rl_driverdetial_passenger_number;
	private TextView tv_driverdetial_passenger_number;
	private RelativeLayout rl_driverdetial_bagnumber;
	private TextView tv_driverdetial_bagnumber;
	private RelativeLayout rl_driverdetial_license_plate_number;
	private TextView tv_driverdetial_license_plate_number;
	private RelativeLayout rl_driverdetial_avatar;
	private ImageView iv_driverdetial_avatar;
	private RelativeLayout rl_driverdetial_vehicle_photo_url;
	private ImageView iv_driverdetial_vehicle_photo_url;
	private RelativeLayout rl_driverdetial_vehicle_driving;
	private ImageView iv_driverdetial_vehicle_driving;
	private RelativeLayout rl_driverdetial_driver_license;
	private ImageView iv_driverdetial_driver_license;
	private RelativeLayout rl_driverdetial_operating_certificate;
	private ImageView iv_driverdetial_operating_certificate;
	private ProgressBar pb;
	private ScrollView sv;
	private String SUMITSIJIZILIAOURL = Myconstants.SUMITSIJIZILIAO;
	private String RECIVERZILIAO = Myconstants.RECIVERZILIAO;
	private Activity mActivity;
	/*
	 * private static final String[] mCountries = { "大巴", "中巴", "越野", "商务",
	 * "小车","不限车型 " }; private static final String[] mCountries2 = {
	 * "54781c55f083f93ecdd056d1", "54781c45f083f93ecdd056d0",
	 * "54781c33f083f93ecdd056cf", "54781c12f083f93ecdd056ce",
	 * "54781becf083f93ecdd056cd" ,"54b4f6296909ad52466667dd" };
	 */
	private String[] mCountries;
	private String[] mCountries2;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Driverdetialbean driverdetialbean = (Driverdetialbean) msg.obj;
				tv_driverdetial_main_cities.setText(driverdetialbean
						.getMain_cities());
				tv_driverdetial_name.setText(driverdetialbean.getName());

				tv_driverdetial_introduction.setText(driverdetialbean
						.getintroduction());
				tv_driverdetial_nation.setText(driverdetialbean.getNation());

				tv_driverdetial_vehicle_mode_id.setText(driverdetialbean
						.getVehicle_mode_id());
				tv_driverdetial_vehicle_model.setText(driverdetialbean
						.getVehicle_model());

				tv_driverdetial_price.setText(driverdetialbean.getPrice());
				tv_driverdetial_vehicle_years.setText(driverdetialbean
						.getVehicle_years());

				tv_driverdetial_bagnumber.setText(driverdetialbean
						.getbag_number());
				tv_driverdetial_license_plate_number.setText(driverdetialbean
						.getLicense_plate_number());

				tv_driverdetial_passenger_number.setText(driverdetialbean
						.getPassenger_number());
				tv_driverdetial_driver_years.setText(driverdetialbean
						.getDriver_years());
				BitmapUtils bitmapUtils1 = new BitmapUtils(
						getApplicationContext());

				bitmapUtils1.display(iv_driverdetial_avatar,
						driverdetialbean.getAvatar());

				BitmapUtils bitmapUtils2 = new BitmapUtils(mActivity);

				bitmapUtils2.display(iv_driverdetial_driver_license,
						driverdetialbean.getDriver_license());

				BitmapUtils bitmapUtils3 = new BitmapUtils(mActivity);
				bitmapUtils3.display(iv_driverdetial_operating_certificate,
						driverdetialbean.getOperating_certificate());

				BitmapUtils bitmapUtils4 = new BitmapUtils(mActivity);
				bitmapUtils4.display(iv_driverdetial_vehicle_driving,
						driverdetialbean.getVehicle_driving());

				BitmapUtils bitmapUtils5 = new BitmapUtils(mActivity);
				bitmapUtils5.display(iv_driverdetial_vehicle_photo_url,
						driverdetialbean.getvehicle_photo_url());

				pb.setVisibility(View.GONE);
				sv.setVisibility(View.VISIBLE);
				break;
			case 2:
				pb.setVisibility(View.GONE);
				ll_driver_nofound.setVisibility(View.VISIBLE);
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
	private RelativeLayout rl_driverdetial_main_cities;
	private TextView tv_driverdetial_main_cities;
	private LinearLayout ll_driver_nofound;
	private Button but_driver_nofound;
	private HashMap<String, String> hashMap;
	private String tempid;
	private int tempi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_driverdetail_);
		mActivity = this;
		hashMap = new HashMap<String, String>();

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
		if (mCountries != null && mCountries2 != null) {
			String temp0name = mCountries[0];
			String temp0id = mCountries2[0];
			mCountries[0] = "请选择车型";
			mCountries2[0] = tempid;
			mCountries[tempi] = temp0name;
			mCountries2[tempi] = temp0id;
			for (int i = 0; i < mCountries.length; i++) {
				hashMap.put(mCountries[i], mCountries2[i]);
			}

		}

		initview();

		initdata();

	}

	private void initview() {

		pb = (ProgressBar) findViewById(R.id.pb_driverdetial);
		sv = (ScrollView) findViewById(R.id.sv_driverdetial);

		// 主要城市17
		rl_driverdetial_main_cities = (RelativeLayout) findViewById(R.id.rl_driverdetial_main_cities);
		tv_driverdetial_main_cities = (TextView) findViewById(R.id.tv_driverdetial_main_cities);
		rl_driverdetial_main_cities.setOnClickListener(this);

		// 姓名1
		rl_driverdetial_name = (RelativeLayout) findViewById(R.id.rl_driverdetial_name);
		tv_driverdetial_name = (TextView) findViewById(R.id.tv_driverdetial_name);
		rl_driverdetial_name.setOnClickListener(this);

		// 简介2
		rl_driverdetial_introduction = (RelativeLayout) findViewById(R.id.rl_driverdetial_introduction);
		tv_driverdetial_introduction = (TextView) findViewById(R.id.tv_driverdetial_introduction);
		rl_driverdetial_introduction.setOnClickListener(this);
		// 驾龄3
		rl_driverdetial_driver_years = (RelativeLayout) findViewById(R.id.rl_driverdetial_driver_years);
		tv_driverdetial_driver_years = (TextView) findViewById(R.id.tv_driverdetial_driver_years);
		rl_driverdetial_driver_years.setOnClickListener(this);
		// 民族4
		// TODO
		rl_driverdetial_nation = (RelativeLayout) findViewById(R.id.rl_driverdetial_nation);
		tv_driverdetial_nation = (TextView) findViewById(R.id.tv_driverdetial_nation);
		rl_driverdetial_nation.setOnClickListener(this);
		// 车型5
		rl_driverdetial_vehicle_mode_id = (RelativeLayout) findViewById(R.id.rl_driverdetial_vehicle_mode_id);
		tv_driverdetial_vehicle_mode_id = (TextView) findViewById(R.id.tv_driverdetial_vehicle_mode_id);
		rl_driverdetial_vehicle_mode_id.setOnClickListener(this);

		// 车型号6
		rl_driverdetial_vehicle_model = (RelativeLayout) findViewById(R.id.rl_driverdetial_vehicle_model);
		tv_driverdetial_vehicle_model = (TextView) findViewById(R.id.tv_driverdetial_vehicle_model);
		rl_driverdetial_vehicle_model.setOnClickListener(this);
		// 包车价7
		rl_driverdetial_price = (RelativeLayout) findViewById(R.id.rl_driverdetial_price);
		tv_driverdetial_price = (TextView) findViewById(R.id.tv_driverdetial_price);
		rl_driverdetial_price.setOnClickListener(this);
		// 车龄8
		rl_driverdetial_vehicle_years = (RelativeLayout) findViewById(R.id.rl_driverdetial_vehicle_years);
		tv_driverdetial_vehicle_years = (TextView) findViewById(R.id.tv_driverdetial_vehicle_years);
		rl_driverdetial_vehicle_years.setOnClickListener(this);
		// 建议乘客数9
		rl_driverdetial_passenger_number = (RelativeLayout) findViewById(R.id.rl_driverdetial_passenger_number);
		tv_driverdetial_passenger_number = (TextView) findViewById(R.id.tv_driverdetial_passenger_number);
		rl_driverdetial_passenger_number.setOnClickListener(this);
		// 建议行李数10
		rl_driverdetial_bagnumber = (RelativeLayout) findViewById(R.id.rl_driverdetial_bag_number);
		tv_driverdetial_bagnumber = (TextView) findViewById(R.id.tv_driverdetial_bag_number);
		rl_driverdetial_bagnumber.setOnClickListener(this);
		// 车牌号11
		rl_driverdetial_license_plate_number = (RelativeLayout) findViewById(R.id.rl_driverdetial_license_plate_number);
		tv_driverdetial_license_plate_number = (TextView) findViewById(R.id.tv_driverdetial_license_plate_number);
		rl_driverdetial_license_plate_number.setOnClickListener(this);
		// 司机头像12
		rl_driverdetial_avatar = (RelativeLayout) findViewById(R.id.rl_driverdetial_avatar);
		iv_driverdetial_avatar = (ImageView) findViewById(R.id.iv_imageview_avatar);
		rl_driverdetial_avatar.setOnClickListener(this);
		// 车辆图片13
		rl_driverdetial_vehicle_photo_url = (RelativeLayout) findViewById(R.id.rl_driverdetial_vehicle_photo_url);
		iv_driverdetial_vehicle_photo_url = (ImageView) findViewById(R.id.iv_imageview_vehicle_photo_url);
		rl_driverdetial_vehicle_photo_url.setOnClickListener(this);
		// 车辆驾驶证14
		rl_driverdetial_vehicle_driving = (RelativeLayout) findViewById(R.id.rl_driverdetial_vehicle_driving);
		iv_driverdetial_vehicle_driving = (ImageView) findViewById(R.id.iv_imageview_vehicle_driving);
		rl_driverdetial_vehicle_driving.setOnClickListener(this);
		// 驾驶证15
		rl_driverdetial_driver_license = (RelativeLayout) findViewById(R.id.rl_driverdetial_driver_license);
		iv_driverdetial_driver_license = (ImageView) findViewById(R.id.iv_imageview_driver_license);
		rl_driverdetial_driver_license.setOnClickListener(this);
		// 营运证16
		rl_driverdetial_operating_certificate = (RelativeLayout) findViewById(R.id.rl_driverdetial_operating_certificate);
		iv_driverdetial_operating_certificate = (ImageView) findViewById(R.id.iv_imageview_operating_certificate);
		rl_driverdetial_operating_certificate.setOnClickListener(this);
		// 重新连接

		ll_driver_nofound = (LinearLayout) findViewById(R.id.ll_driver_nofound);
		but_driver_nofound = (Button) findViewById(R.id.but_driver_nofound);
		but_driver_nofound.setOnClickListener(this);
	}

	private void initdata() {

		pb.setVisibility(View.VISIBLE);
		sv.setVisibility(View.GONE);
		HttpUtils httpUtils = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("driver_id", Myconstants.driver_id);
		// UIUtils.showToastSafe(Constants.driver_id);
		// TODO
		// System.out.println(Constants.SIJIZILIAOURLGET+Constants.DRIVERID);
		httpUtils.send(HttpMethod.POST, RECIVERZILIAO, params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						Gson gson = new Gson();
						Driverbeanrespose fromJson = gson.fromJson(
								responseInfo.result, Driverbeanrespose.class);

						Logger.e("司机详情得到的数据", fromJson.toString());

						if (fromJson.getStatus().equals("1")) {
							Message message = new Message();
							message.obj = fromJson.getResult();
							message.what = 1;
							SPUtils.putObject("drverbean", fromJson.getResult());
							handler.sendMessage(message);
						} else {
							// TODO

							Driverdetialbean driverbean = SPUtils
									.getObject("drverbean");
							if (driverbean == null) {
								handler.sendEmptyMessage(2);
							} else {
								Message message = new Message();
								message.obj = driverbean;
								message.what = 1;
								handler.sendMessage(message);
							}

						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						UIUtils.showToastSafe("请检查网络。。错误代码201");
						handler.sendEmptyMessage(2);
					}
				});
	}

	public void callback(View view) {
		finish();
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.rl_driverdetial_main_cities:
			intent = new Intent(this, Rename_Activity.class);
			startActivityForResult(intent, 17);
			break;
		case R.id.rl_driverdetial_name:
			intent = new Intent(this, Rename_Activity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.rl_driverdetial_introduction:
			intent = new Intent(this, Rename_Activity.class);
			startActivityForResult(intent, 2);
			break;
		case R.id.rl_driverdetial_driver_years:
			intent = new Intent(this, Renamenumber_Activity.class);
			startActivityForResult(intent, 3);
			break;
		case R.id.rl_driverdetial_nation:
			intent = new Intent(this, Rename_Activity.class);
			startActivityForResult(intent, 4);
			break;
		case R.id.rl_driverdetial_vehicle_mode_id:
			intent = new Intent(this, Renamevehicle_Activity.class);
			startActivityForResult(intent, 5);
			break;
		case R.id.rl_driverdetial_vehicle_model:
			intent = new Intent(this, Rename_Activity.class);
			startActivityForResult(intent, 6);
			break;
		case R.id.rl_driverdetial_price:
			intent = new Intent(this, Renamenumber_Activity.class);
			startActivityForResult(intent, 7);
			break;
		case R.id.rl_driverdetial_vehicle_years:
			intent = new Intent(this, Renamenumber_Activity.class);
			startActivityForResult(intent, 8);
			break;
		case R.id.rl_driverdetial_passenger_number:
			intent = new Intent(this, Renamenumber_Activity.class);
			startActivityForResult(intent, 9);
			break;
		case R.id.rl_driverdetial_bag_number:
			intent = new Intent(this, Renamenumber_Activity.class);
			startActivityForResult(intent, 10);
			break;
		case R.id.rl_driverdetial_license_plate_number:
			intent = new Intent(this, Rename_Activity.class);
			startActivityForResult(intent, 11);
			break;
		case R.id.rl_driverdetial_avatar:
			intent = new Intent(this, Resetdriverphoto_Activity.class);
			intent.putExtra("type", 12);
			startActivityForResult(intent, 12);
			break;
		case R.id.rl_driverdetial_vehicle_photo_url:
			intent = new Intent(this, Resetdriverphoto_Activity.class);
			intent.putExtra("type", 13);
			intent.putExtra("name", tv_driverdetial_vehicle_model.getText()
					.toString());
			startActivityForResult(intent, 13);
			break;
		case R.id.rl_driverdetial_vehicle_driving:
			intent = new Intent(this, Resetdriverphoto_Activity.class);
			intent.putExtra("type", 14);
			startActivityForResult(intent, 14);
			break;
		case R.id.rl_driverdetial_driver_license:
			intent = new Intent(this, Resetdriverphoto_Activity.class);
			intent.putExtra("type", 15);
			startActivityForResult(intent, 15);
			break;
		case R.id.rl_driverdetial_operating_certificate:
			intent = new Intent(this, Resetdriverphoto_Activity.class);
			intent.putExtra("type", 16);
			startActivityForResult(intent, 16);
			break;
		case R.id.but_driver_nofound:
			ll_driver_nofound.setVisibility(View.GONE);
			initdata();

			break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		String result1 = data.getStringExtra("some");
		if (TextUtils.isEmpty(result1)) {
			UIUtils.showToastSafe("更改为空");
			return;
		}

		switch (requestCode) {

		case 17:
			tv_driverdetial_main_cities.setText(result1);
			break;
		case 1:
			tv_driverdetial_name.setText(result1);
			break;

		case 2:
			tv_driverdetial_introduction.setText(result1);
			break;
		case 3:
			tv_driverdetial_driver_years.setText(result1);
			break;

		case 4:
			tv_driverdetial_nation.setText(result1);
			break;

		case 5:
			if(mCountries!=null){
				tv_driverdetial_vehicle_mode_id.setText(mCountries[(Integer
						.parseInt(result1))]);

			}else {
				UIUtils.showToastSafe("占时不能设置");
			}
			
			break;

		case 6:
			tv_driverdetial_vehicle_model.setText(result1);
			break;

		case 7:
			tv_driverdetial_price.setText(result1);
			break;

		case 8:
			tv_driverdetial_vehicle_years.setText(result1);
			break;

		case 9:
			tv_driverdetial_passenger_number.setText(result1);
			break;

		case 10:
			tv_driverdetial_bagnumber.setText(result1);
			break;

		case 11:
			tv_driverdetial_license_plate_number.setText(result1);
			break;
		case 12:
			new BitmapUtils(this).display(iv_driverdetial_avatar, result1);
			break;
		case 13:
			new BitmapUtils(this).display(iv_driverdetial_vehicle_photo_url,
					result1);
			break;
		// TODO
		case 14:
			new BitmapUtils(this).display(iv_driverdetial_vehicle_driving,
					result1);
			break;
		case 15:
			new BitmapUtils(this).display(iv_driverdetial_driver_license,
					result1);
			break;
		case 16:
			new BitmapUtils(this).display(
					iv_driverdetial_operating_certificate, result1);
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void savetointernet(View view) {

		HttpUtils httpUtils = new HttpUtils();

		RequestParams params = new RequestParams();
		// 13
		params.addBodyParameter("driver_id", Myconstants.driver_id);
		// 12
		params.addBodyParameter("license_plate_number",
				tv_driverdetial_license_plate_number.getText().toString());

		// 11
		params.addBodyParameter("name", tv_driverdetial_name.getText()
				.toString());
		// 10
		params.addBodyParameter("passenger_number",
				tv_driverdetial_passenger_number.getText().toString());

		// 9
		params.addBodyParameter("vehicle_model", tv_driverdetial_vehicle_model
				.getText().toString());
		// 8
		params.addBodyParameter("price", tv_driverdetial_price.getText()
				.toString());

		// 7
		params.addBodyParameter("nation", tv_driverdetial_nation.getText()
				.toString());
		// 6
		params.addBodyParameter("remark", tv_driverdetial_introduction
				.getText().toString());

		// 5
		params.addBodyParameter("driving_years", tv_driverdetial_driver_years
				.getText().toString());
		// 4
		params.addBodyParameter("vehicle_model_id",
				hashMap.get(tv_driverdetial_vehicle_mode_id.getText()));

		// 3
		params.addBodyParameter("luggage", tv_driverdetial_bagnumber.getText()
				.toString());
		// 2
		params.addBodyParameter("vehicle_age", tv_driverdetial_vehicle_years
				.getText().toString());

		// 1
		params.addBodyParameter("main_cities", tv_driverdetial_main_cities
				.getText().toString());

		httpUtils.send(HttpMethod.POST, SUMITSIJIZILIAOURL, params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						UIUtils.showProgresssafe("正在上传资料。。。",
								Driverdetail_Activity.this);
						super.onStart();
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						UIUtils.stopProgresssafe();
						String results = responseInfo.result.toString().trim();
						Gson gson = new Gson();
						Register23response fromJson = gson.fromJson(results,
								Register23response.class);
						// UIUtils.showToastSafe(fromJson.getStatus());
						if (fromJson.getStatus().equals("1")) {
							UIUtils.showToastSafe(fromJson.getResult()
									.getcode_msg());
						} else {
							UIUtils.showToastSafe(fromJson.getResult()
									.getcode_msg());
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						UIUtils.stopProgresssafe();
						UIUtils.showToastSafe("请检查网络，错误代码201");
					}
				});
	}
}
