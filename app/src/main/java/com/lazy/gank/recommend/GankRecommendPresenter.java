package com.lazy.gank.recommend;

import android.support.annotation.NonNull;

import com.lazy.gank.entity.CategoryResult;
import com.lazy.gank.entity.DayGankResults;
import com.lazy.gank.entity.GankResult;
import com.lazy.gank.entity.GankResults;
import com.lazy.gank.internal.di.scope.ActivityScope;
import com.lazy.gank.network.GankService;
import com.lazy.gank.network.GankServiceProxy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Sky on 2016/9/12.
 *
 * @Describe
 */
@ActivityScope
public class GankRecommendPresenter implements GankRecommendContract.Presenter {
	GankRecommendContract.View mView;

	GankService mGankService;

	private CompositeSubscription mCompositeSubscription;

	//构造器参数注入
	@Inject
	public GankRecommendPresenter(GankService mGankService) {
		this.mGankService = mGankService;
		mCompositeSubscription = new CompositeSubscription();
	}

	public void setView(@NonNull GankRecommendContract.View mView) {
		this.mView = mView;
	}


	@Override
	public void subscribe() {
		showHistoryGank();
	}

	@Override
	public void unsubscribe() {
		if (!mCompositeSubscription.isUnsubscribed()) {
			mCompositeSubscription.unsubscribe();
		}
	}

	@Override
	public void showHistoryGank() {
		Subscription subscription = this.mGankService //没用Dagger  GankServiceProxy.getGankService()
				.getDayHistoty()
				.subscribeOn(Schedulers.io())
				.flatMap(new Func1<GankResults<String>, Observable<DayGankResults>>() {
					@Override
					public Observable<DayGankResults> call(GankResults<String> stringGankResults) {
						String lastDate = stringGankResults.getResults().get(10);
						String[] dateWords = lastDate.split("-");
						return GankServiceProxy.getGankService().getGankByDate(dateWords[0],
								dateWords[1], dateWords[2]);
					}
				})
				.map(new Func1<DayGankResults, List<GankResult>>() {
					@Override
					public List<GankResult> call(DayGankResults dayGankResults) {
						CategoryResult category = dayGankResults.getResults();
						List<GankResult> gankResults = new ArrayList<GankResult>();
						if (category != null) {
							gankResults.addAll(category.androidList == null ? new ArrayList<GankResult>(0) : category.androidList);
							gankResults.addAll(category.iOSList == null ? new ArrayList<GankResult>(0) : category.iOSList);
							gankResults.addAll(category.meizhiList == null ? new ArrayList<GankResult>(0) : category.meizhiList);
							gankResults.addAll(category.extendSourceList == null ? new ArrayList<GankResult>(0) : category.extendSourceList);
							gankResults.addAll(category.recommandList == null ? new ArrayList<GankResult>(0) : category.recommandList);
						}

						return gankResults;
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<List<GankResult>>() {

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
					public void onNext(List<GankResult> gankResults) {
						mView.addGanks(gankResults);
					}

				});
		mCompositeSubscription.add(subscription);

	}

	@Override
	public void showRecommendGank(String year, String mouth, String day) {

	}
}
