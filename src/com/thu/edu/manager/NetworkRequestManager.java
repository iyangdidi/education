package com.thu.edu.manager;

import com.thu.edu.request.*;
import com.thu.edu.util.*;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

//import com.bigbaby.global.ConstantsValue;
//import com.bigbaby.model.request.AbstractRequest;
//import com.bigbaby.util.CommonUtil;
//import com.bigbaby.util.NetworkUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;


public class NetworkRequestManager {
	
	private static NetworkRequestManager networkManager;
	private Context mContext;
	
	public static NetworkRequestManager getInstance(Context context) {
		
		synchronized (NetworkRequestManager.class) {
			if (networkManager == null) {
				networkManager = new NetworkRequestManager(context);
			}
		}
		return networkManager;
	}
	
	private NetworkRequestManager(Context context) {
		mContext = context;
		return;
	}
	
	public void sendApiRequest(Context context, Handler handler, String url,int requestId,AbstractRequest requestObject){
		
		QueuedRequest qr = new QueuedRequest();
		qr.url=url;
		qr.requestId = requestId;
		qr.handler=handler;
		qr.requestObject=requestObject;
		
		if (NetworkUtil.isNetworkAvailable(context)) {
			try{
				_sendApiRequest(context, qr);
				
			}catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	            if (qr.handler != null) {
					qr.handler.sendMessage(qr.handler.obtainMessage(
					ConstantsValue.MSGCODE_HTTP_ERROR, qr));
				}
			}
		}else{
			qr.handler.sendMessage(qr.handler.obtainMessage(
					ConstantsValue.MSGCODE_NO_NETWORK_ERROR, qr));
		}
	}
	
	
	public void _sendApiRequest(Context context, final QueuedRequest qr) throws UnsupportedEncodingException{
		String requestParams = CommonUtil.serializeRequestParams(qr.requestObject);
		StringEntity entity = new StringEntity(requestParams, "utf-8");	
		//entity.setContentEncoding(HTTP.UTF_8);
		AsyncHttpClient httpClient = new AsyncHttpClient();
		httpClient.setTimeout(6000);
												
		entity.setContentType(RequestParams.APPLICATION_JSON);
		httpClient.post(context, qr.url, entity, RequestParams.APPLICATION_JSON, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				qr.responseHttpCode = statusCode;
				qr.responseString=responseString;
				
				try {
					if (qr.handler != null) 
						qr.handler.sendMessage(qr.handler.obtainMessage(
							ConstantsValue.MSGCODE_HTTP_RESPONSE, qr));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable arg3) {
				
				qr.responseHttpCode = statusCode;
				
				if (qr.handler != null) 
					qr.handler.sendMessage(qr.handler.obtainMessage(
							ConstantsValue.MSGCODE_HTTP_ERROR, qr));
				
				Toast.makeText(mContext, "网络不给力哦~", 0).show();
			}
		});
		return;
	}
	
	public static class QueuedRequest {
		
		public int responseHttpCode;
		public int requestId;
		public AbstractRequest requestObject;
		public String url;
		public Handler handler;
		public String responseString;
		public String imageUrl;//只用做上传CDN时记录成功后的图片路�?
	}
	
}
