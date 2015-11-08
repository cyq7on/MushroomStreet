package com.cyq7on.mushrommstreet.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.adapter.DynamicAdapter;
import com.example.mushroomstreet.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class ShoppingTab {
	/**
	 * @Title: ShoppingTab.java
	 * @Package com.cyq7on.mushrommstreet.view
	 * @Description: 逛逛逛底部viewpager tab类
	 * @author cyq7on
	 * @date 2015-11-7 下午8:05:55
	 * @version V1.0
	 */
	private ListView listView;
	private DynamicAdapter adapter;
	private View view;
	private Context context;
	private List<DynamicVo> list;
	private List<String> tabList;
	private PullToRefreshScrollView pullRefreshScrollView;

	public ShoppingTab(Context mContext, List<String> urlList,
			PullToRefreshScrollView scrollView) {
		super();
		context = mContext;
		tabList = urlList;
		pullRefreshScrollView = scrollView;
		view = View.inflate(context, R.layout.list_listview, null);
		listView = (ListView) view.findViewById(R.id.listview);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(context, list.get(arg2).getUserName(),
						Toast.LENGTH_LONG).show();
			}
		});
		listView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					pullRefreshScrollView.setMode(Mode.DISABLED);
				}
//				if (event.getAction() == MotionEvent.ACTION_UP) {
//					pullRefreshScrollView.setMode(Mode.PULL_FROM_START);
//				}
				return false;
			}
		});
	}

	// 请求服务器数据在此进行
	public List<DynamicVo> initData(String info) {
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
		this.list = new ArrayList<DynamicVo>();
		this.list.addAll(list);
		adapter = new DynamicAdapter(context, list);
		listView.setAdapter(adapter);
		return list;
	}

	public View getView() {
		return view;
	}

	public ListView getListView() {
		return listView;
	}

	public void setListView(ListView listView) {
		this.listView = listView;
	}

	public DynamicAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(DynamicAdapter adapter) {
		this.adapter = adapter;
	}

}
