package com.lazy.gank.internal.di.modules;

import com.lazy.gank.interfaces.IConstant;
import com.lazy.gank.internal.di.rules.IRetrofitRule;
import com.lazy.gank.logging.Logcat;
import com.lazy.gank.network.GankService;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jude.fitsystemwindowlayout.Utils.TAG;

/**
 * Created by Sky on 2016/9/21.
 *
 * @Describe
 */
@Module
public class RetrofitModule implements IRetrofitRule {


	@Provides
	@Singleton
	@Override
	public OkHttpClient defaultOkHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.addNetworkInterceptor(new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();
				Logcat.i("intercept: " + request.url().toString(), TAG);

				Response proceed = chain.proceed(request);
				return proceed;
			}
		});
		return builder.build();
	}

	@Provides
	@Singleton
	@Override
	public Retrofit getGankRetrofit() {
		Retrofit mGankRetrofit = new retrofit2.Retrofit.Builder()
				.baseUrl(IConstant.GANHUO_API)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.client(defaultOkHttpClient())
				.build();
		return mGankRetrofit;
	}

	@Provides
	@Singleton
	@Override
	public GankService getGankService() {
		return getGankRetrofit().create(GankService.class);
	}
}
