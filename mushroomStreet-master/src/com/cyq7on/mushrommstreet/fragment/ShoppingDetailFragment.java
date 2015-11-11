package com.cyq7on.mushrommstreet.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.activity.ShoppingdetailActivity;
import com.cyq7on.mushrommstreet.bean.ShoppingDetailVo;
import com.cyq7on.mushroomstreet.AppConfig;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ShoppingDetailFragment extends Fragment{
	
	private View view;
	private GridView gridView;
	private List<ShoppingDetailVo> dataList = new ArrayList<ShoppingDetailVo>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_shoppingdetail, container, 
				false);
		initData();
		initView();
		return view;
	}

	private void initView(){
		gridView = (GridView) view.findViewById(R.id.gv);
		gridView.setAdapter(new GridViewAdapter());
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), arg2 + "",
						Toast.LENGTH_LONG).show();
			}
		});
	}
	private class GridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			GridViewItem gi;
			if (convertView == null) {
				gi = new GridViewItem();
				convertView = LayoutInflater.from(getActivity()).
						inflate(R.layout.item_shopping, parent, false);
				gi.iv = (ImageView) convertView.findViewById(R.id.iv);
				gi.tvName = (TextView) convertView.findViewById(R.id.tv_name);
				gi.tvPriceNow = (TextView) 
						convertView.findViewById(R.id.tv_price_now);
				gi.tvPriceOld = (TextView) 
						convertView.findViewById(R.id.tv_price_old);
				convertView.setTag(gi);
			}else {
				gi = (GridViewItem) convertView.getTag();
			}
			if (dataList == null || dataList.size() == 0) {
				return convertView;
			}
			ShoppingDetailVo vo = dataList.get(position);
			ImageLoader.getInstance().displayImage(vo.getImageUrl(),
					gi.iv, AppConfig.options);
			gi.tvName.setText(vo.getName());
			gi.tvPriceNow.setText("¥" + vo.getPriceNow());
			gi.tvPriceOld.setText("¥" + vo.getPriceOld());
			return convertView;
		}
		
	}
	private class GridViewItem {
		ImageView iv;
		TextView tvName;
		TextView tvPriceNow;
		TextView tvPriceOld;
	}
	private void initData(){
		//模拟服务器获取数据
		ShoppingDetailVo vo;
		int length = AppConfig.urlImage.length;
		for (int i = 0; i < length; i++) {
			int k = (int) (Math.random() * length);
			vo = new ShoppingDetailVo(AppConfig.urlImage[k], 
					"气质时尚羊毛外套"+k, k * 100 + "", k * 150 + "");
			dataList.add(vo);
		}
	}
	
}
