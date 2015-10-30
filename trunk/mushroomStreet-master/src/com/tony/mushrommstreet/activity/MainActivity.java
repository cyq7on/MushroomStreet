package com.tony.mushrommstreet.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.fragment.BuyFragment;
import com.tony.mushrommstreet.fragment.ChatFragment;
import com.tony.mushrommstreet.fragment.MineFragment;
import com.tony.mushrommstreet.fragment.PhotoFragment;
import com.tony.mushrommstreet.fragment.ShoppingFragment;
import com.tony.mushrommstreet.utils.PreferenceUtils;
import com.tony.mushroomstreet.AppConfig;


/** 用于显示主页面 */
public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener {

	private RadioGroup radioGroup;
	/**
	 * 1表示ShoppingFragment 2表示BuyFragment 3表示PhotoFragment
	 * 4表示ChatFragment 5表示MineFragment
	 * */
	private int whichFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		AppConfig.initConfig(this);
		initView();
		initListener();
	}

	public void initView() {
		radioGroup = (RadioGroup) findViewById(R.id.bottom_menu);
		int flag = getIntent().getIntExtra("whichFragment", -1);
		switch (flag) {
		case -1:
			radioGroup.check(R.id.shopping);
			redirectTo(ShoppingFragment.getInstance());
			whichFragment = 1;
			break;
		case 4:
			radioGroup.check(R.id.message);
			redirectTo(ChatFragment.getInstance());
			whichFragment = 4;
			break;
		case 5:
			radioGroup.check(R.id.mine);
			redirectTo(MineFragment.getInstance());
			whichFragment = 5;
			break;

		default:
			break;
		}
	}
	

	public void initListener() {
		radioGroup.setOnCheckedChangeListener(this);
	}
	
	public void redirectTo(Fragment fragment) {
		FragmentTransaction beginTransaction = getSupportFragmentManager()
				.beginTransaction();
		beginTransaction.replace(R.id.fragment_container, fragment)
				.commitAllowingStateLoss();
	}

	public void switchContent(Fragment from, Fragment to) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		if (!to.isAdded()) { // 先判断是否被add过
			transaction.hide(from).add(R.id.fragment_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
		} else {
			transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
		}
	}
	
	
	/**单纯使用redirectTo切换fragment会导致fragment切换时候数据丢失
	 * 因为fragment被replace掉了，所以改用switchContent切换fragment
	 * 9.14
	 * switchContent会造成actionBar title异常，暂时换回redirecTo
	 * 9.19
	 * actionBarTitle 问题解决
	 * 9.26
	 * */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		Intent intent;
		switch (checkedId) {
		case R.id.shopping:
			switch (whichFragment) {
			case 2:
				switchContent(BuyFragment.getInstance(),
						ShoppingFragment.getInstance());
				break;
			case 3:
				switchContent(ChatFragment.getInstance(),
						ShoppingFragment.getInstance());
				break;
			case 4:
				switchContent(ChatFragment.getInstance(),
						ShoppingFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						ShoppingFragment.getInstance());
				break;
			}
			whichFragment = 1;
			break;
		case R.id.buy:
			switch (whichFragment) {
			case 1:
				switchContent(ShoppingFragment.getInstance(),
						BuyFragment.getInstance());
				break;
			case 3:
				switchContent(PhotoFragment.getInstance(),
						BuyFragment.getInstance());
				break;
			case 4:
				switchContent(ChatFragment.getInstance(),
						BuyFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						BuyFragment.getInstance());
				break;
			}
			whichFragment = 2;
			break;
		case R.id.photo:
			switch (whichFragment) {
			case 1:
				switchContent(ShoppingFragment.getInstance(),
						PhotoFragment.getInstance());
				break;
			case 2:
				switchContent(BuyFragment.getInstance(),
						PhotoFragment.getInstance());
				break;
			case 4:
				switchContent(ChatFragment.getInstance(),
						PhotoFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						PhotoFragment.getInstance());
				break;
			}
			whichFragment = 3;
			break;
		case R.id.message:
			if (!PreferenceUtils.getBoolean(this, "isLogin", false)) {
				switch (whichFragment) {
				case 1:
					radioGroup.check(R.id.shopping);
					break;
					
				case 2:
					radioGroup.check(R.id.buy);
					break;

				default:
					break;
				}
				intent = new Intent(this,LoginActivity.class);
				intent.putExtra("whichFragment", 4);
				startActivity(intent);
				finish();
				return;
			}
			switch (whichFragment) {
			case 1:
				switchContent(ShoppingFragment.getInstance(),
						ChatFragment.getInstance());
				break;
			case 2:
				switchContent(BuyFragment.getInstance(),
						ChatFragment.getInstance());
				break;
			case 3:
				switchContent(PhotoFragment.getInstance(),
						ChatFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						ChatFragment.getInstance());
				break;
			}
			whichFragment = 4;
			break;
		case R.id.mine:
			if (!PreferenceUtils.getBoolean(this, "isLogin", false)) {
				switch (whichFragment) {
				case 1:
					radioGroup.check(R.id.shopping);
					break;
					
				case 2:
					radioGroup.check(R.id.buy);
					break;

				default:
					break;
				}
				intent = new Intent(this,LoginActivity.class);
				intent.putExtra("whichFragment", 5);
				startActivity(intent);
				finish();
				return;
			}
			switch (whichFragment) {
			case 1:
				switchContent(ShoppingFragment.getInstance(),
						MineFragment.getInstance());
				break;
			case 2:
				switchContent(BuyFragment.getInstance(),
						MineFragment.getInstance());
				break;
			case 3:
				switchContent(PhotoFragment.getInstance(),
						MineFragment.getInstance());
				break;
			case 4:
				switchContent(ChatFragment.getInstance(),
						MineFragment.getInstance());
				break;
			}
			whichFragment = 5;
			break;
		default:
			break;
		}
		
	}

}
