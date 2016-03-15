package com.bin.zhbj.base;

import android.app.Activity;
import android.view.View;

public abstract class BaseMenuDetailPager {
	public Activity  mActivity;
	public View mRootView;//根布局对象
	public BaseMenuDetailPager(Activity activity){
		mActivity=activity;
		mRootView=initView();
	}
	public abstract View initView();
	public void initData(){
		
	}	
	
}
