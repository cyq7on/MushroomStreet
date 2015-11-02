package com.cyq7on.mushrommstreet.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.activity.MainActivity;
import com.cyq7on.mushrommstreet.shoppingfragment.activity.SearchActivity;
import com.cyq7on.mushrommstreet.view.HorizontalListView;
import com.example.mushroomstreet.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.CirclePageIndicator;


public class ShoppingFragment extends BasicFragment implements OnClickListener{

	private ImageView ivAdd;
	private EditText etSearch;
	private ViewPager vpTop;
	//轮播图和griveiw的数据源，这里为了方便二者用了同一个数据源
	private List<ImageView> list = new ArrayList<ImageView>();
	private CirclePageIndicator circlePageIndicator;
	private Handler mHandler;
	private int which;//哪个图片被选中
	private GridView gridView;
	private HorizontalListView horizontalListView;
	//轮播图片地址
	private String [] url = {
			"https://img.alicdn.com/tps/TB1Z1siKXXXXXcvXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB16nMPKXXXXXcQXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1hMIUKXXXXXakXVXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/tps/TB1MsspKXXXXXcUXFXXXXXXXXXX-400-200.jpg",
			"https://img.alicdn.com/bao/uploaded/i1/TB1OwwiKXXXXXaQXpXXSutbFXXX.jpg",
			"https://img.alicdn.com/bao/uploaded/i1/TB1XzItKXXXXXbLXVXXSutbFXXX.jpg"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_shopping, container, false);
		initView();
		return view;
	}
	
	@Override
	public void initView() {
		activity = (MainActivity) getActivity();
		ivAdd = (ImageView) view.findViewById(R.id.iv_add);
		etSearch = (EditText) view.findViewById(R.id.et_search);
//		gridView = (GridView) view.findViewById(R.id.gridview);
		horizontalListView = (HorizontalListView) 
				view.findViewById(R.id.horizontallistview);
		
		horizontalListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(activity, arg2+"", Toast.LENGTH_LONG).show();
			}
		});
		ivAdd.setOnClickListener(this);
		etSearch.setOnClickListener(this);
		initViewPager();
		horizontalListView.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
//				ImageView iv;
//				if (convertView == null) {
//					convertView = LayoutInflater.from(activity).inflate(
//							R.layout.item_hlistview, null);
//					iv = (ImageView) convertView.findViewById(R.id.iv);
//					convertView.setTag(iv);
//				}else {
//					iv = (ImageView) convertView.getTag();
//				}
//				return iv;
//				return list.get(position);
				
				View view = LayoutInflater.from(activity).inflate(
						R.layout.item_hlistview, null);
				return view;
			}
			
			@Override
			public int getCount() {
				return url.length;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
//		gridView.setAdapter(new BaseAdapter() {
//			
//			@Override
//			public View getView(int position, View convertView, ViewGroup parent) {
//				// TODO Auto-generated method stub
//				ImageView imageView;  
//	            if(convertView==null){  
//	                imageView=new ImageView(activity);  
//	                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));  
//	                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);  
//	                imageView.setPadding(8, 8, 8, 8);  
//	            }else{  
//	                imageView = (ImageView) convertView;  
//	            }  
////	            imageView = list.get(position);  
//	            imageView.setImageResource(R.drawable.ic_launcher);
//	            return imageView;  
//			}
//			
//			@Override
//			public long getItemId(int position) {
//				// TODO Auto-generated method stub
//				return position;
//			}
//			
//			@Override
//			public Object getItem(int position) {
//				// TODO Auto-generated method stub
//				return list.get(position);
//			}
//			
//			@Override
//			public int getCount() {
//				// TODO Auto-generated method stub
//				return list.size() +6;
//			}
//		});
	}
	
	private void initViewPager() {
		vpTop = (ViewPager) view.findViewById(R.id.vp_top);
		circlePageIndicator = (CirclePageIndicator) 
				view.findViewById(R.id.circlepageindicator);
		ImageView iv;
		for (int i = 0; i < url.length; i++) {
			//显示图片的配置  
	        DisplayImageOptions options = new DisplayImageOptions.Builder()  
//	                .showImageOnLoading(R.drawable.ic_launcher)  
//	                .showImageOnFail(R.drawable.ic_launcher)  
	                .cacheInMemory(true)  
	                .cacheOnDisk(true)  
	                .bitmapConfig(Bitmap.Config.RGB_565)  
	                .build();  
	        iv = new ImageView(activity);  
	        iv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(activity, "您选择了第"+(which+1)+"张", Toast.LENGTH_LONG).show();
				}
			});
	        ImageLoader.getInstance().displayImage(url[i], iv, options);  
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
			public void destroyItem(ViewGroup container, int position, Object object) {
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
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_add:
			Builder builder = new Builder(activity);
			final String[] items = { "查找好友", "扫一扫"};
			builder.setItems(items, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(activity, items[which], Toast.LENGTH_LONG).show();
				}
			});
			AlertDialog alertDialog = builder.create();
			Window win = alertDialog.getWindow();
			LayoutParams params = new LayoutParams();
			params.x = 80;//设置x坐标
			params.y = 60;//设置y坐标
			win.setAttributes(params);
			alertDialog.show();
			break;

		case R.id.et_search:
			startActivity(new Intent(activity,SearchActivity.class));
		default:
			break;
		}
	}

}
