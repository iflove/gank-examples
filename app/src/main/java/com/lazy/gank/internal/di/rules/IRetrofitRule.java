package com.lazy.gank.internal.di.rules;

import com.lazy.gank.network.GankService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Sky on 2016/9/26.
 *
 * @Describe
 */

public interface IRetrofitRule {
	// TODO: 2016/9/21

	OkHttpClient defaultOkHttpClient();

	Retrofit getGankRetrofit();

	GankService getGankService();
}
