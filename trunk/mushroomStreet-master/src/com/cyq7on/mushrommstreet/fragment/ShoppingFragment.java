package com.cyq7on.mushrommstreet.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.activity.MainActivity;
import com.cyq7on.mushrommstreet.activity.ShoppingdetailActivity;
import com.cyq7on.mushrommstreet.adapter.DynamicAdapter;
import com.cyq7on.mushrommstreet.bean.DynamicVo;
import com.cyq7on.mushrommstreet.bean.ShoppingTab;
import com.cyq7on.mushrommstreet.shoppingfragment.activity.SearchActivity;
import com.cyq7on.mushrommstreet.utils.DeviceInfo;
import com.cyq7on.mushrommstreet.view.HorizontalListView;
import com.cyq7on.mushrommstreet.view.StickyScrollView;
import com.cyq7on.mushroomstreet.AppConfig;
import com.handmark.pulltorefresh.library.PullToRefreshLayout;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TabPageIndicator;

public class ShoppingFragment extends BaseFragment implements 
OnClickListener {

	private ImageView ivAdd;
	private EditText etSearch;
	private ViewPager vpTop, vpBottom;
	private List<ImageView> list = new ArrayList<ImageView>();// 轮播图片数据源
	private List<ShoppingTab> tabList = new ArrayList<ShoppingTab>();
	private CirclePageIndicator circlePageIndicator;
	private TabPageIndicator tabPageIndicator,topIndicator;
	private Handler mHandler;
	private int which;// 哪个图片被选中
	private HorizontalListView horizontalListView;
	private int[] source = { R.drawable.ic_launcher,
			R.drawable.index_follow_icon, R.drawable.index_my_icon };
	private DynamicAdapter adapter;// 动态适配器
	private List<DynamicVo> listDynamic = new ArrayList<DynamicVo>();
	private PullToRefreshLayout pullToRefreshLayout;
	private StickyScrollView stickyScrollView;
	// 轮播图片地址
	private String[] url = {
			"https://img.alicdn.com/tps/TB1Z1siKXXXXXcvXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB16nMPKXXXXXcQXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1hMIUKXXXXXakXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1MsspKXXXXXcUXFXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/bao/uploaded/i1/TB1OwwiKXXXXXaQXpXXSutbFXXX.jpg",
			"https://img.alicdn.com/bao/uploaded/i1/TB1XzItKXXXXXbLXVXXSutbFXXX.jpg" };
	// 滑动图片地址
	private String[] url1 = {
			"https://img.alicdn.com/tps/TB1Z1siKXXXXXcvXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB16nMPKXXXXXcQXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1hMIUKXXXXXakXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1MsspKXXXXXcUXFXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1Z1siKXXXXXcvXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB16nMPKXXXXXcQXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1hMIUKXXXXXakXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1MsspKXXXXXcUXFXXXXXXXXXX-400-200.jpg" };

	// viewpager页签数据
	private String[] tabData = { "关注", "热门", "私搭", "晒货", "男票", "星榜", "妆呗",
			"潮报", "萌萌", "脸赞" ,"好吃","旅行"};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity = (MainActivity) getActivity();
		view = inflater.inflate(R.layout.fragment_shopping, container, false);
		initView();
		return view;
	}



	@Override
	public void initView() {
		// ivAdd = (ImageView) view.findViewById(R.id.iv_add);
		etSearch = (EditText) view.findViewById(R.id.et_search);
		// ivAdd.setOnClickListener(this);
		etSearch.setOnClickListener(this);
		stickyScrollView = (StickyScrollView) view.findViewById(R.id.stickyscrollview);
//		topIndicator = (TabPageIndicator) view.findViewById(R.id.top_tabpageindicator);
		//当布局的状态或者控件的可见性发生改变回调的接口
//		view.findViewById(R.id.ll_parent).getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//			
//			@Override
//			public void onGlobalLayout() {
//				//这一步很重要，使得上面的购买布局和下面的购买布局重合
//				onScroll(stickyScrollView.getScrollY());
//			}
//		});
		initPullToRefreshLayout();
		initViewPager();
		initHorizontalListView();
	}

	private void initPullToRefreshLayout() {

//		pullToRefreshLayout = (PullToRefreshLayout) view
//				.findViewById(R.id.pulltorefreshlayout);
//		//禁止刷新效果
//		pullRefreshScrollView.setMode(Mode.DISABLED);
		// pullRefreshScrollView.setOnRefreshListener(
		// new OnRefreshListener<ScrollView>() {
		//
		// @Override
		// public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		// if (pullRefreshScrollView.isHeaderShown()) {
		// listDynamic.clear();
		// SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
		// for (int i = 0; i < 4; i++) {
		// // 这里为了简便就添加了部分信息
		// DynamicVo data = new DynamicVo();
		// data.setUserName("花千骨" + i + "下拉刷新");
		// data.setUrl("http://www.qq1234."
		// + "org/uploads/allimg/150706/8_150706145211_9.jpg");
		// data.setDate(sdf.format(new Date()));
		// data.setTvPlace("成都" + i);
		// List<String> urlList = new ArrayList<String>();
		// for (int j = 0; j <= i; j++) {
		// urlList.add(url[j % url.length]);
		// }
		// data.setContentImageurl(urlList);
		// listDynamic.add(data);
		// }
		// adapter.notifyDataSetChanged();
		// }else {
		// for (int i = 0; i < 4; i++) {
		// // 这里为了简便就添加了部分信息
		// DynamicVo data = new DynamicVo();
		// data.setUserName("花千骨" + i + "加载更多");
		// data.setUrl("http://www.qq1234."
		// + "org/uploads/allimg/150706/8_150706145211_9.jpg");
		// SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
		// data.setDate(sdf.format(new Date()));
		// data.setTvPlace("成都" + i);
		// List<String> urlList = new ArrayList<String>();
		// for (int j = 0; j <= i; j++) {
		// urlList.add(url[j % url.length]);
		// }
		// data.setContentImageurl(urlList);
		// listDynamic.add(data);
		// }
		// adapter.notifyDataSetChanged();
		// setListViewHeight(listView);
		// }
		// pullRefreshScrollView.onRefreshComplete();
		// }
		// });

	}


	private void initHorizontalListView() {

		horizontalListView = (HorizontalListView) view.findViewById(R.id.hl_iv);
		horizontalListView.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView iv;
				if (convertView == null) {
					convertView = LayoutInflater.from(activity).inflate(
							R.layout.item_hlistview, null);
					iv = (ImageView) convertView.findViewById(R.id.iv);
					convertView.setTag(iv);
				} else {
					iv = (ImageView) convertView.getTag();
				}
				ImageLoader.getInstance().displayImage(url1[position], iv,
						AppConfig.options);
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return url1.length;
			}
		});

		horizontalListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(activity,
						ShoppingdetailActivity.class);
				startActivity(intent);
