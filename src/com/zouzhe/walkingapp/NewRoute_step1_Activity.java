package com.zouzhe.walkingapp;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.zouzhe.walkingapp.myconstants.Myconstants;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

public class NewRoute_step1_Activity extends ActionBarActivity {

	private EditText et_newroutestep1_chufadi;
	private ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_route_step1_);
		initview();
	}

	private void initview() {
		et_newroutestep1_chufadi = (EditText) findViewById(R.id.et_newroutestep1_chufadi);
		listview = (ListView) findViewById(R.id.lv_myroute_listview);
		
		et_newroutestep1_chufadi.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				HttpUtils httpUtils = new HttpUtils();
				if(s!=null){
					RequestParams params=new RequestParams();
					params.addBodyParameter("str", s+"");
					httpUtils.send(HttpMethod.POST, Myconstants.location, params,new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							System.out.println("我这边在返回地点噢"+arg0.result);
						}
					});
				}
			}
		});
		
	}

}
