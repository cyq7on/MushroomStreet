package com.cyq7on.mushrommstreet.view;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyq7on.mushrommstreet.R;


public class TitleBar extends FrameLayout {

	private Button btnBackc;

	private TextView tvTitle;

	private Button btnNext;
	
	private ImageView imageTitle;
	

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initTitleBar();
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initTitleBar();
	}

	public TitleBar(Context context) {
		super(context);
		initTitleBar();
	}

	private void initTitleBar() {
		LayoutInflater.from(getContext()).inflate(R.layout.title_bar, this);
		btnBackc = (Button) findViewById(R.id.btnBack);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		btnNext = (Button) findViewById(R.id.btnNext);
		imageTitle=  (ImageView) findViewById(R.id.imageTitle);
		btnBackc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity) getContext()).onBackPressed();
			}
		});
	}

	public void setTitle(int titleId) {
		tvTitle.setText(titleId);
	}
	public void setTitle(String title) {
		tvTitle.setText(title);
	}

	public void setLeftText(int leftTextId) {
		btnBackc.setText(leftTextId);
	}

	public void setRightText(int rightTextId) {
		btnNext.setText(rightTextId);
	}
	
	public void setRightText(String text) {
		btnNext.setText(text);
	}

	public Button getLeftButton() {
		return btnBackc;
	}

	public Button getRightButton() {
		return btnNext;
	}

	public void setLeftsetBackground(int i) {
		btnBackc.setBackgroundResource(i);
	}

	public void setRihtBackground(int i) {
		btnNext.setBackgroundResource(i);
	}
	public void setImageTitleBackground(int i) {
		imageTitle.setBackgroundResource(i);
	}
	public void setRightButtonListener(View.OnClickListener listener) {
		btnNext.setOnClickListener(listener);
	}
	public void setLeftButtonListener(View.OnClickListener listener) {
		btnBackc.setOnClickListener(listener);
	}
	public void setTitleButtonListener(View.OnClickListener listener) {
		tvTitle.setOnClickListener(listener);
	}
	public void setImageTitleVisible(){
		imageTitle.setVisibility(VISIBLE);
	}
	public void setLeftVisible(){
		btnBackc.setVisibility(VISIBLE);
	}
	public void setNextWidth(int i,int j){
		btnNext.setHeight(i);
		btnNext.setWidth(j);
		
	}
	
	public void setRightVisible(){
		btnNext.setVisibility(VISIBLE);
	}
}