//				pullRefreshScrollView
//						.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_START);
			}
		});
	}

	private void initViewPager() {
		vpTop = (ViewPager) view.findViewById(R.id.vp_top);
		vpTop.setOffscreenPageLimit(1);
		circlePageIndicator = (CirclePageIndicator) view
				.findViewById(R.id.circlepageindicator);
		ImageView iv;
		for (int i = 0; i < url.length; i++) {
			iv = new ImageView(activity);
			iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(activity,
							ShoppingdetailActivity.class);
					startActivity(intent);
				}
			});
			ImageLoader.getInstance().displayImage(url[i], iv,
					AppConfig.options);
			list.add(iv);
		}
		vpTop.setAdapter(new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return list.size();
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				ImageView iv = list.get(position);
				iv.setScaleType(ScaleType.FIT_XY);// 基于控件大小填充图片
				container.addView(iv);
				return iv;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View) object);
			}
		});

		circlePageIndicator.setViewPager(vpTop);
		circlePageIndicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				which = arg0;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		// 自动轮播条显示
		if (mHandler == null) {
			mHandler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					int currentItem = vpTop.getCurrentItem();

					if (currentItem < list.size() - 1) {
						currentItem++;
					} else {
						currentItem = 0;
					}

					vpTop.setCurrentItem(currentItem);// 切换到下一个页面
					mHandler.sendEmptyMessageDelayed(0, 3000);// 继续延时3秒发消息,
				};
			};

			mHandler.sendEmptyMessageDelayed(0, 3000);// 延时3秒后发消息
		}

		// 底部viewpager
		for (int i = 0; i < tabData.length; i++) {
			ShoppingTab tab = new ShoppingTab(activity,Arrays.asList(url),
					pullToRefreshLayout);
//			ShoppingTab tab = new ShoppingTab(activity,Arrays.asList(url),
//					null);
//			ShoppingTab tab = new ShoppingTab(activity,getData("test"));
			tab.initData(tabData[0]);//初始化第一个视图的数据
			tabList.add(tab);
		}
		vpBottom = (ViewPager) view.findViewById(R.id.id_stickynavlayout_viewpager);
		vpBottom.setOffscreenPageLimit(1);
		vpBottom.setAdapter(new PagerAdapter() {

			/**
			 * 重写此方法,返回页面标题,用于viewpagerIndicator的页签显示
			 */
			@Override
			public CharSequence getPageTitle(int position) {
				return tabData[position];
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return tabData.length;
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				ShoppingTab tab = tabList.get(position);
				View view = tab.getView();
				container.addView(view);
//				tab.initData(tabData[position]);
				return view;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View) object);
			}
		});
