package com.lazy.gank.interfaces.mvp;

/**
 */
public interface BaseView {

	void showMessage(String msg);

	void showLoading();

	void hideLoading();

	// TODO: 2016/9/26 Dagger2 DI 注入
//	void setPresenter(T presenter);
}