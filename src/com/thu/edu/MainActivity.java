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
		
		//����Ĭ������̲�����
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

	    //���������ҳĬ��Ϊ���ֿ�
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
	 * ���������Ŀ������
	 * @param flag ��ʾ���ĸ�btn����Ľ��
	 * @param type ��ʾ�Ǳ��ػ�����������
	 * @return
	 */
	public List<Map<String, Object>> getMusics(int head, int type){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		if(head == Constants.Head_yueku && type == Constants.bendi){//�ֿ�-����
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
	

	//����ֿⰴť
	public void yuekuClick(View view){
		Toast.makeText(this, "�ֿ�", 2000).show();
		
		this.head = Constants.Head_yueku;
		this.bendi_fold = false;
		this.zaixian_fold = false;
		
		this.getMusicList(head, bendi_fold, zaixian_fold);
		
	}	
	
	//�����Ƭ���ɰ�ť
	public void duanpianClick(View view){
		Toast.makeText(this, "��ƪ����", 2000).show();
		
		this.head = Constants.Head_duanpian;
		this.bendi_fold = false;
		this.zaixian_fold = false;
		
		this.getMusicList(head, bendi_fold, zaixian_fold);
		
	}
	public void yueqiClick(View view){
		Toast.makeText(this, "������ʶ", 2000).show();	
		
		scroll.removeAllViews();
		
		LinearLayout linearLayout = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.list_instrument_items, null);
		linearLayout = getInstruments(linearLayout);	
		
		scroll.addView(linearLayout);
	}
	public LinearLayout getInstruments(LinearLayout view){

		//��ʼ����
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

				// ��Assets����
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

			Toast.makeText(context, "���Ŵ���", 1000).show();
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
			
			//��������Ŷ�Ӧ����Ƶ	
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
		java.util.Random random=new java.util.Random();// ���������
		int index=random.nextInt(6);//����0-5�������
		
		//���ѡһ�����ֲ���
		String music_name = musicInfo.name_instrumentList.get(index)+".mp3";
		myPlay(music_name);
		trainCurrent = index+1;
	}
	public void youxiClick(View view){
		Toast.makeText(this, "��Ϸ", 2000).show();
		
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
				title.setText("����ץС��");
				content.setText("�龰����    1�������֣��׶������ʦѧС���Σ��ꡰɺ���������ҳ�������Ӥ�׶�һ��ѧ��С���ζ�����    2�������ʦ�����ݣ�Ӥ�׶���һ��׷׽���ݡ�   ������Ϸ���������ˡ�    ���š����㡱���֣���ʦ����Ӥ�׶�����������ҳ�����һ������������Ӥ�׶����������    ����ͣ�������㡱�����ˣ��롰С�㡱���ֳ������棬��Ϸ���Ρ�   �ġ�����ʳ��    ��С�㡱�ڻ�����ɢ��ʳ��ǹ������ҳ�������С��������ȥ�ң��ҵ�ʳ�ϴ�ֺ��ٳԡ�");
				break;
			case 3:
				title.setText("��ӥץС��");
				content.setText("��Ϸ��ʼʱǰ�ȷֽ�ɫ����һ�˵�ĸ����һ�˵���ӥ������ĵ�С����С��������ĸ����ǣ���½��ų�һ�ӣ���ӥվ��ĸ�����棬��׽С�����ơ���Ϸ��ʼʱ����ӥ�������ϼ�������ĸ������С��������״��ĸ��������������С������ӥ�ٽ���ת��Ȧȥ׽С������С������ĸ��������������");
				break;
			case 2:
				title.setText("���Ĵ���");
				content.setText("���˻�ʮ��Χ��ԲȦ���£�����һ���û�����һС�����������һ�˱��Ŵ�һ����ۻ��ģ����ӡ��ڰ�������ܷ������������壩������ʱ���˿�ʼ���δ���������ֹͣΪֹ����ʱ����˭���У�������λǰ����˭����̨���ݽ�Ŀ�����ǳ��衢���衢˵Ц������ش����⡢���ա���ֽ���涨���µȣ���żȻ��������������У������˿�ͨ����ȭ��������ʽ�������ߡ�");
				break;

			default:
				break;
			}			
			
		}
		
	}
	public void shitingClick(View view){
		Toast.makeText(this, "����ѵ��", 2000).show();
		
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
				describe.setText("���������ȵļ��ڣ��Һ����贩�Ŷ���Ͷ̿㡣");
				break;
			case 2:
				imgage.setImageResource(R.drawable.shiting_item_2);
				describe.setText("������ҵ�������Ӿ���ں���Ҫ������Ļ�����Ӿ��һ��ܰ����˶������˶��������Ҹ���ǿ׳��");
				play=false;
				myPlay("shiting_2.mp3");
				break;
			case 3:
				imgage.setImageResource(R.drawable.shiting_item_3);
				describe.setText("����Ӿ�Һ��ȣ�������ϳ�ѩ�⣻ѩ������岻�ã��Ҳ��ܳԺܶࡣ");	
				play=false;
				myPlay("shiting_3.mp3");
				break;

			case 4:
				imgage.setImageResource(R.drawable.shiting_item_4);
				describe.setText("�ְֿ������һؼң������ҹ��úܿ��ġ�");
				play=false;
				myPlay("shiting_4.mp3");
				break;
			case 5:
				imgage.setImageResource(R.drawable.shiting_item_5);
				describe.setText("�ص��ң���մ����ˣ������ر�󣻽��ţ������˴��ꣻ���쾭������������������");
				play=false;
				myPlay("shiting_5.mp3");
				break;
			case 6:
				imgage.setImageResource(R.drawable.shiting_item_6);
				describe.setText("�����߳����˲ʺ磻�ʺ���������ɫ���ǳ���������������������ˣ��Ұ����졣");
				break;

			default:
				break;
			}
			
		}
		
	}
	
	public void dingzhiClick(View view){
		Toast.makeText(this, "����", 2000).show();
	}
	
	public void xiaolianOnClick(View view){		
		final View view2 = LayoutInflater.from(this).inflate(R.layout.bigface, null);  
		FaceDialog dialog = new FaceDialog(context);
		dialog.create().show();
	}
		
	public void searchOnClick(View view){
		Toast.makeText(this, "����", 2000).show();
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
		//��һ��ȡ��һ��
		bendi_fold = !bendi_fold;			
		getMusicList(head, bendi_fold, zaixian_fold);
	}
	public void zaixianFoldOnClick(View view){		
		//��һ��ȡ��һ��
		zaixian_fold = !zaixian_fold;			
		getMusicList(head, bendi_fold, zaixian_fold);
	}	
	
	public void showSearchList(){
		//�Ƴ�ԭ�ؼ�
		scroll.removeAllViews();
		
		//��Ӳ����ļ�
		View viewTmp = LayoutInflater.from(this).inflate(R.layout.list_search_content, null);
		scroll.addView(viewTmp);
		
		//����list����		
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
		//�Ƴ�ԭ�ؼ�
		scroll.removeAllViews();
		
		//��Ӳ����ļ�
		View viewTmp = LayoutInflater.from(this).inflate(R.layout.list_music_items, null);
		scroll.addView(viewTmp);
		
		//����list����
		List<Map<String, Object>> list = null;
		//���ñ���ListView
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
		
		//��������ListView
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
			
			//��ò��Ÿ�������
			String music_name =  null;
			if(head == Constants.Head_yueku && isBendi){//�ֿ�-����
				music_name = musicInfo.yueku_bendiList.get(position);				
			}else if(head == Constants.Head_yueku && !isBendi){//�ֿ�-����
				music_name = musicInfo.yueku_zaixianList.get(position);
			}else if(head == Constants.Head_duanpian && isBendi){//��ƪ-����
				music_name = musicInfo.duanpian_bendiList.get(position);
			}else if(head == Constants.Head_duanpian && !isBendi){//��ƪ-����
				music_name = musicInfo.duanpian_zaixianList.get(position);
			}
			music_name = music_name+".mp3";
			
			
			try {

				if (play) {
					mediaPlayer.stop();
					play = false;
				} else {

					// ��Assets����
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

				Toast.makeText(context, "���Ŵ���", 1000).show();
			}

		}
		
//	    /** 
//	     * ��������MP3 
//	     * @return 
//	     */  
//	    public MediaPlayer createLocalMp3(int music_id){  
//	        /** 
//	         * ������Ƶ�ļ��ķ����� 
//	         * 1��������ԴĿ¼���ļ���MediaPlayer.create(MainActivity.this,R.raw.beatit);//����res/raw ��ԴĿ¼�µ�MP3�ļ� 
//	         * 2:����sdcard�����ļ���mediaPlayer=new MediaPlayer(); 
//	         *   mediaPlayer.setDataSource("/sdcard/beatit.mp3");//ǰ����sdcard��Ҫ�ȵ�����Ƶ�ļ� 
//	         */  
//	        MediaPlayer mp=MediaPlayer.create(MainActivity.this, music_id);  
//	        mp.stop();  
//	        return mp;  
//	    }  
		
	}
}
