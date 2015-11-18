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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
	private List<ShoppingDetailVo> dataList = new ArrayList<ShoppingDetailVo>();
	private Orderdapter oederAdapter;
	private TextView tvAllPrice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmorder);
		initData();
		initView();
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

	private class Orderdapter extends BaseAdapter {

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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder vh;
			if (convertView == null) {
				vh = new ViewHolder();
				convertView = LayoutInflater.from(ConfirmOrderActivity.this)
						.inflate(R.layout.item_confirmorder, null);
				vh.iv = (ImageView) convertView.findViewById(R.id.iv);
				vh.tvName = (TextView) convertView.findViewById(R.id.tv_name);
				vh.tvColor = (TextView) convertView.findViewById(R.id.tv_color);
				vh.tvSize = (TextView) convertView.findViewById(R.id.tv_size);
				vh.tvPriceOld = (TextView) convertView
						.findViewById(R.id.tv_price_old);
				vh.tvPirceNow = (TextView) convertView
						.findViewById(R.id.tv_price_now);
				vh.tvCount = (TextView) convertView.findViewById(R.id.tv_count);
				vh.btnSub = (Button) convertView.findViewById(R.id.btn_sub);
				vh.btnPlus = (Button) convertView.findViewById(R.id.btn_plus);
				vh.btnChat = (Button) convertView.findViewById(R.id.btn_chat);
				vh.tvFreight = (TextView) convertView
						.findViewById(R.id.tv_freight);
				vh.tvPirceAll = (TextView) convertView
						.findViewById(R.id.tv_priceall);
				vh.tvStore = (TextView) convertView.findViewById(R.id.tv_store);
				vh.et = (EditText) convertView.findViewById(R.id.et);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			if (dataList != null && dataList.size() > 0) {
				ShoppingDetailVo vo = dataList.get(position);
				ImageLoader.getInstance().displayImage(vo.getImageUrl(), vh.iv,
						AppConfig.options);
				vh.tvName.setText(vo.getName());
				vh.tvColor.setText("颜色：" + vo.getColor());
				vh.tvSize.setText("尺码：" + vo.getSize());
				vh.tvPriceOld.setText("¥" + vo.getPriceOld());
				vh.tvPirceNow.setText("¥" + vo.getPriceNow());
				if ("0".equals(vo.getFreight())) {
					vh.tvFreight.setText("全国包邮");
				} else {
					vh.tvFreight.setText(vo.getFreight());
				}
				vh.tvPirceAll.setText("¥" + vo.getPriceAll());
				vh.tvCount.setText(vo.getCount() + "");
				vh.btnSub.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						int count = Integer.parseInt(vh.tvCount.getText()
								.toString());
						if (count > 1) {
							count--;
							vh.tvCount.setText(count + "");
							String info = tvAllPrice.getText().toString()
									.substring(4);
							float price = Float.parseFloat(dataList.get(
									position).getPriceNow());
							float allPrice = Float.parseFloat(info);
							tvAllPrice.setText("合计：￥" + (allPrice - price));
						}
					}
				});
				vh.btnPlus.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						int count = Integer.parseInt(vh.tvCount.getText()
								.toString());
						count++;
						vh.tvCount.setText(count + "");
						String info = tvAllPrice.getText().toString()
								.substring(4);
						float price = Float.parseFloat(dataList.get(position)
								.getPriceNow());
						float allPrice = Float.parseFloat(info);
						if (allPrice == 0) {
							tvAllPrice.setText("合计：￥" + count * price);
						} else {
							tvAllPrice.setText("合计：￥" + (allPrice + price));
						}

					}
				});
				vh.btnChat.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});
				vh.tvStore.setText(vo.getStoreName());
			}

			return convertView;
		}
	}

	private class ViewHolder implements OnClickListener {
		ImageView iv;
		TextView tvName;
		TextView tvColor;
		TextView tvSize;
		TextView tvPriceOld;
		TextView tvPirceNow;
		TextView tvFreight;
		TextView tvPirceAll;
		TextView tvCount;
		TextView tvStore;
		Button btnSub;
		Button btnPlus;
		Button btnChat;
		EditText et;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		tvAllPrice = (TextView) findViewById(R.id.tv_allprice);
		listView = (ListView) findViewById(R.id.listview);
		titleBar.setTitle("确认订单");
		oederAdapter = new Orderdapter();
		listView.setAdapter(oederAdapter);
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
