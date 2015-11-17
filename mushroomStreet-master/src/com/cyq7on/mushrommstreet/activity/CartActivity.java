package com.cyq7on.mushrommstreet.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
	private Button btnCal;

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
		private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		public CartAdapter() {
			for (int i = 0; i < dataList.size(); i++) {
				map.put(i, 1);
			}
		}
		
		public Map<Integer, Integer> getMap() {
			return map;
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
				vh.cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						String info = btnCal.getText().toString();
//						int count = info.substring(4, info.length() - 2);
						System.out.println(info.substring(4, info.length() - 2));
					}
				});
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
							map.put(position, count);
							if (vh.cb.isChecked()) {
								String info = tvAllPrice.getText().
										toString().substring(4);
								float price = Float.parseFloat(
										dataList.get(position).getPriceNow());
								float allPrice = Float.parseFloat(info);
//								if (count == 2) {
//									allPrice = 1 * price;
//								} else {
//									allPrice = Float.parseFloat(info);
//								}
								tvAllPrice.setText("总价：￥" + (allPrice - price));
								info = tvSave.getText().toString().
										substring(6);
								float save = Float.parseFloat(info);
								float sub = Float.parseFloat(
										dataList.get(position).getPriceOld()) -
										Float.parseFloat(
										dataList.get(position).getPriceNow());
								tvSave.setText("共为您节省￥" + (save - sub));
							}
							
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
						map.put(position, count);
						if (vh.cb.isChecked()) {
							String info = tvAllPrice.getText().
									toString().substring(4);
							float price = Float.parseFloat(
									dataList.get(position).getPriceNow());
							float allPrice = Float.parseFloat(info);
							if (allPrice == 0) {
								tvAllPrice.setText("总价：￥" + count * price);
							}else {
								tvAllPrice.setText("总价：￥" + (allPrice + price));
							}
							info = tvSave.getText().toString().
									substring(6);
							float save = Float.parseFloat(info);
							float sub = Float.parseFloat(
									dataList.get(position).getPriceOld()) -
									Float.parseFloat(
									dataList.get(position).getPriceNow());
							if (save == 0) {
								tvSave.setText("共为您节省￥" + sub * count);
							}else {
								tvSave.setText("共为您节省￥" + (save + sub));
							}
						}
						
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
		btnCal = (Button) findViewById(R.id.btn_cal);
		titleBar.setTitle("购物车("+ dataList.size() + ")");
		cartAdapter = new CartAdapter();
		listView.setAdapter(cartAdapter);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cb_selectall:
			CheckBox cb;
			LinearLayout item;
			RelativeLayout rl;
			float allPrice = 0;
			float save = 0;
			int count;
			Map<Integer, Integer> map;
			float sub;
			if (cbSelectAll.isChecked()) {
				for (int i = 0; i < dataList.size(); i++) {
					item = (LinearLayout) listView.getChildAt(i);
					rl = (RelativeLayout) item.getChildAt(2);
					cb = (CheckBox) rl.getChildAt(0);
					cb.setChecked(true);
					map = cartAdapter.getMap();
					System.out.println(map);
					count = map.get(i);
					allPrice += count * (Float.parseFloat(
							dataList.get(i).getPriceNow()));
					sub = Float.parseFloat(dataList.get(i).getPriceOld()) - 
							Float.parseFloat(dataList.get(i).getPriceNow());
					save += count * sub;
				}
				tvAllPrice.setText("总价：￥" + allPrice);
				tvSave.setText("共为您节省￥" + save);
				btnCal.setText("去结算(" + dataList.size() + ")");
			}else {
				for (int i = 0; i < dataList.size(); i++) {
					item = (LinearLayout) listView.getChildAt(i);
					rl = (RelativeLayout) item.getChildAt(2);
					cb = (CheckBox) rl.getChildAt(0);
					cb.setChecked(false);
				}
				tvAllPrice.setText("总价：￥" + 0.00);
				tvSave.setText("共为您节省￥" + 0.00);
				btnCal.setText("去结算(0)");
			}
			break;
		case R.id.btn_cal:
			break;
		default:
			break;
		}
	}

}
