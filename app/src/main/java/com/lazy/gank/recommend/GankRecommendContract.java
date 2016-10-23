package com.lazy.gank.recommend;

import com.lazy.gank.entity.DayGankResults;
import com.lazy.gank.entity.GankResult;
import com.lazy.gank.interfaces.mvp.BasePresenter;
import com.lazy.gank.interfaces.mvp.BaseView;

import java.util.List;

/**
 * Created by Sky on 2016/9/12.
 *
 * @Describe
 */

public interface GankRecommendContract {

	interface View extends BaseView {
		void addGanks(List<GankResult> ganks);

		void showDayGankResults(DayGankResults dayGankResults);
	}

	interface Presenter extends BasePresenter {
		void showHistoryGank();

		void showRecommendGank(String year, String mouth, String day);
	}

}
