package com.cyq7on.mushrommstreet.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.R;
import com.cyq7on.mushrommstreet.activity.CheckstandActivity;
import com.cyq7on.mushrommstreet.bean.ShoppingDetailVo;
import com.cyq7on.mushroomstreet.AppConfig;

public class MyPopupWindow implements OnDismissListener, OnClickListener{
	/**   
	 * @Title: MyPopWindow.java 
	 * @Package com.cyq7on.mushrommstreet.view 
	 * @Description: 加入购物车弹窗 
	 * @author cyq7on  
	 * @date 2015-11-16 上午9:58:31 
	 * @version V1.0   
	 */
	private PopupWindow popupWindow;
	private GridView gvColor,gvSize;
	private TextView tvCount;
	private Button btnExit,btnSub,btnPlus,btnSure;
	private List<String> colorList = new ArrayList<String>();
	private List<String> sizeList = new ArrayList<String>();
	private Context context;
	//选择的商品参数
	private String color = "";
	private String size = "";
	private ShoppingDetailVo vo;
	private boolean isBuyNow = false;
	public MyPopupWindow(Context context,List<String> mColorList,
			List<String> mSizeList,ShoppingDetailVo vo) {
		View view = LayoutInflater.from(context).
				inflate(R.layout.popupwindow_cart, null);
		this.context = context;
		this.vo = vo;
		colorList = mColorList;
		sizeList = mSizeList;
		gvColor = (GridView) view.findViewById(R.id.gv_color);
		gvSize = (GridView) view.findViewById(R.id.gv_size);
		tvCount = (TextView) view.findViewById(R.id.tv_count);
		btnExit = (Button) view.findViewById(R.id.btn_exit);
		btnSub = (Button) view.findViewById(R.id.btn_sub);
		btnPlus = (Button) view.findViewById(R.id.btn_plus);
		btnSure = (Button) view.findViewById(R.id.btn_sure);
		
		btnExit.setOnClickListener(this);
		btnSub.setOnClickListener(this);
		btnPlus.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		
		popupWindow = new PopupWindow(view,LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		// 设置popwindow的动画效果
		popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
		popupWindow.setBackgroundDrawable(new 
				ColorDrawable(Color.TRANSPARENT));
		popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
		
		
		gvColor.setAdapter(new ArrayAdapter<String>(context, 
				R.layout.item_popupwindow_gridview, colorList));
		gvSize.setAdapter(new ArrayAdapter<String>(context, 
				R.layout.item_popupwindow_gridview, sizeList));
		
		gvColor.setOnItemClickListener(new OnItemClickListener() {
			TextView tv;
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				color = colorList.get(arg2);
				for (int i = 0; i < colorList.size(); i++) {
					tv = (TextView) gvColor.getChildAt(i);
					if (i == arg2) {
						tv.setTextColor(Color.RED);
						tv.setBackgroundResource(R.
								drawable.shape_textview_pop);
					}else {
						tv.setTextColor(Color.BLACK);
						tv.setBackgroundResource(R.
								drawable.shape_btn_attention);
					}
				}
			}
		});
		
		gvSize.setOnItemClickListener(new OnItemClickListener() {
			TextView tv;
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				size = sizeList.get(arg2);
				for (int i = 0; i < sizeList.size(); i++) {
					tv = (TextView) gvSize.getChildAt(i);
					if (i == arg2) {
						tv.setTextColor(Color.RED);
						tv.setBackgroundResource(R.
								drawable.shape_textview_pop);
					}else {
						tv.setTextColor(Color.BLACK);
						tv.setBackgroundResource(R.
								drawable.shape_btn_attention);
					}
				}
			}
		});
	}
	
	/**弹窗显示的位置*/  
	public void showAsDropDown(View parent,boolean isBuyNow){
		popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		this.isBuyNow = isBuyNow;
	}
	
	// 当popWindow消失时响应
	@Override
	public void onDismiss() {
		if (isBuyNow && color.length() > 0) {
			Intent intent = new Intent(context,CheckstandActivity.class);
			intent.putExtra("allPrice", vo.getPriceAll() + "");
			context.startActivity(intent);
		}
	}
	
	/**消除弹窗*/
	public void dissmiss(){
		popupWindow.dismiss();
	}

	@Override
	public void onClick(View v) {
		int count = Integer.parseInt(tvCount.getText().toString());
		switch (v.getId()) {
		case R.id.btn_exit:
			popupWindow.dismiss();
			break;
		case R.id.btn_sure:
			if (color.equals("") || size.equals("")) {
				Toast.makeText(context, "请选择颜色和尺码", 
						Toast.LENGTH_SHORT).show();
				return;
			}
			vo.setColor(color);
			vo.setSize(size);
			vo.setCount(count);
			int k = (int) (Math.random() * 7);
			vo.setFreight(k + "");
			int price = Integer.parseInt(vo.getPriceNow());
			vo.setPriceAll(count * price);
			AppConfig.goodsList.add(vo);
			popupWindow.dismiss();
			break;
		case R.id.btn_sub:
			if (count > 1) {
				count --;
				tvCount.setText(count + "");
			}
			break;
		case R.id.btn_plus:
			count ++;
			tvCount.setText(count + "");
			break;
		default:
			break;
		}
	}

}
