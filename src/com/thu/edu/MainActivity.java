package com.thu.edu;

import com.thu.edu.util.*;
import com.thu.edu.manager.*;
import com.thu.edu.R.string;
import com.thu.edu.adapter.*;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thu.edu.adapter.HeaderBtnViewPagerAdapter;
import com.thu.edu.adapter.MusicItemsListViewsAdapter;
import com.thu.edu.util.Constants;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.R.bool;
import android.R.integer;
import android.app.Activity;
import android.app.Dialog;
import android.app.LauncherActivity.ListItem;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.support.v4.view.ViewPager;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActivityHeaderBase {
	
	Context context;
	
	LinearLayout content;
	ScrollView scroll;
	EditText search_box;
	
	public boolean bendi_fold = false;
	public boolean zaixian_fold = false;
	public int head = Constants.Head_yueku;
	
	private LayoutInflater layoutInflater;

	public MusicInfo musicInfo = new MusicInfo();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.list_music_items);
		
		
		
		musicInfo.setAll();
		
		
		context = this;
		this.assetManager = context.getAssets();
		
		this.layoutInflater = layoutInflater.from(context);
		
		setContentView(R.layout.activity_main);	
		initHeaderBtn();		
		initViews();	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void initHeaderBtn(){
		ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
		View view1 = LayoutInflater.from(this).inflate(R.layout.header_btn_1, null);
		View view2 = LayoutInflater.from(this).inflate(R.layout.header_btn_2, null);
		
		ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		
		HeaderBtnViewPagerAdapter adapter = new HeaderBtnViewPagerAdapter();
		adapter.setViews(views);
		viewPager.setAdapter(adapter);
	}
	
	public void initViews(){		
		content = (LinearLayout)this.findViewById(R.id.content);
		scroll = (ScrollView)this.findViewById(R.id.scroll);
		
		//设置默认软键盘不弹出
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

	    //设置软件首页默认为：乐库
		yuekuClick(scroll);
	}
	
	public void setListViewHeightBasedOnChildren(ListView listView) {
		if(listView == null) return;
		
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
	        return;
	    }
		
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
	        View listItem = listAdapter.getView(i, null, listView);
	        listItem.measure(0, 0);
	        totalHeight += listItem.getMeasuredHeight();
	    }
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	}
	
	/**
	 * 获得音乐条目的内容
	 * @param flag 表示是哪个btn点击的结果
	 * @param type 表示是本地还是在线数据
	 * @return
	 */
	public List<Map<String, Object>> getMusics(int head, int type){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		if(head == Constants.Head_yueku && type == Constants.bendi){//乐库-本地
			int n = 5;
			for(int i=0; i<n; i++){
				Map<String, Object> mapTmp = new HashMap<String, Object>();
				mapTmp.put(Constants.music_name_txt, musicInfo.name_yueku_bendiList.get(i));
				list.add(mapTmp);
			}			
		}else if(head == Constants.Head_yueku && type == Constants.zaixian){
			int n = 5;
			for(int i=0; i<n; i++){
				Map<String, Object> mapTmp = new HashMap<String, Object>();
				mapTmp.put(Constants.music_name_txt, musicInfo.name_yueku_zaixianList.get(i));
				list.add(mapTmp);
			}	
		}else if(head == Constants.Head_duanpian && type == Constants.bendi){
			int n = 5;
			for(int i=0; i<n; i++){
				Map<String, Object> mapTmp = new HashMap<String, Object>();
				mapTmp.put(Constants.music_name_txt, musicInfo.name_duanpian_bendiList.get(i));
				list.add(mapTmp);
			}				
		}else if(head == Constants.Head_duanpian && type == Constants.zaixian) {
			int n = 5;
			for(int i=0; i<n; i++){
				Map<String, Object> mapTmp = new HashMap<String, Object>();
				mapTmp.put(Constants.music_name_txt, musicInfo.name_duanpian_zaixianList.get(i));
				list.add(mapTmp);
			}	
		}
		
		return list;
	}
	

	//点击乐库按钮
	public void yuekuClick(View view){
		Toast.makeText(this, "乐库", 2000).show();
		
		this.head = Constants.Head_yueku;
		this.bendi_fold = false;
		this.zaixian_fold = false;
		
		this.getMusicList(head, bendi_fold, zaixian_fold);
		
	}	
	
	//点击短片旋律按钮
	public void duanpianClick(View view){
		Toast.makeText(this, "短篇旋律", 2000).show();
		
		this.head = Constants.Head_duanpian;
		this.bendi_fold = false;
		this.zaixian_fold = false;
		
		this.getMusicList(head, bendi_fold, zaixian_fold);
		
	}
	public void yueqiClick(View view){
		Toast.makeText(this, "乐器辨识", 2000).show();	
		
		scroll.removeAllViews();
		
		LinearLayout linearLayout = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.list_instrument_items, null);
		linearLayout = getInstruments(linearLayout);	
		
		scroll.addView(linearLayout);
	}
	public LinearLayout getInstruments(LinearLayout view){

		//初始界面
		LinearLayout item1 = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.item_instrument, null);
		view.addView(item1);
		View item2 = LayoutInflater.from(this).inflate(R.layout.item_instrument_2, null);
		view.addView(item2);
		item1.findViewById(R.id.instrument_1).setOnClickListener(new Yueqi_Item_OnClickListener(this,Constants.Instrument_Jita));
		item1.findViewById(R.id.instrument_2).setOnClickListener(new Yueqi_Item_OnClickListener(this,Constants.Instrument_Gangqin));
		item1.findViewById(R.id.instrument_3).setOnClickListener(new Yueqi_Item_OnClickListener(this,Constants.Instrument_Guzheng));
		item2.findViewById(R.id.instrument_4).setOnClickListener(new Yueqi_Item_OnClickListener(this,Constants.Instrument_Dizi));
		item2.findViewById(R.id.instrument_5).setOnClickListener(new Yueqi_Item_OnClickListener(this,Constants.Instrument_Gu));
		item2.findViewById(R.id.instrument_6).setOnClickListener(new Yueqi_Item_OnClickListener(this,Constants.Instrument_Xiaohao));
				
		LinearLayout train_btn = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.instrument_train_btn, null);
		train_btn.setOnClickListener(new Train_Btn_OnClickListener(this, scroll));
		view.addView(train_btn);
		
		return view;
	}
	public void myPlay(String music_name){
		try {

			if (play) {
				mediaPlayer.stop();
				play = false;
			} else {

				// 从Assets播放
				mediaPlayer = new MediaPlayer();
				AssetFileDescriptor fileDescriptor = assetManager
						.openFd(music_name);
				mediaPlayer.setDataSource(
						fileDescriptor.getFileDescriptor(),
						fileDescriptor.getStartOffset(),
						fileDescriptor.getLength());
				fileDescriptor.close();
				mediaPlayer.prepare();
				mediaPlayer.start();

				play = true;
			}
		} catch (Exception e) {
			e.printStackTrace();

			Toast.makeText(context, "播放错误", 1000).show();
		}

	}
	class Yueqi_Item_OnClickListener implements OnClickListener{
		Context context;
		int instrument;
		public Yueqi_Item_OnClickListener(Context context, int instrument){
			this.context = context;
			this.instrument = instrument;
		}
		@Override
		public void onClick(View arg0) {
			
			//点击，播放对应的音频	
			if(instrument == Constants.Instrument_Jita){
				myPlay("instrument_jita.mp3");
				return;
			}
			if(instrument == Constants.Instrument_Gangqin){
				myPlay("instrument_gangqin.mp3");
				return;
			}
			if(instrument == Constants.Instrument_Guzheng){
				myPlay("instrument_guzheng.mp3");
				return;
			}
			if(instrument == Constants.Instrument_Dizi){
				myPlay("instrument_dizi.mp3");
				return;
			}
			if(instrument == Constants.Instrument_Gu){
				myPlay("instrument_gu.mp3");
				return;
			}
			if(instrument == Constants.Instrument_Xiaohao){
				myPlay("instrument_xiaohao.mp3");
				return;
			}
		}		
	}
	class Train_Btn_OnClickListener implements OnClickListener{
		
		Context context;
		ScrollView scroll;
		
		public Train_Btn_OnClickListener(Context context, ScrollView scroll){
			this.context = context;
			this.scroll = scroll;
		}

		@Override
		public void onClick(View v) {			
			scroll.removeAllViews();
			View view = LayoutInflater.from(context).inflate(R.layout.instrument_train_content, scroll);
			view.findViewById(R.id.train_1).setOnClickListener(new TrainInstrumentOnClickListener(Constants.Instrument_Jita));
			view.findViewById(R.id.train_2).setOnClickListener(new TrainInstrumentOnClickListener(Constants.Instrument_Gangqin));
			view.findViewById(R.id.train_3).setOnClickListener(new TrainInstrumentOnClickListener(Constants.Instrument_Guzheng));
			view.findViewById(R.id.train_4).setOnClickListener(new TrainInstrumentOnClickListener(Constants.Instrument_Dizi));
			view.findViewById(R.id.train_5).setOnClickListener(new TrainInstrumentOnClickListener(Constants.Instrument_Gu));
			view.findViewById(R.id.train_6).setOnClickListener(new TrainInstrumentOnClickListener(Constants.Instrument_Xiaohao));
			
		}
	}
	public int trainCurrent;
	class TrainInstrumentOnClickListener implements OnClickListener{
		
		int instrumentCliked;
		
		public TrainInstrumentOnClickListener(int instrumentCliked){
			this.instrumentCliked = instrumentCliked;
		}

		@Override
		public void onClick(View v) {
			if(trainCurrent == instrumentCliked){
				play = true;
				myPlay("yueqi_right.mp3");		
				play = false;
				myPlay("yueqi_right.mp3");	
			}else{
				play = true;
				myPlay("yueqi_wrong.mp3");
				play = false;
				myPlay("yueqi_wrong.mp3");
			}
		}
		
	}
	public void playOnClick(View view){
		java.util.Random random=new java.util.Random();// 定义随机类
		int index=random.nextInt(6);//生成0-5的随机数
		
		//随机选一个音乐播放
		String music_name = musicInfo.name_instrumentList.get(index)+".mp3";
		myPlay(music_name);
		trainCurrent = index+1;
	}
	public void youxiClick(View view){
		Toast.makeText(this, "游戏", 2000).show();
		
		scroll.removeAllViews();
		
		LinearLayout linearLayout = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.list_game_items, null);
		linearLayout = getGames(linearLayout);	
		
		scroll.addView(linearLayout);
				
	}
	public LinearLayout getGames(LinearLayout view){
		int n=3;
//		for(int i=0; i<n; i++){
//			View item = LayoutInflater.from(this).inflate(R.layout.item_game, view);
//			item.setOnClickListener(new Game_item_OnClickListener(this, scroll));
//		}	
		View item1 = LayoutInflater.from(this).inflate(R.layout.item_game, null);
		item1.setOnClickListener(new Game_item_OnClickListener(this, scroll, 1));
		View item2 = LayoutInflater.from(this).inflate(R.layout.item_game_2, null);
		item2.setOnClickListener(new Game_item_OnClickListener(this, scroll, 2));
		View item3 = LayoutInflater.from(this).inflate(R.layout.item_game_3, null);
		item3.setOnClickListener(new Game_item_OnClickListener(this, scroll, 3));
		
		view.addView(item1);
		view.addView(item2);
		view.addView(item3);
		return view;
	}
	class Game_item_OnClickListener implements OnClickListener{
		
		Context context;
		ScrollView scroll;
		int gameId;
		
		public Game_item_OnClickListener(Context context, ScrollView scroll, int gameId) {
			this.context = context;
			this.scroll = scroll;
			this.gameId = gameId;
		}

		@Override
		public void onClick(View v) {
			scroll.removeAllViews();
			View view = LayoutInflater.from(context).inflate(R.layout.game_detail, scroll);
			TextView title = (TextView)view.findViewById(R.id.game_detail_title);
			TextView content = (TextView)view.findViewById(R.id.game_detail_content);
			switch (gameId) {
			case 1:
				title.setText("鲨鱼抓小鱼");
				content.setText("情景扮演    1、放音乐，幼儿跟随教师学小鱼游，钻“珊瑚”，（家长尽量让婴幼儿一起学做小鱼游动作）    2、配班老师吹泡泡，婴幼儿们一起追捉泡泡。   三、游戏“鲨鱼来了”    播放“鲨鱼”音乐，教师引导婴幼儿快躲起来，家长则找一个跳袋，提醒婴幼儿躲在跳袋里。    音乐停，“鲨鱼”游走了，请“小鱼”们又出来游玩，游戏二次。   四、分享食物    “小鱼”在活动室里分散找食物（糖果），家长尽量让小朋友自身去找，找到食物、洗手后再吃。");
				break;
			case 3:
				title.setText("老鹰抓小鸡");
				content.setText("游戏开始时前先分角色，即一人当母鸡，一人当老鹰，其余的当小鸡。小鸡依次在母鸡后牵着衣襟排成一队，老鹰站在母鸡对面，做捉小鸡姿势。游戏开始时，老鹰叫着做赶鸡运作。母鸡身后的小鸡做惊恐状，母鸡极力保护身后的小鸡。老鹰再叫着转着圈去捉小鸡，众小鸡则在母鸡身后左躲右闪。");
				break;
			case 2:
				title.setText("击鼓传花");
				content.setText("数人或几十人围成圆圈坐下，其中一人拿花（或一小物件）；另有一人背着大家或蒙眼击鼓（桌子、黑板或其他能发出声音的物体），鼓响时众人开始依次传花，至鼓停止为止。此时花在谁手中（或其座位前），谁就上台表演节目（多是唱歌、跳舞、说笑话；或回答问题、猜谜、按纸条规定行事等）；偶然如果花在两人手中，则两人可通过猜拳或其它方式决定负者。");
				break;

			default:
				break;
			}			
			
		}
		
	}
	public void shitingClick(View view){
		Toast.makeText(this, "视听训练", 2000).show();
		
		scroll.removeAllViews();
		
		LinearLayout linearLayout = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.list_shiting_items, null);
		linearLayout = getShiting(linearLayout);	
		
		scroll.addView(linearLayout);
	}
	public LinearLayout getShiting(LinearLayout view){
		int n=1;
		for(int i=0; i<n; i++){
			View item = LayoutInflater.from(this).inflate(R.layout.item_shiting, view);
			item.setOnClickListener(new Shiting_item_OnClickListener(this, scroll, 1));
		}	
		
		return view;
	}
	
