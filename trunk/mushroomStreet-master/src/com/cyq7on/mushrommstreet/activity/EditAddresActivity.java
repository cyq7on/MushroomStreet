package com.cyq7on.mushrommstreet.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.view.TitleBar;

/**
 * 
* @Title: EditAddresActivity.java 
* @Package com.cyq7on.mushrommstreet.activity 
* @Description: 编辑地址页面
* @author cyq7on  
* @date 2015-11-24 下午9:42:16 
* @version V1.0
 */
public class EditAddresActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editaddress);
		initData();
		initView();
	}

	public void initData() {
	}
	
	@Override
	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		titleBar.setTitle("编辑地址");
		titleBar.setRightText("完成");
		titleBar.setRightVisible();
		titleBar.setRightButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}




	public void onClick(View v) {
		switch (v.getId()) {
		default:
			break;
		}
	}

}
