package com.zouzhe.walkingapp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.SyncStateContract.Constants;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.zouzhe.walkingapp.javabean.Recommendresponse;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.utils.Logger;
import com.zouzhe.walkingapp.utils.UIUtils;

public class Resetdriverphoto_Activity extends ActionBarActivity implements OnClickListener {

	private Button bt_resetdriverphoto_save;
	private File tempFile;
	private File sdcardTempFile;
	private AlertDialog dialog;
	private HttpUtils httpUtils;
	private RequestParams params;
	private String uploadHost = Myconstants.SUBMITTUPIANURL;
	private Activity context;
	private Button bt_resetdriverphoto_selectphoto;
	private ImageView iv_resetdriverphoto;
	private Intent intent;
	private int requestcode1;
	private String carname;
	private String cheliangurl = Myconstants.CHELIANGTUPIAN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resetdriverphoto_);
		intent = getIntent();
		context = this;

		// 判断内存卡是否存在
		if (Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {

		} else {
			dialog("没有sd卡，无法上传图片.点击跳往主界面", new dialogPositiveonclicklistener() {

				@Override
				public void positiveOnclik() {

					finish();
				}
			});
			// Toast.makeText(this, "没有SD卡", Toast.LENGTH_LONG).show();
		}

		requestcode1 = intent.getIntExtra("type", 0);
		carname = intent.getStringExtra("name");
		bt_resetdriverphoto_save = (Button) findViewById(R.id.bt_resetdriverphoto_save);
		bt_resetdriverphoto_selectphoto = (Button) findViewById(R.id.bt_resetdriverphoto_selectphoto);
		iv_resetdriverphoto = (ImageView) findViewById(R.id.iv_resetdriverphoto);
		bt_resetdriverphoto_selectphoto.setOnClickListener(this);
		bt_resetdriverphoto_save.setOnClickListener(this);

	}

	public void sendPhoto(RequestParams params, String url) {
		HttpUtils httpUtils2 = new HttpUtils();
		httpUtils2.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						UIUtils.showProgresssafe("正在上传图片。请等待",
								Resetdriverphoto_Activity.this);
						super.onStart();
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

						UIUtils.stopProgresssafe();
						String results = responseInfo.result.toString().trim();
						Gson gson = new Gson();
						Recommendresponse fromJson = gson.fromJson(results,
								Recommendresponse.class);
						if (fromJson.getStatus().equals("1")) {
							intent.putExtra("some",
									sdcardTempFile.getAbsolutePath());
							context.setResult(requestcode1, intent);
							UIUtils.showToastSafe(fromJson.getResult()
									.getcode_msg());
							Logger.e("上传图片返回值是1的时候", fromJson.getResult()
									.getcode_msg());
							context.finish();
						} else {
							UIUtils.showToastSafe(fromJson.getResult()
									.getcode_msg());
							Logger.e("上传图片返回值不是1的时候", fromJson.getResult()
									.getcode_msg());
						}

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						UIUtils.stopProgresssafe();
						UIUtils.showToastSafe("图片上传失败，请检查网络.错误代码201");
					}
				});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_resetdriverphoto_selectphoto:
			if (dialog == null) {
				dialog = new AlertDialog.Builder(this).setItems(
						new String[] { "相机", "相册" },
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (which == 0) {
									// 选择拍照
									Intent cameraintent = new Intent(
											MediaStore.ACTION_IMAGE_CAPTURE);
									tempFile = new File(Environment
											.getExternalStorageDirectory(),
											getPhotoFileName());
									sdcardTempFile = new File(
											"/mnt/sdcard/",
											"tmp_pic_wo_"
													+ SystemClock
															.currentThreadTimeMillis()
													+ ".jpg");
									// 指定调用相机拍照后照片的储存路径
									cameraintent.putExtra(
											MediaStore.EXTRA_OUTPUT,
											Uri.fromFile(tempFile));
									startActivityForResult(cameraintent, 101);

								} else {
									Intent intent = new Intent(
											"android.intent.action.PICK");
									sdcardTempFile = new File(
											"/mnt/sdcard/",
											"tmp_pic_wo_"
													+ SystemClock
															.currentThreadTimeMillis()
													+ ".jpg");
									intent.setDataAndType(
											MediaStore.Images.Media.INTERNAL_CONTENT_URI,
											"image/*");
									intent.putExtra("output",
											Uri.fromFile(sdcardTempFile));
									intent.putExtra("crop", "true");
									// intent.putExtra("aspectX", 2);// 裁剪框比例
									// intent.putExtra("aspectY", 3);
									//intent.putExtra("outputX", 720);// 输出图片大小
									//intent.putExtra("outputY", 1280);
									startActivityForResult(intent, 100);
								}
							}
						}).create();
			}
			if (!dialog.isShowing()) {
				dialog.show();
			}
			break;
		case R.id.bt_resetdriverphoto_save:

			if (sdcardTempFile == null) {
				UIUtils.showToastSafe("请选择图片");
				return;
			}
			if (sdcardTempFile.length() > 10000) {
				Logger.e("hah=========", sdcardTempFile.length() + "+++++"
						+ requestcode1);
				switch (requestcode1) {
				case 12:
					params = new RequestParams();
					params.addBodyParameter("driver_id", Myconstants.driver_id);
					params.addBodyParameter("avatar",
							new File(sdcardTempFile.getAbsolutePath()));
					sendPhoto(params, uploadHost);
					break;

				case 13:
					params = new RequestParams();
					params.addBodyParameter("driver_id", Myconstants.driver_id);
					params.addBodyParameter("vehicle_model", carname);
					Logger.e("检查vehicle", carname + "+++" + cheliangurl);
					params.addBodyParameter("vehicle_model_image", new File(
							sdcardTempFile.getAbsolutePath()));
					sendPhoto(params, cheliangurl);
					break;
				case 14:
					params = new RequestParams();
					params.addBodyParameter("driver_id", Myconstants.driver_id);
					params.addBodyParameter("vehicle_driving", new File(
							sdcardTempFile.getAbsolutePath()));
					sendPhoto(params, uploadHost);
					break;
				case 15:
					params = new RequestParams();
					params.addBodyParameter("driver_id", Myconstants.driver_id);
					params.addBodyParameter("driver_license", new File(
							sdcardTempFile.getAbsolutePath()));
					sendPhoto(params, uploadHost);
					break;
				case 16:
					params = new RequestParams();
					params.addBodyParameter("driver_id", Myconstants.driver_id);
					params.addBodyParameter("operating_certificate", new File(
							sdcardTempFile.getAbsolutePath()));
					sendPhoto(params, uploadHost);
					break;
				default:
					break;
				}

			} else {
				UIUtils.showToastSafe("请选择图片");
				context.finish();
			}

			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 100) {
			Logger.e("filelenth", "" + sdcardTempFile.length());
			if (sdcardTempFile.length() < 10000) {
				UIUtils.showToastSafe("请选择文件");
			} else {
				new BitmapUtils(this).display(iv_resetdriverphoto,
						sdcardTempFile.getAbsolutePath());
			}

		} else if (requestCode == 101) {

			startPhotoZoom(Uri.fromFile(tempFile));
		} else if (requestCode == 102) {

			if (sdcardTempFile.length() < 10000) {
				UIUtils.showToastSafe("请选择文件");
			} else {
				new BitmapUtils(this).display(iv_resetdriverphoto,
						sdcardTempFile.getAbsolutePath());
			}

		}
	}

	private void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");
		intent.putExtra("output", Uri.fromFile(sdcardTempFile));
		// aspectX aspectY 是宽高的比例
		// intent.putExtra("aspectX", 2);
		// intent.putExtra("aspectY", 3);

		// Log.i("sdcardTempFile:", sdcardTempFile);
		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", 720);
		intent.putExtra("outputY", 1280);
		intent.putExtra("return-data", false);
		intent.putExtra("noFaceDetection", true);
		startActivityForResult(intent, 102);

	}

	// 使用系统当前日期加以调整作为照片的名称
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

	protected void dialog(String msg, final dialogPositiveonclicklistener dia) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage(msg);
		builder.setTitle("提示");
		builder.setPositiveButton("确定",
				new android.content.DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dia.positiveOnclik();
						dialog.dismiss();

					}
				});

		builder.create().show();

	}

	public interface dialogPositiveonclicklistener {
		public void positiveOnclik();
	}
}
