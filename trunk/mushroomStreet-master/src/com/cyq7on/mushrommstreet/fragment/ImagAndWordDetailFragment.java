package com.cyq7on.mushrommstreet.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushroomstreet.AppConfig;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 
* @Title: ImagAndWordDetailFragment.java 
* @Package com.cyq7on.mushrommstreet.fragment 
* @Description: 商品详情-图文详情
* @author cyq7on  
* @date 2015-11-14 下午3:57:41 
* @version V1.0
 */
public class ImagAndWordDetailFragment extends Fragment{
	
	private View view;
	private ListView listView;
	private List<String> urlList = new ArrayList<String>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_imagandworddetail, container, 
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
		header.setPadding(10, 10, 10, 10);
		//图片上面的描述，由服务器获取
		header.setText("颜色选择甜美清新的粉色，领子跟袖子拼接白色超好看，\n显肤色 小清新感觉扑面而来\n喜欢得不得了");
		listView.addHeaderView(header);
	}
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return urlList.size();
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
			ImageView iv;
			if (convertView == null) {
				convertView = LayoutInflater.from(getActivity()).
						inflate(R.layout.item_imageandword, null);
				iv = (ImageView) convertView.findViewById(R.id.iv);
				convertView.setTag(iv);
			}else {
				iv = (ImageView) convertView.getTag();
			}
			ImageLoader.getInstance().displayImage(urlList.get(position),
					iv, AppConfig.options);
			return convertView;
		}
		 
	}
	private void initData(){
		//模拟服务器获取数据
		int length = AppConfig.urlImage.length;
		for (int i = 0; i < length / 2; i++) {
			int k = (int) (Math.random() * length);
			urlList.add(AppConfig.urlImage[k]);
		}
	}
	
}
