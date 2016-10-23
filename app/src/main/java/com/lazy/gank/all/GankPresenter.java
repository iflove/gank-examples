package com.lazy.gank.all;

import com.lazy.gank.entity.GankResult;
import com.lazy.gank.entity.GankResults;
import com.lazy.gank.network.GankService;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sky on 2016/9/21.
 *
 * @Describe
 */

public class GankPresenter implements GankContract.Presenter {
	GankContract.View mView;
	GankService mGankService;

	@Inject
	public GankPresenter() {
	}

	@Inject
	public void setGankService(GankService mGankService) {
		this.mGankService = mGankService;
	}

	public void setView(GankContract.View mView) {
		this.mView = mView;
	}

	@Override
	public void showGank(int page, int count) {
		Subscription subscribe = mGankService
				.getCategoryData(mView.getCategory(), count, page)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<GankResults<GankResult>>() {

					@Override
					public void onStart() {
						super.onStart();
						mView.showLoading();
					}

					@Override
					public void onCompleted() {
						mView.hideLoading();
					}

					@Override
					public void onError(Throwable e) {
						mView.showMessage("网络错误");
					}

					@Override
					public void onNext(GankResults<GankResult> results) {
						mView.showGank(results);
					}
				});
	}

	@Override
	public void subscribe() {
		showGank(mView.getCurrentPage(), mView.getCount());
	}

	@Override
	public void unsubscribe() {

	}
}