//	public int shiting_item_index;
//	class NextOnClickListener implements OnClickListener{
//		
//		Context context;
//		ScrollView scroll;
//		int detailId;
//		
//		public Shiting_item_OnClickListener(Context context, ScrollView scroll, int detailId) {
//			this.context = context;
//			this.scroll = scroll;
//			this.detailId = detailId;
//		}
//		
//
//		@Override
//		public void onClick(View v) {
//			
//			
//		}
//		
//	}
	
	//TODO
	class Shiting_item_OnClickListener implements OnClickListener{
		
		Context context;
		ScrollView scroll;
		int detailId;
		
		public Shiting_item_OnClickListener(Context context, ScrollView scroll, int detailId) {
			this.context = context;
			this.scroll = scroll;
			this.detailId = detailId;
		}

		@Override
		public void onClick(View v) {
			scroll.removeAllViews();
			
			View view = LayoutInflater.from(context).inflate(R.layout.shiting_detail, scroll);	
			ImageView imgage = (ImageView)view.findViewById(R.id.shiting_pic);
			TextView describe = (TextView)view.findViewById(R.id.shiting_desc);
			TextView next = (TextView)view.findViewById(R.id.shiting_next);
			
			if(detailId == 5)
				detailId = 1;
			
			next.setOnClickListener(new Shiting_item_OnClickListener(context, scroll, detailId+1));
			
			switch (detailId) {
			case 1:
				imgage.setImageResource(R.drawable.shiting_item_1);
				describe.setText("夏天是炎热的季节；我和妈妈穿着短袖和短裤。");
				break;
			case 2:
				imgage.setImageResource(R.drawable.shiting_item_2);
				describe.setText("妈妈带我到海边游泳；在海边要听妈妈的话；游泳是一项很棒的运动；做运动可以让我更加强壮。");
				play=false;
				myPlay("shiting_2.mp3");
				break;
			case 3:
				imgage.setImageResource(R.drawable.shiting_item_3);
				describe.setText("游完泳我很热，想喝饮料吃雪糕；雪糕对身体不好，我不能吃很多。");	
				play=false;
				myPlay("shiting_3.mp3");
				break;

			case 4:
				imgage.setImageResource(R.drawable.shiting_item_4);
				describe.setText("爸爸开车带我回家；今天我过得很开心。");
				play=false;
				myPlay("shiting_4.mp3");
				break;
			case 5:
				imgage.setImageResource(R.drawable.shiting_item_5);
				describe.setText("回到家，天空打雷了，雷声特别大；接着，下起了大雨；夏天经常出现雷阵雨天气。");
				play=false;
				myPlay("shiting_5.mp3");
				break;
			case 6:
				imgage.setImageResource(R.drawable.shiting_item_6);
				describe.setText("雨后天边出现了彩虹；彩虹有七种颜色，非常美丽；夏天就是这样迷人，我爱夏天。");
				break;

			default:
				break;
			}
			
		}
		
	}
	
	public void dingzhiClick(View view){
		Toast.makeText(this, "定制", 2000).show();
	}
	
	public void xiaolianOnClick(View view){		
		final View view2 = LayoutInflater.from(this).inflate(R.layout.bigface, null);  
		FaceDialog dialog = new FaceDialog(context);
		dialog.create().show();
	}
		
	public void searchOnClick(View view){
		Toast.makeText(this, "检索", 2000).show();
		TextView search_btn = (TextView)this.findViewById(R.id.search_btn);
		EditText search_box = (EditText)this.findViewById(R.id.search_box);
		
		String search_content = search_box.getText().toString();
		
		search_btn.setOnClickListener(new Search_btn_OnClickListener(search_content));
	}
	
	
	List<String> search_list;
	class Search_btn_OnClickListener implements OnClickListener{
		
		String search_content;
		
		public Search_btn_OnClickListener(String search_content){
			this.search_content = search_content;
		}

		@Override
		public void onClick(View v) {
			int len;
			search_list = new ArrayList<String>();
			
			len = musicInfo.name_yueku_bendiList.size();
			for(int i=0; i<len; i++){
				String tmp = musicInfo.name_yueku_bendiList.get(i);
				if(tmp.matches(".*"+search_content+".*")){
					search_list.add(tmp);
				}
			}
			
			len = musicInfo.name_yueku_zaixianList.size();
			for(int i=0; i<len; i++){
				String tmp = musicInfo.name_yueku_zaixianList.get(i);
				if(tmp.matches(".*"+search_content+".*")){
					search_list.add(tmp);
				}
			}
			
			len = musicInfo.name_duanpian_bendiList.size();
			for(int i=0; i<len; i++){
				String tmp = musicInfo.name_duanpian_bendiList.get(i);
				if(tmp.matches(".*"+search_content+".*")){
					search_list.add(tmp);
				}
			}
			
			len = musicInfo.name_duanpian_zaixianList.size();
			for(int i=0; i<len; i++){
				String tmp = musicInfo.name_duanpian_zaixianList.get(i);
				if(tmp.matches(".*"+search_content+".*")){
					search_list.add(tmp);
				}
			}
			
			showSearchList();
			
		}
		
	}
	

	public void bendiFoldOnClick(View view){		
		//点一次取反一次
		bendi_fold = !bendi_fold;			
		getMusicList(head, bendi_fold, zaixian_fold);
	}
	public void zaixianFoldOnClick(View view){		
		//点一次取反一次
		zaixian_fold = !zaixian_fold;			
		getMusicList(head, bendi_fold, zaixian_fold);
	}	
	
	public void showSearchList(){
		//移除原控件
		scroll.removeAllViews();
		
		//添加布局文件
		View viewTmp = LayoutInflater.from(this).inflate(R.layout.list_search_content, null);
		scroll.addView(viewTmp);
		
		//加载list内容		
		ListView list_search_content = (ListView)viewTmp.findViewById(R.id.list_search_content);	
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(int i=0; i<search_list.size(); i++){			
			Map<String, Object> mapTmp = new HashMap<String, Object>();			
			mapTmp.put(Constants.music_name_txt, search_list.get(i));
			list.add(mapTmp);
		}
			
		list_search_content.setAdapter(new MusicItemsListViewsAdapter(this, list));
		list_search_content.setOnItemClickListener(new SearchMusicOnClickListener());
		this.setListViewHeightBasedOnChildren(list_search_content);
	}
	
	class SearchMusicOnClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View v, int position,
				long arg3) {
			
			TextView  music_name = (TextView)v.findViewById(R.id.music_name);
			String name = music_name.getText().toString();
			
			List<String> tmpList;
			tmpList = musicInfo.name_yueku_bendiList;
			for(int i=0; i<tmpList.size(); i++){
				if(name == tmpList.get(i)){
					myPlay(musicInfo.yueku_bendiList.get(i)+".mp3");
				}
			}
			
			tmpList = musicInfo.name_yueku_zaixianList;
			for(int i=0; i<tmpList.size(); i++){
				if(name == tmpList.get(i)){
					myPlay(musicInfo.yueku_zaixianList.get(i)+".mp3");
				}
			}
			
			tmpList = musicInfo.name_duanpian_bendiList;
			for(int i=0; i<tmpList.size(); i++){
				if(name == tmpList.get(i)){
					myPlay(musicInfo.duanpian_bendiList.get(i)+".mp3");
				}
			}
			
			tmpList = musicInfo.name_duanpian_zaixianList;
			for(int i=0; i<tmpList.size(); i++){
				if(name == tmpList.get(i)){
					myPlay(musicInfo.duanpian_bendiList.get(i)+".mp3");
				}
			}
			
		}		
	}
	
	public void getMusicList(int head, boolean bendi_fold, boolean zaixian_fold){
		//移除原控件
		scroll.removeAllViews();
		
		//添加布局文件
		View viewTmp = LayoutInflater.from(this).inflate(R.layout.list_music_items, null);
		scroll.addView(viewTmp);
		
		//加载list内容
		List<Map<String, Object>> list = null;
		//设置本地ListView
		ListView list_bendi_music = (ListView)viewTmp.findViewById(R.id.list_bendi_music);	
		if(bendi_fold){
			list = new ArrayList<Map<String,Object>>();
		}			
		else{
			list = getMusics(head,Constants.bendi);
		}			
		list_bendi_music.setAdapter(new MusicItemsListViewsAdapter(this, list));
		list_bendi_music.setOnItemClickListener(new MusicOnItemClickListener(context, true));
		this.setListViewHeightBasedOnChildren(list_bendi_music);
		
		//设置在线ListView
		ListView list_zaixian_music = (ListView)viewTmp.findViewById(R.id.list_zaixian_music);
		if(zaixian_fold){
			list = new ArrayList<Map<String,Object>>();
		}			
		else{
			list = getMusics(head,Constants.zaixian);
		}
		list_zaixian_music.setAdapter(new MusicItemsListViewsAdapter(this, list));	
		list_zaixian_music.setOnItemClickListener(new MusicOnItemClickListener(context, false));
		this.setListViewHeightBasedOnChildren(list_zaixian_music);
	}
		
	//TODO
	public LinearLayout getMusicItems(int head, int type){
		LinearLayout layout = new LinearLayout(context);
		int n = 5;
		for(int i=0; i<n; i++){
			View view = LayoutInflater.from(this).inflate(R.layout.list_music_items, layout);
		}
		
		return layout;
	}
	
	private MediaPlayer mediaPlayer;
	private boolean play = false;
	AssetManager assetManager;
	class MusicOnItemClickListener implements OnItemClickListener{
		
		
		boolean isBendi;
		public MusicOnItemClickListener(Context context, boolean isBendi){
			//this.assetManager = context.getAssets();
			this.isBendi = isBendi;
		}
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {	
			
			//获得播放歌曲名称
			String music_name =  null;
			if(head == Constants.Head_yueku && isBendi){//乐库-本地
				music_name = musicInfo.yueku_bendiList.get(position);				
			}else if(head == Constants.Head_yueku && !isBendi){//乐库-在线
				music_name = musicInfo.yueku_zaixianList.get(position);
			}else if(head == Constants.Head_duanpian && isBendi){//短篇-本地
				music_name = musicInfo.duanpian_bendiList.get(position);
			}else if(head == Constants.Head_duanpian && !isBendi){//短篇-在线
				music_name = musicInfo.duanpian_zaixianList.get(position);
			}
			music_name = music_name+".mp3";
			
			
			try {

				if (play) {
					mediaPlayer.stop();
					play = false;
				} else {

					// 从Assets播放
					mediaPlayer = new MediaPlayer();
					AssetFileDescriptor fileDescriptor = assetManager
							.openFd(music_name);
					mediaPlayer.setDataSource(
							fileDescriptor.getFileDescriptor(),
							fileDescriptor.getStartOffset(),
							fileDescriptor.getLength());
					fileDescriptor.close();
					mediaPlayer.prepare();
					mediaPlayer.start();

					play = true;
				}
			} catch (Exception e) {
				e.printStackTrace();

				Toast.makeText(context, "播放错误", 1000).show();
			}

		}
		
//	    /** 
//	     * 创建本地MP3 
//	     * @return 
//	     */  
//	    public MediaPlayer createLocalMp3(int music_id){  
//	        /** 
//	         * 创建音频文件的方法： 
//	         * 1、播放资源目录的文件：MediaPlayer.create(MainActivity.this,R.raw.beatit);//播放res/raw 资源目录下的MP3文件 
//	         * 2:播放sdcard卡的文件：mediaPlayer=new MediaPlayer(); 
//	         *   mediaPlayer.setDataSource("/sdcard/beatit.mp3");//前提是sdcard卡要先导入音频文件 
//	         */  
//	        MediaPlayer mp=MediaPlayer.create(MainActivity.this, music_id);  
//	        mp.stop();  
//	        return mp;  
//	    }  
		
	}
}
