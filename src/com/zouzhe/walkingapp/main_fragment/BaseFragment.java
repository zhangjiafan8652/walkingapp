package com.zouzhe.walkingapp.main_fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class BaseFragment  {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private View rootview;
	public Activity mactivity;
	public Context mcontext;

	public BaseFragment(Activity activity) {
		mactivity = activity;
		mcontext = activity;
		rootview=initview();

	}

	

	public abstract View initview();

	public abstract void initdata();

	public View getRootview() {
		return rootview;
	}

}
