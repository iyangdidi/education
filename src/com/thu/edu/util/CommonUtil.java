package com.thu.edu.util;

import com.thu.edu.request.*;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

//import com.bigbaby.application.LocationApplication;
//import com.bigbaby.global.ConstantsValue;
//import com.bigbaby.model.request.AbstractRequest;
//
//import com.bigbaby.R;
//import com.umeng.update.UmengUpdateAgent;

public class CommonUtil {

	private static Toast sToast = null;

	// 还没开始 7天内 7天后
	public static final int NOTSTART = 1, OVERDAY7IN = 2, OVERDAY7 = 3;

//	public static void forceUpdate(Context context) {
//		UmengUpdateAgent.forceUpdate(context);
//	}

//	/** 获得当前经纬度距离 */
//	public static double getCurrentDistance(Activity context, double latitude,
//			double longitude) {
//
//		double distance;
//
//		double creentLatitude = ((LocationApplication) context.getApplication()).nlatitude;
//		double cteentLongitude = ((LocationApplication) context
//				.getApplication()).nlongtitude;
//
//		if (creentLatitude != 0 && cteentLongitude != 0)
//			// 停止定位
//			((LocationApplication) context.getApplicationContext()).mLocationClient
//					.stop();
//
//		double Lat1 = rad(latitude);
//		double Lat2 = rad(creentLatitude);
//		double a = Lat1 - Lat2;
//		double b = rad(cteentLongitude) - rad(cteentLongitude);
//
//		distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
//				+ Math.cos(Lat1) * Math.cos(Lat2)
//				* Math.pow(Math.sin(b / 2), 2)));
//		distance = distance * EARTH_RADIUS;
//		distance = Math.round(distance * 10000) / 10000000;
//		if (distance < 1)
//			distance = 0;
//
//		return distance;
//	}

	private static final double EARTH_RADIUS = 6378137.0;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/** 时间戳转换成中文格式 */
	public static String getDays(long time) {

		SimpleDateFormat format = new SimpleDateFormat("MM月dd日   HH:mm");
		Date date = new Date(time);

		return format.format(date);
	}

	/** 时间戳转换成中文格式: yyyy年MM月dd日 */
	public static String getYYYYMMDD(long time) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = new Date(time);

		return format.format(date);
	}

	/** 时间戳转换成中文格式: xxxx-xx-xx xx:xx */
	public static String getYMd(long time) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date(time);

		return format.format(date);
	}
	
	public static String getYMd2(long time) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(time);

		return format.format(date);
	}
	

	/**
	 * 时间换成时间戳
	 * 
	 * @throws ParseException
	 */
	public static long getDaysFormat(String time) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy年MM月dd日  HH:mm").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	/**
	 * 时间换成时间戳 ：年月日
	 * 
	 * @throws ParseException
	 */
	public static long getLongYYYYMMDD(String time) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy年MM月dd日").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	/** 计算两个时间戳之间的之后的天数间隔 */
	public static String getNextTime(long stopTime) {

		String time = "";

		// 当前时间
		Date now = new Date();
		Calendar c1 = Calendar.getInstance();
		c1.setTime(now);

		// 停止时间
		Date stop = new Date(stopTime);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(stop);

		long l1 = c1.getTimeInMillis();
		long l2 = c2.getTimeInMillis();

		int days = (int) ((l2 - l1) / (24 * 60 * 60 * 1000));
		time = days + "天后";
		if (days < 1) {
			time = "即将开始";
		}

		return time;
	}

	/** return 0 7天内 1 7天后 */
	public static int getHuoDongOverType(long startTime, long endTime) {

		// 当前时间
		Date now = new Date();
		Calendar c1 = Calendar.getInstance();
		c1.setTime(now);
		
		if(startTime < now.getTime())
			return NOTSTART;//没有开始活动

		// 停止时间
		Date stop = new Date(endTime);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(stop);

		long l1 = c1.getTimeInMillis();
		long l2 = c2.getTimeInMillis();

		int day = (int) ((l2 - l1) / (24 * 60 * 60 * 1000));
		if (day > 7)//结束时间大于了7天
			return OVERDAY7;
		
		return OVERDAY7IN;//默认返回结束7天内
	}

	// /** 计算两个时间戳之间的之前的天数间隔 */
	// public static long getPreDays(long stopTime) {
	//
	// // 当前时间
	// Date now = new Date();
	// Calendar c1 = Calendar.getInstance();
	// c1.setTime(now);
	//
	// // 停止时间
	// Date stop = new Date(stopTime);
	// Calendar c2 = Calendar.getInstance();
	// c2.setTime(stop);
	//
	// long l1 = c1.getTimeInMillis();
	// long l2 = c2.getTimeInMillis();
	//
	// int hours = (int) ((l1 - l2) / (60 * 60 * 1000));
	// if (hours > 24) {
	// int days = (int) ((l1 - l2) / (24 * 60 * 60 * 1000));
	//
	// if (days < 24) {
	// return 0;
	// }
	// return days;// 正数为天数
	// }
	//
	// return -hours;// 负数为小时
	// }

	/** 计算两个时间戳之间的之前的精确时间 */
	public static String getPreTime(long stopTime) {

		String time = "";

		// 当前时间
		Date now = new Date();
		Calendar c1 = Calendar.getInstance();
		c1.setTime(now);

		// 停止时间
		Date stop = new Date(stopTime);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(stop);

		long l1 = c1.getTimeInMillis();
		long l2 = c2.getTimeInMillis();

		int days = (int) ((l1 - l2) / (24 * 60 * 60 * 1000));
		time = days + "天前";
		if (days < 1) {
			int hours = (int) ((l1 - l2) / (60 * 60 * 1000));
			time = hours + "小时前";
			if (hours < 1) {
				int min = (int) ((l1 - l2) / (60 * 1000));
				time = min + "分钟前";
			}
		}

		return time;// 负数为小时
	}

	/** 序列化请求的bean为json格式字符串 */
	public static String serializeRequestParams(AbstractRequest requestObject) {
		Gson gson = new Gson();

		requestObject.setChannel(ConstantsValue.CHANNEL);
		requestObject.setUdid(ConstantsValue.UDID);
		requestObject.setVersion(ConstantsValue.VERSION);
		String requestString = gson.toJson(requestObject);

		return requestString;
	}

	/** 弹出提示框 */
	public static void showToast(Context context, String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}

