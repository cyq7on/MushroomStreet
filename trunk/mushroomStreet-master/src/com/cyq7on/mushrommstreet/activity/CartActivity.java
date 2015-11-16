package com.cyq7on.mushrommstreet.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.utils.PreferenceUtils;
import com.cyq7on.mushrommstreet.view.TitleBar;


public class CartActivity extends BaseActivity {

	private EditText etAccount;
	private EditText etPsw;
	private Button btnLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_empty);
		initView();
	}
	@Override
	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		titleBar.setTitle("购物车");
		titleBar.setRightButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CartActivity.this,RegisterActivity.class));
				finish();
			}
		});
		
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		//登录
		case R.id.btn_login:
			String accout = etAccount.getText().toString();
			String psw = etPsw.getText().toString();
			//账号密码正确
			if (accout.equals("1") && psw.equals("1")) {
				PreferenceUtils.setBoolean(this, "isLogin", true);
				//存储账号密码
				PreferenceUtils.setString(this, "accout", accout);
				PreferenceUtils.setString(this, "psw", psw);
				finish();
			}else {
				Toast.makeText(this, "用户名或密码错误\n请重新输入", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.btn_global:
			Toast.makeText(this, "全球手机登录", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_forgetpsw:
			Toast.makeText(this, "忘记密码", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_setting:
			Toast.makeText(this, "设置", Toast.LENGTH_LONG).show();
			break;
		case R.id.tv_qq:
			Toast.makeText(this, "QQ登录", Toast.LENGTH_LONG).show();
			break;
		case R.id.tv_wechat:
			Toast.makeText(this, "微信登录", Toast.LENGTH_LONG).show();
			break;
		case R.id.tv_sina:
			Toast.makeText(this, "新浪登录", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}

}
