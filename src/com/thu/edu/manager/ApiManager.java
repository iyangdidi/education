package com.thu.edu.manager;

import com.thu.edu.request.*;

import com.thu.edu.util.*;
import com.thu.edu.manager.*;
import com.thu.edu.adapter.*;


import java.util.List;

import android.content.Context;
import android.os.Handler;

//import com.bigbaby.entity.request.RequestChangeUserInfo;
//import com.bigbaby.entity.request.RequestFeedList;
//import com.bigbaby.entity.request.RequestHuoDongAttend;
//import com.bigbaby.entity.request.RequestHuoDongAttendedUsers;
//import com.bigbaby.entity.request.RequestHuoDongComing;
//import com.bigbaby.entity.request.RequestHuoDongComment;
//import com.bigbaby.entity.request.RequestHuoDongCommentList;
//import com.bigbaby.entity.request.RequestHuoDongDetail;
//import com.bigbaby.entity.request.RequestHuoDongFinished;
//import com.bigbaby.entity.request.RequestHuoDongForward;
//import com.bigbaby.entity.request.RequestHuoDongImageLike;
//import com.bigbaby.entity.request.RequestHuoDongImageList;
//import com.bigbaby.entity.request.RequestHuoDongImageUpload;
//import com.bigbaby.entity.request.RequestHuoDongList;
//import com.bigbaby.entity.request.RequestHuoDongSave;
//import com.bigbaby.entity.request.RequestInvite;
//import com.bigbaby.entity.request.RequestLogout;
//import com.bigbaby.entity.request.RequestUserFollowing;
//import com.bigbaby.entity.request.RequestUserFriendsList;
//import com.bigbaby.entity.request.RequestUserInfoDetail;
//import com.bigbaby.entity.request.RequestUserInfoDetailWithFlag;
//import com.bigbaby.entity.request.RequestUserList;
//import com.bigbaby.entity.request.RequestUserSearch;
//import com.bigbaby.entity.response.ResponseHuoDongAttend;
//import com.bigbaby.global.ConstantsValue;
//import com.bigbaby.model.request.AbstractRequest;
//import com.bigbaby.model.request.RequestChangePassword;
//import com.bigbaby.model.request.RequestGetVerifyCode;
//import com.bigbaby.model.request.RequestHuoDongImageComment;
//import com.bigbaby.model.request.RequestHuoDongImageCommentList;
//import com.bigbaby.model.request.RequestHuoDongInvite;
//import com.bigbaby.model.request.RequestLogin;
//import com.bigbaby.model.request.RequestRegister;
//import com.bigbaby.model.request.RequestResetPassword;
//import com.bigbaby.model.request.RequestSample;
//import com.bigbaby.model.request.RequestSampleBanner;
//import com.bigbaby.util.CommonUtil;
//import com.bigbaby.util.GlobalUtil;

public class ApiManager {
	private static ApiManager apiManager;
	private Context mContext;
	private NetworkRequestManager networkRequestManager;

	public static ApiManager getInstance(Context context) {
		if (apiManager == null) {
			apiManager = new ApiManager(context);
		}
		return apiManager;
	}

	private ApiManager(Context context) {
		mContext = context;
		networkRequestManager = NetworkRequestManager.getInstance(context);
	}
	
