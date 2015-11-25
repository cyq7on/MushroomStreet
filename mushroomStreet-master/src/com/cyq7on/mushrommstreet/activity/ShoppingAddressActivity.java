package com.cyq7on.mushrommstreet.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.bean.ShoppingAddressVo;
import com.cyq7on.mushrommstreet.bean.ShoppingDetailVo;
import com.cyq7on.mushrommstreet.view.TitleBar;
import com.cyq7on.mushroomstreet.AppConfig;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 
* @Title: ShoppingAddressActivity.java 
* @Package com.cyq7on.mushrommstreet.activity 
* @Description: 管理收获地址页面
* @author cyq7on  
* @date 2015-11-24 下午9:39:02 
* @version V1.0
 */
public class ShoppingAddressActivity extends BaseActivity {
	private ListView listView;
	private List<ShoppingAddressVo> dataList = new ArrayList<ShoppingAddressVo>();
	private Mydapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoppingaddress);
		initData();
		initView();
	}

	public void initData() {
		for (int i = 0; i < 3; i++) {
			ShoppingAddressVo vo = new ShoppingAddressVo("白子画" + i, 
					"1838000000" + i, "四川省成都市龙泉区", 
					i + "单元", false);
			dataList.add(vo);
		}
	}
	
	@Override
	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		listView = (ListView) findViewById(R.id.listview);
		titleBar.setTitle("管理收获地址");
		titleBar.setRightText("添加");
		titleBar.setRightVisible();
		titleBar.setRightButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ShoppingAddressActivity.this,
						EditAddresActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		adapter = new Mydapter();
		listView.setAdapter(adapter);
	}


	private class Mydapter extends BaseAdapter {

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
				convertView = LayoutInflater.from(ShoppingAddressActivity.this)
						.inflate(R.layout.item_shoppingaddress, null);
				vh.info = (TextView) convertView.findViewById(R.id.tv_consignee);
				vh.address = (TextView) convertView.findViewById(R.id.tv_address);
				vh.cb = (CheckBox) convertView.findViewById(R.id.cb);
				vh.btnEdit = (Button) convertView.findViewById(R.id.btn_edit);
				vh.btnDelete = (Button) convertView.findViewById(R.id.btn_delete);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			if (dataList != null && dataList.size() > 0) {
				ShoppingAddressVo vo = dataList.get(position);
				vh.info.setText(vo.getName() + " " + vo.getTle());
				vh.address.setText(vo.getAddress() + vo.getAddressDetail());
				vh.btnEdit.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(
								ShoppingAddressActivity.this,
								EditAddresActivity.class);
						startActivity(intent);
					}
				});
				vh.btnDelete.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dataList.remove(position);
						adapter.notifyDataSetChanged();
					}
				});
			}

			return convertView;
		}
	}

	private class ViewHolder{
		TextView info;
		TextView address;
		CheckBox cb;
		Button btnEdit;
		Button btnDelete;
	}


	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ordernow:
			Intent intent = new Intent(this,CheckstandActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
