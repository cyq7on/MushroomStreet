package com.cyq7on.mushrommstreet.activity;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.fragment.BasicFragment;
import com.cyq7on.mushrommstreet.fragment.BuyFragment;
import com.cyq7on.mushrommstreet.fragment.ChatFragment;
import com.cyq7on.mushrommstreet.fragment.MineFragment;
import com.cyq7on.mushrommstreet.fragment.PhotoFragment;
import com.cyq7on.mushrommstreet.fragment.ShoppingFragment;
import com.cyq7on.mushrommstreet.utils.PreferenceUtils;
import com.cyq7on.mushroomstreet.AppConfig;


/** 用于显示主页面 */
public class MainActivity extends BaseActivity implements
		OnCheckedChangeListener {

	private RadioGroup radioGroup;
	/**
	 * 1表示ShoppingFragment 2表示BuyFragment 3表示PhotoFragment
	 * 4表示ChatFragment 5表示MineFragment
	 * */
	private int whichFragment;
	private int which;
	private BasicFragment shoppinFragment,buyFragment,
	photoFragment,chatFragment,mineFragment;
	private RadioButton rbChat,rbMine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		AppConfig.initConfig(this);
		initView();
		radioGroup.setOnCheckedChangeListener(this);
	}

	public void initView() {
		radioGroup = (RadioGroup) findViewById(R.id.bottom_menu);
		rbChat = (RadioButton) findViewById(R.id.message);
		rbMine = (RadioButton) findViewById(R.id.mine);
		radioGroup.check(R.id.shopping);
		onCheckedChanged(radioGroup,R.id.shopping);
	}
	
	@Override
	
	
	protected void onStart() {
		super.onStart();
		switch (which) {
		case 4:
			if (PreferenceUtils.getBoolean(this, "isLogin", false)
					&& !rbChat.isChecked()) {
				radioGroup.check(R.id.message);
				onCheckedChanged(radioGroup,R.id.message);
			}
			break;
		case 5:
			if (PreferenceUtils.getBoolean(this, "isLogin", false)
					&& !rbMine.isChecked()) {
				radioGroup.check(R.id.mine);
				onCheckedChanged(radioGroup,R.id.mine);
			}
			break;

		default:
			break;
		}
	}
	
	private void hideFragments(FragmentTransaction transaction)  
    {  
        if (shoppinFragment != null)  
        {  
            transaction.hide(shoppinFragment);  
        }  
        if (buyFragment != null)  
        {  
            transaction.hide(buyFragment);  
        }  
        if (photoFragment != null)  
        {  
            transaction.hide(photoFragment);  
        }  
        if (chatFragment != null)  
        {  
            transaction.hide(chatFragment);  
        }  
        if (mineFragment != null)  
        {  
            transaction.hide(mineFragment);  
        }  
          
    }  

	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		Intent intent;
		FragmentManager fragmentManager = getFragmentManager();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况  
        hideFragments(transaction);  
		switch (checkedId) {
		case R.id.shopping:
			if (shoppinFragment == null) {
				shoppinFragment = new ShoppingFragment();
				transaction.add(R.id.fragment_container,shoppinFragment);
			} else {
				transaction.show(shoppinFragment);
			}
			whichFragment = 1;
			break;
		case R.id.buy:
			if (buyFragment == null) {
				buyFragment = new BuyFragment();
				transaction.add(R.id.fragment_container,buyFragment);
			} else {
				transaction.show(buyFragment);
			}			
			whichFragment = 2;
			break;
		case R.id.photo:
			if (photoFragment == null) {
				photoFragment = new PhotoFragment();
				transaction.add(R.id.fragment_container,photoFragment);
			} else {
				transaction.show(photoFragment);
			}
			whichFragment = 3;
			break;
		case R.id.message:
			which = 4;
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
				startActivity(intent);
//				finish();
				return;
			}
			if (chatFragment == null) {System.out.println("new");
				chatFragment = new ChatFragment();
				transaction.add(R.id.fragment_container,chatFragment);
			} else {System.out.println("show");
				transaction.show(chatFragment);
			}
			whichFragment = 4;
			break;
		case R.id.mine:
			which = 5;
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
				startActivity(intent);
//				finish();
				return;
			}
			if (mineFragment == null) {
				mineFragment = new MineFragment();
				transaction.add(R.id.fragment_container,mineFragment);
			} else {
				transaction.show(mineFragment);
			}
			whichFragment = 5;
			break;
		default:
			break;
		}
		transaction.commit();
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_add:
			Builder builder = new Builder(this);
			final String[] items = { "查找好友", "扫一扫" };
			builder.setItems(items, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_LONG)
							.show();
				}
			});
			AlertDialog alertDialog = builder.create();
			Window win = alertDialog.getWindow();
			// LayoutParams params = new LayoutParams();
			// params.x = 80;//设置x坐标
			// params.y = 60;//设置y坐标
			// win.setAttributes(params);
			alertDialog.show();
			
			break;

		default:
			break;
		}
	}

}
