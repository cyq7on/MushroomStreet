package com.cyq7on.mushrommstreet.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import android.widget.ScrollView;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.activity.MainActivity;
import com.cyq7on.mushrommstreet.adapter.DynamicAdapter;
import com.cyq7on.mushrommstreet.bean.DynamicVo;
import com.cyq7on.mushrommstreet.shoppingfragment.activity.SearchActivity;
import com.cyq7on.mushrommstreet.utils.DeviceInfo;
import com.cyq7on.mushrommstreet.view.HorizontalListView;
import com.cyq7on.mushrommstreet.view.XListView;
import com.cyq7on.mushrommstreet.view.XListView.IXListViewListener;
import com.cyq7on.mushroomstreet.AppConfig;
import com.example.mushroomstreet.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TabPageIndicator;

public class ShoppingFragment extends BasicFragment implements
		IXListViewListener, OnClickListener {

	private ImageView ivAdd;
	private EditText etSearch;
	private ViewPager vpTop,vpBottom;
	private List<ImageView> list = new ArrayList<ImageView>();
	private CirclePageIndicator circlePageIndicator;
	private TabPageIndicator tabPageIndicator;
	private Handler mHandler;
	private int which;// �ĸ�ͼƬ��ѡ��
	private HorizontalListView horizontalListView;
	private int[] source = { R.drawable.ic_launcher,
			R.drawable.index_follow_icon, R.drawable.index_my_icon };
	private XListView xListview;//��̬listview
	private ListView listView;
	private DynamicAdapter adapter;//��̬������
	private List<DynamicVo> listDynamic = new ArrayList<DynamicVo>();
	private PullToRefreshScrollView pullRefreshScrollView;
	// �ֲ�ͼƬ��ַ
	private String[] url = {
			"https://img.alicdn.com/tps/TB1Z1siKXXXXXcvXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB16nMPKXXXXXcQXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1hMIUKXXXXXakXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1MsspKXXXXXcUXFXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/bao/uploaded/i1/TB1OwwiKXXXXXaQXpXXSutbFXXX.jpg",
			"https://img.alicdn.com/bao/uploaded/i1/TB1XzItKXXXXXbLXVXXSutbFXXX.jpg" };
	// ����ͼƬ��ַ
	private String[] url1 = {
			"https://img.alicdn.com/tps/TB1Z1siKXXXXXcvXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB16nMPKXXXXXcQXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1hMIUKXXXXXakXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1MsspKXXXXXcUXFXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1Z1siKXXXXXcvXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB16nMPKXXXXXcQXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1hMIUKXXXXXakXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1MsspKXXXXXcUXFXXXXXXXXXX-400-200.jpg" };
	
	//viewpagerҳǩ����
	private String[] tabData = {"��ע","����","˽��","ɹ��","��Ʊ",
			"��ע1","����1","˽��1","ɹ��1","��Ʊ1"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity = (MainActivity) getActivity();
		view = inflater.inflate(R.layout.fragment_shopping, container, false);
		initData();
		initView();
		return view;
	}

	@Override
	public void initData() {
		for (int i = 0; i < 4; i++) {
			// ����Ϊ�˼�������˲�����Ϣ
			DynamicVo data = new DynamicVo();
			data.setUserName("��ǧ��" + i);
			data.setUrl("http://www.qq1234."
					+ "org/uploads/allimg/150706/8_150706145211_9.jpg");
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
			data.setDate(sdf.format(new Date()));
			data.setTvPlace("�ɶ�" + i);
			List<String> urlList = new ArrayList<String>();
			for (int j = 0; j <= i; j++) {
				urlList.add(url[j % url.length]);
			}
			data.setContentImageurl(urlList);
			listDynamic.add(data);
		}
		
	}

	@Override
	public void initView() {
//		ivAdd = (ImageView) view.findViewById(R.id.iv_add);
		etSearch = (EditText) view.findViewById(R.id.et_search);
//		ivAdd.setOnClickListener(this);
		etSearch.setOnClickListener(this);
		
		initViewPager();
		initHorizontalListView();
		initXListView();
		initPullRefreshScrollView();
	}
	
	private void initPullRefreshScrollView() {
		
		pullRefreshScrollView = (PullToRefreshScrollView) 
				view.findViewById(R.id.pull_refresh_scrollview); 
		pullRefreshScrollView.setOnRefreshListener(
				new OnRefreshListener<ScrollView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				if (pullRefreshScrollView.isHeaderShown()) {
					listDynamic.clear();
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
					for (int i = 0; i < 4; i++) {
						// ����Ϊ�˼�������˲�����Ϣ
						DynamicVo data = new DynamicVo();
						data.setUserName("��ǧ��" + i + "����ˢ��");
						data.setUrl("http://www.qq1234."
								+ "org/uploads/allimg/150706/8_150706145211_9.jpg");
						data.setDate(sdf.format(new Date()));
						data.setTvPlace("�ɶ�" + i);
						List<String> urlList = new ArrayList<String>();
						for (int j = 0; j <= i; j++) {
							urlList.add(url[j % url.length]);
						}
						data.setContentImageurl(urlList);
						listDynamic.add(data);
					}
					adapter.notifyDataSetChanged();
				}else {
					for (int i = 0; i < 4; i++) {
						// ����Ϊ�˼�������˲�����Ϣ
						DynamicVo data = new DynamicVo();
						data.setUserName("��ǧ��" + i + "���ظ���");
						data.setUrl("http://www.qq1234."
								+ "org/uploads/allimg/150706/8_150706145211_9.jpg");
						SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
						data.setDate(sdf.format(new Date()));
						data.setTvPlace("�ɶ�" + i);
						List<String> urlList = new ArrayList<String>();
						for (int j = 0; j <= i; j++) {
							urlList.add(url[j % url.length]);
						}
						data.setContentImageurl(urlList);
						listDynamic.add(data);
					}
					adapter.notifyDataSetChanged();
					setListViewHeight(listView);
				}
				pullRefreshScrollView.onRefreshComplete();
			}
		});
		
	}

	private void initXListView() {
		listView = (ListView) view.findViewById(R.id.listview);
//		xListview = (XListView) view.findViewById(R.id.xlistview);
//		xListview.addHeaderView(header);
//		xListview.setPullLoadEnable(true);
//		xListview.setXListViewListener(this);
//		xListview.setRefreshTime(new Date().toLocaleString());
		adapter = new DynamicAdapter(activity, listDynamic);
//		xListview.setAdapter(adapter);
		listView.setAdapter(adapter);
		setListViewHeight(listView);
//		xListview.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				Toast.makeText(activity, arg2 + "", Toast.LENGTH_LONG).show();
//			}
//		});
	}

	// ����ˢ��
	@Override
	public void onRefresh() {
		listDynamic.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
		xListview.setRefreshTime(sdf.format(new Date()));
		for (int i = 0; i < 4; i++) {
			// ����Ϊ�˼�������˲�����Ϣ
			DynamicVo data = new DynamicVo();
			data.setUserName("��ǧ��" + i + "����ˢ��");
			data.setUrl("http://www.qq1234."
					+ "org/uploads/allimg/150706/8_150706145211_9.jpg");
			data.setDate(sdf.format(new Date()));
			data.setTvPlace("�ɶ�" + i);
			List<String> urlList = new ArrayList<String>();
			for (int j = 0; j <= i; j++) {
				urlList.add(url[j % url.length]);
			}
			data.setContentImageurl(urlList);
			listDynamic.add(data);
		}
		adapter.notifyDataSetChanged();
		xListview.stopRefresh();
	}

	// �������ظ���
	@Override
	public void onLoadMore() {
		for (int i = 0; i < 4; i++) {
			// ����Ϊ�˼�������˲�����Ϣ
			DynamicVo data = new DynamicVo();
			data.setUserName("��ǧ��" + i + "���ظ���");
			data.setUrl("http://www.qq1234."
					+ "org/uploads/allimg/150706/8_150706145211_9.jpg");
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
			data.setDate(sdf.format(new Date()));
			data.setTvPlace("�ɶ�" + i);
			List<String> urlList = new ArrayList<String>();
			for (int j = 0; j <= i; j++) {
				urlList.add(url[j % url.length]);
			}
			data.setContentImageurl(urlList);
			listDynamic.add(data);
		}
		adapter.notifyDataSetChanged();
		xListview.stopRefresh();
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
				Toast.makeText(activity, arg2 + "", Toast.LENGTH_LONG).show();
			}
		});
	}

	private void initViewPager() {
		vpTop = (ViewPager) view.findViewById(R.id.vp_top);
		circlePageIndicator = (CirclePageIndicator) view
				.findViewById(R.id.circlepageindicator);
		ImageView iv;
		for (int i = 0; i < url.length; i++) {
			iv = new ImageView(activity);
			iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(activity, "��ѡ���˵�" + (which + 1) + "��",
							Toast.LENGTH_LONG).show();
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
				iv.setScaleType(ScaleType.FIT_XY);// ���ڿؼ���С���ͼƬ
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

		// �Զ��ֲ�����ʾ
		if (mHandler == null) {
			mHandler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					int currentItem = vpTop.getCurrentItem();

					if (currentItem < list.size() - 1) {
						currentItem++;
					} else {
						currentItem = 0;
					}

					vpTop.setCurrentItem(currentItem);// �л�����һ��ҳ��
					mHandler.sendEmptyMessageDelayed(0, 3000);// ������ʱ3�뷢��Ϣ,
				};
			};

			mHandler.sendEmptyMessageDelayed(0, 3000);// ��ʱ3�����Ϣ
		}
	}
	
	/**  
     * ���¼���ListView�ĸ߶ȣ����ScrollView��ListView����View���й�����Ч������Ƕ��ʹ��ʱ���ͻ������  
     * @param listView  
     */  
    public void setListViewHeight(ListView listView) {    
            
        // ��ȡListView��Ӧ��Adapter    
        
        Adapter listAdapter = listView.getAdapter();    
        
        if (listAdapter == null) {    
            return;    
        }    
        int totalHeight = 0;    
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()�������������Ŀ    
            View listItem = listAdapter.getView(i, null, listView);    
            listItem.measure(0, 0); // ��������View �Ŀ��    
            totalHeight += listItem.getMeasuredHeight(); // ͳ������������ܸ߶�    
            float height = DeviceInfo.getScreenHeight(activity);
            totalHeight += height / 20; //����Ϊ�˽��listview���һ����ʾ��ȫ������
        }    
        
        ViewGroup.LayoutParams params = listView.getLayoutParams();    
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));    
        listView.setLayoutParams(params);    
    }    

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//����������е����Ӧ����bug���ʽ���ŵ���MainActivity
//		case R.id.iv_add:
//			Builder builder = new Builder(activity);
//			final String[] items = { "���Һ���", "ɨһɨ" };
//			builder.setItems(items, new DialogInterface.OnClickListener() {
//
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					Toast.makeText(activity, items[which], Toast.LENGTH_LONG)
//							.show();
//				}
//			});
//			AlertDialog alertDialog = builder.create();
//			Window win = alertDialog.getWindow();
//			// LayoutParams params = new LayoutParams();
//			// params.x = 80;//����x����
//			// params.y = 60;//����y����
//			// win.setAttributes(params);
//			alertDialog.show();
//			
//			break;

		case R.id.et_search:
			startActivity(new Intent(activity, SearchActivity.class));
		default:
			break;
		}
	}

}
