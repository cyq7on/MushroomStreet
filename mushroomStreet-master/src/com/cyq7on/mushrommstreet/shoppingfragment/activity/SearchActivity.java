package com.cyq7on.mushrommstreet.shoppingfragment.activity;


import android.os.Bundle;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.activity.BaseActivity;
import com.cyq7on.mushrommstreet.view.TitleBar;


public class SearchActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initView();
	}
	@Override
	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		titleBar.setTitle("test");
	}

}
