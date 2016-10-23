package com.lazy.gank.entity;

import java.util.List;

/**
 * Created by lazy on 16/9/11.
 */

public class GankResults<E> {
	public boolean error;

	public List<E> results;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public List<E> getResults() {
		return results;
	}

	public void setResults(List<E> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "GankResults{" +
				"error=" + error +
				", results=" + results +
				'}';
	}
}
