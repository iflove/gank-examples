package com.lazy.gank.entity;

import java.util.List;

/**
 * Created by lazy on 16/9/11.
 */
public class DayGankResults {
	public List<String> category;
	public boolean error;
	public CategoryResult results;


	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public CategoryResult getResults() {
		return results;
	}

	public void setResults(CategoryResult results) {
		this.results = results;
	}
}
