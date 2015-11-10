package com.cyq7on.mushrommstreet.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.view.TitleBar;


public class HaotaoActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}
	@Override
	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		titleBar.setTitle("haotao");
		titleBar.setRightText("hhhh");
		titleBar.setRightButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(HaotaoActivity.this,LoginActivity.class));
			}
		});
	}

}
