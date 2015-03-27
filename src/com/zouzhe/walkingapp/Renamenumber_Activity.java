package com.zouzhe.walkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.zouzhe.walkingapp.utils.StringUtils;
import com.zouzhe.walkingapp.utils.UIUtils;

public class Renamenumber_Activity extends ActionBarActivity {

	private int requestcode;
	private Intent intent;
	private EditText ed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_renamenumber_);
		intent = getIntent();
		ed = (EditText) findViewById(R.id.ed_rename);
		requestcode = intent.getIntExtra("type", 0);

	}

	public void back(View view) {

		String k = ed.getText().toString().trim();
		if(!StringUtils.isDigit(k)){
			UIUtils.showToastSafe("请输入数字");
			return;
		}
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
