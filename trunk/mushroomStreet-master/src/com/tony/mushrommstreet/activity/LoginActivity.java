package com.tony.mushrommstreet.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.utils.PreferenceUtils;
import com.tony.mushrommstreet.utils.TitleBar;


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
		titleBar.setTitle("µ«¬º");
		titleBar.setRightText("◊¢≤·");
		titleBar.setRightButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
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
			}
		}
	};
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			String accout = etAccount.getText().toString();
			String psw = etPsw.getText().toString();
			//’À∫≈√‹¬Î’˝»∑
			if (accout.equals("1") && psw.equals("1")) {
				PreferenceUtils.setBoolean(this, "isLogin", true);
				startActivity(new Intent(this,HaotaoActivity.class));
				//¥Ê¥¢’À∫≈√‹¬Î
				PreferenceUtils.setString(this, "accout", accout);
				PreferenceUtils.setString(this, "psw", psw);
				finish();
			}else {
				Toast.makeText(this, "”√ªß√˚ªÚ√‹¬Î¥ÌŒÛ\n«Î÷ÿ–¬ ‰»Î", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.btn_global:
			Toast.makeText(this, "»´«Ú ÷ª˙µ«¬º", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_forgetpsw:
			Toast.makeText(this, "Õ¸º«√‹¬Î", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_setting:
			Toast.makeText(this, "…Ë÷√", Toast.LENGTH_LONG).show();
			break;
		case R.id.tv_qq:
			Toast.makeText(this, "QQµ«¬º", Toast.LENGTH_LONG).show();
			break;
		case R.id.tv_wechat:
			Toast.makeText(this, "Œ¢–≈µ«¬º", Toast.LENGTH_LONG).show();
			break;
		case R.id.tv_sina:
			Toast.makeText(this, "–¬¿Àµ«¬º", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}

}
