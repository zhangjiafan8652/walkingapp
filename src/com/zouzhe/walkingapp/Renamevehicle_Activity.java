package com.zouzhe.walkingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.zouzhe.walkingapp.javabean.Cartyperesponse;
import com.zouzhe.walkingapp.utils.SPUtils;
import com.zouzhe.walkingapp.utils.UIUtils;

public class Renamevehicle_Activity extends ActionBarActivity {

	/*
	 * private static final String[] mCountries = { "大巴", "中巴", "越野", "商务",
	 * "小车","不限车型 " }; private static final String[] mCountries2 = {
	 * "54781c55f083f93ecdd056d1", "54781c45f083f93ecdd056d0",
	 * "54781c12f083f93ecdd056ce",
	 * "54781c33f083f93ecdd056cf","54781becf083f93ecdd056cd"
	 * ,"54b4f6296909ad52466667dd" };
	 */
	private Activity mactivity;
	private Spinner vehicle;
	private Intent intent;
	private int requestcode;
	private String[] mCountries;
	private String[] mCountries2;
	private String tempid;
	private int tempi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renamevehicle_);
		mactivity = this;
		intent = getIntent();

		requestcode = intent.getIntExtra("type", 0);

		Cartyperesponse object = SPUtils.getObject("cartyperesponse");
		if (object == null) {
			UIUtils.showToastSafe("请重新登陆联网获取车型信息");
		} else {
			mCountries = new String[object.getResult().size()];
			mCountries2 = new String[object.getResult().size()];
			//mCountries[0] = "请选择车型";
			//mCountries2[0] = object.getResult().get(0).get_id();
			for (int i = 0; i < mCountries2.length; i++) {
				if (object.getResult().get(i).getName().equals("不限车型")) {
					tempid = object.getResult().get(i).get_id();
					tempi = i;
				}
				mCountries[i] = object.getResult().get(i).getName();
				mCountries2[i] = object.getResult().get(i).get_id();

				// System.out.println(mCountries);
			}
			
			String temp0name=mCountries[0];
			String temp0id=mCountries2[0];
			mCountries[0]="请选择车型";
			mCountries2[0]=tempid;
			mCountries[tempi]=temp0name;
			mCountries2[tempi]=temp0id;
			
			

			/*
			 * for (int i = 0; i < mCountries2.length; i++) {
			 * if(object.getResult().get(i).getName().equals("不限车型"))
			 * 
			 * mCountries[i]=object.getResult().get(i).getName();
			 * mCountries2[i]=object.getResult().get(i).get_id();
			 * //System.out.println(mCountries); }
			 */

		}

		vehicle = (Spinner) findViewById(R.id.sn_register2_vehicle);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mCountries);
		ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vehicle.setAdapter(ad);
	}

	public void back(View view) {

		String k = vehicle.getSelectedItemPosition() + "";
		if (!TextUtils.isEmpty(k)) {
			intent.putExtra("some", k);
			this.setResult(requestcode, intent);
			this.finish();
		} else {
			UIUtils.showToastSafe("输入为空");
			this.finish();
		}

	}

	
}
