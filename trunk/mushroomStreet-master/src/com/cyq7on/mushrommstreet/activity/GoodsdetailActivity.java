package com.cyq7on.mushrommstreet.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.bean.ShoppingDetailVo;
import com.cyq7on.mushrommstreet.fragment.GoodsParamsFragment;
import com.cyq7on.mushrommstreet.fragment.HotSaleRecommendFragment;
import com.cyq7on.mushrommstreet.fragment.ImagAndWordDetailFragment;
import com.cyq7on.mushrommstreet.view.MyPopupWindow;
import com.cyq7on.mushrommstreet.view.TBLayout;
import com.cyq7on.mushrommstreet.view.TBLayout.OnPageChangedListener;
import com.cyq7on.mushrommstreet.view.TBLayout.OnPullListener;
import com.cyq7on.mushrommstreet.view.TitleBar;
import com.cyq7on.mushroomstreet.AppConfig;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.TabPageIndicator;

/**
 * 
 * @Title: ImagAndWordDetailFragment.java
 * @Package com.cyq7on.mushrommstreet.activity
 * @Description: ��Ʒ����ҳ��
 * @author cyq7on
 * @date 2015-11-12 ����4:42:40
 * @version V1.0
 */
public class GoodsdetailActivity extends BaseFragmentActivity implements
		OnPullListener, OnPageChangedListener {
	private ViewPager viewPager, vpImage;
	private List<String> dataList = new ArrayList<String>();
	private List<String> urlList = new ArrayList<String>();
	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	private TabPageIndicator tabPageIndicator;
	private TBLayout mLayout;
	private ScrollView mHeader;
	private TextView tvIndex;
	private LinearLayout mContent;
	private int which;
	private Button btnCollect, btnEnter;
	private RadioGroup rg;
	private RadioButton rbLike, rbCart, rbBuyNow;
	private MyPopupWindow popupWindow;
	private List<String> colorList = new ArrayList<String>();
	private List<String> sizeList = new ArrayList<String>();
	private ShoppingDetailVo vo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goodsdetail);
		initData();
		colorList.add("��ɫ");
		colorList.add("��ɫ");
		for (int i = 0; i < 7; i++) {
			sizeList.add(i + 36 + "");
		}
		initView();
	}

	@Override
	public void initData() {
		// ģ�����������
		int len = (int) (Math.random() * AppConfig.urlImage.length);
		for (int i = 0; i < len; i++) {
			urlList.add(AppConfig.urlImage[i]);
		}
		// ��ѡ�е���Ʒ
		vo = new ShoppingDetailVo(AppConfig.urlImage[len],
				"����ʱ����ë����" + len, len * 100 + "", 
				len * 150 + "", "������" + len);
	}

	public void initView() {
		titleBar = (TitleBar) findViewById(R.id.title_bar);
		titleBar.setTitle("��Ʒ����");
		titleBar.setTitleColor(Color.RED);
		titleBar.setRihtBackground(R.drawable.detail_more);
		titleBar.setImageTitleBackground(R.drawable.shop_cart);
		titleBar.setImageTitleVisible();
		titleBar.setImageTitleListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(GoodsdetailActivity.this,
						CartActivity.class));
			}
		});
		titleBar.setRightButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Builder builder = new Builder(GoodsdetailActivity.this);
				final String[] items = { "��Ϣ", "����", "�ٱ�" };
				builder.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							// Intent intent = new
							// Intent(GoodsdetailActivity.this,
							// MainActivity.class);
							// intent.putExtra("info", "chat");
							// startActivity(intent);
							break;

						default:
							break;
						}
						Toast.makeText(GoodsdetailActivity.this, items[which],
								Toast.LENGTH_LONG).show();
					}
				});
				builder.create().show();
			}
		});
		dataList.add("ͼ������");
		dataList.add("��Ʒ����");
		dataList.add("�����Ƽ�");
		initTbLayout();
		initViewPager();
		popupWindow = new MyPopupWindow(this, colorList, sizeList,vo);
		popupWindow.onDismiss();
	}

	private void initTbLayout() {
		mLayout = (TBLayout) findViewById(R.id.tblayout);
		mLayout.setOnPullListener(this);
		mLayout.setOnContentChangeListener(this);
		mHeader = (ScrollView) findViewById(R.id.header);
		mContent = (LinearLayout) mHeader.getChildAt(0);
		btnCollect = (Button) findViewById(R.id.btn_collect);
		btnCollect.setOnClickListener(new OnClickListener() {
			boolean b = false;

			@Override
			public void onClick(View v) {
				if (!b) {
					btnCollect.setText("���ղ�");
					b = true;
				} else {
					btnCollect.setText("+ �ղ�");
					b = false;
				}
			}
		});
		btnEnter = (Button) findViewById(R.id.btn_enter);
		btnEnter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		rg = (RadioGroup) findViewById(R.id.rg_bottom);
		rbLike = (RadioButton) findViewById(R.id.like);
		rbCart = (RadioButton) findViewById(R.id.cart);
		rbBuyNow = (RadioButton) findViewById(R.id.buynow);
		// �Ƿ�ϲ����ť
		rbLike.setOnClickListener(new OnClickListener() {
			boolean b = false;

			@Override
			public void onClick(View v) {
				Drawable drawable;
				if (!b) {
					drawable = getResources().getDrawable(
							R.drawable.like_icon_red);
					b = true;
				} else {
					drawable = getResources().getDrawable(
							R.drawable.like_icon_grey);
					b = false;
				}
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight());
				rbLike.setCompoundDrawablesWithIntrinsicBounds(null, drawable,
						null, null);
			}
		});

		// ���빺�ﳵ��ť
		rbCart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.showAsDropDown(v,false);
			}
		});
		// ��������ť
		rbBuyNow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.showAsDropDown(v,true);
			}
		});
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.chat:

					break;

				default:
					break;
				}
			}
		});
	}

	private void initViewPager() {
		// ͼƬ����viewpager
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
		ImagAndWordDetailFragment imagAndWordDetailFragment = new ImagAndWordDetailFragment();
		GoodsParamsFragment goodsParamsFragment = new GoodsParamsFragment();
		HotSaleRecommendFragment hotSaleRecommendFragment = new HotSaleRecommendFragment();
		fragmentList.add(imagAndWordDetailFragment);
		fragmentList.add(goodsParamsFragment);
		fragmentList.add(hotSaleRecommendFragment);
		viewPager = (ViewPager) findViewById(R.id.footer);
		// viewPager.setOffscreenPageLimit(1);
		viewPager.setAdapter(new FragmentPagerAdapter(
				getSupportFragmentManager()) {

			/**
			 * ��д�˷���,����ҳ�����,����viewpagerIndicator��ҳǩ��ʾ
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
		switch (which) {
		case 0:
			ImagAndWordDetailFragment imagAndWordDetailFragment = (ImagAndWordDetailFragment) fragmentList
					.get(which);
			if (imagAndWordDetailFragment.getListView()
					.getFirstVisiblePosition() == 0) {
				View v = imagAndWordDetailFragment.getListView().getChildAt(0);
				if (v != null && v.getTop() == 0) {
					tabPageIndicator.setVisibility(View.GONE);
					return true;
				}

			}
			break;
		case 1:
			GoodsParamsFragment goodsParamsFragment = (GoodsParamsFragment) fragmentList
					.get(which);
			if (goodsParamsFragment.getListView().getFirstVisiblePosition() == 0) {
				View v = goodsParamsFragment.getListView().getChildAt(0);
				if (v != null && v.getTop() == 0) {
					tabPageIndicator.setVisibility(View.GONE);
					return true;
				}

			}
			break;
		case 2:
			HotSaleRecommendFragment hotSaleRecommendFragment = (HotSaleRecommendFragment) fragmentList
					.get(which);
			if (hotSaleRecommendFragment.getGridView()
					.getFirstVisiblePosition() == 0) {
				View v = hotSaleRecommendFragment.getGridView().getChildAt(0);
				if (v != null && v.getTop() == 0) {
					tabPageIndicator.setVisibility(View.GONE);
					return true;
				}

			}
			break;

		default:
			break;
		}
		return false;
	}

	@Override
	public void onPageChanged(int stub) {
		// TODO Auto-generated method stub

	}

}
