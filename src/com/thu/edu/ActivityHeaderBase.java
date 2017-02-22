package com.thu.edu;

import com.thu.edu.util.*;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
//import com.bigbaby.R;
//import com.bigbaby.global.ConstantsValue;

public class ActivityHeaderBase extends Activity {

	private Activity mActivity;
	public Dialog mDialog;
	
//	private TextView tvLeft, tvRight;
//	private LinearLayout llMid, llLeft, llRight;
//	private ImageView ivLeft, ivRight;
//	private RelativeLayout rlTop;
//	private FrameLayout flContent;
//	private CheckBox cbMid;

	protected Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case ConstantsValue.MSGCODE_HTTP_RESPONSE:
				handleResponse(msg);
				break;
			case ConstantsValue.MSGCODE_HTTP_ERROR:
				handleResponseError(msg);
				break;
			case ConstantsValue.MSGCODE_NO_NETWORK_ERROR:
				handleNoNetworkError(msg);
				break;
			}
		};
	};

	public void handleResponse(android.os.Message msg) {
	}

	public void handleResponseError(android.os.Message msg) {

	}

	public void handleNoNetworkError(android.os.Message msg) {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = ActivityHeaderBase.this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}


//	/** 退出页面 */
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			onFinishAnim(this);
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}

//	/** 退出页面动画 */
//	public static void onFinishAnim(Activity context) {
//		try {
//			context.finish();
//			context.overridePendingTransition(R.anim.slide_right_in,
//					R.anim.slide_right_out);
//		} catch (Exception e) {
//		}
//	}
	
	public void dismissDialog() {
		try {
			if (null != mDialog) {
				if (mDialog.isShowing()) {
					mDialog.dismiss();
				}
				mDialog = null;
			}
		} catch (Exception e) {
		}
	}

//	public void showMyDialog(int type){
//		switch (type) {
//		case 1://加载中
//			View diaView1 = View.inflate(this, R.layout.init_load, null);
//			myDialog(diaView1);
//			break;
//		case 2://请稍等
//			View diaView2 = View.inflate(this, R.layout.load_oper, null);
//			myDialog(diaView2);
//			break;
//		default:
//			break;
//		}
//		
//	}
	
//	public void myDialog(View diaView){
//		try {
//			if (null != mDialog && mDialog.isShowing()) {
//				return;
//			}
//			
//			mDialog = new Dialog(this, R.style.loadingDialogStyle);// 创建自定义样式dialog
//
//			mDialog.setCanceledOnTouchOutside(false);
//			mDialog.setContentView(diaView);// 设置布局
//			mDialog.show();
//		} catch (Exception e) {
//		}
//	}
//	public void showInitDailog() {
//		try {
//			if (null != mDialog && mDialog.isShowing()) {
//				return;
//			}
//			
//			mDialog = new Dialog(this, R.style.loadingDialogStyle);// 创建自定义样式dialog
//
//			View diaView = View.inflate(this, R.layout.init_load, null);
//			mDialog.setCanceledOnTouchOutside(false);
//			mDialog.setContentView(diaView);// 设置布局
//			mDialog.show();
//		} catch (Exception e) {
//		}
//	}

	
//	public class OnClick implements OnClickListener{
//		Intent intent;
//		public OnClick(Intent intent) {
//			this.intent = intent;
//		}
//		@Override
//		public void onClick(View arg0) {
//			mActivity.startActivity(this.intent);
//			mActivity.overridePendingTransition(R.anim.slide_left_in,
//					R.anim.slide_left_out);
//		}
//	}
}
