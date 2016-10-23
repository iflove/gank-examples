package com.lazy.gank.util;

/**
 * Created by Sky on 2016/9/21.
 *
 * @Describe
 */

public final class DoubleClick {
	private static final int DURATION = 1000; //
	private static long lastTimeMillis = 0; //


	public static boolean isDouble() {
		long currentTimeMillis = System.currentTimeMillis();
		if (currentTimeMillis - lastTimeMillis < DURATION) {
			return true;
		}
		lastTimeMillis = currentTimeMillis;
		return false;
	}
}
