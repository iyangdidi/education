package com.thu.edu.util;

import android.os.Environment;

public class ConstantsValue {

	public static String UDID = "";
	public static String VERSION = "";
	public static String CHANNEL = "";

	public static final int MSGCODE_HTTP_RESPONSE = 0;
	public final static int GET_REFRESH = 333;
	public final static int GET_MORE = 22;
	public static final int MSGCODE_HTTP_ERROR = 1;
	public static final int MSGCODE_NO_NETWORK_ERROR = 2;
	public static final int REQURE_SIZE = 10;

	public static final String UUID = "uuid";

//  public static String URL_PRE = "http://223.202.15.133:5000";
//  public static String URL_PRE = "http://192.0.1.160:8080/StartProject";
	public static String URL_PRE = "http://121.40.142.217:8080/BigBaby";
//	public static String URL_PRE = "http://192.168.0.236:8080/BigBaby";

	public static String QINIU_URL_PRE = "http://7u2mqi.com2.z0.glb.qiniucdn.com/";

	public static String QINIU_TOKEN = "wnD3LCd4UGXyDr9vpMFxjO943tpVy1oYQWckJ4kY:gDXUmhzCB6QWvc-Ulaiq655ocBA=:eyJzY29wZSI6Imh1b2RvbmciLCJkZWFkbGluZSI6Mjg0ODMyNjExNn0=";

	public static final String USER_ID = "user_id";

	public static final String SDCARD_ROOT_DIR = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	public static final String IMG_HEAD_PICTURE_CAMERA = SDCARD_ROOT_DIR
			+ "/startproject/headpicture/camera";// 摄像机拍摄的照片文件�?

	/* 头像上传 */
	public final static String NAME_CAPTURE_HEADPICTURE = "HeadPicture.jpg";

	public static final String IS_LOGIN = "is_login";

	/* 设置>修改用户信息 */
	public static final int USER_NICKNAME = 1;
	public static final int USER_GENDER = 2;
	public static final int USER_PHONE = 3;
	public static final int USER_AREA = 4;
	public static final int BABY_NICKNAME = 5;
	public static final int BABY_GENDER = 6;
	public static final int BABY_BIRTH = 7;
	public static final int USER_PORTRAIT = 8;

	/* 登出 */
	public static final int LOGOUT = 1 << 7;
	public static final int LOGIN_OK = 1 << 8;

	public static final int GET_CAPTCHA = 1 << 6;

	public static final String APP_ID = "wx0287b0cf427d591b";// "wx1290cc8f6260649c";//"wx0287b0cf427d591b";

}
