package com.zouzhe.walkingapp.main_fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.gson.Gson;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.squareup.timessquare.CalendarPickerView;
import com.zouzhe.walkingapp.R;
import com.zouzhe.walkingapp.javabean.Drawouttimeresponse;
import com.zouzhe.walkingapp.javabean.Register23response;
import com.zouzhe.walkingapp.javabean.Schedulebean;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.utils.Logger;
import com.zouzhe.walkingapp.utils.UIUtils;

/**
 * Created by Administrator on 2015/3/2.
 */
public class MySchedule_fragment extends BaseFragment implements OnClickListener {

	public MySchedule_fragment(Activity activity) {
		super(activity);
	}

	private CalendarPickerView calendar;
	private Button but_save;
	private LinkedList<Date> linkedList2;
	private String SAVESCHURL = Myconstants.SAVESCHURL;
	private String DATEURL = Myconstants.DATE;
	private static int time = 0;
	private static ProgressDialog progressDlg;
	ArrayList<Date> arrayList = null;
	private Calendar nextYear;
	private DbUtils dbUtils;
	private HashMap<String, String> select;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 101:
				//updateSch();
				UIUtils.showToastSafe("更新日期档期成功");
				UIUtils.stopProgresssafe();
				break;

			default:
				break;
			}
		}

	};

	@Override
	public View initview() {
		// View view=View.inflate(mcontext, R.layout.myschedule_fragment,null);
		View view = View.inflate(mcontext, R.layout.drawouttime, null);
		but_save = (Button) view.findViewById(R.id.but_save);
		but_save.setOnClickListener(this);
		nextYear = Calendar.getInstance();
		nextYear.add(Calendar.YEAR, 1);
		final Calendar lastYear = Calendar.getInstance();
		lastYear.add(Calendar.YEAR, -1);
		calendar = (CalendarPickerView) view.findViewById(R.id.calendar_view);
		Calendar today = Calendar.getInstance();
		ArrayList<Date> dates = new ArrayList<Date>();
		for (int i = 0; i < 1; i++) {
			today.add(Calendar.DAY_OF_MONTH, 0);
			dates.add(today.getTime());
		}
		calendar.init(new Date(), nextYear.getTime()) //
				.inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
				.withSelectedDates(dates);
		select = new HashMap<String, String>();
		calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onDateUnselected(Date date) {
				// TODO Auto-generated method stub
				UIUtils.showToastSafe(selecDatetformat(date).substring(6, 8)
						+ "号不可以出车");

				if (select.containsKey(selecDatetformat(date))) {
					select.remove(selecDatetformat(date));
					select.put(selecDatetformat(date), "2");
				} else {
					select.put(selecDatetformat(date), "2");
				}

			}

			@SuppressWarnings("deprecation")
			@Override
			public void onDateSelected(Date date) {
				// TODO Auto-generated method stub
				UIUtils.showToastSafe(selecDatetformat(date).substring(6, 8)
						+ "号可以出车");
				if (select.containsKey(selecDatetformat(date))) {
					select.remove(selecDatetformat(date));
					select.put(selecDatetformat(date), "1");
				} else {
					select.put(selecDatetformat(date), "1");
				}
			}
		});

		return view;
	}

	@Override
	public void initdata() {
		
		System.out.println("我来了~！~！~！~！！~！~");
		// Schedulebean entity = dbUtils.findById(Schedulebean.class,
		// parent.getId());
		List<Schedulebean> Schedulebeanlists = null;
		try {

			dbUtils = DbUtils.create(mcontext);
			Schedulebeanlists = dbUtils.findAll(Selector.from(
					Schedulebean.class).where("driver_id", "=",
					Myconstants.driver_id));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (Schedulebeanlists != null) {
			Logger.e("测试档期", Schedulebeanlists.size() + "");
		} else {
			Logger.e("为空哦", "schedule为空");
		}
		if (time == 0) {
			// UIUtils.showToastSafe("初始化日期数据");
			try {
				UIUtils.showProgresssafe("正在更新档期数据", mcontext);
				initdataFrominter();
				// runProgress();
			} catch (Exception e) {
				Logger.e("nihaode", e + "");
				e.printStackTrace();
			}

		} else {

		}
	}

	private void initdataFrominter() {
		new Thread() {
			public void run() {
				RequestParams params = new RequestParams();
				HttpUtils httpUtils = new HttpUtils();
				params.addBodyParameter("driver_id", Myconstants.driver_id);
				params.addBodyParameter("page_size", "180");
				params.addBodyParameter("page_number", "1");
				httpUtils.send(HttpRequest.HttpMethod.POST, DATEURL, params,
						new RequestCallBack<String>() {

							public void onStart() {

							};

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {

								String results = responseInfo.result.toString()
										.trim();
								Gson gson = new Gson();
								Drawouttimeresponse fromJson = gson.fromJson(
										results, Drawouttimeresponse.class);

								if (fromJson.getStatus().equals("1")) {
									LinkedList<Schedulebean> Schedulebeanlists = fromJson
											.getResult().getSchedule_list();

									savedata(Schedulebeanlists);
									System.out.println("你懂的" + results);
									LinkedList<Date> Okdates = findOkdates(Schedulebeanlists);
									time = 1;
									// handler.sendEmptyMessage(101);
									updateSch(Okdates);	
									
								} else {
									UIUtils.showToastSafe("请到个人资料中设置出发地");
									UIUtils.stopProgresssafe();
								}

							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								UIUtils.stopProgresssafe();
								UIUtils.showToastSafe("请求档期数据失败,请检查网络");
							}
						});
			};
		}.start();
	}

	private Boolean savedata(LinkedList<Schedulebean> schedulebeanlists) {

		for (int i = 0; i < schedulebeanlists.size(); i++) {
			try {
				dbUtils.save(schedulebeanlists.get(i));
			} catch (DbException e) {
				e.printStackTrace();
			}

		}

		return true;
	}

	private LinkedList<Date> findOkdates(
			LinkedList<Schedulebean> schedulebeanlists) {
		LinkedList<Schedulebean> linkedList = new LinkedList<Schedulebean>();
		for (int j = 0; j < schedulebeanlists.size(); j++) {
			//System.out.println(findallSchedule.get(j).toString());
			if (schedulebeanlists.get(j).getSchedule_status().equals("1")
					&& (Integer.parseInt(schedulebeanlists.get(j).getDate()) > systimeformat())) {
				linkedList.add(schedulebeanlists.get(j));
			}
		}

		linkedList2 = new LinkedList<Date>();
		for (int k = 0; k < linkedList.size(); k++) {
			linkedList2.add(datefomat(linkedList.get(k).getDate()));
		}
		
		return linkedList2;
	}

	private void updateSch(LinkedList<Date> dates1) {
		Calendar today = Calendar.getInstance();
		ArrayList<Date> dates = new ArrayList<Date>();
		for (int i = 0; i < 1; i++) {
			today.add(Calendar.DAY_OF_MONTH, 0);
			dates.add(today.getTime());
		}
		calendar.init(new Date(), nextYear.getTime()) //
				.inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
				.withSelectedDates(dates);
		for (int k = 0; k < dates1.size(); k++) {
			Logger.e("选择的日期是" + dates1.get(k).getYear(), "月"
					+ dates1.get(k).getMonth() + "  "
					+ dates1.get(k).getDate() + "  "
					+ dates1.get(k).getTime());
			calendar.selectDate(dates1.get(k));
		}
		UIUtils.stopProgresssafe();
	}

	/**
	 * 将20140115-----1150015
	 * 
	 * @param k
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Date datefomat(String k) {
		String y = k.substring(0, 4);
		String m = k.substring(4, 6);
		String d = k.substring(6, 8);
		y = (Integer.parseInt(y) - 1900) + "";
		m = (Integer.parseInt(m) - 1) + "";
		String[] date = { y, m, d };
		Date date2 = new Date(Integer.parseInt(date[0]),
				Integer.parseInt(date[1]), Integer.parseInt(date[2]));
		return date2;
	}

	/**
	 * 获取当前系统的时间20150121
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int systimeformat() {
		Date date = new Date(System.currentTimeMillis());
		String y = (date.getYear() + 1900) + "";
		String m = (date.getMonth() + 1) + "";
		if (Integer.parseInt(m) < 10) {
			m = 0 + m;
		}
		String d = date.getDate() + "";
		if (Integer.parseInt(d) < 10) {
			d = 0 + d;
		}
		String date2 = y + m + d;
		return Integer.parseInt(date2);
	}

	/**
	 * 获取当前系统的时间20150121
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String selecDatetformat(Date date) {

		String y = (date.getYear() + 1900) + "";
		String m = (date.getMonth() + 1) + "";
		if (Integer.parseInt(m) < 10) {
			m = 0 + m;
		}
		String d = date.getDate() + "";
		if (Integer.parseInt(d) < 10) {
			d = 0 + d;
		}
		String date2 = y + m + d;
		return date2;
	}

	private void runProgress() {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				UIUtils.showProgresssafe("正在更新档期数据，请稍等一分钟~！", mcontext);

				try {
					sleep(20 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				UIUtils.stopProgresssafe();
			};
		}.start();
	}
	

	public void onClick(View v) {
		Schedulebean schedulebean;
		if(select==null){
			return;
		}
		LinkedList<Schedulebean> newscheList = new LinkedList<Schedulebean>();
		for (Map.Entry<String, String> entry : select.entrySet()) {
			/*
			 * System.out.println("key= " + entry.getKey() + " and value= " +
			 * entry.getValue());
			 */
			schedulebean = new Schedulebean();
			
			try {
				schedulebean = dbUtils.findFirst(Selector.from(
						Schedulebean.class).where("driver_id", "=",
						Myconstants.driver_id).and("date","=",entry.getKey()));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			//System.out.println(Constants.driver_id + "===============");
			if (schedulebean != null) {
				schedulebean.setSchedule_status(entry.getValue());
				newscheList.add(schedulebean);
			} 

		}

		select.clear();
		if (newscheList.size() > 0 && newscheList != null) {
			Gson gson = new Gson();
			String result = gson.toJson(newscheList);
			HttpUtils httpUtils = new HttpUtils();
			RequestParams params = new RequestParams();
			params.addBodyParameter("driver_id", Myconstants.driver_id);
			params.addBodyParameter("scheduleList", result);
			// System.out.println(result);
			httpUtils.send(HttpMethod.POST, SAVESCHURL, params,
					new RequestCallBack<String>() {
				@Override
				public void onStart() {
					UIUtils.showProgresssafe("正在更新档期。。请稍等", mcontext);
					super.onStart();
				}
						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							// UIUtils.showToastSafe("上传成功");
							Gson gson2 = new Gson();
							Register23response fromJson = gson2.fromJson(
									responseInfo.result,
									Register23response.class);
							String msg = fromJson.getResult().getcode_msg();
							//dialog(msg);
							initdataFrominter();
							// Logger.e("你好呀", message)
							//seachdaoTolist();
							//handler.sendEmptyMessage(101);
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							//UIUtils.stopProgresssafe();
							//dialog("网络出错~！");
							//UIUtils.showToastSafe("" + error);
							UIUtils.stopProgresssafe();
							dialog("网络出错~！");
						}
					});
		}

		Logger.e("map集合信息", select.toString());

	}

	protected void dialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
		builder.setMessage(msg);
		builder.setTitle("提示");
		builder.setPositiveButton("确定",
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
