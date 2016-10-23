package com.lazy.gank.entity;

/**
 * Created by lazy on 16/9/11.
 */
public class GankSearchResults extends GankResults<SearchResult> {
	public int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
