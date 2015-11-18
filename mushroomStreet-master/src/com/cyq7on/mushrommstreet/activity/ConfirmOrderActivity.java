package com.cyq7on.mushrommstreet.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.SparseIntArray;
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
/**
 * 
* @Title: ConfirmOrderActivity.java 
* @Package com.cyq7on.mushrommstreet.activity 
* @Description: 确认订单页面
* @author cyq7on  
* @date 2015-11-18 下午3:38:55 
* @version V1.0
 */
public class ConfirmOrderActivity extends BaseActivity {
	private ListView listView;
	private List<ShoppingDetailVo> dataList = 
			new ArrayList<ShoppingDetailVo>();
	private CartAdapter cartAdapter;
	private TextView tvAllPrice,tvSave;
	private Button btnCal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmorder);
//		initData();
//		initView();
	}

	public void initData() {
		// 获取购物车商品
		dataList.addAll(AppConfig.goodsList);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppConfig.goodsList.clear();
		AppConfig.goodsList.addAll(dataList);
	}

	private class CartAdapter extends BaseAdapter {
		private SparseIntArray array = new SparseIntArray();
		
		public CartAdapter() {
			for (int i = 0; i < dataList.size(); i++) {
				array.put(i, dataList.get(i).getCount());
			}System.out.println(array);
		}
		
		public SparseIntArray getSparseArray() {
			return array;
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
				convertView = LayoutInflater.from(ConfirmOrderActivity.this)
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
						int count = Integer.parseInt(
								info.substring(4, info.length() - 1));
						info = tvAllPrice.getText().
								toString().substring(4);
						float price = Float.parseFloat(
								dataList.get(position).getPriceNow());
						float allPrice = Float.parseFloat(info);
						if (isChecked) {
							count ++;
							btnCal.setText("去结算(" + count + ")");
							tvAllPrice.setText("总价：￥" + (allPrice + 
									count * price));
							info = tvSave.getText().toString().
									substring(6);
							float save = Float.parseFloat(info);
							float sub = Float.parseFloat(
									dataList.get(position).getPriceOld()) -
									Float.parseFloat(
									dataList.get(position).getPriceNow());
							tvSave.setText("共为您节省￥" + (save + count * sub));
						} else {
							count --;
							btnCal.setText("去结算(" + count  + ")");
							tvAllPrice.setText("总价：￥" + (allPrice - 
									count * price));
							info = tvSave.getText().toString().
									substring(6);
							float save = Float.parseFloat(info);
							float sub = Float.parseFloat(
									dataList.get(position).getPriceOld()) -
									Float.parseFloat(
									dataList.get(position).getPriceNow());
							tvSave.setText("共为您节省￥" + (save - count * sub));
						}
					}
				});
				vh.tvName.setText(vo.getName());
				vh.tvColor.setText("颜色：" + vo.getColor());
				vh.tvSize.setText("尺码：" + vo.getSize());
				vh.tvPriceOld.setText("¥" + vo.getPriceOld());
				vh.tvPirceNow.setText("¥" + vo.getPriceNow());
				vh.tvCount.setText(array.get(position) + "");
				vh.btnSub.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						int count = Integer.parseInt(vh.
								tvCount.getText().toString());
						if (count > 1) {
							count --;
							vh.tvCount.setText(count + "");
							array.put(position, count);
							if (vh.cb.isChecked()) {
								String info = tvAllPrice.getText().
										toString().substring(4);
								float price = Float.parseFloat(
										dataList.get(position).getPriceNow());
								float allPrice = Float.parseFloat(info);
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
						array.put(position, count);
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
						String info = btnCal.getText().toString();
						int count = Integer.parseInt(
								info.substring(4, info.length() - 1));
						if (count >= 1) {
							btnCal.setText("去结算(" + (count - 1) + ")");
						}
						titleBar.setTitle("购物车("+ dataList.size() + ")");
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
		tvAllPrice = (TextView) findViewById(R.id.tv_allprice);
		tvSave = (TextView) findViewById(R.id.tv_save);
		btnCal = (Button) findViewById(R.id.btn_cal);
		titleBar.setTitle("确认订单");
		cartAdapter = new CartAdapter();
		listView.setAdapter(cartAdapter);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ordernow:
			break;
		default:
			break;
		}
	}

}
