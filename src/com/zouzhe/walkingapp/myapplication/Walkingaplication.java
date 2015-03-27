package com.zouzhe.walkingapp.myapplication;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Walkingaplication extends Application {

	// 获取到主线程的上下文
	private static Walkingaplication mContext = null;
	// 获取到主线程的handler
	private static Handler mMainThreadHandler = null;
	// 获取到主线程的looper
	private static Looper mMainThreadLooper = null;
	// 获取到主线程
	private static Thread mMainThead = null;
	// 获取到主线程的id
	private static int mMainTheadId;
	private static String type;
	// 友盟推送
//	private PushAgent mPushAgent;
	private Builder buil;
	private AlertDialog buile;
	private Button qiandan;
	private Button buqiang;
	private View view1;
	private TextView dialog_name;
	private TextView earnest_money;
	private TextView dialog_time;
	private TextView dialog_route;
	private int RUSHED=2;//抢到
	private int NORUSHED=1;//不抢
	private int ORTHERRUSH=3;//被抢
	//private WalkingDao walkingDao;
	//private Messagebean messagebean;
	private SharedPreferences sp;
	private Editor ed;
	private TextView title;
	private MediaPlayer player;
	private MediaPlayer mediaPlayer;
	/**
	 * 全局异常处理将异常保存到sdcard/crash中
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		//关闭有友盟错误统计
		//MobclickAgent.setCatchUncaughtExceptions(false);
		
		//
		//CrashHandler crashHandler = CrashHandler.getInstance();
		//crashHandler.init(getApplicationContext());
		 
		long nowtime=System.currentTimeMillis();
		this.mContext = this;
		this.mMainThreadHandler = new Handler();
		this.mMainThreadLooper = getMainLooper();
		this.mMainThead = Thread.currentThread();
		// android.os.Process.myUid() 获取到用户id
		// android.os.Process.myPid()获取到进程id
		// android.os.Process.myTid()获取到调用线程的id
		this.mMainTheadId = android.os.Process.myTid();
		
	/*	mPushAgent = PushAgent.getInstance(this);
		walkingDao = new WalkingDao(mContext);
		//开启线程初始化全局变量的静态数据
		initdata();
		messagebean = new Messagebean();
		UmengMessageHandler messageHandler = new UmengMessageHandler() {
			
			@Override
			public Notification getNotification(Context arg0, UMessage msg) {
				messagebean.setMessagetitle(msg.title);
				messagebean.setMessagetext(msg.text);
				messagebean.setdriver_id(Constants.driver_id);
				walkingDao.messageAdd(messagebean);
				
				*//*Intent intent = new Intent(arg0,CopyOfMessagedetailActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);*//*
				
				return super.getNotification(arg0, msg);*/
			}
			
		/*	@Override
			public void dealWithCustomMessage(final Context context,
					final UMessage msg) {
				new Handler(getMainLooper()).post(new Runnable() {

					private Rushdaobean rushdaobean;

					@Override
					public void run() {
						int LOGINSTATE = SPUtils.getSPint("LOGINSTATE", Constants.NEVERLOGIN);
					*//*
						if(LOGINSTATE==Constants.NEVERLOGIN){
							return; 
						}
						*//*
						Constants.driver_id=SPUtils.getSPstring("driver_id", "-1");
						if(buil!=null){
							buile.dismiss();
						}
						
						singSong();
						
						rushdaobean = new Rushdaobean();
						for (Entry<String, String> entry : msg.extra.entrySet()) {
							
							String key = entry.getKey();
							String value = entry.getValue();
							if(key.equals("type")){
								type=value;
							}
							if (key.equals("order_no")) {
								rushdaobean.setOrder_no(value);
							} else if (key.equals("earnest_money")) {
								rushdaobean.setEarnest_money(value);
							} else if (key.equals("name")) {
								rushdaobean.setName(value);
							} else if (key.equals("mobile")) {
								rushdaobean.setMobile(value);
							} else if (key.equals("date_depart")) {
								rushdaobean.setDate_depart(value);

							} else if (key.equals("date_return")) {
								rushdaobean.setDate_return(value);
							} else if (key.equals("route")) {
								rushdaobean.setRoute(value);
							}else if (key.equals("spend_time")) {
								rushdaobean.setSpend_time(value);
							}

						}
						//初始化对话框的视图
						initDialogview();
						if(type.equals("1")){
							OerderDialog(rushdaobean);
						}else if(type.equals("0")){
							orderDialog(rushdaobean);
						}
						
					}
					
					
				});
			}
		};
		mPushAgent.setMessageHandler(messageHandler);

	}*/
/*

	*/
/*
	 * 初始化全局变量需要用到的数据
	 *//*

	private void initdata() {
		new Thread(){
			public void run() {
				Constants.ordercount=walkingDao.orderFindcountbyid(Constants.driver_id)+"";
				Constants.rushordercount=walkingDao.rushfindAllcount()+"";
				Constants.messagecount=walkingDao.messagefindAllcount()+"";
			};
		}.start();
	}
*/

	public static Walkingaplication getApplication() {
		return mContext;
	}

	public static Handler getMainThreadHandler() {
		return mMainThreadHandler;
	}

	public static Looper getMainThreadLooper() {
		return mMainThreadLooper;
	}

	public static Thread getMainThread() {
		return mMainThead;
	}

	public static int getMainThreadId() {
		return mMainTheadId;
	}
