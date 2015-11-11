package com.cyq7on.mushrommstreet.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyq7on.mushrommstreet.activity.MainActivity;

public class BaseFragment extends Fragment{
	
	public View view;
	protected MainActivity activity;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		activity = (MainActivity) getActivity();
		initView();
		initListener();
		initData();
		
		return view;
	}

	public void initView(){
		
	}
	
	public void initListener(){
		
	}
	
	public void initData(){
		
	}
	
}