//		topIndicator = (TabPageIndicator) 
//				view.findViewById(R.id.top_tabpageindicator);
//		topIndicator.setViewPager(vpBottom);
		tabPageIndicator = (TabPageIndicator) view
				.findViewById(R.id.id_stickynavlayout_indicator);
		tabPageIndicator.setViewPager(vpBottom);
		tabPageIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				ShoppingTab tab = tabList.get(arg0);
				tab.initData(tabData[arg0]);
				
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

	/**
	 * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
	 * 
	 * @param listView
	 */
	public void setListViewHeight(ListView listView) {

		// 获取ListView对应的Adapter

		Adapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); // 计算子项View 的宽高
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
			float height = DeviceInfo.getScreenHeight(activity);
			totalHeight += height / 20; // 这里为了解决listview最后一项显示不全的问题
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
	
//	private class TouchListener implements OnTouchListener {
//		@Override
//		public boolean onTouch(View v, MotionEvent event) {
//			if (event.getAction() == MotionEvent.ACTION_DOWN) {
//				pullRefreshScrollView.setMode(Mode.PULL_FROM_START);
//			}
////			if (event.getAction() == MotionEvent.ACTION_UP) {
////				pullRefreshScrollView.setMode(Mode.DISABLED);
////			}
//			return false;
//		}
//	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 放在这里进行点击响应会有bug，故将其放到了MainActivity
		// case R.id.iv_add:
		// Builder builder = new Builder(activity);
		// final String[] items = { "查找好友", "扫一扫" };
		// builder.setItems(items, new DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// Toast.makeText(activity, items[which], Toast.LENGTH_LONG)
		// .show();
		// }
		// });
		// AlertDialog alertDialog = builder.create();
		// Window win = alertDialog.getWindow();
		// // LayoutParams params = new LayoutParams();
		// // params.x = 80;//设置x坐标
		// // params.y = 60;//设置y坐标
		// // win.setAttributes(params);
		// alertDialog.show();
		//
		// break;

		case R.id.et_search:
			startActivity(new Intent(activity, SearchActivity.class));
		default:
			break;
		}
	}

}
