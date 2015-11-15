package com.cyq7on.mushrommstreet.fragment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cyq7on.mushrommstreet.R;

/**
 * 
* @Title: GoodsParamsFragment.java 
* @Package com.cyq7on.mushrommstreet.fragment 
* @Description: 商品详情-商品参数
* @author cyq7on  
* @date 2015-11-15 下午12:49:33 
* @version V1.0
 */
public class GoodsParamsFragment extends Fragment{
	
	private View view;
	private ListView listView;
	private Map<String, String> params = new HashMap<String, String>();
	private Iterator<String> iterator;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_goodsparams, container, 
				false);
		initData();
		initView();
		return view;
	}
	public ListView getListView() {
		return listView;
	}
	private void initView(){
		listView = (ListView) view.findViewById(R.id.listview);
		listView.setAdapter(new MyAdapter());
		TextView header = new TextView(getActivity());
		header.setTextColor(Color.parseColor("#7A7A7A"));
		header.setBackgroundColor(Color.parseColor("#C9C9C9"));
		header.setPadding(10, 10, 10, 10);
		//参数上面的描述，由服务器获取
		header.setText("宝贝为平铺测量，由于测量的差异，会有1-3cm误差，属于正常现象");
		listView.addHeaderView(header);
	}
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return params.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh;
			if (convertView == null) {
				vh = new ViewHolder();
				convertView = LayoutInflater.from(getActivity()).
						inflate(R.layout.item_goodsparams, null);
				vh.key = (TextView) convertView.findViewById(R.id.tv_key);
				vh.value = (TextView) convertView.findViewById(R.id.tv_value);
				convertView.setTag(vh);
			}else {
				vh = (ViewHolder) convertView.getTag();
			}
			if (iterator.hasNext()) {
				String key = iterator.next();
				vh.key.setText(key);
				vh.value.setText(params.get(key));
			}
			return convertView;
		}
		 
	}
	private class ViewHolder {
		TextView key;
		TextView value;
	}
	private void initData(){
		//模拟服务器获取数据
		params.put("尺码", "均码");
		params.put("衣长", "77");
		params.put("袖长", "51");
		params.put("胸围", "65");
		params.put("肩宽", "122");
		params.put("领型", "连帽");
		params.put("材质", "棉");
		iterator = params.keySet().iterator();
	}
	
}
