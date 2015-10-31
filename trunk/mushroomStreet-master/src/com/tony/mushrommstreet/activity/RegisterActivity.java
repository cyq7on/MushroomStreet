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
import com.tony.mushrommstreet.utils.TitleBar;


public class RegisterActivity extends BaseActivity {
	
	private EditText etPhnoeNum;
	private EditText etAuthcode;
	private EditText etPsw;
	private Button btnRigister;
	private String phoneNum;
	private String authcode;
	private String psw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
	}
	@Override
	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		titleBar.setTitle("ע��");
		titleBar.setRightText("ȡ��");
		titleBar.setRightButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		etPhnoeNum = (EditText) findViewById(R.id.et_tele);
		etAuthcode = (EditText) findViewById(R.id.et_authcode);
		etPsw = (EditText) findViewById(R.id.et_password);
		btnRigister = (Button) findViewById(R.id.btn_register);
		etPhnoeNum.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				btnRigister.setBackgroundResource(R.drawable.login);
				btnRigister.setClickable(true);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				phoneNum = s.toString();
				if ("".equals(phoneNum) && "".equals(authcode) && "".equals(psw)) {
					btnRigister.setBackgroundResource(R.drawable.login_white_button_disable);
					btnRigister.setClickable(false);
				}
			}
		});
		etAuthcode.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				authcode = s.toString();
				if ("".equals(phoneNum) && "".equals(authcode) && "".equals(psw)){
					btnRigister.setBackgroundResource(R.drawable.login_white_button_disable);
					btnRigister.setClickable(false);
				}
			}
		});
		etPsw.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				psw = s.toString();
				if ("".equals(phoneNum) && "".equals(authcode) && "".equals(psw)) {
					btnRigister.setBackgroundResource(R.drawable.login_white_button_disable);
					btnRigister.setClickable(false);
				}
			}
		});
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		//ע��
		case R.id.btn_register:
			if ("".equals(authcode)) {
				Toast.makeText(this, "����д��֤��", Toast.LENGTH_LONG).show();
			} else if (psw == null || "".equals(psw) || psw.length() < 6) {
				Toast.makeText(this, "���벻������6λ", Toast.LENGTH_LONG).show();
			} else {
				//���´���ӦΪ�����̨����ע��
				Toast.makeText(this, "ע��ɹ�", Toast.LENGTH_LONG).show();
				startActivity(new Intent(this,LoginActivity.class));
				finish();
			}
			break;
		//��ȡ��֤��	
		case R.id.btn_getcode:
			Toast.makeText(this, "��ȡ��֤��", Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
	}

}
