package com.lazy.gank.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.lazy.gank.app.MyApplication;

public class DataManager {
	private static DataManager mDataManager;
	private static Context context;
	private static final String name = "preferences";
	private int mode;
	//成员变量 cache
	private String umengChannelName = "";

	private DataManager() {
		this.mode = Context.MODE_PRIVATE;
	}

	public static DataManager getInstance() {
		if (mDataManager == null) {
			synchronized (DataManager.class) {
				if (mDataManager == null) {
					context = MyApplication.getAppication();
					mDataManager = new DataManager();
				}
			}
		}
		return mDataManager;
	}

	public SharedPreferences getSharedPreferences() {
		SharedPreferences localSharedPreferences = context.getSharedPreferences(name, mode);
		return localSharedPreferences;
	}

	public String getString(String paramString1, String paramString2) {
		return getSharedPreferences().getString(paramString1, paramString2);
	}

	public int getInt(String paramString, int paramInt) {
		return getSharedPreferences().getInt(paramString, paramInt);
	}

	public long getLong(String paramString, long paramLong) {
		return getSharedPreferences().getLong(paramString, paramLong);
	}

	public boolean getBoolean(String key, boolean defValue) {
		return getSharedPreferences().getBoolean(key, defValue);
	}

	public float getFloat(String paramString, float paramFloat) {
		return getSharedPreferences().getFloat(paramString, paramFloat);
	}

	public boolean putString(String paramString1, String paramString2) {
		return getSharedPreferences().edit().putString(paramString1, paramString2).commit();
	}

	public boolean putInt(String paramString, int paramInt) {
		return getSharedPreferences().edit().putInt(paramString, paramInt).commit();
	}

	public boolean putLong(String paramString, long paramLong) {
		return getSharedPreferences().edit().putLong(paramString, paramLong).commit();
	}

	public boolean putBoolean(String paramString, boolean paramBoolean) {
		return getSharedPreferences().edit().putBoolean(paramString, paramBoolean).commit();
	}

	public boolean putFloat(String paramString, float paramFloat) {
		return getSharedPreferences().edit().putFloat(paramString, paramFloat).commit();
	}

	public SharedPreferences.Editor getEditor() {
		return getSharedPreferences().edit();
	}

	public boolean contains(String paramString) {
		return getSharedPreferences().contains(paramString);
	}

	public String getString(int paramInt, String paramString) {
		return getString(context.getString(paramInt), paramString);
	}

	public int getInt(int paramInt1, int paramInt2) {
		return getInt(context.getString(paramInt1), paramInt2);
	}

	public float getFloat(int paramInt, float paramFloat) {
		return getFloat(context.getString(paramInt), paramFloat);
	}

	public boolean getBoolean(int paramInt, boolean paramBoolean) {
		return getBoolean(context.getString(paramInt), paramBoolean);
	}

	public long getLong(int paramInt, long paramLong) {
		return getLong(context.getString(paramInt), paramLong);
	}

	public boolean putInt(int paramInt1, int paramInt2) {
		return putInt(context.getString(paramInt1), paramInt2);
	}

	public boolean putFloat(int paramInt, float paramFloat) {
		return putFloat(context.getString(paramInt), paramFloat);
	}

	public boolean putBoolean(int paramInt, boolean paramBoolean) {
		return putBoolean(context.getString(paramInt), paramBoolean);
	}

	public boolean putString(int paramInt, String paramString) {
		return putString(context.getString(paramInt), paramString);
	}

	public boolean putLong(int paramInt, long paramLong) {
		return putLong(context.getString(paramInt), paramLong);
	}

	public boolean removeKey(String paramString) {
		return getEditor().remove(paramString).commit();
	}

	public boolean removeAll() {
		return getEditor().clear().commit();
	}


	//meta-data扩展元素数据 获取
	public String getUmengChannel() {
		String umeng_name = "UMENG_CHANNEL";
		//1.获取友盟渠道名  at <application...>元素下
		if (TextUtils.isEmpty(umengChannelName)) {
			umengChannelName = getMetaData(umeng_name);
		}
		return umengChannelName;
	}

	public String getMetaData(String metaDataName) {
		String metaDataValue = "";
		try {
			Context context = MyApplication.getAppication();
			ApplicationInfo info = context.getPackageManager().getApplicationInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
			metaDataValue = info.metaData.getString(metaDataName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return metaDataValue;
	}

}
