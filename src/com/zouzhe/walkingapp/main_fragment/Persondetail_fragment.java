package com.zouzhe.walkingapp.main_fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zouzhe.walkingapp.Driverdetail_Activity;
import com.zouzhe.walkingapp.R;
import com.zouzhe.walkingapp.Route_Activity;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.pulltozoomview.PullToZoomScrollView;
import com.zouzhe.walkingapp.pulltozoomview.PullToZoomScrollViewEx;
import com.zouzhe.walkingapp.utils.IntentUtils;
import com.zouzhe.walkingapp.utils.SPUtils;

/**
 * Created by Administrator on 2015/3/2.
 */
public class Persondetail_fragment extends BaseFragment {
	private PullToZoomScrollViewEx scrollView;
	View view;

	public Persondetail_fragment(Activity activity) {
		super(activity);
	}

	@Override
	public View initview() {

		view = View.inflate(mcontext, R.layout.persondetail_fragment, null);
		scrollView = (PullToZoomScrollViewEx) view
				.findViewById(R.id.scroll_view);
		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		mactivity.getWindowManager().getDefaultDisplay()
				.getMetrics(localDisplayMetrics);
		int mScreenHeight = localDisplayMetrics.heightPixels;
		int mScreenWidth = localDisplayMetrics.widthPixels;
		LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(
				mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
		scrollView.setHeaderLayoutParams(localObject);
		// loadViewForCode();
		View zoomView = scrollView.getZoomView();
		View headerView = scrollView.getHeaderView();
		LinearLayout ll_persondetail_route = (LinearLayout) scrollView
				.findViewById(R.id.ll_persondetail_route);
		// 我的线路
		ll_persondetail_route.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentUtils.startActivity(mactivity, Route_Activity.class);
			}
		});
		Button but_persondetail_logout = (Button) scrollView
				.findViewById(R.id.but_persondetail_logout);
		// 退出登录
		but_persondetail_logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog();
			}
		});
		LinearLayout ll_persondetail_mydata = (LinearLayout) scrollView.findViewById(R.id.ll_persondetail_mydata);
		ll_persondetail_mydata.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivity(mactivity, Driverdetail_Activity.class);
			}
		});
		
		return view;

	}

	@Override
	public void initdata() {

	}

	protected void dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mactivity);
		builder.setMessage("退出登录");
		builder.setTitle("提示");
		builder.setPositiveButton("确定",
				new android.content.DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						SPUtils.putSPint("LOGINSTATE", Myconstants.NEVERLOGIN);
						mactivity.finish();

					}
				});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

		builder.create().show();
	}

	private void loadViewForCode() {
		PullToZoomScrollView scrollView = (PullToZoomScrollView) view
				.findViewById(R.id.scroll_view);
		View headView = LayoutInflater.from(mcontext).inflate(
				R.layout.profile_head_view, null, false);
		View zoomView = LayoutInflater.from(mcontext).inflate(
				R.layout.profile_zoom_view, null, false);
		View contentView = LayoutInflater.from(mcontext).inflate(
				R.layout.profile_content_view, null, false);

	}
}
