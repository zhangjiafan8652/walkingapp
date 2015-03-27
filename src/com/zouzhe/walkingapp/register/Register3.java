package com.zouzhe.walkingapp.register;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.zouzhe.walkingapp.R;
import com.zouzhe.walkingapp.javabean.Recommendresponse;
import com.zouzhe.walkingapp.javabean.Register23response;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.utils.Logger;
import com.zouzhe.walkingapp.utils.UIUtils;

public class Register3 extends Activity implements OnClickListener {

	private Button but_sendtouxiang;
	private Button but_sendsijijiashizheng;
	private Button but_sendcarjiashizheng;
	private Button but_sendyingyunzheng;

	private File tempFile = new File(Environment.getExternalStorageDirectory(),
			getPhotoFileName());
	private File sdcardTempFile;
	private AlertDialog dialog;
	private HttpUtils httpUtils;
	private RequestParams params;
	private Context context;
	private String uploadHost = Myconstants.SUBMITTUPIANURL;
	private int id;
	private int timer = 0;
	private EditText ed_jieshaoren;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register3);
		
		//判断内存卡是否存在
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
           
        }
        else
        {
            dialog("没有sd卡，无法上传图片.点击跳往主界面",new dialogPositiveonclicklistener() {
				
				@Override
				public void positiveOnclik() {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getApplicationContext(), Register4.class);
					startActivity(intent);
					finish();
				}
			} );
        	//Toast.makeText(this, "没有SD卡", Toast.LENGTH_LONG).show();
        }
		
		sdcardTempFile = new File("/mnt/sdcard/", "tmp_pic_wo_"
				+ SystemClock.currentThreadTimeMillis() + ".jpg");
		context = this;
		httpUtils = new HttpUtils();
		timer = 0;
		
		inintview();
		
	}

	/**
	 * 大周日在写这东西。。真的好烦艹。。。
	 */
	private void inintview() {
		but_sendtouxiang = (Button) findViewById(R.id.but_sendtouxiang);
		but_sendsijijiashizheng = (Button) findViewById(R.id.but_sendsijijiashizheng);
		but_sendcarjiashizheng = (Button) findViewById(R.id.but_sendcarjiashizheng);
		but_sendyingyunzheng = (Button) findViewById(R.id.but_sendyingyunzheng);
		ed_jieshaoren = (EditText) findViewById(R.id.et_jieshaoren);
		but_sendtouxiang.setOnClickListener(this);
		but_sendsijijiashizheng.setOnClickListener(this);
		but_sendcarjiashizheng.setOnClickListener(this);
		but_sendyingyunzheng.setOnClickListener(this);

	}

	public void submit(View view) {
		// 点击上传。如果上传成功
		if (timer < 4) {
			UIUtils.showToastSafe("请完善资料");
		} else {
			RequestParams params = new RequestParams();
			HttpUtils http = new HttpUtils();
			String jieshaoren=ed_jieshaoren.getText().toString().trim();
			if(TextUtils.isEmpty(jieshaoren)){
				Intent intent = new Intent(getApplicationContext(), Register4.class);
				startActivity(intent);
				finish();
			}else{
				params.addBodyParameter("driver_id", Myconstants.driver_id);
				params.addBodyParameter("recommended", jieshaoren);
				
				http.send(HttpMethod.POST, Myconstants.JIESHAOREN, params, new RequestCallBack<String>() {

					@Override
					public void onStart() {
						UIUtils.showProgresssafe("正在提交~", context);
						super.onStart();
					}
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						UIUtils.stopProgresssafe();
						String results = responseInfo.result
								.toString().trim();
						Gson gson = new Gson();
						Recommendresponse fromJson = gson.fromJson(
                                results, Recommendresponse.class);
						if(fromJson.getStatus().equals("1")){
							UIUtils.showToastSafe(fromJson.getResult().getcode_msg());
							Intent intent = new Intent(getApplicationContext(), Register4.class);
							UIUtils.showToastSafe(fromJson.getResult().getcode_msg());
							startActivity(intent);
							finish();
						}else {
							UIUtils.showToastSafe(fromJson.getResult().getcode_msg());
						}
						
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						UIUtils.stopProgresssafe();
						UIUtils.showToastSafe("推荐人未提交成功,请检查网络。错误代码201");
						Intent intent = new Intent(getApplicationContext(), Register4.class);
						startActivity(intent);
						finish();
					}
					
				});
				
			}
			
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.but_sendtouxiang:
			params = new RequestParams();
			params.addBodyParameter("driver_id", Myconstants.driver_id);
			params.addBodyParameter("status", "1");
			params.addBodyParameter("avatar",
					new File(sdcardTempFile.getAbsolutePath()));
			id = v.getId();
			openDialog();

			break;
		case R.id.but_sendsijijiashizheng:
			params = new RequestParams();
			params.addBodyParameter("driver_id", Myconstants.driver_id);
			params.addBodyParameter("status", "2");
			params.addBodyParameter("driver_license",
					new File(sdcardTempFile.getAbsolutePath()));
			id = v.getId();
			openDialog();
			break;
		case R.id.but_sendcarjiashizheng:
			params = new RequestParams();
			params.addBodyParameter("driver_id", Myconstants.driver_id);
			params.addBodyParameter("vehicle_driving",
					new File(sdcardTempFile.getAbsolutePath()));
			id = v.getId();
			openDialog();
			break;
		case R.id.but_sendyingyunzheng:
			params = new RequestParams();
			params.addBodyParameter("driver_id", Myconstants.driver_id);
			params.addBodyParameter("status", "4");
			params.addBodyParameter("operating_certificate", new File(
					sdcardTempFile.getAbsolutePath()));
			id = v.getId();
			openDialog();
			break;
		default:
			break;
		}
	}

	private void openDialog() {

		if (dialog == null) {
			dialog = new Builder(this).setItems(
					new String[] { "相机", "相册" },
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (which == 0) {
								// 选择拍照
								Intent cameraintent = new Intent(
										MediaStore.ACTION_IMAGE_CAPTURE);
								// 指定调用相机拍照后照片的储存路径
								cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,
										Uri.fromFile(tempFile));
								startActivityForResult(cameraintent, 101);

							} else {
								Intent intent = new Intent(
										"android.intent.action.PICK");
								intent.setDataAndType(
										MediaStore.Images.Media.INTERNAL_CONTENT_URI,
										"image/*");
								intent.putExtra("output",
										Uri.fromFile(sdcardTempFile));
								intent.putExtra("crop", "true");
								//intent.putExtra("aspectX", 2);// 裁剪框比例
								//intent.putExtra("aspectY", 3);
								intent.putExtra("outputX", 720);// 输出图片大小
								intent.putExtra("outputY", 1280);
								startActivityForResult(intent, 100);
							}
						}
					}).create();
		}
		if (!dialog.isShowing()) {
			dialog.show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 100) {
			Logger.e("filelenth", "" + sdcardTempFile.length());
			if (sdcardTempFile.length() < 10000) {
				UIUtils.showToastSafe("请选择文件");
			} else {
				uploadMethod(params, uploadHost);
			}

		} else if (requestCode == 101) {

			startPhotoZoom(Uri.fromFile(tempFile));
		} else if (requestCode == 102) {

			if (sdcardTempFile.length() < 10000) {
				UIUtils.showToastSafe("请选择文件");
			} else {
				uploadMethod(params, uploadHost);
			}

		}
	}

	private void uploadMethod(RequestParams params, String uploadHost) {
		httpUtils.send(HttpMethod.POST, uploadHost, params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						UIUtils.showProgresssafe("正在上传", context);
						UIUtils.showToastSafe("正在上传");
						switch (id) {
						case R.id.but_sendtouxiang:
							//TODO
							but_sendtouxiang.setText("正在上传请稍等~！");
							but_sendtouxiang.setClickable(false);
							break;
						case R.id.but_sendcarjiashizheng:
							but_sendcarjiashizheng.setText("正在上传请稍等~！");
							but_sendcarjiashizheng.setClickable(false);
							break;
						case R.id.but_sendsijijiashizheng:
							but_sendsijijiashizheng.setText("正在上传请稍等~！");
							but_sendsijijiashizheng.setClickable(false);
							break;
						case R.id.but_sendyingyunzheng:
							but_sendyingyunzheng.setText("正在上传请稍等~！");
							but_sendyingyunzheng.setClickable(false);
							break;
						default:
							break;
						}
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						UIUtils.stopProgresssafe();
						String results = responseInfo.result.toString().trim();
						Gson gson = new Gson();
						Register23response fromJson = gson.fromJson(results,
                                Register23response.class);
						//UIUtils.showToastSafe(fromJson.getStatus());
						if (fromJson.getStatus().equals("1")) {
							switch (id) {
							case R.id.but_sendtouxiang:
								UIUtils.showToastSafe("上传头像成功");
								but_sendtouxiang.setText("上传成功");
								timer = timer + 1;
								break;
							case R.id.but_sendcarjiashizheng:
								UIUtils.showToastSafe("上传车辆驾驶证成功");
								but_sendcarjiashizheng.setText("上传成功");
								timer = timer + 1;
								break;
							case R.id.but_sendsijijiashizheng:
								UIUtils.showToastSafe("上传司机驾驶证成功");
								but_sendsijijiashizheng.setText("上传成功");
								timer = timer + 1;
								break;
							case R.id.but_sendyingyunzheng:
								UIUtils.showToastSafe("上传营运证成功");
								but_sendyingyunzheng.setText("上传成功");
								timer = timer + 1;
								break;
							default:
								break;
							}

						} else {
							UIUtils.showToastSafe(fromJson.getResult().getcode_msg());
							switch (id) {
							case R.id.but_sendtouxiang:
								//TODO
								but_sendtouxiang.setText("上传失败重新上传");
								but_sendtouxiang.setClickable(true);
								break;
							case R.id.but_sendcarjiashizheng:
								but_sendcarjiashizheng.setText("上传失败重新上传");
								but_sendcarjiashizheng.setClickable(true);
								break;
							case R.id.but_sendsijijiashizheng:
								but_sendsijijiashizheng.setText("上传失败重新上传");
								but_sendsijijiashizheng.setClickable(true);
								break;
							case R.id.but_sendyingyunzheng:
								but_sendyingyunzheng.setText("上传失败重新上传");
								but_sendyingyunzheng.setClickable(true);
								break;
							default:
								break;
							}
							
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						UIUtils.stopProgresssafe();
						
						switch (id) {
						
						case R.id.but_sendtouxiang:
							UIUtils.showToastSafe("上传头像失败，请检查网络");
							but_sendtouxiang.setText("上传失败重新上传");
							but_sendtouxiang.setClickable(true);
							break;
						case R.id.but_sendcarjiashizheng:
							UIUtils.showToastSafe("上传车辆驾驶证失败");
							but_sendcarjiashizheng.setText("上传车辆驾驶证重新上传");
							but_sendcarjiashizheng.setClickable(true);
							break;
						case R.id.but_sendsijijiashizheng:
							UIUtils.showToastSafe("上传司机驾驶证失败");
							but_sendsijijiashizheng.setText("上传司机驾驶证失败重新上传");
							but_sendsijijiashizheng.setClickable(true);
							break;
						case R.id.but_sendyingyunzheng:
							UIUtils.showToastSafe("上传营运证失败");
							but_sendyingyunzheng.setText("上传营运证失败重新上传");
							but_sendyingyunzheng.setClickable(true);
							break;

						default:
							break;
						}

					}
				});
	}

	private void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");
		intent.putExtra("output", Uri.fromFile(sdcardTempFile));
		// aspectX aspectY 是宽高的比例
		//intent.putExtra("aspectX", 2);
		//intent.putExtra("aspectY", 3);

		// Log.i("sdcardTempFile:", sdcardTempFile);
		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", 720);
		intent.putExtra("outputY", 1280);
		intent.putExtra("return-data", false);
		intent.putExtra("noFaceDetection", true);
		startActivityForResult(intent, 102);

	}

	// 将进行剪裁后的图片传递到下一个界面上
	private void sentPicToNext(Intent picdata) {
		Bundle bundle = picdata.getExtras();
		if (bundle != null) {
			Bitmap photo = bundle.getParcelable("data");
			if (photo == null) {
			} else {
				// imageView.setImageBitmap(photo);
			}

		} else {
			Toast.makeText(getApplicationContext(), "未选择图片", Toast.LENGTH_SHORT).show();
		}
	}

	// 使用系统当前日期加以调整作为照片的名称
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

	public void xiayibu(View view){
		Intent intent = new Intent(getApplicationContext(), Register4.class);
		startActivity(intent);
		finish();
	}
	
	
	protected void dialog(String msg,final dialogPositiveonclicklistener dia) {
		Builder builder = new Builder(this);
		builder.setMessage(msg);
		builder.setTitle("提示");
		builder.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dia.positiveOnclik();
						dialog.dismiss();

					}
				});

		builder.create().show();
		
	}
	
	public interface dialogPositiveonclicklistener{
		public void positiveOnclik();
	}
	
	
}
