package com.thu.edu.service;

import com.thu.edu.util.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

//import com.bigbaby.database.greendao.DaoSession;
//import com.bigbaby.database.greendao.SampleCity;
//import com.bigbaby.database.greendao.SampleCityDao;
//import com.bigbaby.global.ConstantsValue;
//import com.bigbaby.global.CustomsizeApplication;
//import com.bigbaby.model.response.ResponseSampleCityList;
//import com.bigbaby.ui.activity.MainActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

public class InitialService extends Service {
		
	 private Context mContext;
	
	 @Override
     public void onCreate() {
             super.onCreate();
             mContext = this;
             
             //TO DO 进行初始化时的首次后台请求         
             try{
            	 sendInitailRequest();
             }catch (UnsupportedEncodingException e) {
 	            e.printStackTrace();
 			}
             
	 }
	 
	 
	 @Override
     public IBinder onBind(Intent intent) {
             return null;
     }
	 

     @Override
     public void onDestroy() {
             super.onDestroy();
     }
	 
    
     void sendInitailRequest() throws UnsupportedEncodingException{  
    	 
    	 Log.i("InitialService", " sendInitailRequest");
    	 
    	 String url = ConstantsValue.URL_PRE +"/getInitialData/";
 		
 		
// 		StringEntity entity = new StringEntity(null);	
 		
 		new AsyncHttpClient().post(getApplicationContext(),url, null, "application/json", new TextHttpResponseHandler() {

 			@Override
 			public void onSuccess(int statusCode, Header[] headers,
 					String responseString) {

 				try {
 					
 					Gson gson = new Gson();
// 					ResponseSampleCityList responseData = gson.fromJson(
// 							responseString, ResponseSampleCityList.class);
//
// 					ArrayList<SampleCity> tmpAssetItems = new ArrayList<SampleCity>();
//
// 					tmpAssetItems = (ArrayList<SampleCity>) responseData.getSampleCityList();
// 					
// 					saveDataToDB(tmpAssetItems);

 				} catch (Exception e) {
 					e.printStackTrace();
 				}

 			}

 			@Override
 			public void onFailure(int statusCode, Header[] headers,
 					String responseString, Throwable arg3) {
// 				if(null != arg3.getMessage())
// 					Log.v(MainActivity.class.getName(), arg3.getMessage());
 				
 			}

 		});
    	 
     }
     
     
//     void saveDataToDB(ArrayList<SampleCity> dataList)
//     {
//    	 CustomsizeApplication customsizeApplication = CustomsizeApplication.getInstance();
//    	 
//    	 DaoSession daoSession =  customsizeApplication.getDaoSession(this);
//    	 
//    	 SampleCityDao  cityDao = daoSession.getSampleCityDao();   
//    	 
//    	 for(SampleCity city:dataList ){
//    		 cityDao.insert(city);
//    	 }
//    	 
//    	 //test
//    	 
//    	 List<SampleCity> retList = cityDao.loadAll();
//    	 
//    	 Log.i("InitialService", " saveDataToDB");
//     }
	 
}
