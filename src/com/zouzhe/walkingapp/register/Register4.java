package com.zouzhe.walkingapp.register;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.zouzhe.walkingapp.Mainui_Activity;
import com.zouzhe.walkingapp.R;

public class Register4 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register4_succeed);
		
	}
	
	public void submit(View view){
		//IntentUtils.startActivityForDelayAndFinish(this, LoginActivity.class, 500);
		Intent intent = new Intent(this,Mainui_Activity.class);
		intent.putExtra("register", "1");
		startActivity(intent);
		finish();
	}
	
}
