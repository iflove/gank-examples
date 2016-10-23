package com.lazy.gank.meizi;

import com.lazy.gank.entity.GankResult;
import com.lazy.gank.entity.GankResults;
import com.lazy.gank.interfaces.mvp.BasePresenter;
import com.lazy.gank.interfaces.mvp.BaseView;

/**
 * Created by Sky on 2016/9/20.
 *
 * @Describe
 */

public interface MeiziContract {

	interface Presenter extends BasePresenter {
		void showMeizi(int page, int count);

		void setView(View view);
	}

	interface View extends BaseView {
		void addMeizi(GankResults<GankResult> results);

		int getCurrentPage();

		int getCount();
	}

}
