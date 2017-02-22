package com.thu.edu.util;

import com.thu.edu.service.*;
import com.thu.edu.response.*;


import java.util.UUID;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

//import com.bigbaby.global.ConstantsValue;
//import com.bigbaby.model.response.ResponseBasic;
//import com.bigbaby.service.InitialService;
import com.google.gson.Gson;

public class GlobalUtil {
	
	private static final String PREFERENCE_LOGING_NAME = "login_name";
	private static final String PREFERENCE_PWD = "passwd";
	private static final String PREFERENCE_UID = "uid";
	private static final String PREFERENCE_TOKEN = "token";
	private static final String PREFERENCE_HEAD_IMG_URL = "head_img_url";
	
	
	public static void startInitialService(Context context){
	
		if (NetworkUtil.isNetworkAvailable(context)) {
            Intent intent = new Intent();
            intent.setClass(context, InitialService.class);
            context.startService(intent);
		} else {
			
			//TO DO: error handle
		}
	}
	
	private static String mUUIDString;
	
	public static String getUUIDString(Context context) {
        if (mUUIDString != null) {
                return mUUIDString;
        }

//        String uuidString = PreferenceManager.getDefaultSharedPreferences(
//                        context).getString(ConstantsValue.UUID, null);
        
        String uuidString = PreferenceUtil.getString(context, ConstantsValue.UUID,null);
        
        if (uuidString != null) {
                mUUIDString = uuidString;
        } else {
                UUID uuid = UUID.randomUUID();
//                SharedPreferences prefs = PreferenceManager
//                                .getDefaultSharedPreferences(context);
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString(ConstantsValue.UUID, uuid.toString());
//                editor.commit();

                PreferenceUtil.putString(context, ConstantsValue.UUID, uuid.toString());
                
                mUUIDString = uuid.toString();
        }

        return mUUIDString;
}
	
	public static void  setLoginName(Context context,String name)
	{
		PreferenceUtil.putString(context, PREFERENCE_LOGING_NAME, name);
	}
	
	public static String getLoginName(Context context)
    {
    	return PreferenceUtil.getString(context, PREFERENCE_LOGING_NAME, null);
    }
    
	public static void  setToken(Context context,String token){
		PreferenceUtil.putString(context, PREFERENCE_TOKEN, token);
	}
	
    public static String getToken(Context context){
    	
    	return PreferenceUtil.getString(context, PREFERENCE_TOKEN, "");
    }
    
    public static void  setUid(Context context,int uid){
    	PreferenceUtil.putInt(context, PREFERENCE_UID, uid);
    }
    
    public static int  getUid(Context context){
    	
    	return PreferenceUtil.getInt(context, PREFERENCE_UID, -1);
    }
    
    public static void  setPassword(Context context,String passwd){
    	PreferenceUtil.putString(context, PREFERENCE_PWD, passwd);
    }
    
    
    public static String getPassword(Context context){
    	return PreferenceUtil.getString(context, PREFERENCE_PWD, null);
    }
    
    
    public static void  setHeadImgUrl(Context context,String headImgUrl){
    	PreferenceUtil.putString(context, headImgUrl, headImgUrl);
    }
    
    
    public static String getHeadImgUrl(Context context){
    	return PreferenceUtil.getString(context, PREFERENCE_HEAD_IMG_URL, null);
    }
    
    
    public static boolean saveLoginInfo(Context context,int uid,String token,String loginName,String pwdMd5,String headImgUrl){
		
		if (uid > 0) {
			GlobalUtil.setUid(context,uid);
		} else {
			return false;
		}
		if (!TextUtils.isEmpty(token)) {
			GlobalUtil.setToken(context,token);
		}else {
			return false;
		}
		
		if (!TextUtils.isEmpty(loginName)) {
			GlobalUtil.setLoginName(context,loginName);
		}else {
			return false;
		}
		if (!TextUtils.isEmpty(pwdMd5)) {
			GlobalUtil.setPassword(context,pwdMd5);
		}else {
			return false;
		}
		
		PreferenceUtil.putBoolean(context,
				ConstantsValue.IS_LOGIN, true);
		return true;
	}
    
    /**
    * �?��登录,清空数据
    */
   public static void logout(Context context) {
       
		// DbOpenHelper.getInstance(applicationContext).closeDB();

		setPassword(context,null);
		setToken(context,null);
		//setUid(context, (Integer) null);
		PreferenceUtil.putBoolean(context, ConstantsValue.IS_LOGIN,false);
		
		boolean tmp = PreferenceUtil.getBoolean(context, ConstantsValue.IS_LOGIN,false);

   }
   /**
    * 返回信息请求失败处理
    */
   public static boolean checkResponse(Context context, String responseString){
	   Gson gson = new Gson();
	   try {

			ResponseBasic retInfo = gson.fromJson(responseString,
					ResponseBasic.class);

			if (retInfo.getStatus() != 0) {
				Toast.makeText(context, retInfo.getReason(), 0).show();
				return false;
			}
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   
	   return true;
   }
}
