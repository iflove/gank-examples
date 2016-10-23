package com.lazy.gank.config;

import com.lazy.gank.app.MyApplication;
import com.lazy.gank.util.StorageUtils;

/**
 * Created by Sky on 2016/10/13.
 *
 * @Describe
 */

public final class Config {
	//Created by Sky on 2016/9/21.
	public static final String CARSH_LOG_PATH = StorageUtils.getDiskCacheDir(MyApplication.getAppication(), "carsh").getAbsolutePath();

	public static final String UMENG_APPKEY = "57fee4b6e0f55ad70f001108";

}