/*
	public void OerderDialog(final Rushdaobean rushdaobean) {

		dialog_name.setText(rushdaobean.getName());
		String payment = "已付定金" + rushdaobean.getEarnest_money() + "元";
		earnest_money.setText(payment);
		dialog_route.setText(rushdaobean.getRoute());
	*//*	long oderde=Long.parseLong(rushdaobean.getDate_depart());
		*//*

	private void initDialogview() {
		buil = new Builder(this);
		buile = buil.create();
		view1 = View.inflate(this, R.layout.alertdialog, null);
		title = (TextView) view1.findViewById(R.id.tv_walkapplication_title);

		qiandan = (Button) view1.findViewById(R.id.bt_qiangdan);
		buqiang = (Button) view1.findViewById(R.id.bt_buqiang);
		dialog_name = (TextView) view1.findViewById(R.id.tv_dialog_name);
		earnest_money = (TextView) view1
				.findViewById(R.id.tv_dialog_earnest_money);
		dialog_route = (TextView) view1.findViewById(R.id.tv_dialog_route);
		dialog_time = (TextView) view1.findViewById(R.id.tv_dialog_time);

	}
*//*
        Logger.e("oderde",oderde+"" );
	
		long oderre=Long.parseLong(rushdaobean.getDate_return());
		
		long cha=oderre-oderde;
		Logger.e("oderre", oderre+"");
		//String[] day=DateUtils.timeFormatMore(Long.parseLong(order.getDate_depart())-(Long.parseLong(order.getDate_return())));
		String timeFormatms=DateUtils.timeFormatms(cha);
		String Date_depart = DateUtils.formatTime(
				Long.parseLong(rushdaobean.getDate_depart()), "yyyy.MM.dd");
		String Date_return = DateUtils.formatTime(
				Long.parseLong(rushdaobean.getDate_return()), "yyyy.MM.dd");*//*
		String time = rushdaobean.getSpend_time() + "天" + "   " + rushdaobean.getDate_depart() + "-" + rushdaobean.getDate_return();
		dialog_time.setText(time);

		qiandan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("抢单成功");
				RequestParams Params = new RequestParams();
				Params.addBodyParameter("driver_id", Constants.driver_id);
				Params.addBodyParameter("order_no",rushdaobean.getOrder_no());
				HttpUtils http = new HttpUtils();
				http.send(HttpMethod.POST, "http://172.24.30.1/zouzhe/servlet/LoginServlet", Params, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						Logger.i("抢单成功", responseInfo.result.toString());
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Logger.i("错误信息", msg);
						rushdaobean.setStatus("ORTHERRUSH");
						rushdaobean.setdriver_id(Constants.driver_id);
						boolean k=walkingDao.rushAdd(rushdaobean);
						if(k){
							System.out.println("rush++++++++插入成功");
						}
					}
				});
				buile.dismiss();
			}
		});
		
		buqiang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				rushdaobean.setStatus("NORUSHED");
				rushdaobean.setdriver_id(Constants.driver_id);
				boolean k=walkingDao.rushAdd(rushdaobean);
				if(k){
					System.out.println("rush++++++++插入成功");
				}
				buile.dismiss();
			}
		});

		buile.setView(view1);
		buile.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		buile.setCanceledOnTouchOutside(false);
		buile.show();
	}
	

	public void orderDialog(final Rushdaobean rushdaobean) {

		title.setText("新定单");
		dialog_name.setText(rushdaobean.getName());
		String payment = "已付定金" + rushdaobean.getEarnest_money() + "元";
		earnest_money.setText(payment);
		dialog_route.setText(rushdaobean.getRoute());
	*//*	long oderde=Long.parseLong(rushdaobean.getDate_depart());
		Logger.e("oderde",oderde+"" );
	
		long oderre=Long.parseLong(rushdaobean.getDate_return());
		
		long cha=oderre-oderde;
		Logger.e("oderre", oderre+"");
		//String[] day=DateUtils.timeFormatMore(Long.parseLong(order.getDate_depart())-(Long.parseLong(order.getDate_return())));
		String timeFormatms=DateUtils.timeFormatms(cha);
		String Date_depart = DateUtils.formatTime(
				Long.parseLong(rushdaobean.getDate_depart()), "yyyy.MM.dd");
		String Date_return = DateUtils.formatTime(
				Long.parseLong(rushdaobean.getDate_return()), "yyyy.MM.dd");*//*
		String time = rushdaobean.getSpend_time() + "天" + "   " + rushdaobean.getDate_depart() + "-" + rushdaobean.getDate_return();
		
		dialog_time.setText(time);
		qiandan.setText("稍后再说");
		buqiang.setText("拨打电话");
		qiandan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				buile.dismiss();
			}
		});
		
		buqiang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				  //用intent启动拨打电话  
				String telphone="tel:"+rushdaobean.getMobile();
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse(telphone));  
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
                mContext.startActivity(intent); 
				buile.dismiss();
			}
		});

		buile.setView(view1);
		buile.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		buile.setCanceledOnTouchOutside(false);
		buile.show();
	}

	
	public void singSong(){
		
		if(mediaPlayer!=null){
			mediaPlayer.stop();
			mediaPlayer=null;
		}
		
		mediaPlayer = MediaPlayer.create(this, R.raw.tishi);
		try {
			mediaPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mediaPlayer.start();
		
		
	}
	
	public void stopSong(){
		if(null!=player){
			player.stop();
		}
	}
	
	*/

}
