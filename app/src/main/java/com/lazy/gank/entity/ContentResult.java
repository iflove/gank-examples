package com.lazy.gank.entity;

import java.util.Date;

/**
 * Created by lazy on 16/9/11.
 */
public class ContentResult {

	public String title;

	public String content;

	public Date publishedAt;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}
}
