package com.cyq7on.mushrommstreet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.Pullable;
/**
 * @blog http://blog.csdn.net/xiaanming
 * 
 * @author xiaanming
 *
 */
public class StickyScrollView extends ScrollView implements Pullable{
	private OnScrollListener onScrollListener;
	
	public StickyScrollView(Context context) {
		this(context, null);
	}
	
	public StickyScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public StickyScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	
	/**
	 * ���ù����ӿ�
	 * @param onScrollListener
	 */
	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}
	

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(onScrollListener != null){
			onScrollListener.onScroll(t);
		}
	}





	/**
	 * 
	 * �����Ļص��ӿ�
	 * 
	 * @author xiaanming
	 *
	 */
	public interface OnScrollListener{
		/**
		 * �ص������� ����MyScrollView������Y�������
		 * @param scrollY
		 * 				��
		 */
		public void onScroll(int scrollY);
	}
	
	@Override
	public boolean canPullDown()
	{
		if (getScrollY() == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean canPullUp()
	{
		if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
			return true;
		else
			return false;
	}

}
