package com.thu.edu.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;


/**
 * 
 * 包含网络处理方法的工具类
 */

public class NetworkUtil {
	
	private static final String TAG = NetworkInfo.class.getSimpleName();

	public static final String WIFI = "WiFi";

	/**
	 * Returns whether the network is available
	 */
	public static boolean isNetworkAvailable(Context context) {
		if (context == null)
			return false;

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.w(TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo[] infos = connectivity.getAllNetworkInfo();
			if (infos != null) {
				for (int i = 0, count = infos.length; i < count; i++) {
					if (infos[i] != null) {
						if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns whether the network is roaming
	 */
	public static boolean isNetworkRoaming(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.w(TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null
					&& info.getType() == ConnectivityManager.TYPE_MOBILE) {
				TelephonyManager telManager = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				if (telManager != null && telManager.isNetworkRoaming()) {
					Log.v(TAG, "network is roaming");
					return true;
				} else {
					Log.v(TAG, "network is not roaming");
				}
			} else {
				Log.v(TAG, "not using mobile network");
			}
		}
		return false;
	}

	/**
	 * Wi-Fi 是否已连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiNetworkAvailable(Context context) {
		ConnectivityManager cm = ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE));
		if (cm != null) {
			NetworkInfo info = cm.getActiveNetworkInfo();
			if (info == null || !info.isConnected()) {
				return false;
			}

			if (info.getType() == ConnectivityManager.TYPE_WIFI) {
				return true;
			}
		}

		return false;
	}

	public static boolean isCMWAP(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && StringUtils.equals(info.getExtraInfo(), "cmwap")) {
			return true;
		}
		
		return false;
	}

	public static String getWifiName(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		return wInfo == null ? "" : wInfo.getSSID();
	}

	/**
	 * 获取当前网络类型
	 * 
	
	 */
	public static String getNetwork(Context context) {
		String network = null;

		if (NetworkUtil.isWifiNetworkAvailable(context)) {
			network = WIFI;
		} else {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			if (tm != null) {
				int type = tm.getNetworkType();
				switch (type) {
				case TelephonyManager.NETWORK_TYPE_UMTS:
				case TelephonyManager.NETWORK_TYPE_HSDPA:
				case TelephonyManager.NETWORK_TYPE_GPRS:
				case TelephonyManager.NETWORK_TYPE_EDGE:
				case TelephonyManager.NETWORK_TYPE_EVDO_0:
				case TelephonyManager.NETWORK_TYPE_EVDO_A:
				case TelephonyManager.NETWORK_TYPE_EVDO_B:
				case TelephonyManager.NETWORK_TYPE_CDMA:
					network = type + "";
					break;

				default:
					network = "Unknown";
					break;
				}
			} else {
				network = "Unknown";
			}
		}

		return network;
	}

	
	/**
	 * 判断当前网络类型是否为2g gsm,cdma,gprs,EDGE为2g
	 * 
	 
	 */
	public static Boolean isTwoG(Context context) {
		Boolean flag = false;
		String network = null;

		if (NetworkUtil.isWifiNetworkAvailable(context)) {
			network = WIFI;
			flag = false;
		} else {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			if (tm != null) {
				int type = tm.getNetworkType();
				switch (type) {
				case TelephonyManager.NETWORK_TYPE_GPRS:
				case TelephonyManager.NETWORK_TYPE_EDGE:
				case TelephonyManager.NETWORK_TYPE_CDMA:
					flag = true;
					break;

				default:
					flag = false;
					break;
				}
			}
		}

		return flag;
	}

	public static String getNetworkTypeString(Context context) {
		String network = "";

		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			String type = networkInfo.getTypeName();

			if (type.equalsIgnoreCase("WIFI")) {
				network = WIFI;
			} else if (type.equalsIgnoreCase("MOBILE")) {
				String proxyHost = android.net.Proxy.getDefaultHost();

				network = TextUtils.isEmpty(proxyHost) ? (isFastMobileNetwork(context))
						: "WAP";
			}
		} else {
			network = "unkown";
		}
		return network;
	}

	private static String isFastMobileNetwork(Context context) {

		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		switch (telephonyManager.getNetworkType()) {
		case TelephonyManager.NETWORK_TYPE_1xRTT:
		case TelephonyManager.NETWORK_TYPE_CDMA:
		case TelephonyManager.NETWORK_TYPE_EDGE:
		case TelephonyManager.NETWORK_TYPE_GPRS:
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return "2G";
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
		case TelephonyManager.NETWORK_TYPE_HSDPA:
		case TelephonyManager.NETWORK_TYPE_HSPA:
		case TelephonyManager.NETWORK_TYPE_HSUPA:
		case TelephonyManager.NETWORK_TYPE_UMTS:
		case TelephonyManager.NETWORK_TYPE_EHRPD:
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			return "3G";
		case TelephonyManager.NETWORK_TYPE_LTE:
			return "4G";
		default:
			return "unkown";
		}
	}

	public static String[] getNetWorkInfo(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null) {
			String[] networkInfo = new String[3];
			networkInfo[0] = getNetworkTypeString(context);
			networkInfo[1] = (info.getExtraInfo() == null ? "" : info
					.getExtraInfo());
			networkInfo[2] = info.getSubtypeName();

			return networkInfo;
		}

		return null;
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("getLocalIpAddress", ex.toString());
		} catch (Exception e) {
			Log.e("getLocalIpAddress", e.toString());
		}
		return null;
	}

	public static boolean hasMoreThanOneConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		} else {
			NetworkInfo[] info = manager.getAllNetworkInfo();
			int counter = 0;
			for (int i = 0; i < info.length; i++) {
				if (info[i] != null) {
					if (info[i].isConnected()) {
						counter++;
					}
				}
			}
			if (counter > 1) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 处于2G/3G网络环境下
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobileNetworkAvaliable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			State wifiState = null;
			State mobileState = null;
			if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null) {
				wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
						.getState();
			}
			if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
				mobileState = cm
						.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
						.getState();
			}

			if (wifiState != null && mobileState != null
					&& State.CONNECTED != wifiState
					&& State.CONNECTED == mobileState) { // 连上了手机网络
				return true;
			}
		}
		return false;
	}
}
