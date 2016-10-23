package com.lazy.gank.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lazy on 16/9/12.
 */
public class CategoryResult {

	@SerializedName("Android")
	public List<GankResult> androidList;

	@SerializedName("休息视频")
	public List<GankResult> videoList;

	@SerializedName("iOS")
	public List<GankResult> iOSList;

	@SerializedName("福利")
	public List<GankResult> meizhiList;

	@SerializedName("拓展资源")
	public List<GankResult> extendSourceList;

	@SerializedName("瞎推荐")
	public List<GankResult> recommandList;

	public List<GankResult> getAndroidList() {
		return androidList;
	}

	public void setAndroidList(List<GankResult> androidList) {
		this.androidList = androidList;
	}

	public List<GankResult> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<GankResult> videoList) {
		this.videoList = videoList;
	}

	public List<GankResult> getiOSList() {
		return iOSList;
	}

	public void setiOSList(List<GankResult> iOSList) {
		this.iOSList = iOSList;
	}

	public List<GankResult> getMeizhiList() {
		return meizhiList;
	}

	public void setMeizhiList(List<GankResult> meizhiList) {
		this.meizhiList = meizhiList;
	}

	public List<GankResult> getExtendSourceList() {
		return extendSourceList;
	}

	public void setExtendSourceList(List<GankResult> extendSourceList) {
		this.extendSourceList = extendSourceList;
	}

	public List<GankResult> getRecommandList() {
		return recommandList;
	}

	public void setRecommandList(List<GankResult> recommandList) {
		this.recommandList = recommandList;
	}
}
