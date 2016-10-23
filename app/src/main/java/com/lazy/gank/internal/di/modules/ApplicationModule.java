package com.lazy.gank.internal.di.modules;


import android.content.Context;

import com.lazy.gank.app.MyApplication;
import com.lazy.gank.internal.di.rules.IApplicationRule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sky on 2016/9/21.
 *
 * @Describe Dagger module that provides objects which will live during the application lifecycle.
 */

@Module
public class ApplicationModule implements IApplicationRule{
	private final MyApplication mAppication;

	public ApplicationModule(MyApplication mAppication) {
		this.mAppication = mAppication;
	}

	@Provides
	@Singleton
	@Override
	public MyApplication getMyApplication() {
		return mAppication;
	}

	@Provides
	@Singleton
	@Override
	public Context getAppContext() {
		return this.mAppication.getAppContext();
	}

}
