package com.lazy.gank.internal.di.rules;

import android.content.Context;

import com.lazy.gank.app.MyApplication;

public interface IApplicationRule {

	MyApplication getMyApplication();

	Context getAppContext();

	// TODO: 2016/9/21
}