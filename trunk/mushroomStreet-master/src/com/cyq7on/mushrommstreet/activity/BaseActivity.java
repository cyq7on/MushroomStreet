package com.cyq7on.mushrommstreet.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.cyq7on.mushrommstreet.utils.TitleBar;
import com.example.mushroomstreet.R;


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
