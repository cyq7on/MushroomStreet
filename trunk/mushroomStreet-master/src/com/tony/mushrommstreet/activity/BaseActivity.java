package com.tony.mushrommstreet.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.utils.TitleBar;


/**项目activity的基类**/
public class BaseActivity extends Activity {

	public TitleBar titleBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.activity_base);
//		initView();
		initData();
	}
	public void initView() {
//		titleBar = (TitleBar) findViewById(R.id.title_bar);
		titleBar.setLeftButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	};
	
	public void initData() {
		
	};
}
