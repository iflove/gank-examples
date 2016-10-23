package com.lazy.gank.network;

import android.support.annotation.NonNull;

import com.lazy.gank.interfaces.IConstant;
import com.lazy.gank.logging.Logcat;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lazy on 16/9/11.
 */
class RetrofitManager {
	private static final String TAG = "RetrofitManager";
	private static RetrofitManager mRetrofitManager;
	private Retrofit mGankRetrofit;

	private RetrofitManager() {
	}

	@NonNull
	public static RetrofitManager getInstance() {
		if (mRetrofitManager == null) {
			synchronized (RetrofitManager.class) {
				if (mRetrofitManager == null) {
					mRetrofitManager = new RetrofitManager();
				}
			}
		}
		return mRetrofitManager;
	}

	@NonNull
	public Retrofit getGankRetrofit() {
		if (mGankRetrofit == null) {
			synchronized (RetrofitManager.class) {
				if (mGankRetrofit == null) {
					mGankRetrofit = new retrofit2.Retrofit.Builder()
							.baseUrl(IConstant.GANHUO_API)
							.addConverterFactory(GsonConverterFactory.create())
							.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
							.client(defaultOkHttpClient())
							.build();
				}
			}
		}
		return mGankRetrofit;
	}

	private OkHttpClient defaultOkHttpClient() {
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
}
