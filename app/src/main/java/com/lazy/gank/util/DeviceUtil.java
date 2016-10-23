package com.lazy.gank.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.lazy.gank.app.MyApplication;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Sky on 2016/9/13.
 *
 * @Describe
 */

public final class DeviceUtil {
	public static String mac = "00:00:00:00:00:00";
	public static final String defMacAddr = "00:00:00:00:00:00";
	public static int defaultMacAddrLength = defMacAddr.length();

	public static String getPkgName() {
		return MyApplication.getAppication().getPackageName();
	}

	public static String getVersionName() {
		String appversion = "";
		try {
			PackageManager packageManager = MyApplication.getAppication().getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(MyApplication.getAppication().getPackageName(), 0);
			appversion = packInfo.versionName;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return appversion;
	}

	public static String getVersion(Context context) {
		String appversion = "";
		try {
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			appversion = packInfo.versionName;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return appversion;
	}


	public static int getVersionCode(Context context) {
		int versieoncode = 0;
		try {
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			versieoncode = packInfo.versionCode;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return versieoncode;
	}

	/*
	 * Get the STB MacAddress
	 */
	private static String getMacAddress(Context context) {
		if (!isMacAddressAllZore(mac) && !StringUtils.isBlank(mac)) {
			return mac;
		}

		//优先级1使用以太网
		try {
			mac = loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, defaultMacAddrLength);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mac;
	}

	public static String getMac() {
		return mac;
	}

	public static boolean isMacAddressAllZore(String addr) {
		String source = new String(addr);
		String str = source.replaceAll(":", "0");
		String[] splits = str.split("0");

		return splits.length == 0;
	}

	/*
	 * Load file content to String
	 */
	public static String loadFileAsString(String filePath) throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}
}
