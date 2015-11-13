package com.cyq7on.mushrommstreet.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.fragment.ShoppingDetailFragment;
import com.cyq7on.mushrommstreet.view.TBLayout;
import com.cyq7on.mushrommstreet.view.TBLayout.OnPageChangedListener;
import com.cyq7on.mushrommstreet.view.TBLayout.OnPullListener;
import com.cyq7on.mushrommstreet.view.TitleBar;
import com.cyq7on.mushroomstreet.AppConfig;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.TabPageIndicator;

/**
 * 
 * @Title: GoodsdetailActivity.java
 * @Package com.cyq7on.mushrommstreet.activity
 * @Description: 商品详情页面
 * @author cyq7on
 * @date 2015-11-12 下午4:42:40
 * @version V1.0
 */
public class GoodsdetailActivity extends BaseFragmentActivity implements
		OnPullListener, OnPageChangedListener {
	private ViewPager viewPager, vpImage;
	private List<String> dataList = new ArrayList<String>();
	private List<String> urlList = new ArrayList<String>();
	private List<ShoppingDetailFragment> fragmentList = new ArrayList<ShoppingDetailFragment>();
	private TabPageIndicator tabPageIndicator;
	private TBLayout mLayout;
	private ScrollView mHeader;
	private TextView tvIndex;
	private LinearLayout mContent;
	private int which;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goodsdetail);
		initData();
		initView();
	}

	@Override
	public void initData() {
		// 模拟请求服务器
		int len = (int) (Math.random() * AppConfig.urlImage.length);
		for (int i = 0; i < len; i++) {
			urlList.add(AppConfig.urlImage[i]);
		}
	}

	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		titleBar.setTitle("商品详情");
		titleBar.setTitleColor(Color.RED);
		titleBar.setRihtBackground(R.drawable.detail_more);
		titleBar.setImageTitleBackground(R.drawable.shop_cart);
		titleBar.setImageTitleVisible();
		titleBar.setImageTitleListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(GoodsdetailActivity.this, "购物车",
						Toast.LENGTH_LONG).show();
			}
		});
		titleBar.setRightButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(GoodsdetailActivity.this, "分享",
						Toast.LENGTH_LONG).show();
			}
		});
		dataList.add("图文详情");
		dataList.add("商品参数");
		dataList.add("热卖推荐");
		initTbLayout();
		initViewPager();
	}

	private void initTbLayout() {
		mLayout = (TBLayout) findViewById(R.id.tblayout);
		mLayout.setOnPullListener(this);
		mLayout.setOnContentChangeListener(this);
		mHeader = (ScrollView) findViewById(R.id.header);
		mContent = (LinearLayout) mHeader.getChildAt(0);
	}

	private void initViewPager() {
		// 图片滑动viewpager
		tvIndex = (TextView) findViewById(R.id.tv_index);
		tvIndex.setText(1 + "/" + urlList.size());
		vpImage = (ViewPager) findViewById(R.id.vp_image);
		vpImage.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				tvIndex.setText(arg0 + 1 + "/" + urlList.size());
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		vpImage.setAdapter(new PagerAdapter() {
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				ImageView iv = new ImageView(GoodsdetailActivity.this);
				iv.setScaleType(ScaleType.FIT_XY);
				ImageLoader.getInstance().displayImage(urlList.get(position),
						iv, AppConfig.options);
				container.addView(iv);
				return iv;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View) object);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return urlList.size();
			}
		});
		ShoppingDetailFragment fragment;
		for (int i = 0; i < dataList.size(); i++) {
			fragment = new ShoppingDetailFragment();
			fragmentList.add(fragment);
		}
		viewPager = (ViewPager) findViewById(R.id.footer);
		// viewPager.setOffscreenPageLimit(1);
		viewPager.setAdapter(new FragmentPagerAdapter(
				getSupportFragmentManager()) {

			/**
			 * 重写此方法,返回页面标题,用于viewpagerIndicator的页签显示
			 */
			@Override
			public CharSequence getPageTitle(int position) {
				return dataList.get(position);
			}

			@Override
			public int getCount() {
				return dataList.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return fragmentList.get(arg0);
			}
		});
		tabPageIndicator = (TabPageIndicator) findViewById(R.id.tabpageindicator);
		tabPageIndicator.setViewPager(viewPager);
		tabPageIndicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				which = arg0;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean headerFootReached(MotionEvent event) {
		System.out.println(tvIndex.getScrollY());
		if (mHeader.getScrollY() + mHeader.getHeight() >= mContent.getHeight()) {
			tabPageIndicator.setVisibility(View.VISIBLE);
			return true;
		}
		return false;
	}

	@Override
	public boolean footerHeadReached(MotionEvent event) {
		// if (mFooter.getFirstVisiblePosition() == 0) {
		// View v = mFooter.getChildAt(0);
		// if (v != null && v.getTop() == 0) {
		// return true;
		// }
		// }
		if (fragmentList.get(which).getGridView().getFirstVisiblePosition() == 0) {
			View v = fragmentList.get(which).getGridView().getChildAt(0);
			if (v != null && v.getTop() == 0) {
				tabPageIndicator.setVisibility(View.GONE);
				return true;
			}

		}
		return false;
	}

	@Override
	public void onPageChanged(int stub) {
		// TODO Auto-generated method stub

	}

}
