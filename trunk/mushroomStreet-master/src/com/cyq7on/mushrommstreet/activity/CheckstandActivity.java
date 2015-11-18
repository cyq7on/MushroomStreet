package com.cyq7on.mushrommstreet.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.view.TitleBar;

/**
 * 
* @Title: CheckstandActivity.java 
* @Package com.cyq7on.mushrommstreet.activity 
* @Description: 收银台页面
* @author cyq7on  
* @date 2015-11-18 下午8:08:48 
* @version V1.0
 */
public class CheckstandActivity extends BaseActivity {
	private TextView tvAllPrice;
	private CheckBox cb1,cb2,cb3,cb4,cb5;
	private List<CheckBox> list = new ArrayList<CheckBox>();
	private int which;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkstand);
		initData();
		initView();
	}

	public void initData() {
	}
	
	@Override
	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		tvAllPrice = (TextView) findViewById(R.id.tv_pay);
		tvAllPrice.setText("支付：￥" + getIntent().getStringExtra("allPrice"));
		titleBar.setTitle("收银台");
		cb1 = (CheckBox) findViewById(R.id.cb1);
		cb2 = (CheckBox) findViewById(R.id.cb2);
		cb3 = (CheckBox) findViewById(R.id.cb3);
		cb4 = (CheckBox) findViewById(R.id.cb4);
		cb5 = (CheckBox) findViewById(R.id.cb5);
		cb1.setOnClickListener(new Listener(0));
		cb2.setOnClickListener(new Listener(1));
		cb3.setOnClickListener(new Listener(2));
		cb4.setOnClickListener(new Listener(3));
		cb5.setOnClickListener(new Listener(4));
		list.add(cb1);
		list.add(cb2);
		list.add(cb3);
		list.add(cb4);
		list.add(cb5);
	}

	private class Listener implements OnClickListener {
		private int index;
		
		public  Listener(int i) {
			index = i;
		}


		@Override
		public void onClick(View v) {
			which = index;
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setChecked(false);
			}
			list.get(index).setChecked(true);
		}
		
	}

	public void onClick(View v) {
		switch (v.getId()) {
		//付款
		case R.id.btn_pay:
			break;
		default:
			break;
		}
	}

}
