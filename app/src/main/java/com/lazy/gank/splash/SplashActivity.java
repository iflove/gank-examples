package com.lazy.gank.splash;

import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lazy.gank.R;
import com.lazy.gank.base.BaseActivity;
import com.lazy.gank.home.MainActivity;
import com.lazy.gank.internal.di.components.ActivityComponent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {
	@BindView(R.id.splash_default)
	SimpleDraweeView splashDefault;
	@BindView(R.id.splash_copy)
	SimpleDraweeView splashCopy;
	private Subscription subscription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		processExtraData();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (!subscription.isUnsubscribed()) {
			subscription.unsubscribe();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void findViews() {
		setContentView(R.layout.activity_splash);
		ButterKnife.bind(this);

		splashDefault.setImageURI(Uri.parse("asset:///" + "splash/ic_splash_default.png"));
		splashCopy.setImageURI(Uri.parse("asset:///" + "splash/ic_splash_copy.png"));
	}

	@Override
	public void setViewListener() {

	}

	@Override
	public void processExtraData() {
		subscription = Observable.interval(1, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Long>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onNext(Long aLong) {
						if (aLong == 2) {
							unsubscribe();
							startActivity(MainActivity.class);
							finish();
						}
					}
				});
	}

	@Override
	protected void initializeInjector(ActivityComponent activityComponent) {

	}

}
