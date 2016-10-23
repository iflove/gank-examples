package com.lazy.gank.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lazy on 16/9/11.
 */
public class Category {

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

	@SerializedName("App")
	public List<GankResult> appList;
}
