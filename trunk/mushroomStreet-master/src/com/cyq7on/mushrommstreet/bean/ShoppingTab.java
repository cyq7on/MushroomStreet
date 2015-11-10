package com.cyq7on.mushrommstreet.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.adapter.DynamicAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshLayout;
import com.handmark.pulltorefresh.library.PullToRefreshLayout.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ShoppingTab {
	/**
	 * @Title: ShoppingTab.java
	 * @Package com.cyq7on.mushrommstreet.view
	 * @Description: 逛逛逛底部viewpager tab类
	 * @author cyq7on
	 * @date 2015-11-7 下午8:05:55
	 * @version V1.0
	 */
	private PullToRefreshListView listView;
	private DynamicAdapter adapter;
	private View view;
	private Context context;
	private List<DynamicVo> list;
	private List<String> tabList;
	private PullToRefreshLayout pullToRefreshLayout;

	public ShoppingTab(Context mContext, List<String> urlList,
			PullToRefreshLayout pullToRefreshLayout) {
		super();
		context = mContext;
		tabList = urlList;
		this.pullToRefreshLayout = pullToRefreshLayout;
		view = View.inflate(context, R.layout.list_listview, null);
		listView = (PullToRefreshListView ) view.findViewById(R.id.listview);
		listView.setMode(Mode.BOTH);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(context, list.get(arg2).getUserName(),
						Toast.LENGTH_LONG).show();
			}
		});
		if (pullToRefreshLayout != null) {
			pullToRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
				
				@Override
				public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
					ShoppingTab.this.pullToRefreshLayout.refreshFinish(0);
				}
				
				@Override
				public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
					ShoppingTab.this.pullToRefreshLayout.loadmoreFinish(0);
				}
			});
		}
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			//下拉刷新
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
//				listView.onRefreshComplete();
				listView.postDelayed(new Runnable() {
		            @Override
		            public void run() {
		            	listView.onRefreshComplete();
		            }
		        }, 1000);
			}
			//上拉加载更多
			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
//				listView.onRefreshComplete();
				listView.postDelayed(new Runnable() {
		            @Override
		            public void run() {
		            	listView.onRefreshComplete();
		            }
		        }, 1000);
			}
		});
	}

	public void initData(String info) {
		this.list = new ArrayList<DynamicVo>();
		this.list.addAll(getData(info));
		adapter = new DynamicAdapter(context, list);
		listView.setAdapter(adapter);
		
	}
	
	// 请求服务器数据在此进行
	private List<DynamicVo> getData(String info) {
		ArrayList<DynamicVo> list = new ArrayList<DynamicVo>();
		for (int i = 0; i < 4; i++) {
			// 这里为了简便就添加了部分信息
			DynamicVo data = new DynamicVo();
			data.setUserName(info + i);
			data.setUrl("http://www.qq1234."
					+ "org/uploads/allimg/150706/8_150706145211_9.jpg");
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
			data.setDate(sdf.format(new Date()));
			data.setTvPlace("成都" + i);
			List<String> urlList = new ArrayList<String>();
			// 这里为了显示不同的图片张数
			for (int j = 0; j <= i; j++) {
				urlList.add(tabList.get(j % tabList.size()));
			}
			data.setContentImageurl(urlList);
			list.add(data);
		}
		return list;
	}

	public View getView() {
		return view;
	}

	public DynamicAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(DynamicAdapter adapter) {
		this.adapter = adapter;
	}

}
