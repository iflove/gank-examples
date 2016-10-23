package com.lazy.gank.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ChannelUtil {

	private static final String CHANNEL_KEY = "channel";
	private static final String CHANNEL_DEFAULT = "offical";

	private static final String PREF_KEY_CHANNEL = "pref_key_channel";
	private static final String PREF_KEY_CHANNEL_VERSION = "pref_key_channel_version";

	private static String mChannel;

	/**
	 * 返回市场。  如果获取失败返回""
	 *
	 * @param context
	 * @return
	 */
	public static String getChannelFromMetaInfo(Context context) {
		return getChannelFromMetaInfo(context, CHANNEL_DEFAULT);
	}

	/**
	 * 读取apk注释
	 *
	 * @param context
	 * @return
	 */
	public static String getChannelFromCommentInfo(Context context) {
		return getChannelFromCommentInfo(context, CHANNEL_DEFAULT);
	}

	/**
	 * 返回市场。  如果获取失败返回defaultChannel
	 *
	 * @param context
	 * @param defaultChannel
	 * @return
	 */
	public static String getChannelFromCommentInfo(Context context, String defaultChannel) {
		//内存中获取
		if (!TextUtils.isEmpty(mChannel)) {
			return mChannel;
		}
		//sp中获取
		mChannel = getChannelFromSP(context);
		if (!TextUtils.isEmpty(mChannel)) {
			return mChannel;
		}
		//从apk中获取
		mChannel = getChannelFromApkComment(context);
		if (!TextUtils.isEmpty(mChannel)) {
			//保存sp中备用
			saveChannelInSP(context, mChannel);
			return mChannel;
		}
		//全部获取失败
		return defaultChannel;
	}

	/**
	 * 返回市场。  如果获取失败返回defaultChannel
	 *
	 * @param context
	 * @param defaultChannel
	 * @return
	 */
	public static String getChannelFromMetaInfo(Context context, String defaultChannel) {
		//内存中获取
		if (!TextUtils.isEmpty(mChannel)) {
			return mChannel;
		}
		//sp中获取
		mChannel = getChannelFromSP(context);
		if (!TextUtils.isEmpty(mChannel)) {
			return mChannel;
		}
		//从apk中获取
		mChannel = getChannelFromApk(context, CHANNEL_KEY);
		if (!TextUtils.isEmpty(mChannel)) {
			//保存sp中备用
			saveChannelInSP(context, mChannel);
			return mChannel;
		}
		//全部获取失败
		return defaultChannel;
	}

	/**
	 * 从apk注释中获取渠道信息
	 *
	 * @param context
	 * @return
	 */
	private static String getChannelFromApkComment(Context context) {
		//从apk包中获取
		ApplicationInfo appinfo = context.getApplicationInfo();
		String sourceDir = appinfo.sourceDir;
		File apk = new File(sourceDir);
		byte[] flavor = new byte[0];
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(apk, "r");
			randomAccessFile.seek(randomAccessFile.length() - 2);
			short offset = (short) randomAccessFile.read();

			randomAccessFile.seek(randomAccessFile.length() - offset);
			int magic = randomAccessFile.readInt();

			if (magic != 0x52560b0b) {
				System.out.println("魔数不对");
			}

			flavor = new byte[offset - 2 - 4];
			randomAccessFile.read(flavor);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

		String content = new String(flavor);
		System.out.println(content);
		return content;
	}

	/**
	 * 从apk中获取版本信息
	 *
	 * @param context
	 * @param channelKey
	 * @return
	 */
	private static String getChannelFromApk(Context context, String channelKey) {
		//从apk包中获取
		ApplicationInfo appinfo = context.getApplicationInfo();
		String sourceDir = appinfo.sourceDir;
		//默认放在meta-inf/里， 所以需要再拼接一下
		String key = "META-INF/" + channelKey;
		String ret = "";
		ZipFile zipfile = null;

		try {
			zipfile = new ZipFile(sourceDir);
			Enumeration<?> entries = zipfile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = ((ZipEntry) entries.nextElement());
//				ZipEntry entry = zipfile.getEntry("META-INF/CERT.RSA");
				String entryName = entry.getName();
				out("APK entry name --------> " + entryName);
				if (entryName.startsWith(key)) {
					ret = entryName;
					break;
				}
			}
		} catch (IOException e) {
			out(e);
		} finally {
			if (zipfile != null) {
				try {
					zipfile.close();
				} catch (IOException e) {
					out(e);
				}
			}
		}
		String[] split = ret.split("_");
		String channel = "";
		if (split != null && split.length >= 2) {
			channel = ret.substring(split[0].length() + 1);
		}
		return channel;
	}

	/**
	 * 本地保存channel & 对应版本号
	 *
	 * @param context
	 * @param channel
	 */
	private static void saveChannelInSP(Context context, String channel) {
		DataManager.getInstance().putString(PREF_KEY_CHANNEL, channel);
		DataManager.getInstance().getInt(PREF_KEY_CHANNEL_VERSION, getVersionCode(context));
	}

	/**
	 * 从sp中获取channel
	 *
	 * @param context
	 * @return 为空表示获取异常、sp中的值已经失效、sp中没有此值
	 */
	private static String getChannelFromSP(Context context) {
		int currentVersionCode = getVersionCode(context);
		if (currentVersionCode == -1) {
			//获取错误
			return "";
		}
		int versionCodeSaved = DataManager.getInstance().getInt(PREF_KEY_CHANNEL_VERSION, -1);
		if (versionCodeSaved == -1) {
			//本地没有存储的channel对应的版本号
			//第一次使用  或者 原先存储版本号异常
			return "";
		}
		if (currentVersionCode != versionCodeSaved) {
			return "";
		}
		return DataManager.getInstance().getString(PREF_KEY_CHANNEL, "");
	}

	public static int getVersionCode(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			out(e);
		}
		return -1;
	}

	static void out(Object s) {
		android.util.Log.d("", s.toString());
	}
}  