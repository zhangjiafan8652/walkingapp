package com.zouzhe.walkingapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zouzhe.walkingapp.main_fragment.BaseFragment;
import com.zouzhe.walkingapp.main_fragment.MySchedule_fragment;
import com.zouzhe.walkingapp.main_fragment.NowRecord_fragment;
import com.zouzhe.walkingapp.main_fragment.OrderRecord_fragment;
import com.zouzhe.walkingapp.main_fragment.Persondetail_fragment;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.utils.Logger;
import com.zouzhe.walkingapp.utils.SPUtils;

public class Mainui_Activity extends Activity {

	TextView tv_mainui_title;
	TextView tv_mainui_message;
	FrameLayout fl_mainui_container;
	RadioButton rb_mainui_OrderRecord;
	RadioGroup rg_mainui_selectitem;
	RadioButton rb_mainui_persondetail;
	RadioButton rb_mainui_MySchedule;
	RadioButton rb_mainui_NowCarpooling;
	RadioButton rb_mainui_NowRecord;
	ViewPager vp_mainui_viewpage;
	private List<BaseFragment> fragments;
	private Activity mActivity;
	private MyViewpagerAdapter myViewpagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainui_);
		//Logger.e("傻瓜啊", "都不知道你在干吗4");
		Myconstants.driver_id = SPUtils.getSPstring("driver_id", "");
		
		System.out.println(Myconstants.driver_id+"司机id++++++++++++++");
		mActivity = this;
		initview();
		initdata();

	}

	private void initview() {
		tv_mainui_title = (TextView) findViewById(R.id.tv_mainui_titile);
		tv_mainui_message = (TextView) findViewById(R.id.tv_mainui_message);
		fl_mainui_container = (FrameLayout) findViewById(R.id.fl_main_ui_container);
		vp_mainui_viewpage = (ViewPager) findViewById(R.id.vp_mainui_viewpage);
		// 选项卡集合容器
		rg_mainui_selectitem = (RadioGroup) findViewById(R.id.rg_mainui_selectitem);
		// 主页面下面的选项卡
		rb_mainui_OrderRecord = (RadioButton) findViewById(R.id.rb_mainui_OrderRecord);
		rb_mainui_NowRecord = (RadioButton) findViewById(R.id.rb_mainui_NowRecord);
		// rb_mainui_NowCarpooling = (RadioButton)
		// findViewById(R.id.rb_mainui_NowCarpooling);
		rb_mainui_MySchedule = (RadioButton) findViewById(R.id.rb_mainui_MySchedule);
		rb_mainui_persondetail = (RadioButton) findViewById(R.id.rb_mainui_Persondetail);
	}

	private void initdata() {
		fragments = new ArrayList<BaseFragment>();
		fragments.add(new OrderRecord_fragment(mActivity));

		fragments.add(new NowRecord_fragment(mActivity));
		// fragments.add(new NowCarpooling_fragment());
		fragments.add(new Persondetail_fragment(mActivity));
		fragments.add(new MySchedule_fragment(mActivity));

		myViewpagerAdapter = new MyViewpagerAdapter();
		// 设置adapter
		vp_mainui_viewpage.setAdapter(myViewpagerAdapter);
		rg_mainui_selectitem.check(R.id.rb_mainui_OrderRecord);
		fragments.get(0).initdata();
		vp_mainui_viewpage
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageScrolled(int position,
							float positionOffset, int positionOffsetPixels) {

					}

					@Override
					public void onPageSelected(int position) {
						switch (position) {
						case 0:
							if (rg_mainui_selectitem.getCheckedRadioButtonId() == R.id.rb_mainui_OrderRecord) {
								return;
							}
							// UIUtils.showToastSafe("你选的是订单记录");
							rg_mainui_selectitem
									.check(R.id.rb_mainui_OrderRecord);
							// fragments.get(0).initdata();
							break;
						case 1:
							if (rg_mainui_selectitem.getCheckedRadioButtonId() == R.id.rb_mainui_NowRecord) {
								return;
							}
							// UIUtils.showToastSafe("你选的是现场成单");
							rg_mainui_selectitem
									.check(R.id.rb_mainui_NowRecord);
							// fragments.get(1).initdata();
							break;
						case 2:
							if (rg_mainui_selectitem.getCheckedRadioButtonId() == R.id.rb_mainui_Persondetail) {
								return;
							}
							// UIUtils.showToastSafe("你选的是个人中心");
							// fragments.get(2).initdata();
							rg_mainui_selectitem
									.check(R.id.rb_mainui_Persondetail);
							break;
						case 3:
							if (rg_mainui_selectitem.getCheckedRadioButtonId() == R.id.rb_mainui_MySchedule) {
								return;
							}
							// UIUtils.showToastSafe("你选的是我的日程");
							// fragments.get(3).initdata();
							rg_mainui_selectitem
									.check(R.id.rb_mainui_MySchedule);
							break;
						}
					}

					@Override
					public void onPageScrollStateChanged(int state) {
					}
				});

		rg_mainui_selectitem
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.rb_mainui_MySchedule:
							if (vp_mainui_viewpage.getCurrentItem() == 3) {
								return;
							}
							// UIUtils.showToastSafe("你选的是我的日程");
							vp_mainui_viewpage.setCurrentItem(3);
							setTitle(tv_mainui_title, "我的日程");
							 fragments.get(3).initdata();
							break;

						// * case R.id.rb_mainui_NowCarpooling:
						// * UIUtils.showToastSafe("你选的是发起拼车");
						// * vp_mainui_viewpage.setCurrentItem(1); break;

						case R.id.rb_mainui_NowRecord:
							if (vp_mainui_viewpage.getCurrentItem() == 1) {
								return;
							}
							// UIUtils.showToastSafe("你选的是现场成单");
							vp_mainui_viewpage.setCurrentItem(1);
							 fragments.get(1).initdata();
							setTitle(tv_mainui_title, "现场成单");
							break;
						case R.id.rb_mainui_OrderRecord:
							if (vp_mainui_viewpage.getCurrentItem() == 0) {
								return;
							}
							// UIUtils.showToastSafe("你选的是订单记录");
							vp_mainui_viewpage.setCurrentItem(0);
							setTitle(tv_mainui_title, "订单记录");
							 fragments.get(0).initdata();
							break;
						case R.id.rb_mainui_Persondetail:
							// UIUtils.showToastSafe("你选的是个人中心");
							if (vp_mainui_viewpage.getCurrentItem() == 2) {
								return;
							}
							vp_mainui_viewpage.setCurrentItem(2);
							setTitle(tv_mainui_title, "个人中心");
							 fragments.get(2).initdata();
							break;
						}
					}
				});

	}

	class MyViewpagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragments.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			BaseFragment pager = (BaseFragment) fragments.get(position);
			View rootView = pager.getRootview();
			container.addView(rootView);
			// 初始化数据
			//pager.initdata();

			return rootView;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

	public void setTitle(TextView textview, String titlename) {
		if (textview != null) {
			textview.setText(titlename);
		}
	}

	@Override
	public void onBackPressed() {
		dialog();

	}

	protected void dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
		builder.setMessage("退出登录");
		builder.setTitle("提示");
		builder.setPositiveButton("确定",
				new android.content.DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						// SPUtils.putSPint("LOGINSTATE",
						// Myconstants.NEVERLOGIN);
						mActivity.finish();

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

}