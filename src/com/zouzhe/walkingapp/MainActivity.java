package com.zouzhe.walkingapp;

import java.util.ArrayList;
import java.util.List;

import com.zouzhe.walkingapp.main_fragment.BaseFragment;
import com.zouzhe.walkingapp.main_fragment.MySchedule_fragment;
import com.zouzhe.walkingapp.main_fragment.NowRecord_fragment;
import com.zouzhe.walkingapp.main_fragment.OrderRecord_fragment;
import com.zouzhe.walkingapp.main_fragment.Persondetail_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

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
	private String fragmentname;
	private List<BaseFragment> fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainui_);

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
		fragments.add(new OrderRecord_fragment(this));
		fragments.add(new NowRecord_fragment(this));
		// fragments.add(new NowCarpooling_fragment());
		fragments.add(new Persondetail_fragment(this));
		fragments.add(new MySchedule_fragment(this));

		MyViewpagerAdapter myViewpagerAdapter = new MyViewpagerAdapter();
		vp_mainui_viewpage.setAdapter(myViewpagerAdapter);
		rg_mainui_selectitem.check(R.id.rb_mainui_OrderRecord);

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
							// UIUtils.showToastSafe("你选的是订单记录");
							rg_mainui_selectitem
									.check(R.id.rb_mainui_OrderRecord);
							break;
						case 1:
							// UIUtils.showToastSafe("你选的是现场成单");
							rg_mainui_selectitem
									.check(R.id.rb_mainui_NowRecord);
							break;
						case 2:
							// UIUtils.showToastSafe("你选的是个人中心");
							rg_mainui_selectitem
									.check(R.id.rb_mainui_Persondetail);
							break;
						case 3:
							// UIUtils.showToastSafe("你选的是我的日程");
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
							// UIUtils.showToastSafe("你选的是我的日程");
							vp_mainui_viewpage.setCurrentItem(3);
							setTitle(tv_mainui_title, "我的日程");
							break;
						/*
						 * case R.id.rb_mainui_NowCarpooling:
						 * UIUtils.showToastSafe("你选的是发起拼车");
						 * vp_mainui_viewpage.setCurrentItem(1); break;
						 */
						case R.id.rb_mainui_NowRecord:
							// UIUtils.showToastSafe("你选的是现场成单");
							vp_mainui_viewpage.setCurrentItem(1);
							setTitle(tv_mainui_title, "现场成单");
							break;
						case R.id.rb_mainui_OrderRecord:
							// UIUtils.showToastSafe("你选的是订单记录");
							vp_mainui_viewpage.setCurrentItem(0);
							setTitle(tv_mainui_title, "订单记录");
							break;
						case R.id.rb_mainui_Persondetail:
							// UIUtils.showToastSafe("你选的是个人中心");
							vp_mainui_viewpage.setCurrentItem(2);
							setTitle(tv_mainui_title, "个人中心");
							break;
						}
					}
				});

	}

	class MyViewpagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return fragments.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BaseFragment pager = (BaseFragment) fragments.get(position);
			View rootView = pager.getRootview();
			container.addView(rootView);

			// 初始化数据
			pager.initdata();
			return rootView;
		}

	}

	public void setTitle(TextView textview, String titlename) {
		if (textview != null) {
			textview.setText(titlename);
		}
	}

}
