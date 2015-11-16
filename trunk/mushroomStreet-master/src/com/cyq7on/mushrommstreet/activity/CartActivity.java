package com.cyq7on.mushrommstreet.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.bean.ShoppingDetailVo;
import com.cyq7on.mushrommstreet.view.TitleBar;
import com.cyq7on.mushroomstreet.AppConfig;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CartActivity extends BaseActivity {
	private ListView listView;
	private List<ShoppingDetailVo> dataList = 
			new ArrayList<ShoppingDetailVo>();
	private CartAdapter cartAdapter;
	private CheckBox cbSelectAll;
	private TextView tvAllPrice,tvSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_cart_empty);
		setContentView(R.layout.activity_cart);
		listView = (ListView) findViewById(R.id.listview);
		initData();
		initView();
	}

	public void initData() {
		// 模拟服务器获取数据
		ShoppingDetailVo vo;
		int length = (int) (AppConfig.urlImage.length * Math.random());
		if (length == 0) {
			length = 8;
		}
		for (int i = 0; i < length / 2; i++) {
			int k = (int) (Math.random() * length);
			vo = new ShoppingDetailVo(AppConfig.urlImage[k], "气质时尚羊毛外套" + k, k
					* 100 + "", k * 150 + "" ,"蓝",k + "","格子铺" + i);
			dataList.add(vo);
		}
	}

	private class CartAdapter extends BaseAdapter {
		private CheckBox cb;
		
		public CheckBox getCheckBox() {
			return cb;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return dataList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder vh;
			if (convertView == null) {
				vh = new ViewHolder();
				convertView = LayoutInflater.from(CartActivity.this)
						.inflate(R.layout.item_cart, null);
				vh.iv = (ImageView) convertView.findViewById(R.id.iv);
				vh.tvName = (TextView) convertView.
						findViewById(R.id.tv_name);
				vh.tvColor = (TextView) convertView.
						findViewById(R.id.tv_color);
				vh.tvSize = (TextView) convertView.
						findViewById(R.id.tv_size);
				vh.tvPriceOld = (TextView) convertView.
						findViewById(R.id.tv_price_old);
				vh.tvPirceNow = (TextView) convertView.
						findViewById(R.id.tv_price_now);
				vh.tvCount = (TextView) convertView.
						findViewById(R.id.tv_count);
				vh.cb = (CheckBox) convertView.
						findViewById(R.id.cb_select);
				cb = vh.cb;
				vh.btnSub = (Button) convertView.
						findViewById(R.id.btn_sub);
				vh.btnPlus = (Button) convertView.
						findViewById(R.id.btn_plus);
				vh.btnDelete = (Button) convertView.
						findViewById(R.id.btn_delete);
				vh.btnStore = (Button) convertView.
						findViewById(R.id.btn_store);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			if (dataList != null && dataList.size() > 0) {
				ShoppingDetailVo vo = dataList.get(position);
				ImageLoader.getInstance().displayImage(vo.getImageUrl(), 
						vh.iv, AppConfig.options);
				vh.tvName.setText(vo.getName());
				vh.tvColor.setText("颜色：" + vo.getColor());
				vh.tvSize.setText("尺码：" + vo.getSize());
				vh.tvPriceOld.setText("¥" + vo.getPriceOld());
				vh.tvPirceNow.setText("¥" + vo.getPriceNow());
				vh.tvCount.setText("1");
				vh.btnSub.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						int count = Integer.parseInt(vh.
								tvCount.getText().toString());
						if (count > 1) {
							count --;
							vh.tvCount.setText(count + "");
						}
					}
				});
				vh.btnPlus.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						int count = Integer.parseInt(vh.
								tvCount.getText().toString());
						count ++;
						vh.tvCount.setText(count + "");
					}
				});
				vh.btnDelete.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dataList.remove(position);
						cartAdapter.notifyDataSetChanged();
					}
				});
				vh.btnStore.setText(vo.getStoreName());
				vh.btnStore.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
					}
				});
			}
			
			return convertView;
		}
	}
	
	private class ViewHolder implements OnClickListener{
		ImageView iv;
		TextView tvName;
		TextView tvColor;
		TextView tvSize;
		TextView tvPriceOld;
		TextView tvPirceNow;
		TextView tvCount;
		CheckBox cb;
		Button btnSub;
		Button btnPlus;
		Button btnDelete;
		Button btnStore;
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	}

	@Override
	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		cbSelectAll = (CheckBox) findViewById(R.id.cb_selectall);
		tvAllPrice = (TextView) findViewById(R.id.tv_allprice);
		tvSave = (TextView) findViewById(R.id.tv_save);
		titleBar.setTitle("购物车("+ dataList.size() + ")");
		cartAdapter = new CartAdapter();
		listView.setAdapter(cartAdapter);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cb_selectall:
			CheckBox cb;
			View view;
			if (cbSelectAll.isChecked()) {
				for (int i = 0; i < dataList.size(); i++) {
					view = listView.getChildAt(i);
					cb = (CheckBox) view.findViewById(R.id.cb_select);
					cb.setChecked(false);
				}
			}else {
				for (int i = 0; i < dataList.size(); i++) {
					view = listView.getChildAt(i);
					cb = (CheckBox) view.findViewById(R.id.cb_select);
					cb.setChecked(true);
				}
			}
			break;
		case R.id.btn_cal:
			break;
		default:
			break;
		}
	}

}
