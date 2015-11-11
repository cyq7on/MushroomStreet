package com.cyq7on.mushrommstreet.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.fragment.ShoppingDetailFragment;
import com.cyq7on.mushrommstreet.view.TitleBar;
import com.viewpagerindicator.TabPageIndicator;

/**
 * 
 * @Title: ShoppingdetailActivity.java
 * @Package com.cyq7on.mushrommstreet.activity
 * @Description: 购物详情页面
 * @author cyq7on
 * @date 2015-11-11 下午2:22:21
 * @version V1.0
 */
public class ShoppingdetailActivity extends BaseFragmentActivity {
	private ViewPager viewPager;
	private List<String> datalist = new ArrayList<String>();
	private List<ShoppingDetailFragment> fragmentList = 
			new ArrayList<ShoppingDetailFragment>();
	private TabPageIndicator tabPageIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoppingdetail);
		initView();
	}

	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		titleBar.setTitle("购物狂欢");
		titleBar.setRightText("分享");
		titleBar.setRightButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(ShoppingdetailActivity.this, "分享",
						Toast.LENGTH_LONG).show();
			}
		});
		datalist.add("限时抢购");
		datalist.add("品牌精选");
		datalist.add("红人精选");
		datalist.add("海淘精选");
		datalist.add("大牌美妆");
		datalist.add("设计美妆");
		initViewPager();
	}

	private void initViewPager() {
		ShoppingDetailFragment fragment;
		for (int i = 0; i < datalist.size(); i++) {
			fragment = new ShoppingDetailFragment();
			fragmentList.add(fragment);
		}
		viewPager = (ViewPager)findViewById(R.id.viewpager);
//		viewPager.setOffscreenPageLimit(1);
		viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			/**
			 * 重写此方法,返回页面标题,用于viewpagerIndicator的页签显示
			 */
			@Override
			public CharSequence getPageTitle(int position) {
				return datalist.get(position);
			}

			@Override
			public int getCount() {
				return datalist.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				return fragmentList.get(arg0);
			}
		});
		tabPageIndicator = (TabPageIndicator)findViewById(R.id.tabpageindicator);
		tabPageIndicator.setViewPager(viewPager);
	}

}
