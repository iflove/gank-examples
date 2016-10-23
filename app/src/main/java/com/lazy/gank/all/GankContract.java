package com.lazy.gank.all;

import com.lazy.gank.entity.GankResult;
import com.lazy.gank.entity.GankResults;
import com.lazy.gank.interfaces.mvp.BasePresenter;
import com.lazy.gank.interfaces.mvp.BaseView;

/**
 * Created by Sky on 2016/9/21.
 *
 * @Describe
 */

public interface GankContract {

	interface Presenter extends BasePresenter {
		void showGank(int page, int count);

		void setView(View View);
	}

	interface View extends BaseView {
		String getCategory();

		int getCurrentPage();

		int getCount();

		void showGank(GankResults<GankResult> results);
	}
}
