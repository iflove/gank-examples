package com.lazy.gank.entity;

import java.util.Date;

/**
 * Created by lazy on 16/9/11.
 */
public class SearchResult {

	public Date publishedAt;

	public String type;

	public String url;

	public String desc;

	public String who;

	public String readability;


	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getReadability() {
		return readability;
	}

	public void setReadability(String readability) {
		this.readability = readability;
	}

	@Override
	public String toString() {
		return "SearchResult{" +
				"publishedAt=" + publishedAt +
				", type='" + type + '\'' +
				", url='" + url + '\'' +
				", desc='" + desc + '\'' +
				", who='" + who + '\'' +
				", readability='" + readability + '\'' +
				'}';
	}
}
