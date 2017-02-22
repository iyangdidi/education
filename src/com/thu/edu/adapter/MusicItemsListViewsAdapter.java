package com.thu.edu.adapter;

import java.util.List;
import java.util.Map;

import com.thu.edu.R;
import com.thu.edu.R.id;
import com.thu.edu.R.layout;
import com.thu.edu.util.Constants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MusicItemsListViewsAdapter extends BaseAdapter{
	
	private List<Map<String, Object>> data;
	private LayoutInflater layoutInflater;
	private Context context;
	 
	public MusicItemsListViewsAdapter(Context context, List<Map<String, Object>> data){
		this.data = data;
		this.context = context;
		this.layoutInflater = layoutInflater.from(context);
	} 
	
	class Item_Music{
		public TextView music_name;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item_Music item_Music = null;
		if(convertView == null){
			item_Music = new Item_Music();
			convertView = layoutInflater.inflate(R.layout.item_music, null);
			item_Music.music_name = (TextView)convertView.findViewById(R.id.music_name);
			convertView.setTag(item_Music);
		}else{
			item_Music = (Item_Music)convertView.getTag();
		}
		//°ó¶¨Êý¾Ý
		item_Music.music_name.setText((String)data.get(position).get(Constants.music_name_txt));
		return convertView;
	}	
}
