package com.thu.edu;

import java.util.ArrayList;
import java.util.List;

import com.thu.edu.util.Constants;

public class MusicInfo {
	
	public List<String> yueku_bendiList;
	public List<String> yueku_zaixianList;
	public List<String> duanpian_bendiList;
	public List<String> duanpian_zaixianList;
	
	public List<String> name_yueku_bendiList;
	public List<String> name_yueku_zaixianList;
	public List<String> name_duanpian_bendiList;
	public List<String> name_duanpian_zaixianList;
	
	public List<String> name_instrumentList;
	
	public MusicInfo(){
		yueku_bendiList = new ArrayList<String>();
		yueku_zaixianList = new ArrayList<String>();
		duanpian_bendiList = new ArrayList<String>();
		duanpian_zaixianList = new ArrayList<String>();
		
		name_yueku_bendiList = new ArrayList<String>();
		name_yueku_zaixianList = new ArrayList<String>();
		name_duanpian_bendiList = new ArrayList<String>();
		name_duanpian_zaixianList = new ArrayList<String>();
		
		name_instrumentList = new ArrayList<String>();
	}
	
	public void setAll(){
		this.setDuanpian_bendiList();
		this.setDuanpian_zaixianList();
		this.setYueku_bendiList();
		this.setYueku_zaixianList();
		
		this.setName_duanpian_bendiList();
		this.setName_duanpian_zaixianList();
		this.setName_yueku_bendiList();
		this.setName_yueku_zaixianList();
		
		this.setName_instrumentList();
	}
	public void setName_instrumentList(){
		name_instrumentList.add("instrument_jita");
		name_instrumentList.add("instrument_gangqin");
		name_instrumentList.add("instrument_guzheng");
		name_instrumentList.add("instrument_dizi");
		name_instrumentList.add("instrument_gu");
		name_instrumentList.add("instrument_xiaohao");
	}
	
	public void setYueku_bendiList(){
		
		int n = 5;
		for(int i=1; i<=n; i++){
			String tmp = "yueku_bendi_"+i;
			yueku_bendiList.add(tmp);
		}		
	}
	
	public void setYueku_zaixianList(){
		
		int n = 5;
		for(int i=1; i<=n; i++){
			String tmp = "yueku_zaixian_"+i;
			yueku_zaixianList.add(tmp);
		}		
	}
	
	public void setDuanpian_bendiList(){
		
		int n = 5;
		for(int i=1; i<=n; i++){
			String tmp = "duanpian_bendi_"+i;
			duanpian_bendiList.add(tmp);
		}		
	}
	
	public void setDuanpian_zaixianList(){
		
		int n = 5;
		for(int i=1; i<=n; i++){
			String tmp = "duanpian_zaixian_"+i;
			duanpian_zaixianList.add(tmp);
		}		
	}
	
	public void setName_yueku_bendiList(){
		name_yueku_bendiList.add("从1数到10");
		name_yueku_bendiList.add("大头歌");
		name_yueku_bendiList.add("干净的小手");
		name_yueku_bendiList.add("共产儿童歌");
		name_yueku_bendiList.add("拉拉勾");
	}
	
	public void setName_yueku_zaixianList(){
		name_yueku_zaixianList.add("两只老虎");
		name_yueku_zaixianList.add("鲁冰花");
		name_yueku_zaixianList.add("泥娃娃");
		name_yueku_zaixianList.add("三只小熊");
		name_yueku_zaixianList.add("手指动物歌曲");
	}
	
	public void setName_duanpian_bendiList(){
		name_duanpian_bendiList.add("水果歌");
		name_duanpian_bendiList.add("我的好妈妈");
		name_duanpian_bendiList.add("我的头和我的肩");
		name_duanpian_bendiList.add("小白兔白又白");
		name_duanpian_bendiList.add("小宝宝礼貌歌");
	}
	
	public void setName_duanpian_zaixianList(){
		name_duanpian_zaixianList.add("小手拍拍");
		name_duanpian_zaixianList.add("小兔子乖乖");
		name_duanpian_zaixianList.add("小燕子");
		name_duanpian_zaixianList.add("小猪睡觉");
		name_duanpian_zaixianList.add("找朋友");
	
	}

}
