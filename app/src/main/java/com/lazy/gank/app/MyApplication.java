package com.lazy.gank.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.lazy.gank.config.Config;
import com.lazy.gank.interfaces.IConstant;
import com.lazy.gank.internal.di.components.ApplicationComponent;
import com.lazy.gank.internal.di.components.DaggerApplicationComponent;
import com.lazy.gank.internal.di.modules.ApplicationModule;
import com.lazy.gank.logging.Builder;
import com.lazy.gank.logging.Logcat;
import com.lazy.gank.logging.MyActivityLifecycle;
import com.lazy.gank.util.ChannelUtil;
import com.lazy.gank.util.CrashHandler;
import com.lazy.gank.util.StorageUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lazy on 16/8/14.
 */

public class MyApplication extends Application implements IConstant {
	public static final String TAG = "MyAppication";
	// Just DO: 2016/9/22 Dagger
	@Deprecated
	private static MyApplication mApplication;
	private Context mContext;
	private Resources mResources;

	private MyActivityLifecycle mActivityLifecycle;

	//dagger
	private ApplicationComponent mApplicationComponent;

	@Deprecated
	@NonNull
	public static MyApplication getAppication() {
		return mApplication;
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
	@Override
	public void onCreate() {
		super.onCreate();
		initialize();
		initializeUMAnalytics();
		this.initializeInjector();
	}

	private void initializeUMAnalytics() {
		//动态设置渠道信息
		String mChannelId = ChannelUtil.getChannelFromCommentInfo(this);
		Logcat.d("startWithConfigure: mChannelId = " + mChannelId, "MobclickAgent");
		MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(this, Config.UMENG_APPKEY, mChannelId);
		MobclickAgent.startWithConfigure(config);
	}

	private void initializeInjector() {
		this.mApplicationComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
	}

	private void initialize() {
		mApplication = this;
		mContext = this.getApplicationContext();
		mResources = this.getResources();
		Builder builder = Logcat.newBuilder();
		builder.logCatLogLevel(Logcat.SHOW_VERBOSE_LOG);
//        builder.logCatLogLevel(Logcat.NOT_SHOW_LOG);
//        Logcat.initialize(this, builder.build());
		Logcat.initialize(this);
		if (IS_DEBUG_ACTIVITYLIFE) {
			mActivityLifecycle = new MyActivityLifecycle();
			this.registerActivityLifecycleCallbacks(mActivityLifecycle);
		}
		//
		Set<RequestListener> requestListeners = new HashSet<>();
		requestListeners.add(new RequestLoggingListener());
		ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
				// other setters
				.setRequestListeners(requestListeners)
				//配置图片磁盘缓存
				.setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this)
						.setBaseDirectoryName("image") //父目录下的文件夹名
						.setBaseDirectoryPath(StorageUtils.getAppCacheFile(this)) //保存父目录
						.build())
				.build();
		Fresco.initialize(this, config);
		FLog.setMinimumLoggingLevel(FLog.DEBUG);

		try {
			CrashHandler crashHandler = CrashHandler.getInstance();
			crashHandler.init(this, Config.CARSH_LOG_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@NonNull
	public Context getAppContext() {
		return mContext;
	}

	@NonNull
	public Resources getAppResources() {
		return mResources;
	}

	public ApplicationComponent getApplicationComponent() {
		return mApplicationComponent;
	}

}
