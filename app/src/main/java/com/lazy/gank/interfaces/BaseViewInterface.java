package com.lazy.gank.interfaces;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.lazy.gank.app.MyApplication;

public interface BaseViewInterface {

	public void findViews();

	public void setViewListener();

	/**
	 * 加载数据
	 */
	public void processExtraData();

	void showMessage(String msg);

	//规范获取
	@NonNull
	Activity getActivity();

	void startActivity(Class<? extends Activity> clazz);

	@NonNull
	MyApplication getMyAppication();
}