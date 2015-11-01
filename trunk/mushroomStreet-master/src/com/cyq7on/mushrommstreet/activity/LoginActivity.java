package com.cyq7on.mushrommstreet.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.utils.PreferenceUtils;
import com.cyq7on.mushrommstreet.utils.TitleBar;
import com.example.mushroomstreet.R;


public class LoginActivity extends BaseActivity {

	private EditText etAccount;
	private EditText etPsw;
	private Button btnLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}
	@Override
	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		titleBar.setTitle("��¼");
		titleBar.setRightText("ע��");
		titleBar.setLeftButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				startActivity(new Intent(LoginActivity.this,MainActivity.class));
				finish();
			}
		});
		titleBar.setRightButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
				finish();
			}
		});
		
		etAccount = (EditText) findViewById(R.id.et_account);
		etPsw = (EditText) findViewById(R.id.et_password);
		btnLogin = (Button) findViewById(R.id.btn_login);
		etAccount.addTextChangedListener(textWatcher);
		etPsw.addTextChangedListener(textWatcher);
	}
	private TextWatcher textWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			btnLogin.setBackgroundResource(R.drawable.login);
			btnLogin.setClickable(true);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			if ("".equals(s.toString())) {
				btnLogin.setBackgroundResource(R.drawable.login_white_button_disable);
				btnLogin.setClickable(false);
			}
		}
	};
	
	public void onClick(View v) {
		switch (v.getId()) {
		//��¼
		case R.id.btn_login:
			String accout = etAccount.getText().toString();
			String psw = etPsw.getText().toString();
			//�˺�������ȷ
			if (accout.equals("1") && psw.equals("1")) {
				PreferenceUtils.setBoolean(this, "isLogin", true);
				//�洢�˺�����
				PreferenceUtils.setString(this, "accout", accout);
				PreferenceUtils.setString(this, "psw", psw);
				finish();
			}else {
				Toast.makeText(this, "�û������������\n����������", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.btn_global:
			Toast.makeText(this, "ȫ���ֻ���¼", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_forgetpsw:
			Toast.makeText(this, "��������", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_setting:
			Toast.makeText(this, "����", Toast.LENGTH_LONG).show();
			break;
		case R.id.tv_qq:
			Toast.makeText(this, "QQ��¼", Toast.LENGTH_LONG).show();
			break;
		case R.id.tv_wechat:
			Toast.makeText(this, "΢�ŵ�¼", Toast.LENGTH_LONG).show();
			break;
		case R.id.tv_sina:
			Toast.makeText(this, "���˵�¼", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}

}
