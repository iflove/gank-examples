package com.lazy.gank.internal.di.components;

import com.lazy.gank.base.BaseActivity;
import com.lazy.gank.internal.di.modules.ApplicationModule;
import com.lazy.gank.internal.di.modules.RetrofitModule;
import com.lazy.gank.internal.di.rules.IApplicationRule;
import com.lazy.gank.internal.di.rules.IRetrofitRule;
import com.lazy.gank.internal.di.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sky on 2016/9/21.
 *
 * @Describe A component whose lifetime is the life of the application.
 */
@ApplicationScope
@Singleton
@Component(modules = {ApplicationModule.class, RetrofitModule.class})
public interface ApplicationComponent extends IApplicationRule, IRetrofitRule {

	//注入对象
	void inject(BaseActivity baseActivity);

}


