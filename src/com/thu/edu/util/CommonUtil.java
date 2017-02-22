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

	// ��û��ʼ 7���� 7���
	public static final int NOTSTART = 1, OVERDAY7IN = 2, OVERDAY7 = 3;

//	public static void forceUpdate(Context context) {
//		UmengUpdateAgent.forceUpdate(context);
//	}

//	/** ��õ�ǰ��γ�Ⱦ��� */
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
//			// ֹͣ��λ
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

	/** ʱ���ת�������ĸ�ʽ */
	public static String getDays(long time) {

		SimpleDateFormat format = new SimpleDateFormat("MM��dd��   HH:mm");
		Date date = new Date(time);

		return format.format(date);
	}

	/** ʱ���ת�������ĸ�ʽ: yyyy��MM��dd�� */
	public static String getYYYYMMDD(long time) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��");
		Date date = new Date(time);

		return format.format(date);
	}

	/** ʱ���ת�������ĸ�ʽ: xxxx-xx-xx xx:xx */
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
	 * ʱ�任��ʱ���
	 * 
	 * @throws ParseException
	 */
	public static long getDaysFormat(String time) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy��MM��dd��  HH:mm").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	/**
	 * ʱ�任��ʱ��� ��������
	 * 
	 * @throws ParseException
	 */
	public static long getLongYYYYMMDD(String time) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy��MM��dd��").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	/** ��������ʱ���֮���֮���������� */
	public static String getNextTime(long stopTime) {

		String time = "";

		// ��ǰʱ��
		Date now = new Date();
		Calendar c1 = Calendar.getInstance();
		c1.setTime(now);

		// ֹͣʱ��
		Date stop = new Date(stopTime);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(stop);

		long l1 = c1.getTimeInMillis();
		long l2 = c2.getTimeInMillis();

		int days = (int) ((l2 - l1) / (24 * 60 * 60 * 1000));
		time = days + "���";
		if (days < 1) {
			time = "������ʼ";
		}

		return time;
	}

	/** return 0 7���� 1 7��� */
	public static int getHuoDongOverType(long startTime, long endTime) {

		// ��ǰʱ��
		Date now = new Date();
		Calendar c1 = Calendar.getInstance();
		c1.setTime(now);
		
		if(startTime < now.getTime())
			return NOTSTART;//û�п�ʼ�

		// ֹͣʱ��
		Date stop = new Date(endTime);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(stop);

		long l1 = c1.getTimeInMillis();
		long l2 = c2.getTimeInMillis();

		int day = (int) ((l2 - l1) / (24 * 60 * 60 * 1000));
		if (day > 7)//����ʱ�������7��
			return OVERDAY7;
		
		return OVERDAY7IN;//Ĭ�Ϸ��ؽ���7����
	}

	// /** ��������ʱ���֮���֮ǰ��������� */
	// public static long getPreDays(long stopTime) {
	//
	// // ��ǰʱ��
	// Date now = new Date();
	// Calendar c1 = Calendar.getInstance();
	// c1.setTime(now);
	//
	// // ֹͣʱ��
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
	// return days;// ����Ϊ����
	// }
	//
	// return -hours;// ����ΪСʱ
	// }

	/** ��������ʱ���֮���֮ǰ�ľ�ȷʱ�� */
	public static String getPreTime(long stopTime) {

		String time = "";

		// ��ǰʱ��
		Date now = new Date();
		Calendar c1 = Calendar.getInstance();
		c1.setTime(now);

		// ֹͣʱ��
		Date stop = new Date(stopTime);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(stop);

		long l1 = c1.getTimeInMillis();
		long l2 = c2.getTimeInMillis();

		int days = (int) ((l1 - l2) / (24 * 60 * 60 * 1000));
		time = days + "��ǰ";
		if (days < 1) {
			int hours = (int) ((l1 - l2) / (60 * 60 * 1000));
			time = hours + "Сʱǰ";
			if (hours < 1) {
				int min = (int) ((l1 - l2) / (60 * 1000));
				time = min + "����ǰ";
			}
		}

		return time;// ����ΪСʱ
	}

	/** ���л������beanΪjson��ʽ�ַ��� */
	public static String serializeRequestParams(AbstractRequest requestObject) {
		Gson gson = new Gson();

		requestObject.setChannel(ConstantsValue.CHANNEL);
		requestObject.setUdid(ConstantsValue.UDID);
		requestObject.setVersion(ConstantsValue.VERSION);
		String requestString = gson.toJson(requestObject);

		return requestString;
	}

	/** ������ʾ�� */
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

	/** ��ȡӦ�ó���汾 */
	public static String getAppVersion(Context context) {
		// ����������ȡ����Ϣ,Ҳ�����嵥�ļ�����Ϣ��
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packageInfo = pm.getPackageInfo(
					context.getPackageName(), 0);
			return StringUtils.substring(packageInfo.versionName, 0, 1);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return ""; // can't reach �϶����ᷢ����
		}
	}

	/** ��������� */
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
			// ���MD5ժҪ�㷨�� MessageDigest ����
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽڸ���ժҪ
			mdInst.update(btInput);
			// �������
			byte[] md = mdInst.digest();
			// ������ת����ʮ�����Ƶ��ַ�����ʽ
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

	/* �Ա�intת���� */
	public static String getGender(int tag) {// 0���У�1��Ů
		return (tag == 0) ? "��" : "Ů";
	}

	public static int getGenderInt(String value) {// 0���У�1��Ů
		return (value == "��") ? 0 : 1;
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

		return age + "��";
	}
	
//	public static View setRFocused(View view, Context context){		
//		ImageView user_state_icon = (ImageView) view.findViewById(R.id.user_state_icon);
//		TextView user_state = (TextView) view.findViewById(R.id.user_state);
//		
//		view.setBackgroundColor(context.getResources().getColor(R.color.font_blue1));
//		user_state_icon.setImageResource(R.drawable.ic_focus_y);
//		user_state.setText("�ѹ�ע");
//		
//		return view;
//	}
//	public static View setRAddFocus(View view, Context context){		
//		ImageView user_state_icon = (ImageView) view.findViewById(R.id.user_state_icon);
//		TextView user_state = (TextView) view.findViewById(R.id.user_state);
//		
//		view.setBackgroundColor(context.getResources().getColor(R.color.font_red1));
//		user_state_icon.setImageResource(R.drawable.ic_focus_a);
//		user_state.setText("�ӹ�ע");
//		
//		return view;
//	}
//	public static View setRBiFocus(View view, Context context){		
//		ImageView user_state_icon = (ImageView) view.findViewById(R.id.user_state_icon);
//		TextView user_state = (TextView) view.findViewById(R.id.user_state);
//		
//		view.setBackgroundColor(context.getResources().getColor(R.color.font_blue1));
//		user_state_icon.setImageResource(R.drawable.ic_focus_aic_focus_a);
//		user_state.setText("�����ע");
//		
//		return view;
//	}
}
