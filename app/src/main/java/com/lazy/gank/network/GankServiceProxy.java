package com.lazy.gank.network;

/**
 * Created by Sky on 2016/9/12.
 *
 * @Describe
 */

public class GankServiceProxy {
	private static GankService sGankService;

	public static GankService getGankService() {
		if (sGankService == null) {
			synchronized (GankServiceProxy.class) {
				if (sGankService == null) {
					sGankService = RetrofitManager.getInstance().getGankRetrofit().create(GankService.class);
				}
			}
		}
		return sGankService;
	}

}