	public void setBaseParamters(AbstractRequest baseReq)
	{
		String version = "1";
		baseReq.setVersion(version);
		
		String udid = GlobalUtil.getUUIDString(mContext);
		baseReq.setUdid(udid);
		
		String channel ="test";
		baseReq.setChannel(channel);
	}
//	
//	public void login(Handler handler, int requestId, String loginName,
//			String password, String registrationID) {
//		String url = ConstantsValue.URL_PRE + "/user/login/";
//
//		RequestLogin request = new RequestLogin();
//		request.setName(loginName);
//		request.setPasswd(password);
//		request.setClientPushId(registrationID);
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//
//	}
//
//	public void register(Handler handler, int requestId, String loginName,
//			String password, String verifyCode, String nickname, String registrationID) {
//		String url = ConstantsValue.URL_PRE + "/user/register/";
//
//		RequestRegister request = new RequestRegister();
//		request.setLoginName(loginName);
//		request.setPasswd(password);
//		request.setVerifyCode(verifyCode);
//		request.setNickName(nickname);
//		request.setClientPushId(registrationID);
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//
//	}
//
//	public void changeUserInfo(Handler handler, int requestId, int uid,
//			String loginName, String imgUrl) {
//		String url = ConstantsValue.URL_PRE + "/user/change_user_info/";
//
//		RequestChangeUserInfo request = new RequestChangeUserInfo();
//		request.setNickName(loginName);
//		request.setUid(uid);
//		request.setImgUrl(imgUrl);
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//
//	}
//
//	public void getVerifyCode(Handler handler, int requestId, String phoneNumber) {
//		String url = ConstantsValue.URL_PRE + "/user/get_verify_code/";
//
//		RequestGetVerifyCode request = new RequestGetVerifyCode();
//		request.setPhoneNumber(phoneNumber);
//				
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//
//	}
//
//	public void resetPassword(Handler handler, int requestId,
//			String phoneNumer, String newPwd, String verifyCode) {
//		String url = ConstantsValue.URL_PRE + "/user/reset_password/";
//
//		RequestResetPassword request = new RequestResetPassword();
//		request.setPasswd(newPwd);
//		request.setName(phoneNumer);
//		request.setVerifyCode(verifyCode);
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//
//	}
//
//	public void changePassword(Handler handler, int requestId,
//			String oldPasswd, String newPasswd) {
//		String url = ConstantsValue.URL_PRE + "/user/change_password/";
//
//		RequestChangePassword request = new RequestChangePassword();
//
//		request.setPasswdOld(oldPasswd);
//		request.setPasswdNew(newPasswd);
//
//		String token = GlobalUtil.getToken(mContext);
//		request.setToken(token);
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//
//	public void getSampleData(Handler handler, int requestId, int start,
//			int size) {
//
//		String url = ConstantsValue.URL_PRE + "/getSampleData/";
//
//		RequestSample request = new RequestSample();
//		request.setStart(start);
//		request.setSize(size);
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//
//	}
//
//	public void getHuoDongData(Handler handler, int requestId, int start,
//			int size) {
//
//		String url = ConstantsValue.URL_PRE + "/getHuoDongList";
//
//		RequestHuoDongList request = new RequestHuoDongList();
//		request.setStart(start);
//		request.setSize(size);
//		request.setToken(GlobalUtil.getToken(mContext));
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getSelectionData(Handler handler, int requestId, int start,
//			int size, String listType) {
//
//		String url = ConstantsValue.URL_PRE + "/getHuoDongList";
//
//		RequestHuoDongList request = new RequestHuoDongList();
//		request.setStart(start);
//		request.setSize(size);
//		request.setListType(listType);
//		request.setToken(GlobalUtil.getToken(mContext));
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getHuoDongDetail(Handler handler, int requestId, long huoDongId) {
//
//		String url = ConstantsValue.URL_PRE + "/getHuoDongDetail";
//
//		RequestHuoDongDetail request = new RequestHuoDongDetail();
//		request.setHuoDongId(huoDongId);
//		request.setUid(GlobalUtil.getUid(mContext));
//		request.setToken(GlobalUtil.getToken(mContext));
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getSampleBanner(Handler handler, int requestId, int start,
//			int size) {
//
//		String url = ConstantsValue.URL_PRE + "/getSampleBanner/";
//
//		RequestSampleBanner requestBanner = new RequestSampleBanner();
//		requestBanner.setStart(start);
//		requestBanner.setSize(size);
//
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				requestBanner);
//
//	}
//
//	public void forwardHuoDong(Handler handler, int requestId,
//			RequestHuoDongForward huoDongForward) {
//		String url = ConstantsValue.URL_PRE + "/huoDongFoward";
//		
//		setBaseParamters(huoDongForward);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				huoDongForward);
//	}
//
//	public void saveHuoDong(Handler handler, int requestId,
//			RequestHuoDongSave huoDongSave) {
//
//		String url = ConstantsValue.URL_PRE + "/huoDongSave";
//		
//		setBaseParamters(huoDongSave);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				huoDongSave);
//	}
//
//	public void huoDongAttend(Handler handler, int requestId,
//			RequestHuoDongAttend huoDongAttend) {
//		String url = ConstantsValue.URL_PRE + "/huoDongAttend";
//		
//		setBaseParamters(huoDongAttend);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				huoDongAttend);
//	}
//
//	public void getFinishedData(Handler handler, int requestId, int start,
//			int size) {
//
//		String url = ConstantsValue.URL_PRE + "/getHuoDongFinishedList";
//
//		RequestHuoDongFinished request = new RequestHuoDongFinished();
//		request.setStart(start);
//		request.setSize(size);
//		request.setToken(GlobalUtil.getToken(mContext));
//		request.setUid(GlobalUtil.getUid(mContext));
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getComingData(Handler handler, int requestId, int start,
//			int size) {
//
//		String url = ConstantsValue.URL_PRE + "/getHuoDongComingList";
//
//		RequestHuoDongComing request = new RequestHuoDongComing();
//		request.setStart(start);
//		request.setSize(size);
//		request.setToken(GlobalUtil.getToken(mContext));
//		request.setUid(GlobalUtil.getUid(mContext));
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getHuoDongImageList(Handler handler, int requestId, int start, int size, long huoDongId) {
//		String url = ConstantsValue.URL_PRE + "/getHuoDongImageList";
//
//		RequestHuoDongImageList request = new RequestHuoDongImageList();
//		request.setHuoDongId(huoDongId);
//		request.setStart(start);
//		request.setSize(size);
//		request.setToken(GlobalUtil.getToken(mContext));
//		request.setUid(GlobalUtil.getUid(mContext));
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getHuoDongComments(Handler handler, int requestId, int start, int size, long huoDongId) {
//		String url = ConstantsValue.URL_PRE + "/getHuoDongCommentList";
//
//		RequestHuoDongCommentList request = new RequestHuoDongCommentList();
//		request.setHuoDongId(huoDongId);
//		request.setStart(start);
//		request.setSize(size);
//		request.setToken(GlobalUtil.getToken(mContext));
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	
//	public void getImageComments(Handler handler, int requestId, int start, int size, long imageId) {
//		String url = ConstantsValue.URL_PRE + "/getImageCommentList";
//
//		RequestHuoDongImageCommentList request = new RequestHuoDongImageCommentList();
//		request.setImageId(imageId);
//		request.setStart(start);
//		request.setSize(size);
//		request.setToken(GlobalUtil.getToken(mContext));
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	
//
//	public void createHuoDongComment(Handler handler, int requestId, long huoDongId, long uid,
//			int socre, String comment, String token) {
//		String url = ConstantsValue.URL_PRE + "/huoDongComment";
//
//		RequestHuoDongComment request = new RequestHuoDongComment();
//		request.setHuoDongId(uid);
//		request.setScore(socre);
//		request.setContent(comment);
//		request.setToken(token);
//		request.setUid(uid);
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	
//	public void createHuoDongImageComment(Handler handler, int requestId, long imageId, String token, String content) {
//		String url = ConstantsValue.URL_PRE + "/huoDongImageComment";
//
//		RequestHuoDongImageComment request = new RequestHuoDongImageComment();
//		request.setImageId(imageId);
//		request.setToken(token);
//		request.setContent(content);
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getHuoDongImageAlbum(Handler handler, int requestId, int start, int size, long huoDongId) {
//		String url = ConstantsValue.URL_PRE + "/getHuoDongImageAlbum";
//
//		RequestHuoDongImageList request = new RequestHuoDongImageList();
//		request.setHuoDongId(huoDongId);
//		request.setUid(GlobalUtil.getUid(mContext));
//		request.setStart(start);
//		request.setSize(size);
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	public void getHuoDongImageAlbum(Handler handler, int requestId, int start, int size) {
//		String url = ConstantsValue.URL_PRE + "/getHuoDongImageAlbum";
//
//		RequestHuoDongImageList request = new RequestHuoDongImageList();
//		request.setUid(GlobalUtil.getUid(mContext));
//		request.setStart(start);
//		request.setSize(size);
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	
//	/**
//	 * �?粉丝/关注：获得粉�?关注列表
//	 * 
//	 */
//	public void getFansList(Handler handler,int requestId, int start, int size, int fanType, long uid) {
//		String url = ConstantsValue.URL_PRE;
//		if(fanType == 0)//0：粉�?
//			url += "/getFollowers";
//		else			//1：关�?
//			url += "/getFollowings";
//
//		RequestUserFriendsList request = new RequestUserFriendsList();
//		request.setStart(start);
//		request.setSize(size);
//		request.setUid(uid);
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId, request);
//	}
//	
//	/**
//	 * �?关注：取消关�?
//	 */
//	public void changeUserState(Handler handler,int requestId, long followingId, int changeType){//被关注的用户ID
//		String url = ConstantsValue.URL_PRE;
//		if(changeType == 0)//0：添加关�?
//			url += "/addFollowing";
//		else			   //1：取消关�?
//			url += "/cancelFollowing";
//		
//		RequestUserFollowing request = new RequestUserFollowing();
//		request.setFollowingId(followingId);
//		request.setToken(GlobalUtil.getToken(mContext));
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId, request);
//	}
//	/**
//	 * �?设置：用户详细信息加�?
//	 * @param handler
//	 * @param requestId
//	 * @param uid 用户ID
//	 */
//	public void getUserInfoDetail(Handler handler, int requestId, long uid){
//		String url = ConstantsValue.URL_PRE;
//		url += "/getUserInfoDetail";
//		
//		RequestUserInfoDetail request = new RequestUserInfoDetail();
//		request.setUid(uid);
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId, request);
//	}
//	
//	public void likeImage(Handler handler, int requestId,
//			RequestHuoDongImageLike request, String requstUrl) {
//		String url = ConstantsValue.URL_PRE + "/" + requstUrl;
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId, request);
//	}
//	public void getHuoDongList(Handler handler, int requestId, int start,int size, String listType) {
//
//		String url = ConstantsValue.URL_PRE + "/getHuoDongList";
//
//		RequestHuoDongList request = new RequestHuoDongList();
//		request.setStart(start);
//		request.setSize(size);
//		request.setListType(listType);
//		request.setToken(GlobalUtil.getToken(mContext));
//		request.setUid(GlobalUtil.getUid(mContext));
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getFriendList(Handler handler, int requestId) {
//		
//		String url = ConstantsValue.URL_PRE + "/getFriendList";
//		
//		RequestUserFriendsList request = new RequestUserFriendsList();
//		request.setUid(GlobalUtil.getUid(mContext));
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void huoDongInvite(Handler handler, int requestId, long huoDongId,
//			List<Long> inviteFriend) {
//		
//		String url = ConstantsValue.URL_PRE + "/huoDongInvite";
//		
//		RequestHuoDongInvite request = new RequestHuoDongInvite();
//		request.setHuoDongId(huoDongId);
//		request.setToken(GlobalUtil.getToken(mContext));
//		request.setInviteUids(inviteFriend);
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	
//	public void changeUserInfo(Handler handler, int requestId, String newValue){
//		String url = ConstantsValue.URL_PRE;
//		RequestChangeUserInfo request = new RequestChangeUserInfo();
//		request.setToken(GlobalUtil.getToken(mContext));
//		
//		switch (requestId) {
//		case ConstantsValue.USER_PORTRAIT:
//			url += "/changeHeadImage";
//			request.setImgUrl(newValue);
//			break;
//		case ConstantsValue.USER_NICKNAME:
//			url += "/changeNickname";
//			request.setNickName(newValue);
//			break;
//		case ConstantsValue.USER_GENDER:
//			url += "/changeSex";
//			request.setSex(CommonUtil.getGenderInt(newValue));
//			break;
//		case ConstantsValue.USER_AREA:
//			url += "/changeAddress";
//			request.setAddress(newValue);
//			break;
//		case ConstantsValue.USER_PHONE:
//			url += "/changePhone";
//			String[] values = newValue.split(" ");
//			request.setPhone(values[0]);
//			request.setVerifyCode(values[1]);
//			request.setPassword(CommonUtil.MD5(values[2]));
//			
//			break;
//		case ConstantsValue.BABY_NICKNAME:
//			url += "/changeBabyNickname";
//			request.setBabyNickname(newValue);
//			break;
//		case ConstantsValue.BABY_GENDER:
//			url += "/changeBabySex";
//			request.setBabySex(CommonUtil.getGenderInt(newValue));
//			break;
//		case ConstantsValue.BABY_BIRTH:
//			url += "/changeBabyBirthDate";
//			request.setBabyBirth(CommonUtil.getLongYYYYMMDD(newValue));
//			break;
//		default:
//			break;
//		}
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getAttendHuoDongUserList(Handler handler,
//			int requestId) {
//		
//		String url = ConstantsValue.URL_PRE + "/getAttendHuoDongUserList";
//		
//		RequestUserList request = new RequestUserList();
//		request.setUid(GlobalUtil.getUid(mContext));
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getFeedTopNewData(Handler handler, int requestId,
//			int mNextIndexStart, int requireSize) {
//		
//		String url = ConstantsValue.URL_PRE + "/getFeedList";
//		
////		 int version：该api的版本号
////		 int udid：客户端的唯�?��别号 
////		 string channel：客户端发布的渠道号（主要用于android�?
////		 string token�?客户端登录后保存在本地的token，没登录时为空�?
////		int start:起始位置
////		int size:�?��请求多少条记�?
////		String feedType:消息类型:newest-�?��,huodong-活动相关,mine-我的,msg-个人中心的消息页�?
////		long uid:用户ID。说明：和哪个用户相关的动�?
//		
//		// TODO request.setChannel()
//		// TODO int udid：客户端的唯�?��别号  request.setUdid(GlobalUtil.get)
//		RequestFeedList request = new RequestFeedList(); 
//		request.setStart(mNextIndexStart);
//		request.setSize(requireSize);
//		request.setFeedType("newest");
//		request.setUid(GlobalUtil.getUid(mContext));
//		request.setToken(GlobalUtil.getToken(mContext));
//		
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	/**
//	 * 通用动�?消息请求接口
//	 * feedType:消息类型:newest-�?��,huodong-活动相关,mine-我的,msg-个人中心的消息页�?
//	 * @param handler
//	 * @param requestId
//	 * @param mNextIndexStart
//	 * @param requireSize
//	 */
//	public void getFeedList(Handler handler, int requestId,
//			int mNextIndexStart, int requireSize, String feedType) {
//		
//		String url = ConstantsValue.URL_PRE + "/getFeedList";
//
//		RequestFeedList request = new RequestFeedList(); 
//		request.setStart(mNextIndexStart);
//		request.setSize(requireSize);
//		request.setFeedType(feedType);
//		request.setUid(GlobalUtil.getUid(mContext));
//		request.setToken(GlobalUtil.getToken(mContext));		
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	
//	public void getFeedHuoDongData(Handler handler, int requestId,
//			int mNextIndexStart, int requireSize) {
//		
//		String url = ConstantsValue.URL_PRE + "/getFeedList";
//		
////		 int version：该api的版本号
////		 int udid：客户端的唯�?��别号 
////		 string channel：客户端发布的渠道号（主要用于android�?
////		 string token�?客户端登录后保存在本地的token，没登录时为空�?
////		int start:起始位置
////		int size:�?��请求多少条记�?
////		String feedType:消息类型:newest-�?��,huodong-活动相关,mine-我的,msg-个人中心的消息页�?
////		long uid:用户ID。说明：和哪个用户相关的动�?
//		
//		// TODO request.setChannel()
//		// TODO int udid：客户端的唯�?��别号  request.setUdid(GlobalUtil.get)
//		RequestFeedList request = new RequestFeedList(); 
//		request.setStart(mNextIndexStart);
//		request.setSize(requireSize);
//		request.setFeedType("huodong");
//		request.setUid(GlobalUtil.getUid(mContext));
//		request.setToken(GlobalUtil.getToken(mContext));
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	
//	public void getFeedMineData(Handler handler, int requestId,
//			int mNextIndexStart, int requireSize) {
//		
//		String url = ConstantsValue.URL_PRE + "/getFeedList";
//		
////		 int version：该api的版本号
////		 int udid：客户端的唯�?��别号 
////		 string channel：客户端发布的渠道号（主要用于android�?
////		 string token�?客户端登录后保存在本地的token，没登录时为空�?
////		int start:起始位置
////		int size:�?��请求多少条记�?
////		String feedType:消息类型:newest-�?��,huodong-活动相关,mine-我的,msg-个人中心的消息页�?
////		long uid:用户ID。说明：和哪个用户相关的动�?
//		
//		// TODO request.setChannel()
//		// TODO int udid：客户端的唯�?��别号  request.setUdid(GlobalUtil.get)
//		RequestFeedList request = new RequestFeedList(); 
//		request.setStart(mNextIndexStart);
//		request.setSize(requireSize);
//		request.setFeedType("mine");
//		request.setUid(GlobalUtil.getUid(mContext));
//		request.setToken(GlobalUtil.getToken(mContext));
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	
//	public void searchUserList(Handler handler, int requestId,int uid ,String keywords) {
//		
//		String url = ConstantsValue.URL_PRE + "/searchUserList";
//		
//		RequestUserSearch request = new RequestUserSearch();
//		request.setUid(uid);
//		request.setKeywords(keywords);		
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	
//	public void userInvite(Handler handler, int requestId, long huoDongId) {
//
//		String url = ConstantsValue.URL_PRE + "/userInvite";
//
//		RequestInvite request = new RequestInvite();
//		request.setToken(GlobalUtil.getToken(mContext));
//		request.setHuoDongId(huoDongId);
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	public void logout(Handler handler, int requestId) {
//
//		String url = ConstantsValue.URL_PRE + "/user/logout";
//
//		RequestLogout request = new RequestLogout();
//		request.setToken(GlobalUtil.getToken(mContext));
//		request.setUid(GlobalUtil.getUid(mContext));
//
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//	
//	public void upLoadImage(Handler handler, int requestId, long huoDongId, List<String> imageUrls){
//		String url = ConstantsValue.URL_PRE + "/uploadImage";
//		
//		RequestHuoDongImageUpload request = new RequestHuoDongImageUpload();
//		request.setHuoDongId(huoDongId);
//		request.setImageUrls(imageUrls);
//		request.setToken(GlobalUtil.getToken(mContext));
//		
//		setBaseParamters(request);
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}
//
//	public void getAttendedUserList(Handler handler, int requestId,
//			int mNextIndexStart, int requireSize, long huoDongId, long uid) {
//		String url = ConstantsValue.URL_PRE + "/getAttendedUserList";
//		
//		RequestHuoDongAttendedUsers request = new RequestHuoDongAttendedUsers();
//		request.setHuoDongId(huoDongId);
//		request.setToken(GlobalUtil.getToken(mContext));
//		request.setUid(uid);
//		request.setStart(mNextIndexStart);
//		request.setSize(requireSize);
//		
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId,
//				request);
//	}	
//	
//	public  void getUserInfoDetailWithFlag(Handler handler, int requestId, long followingId){
//		String url = ConstantsValue.URL_PRE + "/getUserInfoDetailWithFlag";
//		
//		RequestUserInfoDetailWithFlag request = new RequestUserInfoDetailWithFlag();
//		setBaseParamters(request);
//		request.setUid(GlobalUtil.getUid(mContext));
//		request.setFollowingId(followingId);
//		
//		networkRequestManager.sendApiRequest(mContext, handler, url, requestId, request);
//	}
}
