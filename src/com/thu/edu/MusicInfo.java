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
		name_yueku_bendiList.add("��1����10");
		name_yueku_bendiList.add("��ͷ��");
		name_yueku_bendiList.add("�ɾ���С��");
		name_yueku_bendiList.add("������ͯ��");
		name_yueku_bendiList.add("������");
	}
	
	public void setName_yueku_zaixianList(){
		name_yueku_zaixianList.add("��ֻ�ϻ�");
		name_yueku_zaixianList.add("³����");
		name_yueku_zaixianList.add("������");
		name_yueku_zaixianList.add("��ֻС��");
		name_yueku_zaixianList.add("��ָ�������");
	}
	
	public void setName_duanpian_bendiList(){
		name_duanpian_bendiList.add("ˮ����");
		name_duanpian_bendiList.add("�ҵĺ�����");
		name_duanpian_bendiList.add("�ҵ�ͷ���ҵļ�");
		name_duanpian_bendiList.add("С���ð��ְ�");
		name_duanpian_bendiList.add("С������ò��");
	}
	
	public void setName_duanpian_zaixianList(){
		name_duanpian_zaixianList.add("С������");
		name_duanpian_zaixianList.add("С���ӹԹ�");
		name_duanpian_zaixianList.add("С����");
		name_duanpian_zaixianList.add("С��˯��");
		name_duanpian_zaixianList.add("������");
	
	}

}
