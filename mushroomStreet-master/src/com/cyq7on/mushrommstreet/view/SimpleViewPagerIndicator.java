package com.cyq7on.mushrommstreet.view;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SimpleViewPagerIndicator extends LinearLayout 
{

	private static final int COLOR_TEXT_NORMAL = 0xFF000000;
	private static final int COLOR_INDICATOR_COLOR = Color.GREEN;

	private List<String> mTitles;
	private int mTabCount;
	private int mIndicatorColor = COLOR_INDICATOR_COLOR;
	private float mTranslationX;
	private Paint mPaint = new Paint();
	private int mTabWidth,i;
	private ViewPager viewPager;

	public SimpleViewPagerIndicator(Context context)
	{
		this(context, null);
	}

	public SimpleViewPagerIndicator(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mPaint.setColor(mIndicatorColor);
		mPaint.setStrokeWidth(9.0F);
	}
	
	public void setViewPager(ViewPager view) {
		if (viewPager == view) {
			return;
		}
		if (viewPager != null) {
			viewPager.setOnPageChangeListener(null);
		}
		final PagerAdapter adapter = view.getAdapter();
		if (adapter == null) {
			throw new IllegalStateException(
					"ViewPager does not have adapter instance.");
		}
		viewPager = view;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		mTabWidth = w / mTabCount;
	}

	public void setTitles(List<String> titles)
	{
		mTitles = titles;
		mTabCount = titles.size();
		generateTitleView();

	}

	public void setIndicatorColor(int indicatorColor)
	{
		this.mIndicatorColor = indicatorColor;
	}

	@Override
	protected void dispatchDraw(Canvas canvas)
	{
		super.dispatchDraw(canvas);
		canvas.save();
		canvas.translate(mTranslationX, getHeight() - 2);
		canvas.drawLine(0, 0, mTabWidth, 0, mPaint);
		canvas.restore();
	}

	public void scroll(int position, float offset)
	{
		/**
		 * <pre>
		 *  0-1:position=0 ;1-0:postion=0;
		 * </pre>
		 */
		mTranslationX = getWidth() / mTabCount * (position + offset);
		invalidate();
	}


	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		return super.dispatchTouchEvent(ev);
	}

	private void generateTitleView()
	{
		if (getChildCount() > 0)
			this.removeAllViews();
		int count = mTitles.size();

		setWeightSum(count);
		for (int i = 0; i < count; i++)
		{
			TextView tv = new TextView(getContext());
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
					LayoutParams.MATCH_PARENT);
			lp.weight = 1;
			tv.setGravity(Gravity.CENTER);
			tv.setTextColor(COLOR_TEXT_NORMAL);
			tv.setText(mTitles.get(i));
			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			tv.setLayoutParams(lp);
			tv.setOnClickListener(new ClickListener(i));
			addView(tv);
		}
	}
	
	private class ClickListener implements OnClickListener {
		private int index;
		public ClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			viewPager.setCurrentItem(index);
		}
		
	}

}
