package com.lazy.gank.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.lazy.gank.BuildConfig;
import com.lazy.gank.app.MyApplication;
import com.lazy.gank.interfaces.BaseViewInterface;
import com.lazy.gank.interfaces.IConstant;
import com.lazy.gank.internal.di.HasComponent;
import com.lazy.gank.internal.di.components.ActivityComponent;
import com.lazy.gank.internal.di.components.ApplicationComponent;
import com.lazy.gank.internal.di.components.DaggerActivityComponent;
import com.lazy.gank.internal.di.modules.ActivityModule;
import com.lazy.gank.util.ActManager;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by lazy on 16/9/11.
 */
public abstract class BaseActivity extends AppCompatActivity implements IConstant, BaseViewInterface, HasComponent<ActivityComponent> {

	protected final String TAG = this.getClass().getSimpleName();

	//dagger
	protected ActivityComponent mActivityComponent;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getApplicationComponent().inject(this);
		initializeInjector();
		initializeInjector(mActivityComponent);
//		this.getNetworkComponent().inject(this);
		ActManager.addActivity(this);
		if (!BuildConfig.DEBUG) {
			MobclickAgent.setDebugMode(false);
			MobclickAgent.openActivityDurationTrack(false);
			//发送策略定义了用户由统计分析SDK产生的数据发送回友盟服务器的频率。
//			MobclickAgent.updateOnlineConfig(this);
		}
		findViews();
		setViewListener();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
//		setIntent(intent);

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!BuildConfig.DEBUG) {
			MobclickAgent.onResume(this);
			MobclickAgent.onPageStart(this.getClass().getSimpleName());
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (!BuildConfig.DEBUG) {
			MobclickAgent.onPause(this);
			MobclickAgent.onPageEnd(this.getClass().getSimpleName());
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActManager.removeActivity(this);
	}

	/**
	 * 绑定 View 控件
	 *
	 * @param id
	 * @param <T>
	 * @return
	 */
	protected <T> T bindViewById(@IdRes int id) {
		//noinspection unchecked
		return (T) super.findViewById(id);
	}

	@Override
	public abstract void findViews();

	@Override
	public abstract void setViewListener();

	@Override
	public abstract void processExtraData();

	@NonNull
	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	public void startActivity(Class<? extends Activity> clazz) {
		Intent intent = new Intent(this, clazz);
		startActivity(intent);
	}

	@NonNull
	@Override
	public MyApplication getMyAppication() {
		return MyApplication.getAppication();
	}

	@Override
	public void showMessage(@NonNull String msg) {
		Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show();
	}

	/**
	 * Get the Main Application component for dependency injection.
	 *
	 * @return {@link ApplicationComponent}
	 */
	protected ApplicationComponent getApplicationComponent() {
		return getMyAppication().getApplicationComponent();
	}

	/**
	 * Get an Activity module for dependency injection.
	 *
	 * @return {@link ActivityModule}
	 */
	protected ActivityModule getActivityModule() {
		return new ActivityModule(this);
	}

	private void initializeInjector() {
		mActivityComponent = DaggerActivityComponent.builder()
				.applicationComponent(getApplicationComponent())
				.activityModule(new ActivityModule(getActivity()))
				.build();
	}

	protected abstract void initializeInjector(@NonNull ActivityComponent activityComponent);

	@Override
	public ActivityComponent getComponent() {
		return mActivityComponent;
	}
}
