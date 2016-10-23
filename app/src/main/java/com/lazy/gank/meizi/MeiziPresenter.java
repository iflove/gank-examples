package com.lazy.gank.meizi;

import com.lazy.gank.entity.GankResult;
import com.lazy.gank.entity.GankResults;
import com.lazy.gank.network.GankService;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Sky on 2016/9/20.
 *
 * @Describe
 */

public class MeiziPresenter implements MeiziContract.Presenter {

	MeiziContract.View mView;
	GankService mGankService;
	private CompositeSubscription mCompositeSubscription;

	@Inject
	public MeiziPresenter() {
		mCompositeSubscription = new CompositeSubscription();
	}

	@Inject
	public void setGankService(GankService mGankService) {
		this.mGankService = mGankService;
	}


	public void setView(MeiziContract.View mView) {
		this.mView = mView;
	}

	@Override
	public void showMeizi(int page, int count) {
		Subscription subscribe = this.mGankService //没用Dagger  GankServiceProxy.getGankService()
				.getCategoryData("福利", count, page)
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
						mView.addMeizi(results);
					}
				});
		mCompositeSubscription.add(subscribe);
	}

	@Override
	public void subscribe() {
		showMeizi(mView.getCurrentPage(), mView.getCount());
	}

	@Override
	public void unsubscribe() {
		if (!mCompositeSubscription.isUnsubscribed()) {
			mCompositeSubscription.unsubscribe();
		}
	}

}