//	public static void shortToast(Context context, int resId) {
//
//		LayoutInflater inflater = LayoutInflater.from(context);
//		View contentView = inflater.inflate(R.layout.toast_newly_bg, null);
//		TextView textView = (TextView) contentView.findViewById(R.id.tost_tv);
//
//		if (sToast == null) {
//			// Rect r = DeviceUtil.getScreenRect(context);
//			sToast = new Toast(context);
//			textView.setText(context.getText(resId));
//			sToast.setView(contentView);
//			sToast.setDuration(Toast.LENGTH_LONG);
//			// sToast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
//			// r.bottom / 5);
//			sToast.show();
//		} else {
//			textView.setText(context.getText(resId));
//			sToast.setView(contentView);
//			sToast.setDuration(Toast.LENGTH_LONG);
//			sToast.show();
//		}
//	}

	/** 获取应用程序版本 */
	public static String getAppVersion(Context context) {
		// 包管理器获取包信息,也就是清单文件的信息。
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packageInfo = pm.getPackageInfo(
					context.getPackageName(), 0);
			return StringUtils.substring(packageInfo.versionName, 0, 1);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return ""; // can't reach 肯定不会发生。
		}
	}

	/** 获得渠道号 */
	public static String getChanel(Context context) {
		String CHANNELID = "000000";
		try {
			ApplicationInfo appInfo = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			CHANNELID = appInfo.metaData.getString("UMENG_CHANNEL");
		} catch (Exception e) {
			//
		}
		showToast(context, CHANNELID);
		return CHANNELID;
	}

//	public static void setScreenOrientation(Activity activity) {
//		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
//
//		if (currentapiVersion >= android.os.Build.VERSION_CODES.FROYO) {
//			Configuration config = activity.getResources().getConfiguration();
//
//			int screenSize = config.screenLayout
//					& Configuration.SCREENLAYOUT_SIZE_MASK;
//			Rect r = DeviceUtil.getScreenRect(activity);
//			int screenSq = r.right * r.bottom;
//
//			if (screenSize != Configuration.SCREENLAYOUT_SIZE_XLARGE
//					&& screenSize != Configuration.SCREENLAYOUT_SIZE_LARGE
//					&& (screenSize != Configuration.SCREENLAYOUT_SIZE_NORMAL || screenSq < 900000)) {
//				activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//			}
//		} else {
//			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//		}
//
//	}

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isMobileNO(String mobiles) {

		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|17[0-9])\\d{8}$");

		Matcher m = p.matcher(mobiles);

		System.out.println(m.matches() + "---");

		return m.matches();

	}

	/* 性别int转中文 */
	public static String getGender(int tag) {// 0：男；1：女
		return (tag == 0) ? "男" : "女";
	}

	public static int getGenderInt(String value) {// 0：男；1：女
		return (value == "男") ? 0 : 1;
	}

//	public static void back(Activity context) {
//		context.finish();
//		context.overridePendingTransition(R.anim.slide_right_in,
//				R.anim.slide_right_out);
//	}

	public static String getAge(Long birth) {

		Calendar cal = Calendar.getInstance();
		Date birthDay = new Date(birth);

		if (cal.before(birthDay)) {
			return "The birthDay is before Now.It's unbelievable!";
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}

		return age + "岁";
	}
	
//	public static View setRFocused(View view, Context context){		
//		ImageView user_state_icon = (ImageView) view.findViewById(R.id.user_state_icon);
//		TextView user_state = (TextView) view.findViewById(R.id.user_state);
//		
//		view.setBackgroundColor(context.getResources().getColor(R.color.font_blue1));
//		user_state_icon.setImageResource(R.drawable.ic_focus_y);
//		user_state.setText("已关注");
//		
//		return view;
//	}
//	public static View setRAddFocus(View view, Context context){		
//		ImageView user_state_icon = (ImageView) view.findViewById(R.id.user_state_icon);
//		TextView user_state = (TextView) view.findViewById(R.id.user_state);
//		
//		view.setBackgroundColor(context.getResources().getColor(R.color.font_red1));
//		user_state_icon.setImageResource(R.drawable.ic_focus_a);
//		user_state.setText("加关注");
//		
//		return view;
//	}
//	public static View setRBiFocus(View view, Context context){		
//		ImageView user_state_icon = (ImageView) view.findViewById(R.id.user_state_icon);
//		TextView user_state = (TextView) view.findViewById(R.id.user_state);
//		
//		view.setBackgroundColor(context.getResources().getColor(R.color.font_blue1));
//		user_state_icon.setImageResource(R.drawable.ic_focus_aic_focus_a);
//		user_state.setText("互相关注");
//		
//		return view;
//	}
}
